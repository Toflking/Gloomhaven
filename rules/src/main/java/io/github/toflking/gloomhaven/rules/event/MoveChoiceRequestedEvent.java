package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record MoveChoiceRequestedEvent(FigureId actor, int amount, MovementType type) implements GameEvent {
}
