package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.*;
import io.github.toflking.gloomhaven.rules.board.HexPattern;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.LineOfSightResolver;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.identities.*;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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

    public AttackChoice resolve(AttackTargetPlan plan) {
        Objects.requireNonNull(plan, "plan");

        return switch (plan) {
            case AttackTargetPlan.Query query -> resolveQuery(query);
            case AttackTargetPlan.Area area -> resolveArea(area);
            case AttackTargetPlan.PrimaryAndDerived ignored ->
                throw unsupported(plan);
            case AttackTargetPlan.Chain ignored ->
                throw unsupported(plan);
        };
    }

    private AttackChoice.FigureTargets resolveQuery(
        AttackTargetPlan.Query plan
    ) {
        Set<FigureId> eligibleTargets = new LinkedHashSet<>();

        for (Figure figure : state.figures()) {
            HexPosition position = state.positionOf(figure.id());
            if (matchesRegion(plan.candidates().region(), position)
                && matchesLineOfSight(
                    plan.candidates().lineOfSight(),
                    position
                )
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

        return new AttackChoice.FigureTargets(
            eligibleTargets,
            minimum,
            maximum,
            plan.duplicates(),
            selectionMode
        );
    }

    private AttackChoice.AreaPlacement resolveArea(
        AttackTargetPlan.Area plan
    ) {
        Map<AreaPlacementKey, ResolvedAreaPlacement> placements =
            new LinkedHashMap<>();

        int maximumRotations = plan.placement().allowRotation() ? 6 : 1;
        int reflectionCount = plan.placement().allowReflection() ? 2 : 1;

        for (HexPosition anchor : placementAnchors(plan.placement())) {
            for (int reflection = 0;
                 reflection < reflectionCount;
                 reflection++) {
                for (int rotation = 0;
                     rotation < maximumRotations;
                     rotation++) {
                    boolean reflected = reflection == 1;
                    Set<HexPosition> affectedHexes = placePattern(
                        plan.pattern(),
                        anchor,
                        rotation,
                        reflected
                    );

                    boolean allHexesOnBoard = affectedHexes.stream()
                        .allMatch(state.board()::contains);
                    boolean eligible = allHexesOnBoard
                        && matchesPlacementRule(
                            plan.placement(),
                            affectedHexes
                        )
                        && containsEligibleAreaTarget(
                            affectedHexes,
                            plan
                        );

                    placements.putIfAbsent(
                        new AreaPlacementKey(anchor, affectedHexes),
                        new ResolvedAreaPlacement(
                            new AttackChoice.AreaPlacementOption(
                                anchor,
                                rotation,
                                reflected
                            ),
                            affectedHexes,
                            targetableAreaHexes(plan, affectedHexes),
                            eligible
                        )
                    );
                }
            }
        }

        return new AttackChoice.AreaPlacement(
            List.copyOf(placements.values()),
            plan.placement() instanceof AreaPlacementRule.AroundOrigin ? AreaPlacementControl.LOCKED_TO_ORIGIN : AreaPlacementControl.POINTER_CONTROLLED
        );
    }

    private List<HexPosition> placementAnchors(
        AreaPlacementRule placement
    ) {
        return switch (placement) {
            case AreaPlacementRule.AroundOrigin ignored ->
                List.of(attackOrigin);
            case AreaPlacementRule.WithinRange ignored ->
                state.board().positions()
                    .stream()
                    .sorted(Comparator
                        .comparingInt(HexPosition::q)
                        .thenComparingInt(HexPosition::r))
                    .toList();
        };
    }

    private boolean matchesPlacementRule(
        AreaPlacementRule placement,
        Set<HexPosition> affectedHexes
    ) {
        return switch (placement) {
            case AreaPlacementRule.AroundOrigin ignored -> true;
            case AreaPlacementRule.WithinRange within -> {
                int range = requireNonNegative(
                    within.range().evaluate(expressionContext),
                    "Area placement range"
                );
                yield affectedHexes.stream()
                    .anyMatch(position ->
                        position.distanceTo(attackOrigin) <= range
                            && matchesLineOfSight(
                                within.lineOfSight(),
                                position
                            )
                    );
            }
        };
    }

    private boolean containsEligibleAreaTarget(
        Set<HexPosition> affectedHexes,
        AttackTargetPlan.Area plan
    ) {
        return !resolveAreaTargets(plan, affectedHexes).isEmpty();
    }

    List<FigureId> resolveAreaTargets(
        AttackTargetPlan.Area plan,
        Set<HexPosition> affectedHexes
    ) {
        Objects.requireNonNull(plan, "plan");
        Objects.requireNonNull(affectedHexes, "affectedHexes");

        LineOfSightPolicy lineOfSight = areaLineOfSight(plan);

        return affectedHexes.stream()
            .sorted(Comparator
                .comparingInt(HexPosition::q)
                .thenComparingInt(HexPosition::r))
            .map(state::figureAt)
            .flatMap(java.util.Optional::stream)
            .filter(figure -> matchesPredicate(plan.targets(), figure))
            .filter(figure -> matchesLineOfSight(
                lineOfSight,
                state.positionOf(figure.id())
            ))
            .map(Figure::id)
            .toList();
    }

    private Set<HexPosition> targetableAreaHexes(
        AttackTargetPlan.Area plan,
        Set<HexPosition> affectedHexes
    ) {
        LineOfSightPolicy lineOfSight = areaLineOfSight(plan);
        return affectedHexes.stream()
            .filter(state.board()::contains)
            .filter(position -> matchesLineOfSight(
                lineOfSight,
                position
            ))
            .collect(java.util.stream.Collectors.toUnmodifiableSet());
    }

    private record AreaPlacementKey(
        HexPosition anchor,
        Set<HexPosition> affectedHexes
    ) {
    }

    private static LineOfSightPolicy areaLineOfSight(
        AttackTargetPlan.Area plan
    ) {
        return switch (plan.placement()) {
            case AreaPlacementRule.AroundOrigin ignored ->
                LineOfSightPolicy.REQUIRED;
            case AreaPlacementRule.WithinRange within ->
                within.lineOfSight();
        };
    }

    private static Set<HexPosition> placePattern(
        HexPattern pattern,
        HexPosition anchor,
        int clockwiseRotations,
        boolean reflected
    ) {
        Set<HexPosition> result = new LinkedHashSet<>();
        for (HexPosition tile : pattern.tiles()) {
            HexPosition transformed = reflected ? reflect(tile) : tile;
            for (int i = 0; i < clockwiseRotations; i++) {
                transformed = rotateClockwise(transformed);
            }
            result.add(new HexPosition(
                anchor.q() + transformed.q(),
                anchor.r() + transformed.r()
            ));
        }
        return Set.copyOf(result);
    }

    private static HexPosition rotateClockwise(HexPosition position) {
        return new HexPosition(
            -position.r(),
            position.q() + position.r()
        );
    }

    private static HexPosition reflect(HexPosition position) {
        return new HexPosition(
            position.q(),
            -position.q() - position.r()
        );
    }

    private static UnsupportedOperationException unsupported(
        AttackTargetPlan plan
    ) {
        return new UnsupportedOperationException(
            "Attack target plan is not supported yet: "
                + plan.getClass().getSimpleName()
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

    private boolean matchesLineOfSight(
        LineOfSightPolicy policy,
        HexPosition candidate
    ) {
        return policy == LineOfSightPolicy.IGNORED
            || LineOfSightResolver.hasLineOfSight(
                state.board(),
                attackOrigin,
                candidate
            );
    }

    private boolean matchesPredicate(
        FigurePredicate predicate,
        Figure candidate
    ) {
        return switch (predicate) {
            case FigurePredicate.Any ignored -> true;
            case FigurePredicate.Relationship relationship ->
                matchesRelationship(relationship, candidate);
            case FigurePredicate.Kind kind ->
                matchesKind(kind.identity(), candidate);
            case FigurePredicate.Identity identity ->
                matchesIdentity(identity.identity(), candidate);
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
                    && candidate.factionId().equals(reference.factionId());
            case ENEMY ->
                !candidate.factionId().equals(reference.factionId());
        };
    }

    private boolean matchesKind(
        FigureIdentity identity,
        Figure candidate
    ) {
        return switch (identity) {
            case CharacterIdentity ignored -> candidate.type() instanceof CharacterIdentity;
            case MonsterIdentity ignored -> candidate.type() instanceof MonsterIdentity;
            case CharacterSummonIdentity ignored -> candidate.type() instanceof CharacterSummonIdentity;
            case ObjectiveIdentity ignored -> candidate.type() instanceof ObjectiveIdentity;
        };
    }

    private boolean matchesIdentity(
        FigureIdentity identity,
        Figure candidate
    ) {
        return switch (identity) {
            case CharacterIdentity character -> {
                if (!(candidate.type() instanceof CharacterIdentity characterCandidate)) {
                    yield false;
                }
                yield characterCandidate.id().equals(character.id());
            }
            case MonsterIdentity monster -> {
                if (!(candidate.type() instanceof MonsterIdentity monsterCandidate)) {
                    yield false;
                }
                yield (monsterCandidate.monsterTypeId() == monster.monsterTypeId()) && (monsterCandidate.rank() == monster.rank());
            }
            case CharacterSummonIdentity summon -> {
                if (!(candidate.type() instanceof CharacterSummonIdentity summonCandidate)) {
                    yield false;
                }
                yield summonCandidate.definitionId() == summon.definitionId();
            }
            case ObjectiveIdentity ignored -> candidate.type() instanceof ObjectiveIdentity;
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
