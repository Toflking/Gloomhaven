package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.VisibilityPolicy;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaChoice;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingFigureTargetChoice;
import io.github.toflking.gloomhaven.rules.execution.ValidationResult;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class TargetValidator {
    private final ScenarioState state;

    public TargetValidator(ScenarioState state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    public ValidationResult validateTargets(List<FigureId> targets, PendingFigureTargetChoice choice) {
        Objects.requireNonNull(targets, "targets");
        Objects.requireNonNull(choice, "choice");

        if (targets.size() < choice.selection().minimumTargets()) {
            return ValidationResult.invalid(
                "Too few targets were selected"
            );
        }

        if (targets.size() > choice.selection().maximumTargets()) {
            return ValidationResult.invalid(
                "Too many targets were selected"
            );
        }

        Set<FigureId> distinctTargets = new HashSet<>(targets);
        if (choice.selection().selectionMode() == TargetSelectionMode.FIXED
            && (targets.size() != choice.selection().eligibleTargets().size()
            || !distinctTargets.equals(choice.selection().eligibleTargets()))) {
            return ValidationResult.invalid(
                "Fixed targets cannot be changed"
            );
        }

        if (choice.selection().duplicates() == DuplicatePolicy.DISTINCT_TARGETS
            && distinctTargets.size() != targets.size()) {
            return ValidationResult.invalid(
                "The same figure cannot be targeted more than once"
            );
        }

        for (FigureId target : targets) {
            if (!choice.selection().eligibleTargets().contains(target)) {
                return ValidationResult.invalid(
                    "Selected figure is not an eligible target"
                );
            }

            if (!state.hasFigure(target)) {
                return ValidationResult.invalid(
                    "Selected target is no longer in the scenario"
                );
            }
        }

        return ValidationResult.valid(targets);
    }

    public ValidationResult validateArea(
        FigureTargetSelection.AreaPlacementOption placement,
        PendingAreaChoice choice
    ) {
        Objects.requireNonNull(placement, "placement");
        Objects.requireNonNull(choice, "choice");

        ResolvedAreaPlacement resolvedPlacement = choice
            .selection()
            .placements()
            .stream()
            .filter(candidate -> candidate.option().equals(placement))
            .findFirst()
            .orElse(null);

        if (resolvedPlacement == null) {
            return ValidationResult.invalid(
                "Selected area is not an eligible placement"
            );
        }
        if (!resolvedPlacement.eligible()) {
            return ValidationResult.invalid(
                "Selected area is not an eligible placement"
            );
        }

        ActionExecution execution = state.execution();
        if (execution == null) {
            return ValidationResult.invalid(
                "There is no active action execution"
            );
        }
        if (!execution.actor().equals(choice.actorId())) {
            return ValidationResult.invalid(
                "The pending area choice belongs to another figure"
            );
        }

        TargetPlan targeting;
        ActionOrigin origin;
        VisibilityPolicy visibilityPolicy;
        switch (choice) {
            case PendingAreaChoice.Attack ignored -> {
                targeting = execution.attackExecution()
                    .attack()
                    .targeting();
                origin = execution.attackExecution()
                    .attack()
                    .origin();
                visibilityPolicy = execution.attackExecution()
                    .attack()
                    .visibilityPolicy();
            }
            case PendingAreaChoice.Heal ignored -> {
                targeting = execution.healExecution()
                    .heal()
                    .targeting();
                origin = execution.healExecution()
                    .heal()
                    .origin();
                visibilityPolicy = execution.healExecution()
                    .heal()
                    .visibilityPolicy();
            }
            case PendingAreaChoice.Damage ignored -> {
                targeting = execution.damageExecution()
                    .damage()
                    .targeting();
                origin = execution.damageExecution()
                    .damage()
                    .origin();
                visibilityPolicy = execution.damageExecution()
                    .damage()
                    .visibilityPolicy();
            }
            case PendingAreaChoice.Condition ignored -> {
                targeting = execution.conditionExecution()
                    .condition()
                    .targeting();
                origin = execution.conditionExecution()
                    .condition()
                    .origin();
                visibilityPolicy = execution.conditionExecution()
                    .condition()
                    .visibilityPolicy();
            }
        }

        if (!(targeting instanceof TargetPlan.Area areaPlan)) {
            return ValidationResult.invalid(
                "The active ability does not use area targeting"
            );
        }

        List<FigureId> targets = new TargetResolver(
            state,
            execution,
            origin,
            visibilityPolicy
        ).resolveAreaTargets(areaPlan, resolvedPlacement.affectedHexes());

        if (targets.isEmpty()) {
            return ValidationResult.invalid(
                "The selected area no longer contains an eligible target"
            );
        }

        return ValidationResult.valid(targets);
    }
}
