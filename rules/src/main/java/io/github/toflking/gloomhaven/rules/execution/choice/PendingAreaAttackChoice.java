package io.github.toflking.gloomhaven.rules.execution.choice;

import io.github.toflking.gloomhaven.rules.execution.attack.AreaPlacementControl;
import io.github.toflking.gloomhaven.rules.execution.attack.ResolvedAreaPlacement;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;
import java.util.Objects;

public record PendingAreaAttackChoice(
    FigureId attackerId,
    List<ResolvedAreaPlacement> placements,
    AreaPlacementControl control
) implements PendingChoice {
    public PendingAreaAttackChoice {
        Objects.requireNonNull(attackerId, "attackerId");
        placements = List.copyOf(placements);
        Objects.requireNonNull(control, "control");
    }
}
