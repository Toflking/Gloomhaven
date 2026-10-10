package io.github.toflking.gloomhaven.rules.figure.identities;

import io.github.toflking.gloomhaven.rules.figure.CharacterTypeId;

public record CharacterIdentity(
    CharacterTypeId id
) implements FigureIdentity {
}
