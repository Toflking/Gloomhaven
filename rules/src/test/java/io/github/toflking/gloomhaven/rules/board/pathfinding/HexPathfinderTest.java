package io.github.toflking.gloomhaven.rules.board.pathfinding;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexEdge;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HexPathfinderTest {
    private static final Figure MOVING_FIGURE = new Figure(
        new FigureId("moving-figure"),
        FigureType.CHARACTER,
        10
    );

    @Test
    void findsShortestPathAcrossBoard() {
        HexBoard board = boardWithRadius(2);
        HexPosition start = new HexPosition(-2, 0);
        HexPosition destination = new HexPosition(2, 0);

        List<HexPosition> path = HexPathfinder.shortestPath(
            board,
            start,
            destination,
            4,
            MovementType.NORMAL,
            MOVING_FIGURE,
            Map.of()
        );

        assertEquals(start, path.getFirst());
        assertEquals(destination, path.getLast());
        assertEquals(start.distanceTo(destination) + 1, path.size());

        for (int index = 1; index < path.size(); index++) {
            assertEquals(
                1,
                path.get(index - 1).distanceTo(path.get(index))
            );
        }
    }

    @Test
    void includesSingleHexWhenStartIsDestination() {
        HexBoard board = boardWithRadius(1);
        HexPosition position = new HexPosition(0, 0);

        assertEquals(
            List.of(position),
            findPath(
                board,
                position,
                position,
                0,
                MovementType.NORMAL
            )
        );
    }

    @Test
    void followsAvailableBoardHexesAroundGap() {
        HexPosition start = new HexPosition(0, 0);
        HexPosition firstStep = new HexPosition(1, -1);
        HexPosition secondStep = new HexPosition(2, -1);
        HexPosition destination = new HexPosition(2, 0);
        HexTile normalTile = new HexTile(TerrainType.NORMAL);
        HexBoard board = new HexBoard(Map.of(
            start, normalTile,
            firstStep, normalTile,
            secondStep, normalTile,
            destination, normalTile
        ), Set.of());

        assertEquals(
            List.of(start, firstStep, secondStep, destination),
            findPath(
                board,
                start,
                destination,
                3,
                MovementType.NORMAL
            )
        );
    }

    @Test
    void returnsEmptyPathWhenDestinationIsNotOnBoard() {
        HexBoard board = boardWithRadius(1);

        List<HexPosition> path = HexPathfinder.shortestPath(
            board,
            new HexPosition(0, 0),
            new HexPosition(3, 0),
            3,
            MovementType.NORMAL,
            MOVING_FIGURE,
            Map.of()
        );

        assertTrue(path.isEmpty());
    }

    @Test
    void returnsEmptyPathWhenDestinationExceedsMovementAmount() {
        HexBoard board = boardWithRadius(2);

        assertTrue(findPath(
            board,
            new HexPosition(0, 0),
            new HexPosition(2, 0),
            1,
            MovementType.NORMAL
        ).isEmpty());
    }

    @Test
    void difficultTerrainCostsTwoForNormalMovement() {
        HexPosition start = new HexPosition(0, 0);
        HexPosition difficult = new HexPosition(1, 0);
        HexPosition destination = new HexPosition(2, 0);
        HexBoard board = new HexBoard(Map.of(
            start, tile(TerrainType.NORMAL),
            difficult, tile(TerrainType.DIFFICULT),
            destination, tile(TerrainType.NORMAL)
        ), Set.of());

        assertTrue(findPath(
            board,
            start,
            destination,
            2,
            MovementType.NORMAL
        ).isEmpty());
        assertEquals(
            List.of(start, difficult, destination),
            findPath(
                board,
                start,
                destination,
                3,
                MovementType.NORMAL
            )
        );
    }

    @Test
    void normalMovementAvoidsTrapAndHazardousTerrainWhenPossible() {
        for (
            TerrainType dangerousTerrain
            : List.of(TerrainType.TRAP, TerrainType.HAZARDOUS)
        ) {
            HexPosition start = new HexPosition(0, 0);
            HexPosition dangerous = new HexPosition(1, 0);
            HexPosition safeFirst = new HexPosition(0, 1);
            HexPosition safeSecond = new HexPosition(1, 1);
            HexPosition destination = new HexPosition(2, 0);
            HexBoard board = new HexBoard(Map.of(
                start, tile(TerrainType.NORMAL),
                dangerous, tile(dangerousTerrain),
                safeFirst, tile(TerrainType.NORMAL),
                safeSecond, tile(TerrainType.NORMAL),
                destination, tile(TerrainType.NORMAL)
            ), Set.of());

            assertEquals(
                List.of(
                    start,
                    safeFirst,
                    safeSecond,
                    destination
                ),
                findPath(
                    board,
                    start,
                    destination,
                    3,
                    MovementType.NORMAL
                )
            );
            assertEquals(
                List.of(start, dangerous, destination),
                findPath(
                    board,
                    start,
                    destination,
                    2,
                    MovementType.NORMAL
                )
            );
        }
    }

    @Test
    void prefersHazardousTerrainOverTrapWhenDangerIsUnavoidable() {
        HexPosition start = new HexPosition(0, 0);
        HexPosition trap = new HexPosition(1, 0);
        HexPosition hazardous = new HexPosition(0, 1);
        HexPosition destination = new HexPosition(1, 1);
        HexBoard board = new HexBoard(Map.of(
            start, tile(TerrainType.NORMAL),
            trap, tile(TerrainType.TRAP),
            hazardous, tile(TerrainType.HAZARDOUS),
            destination, tile(TerrainType.NORMAL)
        ), Set.of());

        assertEquals(
            List.of(start, hazardous, destination),
            findPath(
                board,
                start,
                destination,
                2,
                MovementType.NORMAL
            )
        );
    }

    @Test
    void doorIsTraversedLikeNormalTerrain() {
        HexPosition start = new HexPosition(0, 0);
        HexPosition door = new HexPosition(1, 0);
        HexPosition destination = new HexPosition(2, 0);
        HexBoard board = new HexBoard(Map.of(
            start, tile(TerrainType.NORMAL),
            door, tile(TerrainType.DOOR),
            destination, tile(TerrainType.NORMAL)
        ), Set.of());

        assertEquals(
            List.of(start, door, destination),
            findPath(
                board,
                start,
                destination,
                2,
                MovementType.NORMAL
            )
        );
    }

    @Test
    void wallsBlockEveryMovementTypeInBothDirections() {
        HexPosition first = new HexPosition(0, 0);
        HexPosition second = new HexPosition(1, 0);
        HexBoard board = new HexBoard(
            Map.of(
                first, tile(TerrainType.NORMAL),
                second, tile(TerrainType.NORMAL)
            ),
            Set.of(new HexEdge(first, second))
        );

        assertTrue(board.hasWallBetween(first, second));
        assertTrue(board.hasWallBetween(second, first));

        for (MovementType movementType : MovementType.values()) {
            assertTrue(findPath(
                board,
                first,
                second,
                1,
                movementType
            ).isEmpty());
        }
    }

    @Test
    void jumpAndFlyCanCrossObstaclesButOnlyFlyCanEndOnOne() {
        HexPosition start = new HexPosition(0, 0);
        HexPosition obstacle = new HexPosition(1, 0);
        HexPosition destination = new HexPosition(2, 0);
        HexBoard crossingBoard = new HexBoard(Map.of(
            start, tile(TerrainType.NORMAL),
            obstacle, tile(TerrainType.OBSTACLE),
            destination, tile(TerrainType.NORMAL)
        ), Set.of());

        assertTrue(findPath(
            crossingBoard,
            start,
            destination,
            2,
            MovementType.NORMAL
        ).isEmpty());
        assertEquals(
            List.of(start, obstacle, destination),
            findPath(
                crossingBoard,
                start,
                destination,
                2,
                MovementType.JUMP
            )
        );
        assertEquals(
            List.of(start, obstacle, destination),
            findPath(
                crossingBoard,
                start,
                destination,
                2,
                MovementType.FLY
            )
        );

        HexBoard landingBoard = new HexBoard(Map.of(
            start, tile(TerrainType.NORMAL),
            obstacle, tile(TerrainType.OBSTACLE)
        ), Set.of());

        assertTrue(findPath(
            landingBoard,
            start,
            obstacle,
            1,
            MovementType.JUMP
        ).isEmpty());
        assertEquals(
            List.of(start, obstacle),
            findPath(
                landingBoard,
                start,
                obstacle,
                1,
                MovementType.FLY
            )
        );
    }

    @Test
    void jumpAndFlyCanCrossEnemiesButNoMovementCanEndOnThem() {
        HexPosition start = new HexPosition(0, 0);
        HexPosition enemyPosition = new HexPosition(1, 0);
        HexPosition destination = new HexPosition(2, 0);
        HexBoard board = new HexBoard(Map.of(
            start, tile(TerrainType.NORMAL),
            enemyPosition, tile(TerrainType.NORMAL),
            destination, tile(TerrainType.NORMAL)
        ), Set.of());
        Figure enemy = new Figure(
            new FigureId("enemy"),
            FigureType.MONSTER,
            10
        );
        Map<HexPosition, Figure> figures =
            Map.of(enemyPosition, enemy);

        assertTrue(findPath(
            board,
            start,
            destination,
            2,
            MovementType.NORMAL,
            figures
        ).isEmpty());
        assertEquals(
            List.of(start, enemyPosition, destination),
            findPath(
                board,
                start,
                destination,
                2,
                MovementType.JUMP,
                figures
            )
        );
        assertEquals(
            List.of(start, enemyPosition, destination),
            findPath(
                board,
                start,
                destination,
                2,
                MovementType.FLY,
                figures
            )
        );

        for (MovementType movementType : MovementType.values()) {
            assertTrue(findPath(
                board,
                start,
                enemyPosition,
                1,
                movementType,
                figures
            ).isEmpty());
        }
    }

    private List<HexPosition> findPath(
        HexBoard board,
        HexPosition start,
        HexPosition destination,
        int amount,
        MovementType movementType
    ) {
        return findPath(
            board,
            start,
            destination,
            amount,
            movementType,
            Map.of()
        );
    }

    private List<HexPosition> findPath(
        HexBoard board,
        HexPosition start,
        HexPosition destination,
        int amount,
        MovementType movementType,
        Map<HexPosition, Figure> figures
    ) {
        return HexPathfinder.shortestPath(
            board,
            start,
            destination,
            amount,
            movementType,
            MOVING_FIGURE,
            figures
        );
    }

    private static HexTile tile(TerrainType terrainType) {
        return new HexTile(terrainType);
    }

    private HexBoard boardWithRadius(int radius) {
        Map<HexPosition, HexTile> tiles = new HashMap<>();

        for (int q = -radius; q <= radius; q++) {
            int minimumR = Math.max(-radius, -q - radius);
            int maximumR = Math.min(radius, -q + radius);

            for (int r = minimumR; r <= maximumR; r++) {
                tiles.put(
                    new HexPosition(q, r),
                    new HexTile(TerrainType.NORMAL)
                );
            }
        }

        return new HexBoard(tiles, Set.of());
    }
}
