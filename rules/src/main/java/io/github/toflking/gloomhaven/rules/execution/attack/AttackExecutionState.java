package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.EffectiveAttack;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.AbilityValueExecutionState;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class AttackExecutionState {
    private final EffectiveAttack attack;
    private final AbilityValueExecutionState amount;
    private List<AttackInstance> attacks;
    private int nextAttackIndex;
    private boolean targetsResolved;

    public AttackExecutionState(
        EffectiveAttack attack,
        AbilityExpressionContext abilityStartContext
    ) {
        this.attack = Objects.requireNonNull(attack, "attack");
        this.amount = new AbilityValueExecutionState(
            attack.amount(),
            abilityStartContext
        );
        this.attacks = List.of();
    }

    private AttackExecutionState(
        EffectiveAttack attack,
        AbilityValueExecutionState amount,
        List<AttackInstance> attacks,
        int nextAttackIndex,
        boolean targetsResolved
    ) {
        this.attack = Objects.requireNonNull(attack, "attack");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.attacks = List.copyOf(attacks);
        this.nextAttackIndex = nextAttackIndex;
        this.targetsResolved = targetsResolved;
    }

    public EffectiveAttack attack() {
        return attack;
    }

    public int previewAmount(AbilityExpressionContext context) {
        return Math.max(0, amount.preview(context));
    }

    public void setTargets(
        List<FigureId> targetIds,
        AbilityExpressionContext afterTargetSelectionContext
    ) {
        Objects.requireNonNull(targetIds, "targetIds");
        Objects.requireNonNull(
            afterTargetSelectionContext,
            "afterTargetSelectionContext"
        );
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
        amount.resolveAfterTargetSelection(
            afterTargetSelectionContext
        );
        targetsResolved = true;
    }

    public int amountForCurrentAttack(
        AbilityExpressionContext context
    ) {
        if (!targetsResolved || isFinished()) {
            throw new IllegalStateException(
                "There is no current attack"
            );
        }
        return Math.max(
            0,
            amount.currentValue(context, EvaluationMoment.PER_ATTACK)
        );
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
            amount.snapshot(),
            attacks,
            nextAttackIndex,
            targetsResolved
        );
    }

    public static AttackExecutionState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");

        return new AttackExecutionState(
            snapshot.attack(),
            AbilityValueExecutionState.restore(snapshot.amount()),
            snapshot.attacks(),
            snapshot.nextAttackIndex(),
            snapshot.targetsResolved()
        );
    }

    public record Snapshot(
        EffectiveAttack attack,
        AbilityValueExecutionState.Snapshot amount,
        List<AttackInstance> attacks,
        int nextAttackIndex,
        boolean targetsResolved
    ) {

        public Snapshot {
            Objects.requireNonNull(attack, "attack");
            Objects.requireNonNull(amount, "amount");
            attacks = List.copyOf(attacks);

            if (!amount.value().equals(attack.amount())) {
                throw new IllegalArgumentException(
                    "Cached attack amount does not match the attack"
                );
            }

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
            if (targetsResolved
                && attack.amount().evaluationMoment()
                    == EvaluationMoment.AFTER_TARGET_SELECTION
                && amount.valueAfterTargetSelection() == null) {
                throw new IllegalArgumentException(
                    "Post-selection attack amount is missing"
                );
            }
        }
    }
}
