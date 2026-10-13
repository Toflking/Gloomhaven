package io.github.toflking.gloomhaven.rules.execution.attack;

import io.github.toflking.gloomhaven.rules.execution.attack.preview.ConditionPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;
import java.util.Objects;

public record ResolvedConditionChoice(
    FigureTargetSelection selection,
    Map<FigureId, ConditionPreview> previews
) {
    public ResolvedConditionChoice {
        Objects.requireNonNull(selection, "selection");
        previews = Map.copyOf(previews);
    }
}
