package io.github.toflking.gloomhaven.rules.execution.attack.preview;

import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.List;

public record EffectPreview(
    List<Conditions.ConditionId> addedConditions,
    List<Conditions.ConditionId> removedConditions
) {
    private static final EffectPreview EMPTY =
        new EffectPreview(List.of(), List.of());

    public EffectPreview {
        addedConditions = List.copyOf(addedConditions);
        removedConditions = List.copyOf(removedConditions);
    }

    public static EffectPreview empty() {
        return EMPTY;
    }
}
