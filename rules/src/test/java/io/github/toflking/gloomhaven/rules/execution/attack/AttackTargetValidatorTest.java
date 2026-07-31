package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAttackChoice;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AttackTargetValidatorTest {
    private static final FigureId ATTACKER =
        new FigureId("attacker");
    private static final FigureId TARGET =
        new FigureId("target");

    @Test
    void acceptsOnlyEligibleTargetsWithinCardinality() {
        ScenarioState state = scenario();
        AttackTargetValidator validator =
            new AttackTargetValidator(state);
        PendingAttackChoice choice = new PendingAttackChoice(
            ATTACKER,
            Set.of(TARGET),
            0,
            1,
            DuplicatePolicy.DISTINCT_TARGETS,
            TargetSelectionMode.MANUAL
        );

        assertTrue(validator.validate(List.of(TARGET), choice).isValid());
        assertTrue(validator.validate(List.of(), choice).isValid());
        assertFalse(
            validator.validate(List.of(ATTACKER), choice).isValid()
        );
        assertFalse(
            validator.validate(List.of(TARGET, TARGET), choice).isValid()
        );
    }

    @Test
    void allowsRepeatedTargetsWhenTheEffectiveAttackPermitsIt() {
        ScenarioState state = scenario();
        PendingAttackChoice choice = new PendingAttackChoice(
            ATTACKER,
            Set.of(TARGET),
            0,
            2,
            DuplicatePolicy.REPEATED_TARGETS_ALLOWED,
            TargetSelectionMode.MANUAL
        );

        assertTrue(
            new AttackTargetValidator(state)
                .validate(List.of(TARGET, TARGET), choice)
                .isValid()
        );
    }

    @Test
    void rejectsChangesToFixedTargets() {
        ScenarioState state = scenario();
        PendingAttackChoice choice = new PendingAttackChoice(
            ATTACKER,
            Set.of(ATTACKER, TARGET),
            2,
            2,
            DuplicatePolicy.REPEATED_TARGETS_ALLOWED,
            TargetSelectionMode.FIXED
        );
        AttackTargetValidator validator =
            new AttackTargetValidator(state);

        assertTrue(
            validator.validate(List.of(TARGET, ATTACKER), choice).isValid()
        );
        assertFalse(
            validator.validate(List.of(TARGET, TARGET), choice).isValid()
        );
    }

    private static ScenarioState scenario() {
        HexPosition attackerPosition = new HexPosition(0, 0);
        HexPosition targetPosition = new HexPosition(1, 0);
        HexBoard board = new HexBoard(
            Map.of(
                attackerPosition,
                new HexTile(TerrainType.NORMAL),
                targetPosition,
                new HexTile(TerrainType.NORMAL)
            ),
            Set.of()
        );
        ScenarioState state = new ScenarioState(board);
        state.addFigure(
            new Figure(ATTACKER, FigureType.CHARACTER, 10),
            attackerPosition
        );
        state.addFigure(
            new Figure(TARGET, FigureType.MONSTER, 10),
            targetPosition
        );
        return state;
    }
}
