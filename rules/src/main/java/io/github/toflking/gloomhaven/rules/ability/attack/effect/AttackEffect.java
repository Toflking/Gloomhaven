package io.github.toflking.gloomhaven.rules.ability.attack.effect;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;

import java.util.Objects;

public sealed interface AttackEffect
    permits AttackEffect.Condition,
    AttackEffect.Curse,
    AttackEffect.ForcedMovement,
    AttackEffect.Pierce,
    AttackEffect.ExecuteAfterAttack,
    AttackEffect.Contextual {

    TargetEffectTiming timing();

    boolean optional();

    record Condition(
        Conditions.ConditionDefinition condition,
        boolean optional
    ) implements AttackEffect {

        public Condition {
            Objects.requireNonNull(condition, "condition");
        }

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.AFTER_DAMAGE;
        }
    }

    record Curse(boolean optional) implements AttackEffect {

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.AFTER_DAMAGE;
        }
    }

    record ForcedMovement(
        ForcedMovementType type,
        IntExpression amount,
        boolean optional
    ) implements AttackEffect {

        public ForcedMovement {
            Objects.requireNonNull(type, "type");
            Objects.requireNonNull(amount, "amount");
        }

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.AFTER_DAMAGE;
        }
    }

    record Pierce(
        IntExpression amount,
        boolean optional
    ) implements AttackEffect {

        public Pierce {
            Objects.requireNonNull(amount, "amount");
        }

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.DAMAGE_CALCULATION;
        }
    }

    record ExecuteAfterAttack(
        FigurePredicate eligibleTargets,
        IntExpression maximumRemainingHealth,
        boolean optional
    ) implements AttackEffect {

        public ExecuteAfterAttack {
            Objects.requireNonNull(
                eligibleTargets,
                "eligibleTargets"
            );
            Objects.requireNonNull(
                maximumRemainingHealth,
                "maximumRemainingHealth"
            );
        }

        @Override
        public TargetEffectTiming timing() {
            return TargetEffectTiming.AFTER_ATTACK;
        }
    }

    record Contextual(
        AttackEffectId id,
        TargetEffectTiming timing,
        boolean optional
    ) implements AttackEffect {

        public Contextual {
            Objects.requireNonNull(id, "id");
            Objects.requireNonNull(timing, "timing");
        }
    }

    enum ForcedMovementType {
        PUSH,
        PULL
    }

    record AttackEffectId(String value) {

        public AttackEffectId {
            Objects.requireNonNull(value, "value");
            if (value.isBlank()) {
                throw new IllegalArgumentException(
                    "Attack effect id cannot be blank"
                );
            }
        }
    }
}
