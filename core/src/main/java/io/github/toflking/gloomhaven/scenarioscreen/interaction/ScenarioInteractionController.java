package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.pathfinding.HexPathfinder;
import io.github.toflking.gloomhaven.rules.command.ChooseFigureTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseAttackAreaCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseMovePathCommand;
import io.github.toflking.gloomhaven.rules.event.UndoPerformedEvent;
import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.DamagePreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.ConditionPreview;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingFigureTargetChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ScenarioInteractionController {
    private final ScenarioState scenarioState;
    private SelectionOption selectionOption = SelectionOption.MANUAL;

    private HexPosition selectedHex;
    private ScenarioInteractionState interactionState =
        new IdleInteraction();

    public ScenarioInteractionController(ScenarioState scenarioState) {
        this.scenarioState = Objects.requireNonNull(
            scenarioState,
            "scenarioState"
        );
    }

    public void handleBoardLeftClick(HexPosition clickedHex) {
        selectedHex = null;

        switch (interactionState) {
            case IdleInteraction ignored -> selectedHex = clickedHex;
            case MovePathInteraction movement ->
                updateMovementPreview(clickedHex, movement);
            case AttackTargetInteraction targeting ->
                addToTargetSelection(clickedHex, targeting);
            case AttackAreaPlacementInteraction area ->
                updateAreaPlacement(clickedHex, area);
            case HealTargetInteraction targeting ->
                addToTargetSelection(clickedHex, targeting);
            case HealAreaPlacementInteraction area ->
                updateAreaPlacement(clickedHex, area);
            case DamageTargetInteraction targeting ->
                addToTargetSelection(clickedHex, targeting);
            case DamageAreaPlacementInteraction area ->
                updateAreaPlacement(clickedHex, area);
            case ConditionTargetInteraction targeting ->
                addToTargetSelection(clickedHex, targeting);
            case ConditionAreaPlacementInteraction area ->
                updateAreaPlacement(clickedHex, area);
        }
    }

    public void handleBoardRightClick(HexPosition clickedHex) {
        switch (interactionState) {
            case IdleInteraction ignored -> {}
            case MovePathInteraction movement -> {}
            case AttackTargetInteraction targeting ->
                removeFromTargetSelection(clickedHex, targeting);
            case AttackAreaPlacementInteraction area ->
                area.unlockAt(clickedHex);
            case HealTargetInteraction targeting ->
                removeFromTargetSelection(clickedHex, targeting);
            case HealAreaPlacementInteraction area ->
                area.unlockAt(clickedHex);
            case DamageTargetInteraction targeting ->
                removeFromTargetSelection(clickedHex, targeting);
            case DamageAreaPlacementInteraction area ->
                area.unlockAt(clickedHex);
            case ConditionTargetInteraction targeting ->
                removeFromTargetSelection(clickedHex, targeting);
            case ConditionAreaPlacementInteraction area ->
                area.unlockAt(clickedHex);
        }
    }

    public void beginMoveChoice(
        int amount,
        FigureId actor,
        MovementType type
    ) {
        interactionState = new MovePathInteraction(amount, actor, type);
    }

    public void beginAttackTargetChoice(
        FigureId attackerId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, AttackPreview> previews
    ) {
        interactionState = new AttackTargetInteraction(
            attackerId,
            selection,
            previews
        );
        switch (selection.selectionMode()) {
            case FIXED ->  selectionOption = SelectionOption.AUTOMATIC;
            case MANUAL -> selectionOption = SelectionOption.MANUAL;
        }
    }

    public void beginAttackAreaPlacementChoice(
        FigureId attacker,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, AttackPreview> previews
    ) {
        interactionState = new AttackAreaPlacementInteraction(
            attacker,
            selection,
            previews
        );
    }

    public void beginHealTargetChoice(
        FigureId attackerId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, HealPreview> previews
    ) {
        interactionState = new HealTargetInteraction(
            attackerId,
            selection,
            previews
        );
        switch (selection.selectionMode()) {
            case FIXED ->  selectionOption = SelectionOption.AUTOMATIC;
            case MANUAL -> selectionOption = SelectionOption.MANUAL;
        }
    }

    public void beginHealAreaPlacementChoice(
        FigureId attacker,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, HealPreview> previews
    ) {
        interactionState = new HealAreaPlacementInteraction(
            attacker,
            selection,
            previews
        );
    }

    public void beginDamageTargetChoice(
        FigureId actorId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, DamagePreview> previews
    ) {
        interactionState = new DamageTargetInteraction(
            actorId, selection, previews
        );
        selectionOption = switch (selection.selectionMode()) {
            case FIXED -> SelectionOption.AUTOMATIC;
            case MANUAL -> SelectionOption.MANUAL;
        };
    }

    public void beginDamageAreaPlacementChoice(
        FigureId actorId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, DamagePreview> previews
    ) {
        interactionState = new DamageAreaPlacementInteraction(
            actorId, selection, previews
        );
    }

    public void beginConditionTargetChoice(
        FigureId actorId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, ConditionPreview> previews
    ) {
        interactionState = new ConditionTargetInteraction(
            actorId, selection, previews
        );
        selectionOption = switch (selection.selectionMode()) {
            case FIXED -> SelectionOption.AUTOMATIC;
            case MANUAL -> SelectionOption.MANUAL;
        };
    }

    public void beginConditionAreaPlacementChoice(
        FigureId actorId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, ConditionPreview> previews
    ) {
        interactionState = new ConditionAreaPlacementInteraction(
            actorId, selection, previews
        );
    }

    public void handleBoardHover(HexPosition hoveredHex) {
        FigureId hoveredFigureId = hoveredHex == null
            ? null
            : scenarioState.figureAt(hoveredHex)
                .map(Figure::id)
                .orElse(null);

        switch (interactionState) {
            case AttackTargetInteraction targeting ->
                targeting.hoverTarget(hoveredFigureId);
            case HealTargetInteraction targeting ->
                targeting.hoverTarget(hoveredFigureId);
            case AttackAreaPlacementInteraction area ->
                area.previewAt(hoveredHex);
            case HealAreaPlacementInteraction area ->
                area.previewAt(hoveredHex);
            case DamageTargetInteraction targeting ->
                targeting.hoverTarget(hoveredFigureId);
            case DamageAreaPlacementInteraction area ->
                area.previewAt(hoveredHex);
            case ConditionTargetInteraction targeting ->
                targeting.hoverTarget(hoveredFigureId);
            case ConditionAreaPlacementInteraction area ->
                area.previewAt(hoveredHex);
            default -> {
            }
        }
    }

    public void rotateAreaPlacement() {
        if (interactionState instanceof AttackAreaPlacementInteraction area) {
            area.rotate();
        } else if (interactionState
            instanceof HealAreaPlacementInteraction area) {
            area.rotate();
        } else if (interactionState
            instanceof DamageAreaPlacementInteraction area) {
            area.rotate();
        } else if (interactionState
            instanceof ConditionAreaPlacementInteraction area) {
            area.rotate();
        }
    }

    public void finishInteraction() {
        interactionState = new IdleInteraction();
    }

    public void restoreAfterUndo(UndoPerformedEvent event) {
        selectedHex = null;

        if (scenarioState.execution() == null) {
            finishInteraction();
            return;
        }

        PendingChoice pendingChoice =
            scenarioState.execution().pendingChoice();

        if (pendingChoice instanceof PendingMoveChoice movement) {
            restoreMoveChoice(event, movement);
            return;
        }

        if (pendingChoice instanceof PendingFigureTargetChoice.Attack attack) {
            restoreAttackChoice(event, attack);
            return;
        }

        if (pendingChoice instanceof PendingFigureTargetChoice.Heal heal) {
            restoreHealChoice(event, heal);
            return;
        }

        if (pendingChoice instanceof PendingFigureTargetChoice.Damage damage) {
            restoreDamageChoice(event, damage);
            return;
        }

        if (pendingChoice instanceof PendingFigureTargetChoice.Condition condition) {
            restoreConditionChoice(event, condition);
            return;
        }

        if (pendingChoice instanceof PendingAreaChoice.Attack area) {
            restoreAreaChoice(event, area);
            return;
        }

        if (pendingChoice instanceof PendingAreaChoice.Heal area) {
            restoreHealAreaChoice(event, area);
            return;
        }


        if (pendingChoice instanceof PendingAreaChoice.Damage area) {
            restoreDamageAreaChoice(event, area);
            return;
        }

        if (pendingChoice instanceof PendingAreaChoice.Condition area) {
            restoreConditionAreaChoice(event, area);
            return;
        }

        finishInteraction();
    }

    public ScenarioInteractionState interactionState() {
        return interactionState;
    }

    public HexPosition selectedHex() {
        return selectedHex;
    }

    public List<HexPosition> movementPath() {
        if (!(interactionState instanceof MovePathInteraction movement)) {
            return List.of();
        }

        return movement.getPath();
    }

    public List<HexPosition> eligibleTargetHexes() {
        if (interactionState instanceof AttackTargetInteraction attack) {
            return attack.getEligibleTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        if (interactionState instanceof HealTargetInteraction heal) {
            return heal.getEligibleTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        if (interactionState instanceof DamageTargetInteraction damage) {
            return damage.eligibleTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        if (interactionState instanceof ConditionTargetInteraction condition) {
            return condition.eligibleTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        return List.of();
    }

    public List<HexPosition> selectedTargetHexes() {
        if (interactionState instanceof AttackTargetInteraction attack) {
            return attack.getSelectedTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        if (interactionState instanceof HealTargetInteraction heal) {
            return heal.getSelectedTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        if (interactionState instanceof DamageTargetInteraction damage) {
            return damage.selectedTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        if (interactionState instanceof ConditionTargetInteraction condition) {
            return condition.selectedTargets().stream()
                .map(scenarioState::positionOf)
                .toList();
        }
        return List.of();
    }

    public Set<HexPosition> eligibleAreaAnchors() {
        if (interactionState instanceof AttackAreaPlacementInteraction area) {
            return area.eligibleAnchors();
        }
        if (interactionState instanceof HealAreaPlacementInteraction area) {
            return area.eligibleAnchors();
        }
        if (interactionState instanceof DamageAreaPlacementInteraction area) {
            return area.eligibleAnchors();
        }
        if (interactionState instanceof ConditionAreaPlacementInteraction area) {
            return area.eligibleAnchors();
        }
        return Set.of();
    }

    public Set<HexPosition> targetableAreaHexes() {
        if (interactionState instanceof AttackAreaPlacementInteraction area) {
            return area.targetableHexes();
        }
        if (interactionState instanceof HealAreaPlacementInteraction area) {
            return area.targetableHexes();
        }
        if (interactionState instanceof DamageAreaPlacementInteraction area) {
            return area.targetableHexes();
        }
        if (interactionState instanceof ConditionAreaPlacementInteraction area) {
            return area.targetableHexes();
        }
        return Set.of();
    }

    public Set<HexPosition> untargetableAreaHexes() {
        if (interactionState instanceof AttackAreaPlacementInteraction area) {
            return area.untargetableHexes();
        }
        if (interactionState instanceof HealAreaPlacementInteraction area) {
            return area.untargetableHexes();
        }
        if (interactionState instanceof DamageAreaPlacementInteraction area) {
            return area.untargetableHexes();
        }
        if (interactionState instanceof ConditionAreaPlacementInteraction area) {
            return area.untargetableHexes();
        }
        return Set.of();
    }

    public Set<HexPosition> invalidAreaPreviewHexes() {
        if (interactionState instanceof AttackAreaPlacementInteraction area) {
            return area.invalidPreviewHexes();
        }
        if (interactionState instanceof HealAreaPlacementInteraction area) {
            return area.invalidPreviewHexes();
        }
        if (interactionState instanceof DamageAreaPlacementInteraction area) {
            return area.invalidPreviewHexes();
        }
        if (interactionState instanceof ConditionAreaPlacementInteraction area) {
            return area.invalidPreviewHexes();
        }
        return Set.of();
    }

    private void updateMovementPreview(
        HexPosition clickedHex,
        MovePathInteraction movement
    ) {
        FigureId movingFigureId = movement.getFigureId();
        HexPosition currentHex = scenarioState.positionOf(movingFigureId);

        movement.setPath(HexPathfinder.shortestPath(
            scenarioState.board(),
            currentHex,
            clickedHex,
            movement.getAmount(),
            movement.getType(),
            scenarioState.figure(movingFigureId),
            scenarioState.figuresByPosition()
        ));
    }

    private void addToTargetSelection(HexPosition clickedHex, AttackTargetInteraction targeting) {
        if (selectionOption == SelectionOption.AUTOMATIC) {
            return;
        }

        Figure selectedFigure =
            scenarioState.figureAt(clickedHex).orElse(null);
        if (selectedFigure == null) {
            return;
        }

        if (!targeting.getEligibleTargets().contains(selectedFigure.id())) return;

        List<FigureId> selectedTargets = targeting.getSelectedTargets();

        switch (targeting.getDuplicates()) {
            case DISTINCT_TARGETS -> {
                if (selectedTargets.contains(selectedFigure.id())) {
                    selectedTargets.remove(selectedFigure.id());
                    return;
                }
                if (selectedTargets.size() >= targeting.getMaximumTargets()) {
                    return;
                }
                selectedTargets.add(selectedFigure.id());
            }
            case REPEATED_TARGETS_ALLOWED -> {
                if (selectedTargets.size()
                    >= targeting.getMaximumTargets()) {
                    return;
                }
                selectedTargets.add(selectedFigure.id());
            }
        }
    }

    private void addToTargetSelection(
        HexPosition clickedHex,
        HealTargetInteraction targeting
    ) {
        if (selectionOption == SelectionOption.AUTOMATIC) {
            return;
        }

        Figure selectedFigure =
            scenarioState.figureAt(clickedHex).orElse(null);
        if (selectedFigure == null
            || !targeting.getEligibleTargets().contains(
                selectedFigure.id()
            )) {
            return;
        }

        List<FigureId> selectedTargets = targeting.getSelectedTargets();
        switch (targeting.getDuplicates()) {
            case DISTINCT_TARGETS -> {
                if (selectedTargets.contains(selectedFigure.id())) {
                    selectedTargets.remove(selectedFigure.id());
                    return;
                }
                if (selectedTargets.size() >= targeting.getMaximumTargets()) {
                    return;
                }
                selectedTargets.add(selectedFigure.id());
            }
            case REPEATED_TARGETS_ALLOWED -> {
                if (selectedTargets.size() >= targeting.getMaximumTargets()) {
                    return;
                }
                selectedTargets.add(selectedFigure.id());
            }
        }
    }

    private void addToTargetSelection(
        HexPosition clickedHex,
        DamageTargetInteraction targeting
    ) {
        Figure selectedFigure = scenarioState.figureAt(clickedHex)
            .orElse(null);
        if (selectionOption == SelectionOption.AUTOMATIC
            || selectedFigure == null
            || !targeting.eligibleTargets().contains(selectedFigure.id())) {
            return;
        }
        updateTargetSelection(
            selectedFigure.id(),
            targeting.selectedTargets(),
            targeting.maximumTargets(),
            targeting.duplicates()
        );
    }

    private void addToTargetSelection(
        HexPosition clickedHex,
        ConditionTargetInteraction targeting
    ) {
        Figure selectedFigure = scenarioState.figureAt(clickedHex)
            .orElse(null);
        if (selectionOption == SelectionOption.AUTOMATIC
            || selectedFigure == null
            || !targeting.eligibleTargets().contains(selectedFigure.id())) {
            return;
        }
        updateTargetSelection(
            selectedFigure.id(),
            targeting.selectedTargets(),
            targeting.maximumTargets(),
            targeting.duplicates()
        );
    }

    private static void updateTargetSelection(
        FigureId figureId,
        List<FigureId> selectedTargets,
        int maximumTargets,
        io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy duplicates
    ) {
        switch (duplicates) {
            case DISTINCT_TARGETS -> {
                if (selectedTargets.remove(figureId)) {
                    return;
                }
                if (selectedTargets.size() < maximumTargets) {
                    selectedTargets.add(figureId);
                }
            }
            case REPEATED_TARGETS_ALLOWED -> {
                if (selectedTargets.size() < maximumTargets) {
                    selectedTargets.add(figureId);
                }
            }
        }
    }

    public void removeFromTargetSelection(HexPosition clickedHex, AttackTargetInteraction targeting) {
        if (selectionOption == SelectionOption.AUTOMATIC) {
            return;
        }

        Figure selectedFigure =
            scenarioState.figureAt(clickedHex).orElse(null);
        if (selectedFigure == null) {
            return;
        }

        List<FigureId> selectedTargets = targeting.getSelectedTargets();

        selectedTargets.remove(selectedFigure.id());
    }

    private void removeFromTargetSelection(
        HexPosition clickedHex,
        HealTargetInteraction targeting
    ) {
        if (selectionOption == SelectionOption.AUTOMATIC) {
            return;
        }

        Figure selectedFigure =
            scenarioState.figureAt(clickedHex).orElse(null);
        if (selectedFigure == null) {
            return;
        }

        targeting.getSelectedTargets().remove(selectedFigure.id());
    }

    private void removeFromTargetSelection(
        HexPosition clickedHex,
        DamageTargetInteraction targeting
    ) {
        if (selectionOption == SelectionOption.AUTOMATIC) return;
        scenarioState.figureAt(clickedHex)
            .ifPresent(figure ->
                targeting.selectedTargets().remove(figure.id())
            );
    }

    private void removeFromTargetSelection(
        HexPosition clickedHex,
        ConditionTargetInteraction targeting
    ) {
        if (selectionOption == SelectionOption.AUTOMATIC) return;
        scenarioState.figureAt(clickedHex)
            .ifPresent(figure ->
                targeting.selectedTargets().remove(figure.id())
            );
    }

    private void updateAreaPlacement(
        HexPosition clickedHex,
        AttackAreaPlacementInteraction area
    ) {
        area.placeAt(clickedHex);
    }

    private void updateAreaPlacement(
        HexPosition clickedHex,
        HealAreaPlacementInteraction area
    ) {
        area.placeAt(clickedHex);
    }

    private void updateAreaPlacement(
        HexPosition clickedHex,
        DamageAreaPlacementInteraction area
    ) {
        area.placeAt(clickedHex);
    }

    private void updateAreaPlacement(
        HexPosition clickedHex,
        ConditionAreaPlacementInteraction area
    ) {
        area.placeAt(clickedHex);
    }

    private void restoreMoveChoice(
        UndoPerformedEvent event,
        PendingMoveChoice movement
    ) {
        MovePathInteraction restored = new MovePathInteraction(
            movement.movementPoints(),
            movement.figureId(),
            movement.movementType()
        );

        if (event.undoneCommand() instanceof ChooseMovePathCommand(List<HexPosition> path)) {
            restored.setPath(path);
        }

        interactionState = restored;
    }

    private void restoreAttackChoice(
        UndoPerformedEvent event,
        PendingFigureTargetChoice.Attack attack
    ) {
        AttackTargetInteraction restored = new AttackTargetInteraction(
            attack.actorId(),
            attack.selection(),
            attack.previews()
        );

        if (event.undoneCommand() instanceof ChooseFigureTargetsCommand(List<FigureId> targets)) {
            restored.replaceSelectedTargets(targets);
        }

        interactionState = restored;
    }

    private void restoreAreaChoice(
        UndoPerformedEvent event,
        PendingAreaChoice.Attack area
    ) {
        AttackAreaPlacementInteraction restored =
            new AttackAreaPlacementInteraction(
                area.actorId(),
                area.selection(),
                area.previews()
            );

        if (event.undoneCommand()
            instanceof ChooseAttackAreaCommand areaCommand) {
            restored.restorePlacement(areaCommand.placement());
        }

        interactionState = restored;
    }

    private void restoreHealAreaChoice(
        UndoPerformedEvent event,
        PendingAreaChoice.Heal area
    ) {
        HealAreaPlacementInteraction restored =
            new HealAreaPlacementInteraction(
                area.actorId(),
                area.selection(),
                area.previews()
            );

        if (event.undoneCommand()
            instanceof ChooseAttackAreaCommand areaCommand) {
            restored.restorePlacement(areaCommand.placement());
        }

        interactionState = restored;
    }

    private void restoreDamageAreaChoice(
        UndoPerformedEvent event,
        PendingAreaChoice.Damage area
    ) {
        DamageAreaPlacementInteraction restored =
            new DamageAreaPlacementInteraction(
                area.actorId(), area.selection(), area.previews()
            );
        if (event.undoneCommand()
            instanceof ChooseAttackAreaCommand areaCommand) {
            restored.restorePlacement(areaCommand.placement());
        }
        interactionState = restored;
    }

    private void restoreConditionAreaChoice(
        UndoPerformedEvent event,
        PendingAreaChoice.Condition area
    ) {
        ConditionAreaPlacementInteraction restored =
            new ConditionAreaPlacementInteraction(
                area.actorId(), area.selection(), area.previews()
            );
        if (event.undoneCommand()
            instanceof ChooseAttackAreaCommand areaCommand) {
            restored.restorePlacement(areaCommand.placement());
        }
        interactionState = restored;
    }

    public void setSelectionOption(SelectionOption selectionOption) {
        this.selectionOption = Objects.requireNonNull(selectionOption);
    }


    public SelectionOption selectionOption() {
        return selectionOption;
    }

    private void restoreHealChoice(
        UndoPerformedEvent event,
        PendingFigureTargetChoice.Heal heal
    ) {
        HealTargetInteraction restored = new HealTargetInteraction(
            heal.actorId(),
            heal.selection(),
            heal.previews()
        );

        if (event.undoneCommand() instanceof ChooseFigureTargetsCommand(List<FigureId> targets)) {
            restored.replaceSelectedTargets(targets);
        }

        interactionState = restored;
    }

    private void restoreDamageChoice(
        UndoPerformedEvent event,
        PendingFigureTargetChoice.Damage damage
    ) {
        DamageTargetInteraction restored = new DamageTargetInteraction(
            damage.actorId(), damage.selection(), damage.previews()
        );
        if (event.undoneCommand()
            instanceof ChooseFigureTargetsCommand(List<FigureId> targets)) {
            restored.replaceSelectedTargets(targets);
        }
        interactionState = restored;
    }

    private void restoreConditionChoice(
        UndoPerformedEvent event,
        PendingFigureTargetChoice.Condition condition
    ) {
        ConditionTargetInteraction restored =
            new ConditionTargetInteraction(
                condition.actorId(),
                condition.selection(),
                condition.previews()
            );
        if (event.undoneCommand()
            instanceof ChooseFigureTargetsCommand(List<FigureId> targets)) {
            restored.replaceSelectedTargets(targets);
        }
        interactionState = restored;
    }
}
