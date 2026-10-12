package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;

public record ChooseFigureTargetsCommand(List<FigureId> targets) implements GameCommand {

    public ChooseFigureTargetsCommand {
        targets = List.copyOf(targets);
    }
}
