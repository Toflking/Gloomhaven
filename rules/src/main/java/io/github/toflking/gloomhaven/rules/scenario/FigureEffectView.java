package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;

import java.util.List;
import java.util.Objects;

public record FigureEffectView(
    FigureId figureId,
    int shield,
    List<Retaliate> retaliates,
    List<ActiveEffect> activeEffects
) {
    public FigureEffectView {
        Objects.requireNonNull(figureId, "figureId");
        if (shield < 0) {
            throw new IllegalArgumentException("Shield cannot be negative");
        }
        retaliates = List.copyOf(retaliates);
        activeEffects = List.copyOf(activeEffects);
    }

    public record Retaliate(
        ActiveEffect.ActiveEffectId effectId,
        ActiveEffect.EffectSource source,
        int amount,
        int range,
        LineOfSightPolicy lineOfSight
    ) {
        public Retaliate {
            Objects.requireNonNull(effectId, "effectId");
            Objects.requireNonNull(source, "source");
            Objects.requireNonNull(lineOfSight, "lineOfSight");
            if (amount <= 0 || range <= 0) {
                throw new IllegalArgumentException(
                    "Retaliate amount and range must be positive"
                );
            }
        }
    }
}
