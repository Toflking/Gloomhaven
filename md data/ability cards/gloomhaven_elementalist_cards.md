# Gloomhaven – Elementalist (EL) Ability Cards

**Source folder:** [Worldhaven – `images/character-ability-cards/gloomhaven/el/`](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/el)  
**Scope:** `images/character-ability-cards/gloomhaven/el/`  
**Files covered:** 30 PNG files = 29 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, summon statistics, elements, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Kill, conditional, and other special effects use the full printed wording. `AOE pattern below` refers to the separate preserved area diagram.

## Elements

```text
INFUSE FIRE
INFUSE ICE
INFUSE AIR
INFUSE EARTH
INFUSE LIGHT
INFUSE DARK
INFUSE ANY_ELEMENT

CONSUME FIRE => effect
CONSUME ANY_ELEMENT => effect
CONSUME FIRE + EARTH => effect
CONSUME ANY_ELEMENT + ANY_ELEMENT => effect
```

`ANY_ELEMENT` means one chosen element from `FIRE`, `ICE`, `AIR`, `EARTH`, `LIGHT`, or `DARK`.

A consume line is conditional: the listed effect is obtained only when every listed element is consumed.

- Two element symbols on one consume line are a combined cost.
- The same active element cannot pay two consumptions.
- Therefore, `CONSUME ANY_ELEMENT + ANY_ELEMENT` requires two distinct active elements.
- Separate consume lines are independent and may be combined when enough different active elements are available.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- For a summon, `[PERSISTENT, LOSS]` means the summon remains active until removed.

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: one AOE hex
```

Where the same statistic is enhanceable in both halves, the half is identified explicitly.

## Summons

```text
SUMMON name [PERSISTENT, LOSS]
HEALTH n
MOVE n
ATTACK n
RANGE n
```

A `—` statistic means the summon does not possess that statistic or corresponding normal ability.

## Hex directions and AOE diagrams

```text
S = acting figure / origin hex
T = attacked hex
```

A diagram may be rotated as allowed by the normal area-effect rules; its relative shape remains unchanged.

Compact three-hex triangle used by several ranged attacks:

```text
      T
    T   T
```

---

# Card back

## `gh-el-back.png`

Elementalist / Triangles ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Formless Power

**File:** `gh-formless-power.png`  
**Level:** 1  
**Initiative:** 45

### TOP

```text
ATTACK 2
RANGE 2
CONSUME ANY_ELEMENT: +1 ATTACK, +1 RANGE, XP +1
CONSUME ANY_ELEMENT: +1 ATTACK, +1 RANGE
```

The two consume lines are independent. Using both requires two different active elements and produces `ATTACK 4, RANGE 4`.

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
Once during each of your Attack actions,
you may CONSUME ANY_ELEMENT to add +1 ATTACK
to the entire Attack action.
XP +2
[PERSISTENT, LOSS]
```

The once-per-action limit applies to the complete Attack action, not separately to each target or each attack inside it.

---

## Frigid Torrent

**File:** `gh-frigid-torrent.png`  
**Level:** 1  
**Initiative:** 35

### TOP

```text
ATTACK 3
CONSUME EARTH: TARGET 2, PIERCE 2, XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 3
RANGE 3
PULL 2
AOE pattern below
INFUSE ICE
INFUSE AIR
XP +1
[LOSS]
```

Exact relative AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

`ENHANCEABLE: ATTACK, PULL, one AOE hex ([0, -1])`

---

## Ice Spikes

**File:** `gh-ice-spikes.png`  
**Level:** 1  
**Initiative:** 40

### TOP

```text
ATTACK 1
RANGE 3
AOE pattern below
CONSUME FIRE: +1 ATTACK, WOUND, XP +1
```

Exact relative AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

`ENHANCEABLE: ATTACK, one AOE hex ([0, -1])`

### BOTTOM

```text
ATTACK 5
RANGE 3
IMMOBILIZE
INFUSE ICE
INFUSE EARTH
XP +1
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE`

---

## Infernal Vortex

**File:** `gh-infernal-vortex.png`  
**Level:** 1  
**Initiative:** 17

### TOP

```text
ATTACK 4
Target all enemies at exactly RANGE 2
INFUSE FIRE
INFUSE AIR
XP +1
[LOSS]
```

