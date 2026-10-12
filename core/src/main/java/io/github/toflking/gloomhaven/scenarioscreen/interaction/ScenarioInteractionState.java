package io.github.toflking.gloomhaven.scenarioscreen.interaction;

public sealed interface ScenarioInteractionState
    permits AttackAreaPlacementInteraction, AttackTargetInteraction, HealAreaPlacementInteraction, HealTargetInteraction, IdleInteraction, MovePathInteraction {
}
