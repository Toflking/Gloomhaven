package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record TurnOrderEntry(
    FigureId figureId,
    int initiative
) {}
