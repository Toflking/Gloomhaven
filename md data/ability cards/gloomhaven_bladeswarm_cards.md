# Gloomhaven – Bladeswarm (BS) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/bs/`  
**Primary image source:** [Worldhaven Bladeswarm image folder](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/bs)  
**Files covered:** 39 PNG files = 30 ability-card fronts + 1 card back + 8 summon reference cards.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, summon statistics, item-slot icons, elements, duration, loss, and enhancement marks use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, TARGET, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Summon panels use HEALTH for the hit-point icon and `—` for a printed dash. Element icons are represented by INFUSE/CONSUME and their names. Hand-slot icons are written as “one-hand” or “two-hand”.

## Elements

```text
INFUSE FIRE
INFUSE ICE
INFUSE AIR
INFUSE EARTH
INFUSE LIGHT
INFUSE DARK
```

The element line is resolved after performing the associated ability half.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn until removed or its attached summon leaves play.
- `[LOSS]` — the card is lost for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[UNRECOVERABLE]` — once lost, the card cannot be recovered, regardless of how it became lost.

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: SUMMON HP (2 marks), SUMMON ATTACK
ENHANCEABLE: MOVE
```

---

# Card back

## `gh-bs-back.png`

Bladeswarm ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Call of the Grave

**File:** `gh-call-of-the-grave.png`  
**Level:** 1  
**Initiative:** 49

### TOP

```text
ATTACK 2

All summoned allies you own adjacent
to the target of the attack perform
ATTACK +0
on the target.
```

The summon attacks are separate attacks performed after the Bladeswarm's attack.

### BOTTOM

```text
All enemies with WOUND suffer 2
damage at the start of each of their turns
instead of suffering 1 damage.
INFUSE DARK
XP +2
[PERSISTENT, LOSS]
```

---

## Corrupting Parasites

**File:** `gh-corrupting-parasites.png`  
**Level:** 1  
**Initiative:** 97

### TOP

```text
Summon Soul Leeches
HEALTH 4
MOVE 3
ATTACK X
RANGE —
X is the number
of adjacent
figures.
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP (2 marks)`

The attack value is recalculated whenever the Soul Leeches attack.

### BOTTOM

```text
WOUND
Target one adjacent enemy

HEAL 1
Self
```

---

## Death March

**File:** `gh-death-march.png`  
**Level:** 1  
**Initiative:** 84

### TOP

```text
Summon Creeping Beetles
HEALTH 5
MOVE 2
ATTACK 2
RANGE —
All adjacent allies
may perform
“MOVE 1”
at end of turn.
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP (2 marks), SUMMON ATTACK`

The optional MOVE 1 is granted separately to every ally adjacent to the Creeping Beetles at the end of their turn.

### BOTTOM

```text
MOVE 4
This movement ignores difficult
and hazardous terrain.
```

---

## Deflecting Blades

**File:** `gh-deflecting-blades.png`  
**Level:** 1  
**Initiative:** 11

### TOP

```text
ATTACK 1
TARGET 3
Target enemies within two hexes
MUDDLE
```

### BOTTOM

```text
MOVE 2

When attacked this round, consider any
positive and x2 attack modifier cards the
enemy draws to be a Null instead.
[ROUND]
```

`ENHANCEABLE: MOVE`

---

## Engulfing Stingers

**File:** `gh-engulfing-stingers.png`  
**Level:** 1  
**Initiative:** 88

### TOP

```text
Summon Angry Wasps Flying
HEALTH 3
MOVE 4
ATTACK —
RANGE —
All adjacent allies
and enemies
suffer 2 damage
at end of turn.
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP (2 marks)`

The Angry Wasps have no printed Attack value. Their end-of-turn damage is not an attack and also affects allies.

### BOTTOM

```text
MOVE 3

All adjacent allies and
enemies suffer 1 damage.
```

`ENHANCEABLE: MOVE`

---

## Focused Scourge

**File:** `gh-focused-scourge.png`  
**Level:** 1  
**Initiative:** 50

### TOP

```text
When one of your ATTACK abilities
specifies “TARGET X”, you may target the
same enemy multiple times with the ability,
counting one target for each attack.
XP +2
[PERSISTENT, LOSS]
```

This changes target selection, but each chosen target still produces a separate attack.

### BOTTOM

