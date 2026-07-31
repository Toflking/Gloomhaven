package io.github.toflking.gloomhaven.scenarioscreen.interaction;

public sealed interface ScenarioInteractionState
    permits AreaPlacementInteraction, AttackTargetInteraction, IdleInteraction, MovePathInteraction {
}
