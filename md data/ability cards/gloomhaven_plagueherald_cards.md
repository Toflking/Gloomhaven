# Gloomhaven – Plagueherald (PH) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/ph/`  
**Source folder:** [Worldhaven – Gloomhaven Plagueherald ability cards](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/ph)  
**Files covered:** 31 PNG files = 30 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes and coordinate listings are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Kill, conditional, and other special effects use the full printed wording. `AOE pattern below` refers to the separate preserved coordinate listing.

## Elements

```text
INFUSE AIR
INFUSE DARK
CONSUME AIR => effect
CONSUME DARK => effect
```

A consume line is conditional: the listed effect is obtained only when that element is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
```

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: two AOE-hex slots
```

## AOE diagrams

- `T` = a hex in the printed targeted area.
- Every enemy selected in a targeted hex is resolved as a separate attack.
- A printed area pattern may be rotated; its relative shape remains unchanged.
- An enhancement mark belonging to an area pattern permits an additional AOE hex through the normal enhancement rules.

Compact seven-hex pattern used by several cards:

```text
    T   T
  T   T   T
    T   T
```

Three-hex triangular pattern:

```text
    T
  T   T
```

---

# Card back

## `gh-ph-back.png`

Plagueherald / Cthulhu ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Biting Gnats

**File:** `gh-biting-gnats.png`  
**Level:** 1  
**Initiative:** 47

### TOP

```text
ATTACK 2
RANGE 4
TARGET 2
XP +1
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
MOVE 4
```

---

## Creeping Curse

**File:** `gh-creeping-curse.png`  
**Level:** 1  
**Initiative:** 72

### TOP

```text
ATTACK 3
RANGE 3
CURSE
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2

CURSE
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, CURSE`

The Curse is a separate non-attack ability performed after the movement.

---

## Foul Wind

**File:** `gh-foul-wind.png`  
**Level:** 1  
**Initiative:** 35

### TOP

```text
During your next six Attack actions
where AIR is Strong or Waning, you may CONSUME AIR
to add +1 ATTACK to the entire Attack action.
TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The printed track refers to the next six Attack actions where Air is Strong or Waning; consuming Air for the bonus is optional. The modifier applies to every separate attack belonging to that attack action.

### BOTTOM

```text
LOOT 1
```

---

## Gathering Doom

**File:** `gh-gathering-doom.png`  
**Level:** 1  
**Initiative:** 76

### TOP

```text
ATTACK 3
RANGE 3
CURSE
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
LOOT 2
INFUSE AIR
XP +1
[LOSS]
```

---

## Grasping Vermin

**File:** `gh-grasping-vermin.png`  
**Level:** 1  
**Initiative:** 69

### TOP

```text
ATTACK 0
RANGE 3
IMMOBILIZE
AOE pattern below

targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2)}
```

### BOTTOM

```text
SHIELD 1
Self

Deduct -1 RANGE from
all your ranged attacks.
XP +1
[PERSISTENT, LOSS]
```

The Range penalty applies to all of SELF's ranged attacks while the card remains active.

---

## Paralyzing Bite

**File:** `gh-paralyzing-bite.png`  
**Level:** 1  
**Initiative:** 31

### TOP

```text
ATTACK 1
RANGE 3
STUN
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 6
Jump
INFUSE AIR
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE`

---

## Scattered Terror

**File:** `gh-scattered-terror.png`  
**Level:** 1  
**Initiative:** 11

### TOP

```text
ATTACK 2
RANGE 3
TARGET 3
PIERCE 2
INFUSE AIR
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE, TARGET, PIERCE`

### BOTTOM

```text
All attacks targeting you
gain Disadvantage this round.
[ROUND]
```

---

## Spread the Plague

**File:** `gh-spread-the-plague.png`  
**Level:** 1  
**Initiative:** 27

### TOP

```text
Once during each of your Attack actions,
you may POISON one ally within
RANGE 2 (or deal 3 damage if the
ally already has POISON) to add
+1 ATTACK to the entire Attack action.
XP +2
[PERSISTENT, LOSS]
```

The chosen ally is the cost of obtaining the attack bonus.  
The bonus applies to every separate attack belonging to that attack action.

### BOTTOM

```text
ATTACK 3
RANGE 3
```

`ENHANCEABLE: ATTACK, RANGE`

---

## Vile Pestilence

**File:** `gh-vile-pestilence.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
ATTACK 1
RANGE 2
AOE pattern below

