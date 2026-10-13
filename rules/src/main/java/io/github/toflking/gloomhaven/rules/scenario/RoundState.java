package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class RoundState {
    private final int roundNumber;
    private final List<TurnOrderEntry> turnOrder;
    private int currentTurnIndex;

    public RoundState(int roundNumber, List<TurnOrderEntry> turnOrder) {
        this(roundNumber, turnOrder, 0);
    }

    private RoundState(
        int roundNumber,
        List<TurnOrderEntry> turnOrder,
        int currentTurnIndex
    ) {
        if (roundNumber < 1) {
            throw new IllegalArgumentException(
                "Round number must be positive"
            );
        }

        this.roundNumber = roundNumber;
        this.turnOrder = List.copyOf(
            Objects.requireNonNull(turnOrder, "turnOrder")
        );
        if (currentTurnIndex < 0
            || currentTurnIndex > this.turnOrder.size()) {
            throw new IllegalArgumentException(
                "Current turn index is outside the turn order"
            );
        }
        this.currentTurnIndex = currentTurnIndex;
    }

    public int roundNumber() {
        return roundNumber;
    }

    public List<TurnOrderEntry> turnOrder() {
        return turnOrder;
    }

    public int currentTurnIndex() {
        return currentTurnIndex;
    }

    /**
     * Returns the index for a given figure id from the turn order. If the figure is not present it returns -1
     * @param figureId the figure of which you try to get the index
     * @return index of the figure or -1 if not present
     */
    public int turnIndexForFigure(FigureId figureId) {
        return IntStream.range(0, turnOrder.size()).filter(index -> turnOrder.get(index).figureId().equals(figureId)).findFirst().orElse(-1);
    }

    public FigureId activeFigure() {
        return turnOrder.get(currentTurnIndex).figureId();
    }

    public void continueToNextFigure() {
        currentTurnIndex++;
    }

    public Snapshot snapshot() {
        return new Snapshot(
            roundNumber,
            turnOrder,
            currentTurnIndex
        );
    }

    public static RoundState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");
        return new RoundState(
            snapshot.roundNumber(),
            snapshot.turnOrder(),
            snapshot.currentTurnIndex()
        );
    }

    public record Snapshot(
        int roundNumber,
        List<TurnOrderEntry> turnOrder,
        int currentTurnIndex
    ) {

        public Snapshot {
            if (roundNumber < 1) {
                throw new IllegalArgumentException(
                    "Round number must be positive"
                );
            }
            turnOrder = List.copyOf(
                Objects.requireNonNull(turnOrder, "turnOrder")
            );
            if (currentTurnIndex < 0
                || currentTurnIndex > turnOrder.size()) {
                throw new IllegalArgumentException(
                    "Current turn index is outside the turn order"
                );
            }
        }
    }
}
