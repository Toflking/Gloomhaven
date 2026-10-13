package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.execution.attack.preview.DamagePreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record ResolvedDamageChoice(
    FigureTargetSelection selection,
    Map<FigureId, DamagePreview> previews
) {
    public ResolvedDamageChoice {
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
