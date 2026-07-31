package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record TurnEndedEvent(FigureId figureId) implements GameEvent {
}
