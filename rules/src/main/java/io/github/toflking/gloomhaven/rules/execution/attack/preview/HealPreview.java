package io.github.toflking.gloomhaven.rules.execution.attack.preview;

public record HealPreview(
    int healValue,
    boolean poison,
    int projectedHealing
) {}
