# Gloomhaven – Beast Tyrant (BT) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/bt/`  
**Source folder:** <https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/bt>  
**Files covered:** 30 PNG files = 29 ability-card fronts + 1 card back.

This is a **mechanically precise, normalized transcription**, not a literal OCR copy.  
The wording is deliberately standardized so that relationships between commands, abilities, modifiers, conditions, costs, tracks, elements, summons, and area patterns remain unambiguous.

---

# Notation / legend

## Card structure

```text
TOP
1. first ability
2. second ability

BOTTOM
1. first ability
2. second ability
```

Abilities within one half are listed in their printed top-to-bottom order.

- `└─` means the line is subordinate to, and modifies, the ability directly above it.
- `THEN` means a separate following ability.
- `OPTIONAL` means that component may be skipped.
- An omitted `RANGE` means the attack is melee.
- `SELF` means the acting figure.
- `BEAR` means the Beast Tyrant's bear summon.
- `ADJACENT` means a distance of exactly 1 hex.
- `TARGET n` means the ability may affect up to `n` different valid targets.
- `ALL_ADJACENT_ENEMIES` means every enemy in one of the six neighboring hexes.
- `ALL_SUMMONED_ALLIES` includes the bear and every other summon belonging to the Beast Tyrant. 

## Commands

```text
COMMAND:
1. first ability
2. second ability
```

Every ability subordinate to `COMMAND` is performed by `BEAR`, using the bear's position and statistics.  
Inside a command, `SELF` therefore refers to `BEAR`.

## Common keywords

```text
ATTACK n
MOVE n
HEAL n [SELF | RANGE n]
SHIELD n
RETALIATE n
LOOT n
PUSH n
PIERCE n
POISON
DISARM
STRENGTHEN
STUN
MUDDLE
IMMOBILIZE
FLYING
EXECUTE
```

`EXECUTE` means the affected enemy is killed without resolving an attack.

## Elements

```text
INFUSE AIR
INFUSE EARTH
CONSUME AIR => effect
CONSUME EARTH => effect
```

A consume line is conditional: the listed effect is obtained only when that element is consumed.

## Summons

Summon statistics are written as follows:

```text
SUMMON Name
├─ HP n
├─ MOVE n
├─ ATTACK n [RANGE n]
└─ traits or special rules
```

`MOVE 0` or `ATTACK 0` records a printed dash as the mechanically equivalent value zero.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is moved to the lost pile after that action resolves.
- `[ROUND, LOSS]` — active for the round, then moved to the lost pile.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[UNRECOVERABLE LOSS]` — moved to the lost pile and cannot be recovered.
- `[PERSISTENT, UNRECOVERABLE LOSS]` — persistent action whose card can never be recovered after it is lost.
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
ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK
ENHANCEABLE: one AOE hex
```

## Relative summon-stat actions

```text
MOVE +0   = use that summon's printed MOVE value
MOVE -1   = use that summon's printed MOVE value minus 1
ATTACK +0 = use that summon's printed ATTACK value
```

## Hex directions and AOE diagrams

Relative directions around the acting figure:

in hex coordinates with [q, r]


A diagram may be rotated around `S` in any possible way or mirroring; its relative shape remains unchanged.

---

# Card back

## `gh-bt-back.png`

Beast Tyrant / Two Minis ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Disorienting Roar

**File:** `gh-disorienting-roar.png`  
**Level:** 1  
**Initiative:** 17

### TOP

```text
COMMAND:
1. MUDDLE and IMMOBILIZE, Target all enemies within Range 2 (of BEAR)
2. XP +1
```

This is a non-attack ability; no attack modifier is drawn.

### BOTTOM

```text
TELEPORT any figure to a hex occupied by a different figure and simultaneously TELEPORT that figure to the hex occupied by the first figure.
[LOSS]
```

The two figures may be allies, enemies, or one of each, provided their positions can legally be occupied by the other figure.

