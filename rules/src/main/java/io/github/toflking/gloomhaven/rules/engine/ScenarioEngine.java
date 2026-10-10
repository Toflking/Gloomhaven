package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackExecutionState;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackChoice;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackInstance;
import io.github.toflking.gloomhaven.rules.ability.movement.MoveAbility;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.command.*;
import io.github.toflking.gloomhaven.rules.event.*;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.execution.ValidationResult;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackTargetResolver;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackTargetValidator;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaAttackChoice;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAttackChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.execution.movement.MovePathValidator;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioPhase;
import io.github.toflking.gloomhaven.rules.scenario.history.CommandHistory;
import io.github.toflking.gloomhaven.rules.scenario.history.ScenarioSnapshot;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import java.util.ArrayList;
import java.util.List;

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
        if (command instanceof UndoCommand undo) {
            return executeUndo(undo);
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

        return result;
    }

    private CommandResult executeTracked(GameCommand command) {
        return switch (command) {
            case ChooseMovePathCommand move ->
                executeMovePathSelection(move);

            case ChooseAttackTargetsCommand attack ->
                executeAttack(attack);

            case ChooseAttackAreaCommand attack ->
                executeAttack(attack);

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


    private CommandResult executeAttack(GameCommand command) {
        if (state.phase() != ScenarioPhase.TURN_EXECUTION) {
            return CommandResult.rejected(
                "An attack can only happen during turn execution"
            );
        }

        ActionExecution execution = state.execution();

        if (execution == null) {
            return CommandResult.rejected(
                "There is no active action execution"
            );
        }

        List<GameEvent> events = new ArrayList<>();
        AttackTargetValidator attackTargetValidator = new AttackTargetValidator(state);
        ValidationResult validationResult;
        switch (execution.pendingChoice()) {
            case PendingAttackChoice attackChoice -> {
                if (!(command instanceof ChooseAttackTargetsCommand attackTargetsCommand)) return CommandResult.rejected("Expected an ChooseAttackTargetsCommand but received " + command.getClass().getName());
                validationResult = attackTargetValidator.validateTargets(attackTargetsCommand.targets(), attackChoice);
            }
            case PendingAreaAttackChoice areaAttackChoice -> {
                if (!(command instanceof ChooseAttackAreaCommand attackAreaCommand)) return CommandResult.rejected("Expected an ChooseAttackAreaCommand but received " + command.getClass().getName());
                validationResult = attackTargetValidator.validateArea(attackAreaCommand.placement(), areaAttackChoice);
            }
            default -> {
                return CommandResult.rejected("There is no pending choice for this attack");
            }
        }

        if (!validationResult.isValid()) {
            return CommandResult.rejected(
                validationResult.rejectionReason()
            );
        }

        execution.clearPendingChoice();
        resolveAttackTargets(validationResult.targets(), events);

        return CommandResult.accepted(events);
    }

    private CommandResult executePlayCardAction(PlayCardActionCommand command) {
        AbilitySource source = command.source();

        CardAction action = cardCatalog.resolve(source);

        state.startActionExecution(
            state.activeFigure(),
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
            default -> throw new IllegalStateException(
                "No executor is registered for ability type: "
                    + currentAbility.getClass().getName()
            );
        }
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
        execution.beginAttack(attack.effectiveDefinition());

        AttackTargetResolver resolver = new AttackTargetResolver(
            state,
            execution
        );
        AttackChoice resolvedChoice = resolver.resolve(attack.targeting());

        switch (resolvedChoice) {
            case AttackChoice.FigureTargets figureTargets -> {
                PendingAttackChoice pendingChoice = new PendingAttackChoice(
                    execution.actor(),
                    figureTargets.eligibleTargets(),
                    figureTargets.minimumTargets(),
                    figureTargets.maximumTargets(),
                    figureTargets.duplicates(),
                    figureTargets.selectionMode()
                );
                execution.setPendingChoice(pendingChoice);
                events.add(new TargetChoiceRequestedEvent(
                    pendingChoice.attackerId(),
                    pendingChoice.eligibleTargets(),
                    pendingChoice.minimumTargets(),
                    pendingChoice.maximumTargets(),
                    pendingChoice.duplicates(),
                    pendingChoice.selectionMode()
                ));
            }
            case AttackChoice.AreaPlacement areaPlacement -> {
                PendingAreaAttackChoice pendingChoice = new PendingAreaAttackChoice(
                    execution.actor(),
                    areaPlacement.placements(),
                    areaPlacement.control()
                );
                execution.setPendingChoice(pendingChoice);
                events.add(new AreaPlacementRequestedEvent(
                    pendingChoice.attackerId(),
                    pendingChoice.placements(),
                    pendingChoice.control()
                ));
            }
            default -> throw new IllegalStateException(
                "This resolved attack choice requires its dedicated choice flow: "
                    + resolvedChoice.getClass().getSimpleName()
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
        attackExecution.setTargets(targets);

        while (!attackExecution.isFinished()) {
            AttackInstance attackInstance =
                attackExecution.currentAttack();

            if (!state.hasFigure(attackInstance.targetId())) {
                attackExecution.completeCurrentAttack();
                continue;
            }

            AttackExpressionContext context =
                new AttackExpressionContext(
                    state,
                    execution.frame(),
                    execution.memory(),
                    attackInstance.targetId(),
                    attackInstance.sequenceIndex()
                );

            int attackValue = Math.max(0, attackExecution.attack().amount().resolve(context));

            Figure target = state.figure(attackInstance.targetId());
            state.damageFigure(target.id(), attackValue);

            execution.markAbilityPerformed();

            int remainingHealth = target.health().currentHealth();

            events.add(new FigureDamagedEvent(
                target.id(),
                attackValue,
                remainingHealth,
                execution.actor()
            ));

            if (target.isDefeated()) {
                HexPosition defeatedAt = state.positionOf(target.id());
                events.add(new FigureDefeatedEvent(
                    target.id(),
                    defeatedAt
                ));
                state.removeFigure(target.id());
            }

            attackExecution.completeCurrentAttack();
        }

        execution.completeCurrentAbility();
        continueExecution(events);
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

        FigureId currentActiveFigure = state.activeFigure();
        if (!command.figureId().equals(currentActiveFigure)) {
            return CommandResult.rejected(
                "Only the active figure can end its turn"
            );
        }

        // nächste bestimmen nach initiative order ... xd
        FigureId nextFigure = new FigureId("monster-1");

        state.setActiveFigure(nextFigure);

        return CommandResult.accepted(
            List.of(new TurnEndedEvent(currentActiveFigure))
        );
    }
}
