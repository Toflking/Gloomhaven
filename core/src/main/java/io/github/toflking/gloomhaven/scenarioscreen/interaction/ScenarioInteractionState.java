package io.github.toflking.gloomhaven.scenarioscreen.interaction;

public sealed interface ScenarioInteractionState
    permits AttackAreaPlacementInteraction, AttackTargetInteraction, ConditionAreaPlacementInteraction, ConditionTargetInteraction, DamageAreaPlacementInteraction, DamageTargetInteraction, HealAreaPlacementInteraction, HealTargetInteraction, IdleInteraction, MovePathInteraction {
}
