package io.github.toflking.gloomhaven.rules.board;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LineOfSightResolverTest {
    private static final HexPosition LEFT = new HexPosition(-1, 0);
    private static final HexPosition MIDDLE = new HexPosition(0, 0);
    private static final HexPosition RIGHT = new HexPosition(1, 0);

    @Test
    void findsLineOfSightWhenNoWallIsTouched() {
        assertTrue(LineOfSightResolver.hasLineOfSight(
            board(Set.of()),
            LEFT,
            RIGHT
        ));
    }

    @Test
    void anIsolatedWallCanBeSeenAroundUsingAnotherCornerPair() {
        assertTrue(LineOfSightResolver.hasLineOfSight(
            board(Set.of(new HexEdge(LEFT, MIDDLE))),
            LEFT,
            MIDDLE
        ));
    }

    @Test
    void touchingOnlyACornerDoesNotEscapeAWalledMiddleHex() {
        Set<HexEdge> walls = new HashSet<>();
        for (HexPosition neighbor : MIDDLE.neighbors()) {
            walls.add(new HexEdge(MIDDLE, neighbor));
        }

        assertFalse(LineOfSightResolver.hasLineOfSight(
            board(walls),
            LEFT,
            RIGHT
        ));
    }

    private static HexBoard board(Set<HexEdge> walls) {
        return new HexBoard(
            Map.of(
                LEFT,
                new HexTile(TerrainType.NORMAL),
                MIDDLE,
                new HexTile(TerrainType.NORMAL),
                RIGHT,
                new HexTile(TerrainType.NORMAL)
            ),
            walls
        );
    }
}
