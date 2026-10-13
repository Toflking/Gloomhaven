package io.github.toflking.gloomhaven.rules.execution.condition;

import io.github.toflking.gloomhaven.rules.ability.condition.EffectiveCondition;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;
import java.util.Objects;

public final class ConditionExecutionState {
    private final EffectiveCondition condition;
    private List<FigureId> targets;
    private int nextTargetIndex;
    private boolean targetsResolved;

    public ConditionExecutionState(EffectiveCondition condition) {
        this.condition = Objects.requireNonNull(condition, "condition");
        targets = List.of();
    }

    private ConditionExecutionState(
        EffectiveCondition condition,
        List<FigureId> targets,
        int nextTargetIndex,
        boolean targetsResolved
    ) {
        this.condition = Objects.requireNonNull(condition, "condition");
        this.targets = List.copyOf(targets);
        this.nextTargetIndex = nextTargetIndex;
        this.targetsResolved = targetsResolved;
    }

    public EffectiveCondition condition() {
        return condition;
    }

    public void setTargets(List<FigureId> targetIds) {
        Objects.requireNonNull(targetIds, "targetIds");
        if (targetsResolved) {
            throw new IllegalStateException(
                "Condition targets have already been resolved"
            );
        }
        targets = List.copyOf(targetIds);
        targetsResolved = true;
    }

    public FigureId currentTarget() {
        requireCurrentTarget();
        return targets.get(nextTargetIndex);
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
            condition,
            targets,
            nextTargetIndex,
            targetsResolved
        );
    }

    public static ConditionExecutionState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");
        return new ConditionExecutionState(
            snapshot.condition(),
            snapshot.targets(),
            snapshot.nextTargetIndex(),
            snapshot.targetsResolved()
        );
    }

    private void requireCurrentTarget() {
        if (!targetsResolved || isFinished()) {
            throw new IllegalStateException(
                "There is no current condition target"
            );
        }
    }

    public record Snapshot(
        EffectiveCondition condition,
        List<FigureId> targets,
        int nextTargetIndex,
        boolean targetsResolved
    ) {
        public Snapshot {
            Objects.requireNonNull(condition, "condition");
            targets = List.copyOf(targets);
            if (nextTargetIndex < 0 || nextTargetIndex > targets.size()) {
                throw new IllegalArgumentException(
                    "Next condition target index is outside the target list"
                );
            }
            if (!targetsResolved
                && (!targets.isEmpty() || nextTargetIndex != 0)) {
                throw new IllegalArgumentException(
                    "Unresolved condition targets cannot contain progress"
                );
            }
        }
    }
}
