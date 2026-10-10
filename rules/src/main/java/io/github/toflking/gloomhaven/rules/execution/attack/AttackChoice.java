package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public sealed interface AttackChoice
    permits AttackChoice.FigureTargets,
    AttackChoice.AreaPlacement,
    AttackChoice.Path,
    AttackChoice.Origin,
    AttackChoice.ChainTarget {

    record FigureTargets(
        Set<FigureId> eligibleTargets,
        int minimumTargets,
        int maximumTargets,
        DuplicatePolicy duplicates,
        TargetSelectionMode selectionMode
    ) implements AttackChoice {

        public FigureTargets {
            eligibleTargets = Set.copyOf(eligibleTargets);
            Objects.requireNonNull(duplicates, "duplicates");
            Objects.requireNonNull(selectionMode, "selectionMode");
            requireCardinality(minimumTargets, maximumTargets);
        }
    }

    record AreaPlacement(
        List<ResolvedAreaPlacement> placements,
        AreaPlacementControl control
    ) implements AttackChoice {

        public AreaPlacement {
            placements = List.copyOf(placements);
            Objects.requireNonNull(control, "control");
        }
    }

    record Path(
        HexPosition start,
        HexPosition destination,
        int maximumLength,
        Set<HexPosition> eligibleHexes
    ) implements AttackChoice {

        public Path {
            Objects.requireNonNull(start, "start");
            Objects.requireNonNull(destination, "destination");
            eligibleHexes = Set.copyOf(eligibleHexes);
            if (maximumLength < 0) {
                throw new IllegalArgumentException(
                    "Maximum path length cannot be negative"
                );
            }
        }
    }

    record Origin(
        List<AttackOriginOption> options
    ) implements AttackChoice {

        public Origin {
            options = List.copyOf(options);
            if (options.size() < 2) {
                throw new IllegalArgumentException(
                    "An attack origin choice requires at least two options"
                );
            }
        }
    }

    record ChainTarget(
        int stepIndex,
        Set<FigureId> eligibleTargets,
        boolean optional
    ) implements AttackChoice {

        public ChainTarget {
            eligibleTargets = Set.copyOf(eligibleTargets);
            if (stepIndex < 0) {
                throw new IllegalArgumentException(
                    "Chain step index cannot be negative"
                );
            }
        }
    }

    record AreaPlacementOption(
        HexPosition anchor,
        int clockwiseRotations,
        boolean reflected
    ) {

        public AreaPlacementOption {
            Objects.requireNonNull(anchor, "anchor");
            if (clockwiseRotations < 0 || clockwiseRotations > 5) {
                throw new IllegalArgumentException(
                    "Hex area rotation must be between zero and five"
                );
            }
        }
    }

    record AttackOriginOption(
        AttackOrigin origin,
        HexPosition position
    ) {

        public AttackOriginOption {
            Objects.requireNonNull(origin, "origin");
            Objects.requireNonNull(position, "position");
            if (origin instanceof AttackOrigin.Choose) {
                throw new IllegalArgumentException(
                    "An origin option must already be concrete"
                );
            }
        }
    }

    private static void requireCardinality(
        int minimum,
        int maximum
    ) {
        if (minimum < 0) {
            throw new IllegalArgumentException(
                "Minimum targets cannot be negative"
            );
        }
        if (maximum < minimum) {
            throw new IllegalArgumentException(
                "Maximum targets cannot be less than minimum targets"
            );
        }
    }
}
