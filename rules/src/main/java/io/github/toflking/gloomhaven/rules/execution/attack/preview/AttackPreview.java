package io.github.toflking.gloomhaven.rules.execution.attack.preview;

public record AttackPreview(
    int attackValueBeforeModifier,
    int assumedModifier,
    int shield,
    int pierce,
    int effectiveShield,
    boolean poison,
    int projectedDamage
) {}
