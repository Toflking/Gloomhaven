package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

import java.util.List;

public final class MovePathInteraction implements ScenarioInteractionState {
    private final FigureId figureId;
    private final int amount;
    private final MovementType type;

    private List<HexPosition> path;

    public MovePathInteraction(int amount, FigureId figureId, MovementType type) {
        this.amount = amount;
        this.figureId = figureId;
        this.type = type;
        this.path = List.of();
    }

    public int getAmount() {
        return amount;
    }

    public FigureId getFigureId() {
        return figureId;
    }

    public List<HexPosition> getPath() {
        return path;
    }

    public void setPath(List<HexPosition> path) {
        this.path = path;
    }

    public MovementType getType() {
        return type;
    }
}
