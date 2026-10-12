package io.github.toflking.gloomhaven.rules.ability.heal;

import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.HealEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record EffectiveHeal(
    AbilityValue amount,
    ActionOrigin origin,
    TargetPlan targeting,
    List<HealEffect> effects
) {

    public EffectiveHeal {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        effects = List.copyOf(effects);
    }

    public EffectiveHeal withAmount(AbilityValue replacement) {
        return new EffectiveHeal(replacement, origin, targeting, effects);
    }

    public EffectiveHeal withOrigin(ActionOrigin replacement) {
        return new EffectiveHeal(amount, replacement, targeting, effects);
    }

    public EffectiveHeal withTargeting(TargetPlan replacement) {
        return new EffectiveHeal(amount, origin, replacement, effects);
    }

    public EffectiveHeal withAddedEffect(HealEffect effect) {
        Objects.requireNonNull(effect, "effect");

        List<HealEffect> updated = new ArrayList<>(effects);
        updated.add(effect);
        return new EffectiveHeal(amount, origin, targeting, updated);
    }
}
