package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record EndTurnCommand(FigureId figureId) implements GameCommand {
    public EndTurnCommand {
        if (figureId == null) {
            throw new IllegalArgumentException(
                "Figure ID cannot be null"
            );
        }
    }
}
