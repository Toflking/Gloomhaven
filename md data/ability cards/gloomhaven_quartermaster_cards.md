# Gloomhaven – Quartermaster (QM) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/qm/`  
**Source folder:** `https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/qm`  
**Files covered:** 29 PNG files = 28 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording, including printed grammatical errors. Icon keywords are written in capitals; experience, item-slot icons, elements, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n`. Recovery, refresh, kill, and other special effects use the full printed wording. Item-slot icons are represented by “small”, “one-hand”, and “two-hand”. `AOE pattern below` refers to the separate preserved area diagram.

## Elements

```text
INFUSE AIR
INFUSE ICE
INFUSE LIGHT
INFUSE DARK
```

The element is infused at the end of the acting figure's turn, following the normal element rules.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is placed in the lost pile after that action is resolved.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[CANNOT_BE_RECOVERED]` — the printed crossed-card symbol prevents that card from being recovered, regardless of how it became lost.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
```

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: one AOE hex
```

## Hex directions and AOE diagrams

Relative directions around the acting figure:

```text
          6   1
       5   S   2
          4   3
```

- `S` = acting figure / origin hex
- `T` = affected or attacked hex
- `.` = unaffected neighboring hex

A melee diagram may be rotated around `S`; its relative shape remains unchanged.  
A ranged AOE diagram may likewise be rotated and translated, subject to the normal range and line-of-sight rules.

---

# Card back

## `gh-qm-back.png`

Quartermaster / Three Spears ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Restock

**File:** `gh-restock.png`  
**Level:** 1  
**Initiative:** 73

### TOP

```text
All allies within RANGE 2 Refresh
one of their spent or consumed item.
XP +2
[LOSS]
```

`SELF` is not an ally and is therefore not affected.

### BOTTOM

```text
ATTACK 3
```

`ENHANCEABLE: ATTACK`

---

## Oversized Pack

**File:** `gh-oversized-pack.png`  
**Level:** 1  
**Initiative:** 81

### TOP

```text
LOOT 2
```

### BOTTOM

```text
Refresh all of your consumed small items.
XP +2
[LOSS]
```

Only consumed small items are refreshed; spent items and non-small items are unaffected.

---

## Sharpening Kit

**File:** `gh-sharpening-kit.png`  
**Level:** 1  
**Initiative:** 23

### TOP

```text
HEAL 4
RANGE 2
XP +1
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
You and all allies within RANGE 2 add
+1 ATTACK to all your attacks this round.
[ROUND]
```

The bonus applies separately to every attack in a multi-target or multi-attack action.

---

## Proficiency

**File:** `gh-proficiency.png`  
**Level:** 1  
**Initiative:** 62

### TOP

```text
Once during each of your Attack actions,
if you use an item, add +1 ATTACK to
the entire Attack action.
XP +2
[PERSISTENT, LOSS]
```

The trigger may occur at most once per attack action.  
When triggered, the bonus applies to every individual attack belonging to that attack action.

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Iron Bulwark

**File:** `gh-iron-bulwark.png`  
**Level:** 1  
**Initiative:** 19

### TOP

```text
ATTACK 3
MUDDLE
XP +1
```

### BOTTOM

```text
SHIELD 1
Affect self and all adjacent allies
[ROUND]
```

`ENHANCEABLE: SHIELD`

Each affected figure gains its own `SHIELD 1` for the round.

---

## Hastened Step

**File:** `gh-hastened-step.png`  
**Level:** 1  
**Initiative:** 26

### TOP

```text
ATTACK 2

MOVE 1

ATTACK 2
```

`ENHANCEABLE: MOVE`

These are three separate abilities in the listed order.  
Either attack may be skipped independently.

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Cleaving Axe

**File:** `gh-cleaving-axe.png`  
**Level:** 1  
**Initiative:** 52

### TOP

```text
ATTACK 2
AOE pattern below
XP +1
```

Exact relative AOE:

