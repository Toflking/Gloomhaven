package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.EffectiveAttack;
import io.github.toflking.gloomhaven.rules.ability.heal.EffectiveHeal;
import io.github.toflking.gloomhaven.rules.ability.damage.EffectiveDamage;
import io.github.toflking.gloomhaven.rules.ability.condition.EffectiveCondition;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackExecutionState;
import io.github.toflking.gloomhaven.rules.execution.damage.DamageExecutionState;
import io.github.toflking.gloomhaven.rules.execution.condition.ConditionExecutionState;
import io.github.toflking.gloomhaven.rules.execution.heal.HealExecutionState;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingChoice;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class ActionExecution {

    private final AbilityExecutionFrame frame;
    private final List<Ability> abilities;
    private final ExecutionMemory memory;

    private int currentAbilityIndex;
    private PendingChoice pendingChoice;
    private AttackExecutionState attackExecution;
    private HealExecutionState healExecution;
    private DamageExecutionState damageExecution;
    private ConditionExecutionState conditionExecution;

    private boolean abilityPerformed;
    private boolean cancellationLocked;

    public ActionExecution(
        FigureId actor,
        AbilitySource source,
        List<Ability> abilities
    ) {
        this(
            AbilityExecutionFrame.selfControlled(actor, source),
            abilities
        );
    }

    public ActionExecution(
        AbilityExecutionFrame frame,
        List<Ability> abilities
    ) {
        this(
            frame,
            abilities,
            new ExecutionMemory(),
            0,
            null,
            null,
            null,
            null,
            null,
            false,
            false
        );
    }

    private ActionExecution(
        AbilityExecutionFrame frame,
        List<Ability> abilities,
        ExecutionMemory memory,
        int currentAbilityIndex,
        PendingChoice pendingChoice,
        AttackExecutionState attackExecution,
        HealExecutionState healExecution,
        DamageExecutionState damageExecution,
        ConditionExecutionState conditionExecution,
        boolean abilityPerformed,
        boolean cancellationLocked
    ) {
        this.frame = Objects.requireNonNull(frame, "frame");
        this.abilities = List.copyOf(abilities);
        this.memory = Objects.requireNonNull(memory, "memory");
        this.currentAbilityIndex = currentAbilityIndex;
        this.pendingChoice = pendingChoice;
        this.attackExecution = attackExecution;
        this.healExecution = healExecution;
        this.damageExecution = damageExecution;
        this.conditionExecution = conditionExecution;
        this.abilityPerformed = abilityPerformed;
        this.cancellationLocked = cancellationLocked;
    }

    public Ability currentAbility() {
        if (isFinished()) {
            throw new IllegalStateException(
                "Action execution is already finished"
            );
        }

        return abilities.get(currentAbilityIndex);
    }

    public void advance() {
        if (pendingChoice != null) {
            throw new IllegalStateException(
                "Cannot advance while a choice is pending"
            );
        }

        currentAbilityIndex++;
    }

    public boolean isFinished() {
        return currentAbilityIndex >= abilities.size();
    }

    public PendingChoice pendingChoice() {
        return pendingChoice;
    }

    public void setPendingChoice(PendingChoice pendingChoice) {
        if (this.pendingChoice != null) {
            throw new IllegalStateException("Cannot set pending choice while a choice is already pending");
        }
        this.pendingChoice = Objects.requireNonNull(
            pendingChoice,
            "pendingChoice"
        );
    }

    public void clearPendingChoice() {
        pendingChoice = null;
    }

    public FigureId actor() {
        return frame.actorId();
    }

    public FigureId owner() {
        return frame.ownerId();
    }

    public FigureId controller() {
        return frame.controllerId();
    }

    public AbilitySource source() {
        return frame.source();
    }

    public AbilityExecutionFrame frame() {
        return frame;
    }

    public ExecutionMemory memory() {
        return memory;
    }

    public void beginAttack(
        EffectiveAttack attack,
        AbilityExpressionContext abilityStartContext
    ) {
        if (hasTargetBasedExecution()) {
            throw new IllegalStateException(
                "A target-based ability execution is already active"
            );
        }
        attackExecution = new AttackExecutionState(
            attack,
            abilityStartContext
        );
    }

    public AttackExecutionState attackExecution() {
        if (attackExecution == null) {
            throw new IllegalStateException(
                "There is no active attack execution"
            );
        }
        return attackExecution;
    }

    public void finishAttack() {
        if (attackExecution == null) {
            throw new IllegalStateException(
                "There is no active attack execution"
            );
        }
        if (!attackExecution.isFinished()) {
            throw new IllegalStateException(
                "Cannot finish an attack while attacks remain unresolved"
            );
        }
        attackExecution = null;
    }

    public void beginHeal(
        EffectiveHeal heal,
        AbilityExpressionContext abilityStartContext
    ) {
        if (hasTargetBasedExecution()) {
            throw new IllegalStateException(
                "A target-based ability execution is already active"
            );
        }
        healExecution = new HealExecutionState(
            heal,
            abilityStartContext
        );
    }

    public HealExecutionState healExecution() {
        if (healExecution == null) {
            throw new IllegalStateException(
                "There is no active heal execution"
            );
        }
        return healExecution;
    }

    public void finishHeal() {
        if (healExecution == null) {
            throw new IllegalStateException(
                "There is no active heal execution"
            );
        }
        if (!healExecution.isFinished()) {
            throw new IllegalStateException(
                "Cannot finish a heal while targets remain unresolved"
            );
        }
        healExecution = null;
    }

    public void beginDamage(
        EffectiveDamage damage,
        AbilityExpressionContext abilityStartContext
    ) {
        if (hasTargetBasedExecution()) {
            throw new IllegalStateException(
                "A target-based ability execution is already active"
            );
        }
        damageExecution = new DamageExecutionState(
            damage,
            abilityStartContext
        );
    }

    public DamageExecutionState damageExecution() {
        if (damageExecution == null) {
            throw new IllegalStateException(
                "There is no active direct-damage execution"
            );
        }
        return damageExecution;
    }

    public void finishDamage() {
        if (damageExecution == null) {
            throw new IllegalStateException(
                "There is no active direct-damage execution"
            );
        }
        if (!damageExecution.isFinished()) {
            throw new IllegalStateException(
                "Cannot finish direct damage while targets remain unresolved"
            );
        }
        damageExecution = null;
    }

    public void beginCondition(EffectiveCondition condition) {
        if (hasTargetBasedExecution()) {
            throw new IllegalStateException(
                "A target-based ability execution is already active"
            );
        }
        conditionExecution = new ConditionExecutionState(condition);
    }

    public ConditionExecutionState conditionExecution() {
        if (conditionExecution == null) {
            throw new IllegalStateException(
                "There is no active condition execution"
            );
        }
        return conditionExecution;
    }

    public void finishCondition() {
        if (conditionExecution == null) {
            throw new IllegalStateException(
                "There is no active condition execution"
            );
        }
        if (!conditionExecution.isFinished()) {
            throw new IllegalStateException(
                "Cannot finish conditions while targets remain unresolved"
            );
        }
        conditionExecution = null;
    }

    public int currentAbilityIndex() {
        return currentAbilityIndex;
    }

    public void completeCurrentAbility() {
        clearPendingChoice();
        if (attackExecution != null) {
            finishAttack();
        }
        if (healExecution != null) {
            finishHeal();
        }
        if (damageExecution != null) {
            finishDamage();
        }
        if (conditionExecution != null) {
            finishCondition();
        }
        advance();
    }

    public void skipCurrentAbility() {
        lockCancellation();
        clearPendingChoice();
        attackExecution = null;
        healExecution = null;
        damageExecution = null;
        conditionExecution = null;
        advance();
    }

    public void terminate() {
        clearPendingChoice();
        attackExecution = null;
        healExecution = null;
        damageExecution = null;
        conditionExecution = null;
        currentAbilityIndex = abilities.size();
    }

    public boolean canCancel() {
        return !cancellationLocked;
    }

    public boolean hasPerformedAbility() {
        return abilityPerformed;
    }

    public void markAbilityPerformed() {
        abilityPerformed = true;
        lockCancellation();
    }

    public void lockCancellation() {
        cancellationLocked = true;
    }

    public Snapshot snapshot() {
        return new Snapshot(
            frame,
            abilities,
            memory.snapshot(),
            currentAbilityIndex,
            Optional.ofNullable(pendingChoice),
            Optional.ofNullable(attackExecution)
                .map(AttackExecutionState::snapshot),
            Optional.ofNullable(healExecution)
                .map(HealExecutionState::snapshot),
            Optional.ofNullable(damageExecution)
                .map(DamageExecutionState::snapshot),
            Optional.ofNullable(conditionExecution)
                .map(ConditionExecutionState::snapshot),
            abilityPerformed,
            cancellationLocked
        );
    }

    public static ActionExecution restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");

        return new ActionExecution(
            snapshot.frame(),
            snapshot.abilities(),
            ExecutionMemory.restore(snapshot.memory()),
            snapshot.currentAbilityIndex(),
            snapshot.pendingChoice().orElse(null),
            snapshot.attackExecution()
                .map(AttackExecutionState::restore)
                .orElse(null),
            snapshot.healExecution()
                .map(HealExecutionState::restore)
                .orElse(null),
            snapshot.damageExecution()
                .map(DamageExecutionState::restore)
                .orElse(null),
            snapshot.conditionExecution()
                .map(ConditionExecutionState::restore)
                .orElse(null),
            snapshot.abilityPerformed(),
            snapshot.cancellationLocked()
        );
    }

    public record Snapshot(
        AbilityExecutionFrame frame,
        List<Ability> abilities,
        ExecutionMemory.Snapshot memory,
        int currentAbilityIndex,
        Optional<PendingChoice> pendingChoice,
        Optional<AttackExecutionState.Snapshot> attackExecution,
        Optional<HealExecutionState.Snapshot> healExecution,
        Optional<DamageExecutionState.Snapshot> damageExecution,
        Optional<ConditionExecutionState.Snapshot> conditionExecution,
        boolean abilityPerformed,
        boolean cancellationLocked
    ) {

        public Snapshot {
            Objects.requireNonNull(frame, "frame");
            abilities = List.copyOf(abilities);
            Objects.requireNonNull(memory, "memory");
            Objects.requireNonNull(pendingChoice, "pendingChoice");
            Objects.requireNonNull(attackExecution, "attackExecution");
            Objects.requireNonNull(healExecution, "healExecution");
            Objects.requireNonNull(damageExecution, "damageExecution");
            Objects.requireNonNull(conditionExecution, "conditionExecution");

            long activeTargetExecutions = java.util.stream.Stream.of(
                attackExecution,
                healExecution,
                damageExecution,
                conditionExecution
            ).filter(Optional::isPresent).count();
            if (activeTargetExecutions > 1) {
                throw new IllegalArgumentException(
                    "Only one target-based execution may be active"
                );
            }

            if (currentAbilityIndex < 0
                || currentAbilityIndex > abilities.size()) {
                throw new IllegalArgumentException(
                    "Current ability index is outside the ability list"
                );
            }
        }
    }

    private boolean hasTargetBasedExecution() {
        return attackExecution != null
            || healExecution != null
            || damageExecution != null
            || conditionExecution != null;
    }
}
