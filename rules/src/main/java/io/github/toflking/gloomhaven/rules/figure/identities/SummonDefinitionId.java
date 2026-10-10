package io.github.toflking.gloomhaven.rules.figure.identities;

public record SummonDefinitionId(String value) {
    public SummonDefinitionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Figure ID cannot be blank"
            );
        }
    }
}
