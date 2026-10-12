package io.github.toflking.gloomhaven.rules.event;

public sealed interface GameEvent permits ActionFinishedEvent, AttackAreaPlacementRequestedEvent, AttackTargetChoiceRequestedEvent, CancelActionEvent, FigureDamagedEvent, FigureDefeatedEvent, FigureMovedEvent, HealAreaPlacementRequestedEvent, HealTargetChoiceRequestedEvent, MoveChoiceRequestedEvent, SkipActionEvent, TurnEndedEvent, UndoPerformedEvent {
}
