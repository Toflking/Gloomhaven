package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.command.GameCommand;

import java.util.Objects;

public record UndoPerformedEvent(
    GameCommand undoneCommand
) implements GameEvent {

    public UndoPerformedEvent {
        Objects.requireNonNull(undoneCommand, "undoneCommand");
    }
}
