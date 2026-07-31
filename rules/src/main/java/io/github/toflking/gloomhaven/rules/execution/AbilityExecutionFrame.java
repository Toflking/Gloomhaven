package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Objects;

public record AbilityExecutionFrame(
    FigureId ownerId,
    FigureId actorId,
    FigureId controllerId,
    AbilitySource source
) {

    public AbilityExecutionFrame {
        Objects.requireNonNull(ownerId, "ownerId");
        Objects.requireNonNull(actorId, "actorId");
        Objects.requireNonNull(controllerId, "controllerId");
        Objects.requireNonNull(source, "source");
    }

    public static AbilityExecutionFrame selfControlled(
        FigureId actorId,
        AbilitySource source
    ) {
        return new AbilityExecutionFrame(
            actorId,
            actorId,
            actorId,
            source
        );
    }
}
