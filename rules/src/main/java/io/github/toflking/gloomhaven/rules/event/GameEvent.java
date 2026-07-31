package io.github.toflking.gloomhaven.rules.event;

public sealed interface GameEvent permits ActionFinishedEvent, CancelActionEvent, FigureDamagedEvent, FigureDefeatedEvent, FigureMovedEvent, MoveChoiceRequestedEvent, SkipActionEvent, TargetChoiceRequestedEvent, TurnEndedEvent, UndoPerformedEvent {
}
