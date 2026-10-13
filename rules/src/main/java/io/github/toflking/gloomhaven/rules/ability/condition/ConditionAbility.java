package io.github.toflking.gloomhaven.rules.ability.condition;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.List;
import java.util.Objects;

/** Applies conditions without performing an attack. */
public record ConditionAbility(
    List<Conditions.ConditionDefinition> conditions,
    ActionOrigin origin,
    TargetPlan targeting,
    VisibilityPolicy visibilityPolicy
) implements Ability {

    public ConditionAbility {
        conditions = List.copyOf(conditions);
        if (conditions.isEmpty()) {
            throw new IllegalArgumentException(
                "A condition ability must apply at least one condition"
            );
        }
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        Objects.requireNonNull(visibilityPolicy, "visibilityPolicy");
    }

    public ConditionAbility(
        List<Conditions.ConditionDefinition> conditions,
        ActionOrigin origin,
        TargetPlan targeting
    ) {
        this(
            conditions,
            origin,
            targeting,
            VisibilityPolicy.RESPECT_ENEMY_INVISIBILITY
        );
    }

    public ConditionAbility(
        List<Conditions.ConditionDefinition> conditions,
        TargetPlan targeting
    ) {
        this(
            conditions,
            new ActionOrigin.Actor(),
            targeting,
            VisibilityPolicy.RESPECT_ENEMY_INVISIBILITY
        );
    }

    public EffectiveCondition effectiveDefinition() {
        return new EffectiveCondition(
            conditions,
            origin,
            targeting,
            visibilityPolicy
        );
    }
}
