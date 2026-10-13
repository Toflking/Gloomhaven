package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.DamagePreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class DamageTargetInteraction
    implements ScenarioInteractionState {
    private final FigureId actorId;
    private final Set<FigureId> eligibleTargets;
    private final int minimumTargets;
    private final int maximumTargets;
    private final DuplicatePolicy duplicates;
    private final TargetSelectionMode selectionMode;
    private final Map<FigureId, DamagePreview> previews;
    private final List<FigureId> selectedTargets = new ArrayList<>();
    private FigureId hoveredTarget;

    public DamageTargetInteraction(
        FigureId actorId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, DamagePreview> previews
    ) {
        this.actorId = Objects.requireNonNull(actorId, "actorId");
        eligibleTargets = Set.copyOf(selection.eligibleTargets());
        minimumTargets = selection.minimumTargets();
        maximumTargets = selection.maximumTargets();
        duplicates = Objects.requireNonNull(selection.duplicates());
        selectionMode = Objects.requireNonNull(selection.selectionMode());
        this.previews = Map.copyOf(previews);
        if (selectionMode == TargetSelectionMode.FIXED) {
            selectedTargets.addAll(eligibleTargets);
        }
    }

    public FigureId actorId() { return actorId; }
    public Set<FigureId> eligibleTargets() { return eligibleTargets; }
    public int minimumTargets() { return minimumTargets; }
    public int maximumTargets() { return maximumTargets; }
    public DuplicatePolicy duplicates() { return duplicates; }
    public TargetSelectionMode selectionMode() { return selectionMode; }
    public List<FigureId> selectedTargets() { return selectedTargets; }

    public DamagePreview previewFor(FigureId figureId) {
        if (!selectedTargets.contains(figureId)
            && !Objects.equals(hoveredTarget, figureId)) {
            return null;
        }
        return previews.get(figureId);
    }

    public void hoverTarget(FigureId figureId) {
        hoveredTarget = figureId != null && eligibleTargets.contains(figureId)
            ? figureId
            : null;
    }

    public void replaceSelectedTargets(List<FigureId> targets) {
        selectedTargets.clear();
        selectedTargets.addAll(List.copyOf(targets));
    }
}