Enemies closer than or farther than exactly 2 hexes are not valid targets.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2
INFUSE ICE
```

`ENHANCEABLE: MOVE`

---

## Lava Eruption

**File:** `gh-lava-eruption.png`  
**Level:** 1  
**Initiative:** 19

### TOP

```text
ATTACK 1
Target all enemies within RANGE 4
INFUSE FIRE
INFUSE EARTH
XP +1
[LOSS]
```

A separate attack is resolved against every valid enemy within range.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2
INFUSE AIR
```

`ENHANCEABLE: MOVE`

---

## Pure Augmentation

**File:** `gh-pure-augmentation.png`  
**Level:** 1  
**Initiative:** 48

### TOP

```text
ATTACK 1
RANGE 3
CONSUME EARTH: +2 ATTACK, XP +1
CONSUME ICE: +2 ATTACK
```

The two modifiers are independent and may stack, producing `ATTACK 5`.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3
CONSUME AIR: +2 MOVE, XP +1
CONSUME FIRE: +2 MOVE
```

The two modifiers are independent and may stack, producing `MOVE 7`.

`ENHANCEABLE: MOVE`

---

## Raw Enhancement

**File:** `gh-raw-enhancement.png`  
**Level:** 1  
**Initiative:** 48

### TOP

```text
ATTACK 1
RANGE 3
CONSUME FIRE: +2 ATTACK, XP +1
CONSUME AIR: +2 ATTACK
```

The two modifiers are independent and may stack, producing `ATTACK 5`.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3
CONSUME ICE: +2 MOVE, XP +1
CONSUME EARTH: +2 MOVE
```

The two modifiers are independent and may stack, producing `MOVE 7`.

`ENHANCEABLE: MOVE`

---

## Shaping the Ether

**File:** `gh-shaping-the-ether.png`  
**Level:** 1  
**Initiative:** 54

### TOP

```text
ATTACK 1
RANGE 3
XP +1
CONSUME FIRE: +2 ATTACK
CONSUME AIR: PUSH 3
CONSUME ICE: STUN
CONSUME EARTH: TARGET 2
```

All four consume lines are independent and may be combined.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
At the end of each of your turns,
you may generate ANY_ELEMENT.

Deduct -1 ATTACK from
all your attacks.
[PERSISTENT, LOSS]
```

---

## Stoking Hail

**File:** `gh-stoking-hail.png`  
**Level:** 1  
**Initiative:** 28

### TOP

```text
ATTACK 2
RANGE 3
CONSUME ICE: STUN, XP +1
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 2
INFUSE FIRE
```

`ENHANCEABLE: MOVE`

---

## Tremulant Cyclone

**File:** `gh-tremulant-cyclone.png`  
**Level:** 1  
**Initiative:** 26

### TOP

```text
ATTACK 1
RANGE 3
AOE pattern below
CONSUME AIR: +1 ATTACK, PUSH 1, XP +1
```

Exact relative AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
MOVE 2
INFUSE EARTH
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Brilliant Flash

**File:** `gh-brilliant-flash.png`  
**Level:** X  
**Initiative:** 67

### TOP

```text
HEAL 3
RANGE 3
INFUSE LIGHT
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
LOOT 1
```

---

## Encompassing Shadow

**File:** `gh-encompassing-shadow.png`  
**Level:** X  
**Initiative:** 23

### TOP

```text
INVISIBLE
Affect one ally within RANGE 3
INFUSE DARK
```

`ALLY` means this action cannot target `SELF`.

### BOTTOM

```text
MOVE 2

All attacks targeting you gain
Disadvantage this round.
[ROUND]
```

`ENHANCEABLE: MOVE`

---

## Malleable Evocation

**File:** `gh-malleable-evocation.png`  
**Level:** X  
**Initiative:** 50

### TOP

```text
ATTACK 0
RANGE 2
XP +1
CONSUME FIRE: +1 ATTACK, WOUND
CONSUME AIR: +1 ATTACK, TARGET 2
CONSUME ICE: +1 ATTACK, IMMOBILIZE
CONSUME EARTH: +1 ATTACK, POISON
```

All four consume lines are independent. Consuming all four elements produces `ATTACK 4, RANGE 2, TARGET 2` with `WOUND`, `IMMOBILIZE`, and `POISON`.

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 2
CONSUME FIRE: +3 MOVE
CONSUME AIR: Jump
CONSUME ICE: HEAL 2
Self
CONSUME EARTH: SHIELD 1
Self
[ROUND for SHIELD]
```

