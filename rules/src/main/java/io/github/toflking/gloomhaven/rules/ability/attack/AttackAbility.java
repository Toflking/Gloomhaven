package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;

import java.util.List;
import java.util.Objects;

public record AttackAbility(
    AbilityValue amount,
    AttackOrigin origin,
    AttackTargetPlan targeting,
    List<AttackEffect> effects
) implements Ability {

    public AttackAbility {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        effects = List.copyOf(effects);
    }

    public AttackAbility(
        AbilityValue amount,
        AttackTargetPlan targeting
    ) {
        this(amount, new AttackOrigin.Actor(), targeting, List.of());
    }

    public EffectiveAttack effectiveDefinition() {
        return new EffectiveAttack(amount, origin, targeting, effects);
    }
}
