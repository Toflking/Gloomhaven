package io.github.toflking.gloomhaven.rules.figure;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConditionState {
    List<FigurePredicate.ConditionId> conditions;

    public ConditionState(List<FigurePredicate.ConditionId> conditions) {
        this.conditions = List.copyOf(conditions);
    }

    public List<FigurePredicate.ConditionId> conditions() {
        return conditions;
    }

    public boolean contains(FigurePredicate.ConditionId condition) {
        return conditions.contains(
            Objects.requireNonNull(condition, "condition")
        );
    }

    public boolean remove(FigurePredicate.ConditionId condition) {
        Objects.requireNonNull(condition, "condition");
        if (!conditions.contains(condition)) {
            return false;
        }

        List<FigurePredicate.ConditionId> updated =
            new ArrayList<>(conditions);
        updated.remove(condition);
        conditions = List.copyOf(updated);
        return true;
    }
}
