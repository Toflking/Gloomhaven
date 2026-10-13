package io.github.toflking.gloomhaven.rules.figure;

import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.figure.identities.FigureIdentity;

import java.util.Objects;
import java.util.Set;

public final class Figure {
    private final FigureId id;
    private final FigureDefinitionId definitionId;

    private final FigureIdentity identity;
    private final FigureOrigin origin;
    private final FactionId factionId;

    private final HealthState health;
    private final ConditionState conditions;
    private final Set<Conditions.ConditionId> conditionImmunities;

    public Figure(
        FigureId id,
        FigureDefinitionId definitionId,
        FigureIdentity identity,
        FigureOrigin origin,
        FactionId factionId,
        HealthState health,
        ConditionState conditions
    ) {
        this(
            id,
            definitionId,
            identity,
            origin,
            factionId,
            health,
            conditions,
            Set.of()
        );
    }

    public Figure(
        FigureId id,
        FigureDefinitionId definitionId,
        FigureIdentity identity,
        FigureOrigin origin,
        FactionId factionId,
        HealthState health,
        ConditionState conditions,
        Set<Conditions.ConditionId> conditionImmunities
    ) {
        this.id = Objects.requireNonNull(id, "id");
        this.definitionId = Objects.requireNonNull(definitionId, "definitionId");
        this.identity = Objects.requireNonNull(identity, "identity");
        this.origin = Objects.requireNonNull(origin, "origin");
        this.factionId = Objects.requireNonNull(factionId, "factionId");
        this.health = Objects.requireNonNull(health, "health");
        this.conditions = Objects.requireNonNull(conditions, "conditions");
        this.conditionImmunities = Set.copyOf(
            Objects.requireNonNull(
                conditionImmunities,
                "conditionImmunities"
            )
        );
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

    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Heal cannot be over maximum"
            );
        }

        health.setCurrentHealth(Math.min(health.maximumHealth, health.currentHealth() + amount));
    }

    public Set<Conditions.ConditionId> conditionImmunities() {
        return conditionImmunities;
    }

    public boolean isImmuneTo(Conditions.ConditionId conditionId) {
        return conditionImmunities.contains(
            Objects.requireNonNull(conditionId, "conditionId")
        );
    }
}
