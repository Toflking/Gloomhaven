package io.github.toflking.gloomhaven.rules.command;

public sealed interface GameCommand permits CancelActionCommand, ChooseAttackAreaCommand, ChooseAttackTargetsCommand, ChooseMovePathCommand, EndTurnCommand, PlayCardActionCommand, SkipActionCommand, UndoCommand {
}
