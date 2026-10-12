package io.github.toflking.gloomhaven.rules.execution.choice;

import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public sealed interface PendingFigureTargetChoice extends PendingChoice permits PendingFigureTargetChoice.Attack, PendingFigureTargetChoice.Heal {
    FigureId actorId();
    FigureTargetSelection.FigureTargets selection();

    record Attack(
        FigureId actorId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, AttackPreview> previews
    ) implements PendingFigureTargetChoice {
        public Attack {
            Objects.requireNonNull(actorId, "actorId");
            Objects.requireNonNull(selection, "selection");
            previews = Map.copyOf(previews);
        }
    }

    record Heal(
        FigureId actorId,
        FigureTargetSelection.FigureTargets selection,
        Map<FigureId, HealPreview> previews
    ) implements PendingFigureTargetChoice {
        public Heal {
            Objects.requireNonNull(actorId, "actorId");
            Objects.requireNonNull(selection, "selection");
            previews = Map.copyOf(previews);
        }
    }
}
