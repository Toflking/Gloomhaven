package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;

import java.util.List;
import java.util.Objects;

public sealed interface AttackOrigin
    permits AttackOrigin.Actor,
    AttackOrigin.StoredFigure,
    AttackOrigin.StoredHex,
    AttackOrigin.Choose {

    record Actor() implements AttackOrigin {
    }

    record StoredFigure(ExecutionVariableId variable)
        implements AttackOrigin {

        public StoredFigure {
            Objects.requireNonNull(variable, "variable");
        }
    }

    record StoredHex(ExecutionVariableId variable)
        implements AttackOrigin {

        public StoredHex {
            Objects.requireNonNull(variable, "variable");
        }
    }

    record Choose(List<AttackOrigin> options)
        implements AttackOrigin {

        public Choose {
            options = List.copyOf(options);
            if (options.size() < 2) {
                throw new IllegalArgumentException(
                    "An origin choice requires at least two options"
                );
            }
        }
    }
}
