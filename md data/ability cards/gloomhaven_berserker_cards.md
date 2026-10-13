# Gloomhaven – Berserker (BE) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/be/`  
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
WOUND
STUN
MUDDLE
IMMOBILIZE
ADVANTAGE
DISADVANTAGE
EXECUTE
```

Printed “suffer damage” sentences are written out in full, not replaced with formulas. `XP +n` represents an experience icon; an experience icon inside a sentence is written as “n experience”.

## Elements

```text
INFUSE FIRE
CONSUME FIRE => effect
```

A consume line is conditional: the listed effect is obtained only when `FIRE` is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [XP+1] [ ] [XP+1] [ ] -> end
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

Conditions and definitions of X are written out on every card. No shorthand such as `LOW_HP`, `MISSING_HP`, `LOST_CARDS`, or `SUFFER_DAMAGE` is used in action text.

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

## `gh-be-back.png`

Berserker / Lightning Bolt ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Blood Pact

**File:** `gh-blood-pact.png`  
**Level:** 1  
**Initiative:** 76

### TOP

```text
ATTACK 6
Suffer damage equal to half of your current hit point value (rounded up).
XP +1
```

The self-damage is equal to half the character's current hit point value at that moment, rounded upward.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
Add +1 ATTACK to all your attacks.
Suffer 1 damage at the start of each of your turns.

XP +2
[PERSISTENT, LOSS]
```

---

## Bounce Back

**File:** `gh-bounce-back.png`  
**Level:** 1  
**Initiative:** 14

### TOP

```text
RETALIATE 2
SELF
[ROUND]
```

`ENHANCEABLE: RETALIATE`

### BOTTOM

```text
MOVE 3

HEAL 1
SELF
```

`ENHANCEABLE: HEAL`

---

## Cauterize

**File:** `gh-cauterize.png`  
**Level:** 1  
**Initiative:** 40

### TOP

```text
ATTACK 1
RANGE 3
TARGET 2
WOUND
INFUSE FIRE
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
The next four times you are healed, add +2 HEAL.

TRACK: [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second and fourth triggered heals each award 1 XP.

---

## Dazing Wound

**File:** `gh-dazing-wound.png`  
**Level:** 1  
**Initiative:** 29

### TOP

```text
ATTACK 3
You may suffer 2 damage to add STUN and gain 1 experience.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Defiance of Death

**File:** `gh-defiance-of-death.png`  
**Level:** 1  
**Initiative:** 31

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience if your current hit point value is less than half your maximum hit point value (rounded up).
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
On the next three sources of damage to you that would reduce you to less than 1 hit point, suffer no damage instead.

