package io.github.toflking.gloomhaven.rules.ability.attack.effect;

import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.Objects;

public sealed interface HealEffect
    permits HealEffect.Condition,
    HealEffect.Bless {

    TargetEffectTiming timing();

    boolean optional();

    record Condition(
        Conditions.ConditionDefinition condition,
        boolean optional
    ) implements HealEffect {

        public Condition {
            Objects.requireNonNull(condition, "condition");
        }

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.AFTER_HEAL;
        }
    }

    record Bless(boolean optional) implements HealEffect {

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.AFTER_HEAL;
        }
    }
}
