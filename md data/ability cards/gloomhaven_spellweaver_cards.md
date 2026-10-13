# Gloomhaven – Spellweaver (SW) Ability Cards

**Scope:** [`images/character-ability-cards/gloomhaven/sw/`](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sw)  
**Files covered:** 28 PNG files = 27 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, tracks, duration, loss, summon statistics, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

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
- `ALLY` never includes `SELF`.
- `ADJACENT` means a distance of exactly 1 hex.
- `TARGET n` means the attack may affect up to `n` different valid targets.

## Common keywords

```text
ATTACK n
MOVE n
JUMP
HEAL n [SELF | RANGE n]
SHIELD n [SELF | ALLY]
RETALIATE n [RANGE n] [SELF | ALLY]
LOOT n
PUSH n
PULL n
CURSE
WOUND
STUN
MUDDLE
IMMOBILIZE
INVISIBLE
SUMMON name
```

Printed direct-damage, recovery, and kill instructions are written out in full. Direct damage is not an attack.

## Elements

```text
INFUSE FIRE
INFUSE ICE
INFUSE AIR
INFUSE EARTH
INFUSE LIGHT
INFUSE DARK

INFUSE ANY  => choose exactly one of the six elements
INFUSE ALL  => infuse all six elements

CONSUME FIRE => effect
CONSUME ANY  => consume exactly one available element of any type => effect
```

A consume line is conditional: the listed effect is obtained only when the required element is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[UNRECOVERABLE LOSS]` — the card is lost and cannot be recovered.
- `[ROUND, LOSS]` — remains active for the round, then moves to the lost pile.
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
ENHANCEABLE: HEAL (2 marks), MOVE
ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON RANGE
ENHANCEABLE: one AOE hex
```

## Summons

Summon statistics are normalized as:

```text
SUMMON name [PERSISTENT, LOSS]
- HEALTH n
- MOVE n
- ATTACK n
- RANGE n
- SPECIAL: ...
```

A summon with omitted `RANGE` performs melee attacks.

## Full wording

Conditions and definitions of X are written out on every card. Experience icons inside sentences are written as “n experience”; standalone experience icons use `XP +n`.

## AOE diagrams and coordinates

- `S` = acting figure / origin hex.
- `T` = printed target hex.
- `E` = printed enhancement-only hex; it is not part of the area unless enhanced.
- `.` = unused position shown only to preserve the shape.

AOE coordinate sets use normalized axial coordinates `(q,r)`. They encode only the relative shape, not an absolute board position.

- A printed AOE may be rotated, but not mirrored.
- For a ranged AOE, placement must satisfy the normal ranged-area targeting rules; the coordinates only define the shape.

---

# Card back

## `gh-sw-back.png`

Spellweaver ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Fire Orbs

**File:** `gh-fire-orbs.png`  
**Level:** 1  
**Initiative:** 69

### TOP

```text
ATTACK 3
RANGE 3
TARGET 3
Gain 1 experience for each enemy targeted.

INFUSE FIRE
[LOSS]
```

Each selected enemy is resolved as a separate attack. XP depends on the number of enemies targeted, not on damage dealt.

`ENHANCEABLE: ATTACK, RANGE, TARGET`

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Flame Strike

**File:** `gh-flame-strike.png`  
**Level:** 1  
**Initiative:** 36

### TOP

```text
ATTACK 3
RANGE 2
CONSUME FIRE: WOUND
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
ATTACK 2
RANGE 2
```

`ENHANCEABLE: ATTACK, RANGE`

---

## Freezing Nova

**File:** `gh-freezing-nova.png`  
**Level:** 1  
**Initiative:** 21

### TOP

```text
ATTACK 2
Target all adjacent enemies
IMMOBILIZE
CONSUME ICE: +1 ATTACK
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 4
RANGE 4
INFUSE LIGHT
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

---

## Frost Armor

**File:** `gh-frost-armor.png`  
**Level:** 1  
**Initiative:** 20

### TOP

```text
ATTACK 2
RANGE 3
CONSUME ANY: +1 ATTACK, 1 experience
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
On the next two sources of damage to you, suffer no damage instead.

