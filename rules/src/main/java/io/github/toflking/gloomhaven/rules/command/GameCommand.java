package io.github.toflking.gloomhaven.rules.command;

public sealed interface GameCommand permits CancelActionCommand, ChooseAttackAreaCommand, ChooseFigureTargetsCommand, ChooseMovePathCommand, EndTurnCommand, PlayCardActionCommand, SkipActionCommand, UndoCommand {
}
