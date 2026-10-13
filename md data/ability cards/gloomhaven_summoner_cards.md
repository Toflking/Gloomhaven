# Gloomhaven – Summoner (SU) Ability Cards

**Scope:** https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/su/  
**Files covered:** 29 PNG files = 28 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, summon statistics, elements, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Recovery, kill, controlled-action, and other special effects use the full printed wording. `AOE pattern below` refers to the separate preserved area diagram.

`ATTACK +n` and `MOVE +n` modify the receiving figure’s base statistic.

## Summons

Summon panels retain their printed name, number of figures, statistics, and traits. `HEALTH` spells out the hit-point icon; `—` preserves a printed dash for a missing base statistic. Element icons beside ATTACK are represented by `INFUSE <element> on attack`. A summon's listed attack conditions apply to its attacks. Multiple identical summons are separate figures with separate health totals.

## Elements

```text
INFUSE FIRE
CONSUME FIRE => effect
```

A consume line is conditional: the listed effect is obtained only when that element is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is lost for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[UNRECOVERABLE]` — once lost, the card cannot be recovered, regardless of how it became lost.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [XP+1] [ ] [XP+1] -> end
```

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: HEAL (2 marks)
ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE
```

## Area diagrams

```text
T = affected hex
```

An area pattern may be rotated without changing its shape.

---

# Card back

## `gh-su-back.png`

Summoner / Circles ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Biting Wind

**File:** `gh-biting-wind.png`  
**Level:** 1  
**Initiative:** 25

### TOP

```text
ATTACK 2
RANGE 3
CONSUME AIR: +1 ATTACK, PUSH 2, XP +1
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
HEAL 3
RANGE 3
```

`ENHANCEABLE: HEAL`

---

## Black Fire

**File:** `gh-black-fire.png`  
**Level:** 1  
**Initiative:** 24

### TOP

```text
ATTACK 1
RANGE 5
TARGET 2
CONSUME FIRE: WOUND, XP +1
```

The consumed effect applies to every attack made as part of this attack ability.

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
MOVE 3

MUDDLE
Target all adjacent enemies
```

`ENHANCEABLE: MOVE`

The Muddle is a separate non-attack ability after the movement.

---

## Bonded Might

**File:** `gh-bonded-might.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
One adjacent summoned ally performs
MOVE +0
ATTACK +0
with you controlling the actions.
XP +1
```

The movement and attack are separate granted abilities in the listed order.

### BOTTOM

```text
LOOT 1
```

---

## Forged Ferocity

**File:** `gh-forged-ferocity.png`  
**Level:** 1  
**Initiative:** 96

### TOP

```text
Summon Iron Beast
HEALTH 5
MOVE 2
ATTACK 1
RANGE —
RETALIATE 1
SHIELD 1
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
SHIELD 1
Affect self and all summoned allies
INFUSE EARTH
[ROUND]
```

`ENHANCEABLE: SHIELD`

Each affected figure receives its own `SHIELD 1` for the round.

---

## Living Night

**File:** `gh-living-night.png`  
**Level:** 1  
**Initiative:** 82

### TOP

```text
Summon 2 Shadow Wolves
HEALTH 3
MOVE 3
ATTACK 2
RANGE —
PIERCE 2
XP +2
[PERSISTENT, LOSS]
```

Two separate Shadow Wolf figures are summoned.

`ENHANCEABLE: SUMMON HEALTH, SUMMON ATTACK`

### BOTTOM

```text
All attacks targeting you
and all summoned allies gain
Disadvantage this round.
INFUSE DARK
[ROUND]
```

---

## Mighty Bond

**File:** `gh-mighty-bond.png`  
**Level:** 1  
**Initiative:** 51

### TOP

```text
One adjacent summoned ally performs
MOVE +0
ATTACK +0
with you controlling the actions.
XP +1
```

The movement and attack are separate granted abilities in the listed order.

### BOTTOM

```text
MOVE 4
Jump
```

`ENHANCEABLE: MOVE`

---

## Unending Dominance

