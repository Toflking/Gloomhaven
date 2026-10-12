package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public sealed interface FigureTargetSelection
    permits FigureTargetSelection.FigureTargets,
    FigureTargetSelection.AreaPlacement,
    FigureTargetSelection.Path,
    FigureTargetSelection.Origin,
    FigureTargetSelection.ChainTarget {

    record FigureTargets(
        Set<FigureId> eligibleTargets,
        int minimumTargets,
        int maximumTargets,
        DuplicatePolicy duplicates,
        TargetSelectionMode selectionMode
    ) implements FigureTargetSelection {

        public FigureTargets {
            eligibleTargets = Set.copyOf(eligibleTargets);
            Objects.requireNonNull(duplicates, "duplicates");
            Objects.requireNonNull(selectionMode, "selectionMode");
            requireCardinality(minimumTargets, maximumTargets);

            if (minimumTargets < 0) {
                throw new IllegalArgumentException(
                    "Minimum targets cannot be negative"
                );
            }
            if (maximumTargets < minimumTargets) {
                throw new IllegalArgumentException(
                    "Maximum targets cannot be less than minimum targets"
                );
            }
        }
    }

    record AreaPlacement(
        List<ResolvedAreaPlacement> placements,
        AreaPlacementControl control
    ) implements FigureTargetSelection {

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
    ) implements FigureTargetSelection {

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
    ) implements FigureTargetSelection {

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
    ) implements FigureTargetSelection {

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
        ActionOrigin origin,
        HexPosition position
    ) {

        public AttackOriginOption {
            Objects.requireNonNull(origin, "origin");
            Objects.requireNonNull(position, "position");
            if (origin instanceof ActionOrigin.Choose) {
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
