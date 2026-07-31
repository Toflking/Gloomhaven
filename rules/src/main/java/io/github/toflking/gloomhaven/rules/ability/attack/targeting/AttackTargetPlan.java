package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;
import io.github.toflking.gloomhaven.rules.board.HexPattern;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public sealed interface AttackTargetPlan
    permits AttackTargetPlan.Query,
    AttackTargetPlan.Area,
    AttackTargetPlan.PrimaryAndDerived,
    AttackTargetPlan.Chain {

    record Query(
        TargetQuery candidates,
        Cardinality cardinality,
        DuplicatePolicy duplicates
    ) implements AttackTargetPlan {

        public Query {
            Objects.requireNonNull(candidates, "candidates");
            Objects.requireNonNull(cardinality, "cardinality");
            Objects.requireNonNull(duplicates, "duplicates");
        }
    }

    record Area(
        HexPattern pattern,
        AreaPlacementRule placement,
        FigurePredicate targets
    ) implements AttackTargetPlan {

        public Area {
            Objects.requireNonNull(pattern, "pattern");
            Objects.requireNonNull(placement, "placement");
            Objects.requireNonNull(targets, "targets");
        }
    }

    record PrimaryAndDerived(
        Query primaryTarget,
        ExecutionVariableId primaryTargetVariable,
        PathSelectionRule pathSelection,
        ExecutionVariableId pathVariable,
        TargetQuery derivedTargets,
        DuplicatePolicy duplicates
    ) implements AttackTargetPlan {

        public PrimaryAndDerived {
            Objects.requireNonNull(primaryTarget, "primaryTarget");
            Objects.requireNonNull(
                primaryTargetVariable,
                "primaryTargetVariable"
            );
            Objects.requireNonNull(pathSelection, "pathSelection");
            Objects.requireNonNull(pathVariable, "pathVariable");
            Objects.requireNonNull(derivedTargets, "derivedTargets");
            Objects.requireNonNull(duplicates, "duplicates");
        }
    }

    record Chain(
        List<ChainStep> steps,
        DuplicatePolicy duplicates
    ) implements AttackTargetPlan {

        public Chain {
            steps = List.copyOf(steps);
            if (steps.isEmpty()) {
                throw new IllegalArgumentException(
                    "A chained attack requires at least one step"
                );
            }
            Objects.requireNonNull(duplicates, "duplicates");
        }
    }

    record ChainStep(
        TargetQuery candidates,
        Optional<AbilityValue> attackValueOverride,
        boolean optional
    ) {

        public ChainStep {
            Objects.requireNonNull(candidates, "candidates");
            Objects.requireNonNull(
                attackValueOverride,
                "attackValueOverride"
            );
        }

        public ChainStep(
            TargetQuery candidates,
            boolean optional
        ) {
            this(candidates, Optional.empty(), optional);
        }
    }
}
