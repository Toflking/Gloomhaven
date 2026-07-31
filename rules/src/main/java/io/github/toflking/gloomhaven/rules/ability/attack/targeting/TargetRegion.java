package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

import java.util.List;
import java.util.Objects;

public sealed interface TargetRegion
    permits TargetRegion.Anywhere,
    TargetRegion.AdjacentTo,
    TargetRegion.WithinRangeOf,
    TargetRegion.ExactRangeFrom,
    TargetRegion.SameRoomAs,
    TargetRegion.OccupyingPath,
    TargetRegion.AdjacentToHexSet,
    TargetRegion.Intersection,
    TargetRegion.Union {

    record Anywhere() implements TargetRegion {
    }

    record AdjacentTo(TargetAnchor anchor)
        implements TargetRegion {

        public AdjacentTo {
            Objects.requireNonNull(anchor, "anchor");
        }
    }

    record WithinRangeOf(
        TargetAnchor anchor,
        IntExpression range
    ) implements TargetRegion {

        public WithinRangeOf {
            Objects.requireNonNull(anchor, "anchor");
            Objects.requireNonNull(range, "range");
        }
    }

    record ExactRangeFrom(
        TargetAnchor anchor,
        IntExpression range
    ) implements TargetRegion {

        public ExactRangeFrom {
            Objects.requireNonNull(anchor, "anchor");
            Objects.requireNonNull(range, "range");
        }
    }

    record SameRoomAs(TargetAnchor anchor)
        implements TargetRegion {

        public SameRoomAs {
            Objects.requireNonNull(anchor, "anchor");
        }
    }

    record OccupyingPath(ExecutionVariableId path)
        implements TargetRegion {

        public OccupyingPath {
            Objects.requireNonNull(path, "path");
        }
    }

    record AdjacentToHexSet(ExecutionVariableId hexes)
        implements TargetRegion {

        public AdjacentToHexSet {
            Objects.requireNonNull(hexes, "hexes");
        }
    }

    record Intersection(List<TargetRegion> regions)
        implements TargetRegion {

        public Intersection {
            regions = requireRegions(regions);
        }
    }

    record Union(List<TargetRegion> regions)
        implements TargetRegion {

        public Union {
            regions = requireRegions(regions);
        }
    }

    private static List<TargetRegion> requireRegions(
        List<TargetRegion> regions
    ) {
        List<TargetRegion> copy = List.copyOf(regions);
        if (copy.isEmpty()) {
            throw new IllegalArgumentException(
                "A composite target region cannot be empty"
            );
        }
        return copy;
    }
}