**File:** `gh-unending-dominance.png`  
**Level:** 1  
**Initiative:** 98

### TOP

```text
Summon Lava Golem
HEALTH 7
MOVE 2
ATTACK 3
RANGE —
INFUSE FIRE on attack
RETALIATE 2
RANGE 2 for RETALIATE
WOUND
XP +6
[PERSISTENT, LOSS, UNRECOVERABLE]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE`

### BOTTOM

```text
Recover up to four of your lost cards.
[LOSS, UNRECOVERABLE]
```

The recovered cards are chosen from `SELF`'s lost pile.

---

## Unwavering Hand

**File:** `gh-unwavering-hand.png`  
**Level:** 1  
**Initiative:** 31

### TOP

```text
HEAL 3
RANGE 3
XP +1
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
Force one enemy within
RANGE 4 to perform

MOVE 1

with you controlling the action.
```

This is forced movement performed as a Move ability, not Push or Pull.

---

## Wild Animation

**File:** `gh-wild-animation.png`  
**Level:** 1  
**Initiative:** 92

### TOP

```text
Summon Thorn Shooter
HEALTH 2
MOVE 1
ATTACK 2
RANGE 4
POISON
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON ATTACK, SUMMON RANGE`

### BOTTOM

```text
MOVE 5
INFUSE AIR
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Ethereal Vines

**File:** `gh-ethereal-vines.png`  
**Level:** X  
**Initiative:** 30

### TOP

```text
ATTACK 1
RANGE 3
IMMOBILIZE
AOE pattern below
```

Exact AOE shape:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

The pattern may be rotated.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2

HEAL 1
Affect all adjacent summoned allies
```

`ENHANCEABLE: MOVE, HEAL`

The Heal is a separate ability after the movement. Every adjacent summoned ally is affected.

---

## Leathery Wings

**File:** `gh-leathery-wings.png`  
**Level:** X  
**Initiative:** 90

### TOP

```text
Summon Bat Swarm
HEALTH 4
MOVE 2
ATTACK 2
RANGE —
INFUSE AIR on attack
Flying
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE`

### BOTTOM

```text
STRENGTHEN
Affect all adjacent allies
```

`ENHANCEABLE: STRENGTHEN`

`SELF` is not included.

---

## Volatile Flame

**File:** `gh-volatile-flame.png`  
**Level:** X  
**Initiative:** 94

### TOP

```text
Summon Living Bomb
HEALTH 1
MOVE 3
ATTACK 2
RANGE —
On death:
ATTACK 3
INFUSE FIRE
Target all enemies
within RANGE 2
XP +2
[PERSISTENT, LOSS]
```

The death trigger creates one separate attack against every valid enemy within Range 2 of the Living Bomb.

`ENHANCEABLE: SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
ATTACK 2
RANGE 3
INFUSE FIRE
```

`ENHANCEABLE: ATTACK`

---

# Level 2 cards

## Earthen Steed

**File:** `gh-earthen-steed.png`  
**Level:** 2  
**Initiative:** 13

### TOP

```text
On any of your Move actions, or the Move
actions of any ally you have summoned, you
may CONSUME EARTH to add +2 MOVE and gain 1 experience.
[PERSISTENT, LOSS]
```

The effect may modify a Move ability performed by `SELF` or by an ally specifically summoned by `SELF`.

### BOTTOM

```text
MOVE 5
Jump
```

`ENHANCEABLE: MOVE`

---

## Grasping the Void

**File:** `gh-grasping-the-void.png`  
**Level:** 2  
**Initiative:** 62

### TOP

```text
ATTACK 2
RANGE 3
CURSE
CONSUME DARK: STUN, XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 2
RANGE 3

HEAL 2
Self
```

`ENHANCEABLE: ATTACK, HEAL`

The attack and heal are separate abilities in the listed order.

---

# Level 3 cards

## Oozing Manifestation

**File:** `gh-oozing-manifestation.png`  
**Level:** 3  
**Initiative:** 86

### TOP

