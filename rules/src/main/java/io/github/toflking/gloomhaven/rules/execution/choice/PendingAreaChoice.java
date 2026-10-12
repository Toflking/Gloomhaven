package io.github.toflking.gloomhaven.rules.execution.choice;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;

public sealed interface PendingAreaChoice extends PendingChoice permits PendingAreaChoice.Attack, PendingAreaChoice.Heal {
    FigureId actorId();
    FigureTargetSelection.AreaPlacement selection();

    record Attack(
        FigureId actorId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, AttackPreview> previews
    ) implements PendingAreaChoice {}

    record Heal(
        FigureId actorId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, HealPreview> previews
    ) implements PendingAreaChoice {}
}
