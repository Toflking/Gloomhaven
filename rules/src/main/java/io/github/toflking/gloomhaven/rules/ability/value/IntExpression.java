package io.github.toflking.gloomhaven.rules.ability.value;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;

import java.util.Objects;

public sealed interface IntExpression
    permits IntExpression.Constant,
    IntExpression.Variable,
    IntExpression.FigureStatValue,
    IntExpression.StoredCount,
    IntExpression.AttackIndex,
    IntExpression.ContextMetric,
    IntExpression.Add,
    IntExpression.Subtract,
    IntExpression.Multiply,
    IntExpression.Divide,
    IntExpression.Minimum,
    IntExpression.Maximum {

    int evaluate(IntExpressionContext context);

    record Constant(int value) implements IntExpression {

        @Override
        public int evaluate(IntExpressionContext context) {
            return value;
        }
    }

    record Variable(ExecutionVariableId variable)
        implements IntExpression {

        public Variable {
            Objects.requireNonNull(variable, "variable");
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return context.integer(variable);
        }
    }

    record FigureStatValue(
        FigureReference figure,
        FigureStat stat
    ) implements IntExpression {

        public FigureStatValue {
            Objects.requireNonNull(figure, "figure");
            Objects.requireNonNull(stat, "stat");
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return context.figureStat(figure, stat);
        }
    }

    record StoredCount(ExecutionVariableId variable)
        implements IntExpression {

        public StoredCount {
            Objects.requireNonNull(variable, "variable");
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return context.count(variable);
        }
    }

    record AttackIndex() implements IntExpression {

        @Override
        public int evaluate(IntExpressionContext context) {
            return context.attackIndex();
        }
    }

    record ContextMetric(MetricId metric)
        implements IntExpression {

        public ContextMetric {
            Objects.requireNonNull(metric, "metric");
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return context.metric(metric);
        }
    }

    record Add(IntExpression left, IntExpression right)
        implements IntExpression {

        public Add {
            requireOperands(left, right);
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return left.evaluate(context) + right.evaluate(context);
        }
    }

    record Subtract(IntExpression left, IntExpression right)
        implements IntExpression {

        public Subtract {
            requireOperands(left, right);
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return left.evaluate(context) - right.evaluate(context);
        }
    }

    record Multiply(IntExpression left, IntExpression right)
        implements IntExpression {

        public Multiply {
            requireOperands(left, right);
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return left.evaluate(context) * right.evaluate(context);
        }
    }

    record Divide(
        IntExpression dividend,
        IntExpression divisor,
        IntegerRounding rounding
    ) implements IntExpression {

        public Divide {
            requireOperands(dividend, divisor);
            Objects.requireNonNull(rounding, "rounding");
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            int numerator = dividend.evaluate(context);
            int denominator = divisor.evaluate(context);

            if (denominator == 0) {
                throw new ArithmeticException(
                    "Cannot divide an ability value by zero"
                );
            }

            return switch (rounding) {
                case FLOOR -> Math.floorDiv(numerator, denominator);
                case CEILING -> -Math.floorDiv(-numerator, denominator);
            };
        }
    }

    record Minimum(IntExpression left, IntExpression right)
        implements IntExpression {

        public Minimum {
            requireOperands(left, right);
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return Math.min(left.evaluate(context), right.evaluate(context));
        }
    }

    record Maximum(IntExpression left, IntExpression right)
        implements IntExpression {

        public Maximum {
            requireOperands(left, right);
        }

        @Override
        public int evaluate(IntExpressionContext context) {
            return Math.max(left.evaluate(context), right.evaluate(context));
        }
    }

    enum FigureStat {
        CURRENT_HEALTH,
        MAXIMUM_HEALTH,
        MISSING_HEALTH
    }

    enum IntegerRounding {
        FLOOR,
        CEILING
    }

    record MetricId(String value) {

        public MetricId {
            Objects.requireNonNull(value, "value");
            if (value.isBlank()) {
                throw new IllegalArgumentException(
                    "Metric id cannot be blank"
                );
            }
        }
    }

    private static void requireOperands(
        IntExpression left,
        IntExpression right
    ) {
        Objects.requireNonNull(left, "left");
        Objects.requireNonNull(right, "right");
    }
}
