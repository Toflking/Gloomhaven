package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.DamageEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.HealEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.TargetEffectTiming;
import io.github.toflking.gloomhaven.rules.ability.heal.HealAbility;
import io.github.toflking.gloomhaven.rules.ability.damage.DamageAbility;
import io.github.toflking.gloomhaven.rules.ability.condition.ConditionAbility;
import io.github.toflking.gloomhaven.rules.ability.effect.EffectDurationRule;
import io.github.toflking.gloomhaven.rules.ability.effect.RetaliateAbility;
import io.github.toflking.gloomhaven.rules.ability.effect.ShieldAbility;
import io.github.toflking.gloomhaven.rules.conditions.ActiveCondition;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.execution.attack.*;
import io.github.toflking.gloomhaven.rules.ability.movement.MoveAbility;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.command.*;
import io.github.toflking.gloomhaven.rules.event.*;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.ValidationResult;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaChoice;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingFigureTargetChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.execution.movement.MovePathValidator;
import io.github.toflking.gloomhaven.rules.execution.heal.HealExecutionState;
import io.github.toflking.gloomhaven.rules.execution.damage.DamageExecutionState;
import io.github.toflking.gloomhaven.rules.execution.condition.ConditionExecutionState;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioPhase;
import io.github.toflking.gloomhaven.rules.scenario.history.CommandHistory;
import io.github.toflking.gloomhaven.rules.scenario.history.ScenarioSnapshot;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffect;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffectResolver;
import io.github.toflking.gloomhaven.rules.scenario.FigureEffectView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ScenarioEngine {
    private final ScenarioState state;
    private final CardCatalog cardCatalog;
    private final CommandHistory commandHistory;

    public ScenarioEngine(ScenarioState state, CardCatalog cardCatalog) {
        this.state = state;
        this.cardCatalog = cardCatalog;
        this.commandHistory = new CommandHistory();
    }

    public CommandResult execute(GameCommand command) {
        Map<FigureId, FigureEffectView> effectsBefore = effectViews();

        if (command instanceof UndoCommand undo) {
            return withEffectChanges(
                executeUndo(undo),
                effectsBefore
            );
        }

        ScenarioSnapshot stateBefore = state.snapshot();
        CommandResult result;

        try {
            result = executeTracked(command);
        } catch (RuntimeException exception) {
            state.restore(stateBefore);
            throw exception;
        }

        if (result.accepted()) {
            commandHistory.record(command, stateBefore);
        }

        return withEffectChanges(result, effectsBefore);
    }

    private CommandResult executeTracked(GameCommand command) {
        return switch (command) {
            case ChooseMovePathCommand move ->
                executeMovePathSelection(move);

            case ChooseFigureTargetsCommand selection ->
                executeFigureTargetSelection(selection);

            case ChooseAttackAreaCommand attack ->
                executeAreaSelection(attack);

            case PlayCardActionCommand playCardAction ->
                executePlayCardAction(playCardAction);

            case CancelActionCommand cancel ->
                executeCancel(cancel);

            case SkipActionCommand skip ->
                executeSkip(skip);

            case EndTurnCommand endTurn ->
                executeEndTurn(endTurn);

            case UndoCommand ignored ->
                throw new IllegalStateException(
                    "Undo commands cannot be recorded in command history"
                );
        };
    }

    public boolean canUndo() {
        return commandHistory.canUndo();
    }

    public int undoDepth() {
        return commandHistory.size();
    }

    private CommandResult executeUndo(UndoCommand command) {
        CommandHistory.Entry entry = commandHistory.popLatest()
            .orElse(null);

        if (entry == null) {
            return CommandResult.rejected(
                "There is no command to undo"
            );
        }

        state.restore(entry.stateBefore());
        return CommandResult.accepted(List.of(
            new UndoPerformedEvent(entry.command())
        ));
    }

    private CommandResult executeMovePathSelection(ChooseMovePathCommand command) {
        if (state.phase() != ScenarioPhase.TURN_EXECUTION) {
            return CommandResult.rejected(
                "A Movement can only happen during turn execution"
            );
        }

        ActionExecution execution = state.execution();

        if (execution == null) {
            return CommandResult.rejected(
                "There is no active action execution"
            );
        }

        if (!(execution.pendingChoice() instanceof PendingMoveChoice choice)) {
            return CommandResult.rejected("There is no pending choice for this move");
        }

        MovePathValidator movePathValidator = new MovePathValidator(state);
        ValidationResult validation = movePathValidator.validate(command.path(), choice);

        if (!validation.isValid()) {
            return CommandResult.rejected(
                validation.rejectionReason()
            );
        }

        HexPosition from = state.positionOf(choice.figureId());
        HexPosition to = command.path().getLast();

        int remaining = choice.movementPoints() - validation.movementSpent();

        List<GameEvent> events = new ArrayList<>();

        state.moveFigure(execution.actor(), to);
        execution.markAbilityPerformed();

        events.add(
            new FigureMovedEvent(execution.actor(), from, to)
        );

        if (remaining == 0) {
            execution.completeCurrentAbility();
            continueExecution(events);
        } else {
            execution.clearPendingChoice();
            execution.setPendingChoice(new PendingMoveChoice(
                choice.figureId(),
                remaining,
                choice.movementType()
            ));

            events.add(new MoveChoiceRequestedEvent(
                choice.figureId(),
                remaining,
                choice.movementType()
            ));

        }

        return CommandResult.accepted(events);
    }


    private CommandResult executeFigureTargetSelection(ChooseFigureTargetsCommand command) {
        if (state.phase() != ScenarioPhase.TURN_EXECUTION) {
            return CommandResult.rejected(
                "A target selection can only happen during turn execution"
            );
        }

        ActionExecution execution = state.execution();

        if (execution == null) {
            return CommandResult.rejected(
                "There is no active action execution"
            );
        }

        List<GameEvent> events = new ArrayList<>();
        TargetValidator targetValidator = new TargetValidator(state);
        ValidationResult validationResult;

        if (!(execution.pendingChoice() instanceof PendingFigureTargetChoice choice)) {
            return CommandResult.rejected("There is no pending figure target selection for this command");
        }

        validationResult = targetValidator.validateTargets(command.targets(), choice);

        if (!validationResult.isValid()) {
            return CommandResult.rejected(
                validationResult.rejectionReason()
            );
        }

        execution.clearPendingChoice();

        switch (choice) {
            case PendingFigureTargetChoice.Attack ignored ->
                resolveAttackTargets(validationResult.targets(), events);
            case PendingFigureTargetChoice.Heal ignored ->
                resolveHealTargets(validationResult.targets(), events);
            case PendingFigureTargetChoice.Damage ignored ->
                resolveDamageTargets(validationResult.targets(), events);
            case PendingFigureTargetChoice.Condition ignored ->
                resolveConditionTargets(validationResult.targets(), events);
            default -> {
                return CommandResult.rejected("There is no pending figure target choice");
            }
        }

        return CommandResult.accepted(events);
    }

    private CommandResult executeAreaSelection(ChooseAttackAreaCommand command) {
        if (state.phase() != ScenarioPhase.TURN_EXECUTION) {
            return CommandResult.rejected(
                "An area selection can only happen during turn execution"
            );
        }

        ActionExecution execution = state.execution();

        if (execution == null) {
            return CommandResult.rejected(
                "There is no active action execution"
            );
        }

        if (!(execution.pendingChoice() instanceof PendingAreaChoice choice)) {
            return CommandResult.rejected(
                "There is no pending area selection for this command"
            );
        }

        List<GameEvent> events = new ArrayList<>();
        TargetValidator targetValidator = new TargetValidator(state);
        ValidationResult validationResult;

        validationResult = targetValidator.validateArea(command.placement(), choice);

        if (!validationResult.isValid()) {
            return CommandResult.rejected(
                validationResult.rejectionReason()
            );
        }

        execution.clearPendingChoice();

        switch (choice) {
            case PendingAreaChoice.Attack ignored ->
                resolveAttackTargets(validationResult.targets(), events);
            case PendingAreaChoice.Heal ignored ->
                resolveHealTargets(validationResult.targets(), events);
            case PendingAreaChoice.Damage ignored ->
                resolveDamageTargets(validationResult.targets(), events);
            case PendingAreaChoice.Condition ignored ->
                resolveConditionTargets(validationResult.targets(), events);
            default -> {
                return CommandResult.rejected(
                    "There is no pending area choice"
                );
            }
        }

        return CommandResult.accepted(events);
    }

    private CommandResult executePlayCardAction(PlayCardActionCommand command) {
        AbilitySource source = command.source();

        CardAction action = cardCatalog.resolve(source);

        state.startActionExecution(
            state.currentRound().activeFigure(),
            source,
            action.abilities()
        );

        List<GameEvent> events = new ArrayList<>();

        continueExecution(events);

        return CommandResult.accepted(events);
    }

    private void continueExecution(List<GameEvent> events) {
        ActionExecution execution = state.execution();

        if (execution.isFinished()) {
            state.finishActionExecution();

            events.add(new ActionFinishedEvent(
                execution.actor(),
                execution.source()
            ));
            return;
        }

        beginCurrentAbility(events);
    }

    private void beginCurrentAbility(List<GameEvent> events) {
        Ability currentAbility = state.execution().currentAbility();
        switch (currentAbility) {
            case MoveAbility move -> beginMove(move, events);
            case AttackAbility attack -> beginAttack(attack, events);
            case HealAbility heal -> beginHeal(heal, events);
            case DamageAbility damage -> beginDamage(damage, events);
            case ConditionAbility condition ->
                beginCondition(condition, events);
            case ShieldAbility shield ->
                applyShield(shield, events);
            case RetaliateAbility retaliate ->
                applyRetaliate(retaliate, events);
            default -> throw new IllegalStateException(
                "No executor is registered for ability type: "
                    + currentAbility.getClass().getName()
            );
        }
    }

    private void applyShield(
        ShieldAbility ability,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        int amount = Math.max(0, ability.amount().resolve(
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        ));

        if (amount > 0) {
            state.addActiveEffect(
                effectSource(execution),
                effectDuration(ability.duration(), execution),
                new ActiveEffect.Effect.Shield(amount, ability.scope())
            );
            execution.markAbilityPerformed();
        }
        execution.completeCurrentAbility();
        continueExecution(events);
    }

    private void applyRetaliate(
        RetaliateAbility ability,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        AbilityExpressionContext context =
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            );
        int amount = Math.max(0, ability.amount().resolve(context));
        int range = Math.max(0, ability.range().resolve(context));

        if (amount > 0 && range > 0) {
            state.addActiveEffect(
                effectSource(execution),
                effectDuration(ability.duration(), execution),
                new ActiveEffect.Effect.Retaliate(
                    amount,
                    range,
                    ability.lineOfSight(),
                    ability.scope()
                )
            );
            execution.markAbilityPerformed();
        }
        execution.completeCurrentAbility();
        continueExecution(events);
    }

    private ActiveEffect.EffectSource effectSource(
        ActionExecution execution
    ) {
        return new ActiveEffect.EffectSource(
            execution.owner(),
            execution.actor(),
            execution.controller(),
            execution.source(),
            execution.currentAbilityIndex(),
            execution.memory().snapshot()
        );
    }

    private ActiveEffect.EffectDuration effectDuration(
        EffectDurationRule duration,
        ActionExecution execution
    ) {
        return switch (duration) {
            case END_OF_ROUND ->
                new ActiveEffect.EffectDuration.EndOfRound(
                    state.currentRound().roundNumber()
                );
            case WHILE_CARD_ACTIVE ->
                new ActiveEffect.EffectDuration.WhileCardActive(
                    execution.owner(),
                    execution.source()
                );
        };
    }

    private Map<FigureId, FigureEffectView> effectViews() {
        return state.figureEffectViews();
    }

    private CommandResult withEffectChanges(
        CommandResult result,
        Map<FigureId, FigureEffectView> before
    ) {
        if (!result.accepted()) {
            return result;
        }

        Map<FigureId, FigureEffectView> after = effectViews();
        Map<FigureId, FigureEffectView> changed = new LinkedHashMap<>();
        after.forEach((figureId, view) -> {
            if (!view.equals(before.get(figureId))) {
                changed.put(figureId, view);
            }
        });
        if (changed.isEmpty()) {
            return result;
        }

        List<GameEvent> events = new ArrayList<>(result.events());
        events.add(new FigureEffectsChangedEvent(changed));
        return CommandResult.accepted(events);
    }

    private void beginMove(MoveAbility move, List<GameEvent> events) {
        ActionExecution execution = state.execution();

        execution.setPendingChoice(
            new PendingMoveChoice(execution.actor(), move.amount(), move.type())
        );

        events.add(
            new MoveChoiceRequestedEvent(
                execution.actor(),
                move.amount(),
                move.type()
            )
        );
    }

    private void beginAttack(AttackAbility attack, List<GameEvent> events) {
        ActionExecution execution = state.execution();
        execution.beginAttack(
            attack.effectiveDefinition(),
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );

        TargetResolver resolver = new TargetResolver(state, execution);
        ResolvedAttackChoice resolvedChoice = resolver.resolveAttack(attack.targeting());

        switch (resolvedChoice.selection()) {
            case FigureTargetSelection.FigureTargets figureTargets -> {
                PendingFigureTargetChoice.Attack pendingChoice = new PendingFigureTargetChoice.Attack(
                    execution.actor(),
                    figureTargets,
                    resolvedChoice.previews()
                );
                execution.setPendingChoice(pendingChoice);
                events.add(new AttackTargetChoiceRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            case FigureTargetSelection.AreaPlacement areaPlacement -> {
                PendingAreaChoice.Attack pendingChoice = new PendingAreaChoice.Attack(
                    execution.actor(),
                    areaPlacement,
                    resolvedChoice.previews()
                );
                execution.setPendingChoice(pendingChoice);
                events.add(new AttackAreaPlacementRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            default -> throw new IllegalStateException(
                "This resolved attack choice requires its dedicated choice flow: "
                    + resolvedChoice.getClass().getSimpleName()
            );
        }
    }

    private void beginHeal(HealAbility heal, List<GameEvent> events) {
        ActionExecution execution = state.execution();
        execution.beginHeal(
            heal.effectiveDefinition(),
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );

        TargetResolver resolver = new TargetResolver(
            state,
            execution,
            execution.healExecution().heal().origin(),
            execution.healExecution().heal().visibilityPolicy()
        );
        ResolvedHealChoice resolvedChoice = resolver.resolveHeal(heal.targeting());

        switch (resolvedChoice.selection()) {
            case FigureTargetSelection.FigureTargets figureTargets -> {
                PendingFigureTargetChoice.Heal pendingChoice = new PendingFigureTargetChoice.Heal(
                    execution.actor(),
                    figureTargets,
                    resolvedChoice.previews()
                );
                execution.setPendingChoice(pendingChoice);
                events.add(new HealTargetChoiceRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            case FigureTargetSelection.AreaPlacement areaPlacement -> {
                PendingAreaChoice.Heal pendingChoice = new PendingAreaChoice.Heal(
                    execution.actor(),
                    areaPlacement,
                    resolvedChoice.previews()
                );
                execution.setPendingChoice(pendingChoice);
                events.add(new HealAreaPlacementRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            default -> throw new UnsupportedOperationException(resolvedChoice.selection().toString());
        }
    }

    private void beginDamage(
        DamageAbility damage,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        execution.beginDamage(
            damage.effectiveDefinition(),
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );

        TargetResolver resolver = new TargetResolver(
            state,
            execution,
            damage.origin(),
            damage.visibilityPolicy()
        );
        ResolvedDamageChoice resolvedChoice = resolver.resolveDamage(
            damage.targeting()
        );

        switch (resolvedChoice.selection()) {
            case FigureTargetSelection.FigureTargets figureTargets -> {
                PendingFigureTargetChoice.Damage pendingChoice =
                    new PendingFigureTargetChoice.Damage(
                        execution.actor(),
                        figureTargets,
                        resolvedChoice.previews()
                    );
                execution.setPendingChoice(pendingChoice);
                events.add(new DamageTargetChoiceRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            case FigureTargetSelection.AreaPlacement areaPlacement -> {
                PendingAreaChoice.Damage pendingChoice =
                    new PendingAreaChoice.Damage(
                        execution.actor(),
                        areaPlacement,
                        resolvedChoice.previews()
                    );
                execution.setPendingChoice(pendingChoice);
                events.add(new DamageAreaPlacementRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            default -> throw new UnsupportedOperationException(
                resolvedChoice.selection().toString()
            );
        }
    }

    private void beginCondition(
        ConditionAbility condition,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        execution.beginCondition(condition.effectiveDefinition());

        TargetResolver resolver = new TargetResolver(
            state,
            execution,
            condition.origin(),
            condition.visibilityPolicy()
        );
        ResolvedConditionChoice resolvedChoice =
            resolver.resolveCondition(condition.targeting());

        switch (resolvedChoice.selection()) {
            case FigureTargetSelection.FigureTargets figureTargets -> {
                PendingFigureTargetChoice.Condition pendingChoice =
                    new PendingFigureTargetChoice.Condition(
                        execution.actor(),
                        figureTargets,
                        resolvedChoice.previews()
                    );
                execution.setPendingChoice(pendingChoice);
                events.add(new ConditionTargetChoiceRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            case FigureTargetSelection.AreaPlacement areaPlacement -> {
                PendingAreaChoice.Condition pendingChoice =
                    new PendingAreaChoice.Condition(
                        execution.actor(),
                        areaPlacement,
                        resolvedChoice.previews()
                    );
                execution.setPendingChoice(pendingChoice);
                events.add(new ConditionAreaPlacementRequestedEvent(
                    pendingChoice.actorId(),
                    pendingChoice.selection(),
                    pendingChoice.previews()
                ));
            }
            default -> throw new UnsupportedOperationException(
                resolvedChoice.selection().toString()
            );
        }
    }

    private void resolveAttackTargets(
        List<FigureId> targets,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        AttackExecutionState attackExecution =
            execution.attackExecution();
        attackExecution.setTargets(
            targets,
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );

        List<AttackEffect> effects = attackExecution.attack().effects();
        ActiveEffectResolver effectResolver =
            new ActiveEffectResolver(state);
        boolean attackerDefeated = false;

        while (!attackExecution.isFinished()) {
            AttackInstance attackInstance =
                attackExecution.currentAttack();

            if (!state.hasFigure(attackInstance.targetId())) {
                attackExecution.completeCurrentAttack();
                continue;
            }

            AbilityExpressionContext context =
                AbilityExpressionContext.forTarget(
                    state,
                    execution.frame(),
                    execution.memory(),
                    attackInstance.targetId(),
                    attackInstance.sequenceIndex()
                );

            Figure target = state.figure(attackInstance.targetId());

            int attackValue = attackExecution.amountForCurrentAttack(context);
            int shield = effectResolver.shieldFor(target.id());
            int pierce = effects.stream()
                .filter(AttackEffect.Pierce.class::isInstance)
                .map(AttackEffect.Pierce.class::cast)
                .mapToInt(effect -> Math.max(
                    0,
                    effect.amount().evaluate(context)
                ))
                .sum();
            int effectiveShield = Math.max(0, shield - pierce);
            int damage = Math.max(
                0,
                attackValue
                    + (target.conditions().contains(Conditions.POISON.id())
                        ? 1
                        : 0)
                    - effectiveShield
            );
            state.damageFigure(target.id(), damage);

            execution.markAbilityPerformed();

            int remainingHealth = target.health().currentHealth();

            events.add(new FigureDamagedEvent(
                target.id(),
                damage,
                remainingHealth,
                execution.actor()
            ));

            if (!target.isDefeated()) {
                applyAttackEffectsAt(
                    TargetEffectTiming.AFTER_DAMAGE,
                    effects,
                    target,
                    events
                );
                applyAttackEffectsAt(
                    TargetEffectTiming.AFTER_ATTACK,
                    effects,
                    target,
                    events
                );

                int retaliateDamage =
                    effectResolver.retaliateDamageAgainst(
                        target.id(),
                        execution.actor()
                    );
                if (retaliateDamage > 0
                    && state.hasFigure(execution.actor())) {
                    Figure attacker = state.figure(execution.actor());
                    state.damageFigure(attacker.id(), retaliateDamage);
                    events.add(new FigureDamagedEvent(
                        attacker.id(),
                        retaliateDamage,
                        attacker.health().currentHealth(),
                        target.id()
                    ));

                    if (attacker.isDefeated()) {
                        HexPosition defeatedAt =
                            state.positionOf(attacker.id());
                        events.add(new FigureDefeatedEvent(
                            attacker.id(),
                            defeatedAt
                        ));
                        state.removeFigure(attacker.id());
                        attackerDefeated = true;
                    }
                }
            }

            if (target.isDefeated()) {
                HexPosition defeatedAt = state.positionOf(target.id());
                events.add(new FigureDefeatedEvent(
                    target.id(),
                    defeatedAt
                ));
                state.removeFigure(target.id());
            }

            attackExecution.completeCurrentAttack();
            if (attackerDefeated) {
                break;
            }
        }

        if (attackerDefeated) {
            execution.terminate();
            continueExecution(events);
            return;
        }

        execution.completeCurrentAbility();
        continueExecution(events);
    }

    private void applyAttackEffectsAt(
        TargetEffectTiming timing,
        List<AttackEffect> effects,
        Figure target,
        List<GameEvent> events
    ) {
        effects.stream()
            .filter(effect -> effect.timing() == timing)
            .forEach(effect -> applyAttackEffect(effect, target, events));
    }

    private void applyAttackEffect(
        AttackEffect effect,
        Figure target,
        List<GameEvent> events
    ) {
        switch (effect) {
            case AttackEffect.Condition condition -> {
                applyCondition(condition.condition(), target, events);

            }
            case AttackEffect.Curse ignored ->
                throw unsupportedAttackEffect(
                    effect,
                    "attack modifier decks are not implemented yet"
                );
            case AttackEffect.ForcedMovement ignored ->
                throw unsupportedAttackEffect(
                    effect,
                    "forced movement execution is not implemented yet"
                );
            case AttackEffect.ExecuteAfterAttack ignored ->
                throw unsupportedAttackEffect(
                    effect,
                    "execute effects are not implemented yet"
                );
            case AttackEffect.Contextual ignored ->
                throw unsupportedAttackEffect(
                    effect,
                    "no contextual attack-effect resolver is registered"
                );
            case AttackEffect.Pierce ignored ->
                throw new IllegalStateException(
                    "Pierce must be resolved during damage calculation"
                );
        }
    }

    private static UnsupportedOperationException unsupportedAttackEffect(
        AttackEffect effect,
        String reason
    ) {
        return new UnsupportedOperationException(
            "Cannot resolve " + effect.getClass().getSimpleName()
                + ": " + reason
        );
    }

    private int calculateExpirationRound(FigureId targetId) {
        if (state.currentRound().currentTurnIndex() >= state.currentRound().turnIndexForFigure(targetId)) {
            return state.currentRound().roundNumber() + 1;
        } else {
            return state.currentRound().roundNumber();
        }
    }

    private void resolveHealTargets(
        List<FigureId> targets,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        HealExecutionState healExecution = execution.healExecution();
        List<HealEffect> effects = healExecution.heal().effects();
        healExecution.setTargets(
            targets,
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );

        while (!healExecution.isFinished()) {
            FigureId figureId = healExecution.currentTarget();
            if (!state.hasFigure(figureId)) {
                healExecution.completeCurrentTarget();
                continue;
            }

            AbilityExpressionContext context =
                AbilityExpressionContext.forTarget(
                    state,
                    execution.frame(),
                    execution.memory(),
                    figureId,
                    healExecution.nextTargetIndex()
                );
            int healAmount = healExecution.amountForCurrentTarget(context);
            Figure target = state.figure(figureId);

            boolean poisoned = target.conditions()
                .remove(Conditions.POISON.id());
            boolean wounded = target.conditions().remove(Conditions.WOUND.id());
            if (!poisoned) {
                state.healFigure(figureId, healAmount);
            }

            execution.markAbilityPerformed();
            healExecution.completeCurrentTarget();

            events.add(new FigureHealedEvent(
                figureId,
                poisoned ? 0 : healAmount,
                target.health().currentHealth(),
                execution.actor()
            ));

            if (poisoned) {
                events.add(new ConditionRemovedEvent(
                    figureId,
                    Conditions.POISON.id()
                ));
            }
            if (wounded) {
                events.add(new ConditionRemovedEvent(
                    figureId,
                    Conditions.WOUND.id()
                ));
            }

            applyHealEffectsAt(
                TargetEffectTiming.AFTER_HEAL,
                effects,
                target,
                events
            );
        }

        execution.completeCurrentAbility();
        continueExecution(events);
    }

    private void applyHealEffectsAt(
        TargetEffectTiming timing,
        List<HealEffect> effects,
        Figure target,
        List<GameEvent> events
    ) {
        effects.stream()
            .filter(effect -> effect.timing() == timing)
            .forEach(effect -> applyHealEffect(effect, target, events));
    }

    private void applyHealEffect(
        HealEffect effect,
        Figure target,
        List<GameEvent> events
    ) {
        switch (effect) {
            case HealEffect.Condition condition ->
                applyCondition(condition.condition(), target, events);
            case HealEffect.Bless ignored ->
                throw unsupportedHealEffect(
                    effect,
                    "attack modifier decks are not implemented yet"
                );
        }
    }

    private static UnsupportedOperationException unsupportedHealEffect(
        HealEffect effect,
        String reason
    ) {
        return new UnsupportedOperationException(
            "Cannot resolve " + effect.getClass().getSimpleName()
                + ": " + reason
        );
    }

    private void resolveDamageTargets(
        List<FigureId> targets,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        DamageExecutionState damageExecution = execution.damageExecution();
        List<DamageEffect> effects = damageExecution.damage().effects();
        damageExecution.setTargets(
            targets,
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );

        while (!damageExecution.isFinished()) {
            FigureId targetId = damageExecution.currentTarget();
            if (!state.hasFigure(targetId)) {
                damageExecution.completeCurrentTarget();
                continue;
            }

            AbilityExpressionContext context =
                AbilityExpressionContext.forTarget(
                    state,
                    execution.frame(),
                    execution.memory(),
                    targetId,
                    damageExecution.nextTargetIndex()
                );
            int amount = damageExecution.amountForCurrentTarget(context);
            Figure target = state.figure(targetId);

            applyDamageEffectsAt(
                TargetEffectTiming.BEFORE_DAMAGE,
                effects,
                target,
                events
            );

            state.damageFigure(targetId, amount);
            execution.markAbilityPerformed();

            events.add(new FigureDamagedEvent(
                targetId,
                amount,
                target.health().currentHealth(),
                execution.actor()
            ));

            if (!target.isDefeated()) {
                applyDamageEffectsAt(
                    TargetEffectTiming.AFTER_DAMAGE,
                    effects,
                    target,
                    events
                );
            }

            if (target.isDefeated()) {
                HexPosition defeatedAt = state.positionOf(targetId);
                events.add(new FigureDefeatedEvent(targetId, defeatedAt));
                state.removeFigure(targetId);
            }
            damageExecution.completeCurrentTarget();
        }

        execution.completeCurrentAbility();
        continueExecution(events);
    }

    private void applyDamageEffectsAt(
        TargetEffectTiming timing,
        List<DamageEffect> effects,
        Figure target,
        List<GameEvent> events
    ) {
        effects.stream()
            .filter(effect -> effect.timing() == timing)
            .forEach(effect -> applyDamageEffect(effect, target, events));
    }

    private void applyDamageEffect(
        DamageEffect effect,
        Figure target,
        List<GameEvent> events
    ) {
        switch (effect) {
            case DamageEffect.ApplyCondition condition ->
                applyCondition(condition.condition(), target, events);
            case DamageEffect.RemoveCondition removeCondition -> {
                if (target.conditions().remove(removeCondition.condition())) {
                    events.add(new ConditionRemovedEvent(
                        target.id(),
                        removeCondition.condition()
                    ));
                }
            }
            case DamageEffect.Curse ignored ->
                throw unsupportedDamageEffect(
                    effect,
                    "attack modifier decks are not implemented yet"
                );
            case DamageEffect.ForcedMovement ignored ->
                throw unsupportedDamageEffect(
                    effect,
                    "forced movement execution is not implemented yet"
                );
            case DamageEffect.Contextual ignored ->
                throw unsupportedDamageEffect(
                    effect,
                    "no contextual damage-effect resolver is registered"
                );
        }
    }

    private static UnsupportedOperationException unsupportedDamageEffect(
        DamageEffect effect,
        String reason
    ) {
        return new UnsupportedOperationException(
            "Cannot resolve " + effect.getClass().getSimpleName()
                + ": " + reason
        );
    }

    private void resolveConditionTargets(
        List<FigureId> targets,
        List<GameEvent> events
    ) {
        ActionExecution execution = state.execution();
        ConditionExecutionState conditionExecution =
            execution.conditionExecution();
        conditionExecution.setTargets(targets);

        while (!conditionExecution.isFinished()) {
            FigureId targetId = conditionExecution.currentTarget();
            if (!state.hasFigure(targetId)) {
                conditionExecution.completeCurrentTarget();
                continue;
            }

            Figure target = state.figure(targetId);
            for (Conditions.ConditionDefinition condition
                : conditionExecution.condition().conditions()) {
                applyCondition(condition, target, events);
            }
            execution.markAbilityPerformed();
            conditionExecution.completeCurrentTarget();
        }

        execution.completeCurrentAbility();
        continueExecution(events);
    }

    private void applyCondition(
        Conditions.ConditionDefinition definition,
        Figure target,
        List<GameEvent> events
    ) {
        if (target.isImmuneTo(definition.id())) {
            return;
        }
        ActiveCondition.ConditionExpiration expiration =
            switch (definition.expirationRule()) {
                case UNTIL_REMOVED ->
                    new ActiveCondition.ConditionExpiration.UntilRemoved();
                case END_OF_TARGETS_NEXT_TURN ->
                    new ActiveCondition.ConditionExpiration.EndOfTurnInRound(
                        calculateExpirationRound(target.id())
                    );
            };

        target.conditions().addCondition(definition.id(), expiration);
        events.add(new ConditionAppliedEvent(
            target.id(),
            definition.id()
        ));
    }

    private CommandResult executeCancel(CancelActionCommand command) {
        ActionExecution execution = state.execution();

        if (execution == null) {
            return CommandResult.rejected(
                "There is no active action to cancel"
            );
        }

        if (!execution.canCancel()) {
            return CommandResult.rejected(
                "The action can no longer be cancelled"
            );
        }

        state.finishActionExecution();

        return CommandResult.accepted(
            List.of(new CancelActionEvent())
        );
    }

    private CommandResult executeSkip(SkipActionCommand command) {
        ActionExecution execution = state.execution();

        if (execution == null) {
            return CommandResult.rejected(
                "There is no active action to cancel"
            );
        }

        execution.skipCurrentAbility();

        List<GameEvent> events = new ArrayList<>();
        events.add(new SkipActionEvent());

        continueExecution(events);

        return CommandResult.accepted(
            events
        );
    }

    private CommandResult executeEndTurn(EndTurnCommand command) {
        if (state.phase() != ScenarioPhase.TURN_EXECUTION) {
            return CommandResult.rejected(
                "A turn can only end during turn execution"
            );
        }

        if (state.execution() != null) {
            return CommandResult.rejected(
                "You can't end your turn during the execution of an Action"
            );
        }

        FigureId currentActiveFigure = state.currentRound().activeFigure();
        Figure figure = state.figure(currentActiveFigure);
        if (!command.figureId().equals(currentActiveFigure)) {
            return CommandResult.rejected(
                "Only the active figure can end its turn"
            );
        }

        List<GameEvent> events = new ArrayList<>();

        for (Conditions.ConditionId conditionId : figure.conditions().conditions().keySet()) {
            if (figure.conditions().removeIfExpired(conditionId, state.currentRound().roundNumber())) {
                events.add(new ConditionRemovedEvent(currentActiveFigure, conditionId));
            }
        }

        state.currentRound().continueToNextFigure();

        events.add(new TurnEndedEvent(currentActiveFigure));

        return CommandResult.accepted(
            events
        );
    }
}
