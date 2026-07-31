package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record ActionFinishedEvent(FigureId figureId, AbilitySource source) implements GameEvent {
}
