package io.github.toflking.gloomhaven.rules.execution.attack.preview;

/** Exact direct damage; no modifier, Shield, Pierce, or Poison applies. */
public record DamagePreview(
    int damageValue,
    int projectedDamage,
    EffectPreview effects
) {
    public DamagePreview(int damageValue, int projectedDamage) {
        this(damageValue, projectedDamage, EffectPreview.empty());
    }
}