```text
Summon Slime Spirit
HEALTH 4
MOVE 3
ATTACK 1
RANGE —
SHIELD 2
MUDDLE
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
ATTACK 3
RANGE 3
IMMOBILIZE
CONSUME DARK: CURSE, XP +1
```

`ENHANCEABLE: ATTACK`

---

## Tear the Fabric

**File:** `gh-tear-the-fabric.png`  
**Level:** 3  
**Initiative:** 41

### TOP

```text
Kill all normal adjacent enemies
whose current hit point value is
equal to or less than 5.
XP +2
[LOSS]
```

Elite enemies and bosses are not valid targets.

### BOTTOM

```text
HEAL 4
Self
CONSUME EARTH: +2 HEAL, XP +1
```

`ENHANCEABLE: HEAL (2 marks)`

Consuming Earth produces `HEAL 6, SELF`.

---

# Level 4 cards

## Divided Mind

**File:** `gh-divided-mind.png`  
**Level:** 4  
**Initiative:** 55

### TOP

```text
Two summoned allies
within RANGE 2 perform
MOVE +0
with you controlling the actions.
XP +1
```

### BOTTOM

```text
Two summoned allies
within RANGE 2 perform
ATTACK +0
with you controlling the actions.
XP +1
```

The action blocks retain the printed “Two summoned allies” wording. The original document notes official errata allowing **up to two** summoned allies on either half; this is an explanatory note rather than printed card text.

---

## Living Mountain

**File:** `gh-living-mountain.png`  
**Level:** 4  
**Initiative:** 88

### TOP

```text
Summon Rock Colossus
HEALTH 7
MOVE 1
ATTACK 3
RANGE —
INFUSE EARTH on attack
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE`

### BOTTOM

```text
MOVE 2

LOOT 1
```

`ENHANCEABLE: MOVE`

The movement and loot are separate abilities in the listed order.

---

# Level 5 cards

## Conjured Aid

**File:** `gh-conjured-aid.png`  
**Level:** 5  
**Initiative:** 81

### TOP

```text
Summon Healing Sprite
HEALTH 2
MOVE 2
ATTACK 1
RANGE 3
Performs
HEAL 2
RANGE 2
at end of turn
XP +3
[PERSISTENT, LOSS]
```

The Healing Sprite resolves its Heal using the normal summon/monster ally-selection rules.

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON RANGE`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Strength in Numbers

**File:** `gh-strength-in-numbers.png`  
**Level:** 5  
**Initiative:** 45

### TOP

```text
One adjacent summoned ally performs
MOVE +1
ATTACK +1
with you controlling the actions.
XP +1
```

The movement and attack are separate granted abilities in the listed order.

### BOTTOM

```text
ATTACK X
RANGE X
where X is the number
of all summoned allies.
```

`X` counts all allies summoned by all characters, not only summons belonging to `SELF`.

---

# Level 6 cards

## Endless Spikes

**File:** `gh-endless-spikes.png`  
**Level:** 6  
**Initiative:** 97

### TOP

```text
Summon 3 Nail Spheres
HEALTH 4
MOVE 2
ATTACK —
RANGE —
RETALIATE 2
SHIELD 1
XP +3
[PERSISTENT, LOSS]
```

Three separate Nail Sphere figures are summoned. They have no attack action and no base Attack value.

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE`

### BOTTOM

```text
RETALIATE 2
Affect all summoned allies
[ROUND]
```

Every allied summoned figure receives its own `RETALIATE 2` for the round. `SELF` is not affected.

---

## Inexorable Momentum

**File:** `gh-inexorable-momentum.png`  
**Level:** 6  
**Initiative:** 32

### TOP

```text
ATTACK 3
RANGE 3
PIERCE 3
CONSUME AIR: +2 ATTACK
```

`ENHANCEABLE: ATTACK, PIERCE`

### BOTTOM

```text
MOVE 3

One adjacent summoned ally performs
MOVE 3
```

`ENHANCEABLE: both MOVE abilities`

The second movement is performed by the selected summoned ally after `SELF` finishes the first movement.

---

# Level 7 cards

## Negative Energy

