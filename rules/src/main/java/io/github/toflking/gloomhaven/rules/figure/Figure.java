package io.github.toflking.gloomhaven.rules.figure;

import java.util.Objects;

public final class Figure {

    private final FigureId id;
    private final FigureType type;
    private final int maximumHealth;

    private int health;

    public Figure(
        FigureId id,
        FigureType type,
        int maximumHealth
    ) {
        this(id, type, maximumHealth, maximumHealth);
    }

    public Figure(
        FigureId id,
        FigureType type,
        int maximumHealth,
        int health
    ) {
        this.id = Objects.requireNonNull(id, "id");
        this.type = Objects.requireNonNull(type, "type");
        if (maximumHealth < 0) {
            throw new IllegalArgumentException(
                "Maximum health cannot be negative"
            );
        }
        if (health < 0 || health > maximumHealth) {
            throw new IllegalArgumentException(
                "Health must be within the figure health range"
            );
        }
        this.maximumHealth = maximumHealth;
        this.health = health;
    }

    public FigureId id() {
        return id;
    }

    public FigureType type() {
        return type;
    }

    public int health() {
        return health;
    }

    public int maximumHealth() {
        return maximumHealth;
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Damage cannot be negative"
            );
        }

        health = Math.max(0, health - amount);
    }
}
