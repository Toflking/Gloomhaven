package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpressionContext;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.Objects;

public final class AbilityExpressionContext
    implements IntExpressionContext {

    private final ScenarioState state;
    private final AbilityExecutionFrame frame;
    private final ExecutionMemory memory;
    private final FigureId currentTarget;
    private final Integer attackIndex;

    private AbilityExpressionContext(
        ScenarioState state,
        AbilityExecutionFrame frame,
        ExecutionMemory memory,
        FigureId currentTarget,
        Integer attackIndex
    ) {
        this.state = Objects.requireNonNull(state, "state");
        this.frame = Objects.requireNonNull(frame, "frame");
        this.memory = Objects.requireNonNull(memory, "memory");
        this.currentTarget = currentTarget;
        if (attackIndex != null && attackIndex < 0) {
            throw new IllegalArgumentException(
                "Attack index cannot be negative"
            );
        }
        this.attackIndex = attackIndex;
    }

    public static AbilityExpressionContext withoutTarget(
        ScenarioState state,
        AbilityExecutionFrame frame,
        ExecutionMemory memory
    ) {
        return new AbilityExpressionContext(
            state,
            frame,
            memory,
            null,
            null
        );
    }

    public static AbilityExpressionContext forTarget(
        ScenarioState state,
        AbilityExecutionFrame frame,
        ExecutionMemory memory,
        FigureId currentTarget,
        int attackIndex
    ) {
        return new AbilityExpressionContext(
            state,
            frame,
            memory,
            Objects.requireNonNull(currentTarget, "currentTarget"),
            attackIndex
        );
    }

    @Override
    public int integer(ExecutionVariableId variable) {
        return memory.requireInteger(variable);
    }

    @Override
    public int figureStat(
        FigureReference reference,
        IntExpression.FigureStat stat
    ) {
        Figure figure = state.figure(resolveFigure(reference));
        return switch (stat) {
            case CURRENT_HEALTH -> figure.health().currentHealth();
            case MAXIMUM_HEALTH -> figure.health().maximumHealth();
            case MISSING_HEALTH ->
                figure.health().maximumHealth()
                    - figure.health().currentHealth();
        };
    }

    @Override
    public int count(ExecutionVariableId variable) {
        return memory.count(variable);
    }

    @Override
    public int attackIndex() {
        if (attackIndex == null) {
            throw new IllegalStateException(
                "No attack index is available in this ability context"
            );
        }
        return attackIndex;
    }

    @Override
    public int metric(IntExpression.MetricId metric) {
        return memory.requireInteger(
            new ExecutionVariableId(metric.value())
        );
    }

    private FigureId resolveFigure(FigureReference reference) {
        return switch (reference) {
            case FigureReference.Actor ignored -> frame.actorId();
            case FigureReference.Owner ignored -> frame.ownerId();
            case FigureReference.Controller ignored -> frame.controllerId();
            case FigureReference.CurrentTarget ignored -> {
                if (currentTarget == null) {
                    throw new IllegalStateException(
                        "No current target is available"
                    );
                }
                yield currentTarget;
            }
            case FigureReference.Stored stored ->
                memory.requireFigure(stored.variable());
        };
    }
}
