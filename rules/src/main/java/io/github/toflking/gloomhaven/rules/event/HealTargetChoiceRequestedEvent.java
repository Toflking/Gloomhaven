package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record HealTargetChoiceRequestedEvent (
    FigureId actorId,
    FigureTargetSelection.FigureTargets selection,
    Map<FigureId, HealPreview> previews
) implements GameEvent {

    public HealTargetChoiceRequestedEvent {
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
