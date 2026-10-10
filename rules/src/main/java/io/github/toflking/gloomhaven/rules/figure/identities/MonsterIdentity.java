package io.github.toflking.gloomhaven.rules.figure.identities;

import io.github.toflking.gloomhaven.rules.figure.MonsterRank;
import io.github.toflking.gloomhaven.rules.figure.MonsterTypeId;

public record MonsterIdentity(
    MonsterTypeId monsterTypeId,
    MonsterRank rank
) implements FigureIdentity {
}
