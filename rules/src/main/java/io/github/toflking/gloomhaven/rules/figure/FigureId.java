package io.github.toflking.gloomhaven.rules.figure;

public record FigureId(String value) {
    public FigureId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Figure ID cannot be blank"
            );
        }
    }
}