**File:** `gh-negative-energy.png`  
**Level:** 7  
**Initiative:** 95

### TOP

```text
Summon Void Eater
HEALTH 5
MOVE 3
ATTACK 2
RANGE 2
CURSE
XP +3
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON ATTACK, SUMMON RANGE`

### BOTTOM

```text
CURSE
Target all enemies adjacent
to any summoned ally
INFUSE DARK
```

An enemy adjacent to multiple summoned allies is still affected only once.

---

## Staff of Visions

**File:** `gh-staff-of-visions.png`  
**Level:** 7  
**Initiative:** 27

### TOP

```text
ATTACK 5
MUDDLE
CONSUME FIRE: +1 ATTACK, WOUND, XP +1
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 3

STRENGTHEN
Affect all adjacent allies
```

`ENHANCEABLE: MOVE, STRENGTHEN`

`SELF` is not included in the Strengthen ability.

---

# Level 8 cards

## Intervening Apparitions

**File:** `gh-intervening-apparitions.png`  
**Level:** 8  
**Initiative:** 68

### TOP

```text
The next three times summoned allies
suffer damage, they suffer no damage instead.
TRACK: [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

The three uses form one shared track across all summoned allies. A prevented source consumes exactly one use.

### BOTTOM

```text
Remove all CURSE cards from your
attack modifier deck, and then shuffle
the discard pile back into the deck.
INFUSE LIGHT
```

This reshuffle is performed even if no Curse card was removed.

---

## Otherworldly Rage

**File:** `gh-otherworldly-rage.png`  
**Level:** 8  
**Initiative:** 35

### TOP

```text
One summoned ally within
RANGE 3 performs
ATTACK +2
WOUND
POISON
with you controlling the action.
XP +1
```

Wound and Poison are applied by the granted attack.

### BOTTOM

```text
Teleport to the hex occupied by any
summoned ally and simultaneously
Teleport that summoned ally to
the hex occupied by you.

MOVE 3
```

`ENHANCEABLE: MOVE`

The position exchange is simultaneous and is not ordinary movement. The Move is a separate following ability.

---

# Level 9 cards

## Horned Majesty

**File:** `gh-horned-majesty.png`  
**Level:** 9  
**Initiative:** 80

### TOP

```text
Summon Black Unicorn
HEALTH 6
MOVE 4
ATTACK 4
RANGE —
PIERCE 3
XP +3
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HEALTH, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
All summoned allies
within RANGE 3 perform
ATTACK +0
with you controlling the actions.
XP +1
```

Each affected summoned ally performs a separate attack ability.

---

## Interplanar Mastery

**File:** `gh-interplanar-mastery.png`  
**Level:** 9  
**Initiative:** 22

### TOP

```text
Recover up to two of your lost cards.
[LOSS, UNRECOVERABLE]
```

The recovered cards are chosen from `SELF`'s lost pile.

### BOTTOM

```text
MOVE 6
Jump
```

`ENHANCEABLE: MOVE`

---

# File checklist

```text
gh-su-back.png
gh-biting-wind.png
gh-black-fire.png
gh-bonded-might.png
gh-forged-ferocity.png
gh-living-night.png
gh-mighty-bond.png
gh-unending-dominance.png
gh-unwavering-hand.png
gh-wild-animation.png
gh-ethereal-vines.png
gh-leathery-wings.png
gh-volatile-flame.png
gh-earthen-steed.png
gh-grasping-the-void.png
gh-oozing-manifestation.png
gh-tear-the-fabric.png
gh-divided-mind.png
gh-living-mountain.png
gh-conjured-aid.png
gh-strength-in-numbers.png
gh-endless-spikes.png
gh-inexorable-momentum.png
gh-negative-energy.png
gh-staff-of-visions.png
gh-intervening-apparitions.png
gh-otherworldly-rage.png
gh-horned-majesty.png
gh-interplanar-mastery.png
```

---

# Sources used

- Visual comparison with the three supplied Summoner card screenshots (015301, 015305, and 015308), covering all 28 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, and explanatory notes outside the action blocks.
