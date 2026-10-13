package io.github.toflking.gloomhaven.rules.execution.damage;

import io.github.toflking.gloomhaven.rules.ability.damage.EffectiveDamage;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.AbilityValueExecutionState;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;
import java.util.Objects;

public final class DamageExecutionState {
    private final EffectiveDamage damage;
    private final AbilityValueExecutionState amount;
    private List<FigureId> targets;
    private int nextTargetIndex;
    private boolean targetsResolved;

    public DamageExecutionState(
        EffectiveDamage damage,
        AbilityExpressionContext abilityStartContext
    ) {
        this.damage = Objects.requireNonNull(damage, "damage");
        amount = new AbilityValueExecutionState(
            damage.amount(),
            abilityStartContext
        );
        targets = List.of();
    }

    private DamageExecutionState(
        EffectiveDamage damage,
        AbilityValueExecutionState amount,
        List<FigureId> targets,
        int nextTargetIndex,
        boolean targetsResolved
    ) {
        this.damage = Objects.requireNonNull(damage, "damage");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.targets = List.copyOf(targets);
        this.nextTargetIndex = nextTargetIndex;
        this.targetsResolved = targetsResolved;
    }

    public EffectiveDamage damage() {
        return damage;
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
                "Damage targets have already been resolved"
            );
        }

        targets = List.copyOf(targetIds);
        amount.resolveAfterTargetSelection(afterTargetSelectionContext);
        targetsResolved = true;
    }

    public FigureId currentTarget() {
        requireCurrentTarget();
        return targets.get(nextTargetIndex);
    }

    public int amountForCurrentTarget(
        AbilityExpressionContext context
    ) {
        requireCurrentTarget();
        return Math.max(
            0,
            amount.currentValue(context, EvaluationMoment.PER_TARGET)
        );
    }

    public void completeCurrentTarget() {
        requireCurrentTarget();
        nextTargetIndex++;
    }

    public boolean hasResolvedTargets() {
        return targetsResolved;
    }

    public boolean isFinished() {
        return targetsResolved && nextTargetIndex >= targets.size();
    }

    public int nextTargetIndex() {
        return nextTargetIndex;
    }

    public Snapshot snapshot() {
        return new Snapshot(
            damage,
            amount.snapshot(),
            targets,
            nextTargetIndex,
            targetsResolved
        );
    }

    public static DamageExecutionState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");
        return new DamageExecutionState(
            snapshot.damage(),
            AbilityValueExecutionState.restore(snapshot.amount()),
            snapshot.targets(),
            snapshot.nextTargetIndex(),
            snapshot.targetsResolved()
        );
    }

    private void requireCurrentTarget() {
        if (!targetsResolved || isFinished()) {
            throw new IllegalStateException(
                "There is no current direct-damage target"
            );
        }
    }

    public record Snapshot(
        EffectiveDamage damage,
        AbilityValueExecutionState.Snapshot amount,
        List<FigureId> targets,
        int nextTargetIndex,
        boolean targetsResolved
    ) {
        public Snapshot {
            Objects.requireNonNull(damage, "damage");
            Objects.requireNonNull(amount, "amount");
            targets = List.copyOf(targets);
            if (!amount.value().equals(damage.amount())) {
                throw new IllegalArgumentException(
                    "Cached damage amount does not match the ability"
                );
            }
            if (nextTargetIndex < 0 || nextTargetIndex > targets.size()) {
                throw new IllegalArgumentException(
                    "Next damage target index is outside the target list"
                );
            }
            if (!targetsResolved
                && (!targets.isEmpty() || nextTargetIndex != 0)) {
                throw new IllegalArgumentException(
                    "Unresolved damage targets cannot contain progress"
                );
            }
            if (targetsResolved
                && damage.amount().evaluationMoment()
                    == EvaluationMoment.AFTER_TARGET_SELECTION
                && amount.valueAfterTargetSelection() == null) {
                throw new IllegalArgumentException(
                    "Post-selection damage amount is missing"
                );
            }
        }
    }
}
