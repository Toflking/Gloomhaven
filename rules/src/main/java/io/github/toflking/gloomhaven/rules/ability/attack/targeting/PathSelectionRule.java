package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

import java.util.Objects;

public record PathSelectionRule(
    IntExpression maximumLength,
    boolean contiguous,
    boolean includePrimaryTarget
) {

    public PathSelectionRule {
        Objects.requireNonNull(maximumLength, "maximumLength");
    }
}
