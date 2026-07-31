package io.github.toflking.gloomhaven.scenarioscreen.debug;

import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexEdge;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class DebugScenarioFactory {
    public static final int BOARD_RADIUS = 3;

    private DebugScenarioFactory() {
    }

    public static ScenarioState create() {
        ScenarioState state = new ScenarioState(createBoard());
        FigureId playerId = new FigureId("player");

        state.addFigure(
            new Figure(playerId, FigureType.CHARACTER, 10),
            new HexPosition(0, 0)
        );
        addFigure(state, "monster-1", FigureType.MONSTER, 12, 1, 0);
        addFigure(state, "monster-2", FigureType.MONSTER, 12, 0, 1);
        addFigure(state, "monster-3", FigureType.MONSTER, 12, 2, -1);
        addFigure(state, "monster-4", FigureType.MONSTER, 12, -2, 1);
        addFigure(state, "monster-5", FigureType.MONSTER, 12, 3, -1);
        addFigure(state, "monster-6", FigureType.MONSTER, 12, 0, -3);
        addFigure(state, "ally-1", FigureType.CHARACTER, 8, -1, 0);

        state.setActiveFigure(playerId);
        return state;
    }

    public static HexBoard createBoard() {
        Map<HexPosition, HexTile> tiles = createTiles();
        return new HexBoard(tiles, createOuterWalls(tiles));
    }

    private static void addFigure(
        ScenarioState state,
        String id,
        FigureType type,
        int maximumHealth,
        int q,
        int r
    ) {
        state.addFigure(
            new Figure(new FigureId(id), type, maximumHealth),
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
