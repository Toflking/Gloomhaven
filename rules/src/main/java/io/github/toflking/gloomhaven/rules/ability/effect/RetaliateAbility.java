package io.github.toflking.gloomhaven.rules.ability.effect;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffect;

import java.util.Objects;

public record RetaliateAbility(
    AbilityValue amount,
    AbilityValue range,
    LineOfSightPolicy lineOfSight,
    ActiveEffect.EffectScope scope,
    EffectDurationRule duration
) implements Ability {
    public RetaliateAbility {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(range, "range");
        Objects.requireNonNull(lineOfSight, "lineOfSight");
        Objects.requireNonNull(scope, "scope");
        Objects.requireNonNull(duration, "duration");
    }

    public RetaliateAbility(
        AbilityValue amount,
        AbilityValue range,
        ActiveEffect.EffectScope scope,
        EffectDurationRule duration
    ) {
        this(
            amount,
            range,
            LineOfSightPolicy.REQUIRED,
            scope,
            duration
        );
    }
}
