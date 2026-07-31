package io.github.toflking.gloomhaven.rules.content.cards;

import io.github.toflking.gloomhaven.rules.ability.movement.MoveAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.card.CardHalf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DebugCardsTest {

    @Test
    void combinedDebugActionMovesThenAttacks() {
        CardDefinition card = new CardCatalog(DebugCards.all())
            .require(DebugCards.MOVE_2_ATTACK_3);

        assertEquals(
            2,
            card.action(CardHalf.TOP).abilities().size()
        );
        assertInstanceOf(
            MoveAbility.class,
            card.action(CardHalf.TOP).abilities().get(0)
        );
        assertInstanceOf(
            AttackAbility.class,
            card.action(CardHalf.TOP).abilities().get(1)
        );
    }
}
