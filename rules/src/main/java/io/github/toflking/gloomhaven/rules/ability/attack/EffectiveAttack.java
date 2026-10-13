package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;

import java.util.List;
import java.util.Objects;

public record EffectiveAttack(
    AbilityValue amount,
    ActionOrigin origin,
    TargetPlan targeting,
    List<AttackEffect> effects,
    VisibilityPolicy visibilityPolicy
) {

    public EffectiveAttack {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        Objects.requireNonNull(visibilityPolicy, "visibilityPolicy");
        effects = List.copyOf(effects);
    }

    public EffectiveAttack withAmount(AbilityValue replacement) {
        return new EffectiveAttack(
            replacement,
            origin,
            targeting,
            effects,
            visibilityPolicy
        );
    }

    public EffectiveAttack withOrigin(ActionOrigin replacement) {
        return new EffectiveAttack(
            amount,
            replacement,
            targeting,
            effects,
            visibilityPolicy
        );
    }

    public EffectiveAttack withTargeting(TargetPlan replacement) {
        return new EffectiveAttack(
            amount,
            origin,
            replacement,
            effects,
            visibilityPolicy
        );
    }

    public EffectiveAttack withAddedEffect(AttackEffect effect) {
        Objects.requireNonNull(effect, "effect");

        List<AttackEffect> updated = new java.util.ArrayList<>(effects);
        updated.add(effect);
        return new EffectiveAttack(
            amount,
            origin,
            targeting,
            updated,
            visibilityPolicy
        );
    }
}
