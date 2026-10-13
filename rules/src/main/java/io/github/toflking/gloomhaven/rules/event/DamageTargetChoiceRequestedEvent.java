package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.DamagePreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record DamageTargetChoiceRequestedEvent(
    FigureId actorId,
    FigureTargetSelection.FigureTargets selection,
    Map<FigureId, DamagePreview> previews
) implements GameEvent {
    public DamageTargetChoiceRequestedEvent {
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
