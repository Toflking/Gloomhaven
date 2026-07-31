package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.board.HexPosition;

import java.util.List;

public record ChooseMovePathCommand(List<HexPosition> path) implements GameCommand {

    public ChooseMovePathCommand {
        path = List.copyOf(path);
    }
}
