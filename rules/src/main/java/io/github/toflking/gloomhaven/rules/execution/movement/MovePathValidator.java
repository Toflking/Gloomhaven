package io.github.toflking.gloomhaven.rules.execution.movement;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.pathfinding.MovementRules;
import io.github.toflking.gloomhaven.rules.execution.ValidationResult;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.List;

public final class MovePathValidator {
    private final ScenarioState state;

    public MovePathValidator(ScenarioState state) {
        this.state = state;
    }

    public ValidationResult validate(List<HexPosition> path, PendingMoveChoice choice) {
        if (path == null || path.size() < 2) {
            return ValidationResult.invalid(
                "Movement path must contain at least one step"
            );
        }

        HexPosition actualStart =
            state.positionOf(choice.figureId());

        if (!path.getFirst().equals(actualStart)) {
            return ValidationResult.invalid(
                "Movement path does not start at the figure's position"
            );
        }

        int movementSpent = 0;

        for (int i = 1; i < path.size(); i++) {
            HexPosition previous = path.get(i - 1);
            HexPosition current = path.get(i);

            if (!state.board().contains(current)) {
                return ValidationResult.invalid(
                    "Movement path leaves the board"
                );
            }

            if (previous.distanceTo(current) != 1) {
                return ValidationResult.invalid(
                    "Movement path contains non-adjacent hexes"
                );
            }

            if (state.board().hasWallBetween(previous, current)) {
                return ValidationResult.invalid(
                    "Movement path crosses a wall"
                );
            }

            movementSpent += movementCost(
                current,
                choice.movementType()
            );

            if (movementSpent > choice.movementPoints()) {
                return ValidationResult.invalid(
                    "Movement path exceeds available movement points"
                );
            }

            if (!canMoveThrough(
                choice.figureId(),
                current,
                choice.movementType()
            )) {
                return ValidationResult.invalid(
                    "Movement path crosses a blocked hex"
                );
            }
        }

        HexPosition destination = path.getLast();

        if (!canEndMovementAt(
            choice.figureId(),
            destination,
            choice.movementType()
        )) {
            return ValidationResult.invalid(
                "Movement cannot end on this hex"
            );
        }

        return ValidationResult.valid();
    }

    private int movementCost(HexPosition position, MovementType movementType) {
        return MovementRules.movementCost(
            state.board().tileAt(position),
            movementType
        );
    }

    private boolean canMoveThrough(FigureId actor, HexPosition position, MovementType movementType) {
        Figure movingFigure = state.figure(actor);
        Figure occupant = state.figureAt(position).orElse(null);

        return MovementRules.canTraverse(
            state.board().tileAt(position),
            movementType,
            movingFigure,
            occupant
        );
    }

    private boolean canEndMovementAt(
        FigureId actor,
        HexPosition position,
        MovementType movementType
    ) {
        Figure movingFigure = state.figure(actor);
        Figure occupant = state.figureAt(position).orElse(null);

        return MovementRules.canEndAt(
            state.board().tileAt(position),
            movementType,
            movingFigure,
            occupant
        );
    }
}