```text
QUARTERMASTER: [0, 0]
Fields targeted: [[1, -1],[1, 0],[0, 1]]
```

Equivalent direction set:

```text
targets = {1, 2, 3}
```

Thus, the attack covers three consecutive hexes adjacent to `S`.  
The entire pattern may be rotated.

`ENHANCEABLE: one AOE hex ([0, -1])`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Impaling Spear

**File:** `gh-impaling-spear.png`  
**Level:** 1  
**Initiative:** 48

### TOP

```text
ATTACK 3
AOE pattern below
XP +1
```

Exact relative shape:

```text
QUARTERMASTER: [0, 0]
Fields targeted: [[1, -1], [2, -2]]
```

The straight line may be rotated into any of the six hex directions.

`ENHANCEABLE: one AOE hex ([3, -3])`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Crippling Bow

**File:** `gh-crippling-bow.png`  
**Level:** 1  
**Initiative:** 44

### TOP

```text
ATTACK 3
RANGE 3
XP +1
```

### BOTTOM

```text
ATTACK 1
RANGE 4
IMMOBILIZE
```

`ENHANCEABLE: ATTACK`

---

# Level X cards

## Crushing Hammer

**File:** `gh-crushing-hammer.png`  
**Level:** X  
**Initiative:** 17

### TOP

```text
ATTACK 3
STUN
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 2

SHIELD 1
Self
[ROUND]
```

`ENHANCEABLE: SHIELD`

---

## Scroll of Recall

**File:** `gh-scroll-of-recall.png`  
**Level:** X  
**Initiative:** 98

### TOP

```text
Refresh one of your consumed small items.
```

### BOTTOM

```text
PULL 1
RANGE 3
TARGET 3
```

`ENHANCEABLE: PULL, TARGET`

This is one multi-target Pull ability, not an attack.

---

## Reserved Energy

**File:** `gh-reserved-energy.png`  
**Level:** X  
**Initiative:** 88

### TOP

```text
One adjacent ally may Refresh
all their spent items.
XP +1
```

Consumed items are unaffected.

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

# Level 2 cards

## Reforge

**File:** `gh-reforge.png`  
**Level:** 2  
**Initiative:** 95

### TOP

```text
You or one adjacent ally may
Refresh one of your spent or
consumed one-hand or two-hand items.
XP +1
```

### BOTTOM

```text
Recover up to two of
your discarded cards.
```

Up to two of `SELF`'s discarded cards are returned to the hand.

---

## Bladed Boomerang

**File:** `gh-bladed-boomerang.png`  
**Level:** 2  
**Initiative:** 40

### TOP

```text
ATTACK 2
RANGE 3
WOUND
XP +1
```

### BOTTOM

```text
MOVE 2

ATTACK 1

MOVE 2
```

`ENHANCEABLE: ATTACK`

These are three separate abilities in the listed order.

---

# Level 3 cards

## Continual Supply

**File:** `gh-continual-supply.png`  
**Level:** 3  
**Initiative:** 86

### TOP

```text
At the end of each of your turns, you may
discard one card to allow an adjacent ally to
Refresh one of their consumed small items.
XP +2
[PERSISTENT, LOSS]
```

The discard is the cost for that individual trigger.

### BOTTOM

```text
Recover up to two of your lost cards.
[LOSS, CANNOT_BE_RECOVERED]
```

Up to two of `SELF`'s lost cards are returned to the hand.  
This card itself cannot be recovered.

---

## Scroll of Lightning

**File:** `gh-scroll-of-lightning.png`  
**Level:** 3  
**Initiative:** 77

### TOP

```text
ATTACK 5
RANGE 3
INFUSE LIGHT
```

### BOTTOM

```text
MOVE 3

PUSH 1
Target all adjacent enemies
INFUSE AIR
```

`ENHANCEABLE: MOVE, PUSH`

The Push is a separate non-attack ability applied to every adjacent enemy.

---

