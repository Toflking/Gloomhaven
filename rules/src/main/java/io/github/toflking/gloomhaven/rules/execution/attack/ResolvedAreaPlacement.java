package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.board.HexPosition;

import java.util.Objects;
import java.util.Set;

public record ResolvedAreaPlacement(
    FigureTargetSelection.AreaPlacementOption option,
    Set<HexPosition> affectedHexes,
    Set<HexPosition> targetableHexes,
    boolean eligible
) {
    public ResolvedAreaPlacement {
        Objects.requireNonNull(option, "option");
        affectedHexes = Set.copyOf(affectedHexes);
        targetableHexes = Set.copyOf(targetableHexes);
        if (!affectedHexes.containsAll(targetableHexes)) {
            throw new IllegalArgumentException(
                "Targetable area hexes must be affected by the placement"
            );
        }
    }

    public ResolvedAreaPlacement(
        FigureTargetSelection.AreaPlacementOption option,
        Set<HexPosition> affectedHexes
    ) {
        this(option, affectedHexes, affectedHexes, true);
    }

    public ResolvedAreaPlacement(
        FigureTargetSelection.AreaPlacementOption option,
        Set<HexPosition> affectedHexes,
        Set<HexPosition> targetableHexes
    ) {
        this(option, affectedHexes, targetableHexes, true);
    }
}
