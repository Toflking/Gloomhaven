package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.*;

public final class AttackTargetInteraction implements ScenarioInteractionState {
    private final FigureId attackerId;
    private final Set<FigureId> eligibleTargets;
    private final int minimumTargets;
    private final int maximumTargets;
    private final DuplicatePolicy duplicates;
    private final TargetSelectionMode selectionMode;
    private final Map<FigureId, AttackPreview> previews;

    private final List<FigureId> selectedTargets = new ArrayList<>();
    private FigureId hoveredTarget;

    public AttackTargetInteraction(
        FigureId attackerId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, AttackPreview> previews
    ) {
        this.attackerId = Objects.requireNonNull(attackerId, "actorId");
        this.eligibleTargets = Set.copyOf(selection.eligibleTargets());
        this.minimumTargets = selection.minimumTargets();
        this.maximumTargets = selection.maximumTargets();
        this.duplicates = Objects.requireNonNull(selection.duplicates(), "duplicates");
        this.selectionMode = Objects.requireNonNull(selection.selectionMode(), "selectionMode");
        this.previews = Map.copyOf(previews);

        if (selectionMode == TargetSelectionMode.FIXED) {
            selectedTargets.addAll(this.eligibleTargets);
        }
    }

    public FigureId getAttackerId() {
        return attackerId;
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

    public AttackPreview previewFor(FigureId figureId) {
        if (!selectedTargets.contains(figureId)
            && !Objects.equals(hoveredTarget, figureId)) {
            return null;
        }
        return previews.get(figureId);
    }

    public void hoverTarget(FigureId figureId) {
        hoveredTarget = figureId != null
            && eligibleTargets.contains(figureId)
            ? figureId
            : null;
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
