package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

import java.util.Objects;

public sealed interface AreaPlacementRule
    permits AreaPlacementRule.AroundOrigin,
    AreaPlacementRule.WithinRange {

    boolean allowRotation();

    boolean allowReflection();

    record AroundOrigin(
        boolean allowRotation,
        boolean allowReflection
    ) implements AreaPlacementRule {
    }

    record WithinRange(
        IntExpression range,
        LineOfSightPolicy lineOfSight,
        boolean allowRotation,
        boolean allowReflection
    ) implements AreaPlacementRule {

        public WithinRange {
            Objects.requireNonNull(range, "range");
            Objects.requireNonNull(lineOfSight, "lineOfSight");
        }
    }
}
