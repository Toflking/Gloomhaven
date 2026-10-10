package io.github.toflking.gloomhaven.rules.figure;

public record CharacterTypeId(String value) {
    public CharacterTypeId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Monster Type ID cannot be blank"
            );
        }
    }
}
