package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record FigureDamagedEvent(FigureId figureId, int amount, int remainingHealth, FigureId attckerId) implements GameEvent {
}