POISON all allies and enemies
in the targeted area.

targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2)}
```

The Poison application affects figures in the area independently of whether an enemy was selected for an attack.

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Winged Congregation

**File:** `gh-winged-congregation.png`  
**Level:** 1  
**Initiative:** 16

### TOP

```text
Gain Flying.
INFUSE AIR
XP +2
[PERSISTENT, LOSS]
```

### BOTTOM

```text
MOVE 2

HEAL 1
Affect all adjacent allies
```

`ENHANCEABLE: MOVE, HEAL`

The Heal is performed on every adjacent ally and does not include SELF.

---

## Wretched Swarm

**File:** `gh-wretched-swarm.png`  
**Level:** 1  
**Initiative:** 84

### TOP

```text
ATTACK 1
RANGE 3
STUN
AOE pattern below
XP +2
[LOSS]

targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2)}
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Blistering Vortex

**File:** `gh-blistering-vortex.png`  
**Level:** X  
**Initiative:** 21

### TOP

```text
ATTACK 3
RANGE 3
AOE pattern below
INFUSE AIR
XP +2
[LOSS]

targets = {(1,0), (0,1), (1,1)}
```

`ENHANCEABLE: ATTACK, one AOE-hex slot ([0 ,0])`

### BOTTOM

```text
MOVE 2

SHIELD 1
Self
[ROUND]
```

`ENHANCEABLE: MOVE, SHIELD`

---

## Epidemic

**File:** `gh-epidemic.png`  
**Level:** X  
**Initiative:** 43

### TOP

```text
ATTACK 3
RANGE 2
TARGET 2
XP +1
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
After each of your Move actions,
POISON all adjacent allies and enemies.
XP +1
[PERSISTENT, LOSS]
```

The trigger occurs after each separate Move action.

---

## Virulent Strain

**File:** `gh-virulent-strain.png`  
**Level:** X  
**Initiative:** 50

### TOP

```text
Remove POISON from any one
enemy to cause it to suffer 3 damage.
XP +1
```

No range is printed; the chosen poisoned enemy may be anywhere.

### BOTTOM

```text
POISON
Target all enemies adjacent
to all enemies with POISON
```

Each affected enemy is poisoned only once even if it is adjacent to multiple poisoned enemies.

---

# Level 2 cards

## Rot Maggots

**File:** `gh-rot-maggots.png`  
**Level:** 2  
**Initiative:** 69

### TOP

```text
ATTACK 4
RANGE 2
POISON

POISON all allies adjacent to the target.
XP +1
```

`ENHANCEABLE: ATTACK`

The additional Poison effect targets allies adjacent to the attacked enemy; it does not include SELF.

### BOTTOM

```text
HEAL 2
RANGE 3
TARGET 2
```

`ENHANCEABLE: RANGE, TARGET`

---

## Under the Skin

**File:** `gh-under-the-skin.png`  
**Level:** 2  
**Initiative:** 59

### TOP

