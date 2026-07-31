package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.pathfinding.HexPathfinder;
import io.github.toflking.gloomhaven.rules.command.ChooseAttackTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseMovePathCommand;
import io.github.toflking.gloomhaven.rules.event.UndoPerformedEvent;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAttackChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class ScenarioInteractionController {
    private final ScenarioState scenarioState;

    private HexPosition selectedHex;
    private ScenarioInteractionState interactionState =
        new IdleInteraction();

    public ScenarioInteractionController(ScenarioState scenarioState) {
        this.scenarioState = Objects.requireNonNull(
            scenarioState,
            "scenarioState"
        );
    }

    public void handleBoardClick(HexPosition clickedHex) {
        selectedHex = null;

        switch (interactionState) {
            case IdleInteraction ignored -> selectedHex = clickedHex;
            case MovePathInteraction movement ->
                updateMovementPreview(clickedHex, movement);
            case AttackTargetInteraction targeting ->
                updateTargetSelection(clickedHex, targeting);
            case AreaPlacementInteraction area ->
                updateAreaPlacement(clickedHex, area);
        }
    }

    public void beginMoveChoice(
        int amount,
        FigureId actor,
        MovementType type
    ) {
        interactionState = new MovePathInteraction(amount, actor, type);
    }

    public void beginTargetChoice(
        FigureId attacker,
        Set<FigureId> eligibleTargets,
        int minimumTargets,
        int maximumTargets,
        DuplicatePolicy duplicates,
        TargetSelectionMode selectionMode
    ) {
        interactionState = new AttackTargetInteraction(
            attacker,
            eligibleTargets,
            minimumTargets,
            maximumTargets,
            duplicates,
            selectionMode
        );
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

        if (pendingChoice instanceof PendingAttackChoice attack) {
            restoreAttackChoice(event, attack);
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
        if (!(interactionState instanceof AttackTargetInteraction attack)) {
            return List.of();
        }

        return attack.getEligibleTargets().stream()
            .map(scenarioState::positionOf)
            .toList();
    }

    public List<HexPosition> selectedTargetHexes() {
        if (!(interactionState instanceof AttackTargetInteraction attack)) {
            return List.of();
        }

        return attack.getSelectedTargets().stream()
            .map(scenarioState::positionOf)
            .toList();
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

    private void updateTargetSelection(
        HexPosition clickedHex,
        AttackTargetInteraction targeting
    ) {
        if (targeting.getSelectionMode() == TargetSelectionMode.FIXED) {
            return;
        }

        Figure selectedFigure =
            scenarioState.figureAt(clickedHex).orElse(null);
        if (selectedFigure == null) {
            return;
        }

        List<FigureId> selectedTargets = targeting.getSelectedTargets();

        switch (targeting.getDuplicates()) {
            case DISTINCT_TARGETS -> {
                if (selectedTargets.contains(selectedFigure.id())
                    && selectedTargets.size()
                    >= targeting.getMinimumTargets()) {
                    selectedTargets.remove(selectedFigure.id());
                } else {
                    if (selectedTargets.size()
                        >= targeting.getMaximumTargets()) {
                        return;
                    }
                    selectedTargets.add(selectedFigure.id());
                }
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

    private void updateAreaPlacement(
        HexPosition clickedHex,
        AreaPlacementInteraction area
    ) {
        throw new UnsupportedOperationException();
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

        if (event.undoneCommand() instanceof ChooseMovePathCommand command) {
            restored.setPath(command.path());
        }

        interactionState = restored;
    }

    private void restoreAttackChoice(
        UndoPerformedEvent event,
        PendingAttackChoice attack
    ) {
        AttackTargetInteraction restored = new AttackTargetInteraction(
            attack.attackerId(),
            attack.eligibleTargets(),
            attack.minimumTargets(),
            attack.maximumTargets(),
            attack.duplicates(),
            attack.selectionMode()
        );

        if (event.undoneCommand()
            instanceof ChooseAttackTargetsCommand command) {
            restored.replaceSelectedTargets(command.targets());
        }

        interactionState = restored;
    }
}
