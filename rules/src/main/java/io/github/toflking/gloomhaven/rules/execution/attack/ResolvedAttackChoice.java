package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record ResolvedAttackChoice(
    FigureTargetSelection selection,
    Map<FigureId, AttackPreview> previews
) {
    public ResolvedAttackChoice {
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
