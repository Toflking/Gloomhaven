package io.github.toflking.gloomhaven.rules.ability.value;

import java.util.Objects;

public record AbilityValue(
    IntExpression expression,
    EvaluationMoment evaluationMoment
) {

    public AbilityValue {
        Objects.requireNonNull(expression, "expression");
        Objects.requireNonNull(evaluationMoment, "evaluationMoment");
    }

    public static AbilityValue fixed(int value) {
        return new AbilityValue(
            new IntExpression.Constant(value),
            EvaluationMoment.ABILITY_START
        );
    }

    public int resolve(IntExpressionContext context) {
        return expression.evaluate(context);
    }
}
