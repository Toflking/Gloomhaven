package io.github.toflking.gloomhaven.rules.ability.value;

import java.util.Objects;

public record ExecutionVariableId(String value) {

    public ExecutionVariableId {
        Objects.requireNonNull(value, "value");
        if (value.isBlank()) {
            throw new IllegalArgumentException(
                "Execution variable id cannot be blank"
            );
        }
    }
}