```text
ATTACK 3
RANGE 3
PIERCE 2
XP +1
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

# Level 3 cards

## Fetid Flurry

**File:** `gh-fetid-flurry.png`  
**Level:** 3  
**Initiative:** 26

### TOP

```text
ATTACK 4
RANGE 3
CURSE
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2
INFUSE AIR
```

`ENHANCEABLE: MOVE`

---

## Succumb to the Gift

**File:** `gh-succumb-to-the-gift.png`  
**Level:** 3  
**Initiative:** 70

### TOP

```text
Kill one normal enemy with
POISON within RANGE 3.
XP +2
[LOSS]
```

Elite and boss enemies are not valid targets.

### BOTTOM

```text
BLESS
Affect all allies with POISON
```

No range is printed; every poisoned ally in the scenario is affected.

---

# Level 4 cards

## Nightmarish Affliction

**File:** `gh-nightmarish-affliction.png`  
**Level:** 4  
**Initiative:** 43

### TOP

```text
ATTACK 1
RANGE 3
TARGET 2
POISON
CURSE
INFUSE DARK
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
IMMOBILIZE
RANGE 3
TARGET 2
```

`ENHANCEABLE: TARGET`

This is a non-attack ability.

---

## Storm of Wings

**File:** `gh-storm-of-wings.png`  
**Level:** 4  
**Initiative:** 91

### TOP

```text
ATTACK 4
RANGE 4
CONSUME AIR: Additionally, target all enemies on
the path to the primary target
XP +2
[LOSS]
```

All additional targets are attacked with the same `ATTACK 4`.  
The path must be a range-valid path to the primary target, but it does not need to be a straight line.

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

# Level 5 cards

## Accelerated End

**File:** `gh-accelerated-end.png`  
**Level:** 5  
**Initiative:** 62

### TOP

```text
All enemies with POISON
suffer 1 damage.
CONSUME DARK: 2 damage instead, XP +1
```

This is direct damage, not an attack.  
The Dark consumption replaces the value `1` with `2`; it does not add a second damage instance.

### BOTTOM

```text
POISON
RANGE 5
TARGET 3
```

`ENHANCEABLE: TARGET`

This is a non-attack ability.

---

## Willing Sacrifice

**File:** `gh-willing-sacrifice.png`  
**Level:** 5  
**Initiative:** 84

### TOP

```text
Once during each of your Move actions,
you may suffer 1 damage to deal 3 damage
to any one enemy with POISON.
XP +2
[PERSISTENT, LOSS]
```

No range is printed for the enemy selected by this effect.

### BOTTOM

```text
All allies with POISON may Recover
up to two of their discarded cards.
CONSUME AIR: Up to three discarded
cards instead, XP +1
XP +1
[LOSS]
```

The Air consumption changes the maximum for every affected ally.  
With Air consumed, the action awards a total of 2 XP.

---

# Level 6 cards

## Black Tides

**File:** `gh-black-tides.png`  
**Level:** 6  
**Initiative:** 37

### TOP

```text
ATTACK 4
RANGE 3
INFUSE AIR
```

`ENHANCEABLE: ATTACK (2 marks), RANGE`

### BOTTOM

```text
MOVE 4
INFUSE DARK
```

`ENHANCEABLE: MOVE`

---

## Stinging Cloud

**File:** `gh-stinging-cloud.png`  
**Level:** 6  
**Initiative:** 66

### TOP

```text
ATTACK 2
RANGE 3
AOE pattern below
CONSUME DARK: MUDDLE, XP +1

targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2)}
```

### BOTTOM

```text
MOVE 3

SHIELD 1
Self
[ROUND]
```

`ENHANCEABLE: MOVE, SHIELD`

---

# Level 7 cards

## Airborne Toxin

**File:** `gh-airborne-toxin.png`  
**Level:** 7  
**Initiative:** 57

### TOP

```text
CURSE and MUDDLE
Target all enemies within RANGE 1
CONSUME AIR: +1 RANGE, XP +1
```

Without Air, this affects all adjacent enemies.  
With Air, this affects every enemy within Range 2.

### BOTTOM

```text
POISON
Target all enemies
INFUSE AIR
XP +1
[LOSS]
```

No range is printed; every enemy in the scenario is affected.

---

## Baneful Hex

**File:** `gh-baneful-hex.png`  
**Level:** 7  
**Initiative:** 33

### TOP

```text
If a CURSE causes an enemy to
deal no damage during its attack,
this enemy suffers 3 damage.
XP +2
[PERSISTENT, LOSS]
```

If advantage or disadvantage reveals two Curse cards, only the activated Curse triggers this effect once for that attack.

### BOTTOM

```text
MOVE 3

