package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.*;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.FigureType;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode.FIXED;
import static io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode.MANUAL;

public final class AttackTargetResolver {
    private final ScenarioState state;
    private final ActionExecution execution;
    private final HexPosition attackOrigin;
    private final AttackExpressionContext expressionContext;

    public AttackTargetResolver(
        ScenarioState state,
        ActionExecution execution
    ) {
        this.state = Objects.requireNonNull(state, "state");
        this.execution = Objects.requireNonNull(execution, "execution");
        this.attackOrigin = resolveAttackOrigin(
            execution.attackExecution().attack().origin()
        );
        this.expressionContext = new AttackExpressionContext(
            state,
            execution.frame(),
            execution.memory(),
            null,
            0
        );
    }

    public ResolvedTargetSelection resolve(
        AttackTargetPlan.Query plan
    ) {
        Set<FigureId> eligibleTargets = new LinkedHashSet<>();

        for (Figure figure : state.figures()) {
            HexPosition position = state.positionOf(figure.id());
            if (matchesRegion(plan.candidates().region(), position)
                && matchesPredicate(
                    plan.candidates().figures(),
                    figure
                )) {
                eligibleTargets.add(figure.id());
            }
        }

        int minimum;
        int maximum;
        TargetSelectionMode selectionMode;

        switch (plan.cardinality()) {
            case Cardinality.ChooseUpTo chooseUpTo -> {
                minimum = 0;
                maximum = requireNonNegative(
                    chooseUpTo.maximum().evaluate(expressionContext),
                    "Maximum target count"
                );
                selectionMode = MANUAL;
            }
            case Cardinality.ChooseExactly chooseExactly -> {
                int count = requireNonNegative(
                    chooseExactly.count().evaluate(expressionContext),
                    "Exact target count"
                );
                minimum = count;
                maximum = count;
                selectionMode = MANUAL;
            }
            case Cardinality.All ignored -> {
                minimum = eligibleTargets.size();
                maximum = eligibleTargets.size();
                selectionMode = FIXED;
            }
        }

        if (plan.duplicates() == DuplicatePolicy.DISTINCT_TARGETS
            && plan.cardinality() instanceof Cardinality.ChooseUpTo) {
            maximum = Math.min(maximum, eligibleTargets.size());
        }

        return new ResolvedTargetSelection(
            eligibleTargets,
            minimum,
            maximum,
            plan.duplicates(),
            selectionMode
        );
    }

    private boolean matchesRegion(
        TargetRegion region,
        HexPosition candidate
    ) {
        return switch (region) {
            case TargetRegion.Anywhere ignored -> true;
            case TargetRegion.AdjacentTo adjacent ->
                candidate.distanceTo(resolveAnchor(adjacent.anchor())) == 1;
            case TargetRegion.WithinRangeOf within ->
                candidate.distanceTo(resolveAnchor(within.anchor()))
                    <= requireNonNegative(
                    within.range().evaluate(expressionContext),
                    "Target range"
                );
            case TargetRegion.ExactRangeFrom exact ->
                candidate.distanceTo(resolveAnchor(exact.anchor()))
                    == requireNonNegative(
                    exact.range().evaluate(expressionContext),
                    "Exact target range"
                );
            case TargetRegion.SameRoomAs ignored ->
                throw new IllegalStateException(
                    "Same-room targeting requires room topology on the board"
                );
            case TargetRegion.OccupyingPath path ->
                execution.memory()
                    .requirePath(path.path())
                    .contains(candidate);
            case TargetRegion.AdjacentToHexSet adjacent ->
                execution.memory()
                    .requireHexes(adjacent.hexes())
                    .stream()
                    .anyMatch(hex -> candidate.distanceTo(hex) == 1);
            case TargetRegion.Intersection intersection ->
                intersection.regions()
                    .stream()
                    .allMatch(child -> matchesRegion(child, candidate));
            case TargetRegion.Union union ->
                union.regions()
                    .stream()
                    .anyMatch(child -> matchesRegion(child, candidate));
        };
    }

