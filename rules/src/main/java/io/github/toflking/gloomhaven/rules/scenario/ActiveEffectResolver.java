package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetAnchor;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetRegion;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.LineOfSightResolver;
import io.github.toflking.gloomhaven.rules.execution.AbilityExecutionFrame;
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.execution.ExecutionMemory;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.figure.identities.CharacterIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.CharacterSummonIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.FigureIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.MonsterIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.ObjectiveIdentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Resolves the effects applying to figures from the current scenario state. */
public final class ActiveEffectResolver {
    private final ScenarioState state;

    public ActiveEffectResolver(ScenarioState state) {
        this.state = Objects.requireNonNull(state, "state");
    }

    public FigureEffectView effectsFor(FigureId figureId) {
        Figure candidate = state.figure(
            Objects.requireNonNull(figureId, "figureId")
        );
        List<ActiveEffect> applicable = state.activeEffects().stream()
            .filter(effect -> appliesTo(effect, candidate))
            .toList();

        int shield = applicable.stream()
            .map(ActiveEffect::effect)
            .filter(ActiveEffect.Effect.Shield.class::isInstance)
            .map(ActiveEffect.Effect.Shield.class::cast)
            .mapToInt(ActiveEffect.Effect.Shield::amount)
            .sum();

        List<FigureEffectView.Retaliate> retaliates = new ArrayList<>();
        for (ActiveEffect activeEffect : applicable) {
            if (activeEffect.effect()
                instanceof ActiveEffect.Effect.Retaliate retaliate) {
                retaliates.add(new FigureEffectView.Retaliate(
                    activeEffect.id(),
                    activeEffect.source(),
                    retaliate.amount(),
                    retaliate.range(),
                    retaliate.lineOfSight()
                ));
            }
        }

        return new FigureEffectView(
            figureId,
            shield,
            retaliates,
            applicable
        );
    }

    public int shieldFor(FigureId figureId) {
        return effectsFor(figureId).shield();
    }

    /**
     * Retaliate uses the figures' positions after the attack. It is direct
     * damage, so Shield is deliberately not consulted here.
     */
    public int retaliateDamageAgainst(
        FigureId retaliatingFigureId,
        FigureId attackerId
    ) {
        if (!state.hasFigure(retaliatingFigureId)
            || !state.hasFigure(attackerId)) {
            return 0;
        }

        int distance = state.positionOf(retaliatingFigureId)
            .distanceTo(state.positionOf(attackerId));

        return effectsFor(retaliatingFigureId).retaliates().stream()
            .filter(retaliate -> distance <= retaliate.range())
            .filter(retaliate ->
                retaliate.lineOfSight() == LineOfSightPolicy.IGNORED
                    || LineOfSightResolver.hasLineOfSight(
                        state.board(),
                        state.positionOf(retaliatingFigureId),
                        state.positionOf(attackerId)
                    )
            )
            .mapToInt(FigureEffectView.Retaliate::amount)
            .sum();
    }

    private boolean appliesTo(
        ActiveEffect activeEffect,
        Figure candidate
    ) {
        ActiveEffect.EffectScope scope = switch (activeEffect.effect()) {
            case ActiveEffect.Effect.Shield shield -> shield.scope();
            case ActiveEffect.Effect.Retaliate retaliate -> retaliate.scope();
        };

        if (!state.hasFigure(activeEffect.source().actorId())) {
            return false;
        }

        return matchesRegion(scope.region(), candidate, activeEffect.source())
            && matchesLineOfSight(
                scope.lineOfSight(),
                candidate,
                activeEffect.source()
            )
            && matchesPredicate(
                scope.figures(),
                candidate,
                activeEffect.source()
            );
    }

    private boolean matchesRegion(
        TargetRegion region,
        Figure candidate,
        ActiveEffect.EffectSource source
    ) {
        HexPosition candidatePosition = state.positionOf(candidate.id());
        AbilityExpressionContext context = expressionContext(source);

        return switch (region) {
            case TargetRegion.Anywhere ignored -> true;
            case TargetRegion.AdjacentTo adjacent ->
                candidatePosition.distanceTo(
                    resolveAnchor(adjacent.anchor(), source)
                ) == 1;
            case TargetRegion.WithinRangeOf within ->
                candidatePosition.distanceTo(
                    resolveAnchor(within.anchor(), source)
                ) <= requireNonNegative(
                    within.range().evaluate(context),
                    "Effect range"
                );
            case TargetRegion.ExactRangeFrom exact ->
                candidatePosition.distanceTo(
                    resolveAnchor(exact.anchor(), source)
                ) == requireNonNegative(
                    exact.range().evaluate(context),
                    "Exact effect range"
                );
            case TargetRegion.SameRoomAs ignored ->
                throw new IllegalStateException(
                    "Same-room effects require room topology on the board"
                );
            case TargetRegion.OccupyingPath path ->
                memory(source).requirePath(path.path())
                    .contains(candidatePosition);
            case TargetRegion.AdjacentToHexSet adjacent ->
                memory(source).requireHexes(adjacent.hexes()).stream()
                    .anyMatch(hex ->
                        candidatePosition.distanceTo(hex) == 1
                    );
            case TargetRegion.Intersection intersection ->
                intersection.regions().stream().allMatch(child ->
                    matchesRegion(child, candidate, source)
                );
            case TargetRegion.Union union ->
                union.regions().stream().anyMatch(child ->
                    matchesRegion(child, candidate, source)
                );
        };
    }

    private boolean matchesLineOfSight(
        LineOfSightPolicy policy,
        Figure candidate,
        ActiveEffect.EffectSource source
    ) {
        return policy == LineOfSightPolicy.IGNORED
            || LineOfSightResolver.hasLineOfSight(
                state.board(),
                state.positionOf(source.actorId()),
                state.positionOf(candidate.id())
            );
    }

