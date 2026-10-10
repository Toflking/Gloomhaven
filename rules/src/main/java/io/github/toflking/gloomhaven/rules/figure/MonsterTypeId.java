package io.github.toflking.gloomhaven.rules.figure;

public record MonsterTypeId(String value) {
    public MonsterTypeId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                "Monster Type ID cannot be blank"
            );
        }
    }
}
