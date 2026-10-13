package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.Cardinality;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.command.ChooseFigureTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseAttackAreaCommand;
import io.github.toflking.gloomhaven.rules.command.PlayCardActionCommand;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.event.ActionFinishedEvent;
import io.github.toflking.gloomhaven.rules.event.AttackAreaPlacementRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.FigureDamagedEvent;
import io.github.toflking.gloomhaven.rules.event.AttackTargetChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.ConditionAppliedEvent;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPattern;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.rules.scenario.TurnOrderEntry;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static io.github.toflking.gloomhaven.rules.TestFigures.character;
import static io.github.toflking.gloomhaven.rules.TestFigures.monster;

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

        AttackTargetChoiceRequestedEvent requested =
            assertInstanceOf(
                AttackTargetChoiceRequestedEvent.class,
                playResult.events().getFirst()
            );
        assertEquals(Set.of(TARGET), requested.selection().eligibleTargets());
        assertEquals(1, requested.selection().maximumTargets());
        assertEquals(TargetSelectionMode.MANUAL, requested.selection().selectionMode());
        assertEquals(3, requested.previews().get(TARGET).projectedDamage());

        CommandResult attackResult = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        FigureDamagedEvent damaged = assertInstanceOf(
            FigureDamagedEvent.class,
            attackResult.events().getFirst()
        );
        assertEquals(3, damaged.amount());
        assertEquals(7, state.figure(TARGET).health().currentHealth());
        assertInstanceOf(
            ActionFinishedEvent.class,
            attackResult.events().getLast()
        );
        assertNull(state.execution());
    }

    @Test
    void evaluatesPerAttackValuesAndPreservesDuplicateTargets() {
        ScenarioState state = scenario();
        TargetPlan.Query targets =
            new TargetPlan.Query(
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
            new ChooseFigureTargetsCommand(List.of(TARGET, TARGET))
        );

        List<Integer> damage = result.events()
            .stream()
            .filter(FigureDamagedEvent.class::isInstance)
            .map(FigureDamagedEvent.class::cast)
            .map(FigureDamagedEvent::amount)
            .toList();

        assertEquals(List.of(3, 2), damage);
        assertEquals(5, state.figure(TARGET).health().currentHealth());
        assertTrue(result.accepted());
    }

    @Test
    void freezesAbilityStartValuesBeforeTargetSelection() {
        ScenarioState state = scenario();
        state.damageFigure(ATTACKER, 2);
        AbilityValue amount = new AbilityValue(
            new IntExpression.Add(
                new IntExpression.FigureStatValue(
                    new FigureReference.Actor(),
                    IntExpression.FigureStat.MISSING_HEALTH
                ),
                new IntExpression.Constant(1)
            ),
            EvaluationMoment.ABILITY_START
        );
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(
                amount,
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(3, requested.previews().get(TARGET).projectedDamage());

        state.damageFigure(ATTACKER, 2);
        CommandResult attackResult = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        FigureDamagedEvent damaged = assertInstanceOf(
            FigureDamagedEvent.class,
            attackResult.events().getFirst()
        );
        assertEquals(3, damaged.amount());
    }

    @Test
    void previewsPostSelectionValuesWithoutCachingThemEarly() {
        ScenarioState state = scenario();
        state.damageFigure(ATTACKER, 2);
        AbilityValue amount = new AbilityValue(
            new IntExpression.Add(
                new IntExpression.FigureStatValue(
                    new FigureReference.Actor(),
                    IntExpression.FigureStat.MISSING_HEALTH
                ),
                new IntExpression.Constant(1)
            ),
            EvaluationMoment.AFTER_TARGET_SELECTION
        );
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(
                amount,
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(3, requested.previews().get(TARGET).projectedDamage());

        state.damageFigure(ATTACKER, 2);
        CommandResult attackResult = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        FigureDamagedEvent damaged = assertInstanceOf(
            FigureDamagedEvent.class,
            attackResult.events().getFirst()
        );
        assertEquals(5, damaged.amount());
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

        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(TargetSelectionMode.FIXED, requested.selection().selectionMode());
        assertEquals(Set.of(TARGET), requested.selection().eligibleTargets());
        assertEquals(10, state.figure(TARGET).health().currentHealth());

        CommandResult attackResult = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        assertTrue(attackResult.accepted());
        assertEquals(7, state.figure(TARGET).health().currentHealth());
    }

    @Test
    void resolvesAndExecutesTheChosenAreaPlacement() {
        ScenarioState state = scenario();
        TargetPlan.Area targeting = new TargetPlan.Area(
            new HexPattern(List.of(new HexPosition(1, 0))),
            new AreaPlacementRule.AroundOrigin(false, false),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.ENEMY
            )
        );
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(AbilityValue.fixed(3), targeting)
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackAreaPlacementRequestedEvent requested = assertInstanceOf(
            AttackAreaPlacementRequestedEvent.class,
            playResult.events().getFirst()
        );
        var placement = requested.selection().placements()
            .getFirst();

        CommandResult attackResult = engine.execute(
            new ChooseAttackAreaCommand(placement.option())
        );

        assertTrue(attackResult.accepted());
        assertEquals(7, state.figure(TARGET).health().currentHealth());
        assertInstanceOf(
            FigureDamagedEvent.class,
            attackResult.events().getFirst()
        );
        assertInstanceOf(
            ActionFinishedEvent.class,
            attackResult.events().getLast()
        );
    }

    @Test
    void appliesAttackConditionsAfterDamage() {
        ScenarioState state = scenario();
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(
                AbilityValue.fixed(3),
                new ActionOrigin.Actor(),
                AttackTargetPlans.singleEnemyWithinRange(1),
                List.of(new AttackEffect.Condition(
                    Conditions.POISON,
                    false
                ))
            )
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(
            List.of(Conditions.POISON.id()),
            requested.previews()
                .get(TARGET)
                .effects()
                .addedConditions()
        );
        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        assertEquals(7, state.figure(TARGET).health().currentHealth());
        assertTrue(
            state.figure(TARGET)
                .conditions()
                .contains(Conditions.POISON.id())
        );
        assertInstanceOf(
            ConditionAppliedEvent.class,
            result.events().get(1)
        );
    }

    @Test
    void doesNotApplyAttackConditionsAfterTheTargetIsDefeated() {
        ScenarioState state = scenario(3);
        ScenarioEngine engine = engine(
            state,
            new AttackAbility(
                AbilityValue.fixed(3),
                new ActionOrigin.Actor(),
                AttackTargetPlans.singleEnemyWithinRange(1),
                List.of(new AttackEffect.Condition(
                    Conditions.POISON,
                    false
                ))
            )
        );

        engine.execute(new PlayCardActionCommand(SOURCE));
        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        assertTrue(result.events().stream()
            .noneMatch(ConditionAppliedEvent.class::isInstance));
        assertFalse(state.hasFigure(TARGET));
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
        return scenario(10);
    }

    private static ScenarioState scenario(int targetHealth) {
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
            monster(TARGET, targetHealth),
            targetPosition
        );
        state.startNextRound(List.of(
            new TurnOrderEntry(ATTACKER, 50),
            new TurnOrderEntry(TARGET, 60)
        ));
        return state;
    }
}
