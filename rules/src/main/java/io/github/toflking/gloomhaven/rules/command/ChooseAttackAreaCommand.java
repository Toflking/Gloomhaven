package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;

import java.util.Objects;

public record ChooseAttackAreaCommand(
    FigureTargetSelection.AreaPlacementOption placement
) implements GameCommand {
    public ChooseAttackAreaCommand {
        Objects.requireNonNull(placement, "placement");
    }
}
