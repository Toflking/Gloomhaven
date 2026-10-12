package io.github.toflking.gloomhaven.rules.ability.attack.effect;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

import java.util.Objects;

public sealed interface AttackEffect
    permits AttackEffect.Condition,
    AttackEffect.ForcedMovement,
    AttackEffect.Pierce,
    AttackEffect.ExecuteAfterAttack,
    AttackEffect.Contextual {

    TargetEffectTiming timing();

    boolean optional();

    record Condition(
        FigurePredicate.ConditionId condition,
        TargetEffectTiming timing,
        boolean optional
    ) implements AttackEffect {

        public Condition {
            Objects.requireNonNull(condition, "condition");
            Objects.requireNonNull(timing, "timing");
        }
    }

    record ForcedMovement(
        ForcedMovementType type,
        IntExpression amount,
        TargetEffectTiming timing,
        boolean optional
    ) implements AttackEffect {

        public ForcedMovement {
            Objects.requireNonNull(type, "type");
            Objects.requireNonNull(amount, "amount");
            Objects.requireNonNull(timing, "timing");
        }
    }

    record Pierce(
        IntExpression amount,
        TargetEffectTiming timing,
        boolean optional
    ) implements AttackEffect {

        public Pierce {
            Objects.requireNonNull(amount, "amount");
            Objects.requireNonNull(timing, "timing");
        }
    }

    record ExecuteAfterAttack(
        FigurePredicate eligibleTargets,
        IntExpression maximumRemainingHealth,
        TargetEffectTiming timing,
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
            Objects.requireNonNull(timing, "timing");
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
