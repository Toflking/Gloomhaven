package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAttackChoice;
import io.github.toflking.gloomhaven.rules.execution.ValidationResult;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class AttackTargetValidator {
    private final ScenarioState state;

    public AttackTargetValidator(ScenarioState state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    public ValidationResult validate(List<FigureId> targets, PendingAttackChoice choice) {
        Objects.requireNonNull(targets, "targets");
        Objects.requireNonNull(choice, "choice");

        if (targets.size() < choice.minimumTargets()) {
            return ValidationResult.invalid(
                "Too few attack targets were selected"
            );
        }

        if (targets.size() > choice.maximumTargets()) {
            return ValidationResult.invalid(
                "Too many attack targets were selected"
            );
        }

        Set<FigureId> distinctTargets = new HashSet<>(targets);
        if (choice.selectionMode() == TargetSelectionMode.FIXED
            && (targets.size() != choice.eligibleTargets().size()
            || !distinctTargets.equals(choice.eligibleTargets()))) {
            return ValidationResult.invalid(
                "Fixed attack targets cannot be changed"
            );
        }

        if (choice.duplicates() == DuplicatePolicy.DISTINCT_TARGETS
            && distinctTargets.size() != targets.size()) {
            return ValidationResult.invalid(
                "The same figure cannot be targeted more than once"
            );
        }

        for (FigureId target : targets) {
            if (!choice.eligibleTargets().contains(target)) {
                return ValidationResult.invalid(
                    "Selected figure is not an eligible attack target"
                );
            }

            if (!state.hasFigure(target)) {
                return ValidationResult.invalid(
                    "Selected attack target is no longer in the scenario"
                );
            }
        }

        return ValidationResult.valid();
    }
}
