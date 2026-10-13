package io.github.toflking.gloomhaven.rules.figure;

import io.github.toflking.gloomhaven.rules.conditions.ActiveCondition;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionStateTest {

    @Test
    void removesAnExpiredTurnCondition() {
        ConditionState state = new ConditionState(Map.of());
        state.addCondition(
            Conditions.STUN.id(),
            new ActiveCondition.ConditionExpiration.EndOfTurnInRound(2)
        );

        assertFalse(state.removeIfExpired(Conditions.STUN.id(), 1));
        assertTrue(state.contains(Conditions.STUN.id()));
        assertTrue(state.removeIfExpired(Conditions.STUN.id(), 2));
        assertFalse(state.contains(Conditions.STUN.id()));
    }

    @Test
    void keepsAConditionThatLastsUntilExplicitlyRemoved() {
        ConditionState state = new ConditionState(Map.of());
        state.addCondition(
            Conditions.POISON.id(),
            new ActiveCondition.ConditionExpiration.UntilRemoved()
        );

        assertFalse(state.removeIfExpired(Conditions.POISON.id(), 20));
        assertTrue(state.contains(Conditions.POISON.id()));
    }
}
