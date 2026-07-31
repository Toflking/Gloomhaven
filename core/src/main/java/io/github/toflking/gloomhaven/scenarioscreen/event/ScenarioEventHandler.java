package io.github.toflking.gloomhaven.scenarioscreen.event;

import io.github.toflking.gloomhaven.rules.engine.CommandResult;
import io.github.toflking.gloomhaven.rules.event.*;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.ScenarioInteractionController;

import java.util.Objects;

public final class ScenarioEventHandler {
    private final ScenarioInteractionController interactionController;

    public ScenarioEventHandler(
        ScenarioInteractionController interactionController
    ) {
        this.interactionController = Objects.requireNonNull(
            interactionController,
            "interactionController"
        );
    }

    public void handle(CommandResult result) {
        if (!result.accepted()) {
            System.out.println(result.rejectionReason());
            return;
        }

        result.events().forEach(this::handle);
    }

    private void handle(GameEvent gameEvent) {
        System.out.println(gameEvent);

        switch (gameEvent) {
            case MoveChoiceRequestedEvent event ->
                interactionController.beginMoveChoice(
                    event.amount(),
                    event.actor(),
                    event.type()
                );
            case TargetChoiceRequestedEvent event ->
                interactionController.beginTargetChoice(
                    event.attackerId(),
                    event.eligibleTargets(),
                    event.minimumTargets(),
                    event.maximumTargets(),
                    event.duplicates(),
                    event.selectionMode()
                );
            case UndoPerformedEvent event ->
                interactionController.restoreAfterUndo(event);
            case ActionFinishedEvent ignored ->
                interactionController.finishInteraction();
            case CancelActionEvent ignored ->
                interactionController.finishInteraction();
            case SkipActionEvent ignored ->
                interactionController.finishInteraction();
            case FigureDamagedEvent ignored -> {
            }
            case FigureDefeatedEvent ignored -> {
            }
            case FigureMovedEvent ignored -> {
            }
            case TurnEndedEvent ignored -> {
            }
        }
    }
}