# Level 4 cards

## Side Pouch

**File:** `gh-side-pouch.png`  
**Level:** 4  
**Initiative:** 84

### TOP

```text
One adjacent ally may Refresh
all their spent and consumed items.
XP +2
[LOSS]
```

The refresh is not restricted by item slot.

### BOTTOM

```text
MOVE 3

LOOT 1
```

`ENHANCEABLE: MOVE`

---

## Giant Club

**File:** `gh-giant-club.png`  
**Level:** 4  
**Initiative:** 61

### TOP

```text
ATTACK 5
XP +1
```

### BOTTOM

```text
ATTACK 3
MUDDLE
```

`ENHANCEABLE: ATTACK`

---

# Level 5 cards

## Reinforced Steel

**File:** `gh-reinforced-steel.png`  
**Level:** 5  
**Initiative:** 12

### TOP

```text
At the end of each round, Refresh
one of your spent items.
XP +2
[PERSISTENT, LOSS]
```

Consumed items are not valid targets for this refresh.

### BOTTOM

```text
SHIELD 2
Affect self and all adjacent allies
[ROUND]
```

`ENHANCEABLE: SHIELD`

Each affected figure gains its own `SHIELD 2` for the round.

---

## Scroll of Judgment

**File:** `gh-scroll-of-judgment.png`  
**Level:** 5  
**Initiative:** 66

### TOP

```text
You and all enemies within
RANGE 4 suffer 2 damage.
XP +2
[LOSS]
```

This is not an attack. It does not draw attack modifiers and is not reduced by Shield.

### BOTTOM

```text
MOVE 2

STUN
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, STUN`

The Stun is a separate non-attack ability.

---

# Level 6 cards

## Catastrophic Bomb

**File:** `gh-catastrophic-bomb.png`  
**Level:** 6  
**Initiative:** 46

### TOP

```text
ATTACK 1
RANGE 3
AOE pattern below
XP +1
```

Exact printed 12-hex AOE:

```text
TARGETS: [[0, 0], [0, 1], [0, 2], [1, -1], [1, 0], [1, 1], [1, 2], [2, -1], [2, 0], [2, 1], [3, -1], [3, 0]]
```

A separate attack is resolved against every enemy occupying an affected hex.  
The entire pattern may be rotated.

`ENHANCEABLE: two AOE hexes ([2, -2], [3, -2])`

### BOTTOM

```text
You and all normal
enemies in the targeted
area suffer 7 damage.
AOE pattern below
XP +1
```

Exact relative targeted area:

```text
QUARTERMASTER: [0, 0]
Fields targeted: [[1, -1], [1, 0]]
```

The two target hexes are adjacent to `SELF` and adjacent to each other.  
The pattern may be rotated.

This is not an attack. Elite and boss enemies are unaffected by the damage to enemies.

---

## Quiver of Arrows

**File:** `gh-quiver-of-arrows.png`  
**Level:** 6  
**Initiative:** 31

### TOP

```text
If any adjacent ally performs a ranged
attack, that ally gains ADD TARGET
for the attack.
XP +2
[PERSISTENT, LOSS]
```

The additional target must satisfy the normal targeting, range, and line-of-sight rules of the attack.

### BOTTOM

```text
MOVE 6
```

`ENHANCEABLE: MOVE`

---

# Level 7 cards

## Scroll of Blizzards

**File:** `gh-scroll-of-blizzards.png`  
**Level:** 7  
**Initiative:** 46

### TOP

```text
ATTACK 1
RANGE 2
AOE pattern below
INFUSE ICE
```

Exact printed 8-hex AOE:

```text
TARGETS: [[0, 0], [1, -1], [2, -2], [3, -3], [1, 0], [2, -1], [3, -2], [4, -3]]
```

The pattern is a connected strip two hexes wide and four hexes long.  
The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, two AOE hexes ([4, -4], [0, 1])`

### BOTTOM

```text
IMMOBILIZE
Target all adjacent enemies

