package io.github.toflking.gloomhaven.rules.figure;

import io.github.toflking.gloomhaven.rules.conditions.ActiveCondition;
import io.github.toflking.gloomhaven.rules.conditions.Conditions.ConditionId;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConditionState {
    Map<ConditionId, ActiveCondition> conditions;

    public ConditionState(Map<ConditionId, ActiveCondition> conditions) {
        this.conditions = Map.copyOf(conditions);
    }

    public Map<ConditionId, ActiveCondition> conditions() {
        return conditions;
    }

    public void addCondition(ConditionId conditionId, ActiveCondition.ConditionExpiration expiration) {
        Objects.requireNonNull(conditionId, "conditionId");
        Objects.requireNonNull(expiration, "expiration");
        Map<ConditionId, ActiveCondition> updated = new HashMap<>(conditions);
        updated.put(conditionId, new ActiveCondition(conditionId, expiration));
        conditions = Map.copyOf(updated);
    }

    public boolean contains(ConditionId condition) {
        return conditions.containsKey(Objects.requireNonNull(condition, "condition"));
    }

    public boolean remove(ConditionId condition) {
        Objects.requireNonNull(condition, "condition");
        if (!conditions.containsKey(condition)) {
            return false;
        }

        Map<ConditionId, ActiveCondition> updated = new HashMap<>(conditions);
        updated.remove(condition);
        conditions = Map.copyOf(updated);
        return true;
    }

    public boolean removeIfExpired(ConditionId conditionId, int roundNumber) {
        ActiveCondition activeCondition = conditions.get(conditionId);
        if (!(activeCondition.expiration() instanceof ActiveCondition.ConditionExpiration.EndOfTurnInRound(int round)))
            return false;
        if (round > roundNumber) {
            return false;
        }
        Map<ConditionId, ActiveCondition> updated = new HashMap<>(conditions);
        updated.remove(conditionId);
        conditions = Map.copyOf(updated);
        return true;
    }
}