All four consume lines are independent.

`ENHANCEABLE: MOVE`

---

# Level 2 cards

## Boiling Arc

**File:** `gh-boiling-arc.png`  
**Level:** 2  
**Initiative:** 47

### TOP

```text
ATTACK 2
AOE pattern below
INFUSE FIRE
```

Exact relative AOE:

```text
origin       =  [0,2]
targets      = [(2, 0), (2, 1), (1,1), (1,2), (2,2)]
```

The attack covers five hexes extending outward from `S`. The pattern may be rotated around `S`.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
CONSUME FIRE: RETALIATE 2
RANGE 2
[ROUND for RETALIATE]
```

`ENHANCEABLE: MOVE`

---

## Crystallizing Blast

**File:** `gh-crystallizing-blast.png`  
**Level:** 2  
**Initiative:** 67

### TOP

```text
ATTACK 1
RANGE 3
AOE pattern below
INFUSE ICE
```

Exact relative AOE:

```text
targets = {(1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2)}
```

The pattern contains seven target hexes.

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 4
CONSUME ICE: SHIELD 2
Self
[ROUND for SHIELD]
```

`ENHANCEABLE: MOVE`

---

# Level 3 cards

## Burial

**File:** `gh-burial.png`  
**Level:** 3  
**Initiative:** 65

### TOP

```text
ATTACK 4
RANGE 4
AOE pattern below
CONSUME EARTH: +1 ATTACK, IMMOBILIZE, XP +1
CONSUME DARK: +1 ATTACK, CURSE, XP +1
[LOSS]
```

Exact relative AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

The two elemental modifiers are independent and may stack.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 3
RANGE 4
INFUSE DARK
```

`ENHANCEABLE: ATTACK`

---

## Chain Lightning

**File:** `gh-chain-lightning.png`  
**Level:** 3  
**Initiative:** 41

### TOP

```text
ATTACK X = 4
RANGE 3
ATTACK X-1 on a new enemy within RANGE 2 of the
first target, then ATTACK X-2 on a third enemy within
RANGE 2 of the second target.
CONSUME LIGHT: +1 ATTACK, PIERCE 2, XP +1
CONSUME AIR: ATTACK X-3 on a fourth enemy within
RANGE 2 of the third target, XP +1
[LOSS]
```

Every target in the chain must be a different enemy. Normally the attack values are `4`, `3`, `2`, and—when `AIR` is consumed—`1`. Consuming `LIGHT` changes `X` to 5, so the corresponding values become `5`, `4`, `3`, and `2`.

### BOTTOM

```text
HEAL 3
RANGE 4
INFUSE LIGHT
```

---

# Level 4 cards

## Gravel Vortex

**File:** `gh-gravel-vortex.png`  
**Level:** 4  
**Initiative:** 62

### TOP

```text
ATTACK 2
Target all adjacent enemies
INFUSE AIR
INFUSE EARTH
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
LOOT 1
CONSUME ANY_ELEMENT + ANY_ELEMENT: LOOT 2
```

The two consumed elements must be different. Resolving the printed `LOOT 1` followed by the conditional `LOOT 2` is mechanically equivalent to a single `LOOT 2`; already-looted hexes are not looted twice.

---

## Primal Duality

**File:** `gh-primal-duality.png`  
**Level:** 4  
**Initiative:** 15

### TOP

```text
ATTACK 5
RANGE 4
TARGET 2
INFUSE ICE
INFUSE FIRE
XP +1
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks), TARGET`

### BOTTOM

```text
MOVE 2
INFUSE LIGHT
INFUSE DARK
```

`ENHANCEABLE: MOVE`

---

# Level 5 cards

## Obsidian Shards

**File:** `gh-obsidian-shards.png`  
**Level:** 5  
**Initiative:** 36

### TOP

```text
ATTACK 2
RANGE 3
TARGET 2
CONSUME FIRE + EARTH: +3 ATTACK, XP +1
```

The attack bonus applies to every attack in this multi-target Attack action.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
SHIELD 1
Self
INFUSE ICE
INFUSE DARK
[ROUND]
```

