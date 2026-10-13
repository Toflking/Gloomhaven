package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetAnchor;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetRegion;
import io.github.toflking.gloomhaven.rules.ability.effect.EffectDurationRule;
import io.github.toflking.gloomhaven.rules.ability.effect.RetaliateAbility;
import io.github.toflking.gloomhaven.rules.ability.effect.ShieldAbility;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.command.ChooseFigureTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.PlayCardActionCommand;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.event.AttackTargetChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.FigureDamagedEvent;
import io.github.toflking.gloomhaven.rules.event.FigureEffectsChangedEvent;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffect;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffectResolver;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.rules.scenario.TurnOrderEntry;
import io.github.toflking.gloomhaven.rules.scenario.history.ScenarioSnapshot;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.toflking.gloomhaven.rules.TestFigures.character;
import static io.github.toflking.gloomhaven.rules.TestFigures.monster;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActiveEffectIntegrationTest {
    private static final FigureId ATTACKER = new FigureId("attacker");
    private static final FigureId DEFENDER = new FigureId("defender");
    private static final FigureId ALLY = new FigureId("ally");
    private static final AbilitySource SOURCE = new AbilitySource(
        new AbilityCardId("active-effects"),
        CardHalf.TOP
    );

    @Test
    void resolvesDynamicRangeAuraFromCurrentPositions() {
        ScenarioState state = scenario(true);
        ActiveEffect.EffectScope adjacentAllies = new ActiveEffect.EffectScope(
            new TargetRegion.WithinRangeOf(
                new TargetAnchor.Actor(),
                new IntExpression.Constant(1)
            ),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.ALLY
            ),
            LineOfSightPolicy.IGNORED
        );
        state.addActiveEffect(
            source(ATTACKER, 0),
            new ActiveEffect.EffectDuration.EndOfRound(1),
            new ActiveEffect.Effect.Shield(2, adjacentAllies)
        );
        ActiveEffectResolver resolver = new ActiveEffectResolver(state);

        assertEquals(2, resolver.effectsFor(ALLY).shield());
        assertEquals(0, resolver.effectsFor(ATTACKER).shield());
        assertEquals(0, resolver.effectsFor(DEFENDER).shield());

        state.moveFigure(ALLY, new HexPosition(2, -1));

        assertEquals(0, resolver.effectsFor(ALLY).shield());
    }

    @Test
    void appliesShieldAndRetaliateToPreviewAndResolution() {
        ScenarioState state = scenario(false);
        ActiveEffect.EffectScope self = selfScope();
        state.addActiveEffect(
            source(DEFENDER, 0),
            new ActiveEffect.EffectDuration.EndOfRound(1),
            new ActiveEffect.Effect.Shield(2, self)
        );
        state.addActiveEffect(
            source(DEFENDER, 1),
            new ActiveEffect.EffectDuration.EndOfRound(1),
            new ActiveEffect.Effect.Retaliate(2, 1, self)
        );
        ScenarioEngine engine = attackEngine(state, 5);

        CommandResult play = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(2, requested.previews().get(DEFENDER).shield());
        assertEquals(3, requested.previews().get(DEFENDER).projectedDamage());
        assertEquals(2, requested.previews().get(DEFENDER).retaliateDamage());

        CommandResult resolved = engine.execute(
            new ChooseFigureTargetsCommand(List.of(DEFENDER))
        );

        List<FigureDamagedEvent> damageEvents = resolved.events().stream()
            .filter(FigureDamagedEvent.class::isInstance)
            .map(FigureDamagedEvent.class::cast)
            .toList();
        assertEquals(List.of(3, 2), damageEvents.stream()
            .map(FigureDamagedEvent::amount)
            .toList());
        assertEquals(7, state.figure(DEFENDER).health().currentHealth());
        assertEquals(8, state.figure(ATTACKER).health().currentHealth());
    }

    @Test
    void defeatedDefenderDoesNotRetaliate() {
        ScenarioState state = scenario(false);
        state.damageFigure(DEFENDER, 8);
        state.addActiveEffect(
            source(DEFENDER, 0),
            new ActiveEffect.EffectDuration.EndOfRound(1),
            new ActiveEffect.Effect.Retaliate(3, 1, selfScope())
        );
        ScenarioEngine engine = attackEngine(state, 3);

        CommandResult play = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(0, requested.previews().get(DEFENDER).retaliateDamage());

        engine.execute(new ChooseFigureTargetsCommand(List.of(DEFENDER)));

        assertEquals(10, state.figure(ATTACKER).health().currentHealth());
    }

    @Test
    void pierceReducesShieldInPreviewAndResolution() {
        ScenarioState state = scenario(false);
        state.addActiveEffect(
            source(DEFENDER, 0),
            new ActiveEffect.EffectDuration.EndOfRound(1),
            new ActiveEffect.Effect.Shield(2, selfScope())
        );
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(5),
            new ActionOrigin.Actor(),
            AttackTargetPlans.singleEnemyWithinRange(1),
            List.of(new AttackEffect.Pierce(
                new IntExpression.Constant(1),
                false
            ))
        );
        ScenarioEngine engine = attackEngine(state, attack);

        CommandResult play = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );
        AttackTargetChoiceRequestedEvent requested = assertInstanceOf(
            AttackTargetChoiceRequestedEvent.class,
            play.events().getFirst()
        );
        assertEquals(1, requested.previews().get(DEFENDER).effectiveShield());
        assertEquals(4, requested.previews().get(DEFENDER).projectedDamage());

        engine.execute(new ChooseFigureTargetsCommand(List.of(DEFENDER)));

        assertEquals(6, state.figure(DEFENDER).health().currentHealth());
    }

    @Test
    void effectAbilitiesPublishCompleteChangedFigureViews() {
        ScenarioState state = scenario(false);
        CardDefinition card = new CardDefinition(
            SOURCE.cardId(),
            "Shield and Retaliate",
            1,
            50,
            new CardAction(List.of(
                new ShieldAbility(
                    AbilityValue.fixed(1),
                    selfScope(),
                    EffectDurationRule.END_OF_ROUND
                ),
                new RetaliateAbility(
                    AbilityValue.fixed(2),
                    AbilityValue.fixed(1),
                    selfScope(),
                    EffectDurationRule.END_OF_ROUND
                )
            )),
            new CardAction(List.of())
        );
        ScenarioEngine engine = new ScenarioEngine(
            state,
            new CardCatalog(List.of(card))
        );

        CommandResult result = engine.execute(
            new PlayCardActionCommand(SOURCE)
        );

        FigureEffectsChangedEvent changed = result.events().stream()
            .filter(FigureEffectsChangedEvent.class::isInstance)
            .map(FigureEffectsChangedEvent.class::cast)
            .findFirst()
            .orElseThrow();
        assertEquals(1, changed.effects().get(ATTACKER).shield());
        assertEquals(
            2,
            changed.effects().get(ATTACKER).retaliates().getFirst().amount()
        );
    }

    @Test
    void snapshotsRestoreEffectsAndStartingNextRoundExpiresRoundEffects() {
        ScenarioState state = scenario(false);
        state.addActiveEffect(
            source(ATTACKER, 0),
            new ActiveEffect.EffectDuration.EndOfRound(1),
            new ActiveEffect.Effect.Shield(1, selfScope())
        );
        ScenarioSnapshot snapshot = state.snapshot();

        state.startNextRound(List.of(
            new TurnOrderEntry(ATTACKER, 50),
            new TurnOrderEntry(DEFENDER, 60)
        ));
        assertTrue(state.activeEffects().isEmpty());

        state.restore(snapshot);
        assertEquals(1, new ActiveEffectResolver(state)
            .effectsFor(ATTACKER)
            .shield());
    }

    private static ScenarioEngine attackEngine(
        ScenarioState state,
        int amount
    ) {
        return attackEngine(
            state,
            new AttackAbility(
                AbilityValue.fixed(amount),
                AttackTargetPlans.singleEnemyWithinRange(1)
            )
        );
    }

    private static ScenarioEngine attackEngine(
        ScenarioState state,
        AttackAbility attack
    ) {
        CardDefinition card = new CardDefinition(
            SOURCE.cardId(),
            "Attack",
            1,
            50,
            new CardAction(List.of(attack)),
            new CardAction(List.of())
        );
        return new ScenarioEngine(state, new CardCatalog(List.of(card)));
    }

    private static ActiveEffect.EffectSource source(
        FigureId actor,
        int abilityIndex
    ) {
        return new ActiveEffect.EffectSource(
            actor,
            SOURCE,
            abilityIndex
        );
    }

    private static ActiveEffect.EffectScope selfScope() {
        return new ActiveEffect.EffectScope(
            new TargetRegion.Anywhere(),
            new FigurePredicate.Relationship(
                new FigureReference.Actor(),
                FigurePredicate.FigureRelation.SELF
            ),
            LineOfSightPolicy.IGNORED
        );
    }

    private static ScenarioState scenario(boolean withAlly) {
        HexPosition attacker = new HexPosition(0, 0);
        HexPosition defender = new HexPosition(1, 0);
        HexPosition ally = new HexPosition(0, 1);
        HexPosition far = new HexPosition(2, -1);
        HexBoard board = new HexBoard(
            Map.of(
                attacker, new HexTile(TerrainType.NORMAL),
                defender, new HexTile(TerrainType.NORMAL),
                ally, new HexTile(TerrainType.NORMAL),
                far, new HexTile(TerrainType.NORMAL)
            ),
            Set.of()
        );
        ScenarioState state = new ScenarioState(board);
        state.addFigure(character(ATTACKER, 10), attacker);
        state.addFigure(monster(DEFENDER, 10), defender);
        if (withAlly) {
            state.addFigure(character(ALLY, 10), ally);
        }
        state.startNextRound(withAlly
            ? List.of(
                new TurnOrderEntry(ATTACKER, 50),
                new TurnOrderEntry(ALLY, 55),
                new TurnOrderEntry(DEFENDER, 60)
            )
            : List.of(
                new TurnOrderEntry(ATTACKER, 50),
                new TurnOrderEntry(DEFENDER, 60)
            ));
        return state;
    }
}
