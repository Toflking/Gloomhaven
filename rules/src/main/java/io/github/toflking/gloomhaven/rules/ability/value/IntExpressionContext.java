package io.github.toflking.gloomhaven.rules.ability.value;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;

public interface IntExpressionContext {

    int integer(ExecutionVariableId variable);

    int figureStat(
        FigureReference figure,
        IntExpression.FigureStat stat
    );

    int count(ExecutionVariableId variable);

    int attackIndex();

    int metric(IntExpression.MetricId metric);
}
