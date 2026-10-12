package io.github.toflking.gloomhaven.rules.ability.attack;

import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;

import java.util.List;
import java.util.Objects;

public sealed interface ActionOrigin
    permits ActionOrigin.Actor,
    ActionOrigin.StoredFigure,
    ActionOrigin.StoredHex,
    ActionOrigin.Choose {

    record Actor() implements ActionOrigin {
    }

    record StoredFigure(ExecutionVariableId variable)
        implements ActionOrigin {

        public StoredFigure {
            Objects.requireNonNull(variable, "variable");
        }
    }

    record StoredHex(ExecutionVariableId variable)
        implements ActionOrigin {

        public StoredHex {
            Objects.requireNonNull(variable, "variable");
        }
    }

    record Choose(List<ActionOrigin> options)
        implements ActionOrigin {

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