```text
Once during each of your Attack actions,
you may CONSUME ANY_ELEMENT to add +1 ATTACK
to the entire Attack action.
INFUSE EARTH
XP +2
[PERSISTENT, LOSS]
```

The optional element consumption adds the bonus to every attack belonging to that Attack action.

---

## Hive Mind

**File:** `gh-hive-mind.png`  
**Level:** 1  
**Initiative:** 05

### TOP

```text
You may control the actions of
all summoned allies you own.
XP +1
[PERSISTENT, LOSS]
```

### BOTTOM

```text
SHIELD 1
Affect self and all summoned
allies you own
XP +2
[PERSISTENT, LOSS]
```

Every affected figure has its own `SHIELD 1` while the card remains active.

---

## Omniscient Assault

**File:** `gh-omniscient-assault.png`  
**Level:** 1  
**Initiative:** 22

### TOP

```text
ATTACK 2
TARGET 2
PIERCE 1
XP +1
```

`ENHANCEABLE: PIERCE`

### BOTTOM

```text
MOVE 2

When attacking this round, consider
any negative and Null attack modifier
cards you draw to be a +0 instead.
[ROUND]
```

`ENHANCEABLE: MOVE`

The x2 modifier remains unchanged because it is neither negative nor NULL.

---

## Scattered Defense

**File:** `gh-scattered-defense.png`  
**Level:** 1  
**Initiative:** 13

### TOP

```text
RETALIATE 1
Self

SHIELD 1
Self
XP +1
[ROUND]
```

`ENHANCEABLE: RETALIATE`

### BOTTOM

```text
MOVE 2

One summoned ally you own performs
MOVE 2
with you controlling the action.
```

`ENHANCEABLE: SELF MOVE`

The summon movement is a separate ability after SELF's movement.

---

## Swarming Minions

**File:** `gh-swarming-minions.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
One summoned ally you own performs
MOVE +0
and
ATTACK +0
in either order, with you
controlling the actions.
XP +1
```

### BOTTOM

```text
All summoned allies you own perform
MOVE +0
and
ATTACK +0
in either order, with you
controlling the actions.
XP +2
[LOSS]
```

The move/attack order may be chosen independently for each summon.

---

## The Storm's Edge

**File:** `gh-the-storms-edge.png`  
**Level:** 1  
**Initiative:** 37

### TOP

```text
WOUND
Target all adjacent enemies
XP +1
```

The top action applies WOUND to all adjacent enemies without performing an attack.

### BOTTOM

```text
ATTACK 2
TARGET 3
Target enemies within two hexes
INFUSE AIR

HEAL X
Self
where X is double the number
of enemies targeted.
XP +2
[LOSS]
```

`TARGETS_CHOSEN` counts selected enemies, not only enemies that ultimately suffer damage.

---

# Level X cards

## Grasping Advance

**File:** `gh-grasping-advance.png`  
**Level:** X  
**Initiative:** 18

### TOP

```text
MOVE 2

ATTACK 2
IMMOBILIZE
```

`ENHANCEABLE: MOVE, ATTACK`

### BOTTOM

```text
LOOT 1
You may perform this action as if you
were occupying the same hex as a
summoned ally you own.
```

The chosen summon is only the virtual origin of the action; SELF does not physically enter its hex.

---

## Tomb of the Immortal

**File:** `gh-tomb-of-the-immortal.png`  
**Level:** X  
**Initiative:** 59

### TOP

```text
Recover one of your lost cards and
immediately play it, performing either
the top or bottom action of the card.
INFUSE LIGHT
[LOSS, UNRECOVERABLE]
```

The recovered card's selected action resolves immediately as part of this action.

### BOTTOM

```text
MOVE 5
```

---

## Unstoppable Army

**File:** `gh-unstoppable-army.png`  
**Level:** X  
**Initiative:** 94

### TOP

```text
HEAL 3
Self
or
Affect all summoned allies you own
INFUSE LIGHT
```

### BOTTOM

```text
Summon Steel Scarabs
HEALTH 4
MOVE 2
ATTACK 1
RANGE —
SHIELD 1
All adjacent allies
gain SHIELD 1.
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP (2 marks), SUMMON ATTACK`

The adjacency Shield is continuously recalculated as figures move.

---

# Level 2 cards

## Infest

**File:** `gh-infest.png`  
**Level:** 2  
**Initiative:** 43