MOVE 2
INFUSE DARK
```

`ENHANCEABLE: IMMOBILIZE, MOVE`

The Immobilize is a separate non-attack ability performed before the movement.

---

## Refreshment

**File:** `gh-refreshment.png`  
**Level:** 7  
**Initiative:** 89

### TOP

```text
Each time you long rest,
Refresh one of your consumed items.
XP +2
[PERSISTENT, LOSS]
```

The refreshed item is not restricted by slot.

### BOTTOM

```text
Any one ally may Recover
all their discarded cards.
XP +2
[LOSS]
```

`SELF` cannot be chosen because the card specifies an ally.

---

# Level 8 cards

## Portable Ballista

**File:** `gh-portable-ballista.png`  
**Level:** 8  
**Initiative:** 41

### TOP

```text
ATTACK 4
AOE pattern below
XP +1
```

Exact relative shape:

```text
QUARTERMASTER: [0, 0]
TARGETS: [[0, -1], [0, -2], [0, -3], [0, -4], [0, -5]]
```

The straight line may be rotated into any of the six hex directions.

`ENHANCEABLE: ATTACK, two AOE hexes ([-1, -4], [1, -5])`

### BOTTOM

```text
During all your attacks this round,
ignore the Shield values of all your targets.
[ROUND]
```

This modifies all of `SELF`'s attacks for the round, not only one attack action.

---

## Fortified Position

**File:** `gh-fortified-position.png`  
**Level:** 8  
**Initiative:** 10

### TOP

```text
At the end of your next five turns, perform a
“HEAL 2, Affect all adjacent allies” action.
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Each trigger performs one Heal ability affecting every adjacent ally.  
The first, third, and fifth uses each award 1 XP.

### BOTTOM

```text
MOVE 3

SHIELD 2
Self
[ROUND]
```

`ENHANCEABLE: MOVE, SHIELD`

---

# Level 9 cards

## Scroll of Annihilation

**File:** `gh-scroll-of-annihilation.png`  
**Level:** 9  
**Initiative:** 56

### TOP

```text
Kill one adjacent normal or elite enemy.
XP +2
[LOSS]
```

Boss enemies are not valid targets.

### BOTTOM

```text
MOVE 3

DISARM
Target all adjacent enemies
```

`ENHANCEABLE: MOVE, DISARM`

The Disarm is a separate non-attack ability performed after the movement.

---

## Bag of Holding

**File:** `gh-bag-of-holding.png`  
**Level:** 9  
**Initiative:** 91

### TOP

```text
LOOT 3
```

### BOTTOM

```text
You and all adjacent allies may Refresh
all your consumed small items.
XP +2
[LOSS]
```

Each affected figure refreshes that figure's own consumed small items.  
Spent items and non-small items are unaffected.

---

# File checklist

```text
gh-bag-of-holding.png
gh-bladed-boomerang.png
gh-catastrophic-bomb.png
gh-cleaving-axe.png
gh-continual-supply.png
gh-crippling-bow.png
gh-crushing-hammer.png
gh-fortified-position.png
gh-giant-club.png
gh-hastened-step.png
gh-impaling-spear.png
gh-iron-bulwark.png
gh-oversized-pack.png
gh-portable-ballista.png
gh-proficiency.png
gh-qm-back.png
gh-quiver-of-arrows.png
gh-refreshment.png
gh-reforge.png
gh-reinforced-steel.png
gh-reserved-energy.png
gh-restock.png
gh-scroll-of-annihilation.png
gh-scroll-of-blizzards.png
gh-scroll-of-judgment.png
gh-scroll-of-lightning.png
gh-scroll-of-recall.png
gh-sharpening-kit.png
gh-side-pouch.png
```

---

# Sources used

- Visual comparison with the three supplied Quartermaster card screenshots (021818, 021822, and 021825), covering all 28 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, tracks, and explanatory notes outside the action blocks.
