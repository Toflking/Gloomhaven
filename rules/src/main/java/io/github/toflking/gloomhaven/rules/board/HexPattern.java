package io.github.toflking.gloomhaven.rules.board;

import java.util.List;
import java.util.Objects;

public record HexPattern(List<HexPosition> tiles) {

    public HexPattern {
        tiles = List.copyOf(
            Objects.requireNonNull(tiles, "tiles")
        );
        if (tiles.isEmpty()) {
            throw new IllegalArgumentException(
                "A hex pattern must contain at least one hex"
            );
        }
    }
}
