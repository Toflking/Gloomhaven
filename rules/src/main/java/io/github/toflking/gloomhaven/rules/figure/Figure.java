package io.github.toflking.gloomhaven.rules.figure;

import io.github.toflking.gloomhaven.rules.figure.identities.FigureIdentity;

import java.util.Objects;

public final class Figure {
    private final FigureId id;
    private final FigureDefinitionId definitionId;

    private final FigureIdentity identity;
    private final FigureOrigin origin;
    private final FactionId factionId;

    private final HealthState health;
    private final ConditionState conditions;


    public Figure(
        FigureId id,
        FigureDefinitionId definitionId,
        FigureIdentity identity,
        FigureOrigin origin,
        FactionId factionId,
        HealthState health, ConditionState conditions
    ) {
        this.id = Objects.requireNonNull(id, "id");
        this.definitionId = Objects.requireNonNull(definitionId, "definitionId");
        this.identity = Objects.requireNonNull(identity, "identity");
        this.origin = Objects.requireNonNull(origin, "origin");
        this.factionId = Objects.requireNonNull(factionId, "factionId");
        this.health = Objects.requireNonNull(health, "health");
        this.conditions = Objects.requireNonNull(conditions, "conditions");
    }

    public FigureId id() {
        return id;
    }

    public FigureDefinitionId definitionId() {
        return definitionId;
    }

    public FigureIdentity type() {
        return identity;
    }

    public FigureOrigin origin() {
        return origin;
    }

    public FactionId factionId() {
        return factionId;
    }

    public HealthState health() {
        return health;
    }

    public ConditionState conditions() {
        return conditions;
    }

    public boolean isDefeated() {
        return health.currentHealth <= 0;
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Damage cannot be negative"
            );
        }

        health.setCurrentHealth(Math.max(0, health.currentHealth() - amount));
    }
}
