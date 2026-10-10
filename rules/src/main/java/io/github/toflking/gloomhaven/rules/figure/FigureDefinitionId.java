package io.github.toflking.gloomhaven.rules.figure;

public record FigureDefinitionId(String value) {
    public FigureDefinitionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Figure Definition ID cannot be blank"
            );
        }
    }
}
