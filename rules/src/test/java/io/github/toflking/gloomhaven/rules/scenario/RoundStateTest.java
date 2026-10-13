package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.figure.FigureId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class RoundStateTest {

    @Test
    void findsAFigureByValueEquivalentId() {
        RoundState round = new RoundState(
            1,
            List.of(new TurnOrderEntry(new FigureId("figure"), 50))
        );

        assertEquals(
            0,
            round.turnIndexForFigure(new FigureId("figure"))
        );
    }

    @Test
    void snapshotRestoresTheCurrentTurnWithoutSharingMutableState() {
        FigureId first = new FigureId("first");
        FigureId second = new FigureId("second");
        RoundState round = new RoundState(
            3,
            List.of(
                new TurnOrderEntry(first, 20),
                new TurnOrderEntry(second, 40)
            )
        );
        RoundState.Snapshot snapshot = round.snapshot();

        round.continueToNextFigure();
        RoundState restored = RoundState.restore(snapshot);

        assertNotSame(round, restored);
        assertEquals(3, restored.roundNumber());
        assertEquals(0, restored.currentTurnIndex());
        assertEquals(first, restored.activeFigure());
    }
}
