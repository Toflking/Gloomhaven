package io.github.toflking.gloomhaven.rules.execution.choice;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.DamagePreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.ConditionPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;

public sealed interface PendingAreaChoice extends PendingChoice permits PendingAreaChoice.Attack, PendingAreaChoice.Heal, PendingAreaChoice.Damage, PendingAreaChoice.Condition {
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

    record Damage(
        FigureId actorId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, DamagePreview> previews
    ) implements PendingAreaChoice {}

    record Condition(
        FigureId actorId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, ConditionPreview> previews
    ) implements PendingAreaChoice {}
}