INFUSE ICE
TRACK: [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only a source that would deal damage consumes a use. Each prevented source awards 1 XP.

---

## Impaling Eruption

**File:** `gh-impaling-eruption.png`  
**Level:** 1  
**Initiative:** 70

### TOP

```text
ATTACK 3
RANGE 4
Additionally, target all enemies on the path to the primary target.
Gain 1 experience for each enemy targeted.

INFUSE EARTH
[LOSS]
```

The path must end at the primary target. The primary target and each additional enemy on the chosen path are separate attack targets.

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Mana Bolt

**File:** `gh-mana-bolt.png`  
**Level:** 1  
**Initiative:** 07

### TOP

```text
ATTACK 2
RANGE 3
CONSUME ANY: +1 ATTACK, 1 experience
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 3
RANGE 1
```

`ENHANCEABLE: HEAL, RANGE`

---

## Reviving Ether

**File:** `gh-reviving-ether.png`  
**Level:** 1  
**Initiative:** 80

### TOP

```text
Recover all your lost cards.
INFUSE DARK
[UNRECOVERABLE LOSS]
```

This card itself is not recovered: it enters the lost pile only after its action has resolved, and its unrecoverable-loss symbol prevents later recovery.

### BOTTOM

```text
MOVE 4
JUMP
```

`ENHANCEABLE: MOVE`

---

## Ride the Wind

**File:** `gh-ride-the-wind.png`  
**Level:** 1  
**Initiative:** 83

### TOP

```text
LOOT 1
```

### BOTTOM

```text
MOVE 8
JUMP
INFUSE AIR
[LOSS]
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Aid from the Ether

**File:** `gh-aid-from-the-ether.png`  
**Level:** X  
**Initiative:** 91

### TOP

```text
HEAL 3
RANGE 3
```

`ENHANCEABLE: HEAL, RANGE`

### BOTTOM

```text
SUMMON Mystic Ally
HEALTH 2
MOVE 2
ATTACK 3
RANGE 2

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON RANGE`

---

## Crackling Air

**File:** `gh-crackling-air.png`  
**Level:** X  
**Initiative:** 25

### TOP

```text
On your next four attacks, add +1 ATTACK.
CONSUME AIR: Add +2 ATTACK instead.

TRACK: [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second and fourth modified attacks each award 1 XP. The element is consumed once when this persistent action is activated, not separately for every attack.

### BOTTOM

```text
MOVE 3

CONSUME FIRE:
RETALIATE 2
SELF
[ROUND]
```

`ENHANCEABLE: MOVE`

---

## Hardened Spikes

**File:** `gh-hardened-spikes.png`  
**Level:** X  
**Initiative:** 26

### TOP

```text
RETALIATE 2
Affect SELF and all adjacent allies
CONSUME EARTH: +1 RETALIATE

XP +2
[ROUND, LOSS]
```

Consuming Earth changes every granted Retaliate value from 2 to 3 for the round.

`ENHANCEABLE: RETALIATE`

### BOTTOM

```text
MOVE 3

CONSUME ICE:
SHIELD 2
SELF
[ROUND]
```

`ENHANCEABLE: MOVE`

---

# Level 2 cards

## Flashing Burst

**File:** `gh-flashing-burst.png`  
**Level:** 2  
**Initiative:** 26

### TOP

```text
ATTACK 3
RANGE 3
INFUSE LIGHT
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Icy Blast

**File:** `gh-icy-blast.png`  
**Level:** 2  
**Initiative:** 66

### TOP

```text
ATTACK 2
RANGE 3
AOE pattern below
MUDDLE
INFUSE ICE
XP +2
[LOSS]
```

Exact relative AOE:

```text
      T   T
    T   T   T
      T   T
```

Normalized axial coordinates:

```text
targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2)}
```

The action resolves one separate attack against each enemy occupying a target hex.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 6
RANGE 2
[LOSS]
```

`ENHANCEABLE: HEAL, RANGE`

---

# Level 3 cards

## Cold Fire

**File:** `gh-cold-fire.png`  
**Level:** 3  
**Initiative:** 67

### TOP

```text
ATTACK 1
RANGE 3
AOE pattern below
CONSUME FIRE: +2 ATTACK
CONSUME ICE: STUN
```

Both elements may be consumed, and their effects stack.

Exact relative AOE:

```text
E   T
  T   T
```

Normalized axial coordinates:

```text
targets     = {(1,0), (0,1), (1,1)}
enhance_hex =  (0,0)
```

`E` is not attacked unless that printed AOE slot has been enhanced.

`ENHANCEABLE: one AOE hex`

### BOTTOM

```text
LOOT 2
[LOSS]
```

---

## Elemental Aid

**File:** `gh-elemental-aid.png`  
**Level:** 3  
**Initiative:** 84

### TOP

```text
HEAL 3
RANGE 2
CONSUME EARTH: +2 HEAL, 1 experience
```

`ENHANCEABLE: HEAL, RANGE`

### BOTTOM

```text
SHIELD 2
Affect any one ally
CONSUME AIR: Affect all allies instead
[ROUND]
```

`SELF` is not affected because the printed target is ally/all allies.

---

# Level 4 cards

## Forked Beam

**File:** `gh-forked-beam.png`  
**Level:** 4  
**Initiative:** 20

