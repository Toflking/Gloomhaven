package io.github.toflking.gloomhaven.rules.board;

import java.util.Objects;

public record HexEdge(HexPosition first, HexPosition second) {

    public HexEdge {
        Objects.requireNonNull(first, "First hex cannot be null");
        Objects.requireNonNull(second, "Second hex cannot be null");

        if (first.distanceTo(second) != 1) {
            throw new IllegalArgumentException(
                "A hex edge must connect adjacent hexes"
            );
        }

        if (compare(first, second) > 0) {
            HexPosition originalFirst = first;
            first = second;
            second = originalFirst;
        }
    }

    private static int compare(
        HexPosition first,
        HexPosition second
    ) {
        int qComparison = Integer.compare(first.q(), second.q());

        if (qComparison != 0) {
            return qComparison;
        }

        return Integer.compare(first.r(), second.r());
    }
}
