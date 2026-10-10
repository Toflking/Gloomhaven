package io.github.toflking.gloomhaven.rules.figure.identities;

import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

public record CharacterSummonIdentity(
    SummonDefinitionId definitionId,
    FigureId summonerId,
    AbilitySource source
) implements FigureIdentity {
}