    private boolean matchesPredicate(
        FigurePredicate predicate,
        Figure candidate
    ) {
        return switch (predicate) {
            case FigurePredicate.Any ignored -> true;
            case FigurePredicate.Relationship relationship ->
                matchesRelationship(relationship, candidate);
            case FigurePredicate.Classification classification ->
                matchesClassification(
                    classification.classification(),
                    candidate
                );
            case FigurePredicate.HasCondition condition ->
                throw new IllegalStateException(
                    "Condition targeting is not available on Figure yet: "
                        + condition.condition().value()
                );
            case FigurePredicate.Contextual contextual ->
                throw new IllegalStateException(
                    "No resolver is registered for contextual target predicate: "
                        + contextual.predicate().value()
                );
            case FigurePredicate.And and ->
                and.predicates()
                    .stream()
                    .allMatch(child -> matchesPredicate(child, candidate));
            case FigurePredicate.Or or ->
                or.predicates()
                    .stream()
                    .anyMatch(child -> matchesPredicate(child, candidate));
            case FigurePredicate.Not not ->
                !matchesPredicate(not.predicate(), candidate);
        };
    }

    private boolean matchesRelationship(
        FigurePredicate.Relationship relationship,
        Figure candidate
    ) {
        FigureId referenceId = resolveFigure(
            relationship.reference()
        );
        Figure reference = state.figure(referenceId);

        return switch (relationship.relation()) {
            case SELF -> candidate.id().equals(referenceId);
            case NOT_SELF -> !candidate.id().equals(referenceId);
            case ALLY ->
                !candidate.id().equals(referenceId)
                    && candidate.type() == reference.type();
            case ENEMY -> candidate.type() != reference.type();
        };
    }

    private boolean matchesClassification(
        FigurePredicate.FigureClassification classification,
        Figure candidate
    ) {
        return switch (classification) {
            case CHARACTER -> candidate.type() == FigureType.CHARACTER;
            case MONSTER -> candidate.type() == FigureType.MONSTER;
            case NORMAL, ELITE, BOSS, SUMMON, FLYING ->
                throw new IllegalStateException(
                    "Figure classification is not available on Figure yet: "
                        + classification
                );
        };
    }

    private FigureId resolveFigure(FigureReference reference) {
        return switch (reference) {
            case FigureReference.Actor ignored -> execution.actor();
            case FigureReference.Owner ignored -> execution.owner();
            case FigureReference.Controller ignored ->
                execution.controller();
            case FigureReference.CurrentTarget ignored ->
                throw new IllegalStateException(
                    "There is no current target during initial target selection"
                );
            case FigureReference.Stored stored ->
                execution.memory().requireFigure(stored.variable());
        };
    }

    private HexPosition resolveAnchor(TargetAnchor anchor) {
        return switch (anchor) {
            case TargetAnchor.AttackOrigin ignored -> attackOrigin;
            case TargetAnchor.Actor ignored ->
                state.positionOf(execution.actor());
            case TargetAnchor.Owner ignored ->
                state.positionOf(execution.owner());
            case TargetAnchor.Controller ignored ->
                state.positionOf(execution.controller());
            case TargetAnchor.PreviousTarget ignored ->
                throw new IllegalStateException(
                    "A previous target is only available during a chained attack"
                );
            case TargetAnchor.StoredFigure stored ->
                state.positionOf(
                    execution.memory().requireFigure(stored.variable())
                );
            case TargetAnchor.StoredHex stored ->
                execution.memory().requireHex(stored.variable());
        };
    }

    private HexPosition resolveAttackOrigin(AttackOrigin origin) {
        return switch (origin) {
            case AttackOrigin.Actor ignored ->
                state.positionOf(execution.actor());
            case AttackOrigin.StoredFigure stored ->
                state.positionOf(
                    execution.memory().requireFigure(stored.variable())
                );
            case AttackOrigin.StoredHex stored ->
                execution.memory().requireHex(stored.variable());
            case AttackOrigin.Choose ignored ->
                throw new IllegalStateException(
                    "The attack origin must be chosen before resolving targets"
                );
        };
    }

    private static int requireNonNegative(
        int value,
        String label
    ) {
        if (value < 0) {
            throw new IllegalStateException(
                label + " resolved to a negative value"
            );
        }
        return value;
    }
}
