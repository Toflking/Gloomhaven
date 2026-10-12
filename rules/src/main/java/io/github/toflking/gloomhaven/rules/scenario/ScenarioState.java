package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.ability.Ability;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.execution.AbilityExecutionFrame;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.scenario.history.ScenarioSnapshot;

import java.util.*;
import java.util.stream.Collectors;

public class ScenarioState {
    private final HexBoard board;
    private final Map<FigureId, Figure> figures;
    private final Map<FigureId, HexPosition> positions;
    private final Collection<Figure> figureView;

    private ScenarioPhase phase;
    private FigureId activeFigure;

    private ActionExecution execution;

    public ScenarioState(HexBoard board) {
        this.board = board;
        this.figures = new HashMap<>();
        this.positions = new HashMap<>();
        this.figureView = Collections.unmodifiableCollection(figures.values());
        this.phase = ScenarioPhase.TURN_EXECUTION;
    }

    public HexBoard board() {
        return board;
    }

    public ScenarioPhase phase() {
        return phase;
    }

    public FigureId activeFigure() {
        return activeFigure;
    }

    public void addFigure(Figure figure, HexPosition position) {
        if (!board.contains(position)) {
            throw new IllegalArgumentException(
                "Position is outside the board"
            );
        }

        if (figureAt(position).isPresent()) {
            throw new IllegalStateException(
                "Position is already occupied"
            );
        }
        figures.put(figure.id(), figure);
        positions.put(figure.id(), position);
    }

    public void removeFigure(FigureId figureId) {
        figures.remove(figureId);
        positions.remove(figureId);
    }

    public Figure figure(FigureId id) {
        Figure figure = figures.get(id);

        if (figure == null) {
            throw new IllegalArgumentException(
                "Unknown figure: " + id
            );
        }

        return figure;
    }

    public boolean hasFigure(FigureId id) {
        return figures.containsKey(id);
    }

    public Collection<Figure> figures() {
        return figureView;
    }

    public Map<HexPosition, Figure> figuresByPosition() {
        return positions.entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                Map.Entry::getValue,
                entry -> figures.get(entry.getKey())
            ));
    }

    public Optional<Figure> figureAt(HexPosition position) {
        return positions.entrySet()
            .stream()
            .filter(entry -> entry.getValue().equals(position))
            .map(entry -> figures.get(entry.getKey()))
            .findFirst();
    }

    public HexPosition positionOf(FigureId figureId) {
        return positions.get(figureId);
    }

    public void moveFigure(FigureId figureId, HexPosition destination) {
        positions.put(figureId, destination);
    }

    public void damageFigure(FigureId figureId, int amount) {
        Figure figure = figure(figureId);
        figure.takeDamage(amount);
    }

    public void healFigure(FigureId figureId, int amount) {
        Figure figure = figure(figureId);
        figure.heal(amount);
    }

    public void setActiveFigure(FigureId figureId) {
        if (!figures.containsKey(figureId)) {
            throw new IllegalArgumentException(
                "Unknown active figure: " + figureId
            );
        }

        activeFigure = figureId;
    }

    public ActionExecution execution() {
        return execution;
    }

    public void startActionExecution(FigureId actor, AbilitySource source, List<Ability> abilities) {
        startActionExecution(
            AbilityExecutionFrame.selfControlled(actor, source),
            abilities
        );
    }

    public void startActionExecution(
        AbilityExecutionFrame frame,
        List<Ability> abilities
    ) {
        if (execution != null) {
            throw new IllegalStateException(
                "An action execution is already active"
            );
        }
        this.execution = new ActionExecution(frame, abilities);
    }

    public void finishActionExecution() {
        this.execution = null;
    }

    public ScenarioSnapshot snapshot() {
        Map<FigureId, ScenarioSnapshot.FigureSnapshot> figureSnapshots =
            figures.values()
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                    Figure::id,
                    ScenarioSnapshot.FigureSnapshot::from
                ));

        return new ScenarioSnapshot(
            figureSnapshots,
            positions,
            phase,
            Optional.ofNullable(activeFigure),
            Optional.ofNullable(execution)
                .map(ActionExecution::snapshot)
        );
    }

    public void restore(ScenarioSnapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");

        snapshot.positions().values().forEach(position -> {
            if (!board.contains(position)) {
                throw new IllegalArgumentException(
                    "Snapshot position is outside the board: "
                        + position
                );
            }
        });

        figures.clear();
        snapshot.figures().forEach((figureId, figureSnapshot) ->
            figures.put(figureId, figureSnapshot.restore())
        );

        positions.clear();
        positions.putAll(snapshot.positions());

        phase = snapshot.phase();
        activeFigure = snapshot.activeFigure().orElse(null);
        execution = snapshot.execution()
            .map(ActionExecution::restore)
            .orElse(null);
    }
}
