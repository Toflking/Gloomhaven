package io.github.toflking.gloomhaven.rules.execution.movement;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.execution.ValidationResult;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.github.toflking.gloomhaven.rules.TestFigures.character;

class MovePathValidatorTest {
    private static final FigureId FIGURE_ID =
        new FigureId("moving-figure");
    private static final HexPosition START =
        new HexPosition(0, 0);
    private static final HexPosition DIFFICULT =
        new HexPosition(1, 0);
    private static final HexPosition DESTINATION =
        new HexPosition(2, 0);

    @Test
    void validPathReportsActualMovementCost() {
        ScenarioState state = scenario();
        PendingMoveChoice choice = new PendingMoveChoice(
            FIGURE_ID,
            3,
            MovementType.NORMAL
        );

        ValidationResult result = new MovePathValidator(state).validate(
            List.of(START, DIFFICULT, DESTINATION),
            choice
        );

        assertTrue(result.isValid());
        assertEquals(3, result.movementSpent());
    }

    private static ScenarioState scenario() {
        ScenarioState state = new ScenarioState(new HexBoard(
            Map.of(
                START, new HexTile(TerrainType.NORMAL),
                DIFFICULT, new HexTile(TerrainType.DIFFICULT),
                DESTINATION, new HexTile(TerrainType.NORMAL)
            ),
            Set.of()
        ));
        state.addFigure(
            character(FIGURE_ID, 10),
            START
        );
        return state;
    }
}
