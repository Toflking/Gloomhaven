package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Objects;

public record AttackInstance(
    FigureId targetId,
    int sequenceIndex
) {

    public AttackInstance {
        Objects.requireNonNull(targetId, "targetId");
        if (sequenceIndex < 0) {
            throw new IllegalArgumentException(
                "Attack sequence index cannot be negative"
            );
        }
    }
}
