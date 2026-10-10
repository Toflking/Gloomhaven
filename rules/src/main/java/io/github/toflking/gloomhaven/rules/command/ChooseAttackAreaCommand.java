package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.execution.attack.AttackChoice;

import java.util.Objects;

public record ChooseAttackAreaCommand(
    AttackChoice.AreaPlacementOption placement
) implements GameCommand {
    public ChooseAttackAreaCommand {
        Objects.requireNonNull(placement, "placement");
    }
}
