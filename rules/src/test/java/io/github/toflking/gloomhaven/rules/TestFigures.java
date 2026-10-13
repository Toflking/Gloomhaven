package io.github.toflking.gloomhaven.rules;

import io.github.toflking.gloomhaven.rules.figure.CharacterTypeId;
import io.github.toflking.gloomhaven.rules.figure.ConditionState;
import io.github.toflking.gloomhaven.rules.figure.FactionId;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureDefinitionId;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureOrigin;
import io.github.toflking.gloomhaven.rules.figure.HealthState;
import io.github.toflking.gloomhaven.rules.figure.MonsterRank;
import io.github.toflking.gloomhaven.rules.figure.MonsterTypeId;
import io.github.toflking.gloomhaven.rules.figure.identities.CharacterIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.MonsterIdentity;

import java.util.Map;

public final class TestFigures {
    private static final FactionId CHARACTERS =
        new FactionId("characters");
    private static final FactionId MONSTERS =
        new FactionId("monsters");

    private TestFigures() {
    }

    public static Figure character(FigureId id, int maximumHealth) {
        return new Figure(
            id,
            new FigureDefinitionId(id.value()),
            new CharacterIdentity(new CharacterTypeId("test-character")),
            FigureOrigin.SCENARIO,
            CHARACTERS,
            new HealthState(maximumHealth),
            new ConditionState(Map.of())
        );
    }

    public static Figure monster(FigureId id, int maximumHealth) {
        return new Figure(
            id,
            new FigureDefinitionId(id.value()),
            new MonsterIdentity(
                new MonsterTypeId("test-monster"),
                MonsterRank.NORMAL
            ),
            FigureOrigin.SCENARIO,
            MONSTERS,
            new HealthState(maximumHealth),
            new ConditionState(Map.of())
        );
    }
}
