package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;

public record ChooseAttackTargetsCommand(List<FigureId> targets) implements GameCommand {

    public ChooseAttackTargetsCommand {
        targets = List.copyOf(targets);
    }
}
