package io.github.toflking.gloomhaven.rules.content.cards;

import io.github.toflking.gloomhaven.rules.ability.movement.MoveAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.condition.ConditionAbility;
import io.github.toflking.gloomhaven.rules.ability.damage.DamageAbility;
import io.github.toflking.gloomhaven.rules.ability.heal.HealAbility;
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

    @Test
    void exposesCurrentTargetAndAreaDebugActions() {
        CardCatalog catalog = new CardCatalog(DebugCards.all());

        assertTopAbilityType(
            catalog,
            DebugCards.ATTACK_2_POISON_RANGE_2,
            AttackAbility.class
        );
        assertTopAbilityType(
            catalog,
            DebugCards.HEAL_3_RANGE_2,
            HealAbility.class
        );
        assertTopAbilityType(
            catalog,
            DebugCards.HEAL_3_AREA_RANGE_3,
            HealAbility.class
        );
        assertTopAbilityType(
            catalog,
            DebugCards.DAMAGE_2_RANGE_2,
            DamageAbility.class
        );
        assertTopAbilityType(
            catalog,
            DebugCards.DAMAGE_2_AREA_RANGE_3,
            DamageAbility.class
        );
        assertTopAbilityType(
            catalog,
            DebugCards.POISON_RANGE_2,
            ConditionAbility.class
        );
        assertTopAbilityType(
            catalog,
            DebugCards.MUDDLE_AREA_RANGE_3,
            ConditionAbility.class
        );
    }

    private static void assertTopAbilityType(
        CardCatalog catalog,
        io.github.toflking.gloomhaven.rules.card.AbilityCardId cardId,
        Class<?> expectedType
    ) {
        CardDefinition card = catalog.require(cardId);
        assertInstanceOf(
            expectedType,
            card.action(CardHalf.TOP).abilities().getFirst()
        );
    }
}
