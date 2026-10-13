package io.github.toflking.gloomhaven.rules.ability.damage;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.DamageEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;

import java.util.List;
import java.util.Objects;

/** Damage that is not an attack and therefore draws no attack modifier. */
public record DamageAbility(
    AbilityValue amount,
    ActionOrigin origin,
    TargetPlan targeting,
    List<DamageEffect> effects,
    VisibilityPolicy visibilityPolicy
) implements Ability {

    public DamageAbility {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        Objects.requireNonNull(visibilityPolicy, "visibilityPolicy");
        effects = List.copyOf(effects);
        if (amount.evaluationMoment() == EvaluationMoment.PER_ATTACK) {
            throw new IllegalArgumentException(
                "A direct-damage amount cannot be evaluated per attack"
            );
        }
    }

    public DamageAbility(
        AbilityValue amount,
        ActionOrigin origin,
        TargetPlan targeting,
        List<DamageEffect> effects
    ) {
        this(
            amount,
            origin,
            targeting,
            effects,
            VisibilityPolicy.IGNORE_ENEMY_INVISIBILITY
        );
    }

    public DamageAbility(
        AbilityValue amount,
        TargetPlan targeting,
        List<DamageEffect> effects
    ) {
        this(
            amount,
            new ActionOrigin.Actor(),
            targeting,
            effects,
            VisibilityPolicy.IGNORE_ENEMY_INVISIBILITY
        );
    }

    public DamageAbility(AbilityValue amount, TargetPlan targeting) {
        this(amount, targeting, List.of());
    }

    public EffectiveDamage effectiveDefinition() {
        return new EffectiveDamage(
            amount,
            origin,
            targeting,
            effects,
            visibilityPolicy
        );
    }
}
