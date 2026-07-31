package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.EffectiveAttack;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackExecutionState;
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
        boolean abilityPerformed,
        boolean cancellationLocked
    ) {
        this.frame = Objects.requireNonNull(frame, "frame");
        this.abilities = List.copyOf(abilities);
        this.memory = Objects.requireNonNull(memory, "memory");
        this.currentAbilityIndex = currentAbilityIndex;
        this.pendingChoice = pendingChoice;
        this.attackExecution = attackExecution;
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

    public void beginAttack(EffectiveAttack attack) {
        if (attackExecution != null) {
            throw new IllegalStateException(
                "An attack execution is already active"
            );
        }
        attackExecution = new AttackExecutionState(attack);
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

    public int currentAbilityIndex() {
        return currentAbilityIndex;
    }

    public void completeCurrentAbility() {
        clearPendingChoice();
        if (attackExecution != null) {
            finishAttack();
        }
        advance();
    }

    public void skipCurrentAbility() {
        lockCancellation();
        clearPendingChoice();
        attackExecution = null;
        advance();
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
        boolean abilityPerformed,
        boolean cancellationLocked
    ) {

        public Snapshot {
            Objects.requireNonNull(frame, "frame");
            abilities = List.copyOf(abilities);
            Objects.requireNonNull(memory, "memory");
            Objects.requireNonNull(pendingChoice, "pendingChoice");
            Objects.requireNonNull(attackExecution, "attackExecution");

            if (currentAbilityIndex < 0
                || currentAbilityIndex > abilities.size()) {
                throw new IllegalArgumentException(
                    "Current ability index is outside the ability list"
                );
            }
        }
    }
}
