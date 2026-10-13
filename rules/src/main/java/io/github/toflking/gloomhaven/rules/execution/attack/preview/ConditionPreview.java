package io.github.toflking.gloomhaven.rules.execution.attack.preview;

import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.List;
import java.util.Objects;

public record ConditionPreview(EffectPreview effects) {
    public ConditionPreview {
        Objects.requireNonNull(effects, "effects");
    }

    public ConditionPreview(List<Conditions.ConditionId> conditions) {
        this(new EffectPreview(conditions, List.of()));
    }

    public List<Conditions.ConditionId> conditions() {
        return effects.addedConditions();
    }
}
