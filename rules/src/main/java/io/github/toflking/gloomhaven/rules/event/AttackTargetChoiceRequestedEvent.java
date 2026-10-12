package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record AttackTargetChoiceRequestedEvent(
    FigureId actorId,
    FigureTargetSelection.FigureTargets selection,
    Map<FigureId, AttackPreview> previews
) implements GameEvent {

    public AttackTargetChoiceRequestedEvent {
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(previews, "previews");
        previews = Map.copyOf(previews);
    }
}
