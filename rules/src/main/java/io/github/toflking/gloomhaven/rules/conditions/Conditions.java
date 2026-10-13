package io.github.toflking.gloomhaven.rules.conditions;

import java.util.Objects;

public final class Conditions {
    public static final ConditionDefinition INVISIBLE = new ConditionDefinition(new ConditionId("invisible"), ConditionExpirationRule.END_OF_TARGETS_NEXT_TURN);
    public static final ConditionDefinition STRENGTHEN = new ConditionDefinition(new ConditionId("strengthen"), ConditionExpirationRule.END_OF_TARGETS_NEXT_TURN);
    public static final ConditionDefinition WOUND = new ConditionDefinition(new ConditionId("wound"), ConditionExpirationRule.UNTIL_REMOVED);
    public static final ConditionDefinition POISON = new ConditionDefinition(new ConditionId("poison"), ConditionExpirationRule.UNTIL_REMOVED);
    public static final ConditionDefinition IMMOBILIZE = new ConditionDefinition(new ConditionId("immobilize"), ConditionExpirationRule.END_OF_TARGETS_NEXT_TURN);
    public static final ConditionDefinition DISARM = new ConditionDefinition(new ConditionId("disarm"), ConditionExpirationRule.END_OF_TARGETS_NEXT_TURN);
    public static final ConditionDefinition STUN = new ConditionDefinition(new ConditionId("stun"), ConditionExpirationRule.END_OF_TARGETS_NEXT_TURN);
    public static final ConditionDefinition MUDDLE = new ConditionDefinition(new ConditionId("muddle"), ConditionExpirationRule.END_OF_TARGETS_NEXT_TURN);

    public record ConditionId(String conditionId) {

        public ConditionId {
            Objects.requireNonNull(conditionId, "conditionId");
        }
    }

    public record ConditionDefinition(
        ConditionId id,
        ConditionExpirationRule expirationRule
    ) {
        public ConditionDefinition {
            Objects.requireNonNull(id, "id");
            Objects.requireNonNull(expirationRule, "expirationRule");
        }
    }

    public enum ConditionExpirationRule {
        END_OF_TARGETS_NEXT_TURN,
        UNTIL_REMOVED
    }
}
