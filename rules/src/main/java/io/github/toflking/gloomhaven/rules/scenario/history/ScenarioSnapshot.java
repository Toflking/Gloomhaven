package io.github.toflking.gloomhaven.rules.scenario.history;

import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.figure.*;
import io.github.toflking.gloomhaven.rules.figure.identities.FigureIdentity;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.scenario.RoundState;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioPhase;
import io.github.toflking.gloomhaven.rules.scenario.ActiveEffect;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public record ScenarioSnapshot(
    Map<FigureId, FigureSnapshot> figures,
    Map<FigureId, HexPosition> positions,
    List<ActiveEffect> activeEffects,
    long nextActiveEffectId,
    Optional<RoundState.Snapshot> currentRound,
    ScenarioPhase phase,
    Optional<ActionExecution.Snapshot> execution
) {

    public ScenarioSnapshot {
        Map<FigureId, FigureSnapshot> copiedFigures =
            Map.copyOf(figures);
        Map<FigureId, HexPosition> copiedPositions =
            Map.copyOf(positions);
        activeEffects = List.copyOf(activeEffects);
        if (nextActiveEffectId < 1) {
            throw new IllegalArgumentException(
                "Next active effect ID must be positive"
            );
        }
        Objects.requireNonNull(currentRound, "currentRound");
        Objects.requireNonNull(phase, "phase");
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

        figures = copiedFigures;
        positions = copiedPositions;
    }

    public record FigureSnapshot(
        FigureId id,
        FigureDefinitionId definitionId,
        FigureIdentity identity,
        FigureOrigin origin,
        FactionId factionId,
        HealthState health,
        ConditionState conditions,
        Set<Conditions.ConditionId> conditionImmunities
    ) {

        public FigureSnapshot {
            Objects.requireNonNull(id, "id");
            Objects.requireNonNull(definitionId, "definitionId");
            Objects.requireNonNull(identity, "identity");
            Objects.requireNonNull(origin, "origin");
            Objects.requireNonNull(factionId, "factionId");
            Objects.requireNonNull(health, "health");
            Objects.requireNonNull(conditions, "conditions");
            conditionImmunities = Set.copyOf(
                Objects.requireNonNull(
                    conditionImmunities,
                    "conditionImmunities"
                )
            );
            if (health.maximumHealth() <= 0) {
                throw new IllegalArgumentException(
                    "Maximum health cannot be negative"
                );
            }
            if (health.currentHealth() < 0 || health.currentHealth() > health.maximumHealth()) {
                throw new IllegalArgumentException(
                    "Health must be within the figure health range"
                );
            }
        }

        public static FigureSnapshot from(Figure figure) {
            return new FigureSnapshot(
                figure.id(),
                figure.definitionId(),
                figure.type(),
                figure.origin(),
                figure.factionId(),
                new HealthState(figure.health().maximumHealth(), figure.health().currentHealth()),
                new ConditionState(figure.conditions().conditions()),
                figure.conditionImmunities()
            );
        }

        public Figure restore() {
            return new Figure(
                id,
                definitionId,
                identity,
                origin,
                factionId,
                health,
                conditions,
                conditionImmunities
            );
        }
    }
}