### TOP

```text
ATTACK 2
RANGE 3
TARGET 2
XP +1
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Spirit of Doom

**File:** `gh-spirit-of-doom.png`  
**Level:** 4  
**Initiative:** 81

### TOP

```text
CURSE
RANGE 4
CONSUME DARK: Kill one normal target instead, 1 experience
[LOSS]
```

Without consuming Dark, the action applies Curse. With Dark, the Curse is replaced by the execution effect.

### BOTTOM

```text
HEAL X
Affect any one ally
where X is half of that ally’s maximum hit point value (rounded down).
CONSUME LIGHT: X is that ally’s maximum hit point value instead.

XP +1
[LOSS]
```

The base value is half of the chosen ally's maximum HP, rounded down. This action cannot target `SELF`.

---

# Level 5 cards

## Chromatic Explosion

**File:** `gh-chromatic-explosion.png`  
**Level:** 5  
**Initiative:** 71

### TOP

```text
ATTACK 3
RANGE 3
AOE pattern below
INFUSE AIR
INFUSE ICE
INFUSE FIRE
INFUSE EARTH
INFUSE LIGHT
INFUSE DARK
XP +2
[LOSS]
```

Exact relative AOE:

```text
    T
  T   T
```

Normalized axial coordinates:

```text
targets = {(1,0), (0,1), (1,1)}
```

`INFUSE ALL` infuses Fire, Ice, Air, Earth, Light, and Dark.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2
INFUSE ANY
```

Choose exactly one element to infuse.

`ENHANCEABLE: MOVE`

---

## Engulfed in Flames

**File:** `gh-engulfed-in-flames.png`  
**Level:** 5  
**Initiative:** 44

### TOP

```text
ATTACK 4
RANGE 3
INFUSE FIRE
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
On the next five melee attacks targeting you, gain RETALIATE 3.

TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The first, third, and fifth qualifying attacks each award 1 XP. A use is consumed when a melee attack targets `SELF`, regardless of whether the attack ultimately deals damage.

---

# Level 6 cards

## Frozen Night

**File:** `gh-frozen-night.png`  
**Level:** 6  
**Initiative:** 46

### TOP

```text
ATTACK 4
AOE pattern below
Gain 1 experience for each enemy targeted.
INFUSE ICE
[LOSS]
```

Printed pattern, including its two enhancement-only hexes:

```text
(WRONG !!!!!!!)
        T   E
      T   T   E
        S   T   T
```

Normalized axial coordinates:

```text
origin       =  (0,2)
targets      = {(2, 0), (2, 1), (1,1), (1,2), (2,2)}
enhance_hexes = {(3,0), (3,1)}
```

Each occupied target hex produces a separate attack. XP depends on enemies targeted. `E` hexes are excluded until enhanced.

`ENHANCEABLE: ATTACK, two AOE hexes`

### BOTTOM

```text
MOVE 3
CONSUME DARK:
+2 MOVE
INVISIBLE
SELF
XP +1
```

`ENHANCEABLE: MOVE`

---

## Living Torch

**File:** `gh-living-torch.png`  
**Level:** 6  
**Initiative:** 96

### TOP

```text
ATTACK 4
RANGE 3
IMMOBILIZE
CONSUME LIGHT: All enemies adjacent to the target suffer 2 damage, 1 experience
```

The adjacent-enemy damage is direct damage, not an additional attack. The original target is not adjacent to itself and therefore is not included in that effect.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
SUMMON Burning Avatar
HEALTH 2
MOVE 3
ATTACK 2
RANGE 3
INFUSE FIRE on its attacks

On death:
ATTACK 3
Target all adjacent enemies

XP +2
[PERSISTENT, LOSS]
```

The on-death attacks are performed by the summon immediately when it dies.

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON RANGE`

---

# Level 7 cards

## Stone Fists

**File:** `gh-stone-fists.png`  
**Level:** 7  
**Initiative:** 62

### TOP

```text
ATTACK 6
PUSH 2
IMMOBILIZE
CONSUME EARTH: TARGET 2, 1 experience

XP +2
[LOSS]
```

Consuming Earth allows up to two separate targets and raises the total printed XP from 2 to 3.

`ENHANCEABLE: PUSH`

### BOTTOM

```text
MOVE 3

SHIELD 1
SELF

