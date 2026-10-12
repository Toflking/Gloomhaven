package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;

import java.util.List;
import java.util.Objects;

public record AttackAbility(
    AbilityValue amount,
    ActionOrigin origin,
    TargetPlan targeting,
    List<AttackEffect> effects
) implements Ability {

    public AttackAbility {
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(origin, "origin");
        Objects.requireNonNull(targeting, "targeting");
        effects = List.copyOf(effects);
        if (amount.evaluationMoment() == EvaluationMoment.PER_TARGET) {
            throw new IllegalArgumentException(
                "An attack amount must use PER_ATTACK instead of PER_TARGET"
            );
        }
    }

    public AttackAbility(
        AbilityValue amount,
        TargetPlan targeting
    ) {
        this(amount, new ActionOrigin.Actor(), targeting, List.of());
    }

    public EffectiveAttack effectiveDefinition() {
        return new EffectiveAttack(amount, origin, targeting, effects);
    }
}
