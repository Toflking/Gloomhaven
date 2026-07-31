package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.event.GameEvent;

import java.util.List;

public record CommandResult(
    boolean accepted,
    List<GameEvent> events,
    String rejectionReason
) {

    public static CommandResult accepted(
        List<GameEvent> events
    ) {
        return new CommandResult(
            true,
            events,
            null
        );
    }

    public static CommandResult rejected(
        String reason
    ) {
        return new CommandResult(
            false,
            List.of(),
            reason
        );
    }
}