CONSUME EARTH: +1 MOVE, +1 SHIELD, 1 experience
[ROUND]
```

With Earth consumed, this becomes `MOVE 4` followed by `SHIELD 2, SELF [ROUND]`.

`ENHANCEABLE: MOVE, SHIELD`

---

## Twin Restoration

**File:** `gh-twin-restoration.png`  
**Level:** 7  
**Initiative:** 75

### TOP

```text
Recover up to two of your lost cards.
[UNRECOVERABLE LOSS]
```

This card itself cannot be selected because it enters the lost pile only after the action resolves. Its unrecoverable-loss symbol prevents later recovery.

### BOTTOM

```text
HEAL 3
RANGE 3
TARGET 2
```

Each target receives a separate Heal 3 ability.

`ENHANCEABLE: HEAL, TARGET`

---

# Level 8 cards

## Cold Front

**File:** `gh-cold-front.png`  
**Level:** 8  
**Initiative:** 61

### TOP

```text
ATTACK 5
RANGE 2
AOE pattern below
INFUSE ICE
XP +2
[LOSS]
```

Exact relative AOE :

```text
(WRONG !!!!!!!)
        T
      T
        T
      T
        T

```

Normalized axial coordinates:

```text
targets = {(-2,2), (-1,1), (0,0), (1,-1), (2,-2)}
```

The five hexes form one connected zig-zag line. The pattern may be rotated but not mirrored.

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
On the next three sources of damage from attacks targeting you, suffer no damage instead and gain RETALIATE 3, RANGE 3.

TRACK: [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only damage originating from an attack that targets `SELF` qualifies. Each prevented source awards 1 XP.

---

## Zephyr Wings

**File:** `gh-zephyr-wings.png`  
**Level:** 8  
**Initiative:** 85

### TOP

```text
LOOT 2
CONSUME AIR: LOOT 3 instead, 1 experience

You may not loot more than four money tokens or treasure tiles with this action.
```

The four-object cap applies even when the expanded Loot 3 area contains more valid objects.

### BOTTOM

```text
MOVE 8
JUMP
CONSUME AIR: +3 MOVE, 1 experience

XP +1
[LOSS]
```

With Air consumed, this is `MOVE 11, JUMP` and awards 2 XP total.

`ENHANCEABLE: MOVE (2 marks)`

---

# Level 9 cards

## Black Hole

**File:** `gh-black-hole.png`  
**Level:** 9  
**Initiative:** 41

### TOP

```text
ATTACK 4
RANGE 3
AOE pattern below
Gain 1 experience for each enemy targeted.
CONSUME DARK: Kill all normal enemies in the targeted area.
[LOSS]
```

Exact relative AOE:

```text
    T
  T   T
```

Normalized axial coordinates:

```text
targets = {(1,0), (0,1), (1,1)}
```

The Dark effect executes only normal enemies in the targeted area; it does not execute elite or boss enemies.

### BOTTOM

```text
Choose a hex within RANGE 4.

PULL 2
Target all enemies within RANGE 4 of the chosen hex and pull them toward it
```

The range used to select the central hex is measured from `SELF`; the second range is measured from that chosen hex.

`ENHANCEABLE: PULL`

---

## Inferno

**File:** `gh-inferno.png`  
**Level:** 9  
**Initiative:** 19

### TOP

```text
ATTACK 3
Target all enemies in the same room as you
CONSUME FIRE: +1 ATTACK, 1 experience

All allies in the same room as you suffer 2 damage.
```

Each enemy is a separate target of the attack. `SELF` does not suffer the ally damage because a figure is not its own ally.

### BOTTOM

```text
RETALIATE 2
RANGE 3
Affect SELF and all allies
[ROUND]
```

`ENHANCEABLE: RETALIATE`

---

# File checklist

```text
gh-sw-back.png
gh-aid-from-the-ether.png
gh-black-hole.png
gh-chromatic-explosion.png
gh-cold-fire.png
gh-cold-front.png
gh-crackling-air.png
gh-elemental-aid.png
gh-engulfed-in-flames.png
gh-fire-orbs.png
gh-flame-strike.png
gh-flashing-burst.png
gh-forked-beam.png
gh-freezing-nova.png
gh-frost-armor.png
gh-frozen-night.png
gh-hardened-spikes.png
gh-icy-blast.png
gh-impaling-eruption.png
gh-inferno.png
gh-living-torch.png
gh-mana-bolt.png
gh-reviving-ether.png
gh-ride-the-wind.png
gh-spirit-of-doom.png
gh-stone-fists.png
gh-twin-restoration.png
gh-zephyr-wings.png
```

---

# Source cross-checks used

- [Worldhaven Spellweaver image folder](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sw)
- Gloomhaven Card Browser card metadata and filenames
- Gloomhaven Secretariat first-edition Spellweaver structured ability definitions and custom card text
- HavenPlanner and Datahaven enhancement-slot and summon-stat data
- Full printed action wording checked against all 27 fronts in the three supplied card images
- Unrecoverable-loss symbols restored on Reviving Ether and Twin Restoration
- Original metadata, enhancements, tracks, and AOE data retained
