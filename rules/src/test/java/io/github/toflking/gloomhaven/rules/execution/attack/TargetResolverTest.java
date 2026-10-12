package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.PathSelectionRule;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexEdge;
import io.github.toflking.gloomhaven.rules.board.HexPattern;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.board.TerrainType;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import io.github.toflking.gloomhaven.rules.execution.ActionExecution;
import io.github.toflking.gloomhaven.rules.execution.AbilityExpressionContext;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.github.toflking.gloomhaven.rules.TestFigures.character;
import static io.github.toflking.gloomhaven.rules.TestFigures.monster;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TargetResolverTest {
    private static final FigureId ACTOR = new FigureId("actor");
    private static final FigureId ENEMY = new FigureId("enemy");
    private static final AbilitySource SOURCE = new AbilitySource(
        new AbilityCardId("resolver-test"),
        CardHalf.TOP
    );

    @Test
    void resolvesQueryPlansThroughTheGeneralPlanEntryPoint() {
        TargetPlan.Query plan =
            AttackTargetPlans.singleEnemyWithinRange(2);
        ScenarioState state = scenarioWithRadius(2);
        state.addFigure(character(ACTOR, 10), new HexPosition(0, 0));
        state.addFigure(monster(ENEMY, 10), new HexPosition(1, 0));

        FigureTargetSelection.FigureTargets choice = assertInstanceOf(
            FigureTargetSelection.FigureTargets.class,
            resolver(state, plan).resolveAttack(plan).selection()
        );

        assertEquals(Set.of(ENEMY), choice.eligibleTargets());
    }

    @Test
    void resolvesAnAreaFixedAroundTheAttackOrigin() {
        TargetPlan.Area plan = new TargetPlan.Area(
            new HexPattern(List.of(
                new HexPosition(1, 0),
                new HexPosition(0, 1)
            )),
            new AreaPlacementRule.AroundOrigin(false, false),
            enemiesOfActor()
        );
        ScenarioState state = scenarioWithRadius(1);
        state.addFigure(character(ACTOR, 10), new HexPosition(0, 0));
        state.addFigure(monster(ENEMY, 10), new HexPosition(1, 0));

        FigureTargetSelection.AreaPlacement choice = assertInstanceOf(
            FigureTargetSelection.AreaPlacement.class,
            resolver(state, plan).resolveAttack(plan).selection()
        );

        assertEquals(
            List.of(new ResolvedAreaPlacement(
                new FigureTargetSelection.AreaPlacementOption(
                    new HexPosition(0, 0),
                    0,
                    false
                ),
                Set.of(
                    new HexPosition(1, 0),
                    new HexPosition(0, 1)
                )
            )),
            choice.placements()
        );
    }

    @Test
    void resolvesRotatedAreaPlacementsWithinRange() {
        TargetPlan.Area plan = new TargetPlan.Area(
            new HexPattern(List.of(
                new HexPosition(0, 0),
                new HexPosition(1, 0)
            )),
            new AreaPlacementRule.WithinRange(
                new IntExpression.Constant(1),
                LineOfSightPolicy.IGNORED,
                true,
                true
            ),
            enemiesOfActor()
        );
        ScenarioState state = scenarioWithRadius(2);
        state.addFigure(character(ACTOR, 10), new HexPosition(0, 0));
        state.addFigure(monster(ENEMY, 10), new HexPosition(0, 2));

        FigureTargetSelection.AreaPlacement choice = assertInstanceOf(
            FigureTargetSelection.AreaPlacement.class,
            resolver(state, plan).resolveAttack(plan).selection()
        );

        assertTrue(choice.placements().stream().anyMatch(
            placement -> placement.option().equals(
                new FigureTargetSelection.AreaPlacementOption(
                    new HexPosition(0, 1),
                    1,
                    false
                )
            )
        ));
    }

    @Test
    void ordersAroundOriginPlacementsByConsecutiveRotation() {
        TargetPlan.Area plan = new TargetPlan.Area(
            new HexPattern(List.of(new HexPosition(1, 0))),
            new AreaPlacementRule.AroundOrigin(true, false),
            enemiesOfActor()
        );
        ScenarioState state = scenarioWithRadius(1);
        HexPosition origin = new HexPosition(0, 0);
        state.addFigure(character(ACTOR, 10), origin);

        List<HexPosition> rotatedPositions = List.of(
            new HexPosition(1, 0),
            new HexPosition(0, 1),
            new HexPosition(-1, 1),
            new HexPosition(-1, 0),
            new HexPosition(0, -1),
            new HexPosition(1, -1)
        );
        for (int index = 0; index < rotatedPositions.size(); index++) {
            state.addFigure(
                monster(new FigureId("enemy-" + index), 10),
                rotatedPositions.get(index)
            );
        }

        FigureTargetSelection.AreaPlacement choice = assertInstanceOf(
            FigureTargetSelection.AreaPlacement.class,
            resolver(state, plan).resolveAttack(plan).selection()
        );

        assertEquals(
            List.of(0, 1, 2, 3, 4, 5),
            choice.placements()
                .stream()
                .map(placement ->
                    placement.option().clockwiseRotations()
                )
                .toList()
        );
        assertEquals(
            rotatedPositions,
            choice.placements()
                .stream()
                .map(placement ->
                    placement.affectedHexes().iterator().next()
                )
                .toList()
        );
    }

    @Test
    void exposesAreaHexesWithoutLineOfSightAsUntargetable() {
        HexPosition origin = new HexPosition(0, 0);
        HexPosition visible = new HexPosition(1, 0);
        HexPosition blocked = new HexPosition(0, 1);
        Set<HexEdge> walls = blocked.neighbors()
            .stream()
            .map(neighbor -> new HexEdge(blocked, neighbor))
            .collect(java.util.stream.Collectors.toSet());
        ScenarioState state = new ScenarioState(new HexBoard(
            Map.of(
                origin, new HexTile(TerrainType.NORMAL),
                visible, new HexTile(TerrainType.NORMAL),
                blocked, new HexTile(TerrainType.NORMAL)
            ),
            walls
        ));
        state.addFigure(character(ACTOR, 10), origin);
        state.addFigure(monster(ENEMY, 10), visible);
        state.addFigure(
            monster(new FigureId("blocked-enemy"), 10),
            blocked
        );
        TargetPlan.Area plan = new TargetPlan.Area(
            new HexPattern(List.of(visible, blocked)),
            new AreaPlacementRule.AroundOrigin(false, false),
            enemiesOfActor()
        );

        FigureTargetSelection.AreaPlacement choice = assertInstanceOf(
            FigureTargetSelection.AreaPlacement.class,
            resolver(state, plan).resolveAttack(plan).selection()
        );
        ResolvedAreaPlacement placement =
            choice.placements().getFirst();

        assertEquals(Set.of(visible, blocked), placement.affectedHexes());
        assertEquals(Set.of(visible), placement.targetableHexes());
    }

    @Test
    void excludesQueryTargetsWithoutRequiredLineOfSight() {
        TargetPlan.Query plan =
            AttackTargetPlans.singleEnemyWithinRange(2);
        HexPosition actorPosition = new HexPosition(0, 0);
        HexPosition enemyPosition = new HexPosition(1, 0);
        ScenarioState state = new ScenarioState(new HexBoard(
            Map.of(
                actorPosition,
                new HexTile(TerrainType.NORMAL),
                enemyPosition,
                new HexTile(TerrainType.NORMAL)
            ),
            enemyPosition.neighbors()
                .stream()
                .map(neighbor -> new HexEdge(enemyPosition, neighbor))
                .collect(java.util.stream.Collectors.toSet())
        ));
        state.addFigure(character(ACTOR, 10), actorPosition);
        state.addFigure(monster(ENEMY, 10), enemyPosition);

        FigureTargetSelection.FigureTargets choice = assertInstanceOf(
            FigureTargetSelection.FigureTargets.class,
            resolver(state, plan).resolveAttack(plan).selection()
        );

        assertTrue(choice.eligibleTargets().isEmpty());
    }

    @Test
    void rejectsTargetPlansWhoseChoiceFlowsAreNotImplemented() {
        TargetPlan.Query query =
            AttackTargetPlans.singleEnemyWithinRange(2);
        ExecutionVariableId primary =
            new ExecutionVariableId("primary");
        ExecutionVariableId path =
            new ExecutionVariableId("path");
        TargetPlan.PrimaryAndDerived primaryAndDerived =
            new TargetPlan.PrimaryAndDerived(
                query,
                primary,
                new PathSelectionRule(
                    new IntExpression.Constant(2),
                    true,
                    true
                ),
                path,
                query.candidates(),
                DuplicatePolicy.DISTINCT_TARGETS
            );
        TargetPlan.Chain chain = new TargetPlan.Chain(
            List.of(new TargetPlan.ChainStep(
                query.candidates(),
                false
            )),
            DuplicatePolicy.DISTINCT_TARGETS
        );
        ScenarioState state = scenarioWithRadius(1);
        state.addFigure(character(ACTOR, 10), new HexPosition(0, 0));
        TargetResolver resolver = resolver(state, query);

        assertThrows(
            UnsupportedOperationException.class,
            () -> resolver.resolveAttack(primaryAndDerived)
        );
        assertThrows(
            UnsupportedOperationException.class,
            () -> resolver.resolveAttack(chain)
        );
    }

    private static FigurePredicate enemiesOfActor() {
        return new FigurePredicate.Relationship(
            new FigureReference.Actor(),
            FigurePredicate.FigureRelation.ENEMY
        );
    }

    private static TargetResolver resolver(
        ScenarioState state,
        TargetPlan plan
    ) {
        AttackAbility attack = new AttackAbility(
            AbilityValue.fixed(2),
            plan
        );
        state.startActionExecution(ACTOR, SOURCE, List.of(attack));
        ActionExecution execution = state.execution();
        execution.beginAttack(
            attack.effectiveDefinition(),
            AbilityExpressionContext.withoutTarget(
                state,
                execution.frame(),
                execution.memory()
            )
        );
        return new TargetResolver(state, execution);
    }

    private static ScenarioState scenarioWithRadius(int radius) {
        Map<HexPosition, HexTile> tiles = new HashMap<>();
        for (int q = -radius; q <= radius; q++) {
            for (int r = -radius; r <= radius; r++) {
                HexPosition position = new HexPosition(q, r);
                if (position.distanceTo(new HexPosition(0, 0)) <= radius) {
                    tiles.put(position, new HexTile(TerrainType.NORMAL));
                }
            }
        }
        return new ScenarioState(new HexBoard(tiles, Set.of()));
    }
}
