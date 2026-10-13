package io.github.toflking.gloomhaven.rules.conditions;

import java.util.Objects;

public record ActiveCondition(
    Conditions.ConditionId conditionId,
    ConditionExpiration expiration
) {
    public ActiveCondition {
        Objects.requireNonNull(conditionId, "conditionId");
        Objects.requireNonNull(expiration, "expiration");
    }

    public sealed interface ConditionExpiration {
        record EndOfTurnInRound(int round) implements ConditionExpiration {}
        record UntilRemoved() implements ConditionExpiration {}
    }
}