---

## Spirit Swap

**File:** `gh-spirit-swap.png`  
**Level:** 1  
**Initiative:** 22

### TOP

```text
COMMAND:
1. MOVE 2
2. XP +1
```

`ENHANCEABLE: MOVE (2 marks)`

### BOTTOM

```text
TELEPORT any enemy to a hex occupied by a different enemy and simultaneously TELEPORT that enemy to the hex occupied by the first enemy.
```

---

## Venomous Ally

**File:** `gh-venomous-ally.png`  
**Level:** 1  
**Initiative:** 79

### TOP

```text
SUMMON Green Adder
├─ HP 4
├─ MOVE 2
├─ ATTACK 1
└─ POISON

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
ATTACK 2
├─ RANGE 4
└─ POISON
```

`ENHANCEABLE: RANGE`

---

## Relentless Ally

**File:** `gh-relentless-ally.png`  
**Level:** 1  
**Initiative:** 81

### TOP

```text
SUMMON Tattered Wolf
├─ HP 4
├─ MOVE 3
└─ ATTACK 2

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Concentrated Rage

**File:** `gh-concentrated-rage.png`  
**Level:** 1  
**Initiative:** 51

### TOP

```text
ATTACK 1
├─ RANGE 4
└─ DISARM
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
Before your BEAR acts, you may choose to forgo the top action of your turn to add +3 ATTACK to all your BEAR's attacks this round.  
Before your BEAR acts, you may choose to forgo the bottom action of your turn to add +3 MOVE to all your BEAR's moves this round.

[PERSISTENT, LOSS]
```

The two choices are independent; either, both, or neither may be used before the bear acts.

---

## Disappearing Wounds

**File:** `gh-disappearing-wounds.png`  
**Level:** 1  
**Initiative:** 23

### TOP

```text
HEAL 4, SELF
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
COMMAND:
1. ATTACK 2
2. XP +1
```

`ENHANCEABLE: ATTACK (2 marks)`

---

## Patch Fur

**File:** `gh-patch-fur.png`  
**Level:** 1  
**Initiative:** 20

### TOP

```text
SHIELD 1 [ROUND]
└─ Target: Affect all summoned allies
```

### BOTTOM

```text
COMMAND:
1. HEAL 4, SELF
2. XP +1
```

`ENHANCEABLE: HEAL (2 marks)`

---

## Maul

**File:** `gh-maul.png`  
**Level:** 1  
**Initiative:** 14

### TOP

```text
COMMAND:
ATTACK 4
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
1. MOVE 3
2. SHIELD 1 [ROUND]
   └─ Affect one adjacent ally
```

`ENHANCEABLE: MOVE`

---

## Forceful Swipe

**File:** `gh-forceful-swipe.png`  
**Level:** 1  
**Initiative:** 35

### TOP

```text
COMMAND:
ATTACK 2
├─ AOE pattern below
└─ XP +1
```

Exact relative AOE:

```text
BEAR: [0, 0]
Fields targeted: [[1, -1],[1, 0],[0, 1]]
```

Equivalent direction set:

```text
targets = {1, 2, 3}
```

The attack covers three consecutive hexes adjacent to `BEAR`. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, one AOE hex ([0, -1])`

### BOTTOM

```text
COMMAND:
1. MOVE 3
2. PUSH 2
   └─ Target: one adjacent enemy
3. XP +1
```

`ENHANCEABLE: MOVE, PUSH`

The push is a separate non-attack ability performed after the movement.

---

## Howling Bolts

**File:** `gh-howling-bolts.png`  
**Level:** 1  
**Initiative:** 47

### TOP

```text
ATTACK 1
├─ RANGE 4
└─ TARGET 3
```

`ENHANCEABLE: TARGET`

### BOTTOM