### TOP

```text
ATTACK 3
POISON
INFUSE EARTH
```

`ENHANCEABLE: ATTACK`

The top action has no printed Range or two-hex target restriction; it is a melee attack.

### BOTTOM

```text
Attach this to one of your
active summon abilities.

This summoned ally adds
POISON to all its attacks.
[PERSISTENT]
```

The card is discarded when the attached summon ability leaves the active area.

---

## Overwhelming Offensive

**File:** `gh-overwhelming-offensive.png`  
**Level:** 2  
**Initiative:** 12

### TOP

```text
ATTACK 2
Target one enemy within two hexes
Add DISARM and gain 1 experience if
the target is adjacent to at least
one summoned ally you own.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3

Each time this round that an adjacent
enemy performs a Move action and
ends it farther away from you, you may
immediately Teleport to any hex
adjacent to the enemy.
INFUSE AIR
[ROUND]
```

Each qualifying enemy movement may trigger the effect separately.

---

# Level 3 cards

## Armed and Dangerous

**File:** `gh-armed-and-dangerous.png`  
**Level:** 3  
**Initiative:** 78

### TOP

```text
In addition to your current items, you
may immediately equip up to four one-hand items,
two two-hand items, or two one-hand and one two-hand items.
XP +2
[PERSISTENT, LOSS]
```

The extra items remain equipped while this card is active.

### BOTTOM

```text
All summoned allies you own perform
MOVE +0
with you controlling the actions.
```

---

## Putrid Grubs

**File:** `gh-putrid-grubs.png`  
**Level:** 3  
**Initiative:** 16

### TOP

```text
Summon 3 Bloat Maggots
HEALTH 3
MOVE 3
ATTACK 1
RANGE —
All adjacent
enemies deduct
-1 ATTACK from
all their attacks.
XP +2
[PERSISTENT, LOSS]
```

The printed modifier is not multiplied when an enemy is adjacent to multiple Bloat Maggots.

### BOTTOM

```text
MOVE 3

SHIELD 1
Self
[ROUND]
```

`ENHANCEABLE: MOVE`

---

# Level 4 cards

## Incubation

**File:** `gh-incubation.png`  
**Level:** 4  
**Initiative:** 09

### TOP

```text
HEAL 2
Self

RETALIATE 1
Self

SHIELD 1
Self
INFUSE EARTH
XP +1
[ROUND for RETALIATE and SHIELD]
```

`ENHANCEABLE: HEAL, SHIELD`

### BOTTOM

```text
Attach this to one of your
active summon abilities.

This summoned ally gains SHIELD 1.
It also performs “HEAL 1, Self”
at the end of each of its turns.
[PERSISTENT]
```


---

## Vampiric Tempest

**File:** `gh-vampiric-tempest.png`  
**Level:** 4  
**Initiative:** 64

### TOP

```text
ATTACK 1
Target all adjacent enemies
WOUND

HEAL X
Self
where X is the number of enemies targeted.
INFUSE DARK
```

The heal is based on enemies targeted, not on enemies damaged.

### BOTTOM

```text
MOVE 2

You are unaffected by
RETALIATE this round.
[ROUND]
```

`ENHANCEABLE: SHIELD`

---

# Level 5 cards

## Bioluminescence

**File:** `gh-bioluminescence.png`  
**Level:** 5  
**Initiative:** 80

### TOP

```text
All summoned allies you own perform
ATTACK +0
with you controlling the actions.
```

### BOTTOM

```text
Summon 2 Lightning Moths Flying
HEALTH 3
MOVE 3
ATTACK —
RANGE —
All adjacent
summoned allies
add +1 ATTACK to
all their attacks.
INFUSE LIGHT
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP (2 marks)`

The printed bonus affects all adjacent summoned allies; it does not restrict ownership. A Lightning Moth does not grant the bonus to itself.

---

## Cyclone of Blades

**File:** `gh-cyclone-of-blades.png`  
**Level:** 5  
**Initiative:** 27

### TOP

```text
ATTACK 2
TARGET 3
XP +1
INFUSE AIR
```

### BOTTOM

```text
MOVE 4
Jump

All allies and enemies moved
through suffer 1 damage.
```

`ENHANCEABLE: MOVE`

A figure is affected when SELF moves through its occupied hex, not merely when SELF moves adjacent to it.

