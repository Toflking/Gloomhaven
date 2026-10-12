package io.github.toflking.gloomhaven.rules.execution.heal;

import io.github.toflking.gloomhaven.rules.ability.heal.EffectiveHeal;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.AbilityValueExecutionState;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;
import java.util.Objects;

public final class HealExecutionState {
    private final EffectiveHeal heal;
    private final AbilityValueExecutionState amount;
    private List<FigureId> targets;
    private int nextTargetIndex;
    private boolean targetsResolved;

    public HealExecutionState(
        EffectiveHeal heal,
        AbilityExpressionContext abilityStartContext
    ) {
        this.heal = Objects.requireNonNull(heal, "heal");
        this.amount = new AbilityValueExecutionState(
            heal.amount(),
            abilityStartContext
        );
        this.targets = List.of();
    }

    private HealExecutionState(
        EffectiveHeal heal,
        AbilityValueExecutionState amount,
        List<FigureId> targets,
        int nextTargetIndex,
        boolean targetsResolved
    ) {
        this.heal = Objects.requireNonNull(heal, "heal");
        this.amount = Objects.requireNonNull(amount, "amount");
        this.targets = List.copyOf(targets);
        this.nextTargetIndex = nextTargetIndex;
        this.targetsResolved = targetsResolved;
    }

    public EffectiveHeal heal() {
        return heal;
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
                "Heal targets have already been resolved"
            );
        }

        targets = List.copyOf(targetIds);
        amount.resolveAfterTargetSelection(
            afterTargetSelectionContext
        );
        targetsResolved = true;
    }

    public FigureId currentTarget() {
        if (!targetsResolved) {
            throw new IllegalStateException(
                "Heal targets have not been resolved"
            );
        }
        if (isFinished()) {
            throw new IllegalStateException(
                "Heal execution is already finished"
            );
        }
        return targets.get(nextTargetIndex);
    }

    public int amountForCurrentTarget(
        AbilityExpressionContext context
    ) {
        if (!targetsResolved || isFinished()) {
            throw new IllegalStateException(
                "There is no current heal target"
            );
        }
        return Math.max(
            0,
            amount.currentValue(context, EvaluationMoment.PER_TARGET)
        );
    }

    public void completeCurrentTarget() {
        if (isFinished()) {
            throw new IllegalStateException(
                "Heal execution is already finished"
            );
        }
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
            heal,
            amount.snapshot(),
            targets,
            nextTargetIndex,
            targetsResolved
        );
    }

    public static HealExecutionState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");
        return new HealExecutionState(
            snapshot.heal(),
            AbilityValueExecutionState.restore(snapshot.amount()),
            snapshot.targets(),
            snapshot.nextTargetIndex(),
            snapshot.targetsResolved()
        );
    }

    public record Snapshot(
        EffectiveHeal heal,
        AbilityValueExecutionState.Snapshot amount,
        List<FigureId> targets,
        int nextTargetIndex,
        boolean targetsResolved
    ) {

        public Snapshot {
            Objects.requireNonNull(heal, "heal");
            Objects.requireNonNull(amount, "amount");
            targets = List.copyOf(targets);

            if (!amount.value().equals(heal.amount())) {
                throw new IllegalArgumentException(
                    "Cached heal amount does not match the heal"
                );
            }

            if (nextTargetIndex < 0
                || nextTargetIndex > targets.size()) {
                throw new IllegalArgumentException(
                    "Next heal target index is outside the target list"
                );
            }
            if (!targetsResolved
                && (!targets.isEmpty() || nextTargetIndex != 0)) {
                throw new IllegalArgumentException(
                    "Unresolved heal targets cannot contain progress"
                );
            }
            if (targetsResolved
                && heal.amount().evaluationMoment()
                    == EvaluationMoment.AFTER_TARGET_SELECTION
                && amount.valueAfterTargetSelection() == null) {
                throw new IllegalArgumentException(
                    "Post-selection heal amount is missing"
                );
            }
        }
    }
}
