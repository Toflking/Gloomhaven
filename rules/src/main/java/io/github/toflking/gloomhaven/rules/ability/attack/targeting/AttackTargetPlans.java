package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

public final class AttackTargetPlans {

    private AttackTargetPlans() {
    }

    public static AttackTargetPlan.Query singleEnemyWithinRange(
        int range
    ) {
        return enemiesWithinRange(
            new IntExpression.Constant(range),
            new Cardinality.ChooseUpTo(
                new IntExpression.Constant(1)
            )
        );
    }

    public static AttackTargetPlan.Query enemiesWithinRange(
        IntExpression range,
        Cardinality cardinality
    ) {
        return new AttackTargetPlan.Query(
            new TargetQuery(
                new TargetRegion.WithinRangeOf(
                    new TargetAnchor.AttackOrigin(),
                    range
                ),
                new FigurePredicate.Relationship(
                    new FigureReference.Actor(),
                    FigurePredicate.FigureRelation.ENEMY
                ),
                LineOfSightPolicy.REQUIRED
            ),
            cardinality,
            DuplicatePolicy.DISTINCT_TARGETS
        );
    }

    public static AttackTargetPlan.Query allAdjacentEnemies() {
        return new AttackTargetPlan.Query(
            new TargetQuery(
                new TargetRegion.AdjacentTo(
                    new TargetAnchor.AttackOrigin()
                ),
                new FigurePredicate.Relationship(
                    new FigureReference.Actor(),
                    FigurePredicate.FigureRelation.ENEMY
                ),
                LineOfSightPolicy.REQUIRED
            ),
            new Cardinality.All(),
            DuplicatePolicy.DISTINCT_TARGETS
        );
    }
}
