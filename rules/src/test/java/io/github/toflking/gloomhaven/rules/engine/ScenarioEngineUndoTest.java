package io.github.toflking.gloomhaven.rules.engine;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.command.CancelActionCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseAttackTargetsCommand;
import io.github.toflking.gloomhaven.rules.command.ChooseMovePathCommand;
import io.github.toflking.gloomhaven.rules.command.PlayCardActionCommand;
import io.github.toflking.gloomhaven.rules.command.SkipActionCommand;
import io.github.toflking.gloomhaven.rules.command.UndoCommand;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.content.cards.DebugCards;
import io.github.toflking.gloomhaven.rules.event.MoveChoiceRequestedEvent;
import io.github.toflking.gloomhaven.rules.event.UndoPerformedEvent;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingAttackChoice;
import io.github.toflking.gloomhaven.rules.execution.choice.PendingMoveChoice;
import io.github.toflking.gloomhaven.rules.scenario.history.ScenarioSnapshot;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScenarioEngineUndoTest {
    private static final FigureId PLAYER = new FigureId("player");
    private static final FigureId MONSTER = new FigureId("monster");
    private static final HexPosition START = new HexPosition(0, 0);
    private static final HexPosition DESTINATION = new HexPosition(0, 1);
    private static final HexPosition SECOND_DESTINATION =
        new HexPosition(0, 2);
    private static final HexPosition MONSTER_HEX = new HexPosition(1, 0);

    @Test
    void undoRestoresMovementAndItsPendingChoice() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        AbilitySource moveSource = new AbilitySource(
            new AbilityCardId("debug-move-1"),
            CardHalf.TOP
        );

        engine.execute(new PlayCardActionCommand(moveSource));
        engine.execute(new ChooseMovePathCommand(
            List.of(START, DESTINATION)
        ));

        assertEquals(DESTINATION, state.positionOf(PLAYER));
        assertNull(state.execution());
        assertEquals(2, engine.undoDepth());

        CommandResult undoResult = engine.execute(new UndoCommand());

        assertTrue(undoResult.accepted());
        UndoPerformedEvent event = assertInstanceOf(
            UndoPerformedEvent.class,
            undoResult.events().getFirst()
        );
        assertInstanceOf(
            ChooseMovePathCommand.class,
            event.undoneCommand()
        );
        assertEquals(START, state.positionOf(PLAYER));
        assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
        assertEquals(1, engine.undoDepth());

        engine.execute(new UndoCommand());

        assertNull(state.execution());
        assertFalse(engine.canUndo());
    }

    @Test
    void undoRestoresDefeatedFigureAndPendingAttack() {
        ScenarioState state = scenario();
        AbilityCardId cardId = new AbilityCardId("fatal-attack");
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(20),
            AttackTargetPlans.singleEnemyWithinRange(1)
        );
        ScenarioEngine engine = new ScenarioEngine(
            state,
            new CardCatalog(List.of(new CardDefinition(
                cardId,
                "Fatal Attack",
                1,
                50,
                new CardAction(List.of(attack)),
                new CardAction(List.of())
            )))
        );

        engine.execute(new PlayCardActionCommand(
            new AbilitySource(cardId, CardHalf.TOP)
        ));
        engine.execute(new ChooseAttackTargetsCommand(
            List.of(MONSTER)
        ));

        assertFalse(state.hasFigure(MONSTER));

        engine.execute(new UndoCommand());

        assertTrue(state.hasFigure(MONSTER));
        assertEquals(10, state.figure(MONSTER).health());
        assertEquals(MONSTER_HEX, state.positionOf(MONSTER));
        assertInstanceOf(
            PendingAttackChoice.class,
            state.execution().pendingChoice()
        );
        assertFalse(
            state.execution()
                .attackExecution()
                .hasResolvedTargets()
        );
    }

    @Test
    void undoRestoresCancelledExecution() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        AbilitySource moveSource = new AbilitySource(
            new AbilityCardId("debug-move-1"),
            CardHalf.TOP
        );

        engine.execute(new PlayCardActionCommand(moveSource));
        engine.execute(new CancelActionCommand());
        assertNull(state.execution());

        engine.execute(new UndoCommand());

        assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
    }

    @Test
    void rejectedCommandsAreNotAddedToHistory() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);

        CommandResult rejected = engine.execute(
            new ChooseMovePathCommand(List.of(START, DESTINATION))
        );

        assertFalse(rejected.accepted());
        assertFalse(engine.canUndo());
        assertFalse(engine.execute(new UndoCommand()).accepted());
    }

    @Test
    void movementMustContainAtLeastOneStep() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        engine.execute(new PlayCardActionCommand(
            new AbilitySource(
                DebugCards.MOVE_2_ATTACK_3,
                CardHalf.TOP
            )
        ));

        CommandResult result = engine.execute(
            new ChooseMovePathCommand(List.of(START))
        );

        assertFalse(result.accepted());
        assertEquals(START, state.positionOf(PLAYER));
        assertTrue(state.execution().canCancel());
        assertFalse(state.execution().hasPerformedAbility());
        assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
    }

    @Test
    void movingAwayAndBackCountsAsCommittedMovement() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        engine.execute(new PlayCardActionCommand(
            new AbilitySource(
                DebugCards.MOVE_2_ATTACK_3,
                CardHalf.TOP
            )
        ));

        CommandResult result = engine.execute(
            new ChooseMovePathCommand(
                List.of(START, DESTINATION, START)
            )
        );

        assertTrue(result.accepted());
        assertEquals(START, state.positionOf(PLAYER));
        assertFalse(state.execution().canCancel());
        assertTrue(state.execution().hasPerformedAbility());
        assertInstanceOf(
            PendingAttackChoice.class,
            state.execution().pendingChoice()
        );
    }

    @Test
    void movementCanBeCommittedInFragments() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        engine.execute(new PlayCardActionCommand(
            new AbilitySource(
                new AbilityCardId("debug-move-2"),
                CardHalf.TOP
            )
        ));

        CommandResult firstFragment = engine.execute(
            new ChooseMovePathCommand(List.of(START, DESTINATION))
        );

        assertTrue(firstFragment.accepted());
        assertEquals(DESTINATION, state.positionOf(PLAYER));
        PendingMoveChoice remainingChoice = assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
        assertEquals(1, remainingChoice.movementPoints());
        assertFalse(state.execution().canCancel());
        MoveChoiceRequestedEvent nextChoice = assertInstanceOf(
            MoveChoiceRequestedEvent.class,
            firstFragment.events().getLast()
        );
        assertEquals(1, nextChoice.amount());

        CommandResult secondFragment = engine.execute(
            new ChooseMovePathCommand(List.of(
                DESTINATION,
                SECOND_DESTINATION
            ))
        );

        assertTrue(secondFragment.accepted());
        assertEquals(SECOND_DESTINATION, state.positionOf(PLAYER));
        assertNull(state.execution());
    }

    @Test
    void undoAndSkipPreserveFragmentedMovementState() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        engine.execute(new PlayCardActionCommand(
            new AbilitySource(
                new AbilityCardId("debug-move-2"),
                CardHalf.TOP
            )
        ));
        engine.execute(new ChooseMovePathCommand(
            List.of(START, DESTINATION)
        ));

        engine.execute(new SkipActionCommand());
        assertNull(state.execution());

        engine.execute(new UndoCommand());
        assertEquals(DESTINATION, state.positionOf(PLAYER));
        PendingMoveChoice afterSkipUndo = assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
        assertEquals(1, afterSkipUndo.movementPoints());
        assertFalse(state.execution().canCancel());

        engine.execute(new UndoCommand());
        assertEquals(START, state.positionOf(PLAYER));
        PendingMoveChoice afterFragmentUndo = assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
        assertEquals(2, afterFragmentUndo.movementPoints());
        assertTrue(state.execution().canCancel());
        assertFalse(state.execution().hasPerformedAbility());
    }

    @Test
    void skippingLocksCancellationWithoutPerformingAnAbility() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        engine.execute(new PlayCardActionCommand(
            new AbilitySource(
                DebugCards.MOVE_2_ATTACK_3,
                CardHalf.TOP
            )
        ));

        engine.execute(new SkipActionCommand());

        assertFalse(state.execution().canCancel());
        assertFalse(state.execution().hasPerformedAbility());
        assertInstanceOf(
            PendingAttackChoice.class,
            state.execution().pendingChoice()
        );

        engine.execute(new UndoCommand());

        assertTrue(state.execution().canCancel());
        assertFalse(state.execution().hasPerformedAbility());
        assertInstanceOf(
            PendingMoveChoice.class,
            state.execution().pendingChoice()
        );
    }

    @Test
    void snapshotDeepCopiesFiguresAndExecutionMemory() {
        ScenarioState state = scenario();
        ScenarioEngine engine = debugEngine(state);
        AbilitySource moveSource = new AbilitySource(
            new AbilityCardId("debug-move-1"),
            CardHalf.TOP
        );
        engine.execute(new PlayCardActionCommand(moveSource));

        ExecutionVariableId amount =
            new ExecutionVariableId("remembered-amount");
        ExecutionVariableId path =
            new ExecutionVariableId("remembered-path");
        state.execution().memory().putInteger(amount, 3);
        state.execution().memory().putPath(
            path,
            List.of(START, DESTINATION)
        );
        Figure originalFigure = state.figure(MONSTER);
        ScenarioSnapshot snapshot = state.snapshot();

        state.damageFigure(MONSTER, 4);
        state.execution().memory().putInteger(amount, 9);
        state.restore(snapshot);

        assertEquals(10, state.figure(MONSTER).health());
        assertNotSame(originalFigure, state.figure(MONSTER));
        assertEquals(
            3,
            state.execution().memory().requireInteger(amount)
        );
        assertEquals(
            List.of(START, DESTINATION),
            state.execution().memory().requirePath(path)
        );
    }

    private static ScenarioEngine debugEngine(ScenarioState state) {
        return new ScenarioEngine(
            state,
            new CardCatalog(DebugCards.all())
        );
    }

    private static ScenarioState scenario() {
        HexTile normal = new HexTile(TerrainType.NORMAL);
        ScenarioState state = new ScenarioState(new HexBoard(
            Map.of(
                START, normal,
                DESTINATION, normal,
                SECOND_DESTINATION, normal,
                MONSTER_HEX, normal
            ),
            Set.of()
        ));
        state.addFigure(
            new Figure(PLAYER, FigureType.CHARACTER, 10),
            START
        );
        state.addFigure(
            new Figure(MONSTER, FigureType.MONSTER, 10),
            MONSTER_HEX
        );
        state.setActiveFigure(PLAYER);
        return state;
    }
}
