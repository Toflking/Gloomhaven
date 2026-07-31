package io.github.toflking.gloomhaven.rules.content.cards;

import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardAction;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CardCatalog {

    private final Map<AbilityCardId, CardDefinition> cards;

    public CardCatalog(Collection<CardDefinition> cardDefinitions) {
        this.cards = cardDefinitions.stream()
            .collect(Collectors.toUnmodifiableMap(
                CardDefinition::id,
                Function.identity()
            ));
    }

    public CardDefinition require(AbilityCardId id) {
        CardDefinition cardDefinition = cards.get(id);

        if (cardDefinition == null) {
            throw new IllegalArgumentException(
                "Unknown card: " + id
            );
        }

        return cardDefinition;
    }

    public CardAction resolve(AbilitySource source) {
        return require(source.cardId())
            .action(source.cardHalf());
    }
}
