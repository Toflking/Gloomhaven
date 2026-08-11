package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class AttackTargetInteraction implements ScenarioInteractionState {
    private final FigureId attacker;
    private final Set<FigureId> eligibleTargets;
    private final int minimumTargets;
    private final int maximumTargets;
    private final DuplicatePolicy duplicates;
    private final TargetSelectionMode selectionMode;

    private final List<FigureId> selectedTargets = new ArrayList<>();

    public AttackTargetInteraction(
        FigureId attacker,
        Set<FigureId> eligibleTargets,
        int minimumTargets,
        int maximumTargets,
        DuplicatePolicy duplicates,
        TargetSelectionMode selectionMode
    ) {
        this.attacker = Objects.requireNonNull(attacker, "attacker");
        this.eligibleTargets = Set.copyOf(eligibleTargets);
        this.minimumTargets = minimumTargets;
        this.maximumTargets = maximumTargets;
        this.duplicates = Objects.requireNonNull(duplicates, "duplicates");
        this.selectionMode = Objects.requireNonNull(
            selectionMode,
            "selectionMode"
        );

        if (selectionMode == TargetSelectionMode.FIXED) {
            selectedTargets.addAll(this.eligibleTargets);
        }
    }

    public FigureId getAttacker() {
        return attacker;
    }

    public DuplicatePolicy getDuplicates() {
        return duplicates;
    }

    public Set<FigureId> getEligibleTargets() {
        return eligibleTargets;
    }

    public int getMaximumTargets() {
        return maximumTargets;
    }

    public int getMinimumTargets() {
        return minimumTargets;
    }

    public List<FigureId> getSelectedTargets() {
        return selectedTargets;
    }

    public boolean areAllEligibleTargetsSelected() {
        return eligibleTargets.size() == selectedTargets.size()
            && eligibleTargets.containsAll(selectedTargets);
    }

    public void replaceSelectedTargets(List<FigureId> targets) {
        selectedTargets.clear();
        selectedTargets.addAll(List.copyOf(targets));
    }

    public TargetSelectionMode getSelectionMode() {
        return selectionMode;
    }
}
