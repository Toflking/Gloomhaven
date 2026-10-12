package io.github.toflking.gloomhaven.rules.ability.attack.effect;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;

import java.util.Objects;

public sealed interface HealEffect
    permits HealEffect.Condition {

    record Condition(
        FigurePredicate.ConditionId condition,
        TargetEffectTiming timing,
        boolean optional
    ) implements HealEffect {

        public Condition {
            Objects.requireNonNull(condition, "condition");
            Objects.requireNonNull(timing, "timing");
        }
    }
}
