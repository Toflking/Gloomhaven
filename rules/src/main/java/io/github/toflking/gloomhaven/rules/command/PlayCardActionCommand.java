package io.github.toflking.gloomhaven.rules.command;

import io.github.toflking.gloomhaven.rules.card.AbilitySource;

public record PlayCardActionCommand(AbilitySource source) implements GameCommand {
}
