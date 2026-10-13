package io.github.toflking.gloomhaven.rules.event;

import io.github.toflking.gloomhaven.rules.scenario.FigureEffectView;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.Map;

/** Complete current effect views for all figures whose view changed. */
public record FigureEffectsChangedEvent(
    Map<FigureId, FigureEffectView> effects
) implements GameEvent {
    public FigureEffectsChangedEvent {
        effects = Map.copyOf(effects);
    }
}
