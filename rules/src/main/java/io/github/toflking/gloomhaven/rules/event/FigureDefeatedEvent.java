package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

public record FigureDefeatedEvent(FigureId figureId, HexPosition position) implements GameEvent {
}
