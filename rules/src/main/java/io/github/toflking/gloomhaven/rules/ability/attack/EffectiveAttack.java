package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;

import java.util.List;
import java.util.Objects;

public record EffectiveAttack(
    AbilityValue amount,
    AttackOrigin origin,
    AttackTargetPlan targeting,
    List<AttackEffect> effects
) {

    public EffectiveAttack {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        effects = List.copyOf(effects);
    }

    public EffectiveAttack withAmount(AbilityValue replacement) {
        return new EffectiveAttack(replacement, origin, targeting, effects);
    }

    public EffectiveAttack withOrigin(AttackOrigin replacement) {
        return new EffectiveAttack(amount, replacement, targeting, effects);
    }

    public EffectiveAttack withTargeting(AttackTargetPlan replacement) {
        return new EffectiveAttack(amount, origin, replacement, effects);
    }

    public EffectiveAttack withAddedEffect(AttackEffect effect) {
        Objects.requireNonNull(effect, "effect");

        List<AttackEffect> updated = new java.util.ArrayList<>(effects);
        updated.add(effect);
        return new EffectiveAttack(amount, origin, targeting, updated);
    }
}
