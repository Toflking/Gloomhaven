package io.github.toflking.gloomhaven.rules.event;

public sealed interface GameEvent permits ActionFinishedEvent, AreaPlacementRequestedEvent, CancelActionEvent, FigureDamagedEvent, FigureDefeatedEvent, FigureMovedEvent, MoveChoiceRequestedEvent, SkipActionEvent, TargetChoiceRequestedEvent, TurnEndedEvent, UndoPerformedEvent {
}
