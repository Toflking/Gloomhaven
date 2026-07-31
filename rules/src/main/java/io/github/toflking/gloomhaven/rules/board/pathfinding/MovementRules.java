package io.github.toflking.gloomhaven.rules.board.pathfinding;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.figure.Figure;

public final class MovementRules {

    private MovementRules() {
    }

    public static int movementCost(
        HexTile tile,
        MovementType movementType
    ) {
        if (
            movementType == MovementType.NORMAL
                && tile.terrain() == TerrainType.DIFFICULT
        ) {
            return 2;
        }

        return 1;
    }

    public static int dangerousTerrainCost(
        HexTile tile,
        MovementType movementType
    ) {
        if (
            movementType == MovementType.NORMAL
                && (
                    tile.terrain() == TerrainType.TRAP
                        || tile.terrain() == TerrainType.HAZARDOUS
                )
        ) {
            return 1;
        }

        return 0;
    }

    public static int trapCost(
        HexTile tile,
        MovementType movementType
    ) {
        if (
            movementType == MovementType.NORMAL
                && tile.terrain() == TerrainType.TRAP
        ) {
            return 1;
        }

        return 0;
    }

    public static boolean canTraverse(
        HexTile tile,
        MovementType movementType,
        Figure movingFigure,
        Figure occupant
    ) {
        if (
            tile.terrain() == TerrainType.OBSTACLE
                && movementType == MovementType.NORMAL
        ) {
            return false;
        }

        if (
            occupant == null
                || occupant.id().equals(movingFigure.id())
                || occupant.type() == movingFigure.type()
        ) {
            return true;
        }

        return movementType == MovementType.JUMP
            || movementType == MovementType.FLY;
    }

    public static boolean canEndAt(
        HexTile tile,
        MovementType movementType,
        Figure movingFigure,
        Figure occupant
    ) {
        if (
            occupant != null
                && !occupant.id().equals(movingFigure.id())
        ) {
            return false;
        }

        if (tile.terrain() != TerrainType.OBSTACLE) {
            return true;
        }

        return movementType == MovementType.FLY;
    }
}
