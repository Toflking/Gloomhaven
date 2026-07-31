package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.Cardinality;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.command.ChooseAttackTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.PlayCardActionCommand;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.event.ActionFinishedEvent;
import io.github.toflking.gloomhaven.rules.event.FigureDamagedEvent;
import io.github.toflking.gloomhaven.rules.event.TargetChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioEngineAttackTest {
    private static final FigureId ATTACKER =
        new FigureId("attacker");
    private static final FigureId TARGET =
        new FigureId("target");
    private static final AbilityCardId CARD_ID =
        new AbilityCardId("test-attack");
    private static final AbilitySource SOURCE =
        new AbilitySource(CARD_ID, CardHalf.TOP);

    @Test
    void requestsConcreteTargetsAndCompletesTheAttack() {
        ScenarioState state = scenario();
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(
                AbilityValue.fixed(3),
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );

        TargetChoiceRequestedEvent requested =
            assertInstanceOf(
                TargetChoiceRequestedEvent.class,
                playResult.events().getFirst()
            );
        assertEquals(Set.of(TARGET), requested.eligibleTargets());
        assertEquals(1, requested.maximumTargets());
        assertEquals(TargetSelectionMode.MANUAL, requested.selectionMode());

        CommandResult attackResult = engine.execute(
            new ChooseAttackTargetsCommand(List.of(TARGET))
        );

        FigureDamagedEvent damaged = assertInstanceOf(
            FigureDamagedEvent.class,
            attackResult.events().getFirst()
        );
        assertEquals(3, damaged.amount());
        assertEquals(7, state.figure(TARGET).health());
        assertInstanceOf(
            ActionFinishedEvent.class,
            attackResult.events().getLast()
        );
        assertNull(state.execution());
    }

    @Test
    void evaluatesPerAttackValuesAndPreservesDuplicateTargets() {
        ScenarioState state = scenario();
        AttackTargetPlan.Query targets =
            new AttackTargetPlan.Query(
                AttackTargetPlans
                    .singleEnemyWithinRange(1)
                    .candidates(),
                new Cardinality.ChooseExactly(
                    new IntExpression.Constant(2)
                ),
                DuplicatePolicy.REPEATED_TARGETS_ALLOWED
            );
        AbilityValue decreasingValue = new AbilityValue(
            new IntExpression.Subtract(
                new IntExpression.Constant(3),
                new IntExpression.AttackIndex()
            ),
            EvaluationMoment.PER_ATTACK
        );
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(decreasingValue, targets)
        );

        engine.execute(new PlayCardActionCommand(SOURCE));
        CommandResult result = engine.execute(
            new ChooseAttackTargetsCommand(List.of(TARGET, TARGET))
        );

        List<Integer> damage = result.events()
            .stream()
            .filter(FigureDamagedEvent.class::isInstance)
            .map(FigureDamagedEvent.class::cast)
            .map(FigureDamagedEvent::amount)
            .toList();

        assertEquals(List.of(3, 2), damage);
        assertEquals(5, state.figure(TARGET).health());
        assertTrue(result.accepted());
    }

    @Test
    void requestsConfirmationBeforeResolvingFixedTargets() {
        ScenarioState state = scenario();
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(
                AbilityValue.fixed(3),
                AttackTargetPlans.allAdjacentEnemies()
            )
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );

        TargetChoiceRequestedEvent requested = assertInstanceOf(
            TargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(TargetSelectionMode.FIXED, requested.selectionMode());
        assertEquals(Set.of(TARGET), requested.eligibleTargets());
        assertEquals(10, state.figure(TARGET).health());

        CommandResult attackResult = engine.execute(
            new ChooseAttackTargetsCommand(List.of(TARGET))
        );

        assertTrue(attackResult.accepted());
        assertEquals(7, state.figure(TARGET).health());
    }

    private static ScenarioEngine engine(
        ScenarioState state,
        AttackAbility attack
    ) {
        CardDefinition card = new CardDefinition(
            CARD_ID,
            "Test Attack",
            1,
            50,
            new CardAction(List.of(attack)),
            new CardAction(List.of())
        );
        return new ScenarioEngine(
            state,
            new CardCatalog(List.of(card))
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
        state.setActiveFigure(ATTACKER);
        return state;
    }
}
