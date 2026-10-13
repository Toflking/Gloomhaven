package io.github.toflking.gloomhaven.rules.ability.damage;

import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.DamageEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record EffectiveDamage(
    AbilityValue amount,
    ActionOrigin origin,
    TargetPlan targeting,
    List<DamageEffect> effects,
    VisibilityPolicy visibilityPolicy
) {
    public EffectiveDamage {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        Objects.requireNonNull(visibilityPolicy, "visibilityPolicy");
        effects = List.copyOf(effects);
    }

    public EffectiveDamage withAmount(AbilityValue replacement) {
        return new EffectiveDamage(replacement, origin, targeting, effects, visibilityPolicy);
    }

    public EffectiveDamage withOrigin(ActionOrigin replacement) {
        return new EffectiveDamage(amount, replacement, targeting, effects, visibilityPolicy);
    }

    public EffectiveDamage withTargeting(TargetPlan replacement) {
        return new EffectiveDamage(amount, origin, replacement, effects, visibilityPolicy);
    }

    public EffectiveDamage withAddedEffect(DamageEffect effect) {
        Objects.requireNonNull(effect, "effect");

        List<DamageEffect> updated = new ArrayList<>(effects);
        updated.add(effect);
        return new EffectiveDamage(amount, origin, targeting, updated, visibilityPolicy);
    }
}
