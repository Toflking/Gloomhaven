package io.github.toflking.gloomhaven.rules.ability.effect;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffect;

import java.util.Objects;

public record ShieldAbility(
    AbilityValue amount,
    ActiveEffect.EffectScope scope,
    EffectDurationRule duration
) implements Ability {
    public ShieldAbility {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(scope, "scope");
        Objects.requireNonNull(duration, "duration");
    }
}
