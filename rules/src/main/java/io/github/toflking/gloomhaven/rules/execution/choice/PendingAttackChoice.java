package io.github.toflking.gloomhaven.rules.execution.choice;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Objects;
import java.util.Set;

public record PendingAttackChoice(
    FigureId attackerId,
    Set<FigureId> eligibleTargets,
    int minimumTargets,
    int maximumTargets,
    DuplicatePolicy duplicates,
    TargetSelectionMode selectionMode
) implements PendingChoice {

    public PendingAttackChoice {
        Objects.requireNonNull(attackerId, "attackerId");
        eligibleTargets = Set.copyOf(eligibleTargets);
        Objects.requireNonNull(duplicates, "duplicates");
        Objects.requireNonNull(selectionMode, "selectionMode");

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
