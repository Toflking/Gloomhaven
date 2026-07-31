package io.github.toflking.gloomhaven.rules.board;

import java.util.List;

public record HexPosition(int q, int r) {
    public int distanceTo(HexPosition other) {
        int dq = q - other.q();
        int dr = r - other.r();

        return (
            Math.abs(dq)
                + Math.abs(dr)
                + Math.abs(dq + dr)
        ) / 2;
    }

    public List<HexPosition> neighbors() {
        return DIRECTIONS.stream()
            .map(direction -> new HexPosition(
                q + direction.q(),
                r + direction.r()
            ))
            .toList();
    }

    private static final List<HexPosition> DIRECTIONS = List.of(
        new HexPosition(1, 0),
        new HexPosition(1, -1),
        new HexPosition(0, -1),
        new HexPosition(-1, 0),
        new HexPosition(-1, 1),
        new HexPosition(0, 1)
    );
}