    private boolean matchesPredicate(
        FigurePredicate predicate,
        Figure candidate,
        ActiveEffect.EffectSource source
    ) {
        return switch (predicate) {
            case FigurePredicate.Any ignored -> true;
            case FigurePredicate.Relationship relationship ->
                matchesRelationship(relationship, candidate, source);
            case FigurePredicate.Kind kind ->
                matchesKind(kind.identity(), candidate);
            case FigurePredicate.Identity identity ->
                matchesIdentity(identity.identity(), candidate);
            case FigurePredicate.HasCondition condition ->
                candidate.conditions().contains(condition.condition());
            case FigurePredicate.Contextual contextual ->
                throw new IllegalStateException(
                    "No resolver is registered for contextual effect predicate: "
                        + contextual.predicate().value()
                );
            case FigurePredicate.And and ->
                and.predicates().stream().allMatch(child ->
                    matchesPredicate(child, candidate, source)
                );
            case FigurePredicate.Or or ->
                or.predicates().stream().anyMatch(child ->
                    matchesPredicate(child, candidate, source)
                );
            case FigurePredicate.Not not ->
                !matchesPredicate(not.predicate(), candidate, source);
        };
    }

    private boolean matchesRelationship(
        FigurePredicate.Relationship relationship,
        Figure candidate,
        ActiveEffect.EffectSource source
    ) {
        FigureId referenceId = resolveFigure(
            relationship.reference(),
            source
        );
        Figure reference = state.figure(referenceId);

        return switch (relationship.relation()) {
            case SELF -> candidate.id().equals(referenceId);
            case NOT_SELF -> !candidate.id().equals(referenceId);
            case ALLY -> !candidate.id().equals(referenceId)
                && candidate.factionId().equals(reference.factionId());
            case ENEMY ->
                !candidate.factionId().equals(reference.factionId());
        };
    }

    private static boolean matchesKind(
        FigureIdentity identity,
        Figure candidate
    ) {
        return switch (identity) {
            case CharacterIdentity ignored ->
                candidate.type() instanceof CharacterIdentity;
            case MonsterIdentity ignored ->
                candidate.type() instanceof MonsterIdentity;
            case CharacterSummonIdentity ignored ->
                candidate.type() instanceof CharacterSummonIdentity;
            case ObjectiveIdentity ignored ->
                candidate.type() instanceof ObjectiveIdentity;
        };
    }

    private static boolean matchesIdentity(
        FigureIdentity identity,
        Figure candidate
    ) {
        return switch (identity) {
            case CharacterIdentity character ->
                candidate.type() instanceof CharacterIdentity other
                    && other.id().equals(character.id());
            case MonsterIdentity monster ->
                candidate.type() instanceof MonsterIdentity other
                    && other.monsterTypeId() == monster.monsterTypeId()
                    && other.rank() == monster.rank();
            case CharacterSummonIdentity summon ->
                candidate.type() instanceof CharacterSummonIdentity other
                    && other.definitionId() == summon.definitionId();
            case ObjectiveIdentity ignored ->
                candidate.type() instanceof ObjectiveIdentity;
        };
    }

    private FigureId resolveFigure(
        FigureReference reference,
        ActiveEffect.EffectSource source
    ) {
        return switch (reference) {
            case FigureReference.Actor ignored -> source.actorId();
            case FigureReference.Owner ignored -> source.ownerId();
            case FigureReference.Controller ignored -> source.controllerId();
            case FigureReference.CurrentTarget ignored ->
                throw statefulScope(reference);
            case FigureReference.Stored stored ->
                memory(source).requireFigure(stored.variable());
        };
    }

    private HexPosition resolveAnchor(
        TargetAnchor anchor,
        ActiveEffect.EffectSource source
    ) {
        if (anchor instanceof TargetAnchor.StoredHex stored) {
            return memory(source).requireHex(stored.variable());
        }

        FigureId figureId = switch (anchor) {
            case TargetAnchor.AttackOrigin ignored -> source.actorId();
            case TargetAnchor.Actor ignored -> source.actorId();
            case TargetAnchor.Owner ignored -> source.ownerId();
            case TargetAnchor.Controller ignored -> source.controllerId();
            case TargetAnchor.PreviousTarget ignored ->
                throw statefulScope(anchor);
            case TargetAnchor.StoredFigure stored ->
                memory(source).requireFigure(stored.variable());
            case TargetAnchor.StoredHex ignored ->
                throw new AssertionError("Handled above");
        };
        if (!state.hasFigure(figureId)) {
            throw new IllegalStateException(
                "Effect anchor figure is no longer in the scenario: "
                    + figureId
            );
        }
        return state.positionOf(figureId);
    }

    private AbilityExpressionContext expressionContext(
        ActiveEffect.EffectSource source
    ) {
        return AbilityExpressionContext.withoutTarget(
            state,
            new AbilityExecutionFrame(
                source.ownerId(),
                source.actorId(),
                source.controllerId(),
                source.abilitySource()
            ),
            memory(source)
        );
    }

    private static ExecutionMemory memory(
        ActiveEffect.EffectSource source
    ) {
        return ExecutionMemory.restore(source.memory());
    }

    private static int requireNonNegative(int value, String label) {
        if (value < 0) {
            throw new IllegalStateException(
                label + " resolved to a negative value"
            );
        }
        return value;
    }

    private static IllegalStateException statefulScope(Object component) {
        return new IllegalStateException(
            "Active effect scopes cannot depend on transient execution state: "
                + component.getClass().getSimpleName()
        );
    }
}
