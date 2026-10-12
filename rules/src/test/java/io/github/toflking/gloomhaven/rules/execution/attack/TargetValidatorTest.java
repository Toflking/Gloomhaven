package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
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
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaChoice;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingFigureTargetChoice;
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

class TargetValidatorTest {
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
        TargetValidator validator =
            new TargetValidator(state);
        PendingFigureTargetChoice choice = attackChoice(
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
        PendingFigureTargetChoice choice = attackChoice(
            Set.of(TARGET),
            0,
            2,
            DuplicatePolicy.REPEATED_TARGETS_ALLOWED,
            TargetSelectionMode.MANUAL
        );

        assertTrue(
            new TargetValidator(state)
                .validateTargets(List.of(TARGET, TARGET), choice)
                .isValid()
        );
    }

    @Test
    void rejectsChangesToFixedTargets() {
        ScenarioState state = scenario();
        PendingFigureTargetChoice choice = attackChoice(
            Set.of(ATTACKER, TARGET),
            2,
            2,
            DuplicatePolicy.REPEATED_TARGETS_ALLOWED,
            TargetSelectionMode.FIXED
        );
        TargetValidator validator =
            new TargetValidator(state);

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
        TargetPlan.Area plan = areaPlan();
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ATTACKER, SOURCE, List.of(attack));
        ActionExecution execution = state.execution();
        beginAttack(state, execution, attack);

        FigureTargetSelection.AreaPlacementOption option =
            new FigureTargetSelection.AreaPlacementOption(
                new HexPosition(0, 0),
                0,
                false
            );
        PendingAreaChoice choice = new PendingAreaChoice.Attack(
            ATTACKER,
            new FigureTargetSelection.AreaPlacement(
                List.of(new ResolvedAreaPlacement(
                    option,
                    Set.of(new HexPosition(1, 0))
                )),
                AreaPlacementControl.LOCKED_TO_ORIGIN
            ),
            Map.of()
        );

        var result = new TargetValidator(state)
            .validateArea(option, choice);

        assertTrue(result.isValid());
        assertEquals(List.of(TARGET), result.targets());
    }

    @Test
    void rejectsAnAreaPlacementThatWasNotOffered() {
        ScenarioState state = scenario();
        TargetPlan.Area plan = areaPlan();
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ATTACKER, SOURCE, List.of(attack));
        beginAttack(state, state.execution(), attack);

        FigureTargetSelection.AreaPlacementOption eligible =
            new FigureTargetSelection.AreaPlacementOption(
                new HexPosition(0, 0),
                0,
                false
            );
        FigureTargetSelection.AreaPlacementOption unlisted =
            new FigureTargetSelection.AreaPlacementOption(
                new HexPosition(1, 0),
                0,
                false
            );
        PendingAreaChoice choice = new PendingAreaChoice.Attack(
            ATTACKER,
            new FigureTargetSelection.AreaPlacement(
                List.of(new ResolvedAreaPlacement(
                    eligible,
                    Set.of(new HexPosition(1, 0))
                )),
                AreaPlacementControl.LOCKED_TO_ORIGIN
            ),
            Map.of()
        );

        assertFalse(new TargetValidator(state)
            .validateArea(unlisted, choice)
            .isValid());
    }

    @Test
    void rejectsAnIneligibleAreaPreview() {
        ScenarioState state = scenario();
        TargetPlan.Area plan = areaPlan();
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ATTACKER, SOURCE, List.of(attack));
        beginAttack(state, state.execution(), attack);

        FigureTargetSelection.AreaPlacementOption option =
            new FigureTargetSelection.AreaPlacementOption(
                new HexPosition(0, 0),
                0,
                false
            );
        PendingAreaChoice choice = new PendingAreaChoice.Attack(
            ATTACKER,
            new FigureTargetSelection.AreaPlacement(
                List.of(new ResolvedAreaPlacement(
                    option,
                    Set.of(new HexPosition(1, 0)),
                    Set.of(new HexPosition(1, 0)),
                    false
                )),
                AreaPlacementControl.LOCKED_TO_ORIGIN
            ),
            Map.of()
        );

        assertFalse(new TargetValidator(state)
            .validateArea(option, choice)
            .isValid());
    }

    private static TargetPlan.Area areaPlan() {
        return new TargetPlan.Area(
            new HexPattern(List.of(new HexPosition(1, 0))),
            new AreaPlacementRule.AroundOrigin(false, false),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.ENEMY
            )
        );
    }

    private static void beginAttack(
        ScenarioState state,
        ActionExecution execution,
        AttackAbility attack
    ) {
        execution.beginAttack(
            attack.effectiveDefinition(),
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );
    }

    private static PendingFigureTargetChoice attackChoice(
        Set<FigureId> eligibleTargets,
        int minimumTargets,
        int maximumTargets,
        DuplicatePolicy duplicates,
        TargetSelectionMode selectionMode
    ) {
        return new PendingFigureTargetChoice.Attack(
            ATTACKER,
            new FigureTargetSelection.FigureTargets(
                eligibleTargets,
                minimumTargets,
                maximumTargets,
                duplicates,
                selectionMode
            ),
            Map.of()
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
