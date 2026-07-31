package io.github.toflking.gloomhaven.rules.content.cards;

import io.github.toflking.gloomhaven.rules.ability.movement.MoveAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.Cardinality;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;

import java.util.List;

public final class DebugCards {
    public static final AbilityCardId ATTACK_2_RANGE_2 =
        new AbilityCardId("debug-attack-2-range-2");
    public static final AbilityCardId ATTACK_3_RANGE_3_TARGET_2 =
        new AbilityCardId("debug-attack-3-range-3-target-2");
    public static final AbilityCardId ATTACK_2_RANGE_3_EXACT_2 =
        new AbilityCardId("debug-attack-2-range-3-exact-2");
    public static final AbilityCardId ATTACK_1_ALL_ADJACENT =
        new AbilityCardId("debug-attack-1-all-adjacent");
    public static final AbilityCardId MOVE_2_ATTACK_3 =
        new AbilityCardId("debug-move-2-attack-3");

    public static List<CardDefinition> all() {
        return List.of(
            movementCard("debug-move-1", 1),
            movementCard("debug-move-2", 2),
            movementCard("debug-move-3", 3),
            movementCard("debug-move-4", 4),
            attackCard(
                ATTACK_2_RANGE_2,
                "Attack 2 Range 2",
                2,
                AttackTargetPlans.singleEnemyWithinRange(2)
            ),
            attackCard(
                ATTACK_3_RANGE_3_TARGET_2,
                "Attack 3 Range 3 Target 2",
                3,
                AttackTargetPlans.enemiesWithinRange(
                    new IntExpression.Constant(3),
                    new Cardinality.ChooseUpTo(
                        new IntExpression.Constant(2)
                    )
                )
            ),
            attackCard(
                ATTACK_2_RANGE_3_EXACT_2,
                "Attack 2 Range 3 Exactly 2",
                2,
                AttackTargetPlans.enemiesWithinRange(
                    new IntExpression.Constant(3),
                    new Cardinality.ChooseExactly(
                        new IntExpression.Constant(2)
                    )
                )
            ),
            attackCard(
                ATTACK_1_ALL_ADJACENT,
                "Attack 1 All Adjacent",
                1,
                AttackTargetPlans.allAdjacentEnemies()
            ),
            new CardDefinition(
                MOVE_2_ATTACK_3,
                "Move 2, Attack 3 Range 3",
                1,
                50,
                new CardAction(List.of(
                    new MoveAbility(2, MovementType.NORMAL),
                    new AttackAbility(
                        AbilityValue.fixed(3),
                        AttackTargetPlans.singleEnemyWithinRange(3)
                    )
                )),
                new CardAction(List.of())
            )
        );
    }

    private static CardDefinition movementCard(String id, int amount) {
        return new CardDefinition(
            new AbilityCardId(id),
            "Move " + amount,
            1,
            50,
            new CardAction(List.of(
                new MoveAbility(
                    amount,
                    MovementType.NORMAL
                )
            )),
            new CardAction(List.of())
        );
    }

    private static CardDefinition attackCard(
        AbilityCardId id,
        String name,
        int amount,
        AttackTargetPlan targeting
    ) {
        return new CardDefinition(
            id,
            name,
            1,
            50,
            new CardAction(List.of(
                new AttackAbility(
                    AbilityValue.fixed(amount),
                    targeting
                )
            )),
            new CardAction(List.of())
        );
    }
}
