package io.github.toflking.gloomhaven.rules.execution.choice;

public sealed interface PendingChoice
    permits PendingMoveChoice,
    PendingAttackChoice {
}
