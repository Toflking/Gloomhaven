package io.github.toflking.gloomhaven.rules.execution.attack.preview;

public record AttackPreview(
    int attackValueBeforeModifier,
    int assumedModifier,
    int shield,
    int pierce,
    int effectiveShield,
    boolean poison,
    int projectedDamage,
    int retaliateDamage,
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
            0,
            EffectPreview.empty()
        );
    }

    public AttackPreview(
        int attackValueBeforeModifier,
        int assumedModifier,
        int shield,
        int pierce,
        int effectiveShield,
        boolean poison,
        int projectedDamage,
        EffectPreview effects
    ) {
        this(
            attackValueBeforeModifier,
            assumedModifier,
            shield,
            pierce,
            effectiveShield,
            poison,
            projectedDamage,
            0,
            effects
        );
    }
}
