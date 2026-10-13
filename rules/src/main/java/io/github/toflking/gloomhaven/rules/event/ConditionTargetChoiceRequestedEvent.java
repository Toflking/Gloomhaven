package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.ConditionPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record ConditionTargetChoiceRequestedEvent(
    FigureId actorId,
    FigureTargetSelection.FigureTargets selection,
    Map<FigureId, ConditionPreview> previews
) implements GameEvent {
    public ConditionTargetChoiceRequestedEvent {
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
