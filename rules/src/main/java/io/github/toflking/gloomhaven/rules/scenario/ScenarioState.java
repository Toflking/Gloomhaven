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

    private RoundState currentRound;
    private final List<ActiveEffect> activeEffects;
    private final Collection<ActiveEffect> activeEffectView;
    private long nextActiveEffectId;

    private ScenarioPhase phase;

    private ActionExecution execution;

    public ScenarioState(HexBoard board) {
        this.board = board;
        this.activeEffects = new ArrayList<>();
        this.activeEffectView = Collections.unmodifiableList(activeEffects);
        this.nextActiveEffectId = 1;
        this.figures = new HashMap<>();
        this.positions = new HashMap<>();
        this.figureView = Collections.unmodifiableCollection(figures.values());
        this.currentRound = null;
        this.phase = ScenarioPhase.TURN_EXECUTION;
    }

    public HexBoard board() {
        return board;
    }

    public ScenarioPhase phase() {
        return phase;
    }

    public RoundState currentRound() {
        return currentRound;
    }

    public Collection<ActiveEffect> activeEffects() {
        return activeEffectView;
    }

    public FigureEffectView effectsFor(FigureId figureId) {
        return new ActiveEffectResolver(this).effectsFor(figureId);
    }

    public Map<FigureId, FigureEffectView> figureEffectViews() {
        ActiveEffectResolver resolver = new ActiveEffectResolver(this);
        return figures.values().stream().collect(Collectors.toUnmodifiableMap(
            Figure::id,
            figure -> resolver.effectsFor(figure.id())
        ));
    }

    public ActiveEffect addActiveEffect(
        ActiveEffect.EffectSource source,
        ActiveEffect.EffectDuration duration,
        ActiveEffect.Effect effect
    ) {
        ActiveEffect activeEffect = new ActiveEffect(
            new ActiveEffect.ActiveEffectId(
                "effect-" + nextActiveEffectId++
            ),
            source,
            duration,
            effect
        );
        activeEffects.add(activeEffect);
        return activeEffect;
    }

    public List<ActiveEffect> removeExpiredEffects() {
        if (currentRound == null) {
            return List.of();
        }

        List<ActiveEffect> removed = activeEffects.stream()
            .filter(activeEffect ->
                activeEffect.duration()
                    instanceof ActiveEffect.EffectDuration.EndOfRound end
                    && end.round() <= currentRound.roundNumber()
            )
            .toList();
        activeEffects.removeAll(removed);
        return removed;
    }

    public List<ActiveEffect> removeEffectsFromCard(
        FigureId ownerId,
        AbilitySource source
    ) {
        Objects.requireNonNull(ownerId, "ownerId");
        Objects.requireNonNull(source, "source");
        List<ActiveEffect> removed = activeEffects.stream()
            .filter(activeEffect ->
                activeEffect.duration()
                    instanceof ActiveEffect.EffectDuration.WhileCardActive active
                    && active.ownerId().equals(ownerId)
                    && active.source().equals(source)
            )
            .toList();
        activeEffects.removeAll(removed);
        return removed;
    }

    public void startNextRound(List<TurnOrderEntry> turnOrder) {
        Objects.requireNonNull(turnOrder, "turnOrder");
        removeExpiredEffects();
        this.currentRound = new RoundState(
            currentRound == null
                ? 1
                : currentRound.roundNumber() + 1,
            turnOrder
        );
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
            activeEffects,
            nextActiveEffectId,
            Optional.ofNullable(currentRound)
                .map(RoundState::snapshot),
            phase,
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

        activeEffects.clear();
        activeEffects.addAll(snapshot.activeEffects());
        nextActiveEffectId = snapshot.nextActiveEffectId();

        currentRound = snapshot.currentRound()
            .map(RoundState::restore)
            .orElse(null);
        phase = snapshot.phase();
        execution = snapshot.execution()
            .map(ActionExecution::restore)
            .orElse(null);
    }
}
