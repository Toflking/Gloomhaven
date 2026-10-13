# Gloomhaven – Brute (BR) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/br/`  
**Files covered:** 30 PNG files = 29 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

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
- `SELF` means the acting character.
- `ADJACENT` means a distance of exactly 1 hex.
- `TARGET n` means the attack may affect up to `n` different valid targets.

## Common keywords

```text
ATTACK n
MOVE n
HEAL n [SELF | RANGE n]
SHIELD n [SELF]
RETALIATE n [SELF]
LOOT n
PUSH n
PULL n
PIERCE n
WOUND
STUN
MUDDLE
IMMOBILIZE
DISARM
JUMP
EXECUTE
```

## Elements

```text
INFUSE AIR
INFUSE EARTH
CONSUME AIR => effect
```

A consume line is conditional: the listed effect is obtained only when the named element is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [ ] [XP+1] [ ] [XP+1] -> end
```

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: one AOE hex
```

## Full wording

Conditions and definitions of X are written out on every card. An experience icon inside a sentence is written as “1 experience”; standalone experience icons use `XP +n`.

## Hex directions and AOE diagrams

Relative directions around the acting figure:

```text
          6   1
        5   S   2
          4   3
```

- `S` = acting figure / origin hex
- `T` = attacked hex
- `.` = unaffected neighboring hex

A diagram may be rotated around `S`; its relative shape remains unchanged.

---

# Card back

## `gh-br-back.png`

Brute / Inox Brute ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Eye for an Eye

**File:** `gh-eye-for-an-eye.png`  
**Level:** 1  
**Initiative:** 18

### TOP

```text
RETALIATE 2
SELF
Gain 1 experience each time you RETALIATE this round.
[ROUND]
```

`ENHANCEABLE: RETALIATE`

### BOTTOM

```text
HEAL 2
RANGE 1
INFUSE EARTH
```

`ENHANCEABLE: HEAL (2 marks)`

The heal may target any valid figure within range under the normal healing rules.

---

## Grab and Go

**File:** `gh-grab-and-go.png`  
**Level:** 1  
**Initiative:** 87

### TOP

```text
LOOT 1
```

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE (2 marks)`

---

## Leaping Cleave

**File:** `gh-leaping-cleave.png`  
**Level:** 1  
**Initiative:** 54

### TOP

```text
ATTACK 3
AOE pattern below
XP +1
```

Exact relative AOE:

```text
BRUTE: [0, 0]
TARGETS: [[1, -1],[1, 0]]
```

Equivalent direction set:

```text
targets = {1, 2}
```

Thus, the attack covers two neighboring hexes that are adjacent to each other. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, one AOE-hex slot ([0, 1])`

### BOTTOM

```text
MOVE 3
JUMP
INFUSE AIR
```

`ENHANCEABLE: MOVE`

---

## Overwhelming Assault

**File:** `gh-overwhelming-assault.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
ATTACK 6
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 3

PUSH 2
Target one adjacent enemy
```

The push is a separate non-attack ability performed after the movement.

---

## Provoking Roar

**File:** `gh-provoking-roar.png`  
**Level:** 1  
**Initiative:** 10

### TOP

```text
ATTACK 2
DISARM
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
Any enemy who targets one of your adjacent allies with an attack this round targets you with that attack instead, regardless of the attack’s range.
[ROUND]
```

This preserves the card's special rule that an enemy targeting one of SELF's adjacent allies targets SELF instead, regardless of the attack's range.

---

## Shield Bash

**File:** `gh-shield-bash.png`  
**Level:** 1  
**Initiative:** 15

### TOP

```text
ATTACK 4
STUN
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
SHIELD 1
SELF
[ROUND]
```

`ENHANCEABLE: SHIELD (2 marks)`

---

## Spare Dagger

**File:** `gh-spare-dagger.png`  
**Level:** 1  
**Initiative:** 27

### TOP

```text
ATTACK 3
RANGE 3
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 2
```

`ENHANCEABLE: ATTACK`

---

## Sweeping Blow

**File:** `gh-sweeping-blow.png`  
**Level:** 1  
**Initiative:** 64

### TOP

```text
ATTACK 2
AOE pattern below
```

Exact relative AOE:

```text
BRUTE: [0, 0]
TARGETS: [[1, -1],[1, 0],[0, 1]]
```

Equivalent direction set:

```text
targets = {1, 2, 3}
```

Thus, the attack covers three consecutive hexes adjacent to `S`. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, one AOE-hex slot ([0, -1])`

### BOTTOM

```text
MOVE 3

PUSH 1
Target all adjacent enemies
```

The push is a separate non-attack ability applied after the movement.

---

## Trample

**File:** `gh-trample.png`  
**Level:** 1  
**Initiative:** 72

### TOP

```text
ATTACK 3
PIERCE 2
```

`ENHANCEABLE: ATTACK, PIERCE`

### BOTTOM

```text
MOVE 4
JUMP

ATTACK 2
Target all enemies moved through

