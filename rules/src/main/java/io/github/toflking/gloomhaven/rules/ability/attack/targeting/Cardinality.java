package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;

import java.util.Objects;

public sealed interface Cardinality
    permits Cardinality.ChooseUpTo,
    Cardinality.ChooseExactly,
    Cardinality.All {

    record ChooseUpTo(IntExpression maximum)
        implements Cardinality {

        public ChooseUpTo {
            Objects.requireNonNull(maximum, "maximum");
        }
    }

    record ChooseExactly(IntExpression count)
        implements Cardinality {

        public ChooseExactly {
            Objects.requireNonNull(count, "count");
        }
    }

    record All() implements Cardinality {
    }
}
