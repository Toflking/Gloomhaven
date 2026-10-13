package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record FigureHealedEvent(FigureId figureId, int amount, int remainingHealth, FigureId healerId) implements GameEvent {}
