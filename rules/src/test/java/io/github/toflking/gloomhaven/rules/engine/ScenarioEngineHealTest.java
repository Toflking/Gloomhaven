package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.Cardinality;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetAnchor;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetQuery;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetRegion;
import io.github.toflking.gloomhaven.rules.ability.heal.HealAbility;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPattern;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.command.ChooseAttackAreaCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseFigureTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.PlayCardActionCommand;
import io.github.toflking.gloomhaven.rules.command.UndoCommand;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.event.ActionFinishedEvent;
import io.github.toflking.gloomhaven.rules.event.HealAreaPlacementRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.HealTargetChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingFigureTargetChoice;
import io.github.toflking.gloomhaven.rules.figure.ConditionState;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.toflking.gloomhaven.rules.TestFigures.character;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioEngineHealTest {
    private static final FigureId HEALER = new FigureId("healer");
    private static final FigureId ALLY = new FigureId("ally");
    private static final AbilityCardId CARD_ID =
        new AbilityCardId("test-heal");
    private static final AbilitySource SOURCE =
        new AbilitySource(CARD_ID, CardHalf.TOP);

    @Test
    void previewsAndExecutesAHealThenRestoresItOnUndo() {
        ScenarioState state = scenario(character(ALLY, 10));
        state.damageFigure(ALLY, 5);
        ScenarioEngine engine = engine(
            state,
            new HealAbility(AbilityValue.fixed(3), oneAdjacentAlly())
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );

        HealTargetChoiceRequestedEvent requested = assertInstanceOf(
            HealTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(Set.of(ALLY), requested.selection().eligibleTargets());
        assertEquals(3, requested.previews().get(ALLY).healValue());
        assertEquals(3, requested.previews().get(ALLY).projectedHealing());
        assertFalse(requested.previews().get(ALLY).poison());

        CommandResult healResult = engine.execute(
            new ChooseFigureTargetsCommand(List.of(ALLY))
        );

        assertTrue(healResult.accepted());
        assertEquals(8, state.figure(ALLY).health().currentHealth());
        assertInstanceOf(
            ActionFinishedEvent.class,
            healResult.events().getLast()
        );
        assertNull(state.execution());

        engine.execute(new UndoCommand());

        assertEquals(5, state.figure(ALLY).health().currentHealth());
        assertInstanceOf(
            PendingFigureTargetChoice.Heal.class,
            state.execution().pendingChoice()
        );
        assertFalse(state.execution().healExecution().hasResolvedTargets());
    }

    @Test
    void poisonConsumesTheHealWithoutRestoringHealth() {
        Figure poisonedAlly = withConditions(
            character(ALLY, 10),
            Conditions.POISON.id(),
            Conditions.WOUND.id()
        );
        ScenarioState state = scenario(poisonedAlly);
        state.damageFigure(ALLY, 5);
        ScenarioEngine engine = engine(
            state,
            new HealAbility(AbilityValue.fixed(3), oneAdjacentAlly())
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        HealTargetChoiceRequestedEvent requested = assertInstanceOf(
            HealTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );

        assertTrue(requested.previews().get(ALLY).poison());
        assertEquals(0, requested.previews().get(ALLY).projectedHealing());

        engine.execute(new ChooseFigureTargetsCommand(List.of(ALLY)));

        assertEquals(5, state.figure(ALLY).health().currentHealth());
        assertFalse(
            state.figure(ALLY).conditions().contains(Conditions.POISON.id())
        );
        assertFalse(
            state.figure(ALLY).conditions().contains(Conditions.WOUND.id())
        );
    }

    @Test
    void woundIsRemovedAndHealingStillRestoresHealth() {
        Figure woundedAlly = withConditions(
            character(ALLY, 10),
            Conditions.WOUND.id()
        );
        ScenarioState state = scenario(woundedAlly);
        state.damageFigure(ALLY, 5);
        ScenarioEngine engine = engine(
            state,
            new HealAbility(AbilityValue.fixed(3), oneAdjacentAlly())
        );

        engine.execute(new PlayCardActionCommand(SOURCE));
        engine.execute(new ChooseFigureTargetsCommand(List.of(ALLY)));

        assertEquals(8, state.figure(ALLY).health().currentHealth());
        assertFalse(
            state.figure(ALLY).conditions().contains(Conditions.WOUND.id())
        );
    }

    @Test
    void resolvesPerTargetHealExpressionsAgainstTheCurrentTarget() {
        ScenarioState state = scenario(character(ALLY, 10));
        state.damageFigure(ALLY, 5);
        AbilityValue missingHealth = new AbilityValue(
            new IntExpression.FigureStatValue(
                new FigureReference.CurrentTarget(),
                IntExpression.FigureStat.MISSING_HEALTH
            ),
            EvaluationMoment.PER_TARGET
        );
        ScenarioEngine engine = engine(
            state,
            new HealAbility(missingHealth, oneAdjacentAlly())
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        HealTargetChoiceRequestedEvent requested = assertInstanceOf(
            HealTargetChoiceRequestedEvent.class,
            playResult.events().getFirst()
        );

        assertEquals(5, requested.previews().get(ALLY).healValue());
        assertEquals(5, requested.previews().get(ALLY).projectedHealing());

        engine.execute(new ChooseFigureTargetsCommand(List.of(ALLY)));

        assertEquals(10, state.figure(ALLY).health().currentHealth());
    }

    @Test
    void previewsExecutesAndRestoresAnAreaHeal() {
        ScenarioState state = scenario(character(ALLY, 10));
        state.damageFigure(ALLY, 5);
        TargetPlan.Area area = new TargetPlan.Area(
            new HexPattern(List.of(new HexPosition(1, 0))),
            new AreaPlacementRule.AroundOrigin(false, false),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.ALLY
            )
        );
        ScenarioEngine engine = engine(
            state,
            new HealAbility(AbilityValue.fixed(3), area)
        );

        CommandResult playResult = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        HealAreaPlacementRequestedEvent requested = assertInstanceOf(
            HealAreaPlacementRequestedEvent.class,
            playResult.events().getFirst()
        );
        assertEquals(3, requested.previews().get(ALLY).projectedHealing());

        CommandResult healResult = engine.execute(
            new ChooseAttackAreaCommand(
                requested.selection()
                    .placements()
                    .getFirst()
                    .option()
            )
        );

        assertTrue(healResult.accepted());
        assertEquals(8, state.figure(ALLY).health().currentHealth());

        engine.execute(new UndoCommand());

        assertEquals(5, state.figure(ALLY).health().currentHealth());
        assertInstanceOf(
            PendingAreaChoice.Heal.class,
            state.execution().pendingChoice()
        );
    }

    private static TargetPlan.Query oneAdjacentAlly() {
        return new TargetPlan.Query(
            new TargetQuery(
                new TargetRegion.AdjacentTo(
                    new TargetAnchor.AttackOrigin()
                ),
                new FigurePredicate.Relationship(
                    new FigureReference.Actor(),
                    FigurePredicate.FigureRelation.ALLY
                ),
                LineOfSightPolicy.REQUIRED
            ),
            new Cardinality.ChooseUpTo(new IntExpression.Constant(1)),
            DuplicatePolicy.DISTINCT_TARGETS
        );
    }

    private static ScenarioEngine engine(
        ScenarioState state,
        HealAbility heal
    ) {
        CardDefinition card = new CardDefinition(
            CARD_ID,
            "Test Heal",
            1,
            50,
            new CardAction(List.of(heal)),
            new CardAction(List.of())
        );
        return new ScenarioEngine(state, new CardCatalog(List.of(card)));
    }

    private static ScenarioState scenario(Figure ally) {
        HexPosition healerPosition = new HexPosition(0, 0);
        HexPosition allyPosition = new HexPosition(1, 0);
        ScenarioState state = new ScenarioState(new HexBoard(
            Map.of(
                healerPosition, new HexTile(TerrainType.NORMAL),
                allyPosition, new HexTile(TerrainType.NORMAL)
            ),
            Set.of()
        ));
        state.addFigure(character(HEALER, 10), healerPosition);
        state.addFigure(ally, allyPosition);
        state.setActiveFigure(HEALER);
        return state;
    }

    private static Figure withConditions(
        Figure figure,
        FigurePredicate.ConditionId... conditions
    ) {
        return new Figure(
            figure.id(),
            figure.definitionId(),
            figure.type(),
            figure.origin(),
            figure.factionId(),
            figure.health(),
            new ConditionState(List.of(conditions))
        );
    }
}
