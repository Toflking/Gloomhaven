package io.github.toflking.gloomhaven.rules.execution.attack.preview;

public record AttackPreview(
    int attackValueBeforeModifier,
    int assumedModifier,
    int shield,
    int pierce,
    int effectiveShield,
    boolean poison,
    int projectedDamage,
    EffectPreview effects
) {
    public AttackPreview(
        int attackValueBeforeModifier,
        int assumedModifier,
        int shield,
        int pierce,
        int effectiveShield,
        boolean poison,
        int projectedDamage
    ) {
        this(
            attackValueBeforeModifier,
            assumedModifier,
            shield,
            pierce,
            effectiveShield,
            poison,
            projectedDamage,
            EffectPreview.empty()
        );
    }
}
