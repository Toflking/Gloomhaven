package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.ConditionPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record ConditionAreaPlacementRequestedEvent(
    FigureId actorId,
    FigureTargetSelection.AreaPlacement selection,
    Map<FigureId, ConditionPreview> previews
) implements GameEvent {
    public ConditionAreaPlacementRequestedEvent {
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
