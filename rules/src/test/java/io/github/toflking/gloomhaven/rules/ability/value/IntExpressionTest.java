package io.github.toflking.gloomhaven.rules.ability.value;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntExpressionTest {

    @Test
    void combinesStoredValuesFigureStateCountsAndAttackIndex() {
        ExecutionVariableId damageSuffered =
            new ExecutionVariableId("damage-suffered");
        ExecutionVariableId targets =
            new ExecutionVariableId("targets");

        IntExpression expression = new IntExpression.Add(
            new IntExpression.Variable(damageSuffered),
            new IntExpression.Add(
                new IntExpression.StoredCount(targets),
                new IntExpression.AttackIndex()
            )
        );

        StubContext context = new StubContext(
            Map.of(damageSuffered, 3),
            Map.of(targets, 2),
            1
        );

        assertEquals(6, expression.evaluate(context));
    }

    @Test
    void supportsFloorAndCeilingDivision() {
        IntExpression dividend = new IntExpression.Constant(5);
        IntExpression divisor = new IntExpression.Constant(2);
        StubContext context = new StubContext(
            Map.of(),
            Map.of(),
            0
        );

        assertEquals(
            2,
            new IntExpression.Divide(
                dividend,
                divisor,
                IntExpression.IntegerRounding.FLOOR
            ).evaluate(context)
        );
        assertEquals(
            3,
            new IntExpression.Divide(
                dividend,
                divisor,
                IntExpression.IntegerRounding.CEILING
            ).evaluate(context)
        );
    }

    private record StubContext(
        Map<ExecutionVariableId, Integer> integers,
        Map<ExecutionVariableId, Integer> counts,
        int attackIndex
    ) implements IntExpressionContext {

        @Override
        public int integer(ExecutionVariableId variable) {
            return integers.get(variable);
        }

        @Override
        public int figureStat(
            FigureReference figure,
            IntExpression.FigureStat stat
        ) {
            return 0;
        }

        @Override
        public int count(ExecutionVariableId variable) {
            return counts.get(variable);
        }

        @Override
        public int metric(IntExpression.MetricId metric) {
            return 0;
        }
    }
}
