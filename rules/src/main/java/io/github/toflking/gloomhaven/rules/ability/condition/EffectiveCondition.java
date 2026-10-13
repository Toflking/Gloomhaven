package io.github.toflking.gloomhaven.rules.ability.condition;

import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.List;
import java.util.Objects;

public record EffectiveCondition(
    List<Conditions.ConditionDefinition> conditions,
    ActionOrigin origin,
    TargetPlan targeting,
    VisibilityPolicy visibilityPolicy
) {
    public EffectiveCondition {
        conditions = List.copyOf(conditions);
        if (conditions.isEmpty()) {
            throw new IllegalArgumentException(
                "An effective condition ability cannot be empty"
            );
        }
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        Objects.requireNonNull(visibilityPolicy, "visibilityPolicy");
    }

    public EffectiveCondition withOrigin(ActionOrigin replacement) {
        return new EffectiveCondition(
            conditions,
            replacement,
            targeting,
            visibilityPolicy
        );
    }

    public EffectiveCondition withTargeting(TargetPlan replacement) {
        return new EffectiveCondition(
            conditions,
            origin,
            replacement,
            visibilityPolicy
        );
    }
}
