package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

public record FigureMovedEvent(FigureId figureId, HexPosition start, HexPosition destination) implements GameEvent {
}
