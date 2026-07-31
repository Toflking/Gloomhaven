package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;

import java.util.Objects;

public sealed interface FigureReference
    permits FigureReference.Actor,
    FigureReference.Owner,
    FigureReference.Controller,
    FigureReference.CurrentTarget,
    FigureReference.Stored {

    record Actor() implements FigureReference {
    }

    record Owner() implements FigureReference {
    }

    record Controller() implements FigureReference {
    }

    record CurrentTarget() implements FigureReference {
    }

    record Stored(ExecutionVariableId variable)
        implements FigureReference {

        public Stored {
            Objects.requireNonNull(variable, "variable");
        }
    }
}
