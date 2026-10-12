package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record ResolvedHealChoice(
    FigureTargetSelection selection,
    Map<FigureId, HealPreview> previews
) {
    public ResolvedHealChoice {
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
