package io.github.toflking.gloomhaven.rules.ability.movement;

import io.github.toflking.gloomhaven.rules.ability.Ability;

public record MoveAbility(int amount, MovementType type)
    implements Ability {

    public MoveAbility {
        if (amount < 0) {
            throw new IllegalArgumentException(
                "Movement cannot be negative"
            );
        }
    }
}
