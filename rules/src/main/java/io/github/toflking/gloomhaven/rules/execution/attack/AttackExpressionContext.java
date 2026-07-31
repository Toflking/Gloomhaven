package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.AbilityExecutionFrame;
import io.github.toflking.gloomhaven.rules.execution.ExecutionMemory;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.Objects;

public final class AttackExpressionContext
    implements IntExpressionContext {

    private final ScenarioState state;
    private final AbilityExecutionFrame frame;
    private final ExecutionMemory memory;
    private final FigureId currentTarget;
    private final int attackIndex;

    public AttackExpressionContext(
        ScenarioState state,
        AbilityExecutionFrame frame,
        ExecutionMemory memory,
        FigureId currentTarget,
        int attackIndex
    ) {
        this.state = Objects.requireNonNull(state, "state");
        this.frame = Objects.requireNonNull(frame, "frame");
        this.memory = Objects.requireNonNull(memory, "memory");
        this.currentTarget = currentTarget;
        if (attackIndex < 0) {
            throw new IllegalArgumentException(
                "Attack index cannot be negative"
            );
        }
        this.attackIndex = attackIndex;
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
            case CURRENT_HEALTH -> figure.health();
            case MAXIMUM_HEALTH -> figure.maximumHealth();
            case MISSING_HEALTH ->
                figure.maximumHealth() - figure.health();
        };
    }

    @Override
    public int count(ExecutionVariableId variable) {
        return memory.count(variable);
    }

    @Override
    public int attackIndex() {
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