XP +2
[LOSS]
```

Resolve a separate attack against each qualifying enemy.

`ENHANCEABLE: MOVE, ATTACK (2 marks)`

---

## Warding Strength

**File:** `gh-warding-strength.png`  
**Level:** 1  
**Initiative:** 32

### TOP

```text
ATTACK 3
PUSH 2
```

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
On the next six sources of damage from attacks targeting you, gain SHIELD 1.

TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying attack-damage sources consume uses. The second, fourth, and sixth uses each award 1 XP.

---

# Level X cards

## Balanced Measure

**File:** `gh-balanced-measure.png`  
**Level:** X  
**Initiative:** 77

### TOP

```text
ATTACK X
where X is the number of hexes you have moved so far this turn.

XP +1
```

### BOTTOM

```text
MOVE X
where X is the amount of damage you have inflicted so far this turn.
```

Both values are determined from events that occurred earlier in the same turn.

---

## Skewer

**File:** `gh-skewer.png`  
**Level:** X  
**Initiative:** 35

### TOP

```text
ATTACK 3
AOE pattern below
CONSUME AIR: +1 ATTACK, PIERCE 1

XP +1
```

Exact relative AOE:

```text
BRUTE: [0, 0]
TARGETS: [[1, -1],[2, -2]]
```

The two target hexes form a straight line extending away from `S`. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, one AOE-hex slot ([3, -3])`

### BOTTOM

```text
MOVE 6
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE (2 marks)`

---

## Wall of Doom

**File:** `gh-wall-of-doom.png`  
**Level:** X  
**Initiative:** 20

### TOP

```text
RETALIATE 2
SELF

SHIELD 2
SELF

XP +2
[ROUND, LOSS]
```

Both defensive abilities remain active for the round.

`ENHANCEABLE: RETALIATE, SHIELD`

### BOTTOM

```text
Add +1 ATTACK to all your attacks this round.
[ROUND]
```

---

# Level 2 cards

## Fatal Advance

**File:** `gh-fatal-advance.png`  
**Level:** 2  
**Initiative:** 40

### TOP

```text
Kill one adjacent normal enemy.
XP +2
[LOSS]
```

Elite and boss enemies are not valid targets.

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Juggernaut

**File:** `gh-juggernaut.png`  
**Level:** 2  
**Initiative:** 34

### TOP

```text
MOVE 2

ATTACK 2
```

`ENHANCEABLE: MOVE, ATTACK`

The movement and attack are separate abilities in the listed order.

### BOTTOM

```text
On the next three sources of damage to you, suffer no damage instead.

TRACK: [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Any source of damage can qualify; it does not have to be an attack. Each prevented source awards 1 XP.

---

# Level 3 cards

## Brute Force

**File:** `gh-brute-force.png`  
**Level:** 3  
**Initiative:** 51

### TOP

```text
ATTACK 2
AOE pattern below
MUDDLE
XP +1
```

Exact relative AOE:

```text
BRUTE: [0, 0]
TARGETS: [[1, -1],[1, 0], [0, 1]]
```

Equivalent direction set:

```text
targets = {1, 2, 3}
```

Thus, the attack covers three consecutive hexes adjacent to `S`. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, two AOE-hex slots ([0, -1], [-1, 1])`

### BOTTOM

```text
MOVE 2

SHIELD 1
SELF
[ROUND]
```

`ENHANCEABLE: SHIELD`

---

## Hook and Chain

**File:** `gh-hook-and-chain.png`  
**Level:** 3  
**Initiative:** 42

### TOP

```text
ATTACK 3
RANGE 3
PULL 2
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 4

If the movement was in a straight line, perform
ATTACK X
where X is the number of hexes you moved with this action.
```

The attack is a separate ability performed after the movement.

---

# Level 4 cards

## Devastating Hack

**File:** `gh-devastating-hack.png`  
**Level:** 4  
**Initiative:** 27

### TOP

```text
ATTACK 8
XP +3
[LOSS]
```

`ENHANCEABLE: ATTACK (3 marks)`

### BOTTOM

```text
LOOT 1
```

---

## Unstoppable Charge

**File:** `gh-unstoppable-charge.png`  
**Level:** 4  
**Initiative:** 86

### TOP

```text
ATTACK 5
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4

STUN
Target all adjacent enemies

[LOSS]
```

`ENHANCEABLE: MOVE`

The stun application is a separate non-attack ability.

---

# Level 5 cards

## Skirmishing Maneuver

**File:** `gh-skirmishing-maneuver.png`  
**Level:** 5  
**Initiative:** 29

### TOP

```text
ATTACK 2

MOVE 2

ATTACK 2
```

`ENHANCEABLE: MOVE`

These are three separate abilities in the listed order; either attack may be skipped independently.

### BOTTOM

```text
ATTACK 3
RANGE 3
XP +1
```

`ENHANCEABLE: ATTACK`

---

## Whirlwind

**File:** `gh-whirlwind.png`  
**Level:** 5  
**Initiative:** 28

### TOP

```text
ATTACK 4
Target all adjacent enemies
Gain 1 experience for each enemy targeted.

[LOSS]
```

`ENHANCEABLE: ATTACK`

Resolve a separate attack against every valid adjacent enemy.

### BOTTOM

