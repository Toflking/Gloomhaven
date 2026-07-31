package io.github.toflking.gloomhaven.rules.card;

public record CardDefinition(
    AbilityCardId id,
    String name,
    int level,
    int initiative,
    CardAction top,
    CardAction bottom
) {
    public CardAction action(CardHalf half) {
        return switch (half) {
            case TOP -> top;
            case BOTTOM -> bottom;
        };
    }
}
