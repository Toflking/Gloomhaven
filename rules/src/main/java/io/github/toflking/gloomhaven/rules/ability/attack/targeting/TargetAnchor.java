package io.github.toflking.gloomhaven.rules.ability.attack.targeting;

import io.github.toflking.gloomhaven.rules.ability.value.ExecutionVariableId;

import java.util.Objects;

public sealed interface TargetAnchor
    permits TargetAnchor.AttackOrigin,
    TargetAnchor.Actor,
    TargetAnchor.Owner,
    TargetAnchor.Controller,
    TargetAnchor.PreviousTarget,
    TargetAnchor.StoredFigure,
    TargetAnchor.StoredHex {

    record AttackOrigin() implements TargetAnchor {
    }

    record Actor() implements TargetAnchor {
    }

    record Owner() implements TargetAnchor {
    }

    record Controller() implements TargetAnchor {
    }

    record PreviousTarget() implements TargetAnchor {
    }

    record StoredFigure(ExecutionVariableId variable)
        implements TargetAnchor {

        public StoredFigure {
            Objects.requireNonNull(variable, "variable");
        }
    }

    record StoredHex(ExecutionVariableId variable)
        implements TargetAnchor {

        public StoredHex {
            Objects.requireNonNull(variable, "variable");
        }
    }
}
