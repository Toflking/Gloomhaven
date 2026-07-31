package io.github.toflking.gloomhaven.rules.scenario.history;

import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioPhase;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public record ScenarioSnapshot(
    Map<FigureId, FigureSnapshot> figures,
    Map<FigureId, HexPosition> positions,
    ScenarioPhase phase,
    Optional<FigureId> activeFigure,
    Optional<ActionExecution.Snapshot> execution
) {

    public ScenarioSnapshot {
        Map<FigureId, FigureSnapshot> copiedFigures =
            Map.copyOf(figures);
        Map<FigureId, HexPosition> copiedPositions =
            Map.copyOf(positions);
        Objects.requireNonNull(phase, "phase");
        Objects.requireNonNull(activeFigure, "activeFigure");
        Objects.requireNonNull(execution, "execution");

        if (!copiedFigures.keySet().equals(copiedPositions.keySet())) {
            throw new IllegalArgumentException(
                "Every snapshot figure requires exactly one position"
            );
        }
        if (new HashSet<>(copiedPositions.values()).size()
            != copiedPositions.size()) {
            throw new IllegalArgumentException(
                "Snapshot figures cannot share a position"
            );
        }
        activeFigure.ifPresent(figureId -> {
            if (!copiedFigures.containsKey(figureId)) {
                throw new IllegalArgumentException(
                    "The active figure is missing from the snapshot"
                );
            }
        });

        figures = copiedFigures;
        positions = copiedPositions;
    }

    public record FigureSnapshot(
        FigureId id,
        FigureType type,
        int maximumHealth,
        int health
    ) {

        public FigureSnapshot {
            Objects.requireNonNull(id, "id");
            Objects.requireNonNull(type, "type");
            if (maximumHealth < 0) {
                throw new IllegalArgumentException(
                    "Maximum health cannot be negative"
                );
            }
            if (health < 0 || health > maximumHealth) {
                throw new IllegalArgumentException(
                    "Health must be within the figure health range"
                );
            }
        }

        public static FigureSnapshot from(Figure figure) {
            return new FigureSnapshot(
                figure.id(),
                figure.type(),
                figure.maximumHealth(),
                figure.health()
            );
        }

        public Figure restore() {
            return new Figure(id, type, maximumHealth, health);
        }
    }
}
