package io.github.toflking.gloomhaven.rules.card;

public record AbilityCardId(String value) {
    public AbilityCardId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Figure ID cannot be blank"
            );
        }
    }
}
