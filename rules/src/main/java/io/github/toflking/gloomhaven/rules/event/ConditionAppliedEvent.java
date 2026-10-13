package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Objects;

public record ConditionAppliedEvent(
    FigureId figureId,
    Conditions.ConditionId conditionId
) implements GameEvent {
    public ConditionAppliedEvent {
        Objects.requireNonNull(figureId, "figureId");
        Objects.requireNonNull(conditionId, "conditionId");
    }
}