---

# Level 6 cards

## Blood Drain

**File:** `gh-blood-drain.png`  
**Level:** 6  
**Initiative:** 47

### TOP

```text
ATTACK 2+X

HEAL 2+X
Self
where X is the number of summoned
allies you own, up to a maximum of 5.
```

### BOTTOM

```text
MOVE 3

One summoned ally you own may perform
ATTACK +0
targeting an enemy that is adjacent to
both you and the summoned ally.
```

The summon attack is optional and is performed after SELF's movement.

---

## Sunstroke

**File:** `gh-sunstroke.png`  
**Level:** 6  
**Initiative:** 32

### TOP

```text
ATTACK 2
WOUND
STUN
INFUSE LIGHT
```

`ENHANCEABLE: ATTACK`

The top action is ATTACK 2 with WOUND and STUN; it has no Heal ability, X formula, or TARGET 3.

### BOTTOM

```text
Attach this to one of your
active summon abilities.

All enemies gain Disadvantage on their
attacks targeting this summoned ally.
This summon also gives WOUND
to one enemy adjacent to it at the end
of each of its turns.
[PERSISTENT]
```

---

# Level 7 cards

## Fury of the Horde

**File:** `gh-fury-of-the-horde.png`  
**Level:** 7  
**Initiative:** 86

### TOP

```text
Summon Rage Hornets Flying
HEALTH 5
MOVE 3
ATTACK 3
RANGE —
Add +2 ATTACK
when the target
is adjacent to any
of your allies.
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP (2 marks), SUMMON ATTACK`

### BOTTOM

```text
MOVE 3

All adjacent allies and
enemies suffer 2 damage.
INFUSE AIR
```

`ENHANCEABLE: MOVE`

The adjacent damage is not an attack and affects allies as well as enemies.

---

## Repulsive Host

**File:** `gh-repulsive-host.png`  
**Level:** 7  
**Initiative:** 19

### TOP

```text
POISON
Target all adjacent enemies

SHIELD 2
Self
INFUSE EARTH
[ROUND]
```

### BOTTOM

```text
MOVE 2

STRENGTHEN
Self
```

`ENHANCEABLE: MOVE`

---

# Level 8 cards

## Bone Daggers

**File:** `gh-bone-daggers.png`  
**Level:** 8  
**Initiative:** 68

### TOP

```text
ATTACK 2
TARGET 4
Target enemies within two hexes

Suffer 1 damage for each
attack performed.
```

The self-damage is based on attacks actually performed, not the maximum `TARGET 4` value.

### BOTTOM

```text
RETALIATE 1
Self

ATTACK 2
TARGET 2
WOUND
INFUSE DARK
[ROUND for RETALIATE]
```

`ENHANCEABLE: ATTACK, TARGET`

The Retaliate ability and the attack are separate abilities in the printed order.

---

## Endless Biting

**File:** `gh-endless-biting.png`  
**Level:** 8  
**Initiative:** 41

### TOP

```text
All summoned allies you own perform
MOVE +0
and
ATTACK +0
in either order, with you
controlling the actions.
```

### BOTTOM

```text
Attach this to one of your
active summon abilities.

This summoned ally’s attacks gain
Advantage, PIERCE 2, and are
unaffected by RETALIATE.
[PERSISTENT]
```

---

# Level 9 cards

## Oasis

**File:** `gh-oasis.png`  
**Level:** 9  
**Initiative:** 04

### TOP

```text
Recover two of your lost cards and
immediately play them in either order,
one for the top action and one for the
bottom action.
INFUSE LIGHT
[LOSS, UNRECOVERABLE]
```

The same recovered card cannot supply both actions.

### BOTTOM

```text
MOVE 5

HEAL 3
Affect self and all summoned allies
adjacent to any hex you entered
during the movement.
INFUSE LIGHT
```

`ENHANCEABLE: MOVE`

A summoned ally only needs to be adjacent to one entered hex; it does not need to be adjacent to SELF at the movement’s final position. The printed Heal does not restrict summon ownership.

---

## Wasteland

**File:** `gh-wasteland.png`  
**Level:** 9  
**Initiative:** 96

### TOP

```text
Summon Rust Vermin
HEALTH 7
MOVE 4
ATTACK 3
RANGE —
Add DISARM
when the target
is affected by a
negative condition.
INFUSE DARK
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON ATTACK`

