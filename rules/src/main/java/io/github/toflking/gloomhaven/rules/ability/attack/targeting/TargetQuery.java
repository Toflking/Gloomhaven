package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import java.util.Objects;

public record TargetQuery(
    TargetRegion region,
    FigurePredicate figures,
    LineOfSightPolicy lineOfSight
) {

    public TargetQuery {
        Objects.requireNonNull(region, "region");
        Objects.requireNonNull(figures, "figures");
        Objects.requireNonNull(lineOfSight, "lineOfSight");
    }
}