`ENHANCEABLE: SHIELD`

---

## Winter's Edge

**File:** `gh-winters-edge.png`  
**Level:** 5  
**Initiative:** 43

### TOP

```text
ATTACK 5
RANGE 2
CONSUME ICE + AIR: +2 RANGE, PIERCE 4, XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 2
RANGE 2
INFUSE FIRE
INFUSE LIGHT
```

`ENHANCEABLE: HEAL, RANGE`

---

# Level 6 cards

## Eye of the Hurricane

**File:** `gh-eye-of-the-hurricane.png`  
**Level:** 6  
**Initiative:** 29

### TOP

```text
HEAL 3
RANGE 4

ATTACK 2
Target all enemies within
RANGE 2 of the healed figure
INFUSE ICE
INFUSE AIR
XP +1
[LOSS]
```

The second ability uses the healed figure as the center for measuring the enemy target area. `SELF` still performs the attacks.

`ENHANCEABLE: HEAL, ATTACK`

### BOTTOM

```text
MOVE 3
CONSUME FIRE: +3 MOVE
CONSUME AIR: Jump, XP +1
```

The two modifiers are independent and may be combined.

---

## Simulacrum

**File:** `gh-simulacrum.png`  
**Level:** 6  
**Initiative:** 94

### TOP

```text
ATTACK 2
RANGE 3
CONSUME ANY_ELEMENT + ANY_ELEMENT: +3 ATTACK, XP +1
CONSUME ANY_ELEMENT + ANY_ELEMENT: TARGET 2, XP +1
CONSUME ANY_ELEMENT + ANY_ELEMENT: IMMOBILIZE, MUDDLE, XP +1
```

Each line requires two distinct active elements. Activating all three lines consumes all six elements.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
Summon Doppelganger
HEALTH 10
MOVE 2
ATTACK —
RANGE —
You may perform
Attack actions as
if you occupy the
summon’s hex.
INFUSE EARTH
XP +1
[PERSISTENT, LOSS]
```

The action block retains the printed “Attack actions” wording. The original document notes official errata changing this to **attack abilities**. For each attack ability, choose either `SELF`'s actual hex or the Doppelganger's hex as its single origin; the ability cannot originate from both. The Doppelganger has no normal attack of its own.

---

# Level 7 cards

## Pragmatic Reinforcement

**File:** `gh-pragmatic-reinforcement.png`  
**Level:** 7  
**Initiative:** 56

### TOP

```text
HEAL 3
RANGE 3
XP +1
CONSUME ICE: SHIELD 1 [ROUND]
CONSUME EARTH: +2 HEAL
CONSUME LIGHT: The healed figure may Recover
one of their discarded cards.
```

All three consume lines are independent.

`ENHANCEABLE: HEAL`

### BOTTOM

```text
ATTACK 3
RANGE 3
XP +1
CONSUME FIRE: POISON, WOUND
CONSUME AIR: +3 RANGE
CONSUME DARK: +2 ATTACK
```

All three consume lines are independent.

`ENHANCEABLE: ATTACK`

---

## Vengeance

**File:** `gh-vengeance.png`  
**Level:** 7  
**Initiative:** 33

### TOP

```text
ATTACK 2
RANGE 3
AOE pattern below
CONSUME DARK + ANY_ELEMENT: Kill all normal
targets instead, XP +1
```

Exact relative AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

The `ANY_ELEMENT` payment must be different from `DARK`. Normal targets are killed instead of resolving their attacks; non-normal targets remain subject to the printed attack.

`ENHANCEABLE: one AOE hex ([0, -1])`

### BOTTOM

```text
MOVE 3