The target's qualifying negative condition must already be present when the summon attacks.

### BOTTOM

```text
PULL 2
RANGE 3
INFUSE DARK
POISON
WOUND
DISARM

The target suffers 2 damage.
```

`ENHANCEABLE: RANGE`

This is a non-attack ability; the direct damage does not draw an attack modifier.

---

# Summon reference cards

These reference entries repeat the summon statistics and full special wording verified on the supplied ability cards. Separate summon-reference images were not supplied. They have no level, initiative, TOP action, or BOTTOM action.

## Angry Wasps

**File:** `gh-angry-wasps.png`

```text
Summon Angry Wasps Flying
HEALTH 3
MOVE 4
ATTACK —
RANGE —
All adjacent allies
and enemies
suffer 2 damage
at end of turn.
```

---

## Bloat Maggots

**File:** `gh-bloat-maggots.png`

```text
Summon Bloat Maggot
HEALTH 3
MOVE 3
ATTACK 1
RANGE —
All adjacent
enemies deduct
-1 ATTACK from
all their attacks.
```

The Putrid Grubs ability summons three separate copies.

---

## Creeping Beetles

**File:** `gh-creeping-beetles.png`

```text
Summon Creeping Beetles
HEALTH 5
MOVE 2
ATTACK 2
RANGE —
All adjacent allies
may perform
“MOVE 1”
at end of turn.
```

---

## Lightning Moths

**File:** `gh-lightning-moths.png`

```text
Summon Lightning Moth Flying
HEALTH 3
MOVE 3
ATTACK —
RANGE —
All adjacent
summoned allies
add +1 ATTACK to
all their attacks.
```

The Bioluminescence ability summons two separate copies.

---

## Rage Hornets

**File:** `gh-rage-hornets.png`

```text
Summon Rage Hornets Flying
HEALTH 5
MOVE 3
ATTACK 3
RANGE —
Add +2 ATTACK
when the target
is adjacent to any
of your allies.
```

---

## Rust Vermin

**File:** `gh-rust-vermin.png`

```text
Summon Rust Vermin
HEALTH 7
MOVE 4
ATTACK 3
RANGE —
Add DISARM
when the target
is affected by a
negative condition.
```

---

## Soul Leeches

**File:** `gh-soul-leeches.png`

```text
Summon Soul Leeches
HEALTH 4
MOVE 3
ATTACK X
RANGE —
X is the number
of adjacent
figures.
```

---

## Steel Scarabs

**File:** `gh-steel-scarabs.png`

```text
Summon Steel Scarabs
HEALTH 4
MOVE 2
ATTACK 1
RANGE —
SHIELD 1
All adjacent allies
gain SHIELD 1.
```

---

# File checklist

```text
gh-bs-back.png
gh-angry-wasps.png
gh-armed-and-dangerous.png
gh-bioluminescence.png
gh-bloat-maggots.png
gh-blood-drain.png
gh-bone-daggers.png
gh-call-of-the-grave.png
gh-corrupting-parasites.png
gh-creeping-beetles.png
gh-cyclone-of-blades.png
gh-death-march.png
gh-deflecting-blades.png
gh-endless-biting.png
gh-engulfing-stingers.png
gh-focused-scourge.png
gh-fury-of-the-horde.png
gh-grasping-advance.png
gh-hive-mind.png
gh-incubation.png
gh-infest.png
gh-lightning-moths.png
gh-oasis.png
gh-omniscient-assault.png
gh-overwhelming-offensive.png
gh-putrid-grubs.png
gh-rage-hornets.png
gh-repulsive-host.png
gh-rust-vermin.png
gh-scattered-defense.png
gh-soul-leeches.png
gh-steel-scarabs.png
gh-sunstroke.png
gh-swarming-minions.png
gh-the-storms-edge.png
gh-tomb-of-the-immortal.png
gh-unstoppable-army.png
gh-vampiric-tempest.png
gh-wasteland.png
```

---

# Sources used

- Visual comparison with the three supplied Bladeswarm card screenshots (022330, 022334, and 022337), covering all 30 ability-card fronts and the card back.
- Summon reference entries reconciled with the summon panels on the supplied ability cards; separate summon-reference images were not supplied.
- Original file metadata and enhancement annotations retained unchanged.
