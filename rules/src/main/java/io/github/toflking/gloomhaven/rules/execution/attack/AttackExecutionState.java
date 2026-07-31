package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.EffectiveAttack;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class AttackExecutionState {
    private final EffectiveAttack attack;
    private List<AttackInstance> attacks;
    private int nextAttackIndex;
    private boolean targetsResolved;

    public AttackExecutionState(EffectiveAttack attack) {
        this.attack = Objects.requireNonNull(attack, "attack");
        this.attacks = List.of();
    }

    public EffectiveAttack attack() {
        return attack;
    }

    public void setTargets(List<FigureId> targetIds) {
        Objects.requireNonNull(targetIds, "targetIds");
        if (targetsResolved) {
            throw new IllegalStateException(
                "Attack targets have already been resolved"
            );
        }

        List<AttackInstance> resolved = new ArrayList<>();
        for (int index = 0; index < targetIds.size(); index++) {
            resolved.add(new AttackInstance(targetIds.get(index), index));
        }
        attacks = List.copyOf(resolved);
        targetsResolved = true;
    }

    public List<AttackInstance> attacks() {
        return attacks;
    }

    public boolean hasResolvedTargets() {
        return targetsResolved;
    }

    public AttackInstance currentAttack() {
        if (!targetsResolved) {
            throw new IllegalStateException(
                "Attack targets have not been resolved"
            );
        }
        if (isFinished()) {
            throw new IllegalStateException(
                "Attack execution is already finished"
            );
        }
        return attacks.get(nextAttackIndex);
    }

    public void completeCurrentAttack() {
        if (isFinished()) {
            throw new IllegalStateException(
                "Attack execution is already finished"
            );
        }
        nextAttackIndex++;
    }

    public boolean isFinished() {
        return targetsResolved
            && nextAttackIndex >= attacks.size();
    }

    public int nextAttackIndex() {
        return nextAttackIndex;
    }

    public Snapshot snapshot() {
        return new Snapshot(
            attack,
            attacks,
            nextAttackIndex,
            targetsResolved
        );
    }

    public static AttackExecutionState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");

        AttackExecutionState restored =
            new AttackExecutionState(snapshot.attack());
        restored.attacks = snapshot.attacks();
        restored.nextAttackIndex = snapshot.nextAttackIndex();
        restored.targetsResolved = snapshot.targetsResolved();
        return restored;
    }

    public record Snapshot(
        EffectiveAttack attack,
        List<AttackInstance> attacks,
        int nextAttackIndex,
        boolean targetsResolved
    ) {

        public Snapshot {
            Objects.requireNonNull(attack, "attack");
            attacks = List.copyOf(attacks);

            if (nextAttackIndex < 0
                || nextAttackIndex > attacks.size()) {
                throw new IllegalArgumentException(
                    "Next attack index is outside the attack list"
                );
            }
            if (!targetsResolved
                && (!attacks.isEmpty() || nextAttackIndex != 0)) {
                throw new IllegalArgumentException(
                    "Unresolved targets cannot contain attack progress"
                );
            }
        }
    }
}