RETALIATE 1
Affect one adjacent ally
CONSUME LIGHT + ANY_ELEMENT: +2 RETALIATE, XP +1
[ROUND]
```

The `ANY_ELEMENT` payment must be different from `LIGHT`.

`ENHANCEABLE: MOVE, RETALIATE`

---

# Level 8 cards

## Elemental Aegis

**File:** `gh-elemental-aegis.png`  
**Level:** 8  
**Initiative:** 14

### TOP

```text
SHIELD 2
Self
[ROUND]
CONSUME ANY_ELEMENT: +1 SHIELD, XP +1
CONSUME ANY_ELEMENT: +1 SHIELD
CONSUME ANY_ELEMENT: +1 SHIELD, XP +1
CONSUME ANY_ELEMENT: +1 SHIELD
```

Each slot requires a different active element. Consuming four elements produces `SHIELD 6` and awards 2 XP.

`ENHANCEABLE: SHIELD`

### BOTTOM

```text
HEAL 5
Self
INFUSE LIGHT
```

`ENHANCEABLE: HEAL (2 marks)`

---

## Volatile Consumption

**File:** `gh-volatile-consumption.png`  
**Level:** 8  
**Initiative:** 64

### TOP

```text
Once per action, if you consume an
element during the action, you may
cause one enemy within RANGE 2 to
suffer 1 damage or you may perform a
“HEAL 1, RANGE 2” action.
XP +2
[PERSISTENT, LOSS]
```

The trigger may occur once for each separate action, not merely once per turn.

### BOTTOM

```text
MOVE 3
Jump
CONSUME FIRE + ANY_ELEMENT: +2 MOVE, WOUND
all enemies moved through, XP +1
CONSUME ICE + ANY_ELEMENT: +2 MOVE, IMMOBILIZE
all enemies moved through, XP +1
```

For each line, `ANY_ELEMENT` must differ from the fixed element. Using both modifiers requires four distinct element consumptions.

`ENHANCEABLE: MOVE`

---

# Level 9 cards

## Eternal Equilibrium

**File:** `gh-eternal-equilibrium.png`  
**Level:** 9  
**Initiative:** 27

### TOP

```text
ATTACK 2
Target all enemies within RANGE 3
CONSUME DARK: +1 ATTACK, CURSE, XP +1
CONSUME LIGHT: +1 ATTACK, MUDDLE, XP +1
```

The two modifiers are independent and may stack. Each valid enemy is attacked separately.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 4
RANGE 4
CONSUME FIRE: RETALIATE 2, XP +1
CONSUME ICE: SHIELD 2, XP +1
[ROUND for RETALIATE and SHIELD]
```

The two consume lines are independent.

`ENHANCEABLE: HEAL`

---

## Ethereal Manifestation

**File:** `gh-ethereal-manifestation.png`  
**Level:** 9  
**Initiative:** 98

### TOP

```text
Summon Mana Sphere
HEALTH 3
MOVE 3
ATTACK 2
INFUSE ANY_ELEMENT on attack
RANGE 3
XP +1
[PERSISTENT, LOSS]
```

The element generated by an attack is chosen independently each time the Mana Sphere attacks.

`ENHANCEABLE: summon MOVE`

### BOTTOM

```text
Perform any of the following abilities:
CONSUME FIRE: ATTACK 3
RANGE 3
CONSUME AIR: MOVE 3
CONSUME ICE: LOOT 1
CONSUME EARTH: HEAL 3
RANGE 3
```

Each listed ability is independently available. Any subset may be performed when its corresponding elements are available, and the chosen abilities may be resolved in any order. Each element can be consumed only once.

---

# File checklist

```text
gh-boiling-arc.png
gh-brilliant-flash.png
gh-burial.png
gh-chain-lightning.png
gh-crystallizing-blast.png
gh-el-back.png
gh-elemental-aegis.png
gh-encompassing-shadow.png
gh-eternal-equilibrium.png
gh-ethereal-manifestation.png
gh-eye-of-the-hurricane.png
gh-formless-power.png
gh-frigid-torrent.png
gh-gravel-vortex.png
gh-ice-spikes.png
gh-infernal-vortex.png
gh-lava-eruption.png
gh-malleable-evocation.png
gh-obsidian-shards.png
gh-pragmatic-reinforcement.png
gh-primal-duality.png
gh-pure-augmentation.png
gh-raw-enhancement.png
gh-shaping-the-ether.png
gh-simulacrum.png
gh-stoking-hail.png
gh-tremulant-cyclone.png
gh-vengeance.png
gh-volatile-consumption.png
gh-winters-edge.png
```

---

# Sources used

- Visual comparison with the three supplied Elementalist card screenshots (020539, 020542, and 020544), covering all 29 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, and explanatory notes outside the action blocks.
