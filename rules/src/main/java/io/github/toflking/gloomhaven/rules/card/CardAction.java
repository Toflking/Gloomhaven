package io.github.toflking.gloomhaven.rules.card;

import io.github.toflking.gloomhaven.rules.ability.Ability;

import java.util.List;

public record CardAction(List<Ability> abilities) {
    public CardAction {
        abilities = List.copyOf(abilities);
    }
}
