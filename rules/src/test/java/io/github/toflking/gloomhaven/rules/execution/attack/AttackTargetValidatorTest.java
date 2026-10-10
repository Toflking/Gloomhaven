package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.board.HexPattern;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaAttackChoice;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.github.toflking.gloomhaven.rules.TestFigures.character;
import static io.github.toflking.gloomhaven.rules.TestFigures.monster;

class AttackTargetValidatorTest {
    private static final FigureId ATTACKER =
        new FigureId("attacker");
    private static final FigureId TARGET =
        new FigureId("target");
    private static final AbilitySource SOURCE = new AbilitySource(
        new AbilityCardId("validator-test"),
        CardHalf.TOP
    );

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

        assertTrue(validator.validateTargets(List.of(TARGET), choice).isValid());
        assertTrue(validator.validateTargets(List.of(), choice).isValid());
        assertFalse(
            validator.validateTargets(List.of(ATTACKER), choice).isValid()
        );
        assertFalse(
            validator.validateTargets(List.of(TARGET, TARGET), choice).isValid()
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
                .validateTargets(List.of(TARGET, TARGET), choice)
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
            validator.validateTargets(List.of(TARGET, ATTACKER), choice).isValid()
        );
        assertFalse(
            validator.validateTargets(List.of(TARGET, TARGET), choice).isValid()
        );
    }

    @Test
    void resolvesAreaTargetsFromTheCurrentScenarioState() {
        ScenarioState state = scenario();
        AttackTargetPlan.Area plan = areaPlan();
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ATTACKER, SOURCE, List.of(attack));
        ActionExecution execution = state.execution();
        execution.beginAttack(attack.effectiveDefinition());

        AttackChoice.AreaPlacementOption option =
            new AttackChoice.AreaPlacementOption(
                new HexPosition(0, 0),
                0,
                false
            );
        PendingAreaAttackChoice choice = new PendingAreaAttackChoice(
            ATTACKER,
            List.of(new ResolvedAreaPlacement(
                option,
                Set.of(new HexPosition(1, 0))
            )),
            AreaPlacementControl.LOCKED_TO_ORIGIN
        );

        var result = new AttackTargetValidator(state)
            .validateArea(option, choice);

        assertTrue(result.isValid());
        assertEquals(List.of(TARGET), result.targets());
    }

    @Test
    void rejectsAnAreaPlacementThatWasNotOffered() {
        ScenarioState state = scenario();
        AttackTargetPlan.Area plan = areaPlan();
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ATTACKER, SOURCE, List.of(attack));
        state.execution().beginAttack(attack.effectiveDefinition());

        AttackChoice.AreaPlacementOption eligible =
            new AttackChoice.AreaPlacementOption(
                new HexPosition(0, 0),
                0,
                false
            );
        AttackChoice.AreaPlacementOption unlisted =
            new AttackChoice.AreaPlacementOption(
                new HexPosition(1, 0),
                0,
                false
            );
        PendingAreaAttackChoice choice = new PendingAreaAttackChoice(
            ATTACKER,
            List.of(new ResolvedAreaPlacement(
                eligible,
                Set.of(new HexPosition(1, 0))
            )),
            AreaPlacementControl.LOCKED_TO_ORIGIN
        );

        assertFalse(new AttackTargetValidator(state)
            .validateArea(unlisted, choice)
            .isValid());
    }

    @Test
    void rejectsAnIneligibleAreaPreview() {
        ScenarioState state = scenario();
        AttackTargetPlan.Area plan = areaPlan();
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ATTACKER, SOURCE, List.of(attack));
        state.execution().beginAttack(attack.effectiveDefinition());

        AttackChoice.AreaPlacementOption option =
            new AttackChoice.AreaPlacementOption(
                new HexPosition(0, 0),
                0,
                false
            );
        PendingAreaAttackChoice choice = new PendingAreaAttackChoice(
            ATTACKER,
            List.of(new ResolvedAreaPlacement(
                option,
                Set.of(new HexPosition(1, 0)),
                Set.of(new HexPosition(1, 0)),
                false
            )),
            AreaPlacementControl.LOCKED_TO_ORIGIN
        );

        assertFalse(new AttackTargetValidator(state)
            .validateArea(option, choice)
            .isValid());
    }

    private static AttackTargetPlan.Area areaPlan() {
        return new AttackTargetPlan.Area(
            new HexPattern(List.of(new HexPosition(1, 0))),
            new AreaPlacementRule.AroundOrigin(false, false),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.ENEMY
            )
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
            character(ATTACKER, 10),
            attackerPosition
        );
        state.addFigure(
            monster(TARGET, 10),
            targetPosition
        );
        return state;
    }
}
