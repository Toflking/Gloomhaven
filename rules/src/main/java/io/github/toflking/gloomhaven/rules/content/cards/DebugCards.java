package io.github.toflking.gloomhaven.rules.content.cards;

import io.github.toflking.gloomhaven.rules.ability.movement.MoveAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.ActionOrigin;
import io.github.toflking.gloomhaven.rules.ability.attack.AttackAbility;
import io.github.toflking.gloomhaven.rules.ability.attack.effect.AttackEffect;
import io.github.toflking.gloomhaven.rules.ability.damage.DamageAbility;
import io.github.toflking.gloomhaven.rules.ability.condition.ConditionAbility;
import io.github.toflking.gloomhaven.rules.ability.heal.HealAbility;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetPlan;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AttackTargetPlans;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.AreaPlacementRule;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.Cardinality;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigureReference;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetAnchor;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetQuery;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetRegion;
import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.IntExpression;
import io.github.toflking.gloomhaven.rules.card.AbilityCardId;
import io.github.toflking.gloomhaven.rules.card.CardAction;
import io.github.toflking.gloomhaven.rules.card.CardDefinition;
import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexPattern;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

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
    public static final AbilityCardId ATTACK_2_AREA_RANGE_3 =
        new AbilityCardId("debug-attack-2-area-range-3");
    public static final AbilityCardId ATTACK_1_AREA_AROUND_ORIGIN =
        new AbilityCardId("debug-attack-1-area-around-origin");
    public static final AbilityCardId ATTACK_2_POISON_RANGE_2 =
        new AbilityCardId("debug-attack-2-poison-range-2");
    public static final AbilityCardId HEAL_3_RANGE_2 =
        new AbilityCardId("debug-heal-3-range-2");
    public static final AbilityCardId HEAL_3_AREA_RANGE_3 =
        new AbilityCardId("debug-heal-3-area-range-3");
    public static final AbilityCardId DAMAGE_2_RANGE_2 =
        new AbilityCardId("debug-damage-2-range-2");
    public static final AbilityCardId DAMAGE_2_AREA_RANGE_3 =
        new AbilityCardId("debug-damage-2-area-range-3");
    public static final AbilityCardId POISON_RANGE_2 =
        new AbilityCardId("debug-poison-range-2");
    public static final AbilityCardId MUDDLE_AREA_RANGE_3 =
        new AbilityCardId("debug-muddle-area-range-3");

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
            ),
            attackCard(
                ATTACK_2_AREA_RANGE_3,
                "Attack 2 Area Range 3",
                2,
                new TargetPlan.Area(
                    new HexPattern(List.of(
                        new HexPosition(0, 0),
                        new HexPosition(1, 0),
                        new HexPosition(0, 1)
                    )),
                    new AreaPlacementRule.WithinRange(
                        new IntExpression.Constant(3),
                        LineOfSightPolicy.REQUIRED,
                        true,
                        true
                    ),
                    enemiesOfActor()
                )
            ),
            attackCard(
                ATTACK_1_AREA_AROUND_ORIGIN,
                "Attack 1 Area Around Origin",
                1,
                new TargetPlan.Area(
                    new HexPattern(List.of(
                        new HexPosition(1, 0),
                        new HexPosition(0, 1)
                    )),
                    new AreaPlacementRule.AroundOrigin(
                        true,
                        false
                    ),
                    enemiesOfActor()
                )
            ),
            abilityCard(
                ATTACK_2_POISON_RANGE_2,
                "Attack 2 Poison Range 2",
                new AttackAbility(
                    AbilityValue.fixed(2),
                    new ActionOrigin.Actor(),
                    AttackTargetPlans.singleEnemyWithinRange(2),
                    List.of(new AttackEffect.Condition(
                        Conditions.POISON,
                        false
                    ))
                )
            ),
            abilityCard(
                HEAL_3_RANGE_2,
                "Heal 3 Range 2",
                new HealAbility(
                    AbilityValue.fixed(3),
                    oneAllyWithinRange(2)
                )
            ),
            abilityCard(
                HEAL_3_AREA_RANGE_3,
                "Heal 3 Area Range 3",
                new HealAbility(
                    AbilityValue.fixed(3),
                    debugAreaWithinRange(3, alliesOfActor())
                )
            ),
            abilityCard(
                DAMAGE_2_RANGE_2,
                "Damage 2 Range 2",
                new DamageAbility(
                    AbilityValue.fixed(2),
                    AttackTargetPlans.singleEnemyWithinRange(2),
                    List.of()
                )
            ),
            abilityCard(
                DAMAGE_2_AREA_RANGE_3,
                "Damage 2 Area Range 3",
                new DamageAbility(
                    AbilityValue.fixed(2),
                    debugAreaWithinRange(3),
                    List.of()
                )
            ),
            abilityCard(
                POISON_RANGE_2,
                "Poison Range 2",
                new ConditionAbility(
                    List.of(Conditions.POISON),
                    AttackTargetPlans.singleEnemyWithinRange(2)
                )
            ),
            abilityCard(
                MUDDLE_AREA_RANGE_3,
                "Muddle Area Range 3",
                new ConditionAbility(
                    List.of(Conditions.MUDDLE),
                    debugAreaWithinRange(3)
                )
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
        TargetPlan targeting
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

    private static CardDefinition abilityCard(
        AbilityCardId id,
        String name,
        io.github.toflking.gloomhaven.rules.ability.Ability ability
    ) {
        return new CardDefinition(
            id,
            name,
            1,
            50,
            new CardAction(List.of(ability)),
            new CardAction(List.of())
        );
    }

    private static TargetPlan.Area debugAreaWithinRange(int range) {
        return debugAreaWithinRange(range, enemiesOfActor());
    }

    private static TargetPlan.Area debugAreaWithinRange(
        int range,
        FigurePredicate targets
    ) {
        return new TargetPlan.Area(
            new HexPattern(List.of(
                new HexPosition(0, 0),
                new HexPosition(1, 0),
                new HexPosition(0, 1)
            )),
            new AreaPlacementRule.WithinRange(
                new IntExpression.Constant(range),
                LineOfSightPolicy.REQUIRED,
                true,
                true
            ),
            targets
        );
    }

    private static TargetPlan.Query oneAllyWithinRange(int range) {
        return new TargetPlan.Query(
            new TargetQuery(
                new TargetRegion.WithinRangeOf(
                    new TargetAnchor.AttackOrigin(),
                    new IntExpression.Constant(range)
                ),
                alliesOfActor(),
                LineOfSightPolicy.REQUIRED
            ),
            new Cardinality.ChooseUpTo(new IntExpression.Constant(1)),
            DuplicatePolicy.DISTINCT_TARGETS
        );
    }

    private static FigurePredicate enemiesOfActor() {
        return new FigurePredicate.Relationship(
            new FigureReference.Actor(),
            FigurePredicate.FigureRelation.ENEMY
        );
    }

    private static FigurePredicate alliesOfActor() {
        return new FigurePredicate.Relationship(
            new FigureReference.Actor(),
            FigurePredicate.FigureRelation.ALLY
        );
    }
}
