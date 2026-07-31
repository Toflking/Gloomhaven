package io.github.toflking.gloomhaven.rules.board;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class HexBoard {

    private final Map<HexPosition, HexTile> tiles;
    private final Set<HexEdge> walls;

    public HexBoard(Map<HexPosition, HexTile> tiles, Set<HexEdge> walls) {
        this.tiles = Map.copyOf(
            Objects.requireNonNull(tiles, "Tiles cannot be null")
        );
        this.walls = Set.copyOf(
            Objects.requireNonNull(walls, "Walls cannot be null")
        );
    }

    public boolean contains(HexPosition position) {
        return tiles.containsKey(position);
    }

    public HexTile tileAt(HexPosition position) {
        HexTile tile = tiles.get(position);

        if (tile == null) {
            throw new IllegalArgumentException(
                "No tile at position " + position
            );
        }

        return tile;
    }

    public boolean hasWallBetween(
        HexPosition current,
        HexPosition neighbor
    ) {
        return walls.contains(new HexEdge(current, neighbor));
    }

    public Set<HexEdge> walls() {
        return walls;
    }
}