CURSE
Target all adjacent enemies
```

`ENHANCEABLE: CURSE`

The Curse application is a separate non-attack ability after the movement.

---

# Level 8 cards

## Grim Bargain

**File:** `gh-grim-bargain.png`  
**Level:** 8  
**Initiative:** 46

### TOP

```text
During your next six Attack actions,
CURSE one ally within RANGE 2 to
gain two ADD TARGET effects.
TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Both Add Target effects belong to the same attack action.  
The ally is cursed as the cost of receiving those effects.

### BOTTOM

```text
MUDDLE
Self

Double the value of your next
Attack action this round.
INFUSE DARK
XP +1
[ROUND, LOSS]
```

The doubling applies to the complete next attack action, including every separate attack produced by that action.

---

## Spreading Scourge

**File:** `gh-spreading-scourge.png`  
**Level:** 8  
**Initiative:** 71

### TOP

```text
ATTACK 1
RANGE 3
MUDDLE
AOE pattern below

targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2), (0, 0), (1, -1), (2, -1), (3, -1), (3, 0)}
```

`ENHANCEABLE: two AOE-hex slots ([2, -2], [3, -2])`

### BOTTOM

```text
HEAL 2
RANGE 3
TARGET 3
```

`ENHANCEABLE: RANGE, TARGET`

---

# Level 9 cards

## Convert the Flock

**File:** `gh-convert-the-flock.png`  
**Level:** 9  
**Initiative:** 29

### TOP

```text
ATTACK 4
RANGE 3
TARGET 3
CURSE
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE, TARGET`

### BOTTOM

```text
All allies with POISON suffer 2 damage.

For each two allies damaged,
add +1 ATTACK to all your
attacks this round and gain 1 experience.
[ROUND]
```

The total attack bonus and XP are both `floor(N / 2)`.

---

## Mass Extinction

**File:** `gh-mass-extinction.png`  
**Level:** 9  
**Initiative:** 94

### TOP

```text
CURSE and WOUND
Target all allies and enemies
INFUSE AIR
INFUSE DARK
```

No range is printed; every ally and enemy in the scenario is affected.  
SELF is not an ally and therefore is not affected.

### BOTTOM

```text
All enemies with POISON
suffer 2 damage.
CONSUME AIR: Suffer 1 additional damage, XP +1
CONSUME DARK: Suffer 1 additional damage, XP +1
```

The two element consumptions are independent and may stack.  
Consuming both makes every poisoned enemy suffer 4 total damage and awards 2 XP.

---

# File checklist

```text
gh-ph-back.png
gh-accelerated-end.png
gh-airborne-toxin.png
gh-baneful-hex.png
gh-biting-gnats.png
gh-black-tides.png
gh-blistering-vortex.png
gh-convert-the-flock.png
gh-creeping-curse.png
gh-epidemic.png
gh-fetid-flurry.png
gh-foul-wind.png
gh-gathering-doom.png
gh-grasping-vermin.png
gh-grim-bargain.png
gh-mass-extinction.png
gh-nightmarish-affliction.png
gh-paralyzing-bite.png
gh-rot-maggots.png
gh-scattered-terror.png
gh-spread-the-plague.png
gh-spreading-scourge.png
gh-stinging-cloud.png
gh-storm-of-wings.png
gh-succumb-to-the-gift.png
gh-under-the-skin.png
gh-vile-pestilence.png
gh-virulent-strain.png
gh-willing-sacrifice.png
gh-winged-congregation.png
gh-wretched-swarm.png
```

---

# Sources used

- Visual comparison with the three supplied Plagueherald card screenshots (022236, 022239, and 022242), covering all 30 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, tracks, and explanatory notes outside the action blocks.
