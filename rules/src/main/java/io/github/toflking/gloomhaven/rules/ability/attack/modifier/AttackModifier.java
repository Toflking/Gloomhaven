package io.github.toflking.gloomhaven.rules.ability.attack.modifier;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

import java.util.Objects;

public sealed interface AttackModifier
    permits AttackModifier.AddTargets,
    AttackModifier.ReplaceTargetPlan,
    AttackModifier.AllowRepeatedTargets,
    AttackModifier.ModifyAttackValue,
    AttackModifier.ModifyRange,
    AttackModifier.AddEffect,
    AttackModifier.ReplaceWithExecute,
    AttackModifier.ChangeOrigin {

    record AddTargets(IntExpression amount)
        implements AttackModifier {

        public AddTargets {
            Objects.requireNonNull(amount, "amount");
        }
    }

    record ReplaceTargetPlan(AttackTargetPlan replacement)
        implements AttackModifier {

        public ReplaceTargetPlan {
            Objects.requireNonNull(replacement, "replacement");
        }
    }

    record AllowRepeatedTargets() implements AttackModifier {
    }

    record ModifyAttackValue(
        ValueOperation operation,
        IntExpression amount
    ) implements AttackModifier {

        public ModifyAttackValue {
            Objects.requireNonNull(operation, "operation");
            Objects.requireNonNull(amount, "amount");
        }
    }

    record ModifyRange(
        ValueOperation operation,
        IntExpression amount
    ) implements AttackModifier {

        public ModifyRange {
            Objects.requireNonNull(operation, "operation");
            Objects.requireNonNull(amount, "amount");
        }
    }

    record AddEffect(AttackEffect effect)
        implements AttackModifier {

        public AddEffect {
            Objects.requireNonNull(effect, "effect");
        }
    }

    record ReplaceWithExecute(
        FigurePredicate eligibleTargets,
        IntExpression maximumReplacements
    ) implements AttackModifier {

        public ReplaceWithExecute {
            Objects.requireNonNull(
                eligibleTargets,
                "eligibleTargets"
            );
            Objects.requireNonNull(
                maximumReplacements,
                "maximumReplacements"
            );
        }
    }

    record ChangeOrigin(AttackOrigin replacement)
        implements AttackModifier {

        public ChangeOrigin {
            Objects.requireNonNull(replacement, "replacement");
        }
    }

    enum ValueOperation {
        ADD,
        MULTIPLY,
        REPLACE
    }
}
