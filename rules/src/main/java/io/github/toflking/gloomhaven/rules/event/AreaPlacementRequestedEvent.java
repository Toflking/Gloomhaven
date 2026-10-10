package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.execution.attack.AreaPlacementControl;
import io.github.toflking.gloomhaven.rules.execution.attack.ResolvedAreaPlacement;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;
import java.util.Objects;

public record AreaPlacementRequestedEvent(
    FigureId attackerId,
    List<ResolvedAreaPlacement> placements,
    AreaPlacementControl control
) implements GameEvent {
    public AreaPlacementRequestedEvent {
        Objects.requireNonNull(attackerId, "attackerId");
        placements = List.copyOf(placements);
        Objects.requireNonNull(control, "control");
    }
}
