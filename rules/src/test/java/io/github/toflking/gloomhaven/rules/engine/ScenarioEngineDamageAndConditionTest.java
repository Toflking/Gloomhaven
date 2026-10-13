package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.ability.condition.ConditionAbility;
import io.github.toflking.gloomhaven.rules.ability.damage.DamageAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.DamageEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.TargetEffectTiming;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
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
import io.github.toflking.gloomhaven.rules.conditions.ActiveCondition;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.event.ConditionAppliedEvent;
import io.github.toflking.gloomhaven.rules.event.ConditionRemovedEvent;
import io.github.toflking.gloomhaven.rules.event.ConditionAreaPlacementRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.ConditionTargetChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.DamageAreaPlacementRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.DamageTargetChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.FigureDamagedEvent;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAreaChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingFigureTargetChoice;
import io.github.toflking.gloomhaven.rules.figure.ConditionState;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.rules.scenario.TurnOrderEntry;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.toflking.gloomhaven.rules.TestFigures.character;
import static io.github.toflking.gloomhaven.rules.TestFigures.monster;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioEngineDamageAndConditionTest {
    private static final FigureId ACTOR = new FigureId("actor");
    private static final FigureId TARGET = new FigureId("target");
    private static final AbilityCardId CARD = new AbilityCardId("test-action");
    private static final AbilitySource SOURCE =
        new AbilitySource(CARD, CardHalf.TOP);

    @Test
    void directDamagePreviewAndExecutionIgnorePoison() {
        ScenarioState state = scenario(poisonedMonster());
        ScenarioEngine engine = engine(
            state,
            new DamageAbility(
                AbilityValue.fixed(3),
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        DamageTargetChoiceRequestedEvent requested = assertInstanceOf(
            DamageTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(3, requested.previews().get(TARGET).projectedDamage());

        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );
        assertEquals(
            3,
            assertInstanceOf(
                FigureDamagedEvent.class,
                result.events().getFirst()
            ).amount()
        );
        assertEquals(7, state.figure(TARGET).health().currentHealth());

        engine.execute(new UndoCommand());
        assertEquals(10, state.figure(TARGET).health().currentHealth());
        assertInstanceOf(
            PendingFigureTargetChoice.Damage.class,
            state.execution().pendingChoice()
        );
    }

    @Test
    void directDamageSupportsAreaPlacement() {
        ScenarioState state = scenario(invisibleMonster(10));
        ScenarioEngine engine = engine(
            state,
            new DamageAbility(AbilityValue.fixed(2), areaTargeting())
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        DamageAreaPlacementRequestedEvent requested = assertInstanceOf(
            DamageAreaPlacementRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(2, requested.previews().get(TARGET).projectedDamage());

        engine.execute(new ChooseAttackAreaCommand(
            requested.selection().placements().getFirst().option()
        ));
        assertEquals(8, state.figure(TARGET).health().currentHealth());
    }

    @Test
    void directDamagePreviewsAppliesAndRestoresTimedConditionEffects() {
        ScenarioState state = scenario(monster(TARGET, 10));
        ScenarioEngine engine = engine(
            state,
            new DamageAbility(
                AbilityValue.fixed(3),
                new ActionOrigin.Actor(),
                AttackTargetPlans.singleEnemyWithinRange(1),
                List.of(
                    new DamageEffect.ApplyCondition(
                        Conditions.POISON,
                        TargetEffectTiming.BEFORE_DAMAGE,
                        false
                    ),
                    new DamageEffect.ApplyCondition(
                        Conditions.WOUND,
                        TargetEffectTiming.AFTER_DAMAGE,
                        false
                    )
                )
            )
        );

        CommandResult play = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        DamageTargetChoiceRequestedEvent requested = assertInstanceOf(
            DamageTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(
            List.of(Conditions.POISON.id(), Conditions.WOUND.id()),
            requested.previews()
                .get(TARGET)
                .effects()
                .addedConditions()
        );

        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        assertEquals(7, state.figure(TARGET).health().currentHealth());
        assertTrue(state.figure(TARGET).conditions()
            .contains(Conditions.POISON.id()));
        assertTrue(state.figure(TARGET).conditions()
            .contains(Conditions.WOUND.id()));
        assertInstanceOf(ConditionAppliedEvent.class, result.events().get(0));
        assertInstanceOf(FigureDamagedEvent.class, result.events().get(1));
        assertInstanceOf(ConditionAppliedEvent.class, result.events().get(2));

        engine.execute(new UndoCommand());

        assertEquals(10, state.figure(TARGET).health().currentHealth());
        assertTrue(state.figure(TARGET).conditions().conditions().isEmpty());
    }

    @Test
    void conditionActionPreviewsAndAppliesEveryCondition() {
        ScenarioState state = scenario(monster(TARGET, 10));
        ScenarioEngine engine = engine(
            state,
            new ConditionAbility(
                List.of(Conditions.POISON, Conditions.WOUND),
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        ConditionTargetChoiceRequestedEvent requested = assertInstanceOf(
            ConditionTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(
            List.of(Conditions.POISON.id(), Conditions.WOUND.id()),
            requested.previews()
                .get(TARGET)
                .effects()
                .addedConditions()
        );

        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );
        assertTrue(state.figure(TARGET).conditions()
            .contains(Conditions.POISON.id()));
        assertTrue(state.figure(TARGET).conditions()
            .contains(Conditions.WOUND.id()));
        assertEquals(
            2,
            result.events().stream()
                .filter(ConditionAppliedEvent.class::isInstance)
                .count()
        );

        engine.execute(new UndoCommand());
        assertInstanceOf(
            PendingFigureTargetChoice.Condition.class,
            state.execution().pendingChoice()
        );
    }

    @Test
    void conditionImmunitySuppressesApplicationAndPreview() {
        ScenarioState state = scenario(immuneMonster(
            10,
            Conditions.POISON.id()
        ));
        ScenarioEngine engine = engine(
            state,
            new ConditionAbility(
                List.of(Conditions.POISON, Conditions.WOUND),
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        ConditionTargetChoiceRequestedEvent requested = assertInstanceOf(
            ConditionTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(
            List.of(Conditions.WOUND.id()),
            requested.previews()
                .get(TARGET)
                .effects()
                .addedConditions()
        );

        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        assertFalse(state.figure(TARGET).conditions()
            .contains(Conditions.POISON.id()));
        assertTrue(state.figure(TARGET).conditions()
            .contains(Conditions.WOUND.id()));
        assertEquals(
            1,
            result.events().stream()
                .filter(ConditionAppliedEvent.class::isInstance)
                .count()
        );

        engine.execute(new UndoCommand());
        assertTrue(state.figure(TARGET).isImmuneTo(Conditions.POISON.id()));
    }

    @Test
    void directDamageCanRemoveAConditionBeforeDamage() {
        ScenarioState state = scenario(poisonedMonster());
        ScenarioEngine engine = engine(
            state,
            new DamageAbility(
                AbilityValue.fixed(3),
                new ActionOrigin.Actor(),
                AttackTargetPlans.singleEnemyWithinRange(1),
                List.of(new DamageEffect.RemoveCondition(
                    Conditions.POISON.id(),
                    TargetEffectTiming.BEFORE_DAMAGE,
                    false
                ))
            )
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        DamageTargetChoiceRequestedEvent requested = assertInstanceOf(
            DamageTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(
            List.of(Conditions.POISON.id()),
            requested.previews()
                .get(TARGET)
                .effects()
                .removedConditions()
        );

        CommandResult result = engine.execute(
            new ChooseFigureTargetsCommand(List.of(TARGET))
        );

        assertFalse(state.figure(TARGET).conditions()
            .contains(Conditions.POISON.id()));
        assertInstanceOf(ConditionRemovedEvent.class, result.events().get(0));
        assertInstanceOf(FigureDamagedEvent.class, result.events().get(1));
    }

    @Test
    void lethalDamagePreviewOmitsAfterDamageConditions() {
        ScenarioState state = scenario(monster(TARGET, 3));
        ScenarioEngine engine = engine(
            state,
            new DamageAbility(
                AbilityValue.fixed(3),
                new ActionOrigin.Actor(),
                AttackTargetPlans.singleEnemyWithinRange(1),
                List.of(
                    new DamageEffect.ApplyCondition(
                        Conditions.POISON,
                        TargetEffectTiming.BEFORE_DAMAGE,
                        false
                    ),
                    new DamageEffect.ApplyCondition(
                        Conditions.WOUND,
                        TargetEffectTiming.AFTER_DAMAGE,
                        false
                    )
                )
            )
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        DamageTargetChoiceRequestedEvent requested = assertInstanceOf(
            DamageTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );

        assertEquals(
            List.of(Conditions.POISON.id()),
            requested.previews()
                .get(TARGET)
                .effects()
                .addedConditions()
        );
    }

    @Test
    void conditionActionSupportsAreaPlacement() {
        ScenarioState state = scenario(monster(TARGET, 10));
        ScenarioEngine engine = engine(
            state,
            new ConditionAbility(List.of(Conditions.MUDDLE), areaTargeting())
        );

        CommandResult play = engine.execute(new PlayCardActionCommand(SOURCE));
        ConditionAreaPlacementRequestedEvent requested = assertInstanceOf(
            ConditionAreaPlacementRequestedEvent.class,
            play.events().getFirst()
        );

        engine.execute(new ChooseAttackAreaCommand(
            requested.selection().placements().getFirst().option()
        ));
        assertTrue(state.figure(TARGET).conditions()
            .contains(Conditions.MUDDLE.id()));

        engine.execute(new UndoCommand());
        assertInstanceOf(
            PendingAreaChoice.Condition.class,
            state.execution().pendingChoice()
        );
    }

    private static TargetPlan.Area areaTargeting() {
        return new TargetPlan.Area(
            new HexPattern(List.of(new HexPosition(1, 0))),
            new AreaPlacementRule.AroundOrigin(false, false),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.ENEMY
            )
        );
    }

    private static ScenarioEngine engine(
        ScenarioState state,
        Ability ability
    ) {
        CardDefinition card = new CardDefinition(
            CARD,
            "Test action",
            1,
            50,
            new CardAction(List.of(ability)),
            new CardAction(List.of())
        );
        return new ScenarioEngine(state, new CardCatalog(List.of(card)));
    }

    private static ScenarioState scenario(Figure target) {
        HexPosition actorPosition = new HexPosition(0, 0);
        HexPosition targetPosition = new HexPosition(1, 0);
        ScenarioState state = new ScenarioState(new HexBoard(
            Map.of(
                actorPosition, new HexTile(TerrainType.NORMAL),
                targetPosition, new HexTile(TerrainType.NORMAL)
            ),
            Set.of()
        ));
        state.addFigure(character(ACTOR, 10), actorPosition);
        state.addFigure(target, targetPosition);
        state.startNextRound(List.of(
            new TurnOrderEntry(ACTOR, 50),
            new TurnOrderEntry(TARGET, 60)
        ));
        return state;
    }

    private static Figure poisonedMonster() {
        Figure figure = monster(TARGET, 10);
        return new Figure(
            figure.id(),
            figure.definitionId(),
            figure.type(),
            figure.origin(),
            figure.factionId(),
            figure.health(),
            new ConditionState(Map.of(
                Conditions.POISON.id(),
                new ActiveCondition(
                    Conditions.POISON.id(),
                    new ActiveCondition.ConditionExpiration.UntilRemoved()
                )
            ))
        );
    }

    private static Figure invisibleMonster(int maximumHealth) {
        Figure figure = monster(TARGET, maximumHealth);
        return new Figure(
            figure.id(),
            figure.definitionId(),
            figure.type(),
            figure.origin(),
            figure.factionId(),
            figure.health(),
            new ConditionState(Map.of(
                Conditions.INVISIBLE.id(),
                new ActiveCondition(
                    Conditions.INVISIBLE.id(),
                    new ActiveCondition.ConditionExpiration.EndOfTurnInRound(1)
                )
            )),
            Set.of()
        );
    }

    private static Figure immuneMonster(
        int maximumHealth,
        Conditions.ConditionId... immunities
    ) {
        Figure figure = monster(TARGET, maximumHealth);
        return new Figure(
            figure.id(),
            figure.definitionId(),
            figure.type(),
            figure.origin(),
            figure.factionId(),
            figure.health(),
            figure.conditions(),
            Set.of(immunities)
        );
    }
}