```text
MOVE 4

PUSH 3
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, PUSH`

The push is a separate non-attack ability performed after the movement.

---

# Level 6 cards

## Immovable Phalanx

**File:** `gh-immovable-phalanx.png`  
**Level:** 6  
**Initiative:** 17

### TOP

```text
ATTACK 4

SHIELD 1
SELF
[ROUND]
```

`ENHANCEABLE: ATTACK, SHIELD`

### BOTTOM

```text
You may treat all MOVE abilities as ATTACK abilities of equal value.
[PERSISTENT, LOSS]
```

The choice is made separately whenever SELF performs a Move ability.

---

## Quietus

**File:** `gh-quietus.png`  
**Level:** 6  
**Initiative:** 57

### TOP

```text
Kill one adjacent normal enemy with STUN.
XP +1
```

Elite and boss enemies are not valid targets.

### BOTTOM

```text
MOVE 3

Add +1 ATTACK to all your attacks this round.
[ROUND]
```

`ENHANCEABLE: MOVE`

The attack bonus applies for the remainder of the round.

---

# Level 7 cards

## Crippling Offensive

**File:** `gh-crippling-offensive.png`  
**Level:** 7  
**Initiative:** 33

### TOP

```text
ATTACK 6
WOUND
STUN
XP +3
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 3

IMMOBILIZE and
PUSH 1
Target one adjacent enemy
```

`ENHANCEABLE: MOVE`

Immobilize and Push apply to the same chosen enemy as one separate non-attack ability.

---

## Defensive Tactics

**File:** `gh-defensive-tactics.png`  
**Level:** 7  
**Initiative:** 26

### TOP

```text
ATTACK 2
RANGE 3
TARGET 2
IMMOBILIZE
XP +1
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
RETALIATE 1
SELF

SHIELD 1
SELF

XP +2
[PERSISTENT, LOSS]
```

Both defensive abilities remain active while the card is active.

---

# Level 8 cards

## Frenzied Onslaught

**File:** `gh-frenzied-onslaught.png`  
**Level:** 8  
**Initiative:** 41

### TOP

```text
MOVE 2

ATTACK 2

MOVE 2

ATTACK 2
```

`ENHANCEABLE: first ATTACK, second ATTACK`

These are four separate abilities resolved in the printed order.

### BOTTOM

```text
Add +3 ATTACK to all your attacks targeting enemies with DISARM, IMMOBILIZE, or STUN this round.

XP +1
[ROUND]
```

An enemy needs at least one of the three listed conditions for the bonus to apply.

---

## Selfish Retribution

**File:** `gh-selfish-retribution.png`  
**Level:** 8  
**Initiative:** 12

### TOP

```text
MOVE 1

LOOT 2

[LOSS]
```

`ENHANCEABLE: MOVE`

### BOTTOM

```text
MOVE 3

RETALIATE 2
SELF

XP +1
[ROUND]
```

`ENHANCEABLE: MOVE`

---

# Level 9 cards

## Face Your End

**File:** `gh-face-your-end.png`  
**Level:** 9  
**Initiative:** 67

### TOP

```text
ATTACK 2
RANGE 4
TARGET 3
PULL 3
XP +1
```

`ENHANCEABLE: ATTACK, TARGET, PULL`

### BOTTOM

```text
Kill one adjacent normal or elite enemy.
XP +2
[LOSS]
```

Boss enemies are not valid targets.

---

## King of the Hill

**File:** `gh-king-of-the-hill.png`  
**Level:** 9  
**Initiative:** 39

### TOP

```text
ATTACK 6
Target all adjacent enemies
PUSH 1
Gain 1 experience for each enemy targeted.

[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

Resolve a separate attack against every valid adjacent enemy. Push applies to each corresponding attack target.

### BOTTOM

```text
HEAL 5
SELF
```

---

# File checklist

```text
gh-balanced-measure.png
gh-br-back.png
gh-brute-force.png
gh-crippling-offensive.png
gh-defensive-tactics.png
gh-devastating-hack.png
gh-eye-for-an-eye.png
gh-face-your-end.png
gh-fatal-advance.png
gh-frenzied-onslaught.png
gh-grab-and-go.png
gh-hook-and-chain.png
gh-immovable-phalanx.png
gh-juggernaut.png
gh-king-of-the-hill.png
gh-leaping-cleave.png
gh-overwhelming-assault.png
gh-provoking-roar.png
gh-quietus.png
gh-selfish-retribution.png
gh-shield-bash.png
gh-skewer.png
gh-skirmishing-maneuver.png
gh-spare-dagger.png
gh-sweeping-blow.png
gh-trample.png
gh-unstoppable-charge.png
gh-wall-of-doom.png
gh-warding-strength.png
gh-whirlwind.png
```

---

# Source cross-checks used

- Worldhaven image folder: `images/character-ability-cards/gloomhaven/br/`
- Full printed action wording checked against all 29 fronts in the three supplied card images
- Original enhancement annotations and AOE data retained
- Gloomhaven card metadata used to cross-check names, levels, initiatives, and filenames
- The supplied Berserker transcription used as the notation and document-structure template