```text
COMMAND:
1. MOVE 5
2. XP +1
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Soaring Ally

**File:** `gh-soaring-ally.png`  
**Level:** X  
**Initiative:** 77

### TOP

```text
SUMMON Red Falcon
├─ HP 2
├─ MOVE 4
├─ ATTACK 2
└─ FLYING

INFUSE AIR
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE`

### BOTTOM

```text
COMMAND:
LOOT 1
```

---

## Focused Aggression

**File:** `gh-focused-aggression.png`  
**Level:** X  
**Initiative:** 31

### TOP

```text
ATTACK 3
└─ RANGE 3
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
COMMAND:
1. ATTACK 5
2. INFUSE EARTH
3. XP +2

[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

---

## Borrowed Essence

**File:** `gh-borrowed-essence.png`  
**Level:** X  
**Initiative:** 56

### TOP

```text
1. ATTACK 2
   ├─ RANGE 3
   └─ CONSUME AIR:
      +2 ATTACK
      XP +1

2. HEAL 2, SELF
   └─ CONSUME EARTH:
      +2 HEAL
      XP +1
```

`ENHANCEABLE: ATTACK, HEAL`

The two element consumptions are independent.

### BOTTOM

```text
Redistribute all DAMAGE taken by you and all summoned allies however you like.  
No figure may end with more damage than their maximum hit point value.
```

The total amount of damage remains unchanged; only its distribution among the affected figures changes.

---

# Level 2 cards

## Energizing Strike

**File:** `gh-energizing-strike.png`  
**Level:** 2  
**Initiative:** 29

### TOP

```text
COMMAND:
1. ATTACK 4
2. STRENGTHEN, SELF
3. XP +1
```

`ENHANCEABLE: ATTACK (2 marks)`

Because this is a command, `SELF` is `BEAR`.

### BOTTOM

```text
HEAL 1
└─ Target: Affect all allies
```

---

## Earthen Spikes

**File:** `gh-earthen-spikes.png`  
**Level:** 2  
**Initiative:** 11

### TOP

```text
ATTACK 3
├─ RANGE 2
├─ IMMOBILIZE
└─ INFUSE EARTH
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
1. SHIELD 2 [ROUND]
   └─ Target: Affect all summoned allies
2. RETALIATE 2 [ROUND]
   └─ Target: Affect all summoned allies
3. XP +2

[ROUND, LOSS]
```

---

# Level 3 cards

## Vicious Ally

**File:** `gh-vicious-ally.png`  
**Level:** 3  
**Initiative:** 86

### TOP

```text
SUMMON Swamp Alligator
├─ HP 5
├─ MOVE 2
├─ ATTACK 3
└─ IMMOBILIZE

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
COMMAND:
1. ATTACK 2
   └─ TARGET 2
2. XP +1
```

`ENHANCEABLE: ATTACK, TARGET`

---

## Unstoppable Beast

**File:** `gh-unstoppable-beast.png`  
**Level:** 3  
**Initiative:** 32

### TOP

```text
COMMAND:
ATTACK 3
├─ AOE pattern below
└─ CONSUME EARTH:
   +1 ATTACK
   XP +1
```

Exact relative AOE:

```text
BEAR: [0, 0]
Fields targeted: [[1, -1],[1, 0],[0, 1]]
```

Equivalent direction set:

```text
targets = {1, 2, 3}
```

The modifier from consuming `EARTH` applies to every attack made as part of this area attack.

`ENHANCEABLE: ATTACK, one AOE hex ([0, -1])`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

# Level 4 cards

## Ancient Ward

**File:** `gh-ancient-ward.png`  
**Level:** 4  
**Initiative:** 19

### TOP

```text
ATTACK 0
├─ RANGE 4
├─ TARGET 2
├─ DISARM
└─ XP +1
```

`ENHANCEABLE: ATTACK, TARGET`

Each target is a separate attack, even though the printed base attack value is zero.

### BOTTOM

```text
HEAL 4
└─ RANGE 4
```

`ENHANCEABLE: HEAL`

---

## Punch Through

**File:** `gh-punch-through.png`  
**Level:** 4  
**Initiative:** 44

### TOP

```text
COMMAND:
1. ATTACK 3
   └─ PIERCE 4
2. XP +1
```

`ENHANCEABLE: ATTACK, PIERCE`

### BOTTOM

```text
1. MOVE 3
2. Add +1 ATTACK to all your COMMANDS this round. [ROUND]
```

`ENHANCEABLE: MOVE`

The bonus applies only for the remainder of the current round after this ability is performed.

---

# Level 5 cards

## Rampage

**File:** `gh-rampage.png`  
**Level:** 5  
**Initiative:** 34

### TOP

```text
COMMAND:
1. ATTACK 2
2. MOVE 2
3. ATTACK 2
4. MOVE 2
5. ATTACK 2
6. XP +2

[LOSS]
```

`ENHANCEABLE: first MOVE, second MOVE`

The five command abilities are resolved in the printed order.

### BOTTOM

```text
Any one summoned ally performs
1. MOVE +0
2. ATTACK +0
with you controlling the actions.
```

The chosen ally uses its own printed Move and Attack values.

---

## Stone Sigil

**File:** `gh-stone-sigil.png`  
**Level:** 5  
**Initiative:** 95

### TOP

```text
SUMMON Monolith
├─ HP 15
├─ NO MOVE
├─ NO ATTACK
└─ SHIELD 2

INFUSE EARTH
XP +2
[PERSISTENT, LOSS]
```

### BOTTOM

```text
COMMAND:
MOVE 5
```

`ENHANCEABLE: MOVE (2 marks)`

---

# Level 6 cards

## Primal Blessing

**File:** `gh-primal-blessing.png`  
**Level:** 6  
**Initiative:** 28

### TOP

```text
HEAL 2
├─ RANGE 4
├─ TARGET 3
└─ XP +1
```

`ENHANCEABLE: TARGET`

Resolve a separate `HEAL 2` ability for each selected target.

### BOTTOM

```text
1. MOVE 2
2. TELEPORT to the hex occupied by your BEAR and simultaneously TELEPORT your BEAR to the hex occupied by you.
3. MOVE 2
```

`ENHANCEABLE: first MOVE, second MOVE`

The swap is a separate ability between the two movements.

---

## Blood Hunger

**File:** `gh-blood-hunger.png`  
**Level:** 6  
**Initiative:** 42

### TOP

```text
[PERSISTENT, LOSS]

After your BEAR's next five attacks, it performs a "HEAL 2, Self" action.
- BEAR performs HEAL 2, SELF.

TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
```

Each individual attack consumes one use, including each attack against a separate target in a multi-target or area attack.

### BOTTOM

```text
COMMAND:
1. ATTACK 3
2. HEAL 3, SELF
3. XP +1
```

`ENHANCEABLE: ATTACK, HEAL`

---

# Level 7 cards

## Storm Sigil

**File:** `gh-storm-sigil.png`  
**Level:** 7  
**Initiative:** 93

### TOP

```text
SUMMON Wind Totem
├─ HP 8
├─ NO MOVE
├─ ATTACK 1
│  └─ Target: Target all adjacent enemies
└─ PUSH 2 on each target

INFUSE AIR
XP +2
[PERSISTENT, LOSS]
```

The Wind Totem resolves a separate `ATTACK 1, PUSH 2` against every adjacent enemy when it attacks.

### BOTTOM

```text
COMMAND:
ATTACK 4
```

`ENHANCEABLE: ATTACK (2 marks)`

---

## Tyrannical Force

**File:** `gh-tyrannical-force.png`  
**Level:** 7  
**Initiative:** 37

### TOP

```text
ATTACK 0
├─ RANGE 3
├─ STUN
└─ INFUSE EARTH
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
All summoned allies within RANGE 3 perform
MOVE -1
with you controlling the actions.
```

Each summoned ally uses its own Move value reduced by 1 with the player controlling it. Figures with a resulting value of zero do not move.

---

# Level 8 cards

## Lash Out

**File:** `gh-lash-out.png`  
**Level:** 8  
**Initiative:** 18

### TOP

```text
COMMAND:
1. ATTACK 4
   └─ Target: Target all adjacent enemies
2. XP +1
```

`ENHANCEABLE: ATTACK`

A separate attack is resolved against every adjacent enemy.

### BOTTOM

```text
COMMAND:
1. MOVE 3
2. PUSH 2
   └─ Target: Target all adjacent enemies
3. XP +1
```

`ENHANCEABLE: MOVE, PUSH`

The push affects every enemy adjacent to the bear after the movement and is not an attack.

---

## Natural Remedy

**File:** `gh-natural-remedy.png`  
**Level:** 8  
**Initiative:** 25

### TOP

```text
HEAL 5
├─ RANGE 3
└─ CONSUME AIR:
   +1 HEAL
   +1 RANGE
   XP +1
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
1. MOVE 4
2. STRENGTHEN: Affect one adjacent ally
```

`ENHANCEABLE: MOVE`

---

# Level 9 cards

## Jaws of Death

**File:** `gh-jaws-of-death.png`  
**Level:** 9  
**Initiative:** 48

### TOP

```text
COMMAND:
1. Kill one adjacent normal enemy whose current hit point value is equal to or less than 6
2. XP +1
```

Elite and boss enemies are not valid targets.

### BOTTOM

```text
COMMAND:
1. ATTACK 2
   └─ PUSH 3
2. MOVE 3
3. XP +1
4. ATTACK 2
```

`ENHANCEABLE: first ATTACK, second ATTACK`

These are three separate command abilities in the printed order: attack, move, attack. `PUSH 3` modifies only the first attack.

---

## Tribal Sigil

**File:** `gh-tribal-sigil.png`  
**Level:** 9  
**Initiative:** 91

### TOP

```text
SUMMON Spirit Banner
├─ HP 8
├─ MOVE 0
├─ ATTACK 0
└─ AURA:
   All adjacent allies add +2 ATTACK to all their attacks.

XP +2
[PERSISTENT, UNRECOVERABLE LOSS]
```

The aura does not affect the Spirit Banner itself because a figure is not adjacent to its own hex.

### BOTTOM

```text
RECOVER up to two of your lost cards with Summon actions on them.
[UNRECOVERABLE LOSS]
```

This card itself cannot be recovered after either half is used as an unrecoverable loss action.

---

# File checklist

```text
gh-ancient-ward.png
gh-blood-hunger.png
gh-borrowed-essence.png
gh-bt-back.png
gh-concentrated-rage.png
gh-disappearing-wounds.png
gh-disorienting-roar.png
gh-earthen-spikes.png
gh-energizing-strike.png
gh-focused-aggression.png
gh-forceful-swipe.png
gh-howling-bolts.png
gh-jaws-of-death.png
gh-lash-out.png
gh-maul.png
gh-natural-remedy.png
gh-patch-fur.png
gh-primal-blessing.png
gh-punch-through.png
gh-rampage.png
gh-relentless-ally.png
gh-soaring-ally.png
gh-spirit-swap.png
gh-stone-sigil.png
gh-storm-sigil.png
gh-tribal-sigil.png
gh-tyrannical-force.png
gh-unstoppable-beast.png
gh-venomous-ally.png
gh-vicious-ally.png
```

---

# Source cross-checks used

- Worldhaven image folder: `images/character-ability-cards/gloomhaven/bt/`
- Worldhaven card filenames and card images
- Gloomy Beast Tyrant structured ability definitions
- Gloomy summon-stat definitions
- Direct visual cross-checks for enhancement dots, duration/loss symbols, summon tables, and AOE patterns