TRACK: [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying damage sources consume uses.

---

## From the Brink

**File:** `gh-from-the-brink.png`  
**Level:** 1  
**Initiative:** 24

### TOP

```text
HEAL X
RANGE 2
where X is the difference between your maximum hit point value and current hit point value.

XP +2
[LOSS]
```

The heal value is calculated from the Berserker's own missing HP, even when another figure is healed.

### BOTTOM

```text
MOVE 3

PUSH 1
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, PUSH`

---

## Furious Aid

**File:** `gh-furious-aid.png`  
**Level:** 1  
**Initiative:** 43

### TOP

```text
LOOT 2
XP +1
[LOSS]
```

### BOTTOM

```text
HEAL 3
RANGE 3
```

`ENHANCEABLE: HEAL`

---

## Growing Rage

**File:** `gh-growing-rage.png`  
**Level:** 1  
**Initiative:** 85

### TOP

```text
ATTACK X
where X is the number of cards you have lost.

XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3

If you have fewer hit points than half your maximum hit point value (rounded up), perform
ATTACK 2
and gain 1 experience.
```

`ENHANCEABLE: MOVE`

The attack is a separate ability performed after the movement.

---

## Resolute Stand

**File:** `gh-resolute-stand.png`  
**Level:** 1  
**Initiative:** 09

### TOP

```text
ATTACK X
where X is the difference between your maximum hit point value and current hit point value.
Gain 1 experience for every 5 damage done.

[LOSS]
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2

RETALIATE 1
SELF
[ROUND]
```

`ENHANCEABLE: MOVE, RETALIATE`

---

## Strength in Agony

**File:** `gh-strength-in-agony.png`  
**Level:** 1  
**Initiative:** 37

### TOP

```text
ATTACK 3
You may suffer 2 damage to add +2 ATTACK and gain 1 experience.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
You may suffer up to 3 damage.
MOVE 4+X
where X is the amount of damage you suffered.
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Glass Hammer

**File:** `gh-glass-hammer.png`  
**Level:** X  
**Initiative:** 11

### TOP

```text
ATTACK X
where X is your current hit point value.
Gain 1 experience for every 5 damage done.

Reduce your current hit point value to 1.
This is not considered damage.

[LOSS]
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Numb the Pain

**File:** `gh-numb-the-pain.png`  
**Level:** X  
**Initiative:** 35

### TOP

```text
ATTACK 3
You may suffer 2 damage to gain SHIELD 1, 1 experience.
[ROUND]
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3

You may suffer 2 damage to STUN one adjacent enemy.
```

`ENHANCEABLE: MOVE`

The stun is a separate non-attack ability.

---

## Unbridled Power

**File:** `gh-unbridled-power.png`  
**Level:** X  
**Initiative:** 67

### TOP

```text
ATTACK 2
STUN
CONSUME FIRE: +2 ATTACK
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
You may overheal to a maximum of 26 hit points, but your maximum hit point value remains the same for the purpose of all ability effects.

HEAL 3
SELF
XP +2
[PERSISTENT, LOSS]
```

---

# Level 2 cards

## Break the Chains

**File:** `gh-break-the-chains.png`  
**Level:** 2  
**Initiative:** 53

### TOP

```text
ATTACK 2
RANGE 3
TARGET 2
PULL 2
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
MOVE 3
Add +2 MOVE, if you have fewer hit points than half of your maximum hit point value (rounded up).
CONSUME FIRE: +2 MOVE, 1 experience
```

The two `+2 MOVE` modifiers are independent and may stack, producing `MOVE 7`.

`ENHANCEABLE: MOVE`

---

## Reckless Offensive

**File:** `gh-reckless-offensive.png`  
**Level:** 2  
**Initiative:** 21

### TOP

```text
You may suffer up to 3 damage.
ATTACK 4
TARGET X
where X is the amount of damage you suffered.
```

`X` is exactly the amount of self-damage chosen for this action.

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
All melee attacks targeting you add +1 ATTACK.

RETALIATE 2
SELF
[PERSISTENT, LOSS]
```

---

# Level 3 cards

## Fatal Fury

**File:** `gh-fatal-fury.png`  
**Level:** 3  
**Initiative:** 34

### TOP

```text
Kill one adjacent normal enemy whose current hit point value is less than half the difference between your maximum hit point value and current hit point value.

INFUSE FIRE
XP +1
```

The comparison is strict. Elite and boss enemies are not valid targets.

### BOTTOM

```text
ATTACK 2

MOVE 1

ATTACK 2
```

`ENHANCEABLE: MOVE`

These are three separate abilities in the listed order; either attack may be skipped independently.

---

## Spiked Armor

**File:** `gh-spiked-armor.png`  
**Level:** 3  
**Initiative:** 16

### TOP

```text
Increase the value of each of your RETALIATE abilities by 2 this round.
XP +1
[ROUND]
```

This modifies each Retaliate ability, rather than granting one standalone Retaliate ability.

### BOTTOM

```text
MOVE 2

ATTACK 2
Target all adjacent enemies
```

`ENHANCEABLE: MOVE, ATTACK`

---

# Level 4 cards

## Flurry of Axes

**File:** `gh-flurry-of-axes.png`  
**Level:** 4  
**Initiative:** 27

### TOP

```text
You may suffer up to 4 damage.
ATTACK X
RANGE 3
Target all enemies
where X is the amount of damage you suffered.

XP +2
[LOSS]
```

A separate attack is resolved against every valid enemy in range.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Shiny Distraction

**File:** `gh-shiny-distraction.png`  
**Level:** 4  
**Initiative:** 08

### TOP

```text
LOOT 1

SHIELD 1
SELF
[ROUND]
```

`ENHANCEABLE: SHIELD`

### BOTTOM

```text
Gain ADVANTAGE on all your attacks this round.
All attacks targeting you gain DISADVANTAGE this round.

INFUSE FIRE
[ROUND]
```

---

# Level 5 cards

## Final Fight

**File:** `gh-final-fight.png`  
**Level:** 5  
**Initiative:** 89

### TOP

```text
Kill one adjacent normal or elite enemy.
You immediately become exhausted.

XP +3
```

This action is not marked as a loss; exhaustion is its explicit consequence.

### BOTTOM

```text
MOVE X
where X is the number of cards you have lost.

CONSUME FIRE: ATTACK 3
```

`ENHANCEABLE: MOVE`

The attack is a separate ability after the movement.

---

## Seeing Red

**File:** `gh-seeing-red.png`  
**Level:** 5  
**Initiative:** 22

### TOP

```text
ATTACK 4
Gain SHIELD 1, 1 experience if you have fewer hit points than half of your maximum hit point value (rounded up).
[ROUND]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
Add +1 ATTACK to all your attacks when you have fewer hit points than half of your maximum hit point value (rounded up).

XP +2
[PERSISTENT, LOSS]
```

The bonus automatically turns on or off as the printed hit point condition changes.

---

# Level 6 cards

## Devil Horns

**File:** `gh-devil-horns.png`  
**Level:** 6  
**Initiative:** 31

### TOP

```text
ATTACK 3
AOE pattern below
WOUND
INFUSE FIRE
```

Exact relative AOE:

```text
BERSERKER: [0, 0]
Fields targetet: [[1, -1],[1, 0],[0, 1]]
```

Equivalent direction set:

```text
targets = {6, 1, 2, 3}
```

Thus, the attack covers four consecutive hexes adjacent to `S`. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, one AOE-hex slot ([0, -1])`

### BOTTOM

```text
ATTACK 4
XP +1
```

`ENHANCEABLE: ATTACK`

---

## Unstoppable Destruction

**File:** `gh-unstoppable-destruction.png`  
**Level:** 6  
**Initiative:** 57

### TOP

```text
ATTACK 4
You may suffer 2 damage to ignore the target’s SHIELD value and gain 1 experience.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
If you short rest at the end of this round, gain all the benefits of a long rest.
[ROUND]
```

---

# Level 7 cards

## Burning Hatred

**File:** `gh-burning-hatred.png`  
**Level:** 7  
**Initiative:** 40

### TOP

```text
ATTACK 3
Target all adjacent enemies
CONSUME FIRE: Target all enemies up to two hexes away instead
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3

WOUND
Target all adjacent enemies
```

`ENHANCEABLE: MOVE`

The wound application is not an attack.

---

## Careless Charge

**File:** `gh-careless-charge.png`  
**Level:** 7  
**Initiative:** 20

### TOP

```text
MOVE 3

ATTACK 4
WOUND SELF and all adjacent allies to add PUSH 2, IMMOBILIZE.
```

`ENHANCEABLE: MOVE, ATTACK`

The Wound affects SELF and all adjacent allies as one cost; the target of the attack receives Push and Immobilize.

### BOTTOM

```text
You are immune to all negative conditions.

XP +2
[PERSISTENT, LOSS]
```

---

# Level 8 cards

## Bone Breaker

**File:** `gh-bone-breaker.png`  
**Level:** 8  
**Initiative:** 41

### TOP

```text
ATTACK 5
WOUND
IMMOBILIZE
INFUSE FIRE
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 3
RANGE 4
Target can no longer fly.
To signify this, place a character token on the target.
```

`ENHANCEABLE: ATTACK`

The attack may still target an enemy that does not currently fly; in that case, the additional flying-removal effect changes nothing.

---

## Vengeful Barrage

**File:** `gh-vengeful-barrage.png`  
**Level:** 8  
**Initiative:** 38

### TOP

```text
On the next five sources of damage to you, perform an "ATTACK 3" action.

TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Each trigger creates a separate attack action. The first, third, and fifth uses each award 1 XP.

### BOTTOM

```text
MOVE 4

CONSUME FIRE: Add +1 ATTACK to all your attacks this round, 1 experience.
[ROUND]
```

`ENHANCEABLE: MOVE`

The bonus applies to all of SELF's attack actions for the remainder of the round, not merely one attack.

---

# Level 9 cards

## Immortality

**File:** `gh-immortality.png`  
**Level:** 9  
**Initiative:** 33

### TOP

```text
On the next five sources of damage to you, suffer no damage instead.

TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The first, third, and fifth prevented damage sources each award 1 XP.

### BOTTOM

```text
MOVE 2

SHIELD 2
SELF

CONSUME FIRE: RETALIATE 2
SELF
[ROUND]
```

`ENHANCEABLE: MOVE, SHIELD`

---

## The Maw of Madness

**File:** `gh-the-maw-of-madness.png`  
**Level:** 9  
**Initiative:** 10

### TOP

```text
ATTACK 3
Target all adjacent enemies
MUDDLE

Perform a "HEAL 2, SELF" action for each enemy damaged.
```

`ENHANCEABLE: ATTACK`

Example: if three enemies actually suffer damage, perform three separate `HEAL 2, SELF` abilities.

### BOTTOM

```text
You may suffer up to 5 damage.
ATTACK 3+X
RANGE 3
IMMOBILIZE
where X is the amount of damage you suffered.
```

`ENHANCEABLE: ATTACK, RANGE`

---

# File checklist

```text
gh-be-back.png
gh-blood-pact.png
gh-bone-breaker.png
gh-bounce-back.png
gh-break-the-chains.png
gh-burning-hatred.png
gh-careless-charge.png
gh-cauterize.png
gh-dazing-wound.png
gh-defiance-of-death.png
gh-devil-horns.png
gh-fatal-fury.png
gh-final-fight.png
gh-flurry-of-axes.png
gh-from-the-brink.png
gh-furious-aid.png
gh-glass-hammer.png
gh-growing-rage.png
gh-immortality.png
gh-numb-the-pain.png
gh-reckless-offensive.png
gh-resolute-stand.png
gh-seeing-red.png
gh-shiny-distraction.png
gh-spiked-armor.png
gh-strength-in-agony.png
gh-the-maw-of-madness.png
gh-unbridled-power.png
gh-unstoppable-destruction.png
gh-vengeful-barrage.png
```

---

# Source cross-checks used

- All 29 card fronts in the three supplied Berserker card images
- Existing metadata, enhancement annotations, and file checklist retained from the supplied Markdown; ATTACK, MOVE, and experience values corrected where the card images differ; original enhancement annotations and AOE data retained
