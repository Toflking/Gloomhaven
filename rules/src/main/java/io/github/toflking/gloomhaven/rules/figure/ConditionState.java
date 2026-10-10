package io.github.toflking.gloomhaven.rules.figure;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;

import java.util.List;

public class ConditionState {
    List<FigurePredicate.ConditionId> conditions;

    public ConditionState(List<FigurePredicate.ConditionId> conditions) {
        this.conditions = List.copyOf(conditions);
    }

    public List<FigurePredicate.ConditionId> conditions() {
        return conditions;
    }
}
