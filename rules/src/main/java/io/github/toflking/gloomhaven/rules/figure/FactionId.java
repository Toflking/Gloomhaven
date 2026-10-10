package io.github.toflking.gloomhaven.rules.figure;

public record FactionId(String value) {
    public FactionId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Figure ID cannot be blank"
            );
        }
    }
}
