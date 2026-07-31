package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.Cardinality;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.PathSelectionRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetAnchor;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetQuery;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetRegion;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;
import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class AttackModelTest {

    @Test
    void representsTargetCountResolvedFromEarlierSelfDamage() {
        ExecutionVariableId damageSuffered =
            new ExecutionVariableId("damage-suffered");

        AttackTargetPlan.Query targeting = new AttackTargetPlan.Query(
            enemiesWithinRange(
                new TargetAnchor.AttackOrigin(),
                new IntExpression.Constant(1)
            ),
            new Cardinality.ChooseUpTo(
                new IntExpression.Variable(damageSuffered)
            ),
            DuplicatePolicy.DISTINCT_TARGETS
        );

        AttackAbility ability = new AttackAbility(
            AbilityValue.fixed(4),
            targeting
        );

        Cardinality.ChooseUpTo cardinality =
            assertInstanceOf(
                Cardinality.ChooseUpTo.class,
                ((AttackTargetPlan.Query) ability.targeting()).cardinality()
            );
        assertEquals(
            new IntExpression.Variable(damageSuffered),
            cardinality.maximum()
        );
    }

    @Test
    void representsPrimaryTargetPathAndDerivedTargets() {
        ExecutionVariableId primary =
            new ExecutionVariableId("primary-target");
        ExecutionVariableId path =
            new ExecutionVariableId("attack-path");

        AttackTargetPlan.PrimaryAndDerived targeting =
            new AttackTargetPlan.PrimaryAndDerived(
                new AttackTargetPlan.Query(
                    enemiesWithinRange(
                        new TargetAnchor.AttackOrigin(),
                        new IntExpression.Constant(4)
                    ),
                    new Cardinality.ChooseExactly(
                        new IntExpression.Constant(1)
                    ),
                    DuplicatePolicy.DISTINCT_TARGETS
                ),
                primary,
                new PathSelectionRule(
                    new IntExpression.Constant(4),
                    true,
                    true
                ),
                path,
                new TargetQuery(
                    new TargetRegion.OccupyingPath(path),
                    enemiesOfActor(),
                    LineOfSightPolicy.REQUIRED
                ),
                DuplicatePolicy.DISTINCT_TARGETS
            );

        assertEquals(path, targeting.pathVariable());
        assertInstanceOf(
            TargetRegion.OccupyingPath.class,
            targeting.derivedTargets().region()
        );
    }

    @Test
    void representsSequentialChainWithPerAttackValue() {
        AbilityValue decreasingAttack = new AbilityValue(
            new IntExpression.Subtract(
                new IntExpression.Variable(
                    new ExecutionVariableId("chain-base")
                ),
                new IntExpression.AttackIndex()
            ),
            EvaluationMoment.PER_ATTACK
        );

        AttackTargetPlan.Chain targeting =
            new AttackTargetPlan.Chain(
                List.of(
                    new AttackTargetPlan.ChainStep(
                        enemiesWithinRange(
                            new TargetAnchor.AttackOrigin(),
                            new IntExpression.Constant(3)
                        ),
                        Optional.empty(),
                        false
                    ),
                    new AttackTargetPlan.ChainStep(
                        enemiesWithinRange(
                            new TargetAnchor.PreviousTarget(),
                            new IntExpression.Constant(2)
                        ),
                        Optional.empty(),
                        false
                    )
                ),
                DuplicatePolicy.DISTINCT_TARGETS
            );

        AttackAbility chainAttack = new AttackAbility(
            decreasingAttack,
            targeting
        );

        assertEquals(
            EvaluationMoment.PER_ATTACK,
            chainAttack.amount().evaluationMoment()
        );
        assertEquals(2, targeting.steps().size());
    }

    @Test
    void representsAlternativeOriginAndRepeatedTargets() {
        AttackOrigin origin = new AttackOrigin.Choose(List.of(
            new AttackOrigin.Actor(),
            new AttackOrigin.StoredFigure(
                new ExecutionVariableId("doppelganger")
            )
        ));

        AttackTargetPlan.Query targets = new AttackTargetPlan.Query(
            enemiesWithinRange(
                new TargetAnchor.AttackOrigin(),
                new IntExpression.Constant(3)
            ),
            new Cardinality.ChooseUpTo(
                new IntExpression.Constant(3)
            ),
            DuplicatePolicy.REPEATED_TARGETS_ALLOWED
        );

        AttackAbility ability = new AttackAbility(
            AbilityValue.fixed(2),
            origin,
            targets,
            List.of()
        );

        assertInstanceOf(AttackOrigin.Choose.class, ability.origin());
        assertEquals(
            DuplicatePolicy.REPEATED_TARGETS_ALLOWED,
            ((AttackTargetPlan.Query) ability.targeting()).duplicates()
        );
    }

    private static TargetQuery enemiesWithinRange(
        TargetAnchor anchor,
        IntExpression range
    ) {
        return new TargetQuery(
            new TargetRegion.WithinRangeOf(anchor, range),
            enemiesOfActor(),
            LineOfSightPolicy.REQUIRED
        );
    }

    private static FigurePredicate enemiesOfActor() {
        return new FigurePredicate.Relationship(
            new FigureReference.Actor(),
            FigurePredicate.FigureRelation.ENEMY
        );
    }
}
