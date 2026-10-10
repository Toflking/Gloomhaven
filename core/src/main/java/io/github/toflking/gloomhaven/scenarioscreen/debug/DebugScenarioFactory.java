package io.github.toflking.gloomhaven.scenarioscreen.debug;

import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexEdge;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
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
import io.github.toflking.gloomhaven.rules.figure.identities.FigureIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.MonsterIdentity;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DebugScenarioFactory {
    public static final int BOARD_RADIUS = 3;
    private static final FactionId CHARACTERS =
        new FactionId("characters");
    private static final FactionId MONSTERS =
        new FactionId("monsters");
    private static final CharacterIdentity DEBUG_CHARACTER =
        new CharacterIdentity(new CharacterTypeId("debug-character"));
    private static final MonsterIdentity DEBUG_MONSTER =
        new MonsterIdentity(
            new MonsterTypeId("debug-monster"),
            MonsterRank.NORMAL
        );

    private DebugScenarioFactory() {
    }

    public static ScenarioState create() {
        ScenarioState state = new ScenarioState(createBoard());
        FigureId playerId = new FigureId("player");

        addFigure(
            state,
            playerId,
            DEBUG_CHARACTER,
            CHARACTERS,
            10,
            0,
            0
        );
        addMonster(state, "monster-1", 12, 1, 0);
        addMonster(state, "monster-2", 12, 0, 1);
        addMonster(state, "monster-3", 12, 2, -1);
        addMonster(state, "monster-4", 12, -2, 1);
        addMonster(state, "monster-5", 12, 3, -1);
        addMonster(state, "monster-6", 12, 0, -3);
        addFigure(
            state,
            new FigureId("ally-1"),
            DEBUG_CHARACTER,
            CHARACTERS,
            8,
            -1,
            0
        );

        state.setActiveFigure(playerId);
        return state;
    }

    public static HexBoard createBoard() {
        Map<HexPosition, HexTile> tiles = createTiles();
        return new HexBoard(tiles, createOuterWalls(tiles));
    }

    private static void addMonster(
        ScenarioState state,
        String id,
        int maximumHealth,
        int q,
        int r
    ) {
        addFigure(
            state,
            new FigureId(id),
            DEBUG_MONSTER,
            MONSTERS,
            maximumHealth,
            q,
            r
        );
    }

    private static void addFigure(
        ScenarioState state,
        FigureId id,
        FigureIdentity identity,
        FactionId factionId,
        int maximumHealth,
        int q,
        int r
    ) {
        state.addFigure(
            new Figure(
                id,
                new FigureDefinitionId(id.value()),
                identity,
                FigureOrigin.SCENARIO,
                factionId,
                new HealthState(maximumHealth),
                new ConditionState(List.of())
            ),
            new HexPosition(q, r)
        );
    }

    private static Map<HexPosition, HexTile> createTiles() {
        Map<HexPosition, HexTile> tiles = new HashMap<>();

        for (int q = -BOARD_RADIUS; q <= BOARD_RADIUS; q++) {
            int minimumR = Math.max(-BOARD_RADIUS, -q - BOARD_RADIUS);
            int maximumR = Math.min(BOARD_RADIUS, -q + BOARD_RADIUS);

            for (int r = minimumR; r <= maximumR; r++) {
                tiles.put(
                    new HexPosition(q, r),
                    new HexTile(TerrainType.NORMAL)
                );
            }
        }

        return tiles;
    }

    private static Set<HexEdge> createOuterWalls(
        Map<HexPosition, HexTile> tiles
    ) {
        Set<HexEdge> walls = new HashSet<>();

        for (HexPosition position : tiles.keySet()) {
            for (HexPosition neighbor : position.neighbors()) {
                if (!tiles.containsKey(neighbor)) {
                    walls.add(new HexEdge(position, neighbor));
                }
            }
        }

        return walls;
    }
}
