package io.github.toflking.gloomhaven.rules.ability.heal;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.HealEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;

import java.util.List;
import java.util.Objects;

public record HealAbility(
    AbilityValue amount,
    ActionOrigin origin,
    TargetPlan targeting,
    List<HealEffect> effects,
    VisibilityPolicy visibilityPolicy
) implements Ability {
    public HealAbility {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        Objects.requireNonNull(visibilityPolicy, "visibilityPolicy");
        effects = List.copyOf(effects);
        if (amount.evaluationMoment() == EvaluationMoment.PER_ATTACK) {
            throw new IllegalArgumentException(
                "A heal amount cannot be evaluated per attack"
            );
        }
    }

    public HealAbility(
        AbilityValue amount,
        ActionOrigin origin,
        TargetPlan targeting,
        List<HealEffect> effects
    ) {
        this(
            amount,
            origin,
            targeting,
            effects,
            VisibilityPolicy.RESPECT_ENEMY_INVISIBILITY
        );
    }

    public HealAbility(
        AbilityValue amount,
        TargetPlan targeting
    ) {
        this(
            amount,
            new ActionOrigin.Actor(),
            targeting,
            List.of(),
            VisibilityPolicy.RESPECT_ENEMY_INVISIBILITY
        );
    }

    public EffectiveHeal effectiveDefinition() {
        return new EffectiveHeal(
            amount,
            origin,
            targeting,
            effects,
            visibilityPolicy
        );
    }
}
