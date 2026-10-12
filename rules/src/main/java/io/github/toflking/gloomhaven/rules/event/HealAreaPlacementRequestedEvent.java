package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record HealAreaPlacementRequestedEvent(
    FigureId actorId,
    FigureTargetSelection.AreaPlacement selection,
    Map<FigureId, HealPreview> previews
) implements GameEvent {
    public HealAreaPlacementRequestedEvent {
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(previews, "previews");
        previews = Map.copyOf(previews);
    }
}

