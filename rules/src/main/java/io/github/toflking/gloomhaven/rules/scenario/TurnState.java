package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

public final class TurnState {

    private final FigureId actingFigure;
    private TurnPhase phase;

    public TurnState(FigureId actingFigure) {
        this.actingFigure = actingFigure;
        this.phase = TurnPhase.CHOOSE_FIRST_CARD_HALF;
    }
}
