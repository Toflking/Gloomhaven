package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import java.util.List;
import java.util.Objects;

public sealed interface FigurePredicate
    permits FigurePredicate.Any,
    FigurePredicate.Relationship,
    FigurePredicate.Classification,
    FigurePredicate.HasCondition,
    FigurePredicate.Contextual,
    FigurePredicate.And,
    FigurePredicate.Or,
    FigurePredicate.Not {

    record Any() implements FigurePredicate {
    }

    record Relationship(
        FigureReference reference,
        FigureRelation relation
    ) implements FigurePredicate {

        public Relationship {
            Objects.requireNonNull(reference, "reference");
            Objects.requireNonNull(relation, "relation");
        }
    }

    record Classification(FigureClassification classification)
        implements FigurePredicate {

        public Classification {
            Objects.requireNonNull(classification, "classification");
        }
    }

    record HasCondition(ConditionId condition)
        implements FigurePredicate {

        public HasCondition {
            Objects.requireNonNull(condition, "condition");
        }
    }

    record Contextual(PredicateId predicate)
        implements FigurePredicate {

        public Contextual {
            Objects.requireNonNull(predicate, "predicate");
        }
    }

    record And(List<FigurePredicate> predicates)
        implements FigurePredicate {

        public And {
            predicates = requirePredicates(predicates);
        }
    }

    record Or(List<FigurePredicate> predicates)
        implements FigurePredicate {

        public Or {
            predicates = requirePredicates(predicates);
        }
    }

    record Not(FigurePredicate predicate)
        implements FigurePredicate {

        public Not {
            Objects.requireNonNull(predicate, "predicate");
        }
    }

    enum FigureRelation {
        ALLY,
        ENEMY,
        SELF,
        NOT_SELF
    }

    enum FigureClassification {
        CHARACTER,
        MONSTER,
        NORMAL,
        ELITE,
        BOSS,
        SUMMON,
        FLYING
    }

    record ConditionId(String value) {

        public ConditionId {
            requireIdentifier(value, "Condition id");
        }
    }

    record PredicateId(String value) {

        public PredicateId {
            requireIdentifier(value, "Predicate id");
        }
    }

    private static List<FigurePredicate> requirePredicates(
        List<FigurePredicate> predicates
    ) {
        List<FigurePredicate> copy = List.copyOf(predicates);
        if (copy.isEmpty()) {
            throw new IllegalArgumentException(
                "A composite figure predicate cannot be empty"
            );
        }
        return copy;
    }

    private static void requireIdentifier(
        String value,
        String label
    ) {
        Objects.requireNonNull(value, "value");
        if (value.isBlank()) {
            throw new IllegalArgumentException(label + " cannot be blank");
        }
    }
}
