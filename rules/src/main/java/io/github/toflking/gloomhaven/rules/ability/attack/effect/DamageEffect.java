package io.github.toflking.gloomhaven.rules.ability.attack.effect;

import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.Objects;

public sealed interface DamageEffect
    permits DamageEffect.ApplyCondition,
    DamageEffect.RemoveCondition,
    DamageEffect.Curse,
    DamageEffect.ForcedMovement,
    DamageEffect.Contextual {

    TargetEffectTiming timing();

    boolean optional();

    record ApplyCondition(
        Conditions.ConditionDefinition condition,
        TargetEffectTiming timing,
        boolean optional
    ) implements DamageEffect {

        public ApplyCondition {
            Objects.requireNonNull(condition, "condition");
            requireDamageTiming(timing);
        }

        public ApplyCondition(
            Conditions.ConditionDefinition condition,
            boolean optional
        ) {
            this(condition, TargetEffectTiming.AFTER_DAMAGE, optional);
        }
    }

    record RemoveCondition(
        Conditions.ConditionId condition,
        TargetEffectTiming timing,
        boolean optional
    ) implements DamageEffect {

        public RemoveCondition {
            Objects.requireNonNull(condition, "condition");
            requireDamageTiming(timing);
        }

        public RemoveCondition(
            Conditions.ConditionId condition,
            boolean optional
        ) {
            this(condition, TargetEffectTiming.AFTER_DAMAGE, optional);
        }
    }

    record Curse(
        TargetEffectTiming timing,
        boolean optional
    ) implements DamageEffect {

        public Curse {
            requireDamageTiming(timing);
        }

        public Curse(boolean optional) {
            this(TargetEffectTiming.AFTER_DAMAGE, optional);
        }
    }

    record ForcedMovement(
        ForcedMovementType type,
        IntExpression amount,
        TargetEffectTiming timing,
        boolean optional
    ) implements DamageEffect {

        public ForcedMovement {
            Objects.requireNonNull(type, "type");
            Objects.requireNonNull(amount, "amount");
            requireDamageTiming(timing);
        }

        public ForcedMovement(
            ForcedMovementType type,
            IntExpression amount,
            boolean optional
        ) {
            this(type, amount, TargetEffectTiming.AFTER_DAMAGE, optional);
        }
    }

    record Contextual(
        DamageEffectId id,
        TargetEffectTiming timing,
        boolean optional
    ) implements DamageEffect {

        public Contextual {
            Objects.requireNonNull(id, "id");
            requireDamageTiming(timing);
        }
    }

    enum ForcedMovementType {
        PUSH,
        PULL
    }

    record DamageEffectId(String value) {

        public DamageEffectId {
            Objects.requireNonNull(value, "value");
            if (value.isBlank()) {
                throw new IllegalArgumentException(
                    "Attack effect id cannot be blank"
                );
            }
        }
    }

    private static void requireDamageTiming(TargetEffectTiming timing) {
        Objects.requireNonNull(timing, "timing");
        if (timing != TargetEffectTiming.BEFORE_DAMAGE
            && timing != TargetEffectTiming.AFTER_DAMAGE) {
            throw new IllegalArgumentException(
                "A direct-damage effect must run before or after damage"
            );
        }
    }
}
