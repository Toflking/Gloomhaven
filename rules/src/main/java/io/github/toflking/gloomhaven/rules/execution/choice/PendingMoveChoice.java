package io.github.toflking.gloomhaven.rules.execution.choice;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record PendingMoveChoice(
    FigureId figureId,
    int movementPoints,
    MovementType movementType
) implements PendingChoice {
}
