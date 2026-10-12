package io.github.toflking.gloomhaven.rules.conditions;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;

public enum Conditions {
    INVISIBLE("invisible"),
    STRENGTHEN("strengthen"),
    WOUND("wound"),
    POISON("poison"),
    IMMOBILIZE("immobilize"),
    DISARM("disarm"),
    STUN("stun"),
    MUDDLE("muddle");

    private final FigurePredicate.ConditionId conditionId;

    Conditions(String conditionId) {
        this.conditionId = new FigurePredicate.ConditionId(conditionId);
    }

    public FigurePredicate.ConditionId id() {
        return conditionId;
    }
}
