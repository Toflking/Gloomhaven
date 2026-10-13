# Gloomhaven – Doomstalker (DS) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/ds/`  
**Source folder:** <https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/ds>  
**Files covered:** 32 PNG files = 31 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, tracks, duration, loss, summon statistics, and enhancement marks use the notation below. Explanatory notes outside the blocks are not printed card text.

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
- `TARGET every enemy within RANGE n` creates one separate attack against every valid enemy in that radius.

## Common keywords

```text
ATTACK n
MOVE n
MOVE +n
HEAL n [SELF | RANGE n]
RETALIATE n [RANGE n, SELF]
LOOT n
PUSH n
PIERCE n
JUMP
FLYING
INVISIBLE
WOUND
STUN
POISON
CURSE
IMMOBILIZE
ADVANTAGE
EXECUTE
TELEPORT
SUMMON
```

`MOVE +0` and `ATTACK +0` on a granted summon action use that summon's own Move and Attack values without a numerical modifier.

## Dooms

```text
DOOM one enemy [PERSISTENT]
```

A `DOOM` is a special persistent negative effect placed on an enemy with the Doomstalker's character token. It is not a condition.

Unless a card says otherwise:

- only one enemy may have the Doomstalker's active Doom token;
- the target becomes `DOOMED`;
- the Doom is discarded when its target dies or another Doom is played;
- bosses may be Doomed, but obstacles with hit points may not be Doomed.

`Inescapable Fate` creates explicit exceptions to the normal one-Doom limit.

## Summons and traps

A summon block lists the summon name followed by its printed statistics and permanent traits.

```text
SUMMON Example [PERSISTENT, LOSS]
├─ HP 5
├─ MOVE 2
└─ ATTACK 3
```

A trap block states the legal placement hex, trap damage, and any additional effects. Splash effects centered on a trap affect figures adjacent to the trap hex, not the figure standing on the trap hex unless separately stated.

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
ENHANCEABLE: ATTACK (2 marks), RANGE
ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK
```

## Full wording

Conditions and definitions of X are written out on every card. Experience icons inside sentences are written as “n experience”; standalone experience icons use `XP +n`.

---

# Card back

## `gh-ds-back.png`

Doomstalker / Angry Face ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## A Moment's Peace

**File:** `gh-a-moments-peace.png`  
**Level:** 1  
**Initiative:** 88

### TOP

```text
LOOT 1
```

### BOTTOM

```text
MOVE 2

HEAL 2
SELF
```

`ENHANCEABLE: MOVE, HEAL`

---

## Crippling Noose

**File:** `gh-crippling-noose.png`  
**Level:** 1  
**Initiative:** 57

### TOP

```text
Create one 4 damage STUN trap in an adjacent empty hex.

Gain 2 experience when the trap is sprung by an enemy.
[LOSS]
```

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

This enemy’s ATTACK, MOVE, and RANGE are all reduced by 1.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The three reductions modify all corresponding abilities belonging to the Doomed enemy.

---

## Detonation

**File:** `gh-detonation.png`  
**Level:** 1  
**Initiative:** 54

### TOP

```text
Create one 3 damage trap in an adjacent empty hex.
All enemies adjacent to this trap suffer 2 damage when the trap is sprung.

Gain 2 experience when the trap is sprung by an enemy.
[LOSS]
```

The splash damage is not an attack.

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

When this enemy dies, all enemies adjacent to the hex in which it died suffer 3 damage.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The death splash is not an attack.

---

## Felling Swoop

**File:** `gh-felling-swoop.png`  
**Level:** 1  
**Initiative:** 75

### TOP

```text
SUMMON War Hawk
HP 3
MOVE 3
ATTACK 3
FLYING

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

When this enemy dies, TELEPORT to the hex occupied by the enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The teleport is treated as movement for effects that prevent movement, but no intervening hexes are entered.

---

## Foot Snare

**File:** `gh-foot-snare.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
Create one 2 damage IMMOBILIZE trap in an adjacent empty hex.
```

### BOTTOM

```text
IMMOBILIZE
Target one adjacent enemy

MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Frightening Curse

**File:** `gh-frightening-curse.png`  
**Level:** 1  
**Initiative:** 37

### TOP

```text
The next three times a DOOMED enemy dies within RANGE 2 of another enemy, transfer one DOOM to that enemy instead of discarding it.

TRACK: [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only a death with at least one valid transfer target consumes a use. If several enemies are within Range 2, choose the recipient.

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

When this enemy dies, force all enemies adjacent to the hex in which it died to perform a "MOVE 1" action with you controlling the actions.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

Each affected enemy performs a separate forced movement.

---

## Multi-Pronged Assault

**File:** `gh-multi-pronged-assault.png`  
**Level:** 1  
**Initiative:** 14

### TOP

```text
ATTACK 4
RANGE 3
TARGET 2

XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE, TARGET`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

All allies add +1 ATTACK to all their attacks targeting this enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

Because the card says `ALLY`, this bonus does not apply to SELF.

---

## Race to the Grave

**File:** `gh-race-to-the-grave.png`  
**Level:** 1  
**Initiative:** 09

### TOP

```text
ATTACK 5
RANGE 5

XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks), RANGE`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

This enemy suffers 2 damage at the start of each of its turns.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The recurring damage is not an attack.

---

## Rain of Arrows

**File:** `gh-rain-of-arrows.png`  
**Level:** 1  
**Initiative:** 33

### TOP

```text
The next four times a DOOMED enemy dies, perform an "ATTACK 2, RANGE 5" action.

TRACK: [XP+1] [ ] [XP+1] [ ] -> end
[PERSISTENT, LOSS]
```

Each trigger creates a separate attack action. The first and third uses each award 1 XP.

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

Add +2 ATTACK to all your attacks targeting this enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

---

## Solid Bow

**File:** `gh-solid-bow.png`  
**Level:** 1  
**Initiative:** 31

### TOP

```text
ATTACK 3
RANGE 5
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Swift Trickery

**File:** `gh-swift-trickery.png`  
**Level:** 1  
**Initiative:** 11

### TOP

```text
ATTACK 2
RANGE 4
Add +2 ATTACK and gain 1 experience if the target is DOOMED.
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Vital Charge

**File:** `gh-vital-charge.png`  
**Level:** 1  
**Initiative:** 78

### TOP

```text
SUMMON Battle Boar
HP 7
MOVE 3
ATTACK 1

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

When this enemy dies, perform a "HEAL 4, SELF" action.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

---

# Level X cards

## Fresh Kill

**File:** `gh-fresh-kill.png`  
**Level:** X  
**Initiative:** 65

### TOP

```text
ATTACK 2
RANGE 4
Add +2 ATTACK and gain 1 experience if the target is undamaged.
```

`ENHANCEABLE: ATTACK, RANGE`

An undamaged target is currently at its maximum hit-point value.

### BOTTOM

```text
MOVE 3
JUMP
```

`ENHANCEABLE: MOVE`

---

## Sap Life

**File:** `gh-sap-life.png`  
**Level:** X  
**Initiative:** 14

### TOP

```text
RETALIATE 3
RANGE 4

XP +2
[ROUND, LOSS]
```

`ENHANCEABLE: RETALIATE, RANGE`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

Each time this enemy suffers damage, perform a "HEAL 2, SELF" action.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

Each separate instance of damage creates a separate Heal ability.

---

## The Hunt Begins

**File:** `gh-the-hunt-begins.png`  
**Level:** X  
**Initiative:** 71

### TOP

```text
SUMMON Vicious Jackal
HP 5
MOVE 2
ATTACK 1
RETALIATE 1
WOUND

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

All summoned allies add +2 ATTACK to all their attacks targeting this enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The effect can apply to summoned allies belonging to other characters; it is not restricted to summons owned by SELF.

---

# Level 2 cards

## Expose

**File:** `gh-expose.png`  
**Level:** 2  
**Initiative:** 13

### TOP

```text
You gain ADVANTAGE on one attack each turn targeting a DOOMED enemy.

All enemies lose INVISIBLE and may no longer gain INVISIBLE.

XP +2
[PERSISTENT, LOSS]
```

The Advantage benefit may be used on at most one qualifying attack during each turn, including a granted attack performed during another figure's turn.

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

You and all allies gain PIERCE 2 on all your attacks targeting this enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

---

## Relentless Offensive

**File:** `gh-relentless-offensive.png`  
**Level:** 2  
**Initiative:** 52

### TOP

```text
ATTACK 2
RANGE 5
TARGET 2
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
Add +2 ATTACK to all your attacks targeting DOOMED enemies this round.
[ROUND]
```

---

# Level 3 cards

## Darkened Skies

**File:** `gh-darkened-skies.png`  
**Level:** 3  
**Initiative:** 25

### TOP

```text
ATTACK 3
Target all enemies within RANGE 3
Gain 1 experience for each two enemies targeted.

[LOSS]
```

A separate attack is resolved against every valid enemy in range.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

When this enemy dies, perform an "ATTACK 2, RANGE 3, TARGET 3" action.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The triggered action may target any valid enemies; it is not centered on the death hex.

---

## Press the Attack

**File:** `gh-press-the-attack.png`  
**Level:** 3  
**Initiative:** 10

### TOP

```text
ATTACK 2
RANGE 4
Add +3 ATTACK and gain 1 experience if the target is DOOMED.
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
RETALIATE 2
RANGE 2
[ROUND]
```

`ENHANCEABLE: RETALIATE`

---

# Level 4 cards

## Flight of Flame

**File:** `gh-flight-of-flame.png`  
**Level:** 4  
**Initiative:** 53

### TOP

```text
Create one 2 damage WOUND trap in an adjacent empty hex.

WOUND all enemies adjacent to this trap when the trap is sprung.
```

The adjacent Wound application is not an attack.

### BOTTOM

```text
MOVE 5
JUMP
```

`ENHANCEABLE: MOVE`

---

## Singular Focus

**File:** `gh-singular-focus.png`  
**Level:** 4  
**Initiative:** 28

### TOP

```text
LOOT 2
XP +2
[LOSS]
```

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

You and all allies gain ADVANTAGE on all your attacks targeting this enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

---

# Level 5 cards

## Inescapable Fate

**File:** `gh-inescapable-fate.png`  
**Level:** 5  
**Initiative:** 97

### TOP

```text
You may have two DOOMS active on the same target.

If a third DOOM is played on the same target, discard one of the others, or if a third DOOM is played on a different target, discard both of the others.

XP +2
[PERSISTENT, LOSS]
```

This permits multiple Dooms only on one enemy; it does not permit Doom tokens on multiple enemies.

### BOTTOM

```text
DOOM: Place your character token on any one normal or elite enemy.
At the start of your next three turns, advance the marker on this card. After it advances three times, the kill the target and discard the card.

TRACK: [ ] [ ] [ ] -> Kill the target, discard
[PERSISTENT]

Discard this if another DOOM is played.
```

Named enemies and bosses are not valid targets for this Doom.

---

## Wild Command

**File:** `gh-wild-command.png`  
**Level:** 5  
**Initiative:** 46

### TOP

```text
One adjacent summoned ally performs
MOVE +0
ATTACK +0
with you controlling the actions.
```

The granted values use the summon's own Move and Attack statistics.

### BOTTOM

```text
MOVE 3

ATTACK 2
Target one DOOMED enemy at any range
```

`ENHANCEABLE: MOVE, ATTACK`

Because the attack says “at any range,” it is a ranged attack and requires line of sight.

---

# Level 6 cards

## Camouflage

**File:** `gh-camouflage.png`  
**Level:** 6  
**Initiative:** 23

### TOP

```text
ATTACK 3
RANGE 5

INVISIBLE
SELF
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3

INVISIBLE
SELF
```

`ENHANCEABLE: MOVE`

---

## Nature's Hunger

**File:** `gh-natures-hunger.png`  
**Level:** 6  
**Initiative:** 82

### TOP

```text
SUMMON Giant Toad
HP 7
MOVE 1
ATTACK 1
STUN

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

When this enemy dies, you and all allies perform a "HEAL 2, SELF" action.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

Each affected figure performs its own separate Heal ability.

---

# Level 7 cards

## Crashing Wave

**File:** `gh-crashing-wave.png`  
**Level:** 7  
**Initiative:** 22

### TOP

```text
MOVE 4

ATTACK 3
Target all adjacent enemies
Gain 1 experience for each enemy targeted.

[LOSS]
```

`ENHANCEABLE: MOVE, ATTACK (2 marks)`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

You and all allies gain CURSE on all your attacks targeting this enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

The attacker applies Curse to the Doomed enemy; the attacker is not cursed.

---

## Impending End

**File:** `gh-impending-end.png`  
**Level:** 7  
**Initiative:** 47

### TOP

```text
ATTACK 4
RANGE 4
Kill the target and gain 1 experience if its hit point value is less than or equal to 2 after the attack.
```

`ENHANCEABLE: ATTACK, RANGE`

The execute check uses the target's hit points after the attack has fully resolved.

### BOTTOM

```text
Add +3 ATTACK to all your attacks targeting DOOMED enemies this round.
[ROUND]
```

---

# Level 8 cards

## Feral Instincts

**File:** `gh-feral-instincts.png`  
**Level:** 8  
**Initiative:** 35

### TOP

```text
MOVE 2

ATTACK 3

LOOT 1
```

`ENHANCEABLE: MOVE, ATTACK`

These are three separate abilities in the listed order.

### BOTTOM

```text
MOVE 3

HEAL 4
SELF
```

`ENHANCEABLE: MOVE, HEAL`

---

## Rising Momentum

**File:** `gh-rising-momentum.png`  
**Level:** 8  
**Initiative:** 12

### TOP

```text
ATTACK 6
RANGE 5
PUSH 3

XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE, PUSH`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

If this enemy dies within RANGE 2 of another enemy, transfer this and any other DOOMS to that enemy instead of discarding them.

When another DOOM is played, discard this card.
[PERSISTENT]
```

If several enemies are valid transfer recipients, choose one. This can transfer multiple Dooms together when `Inescapable Fate` permits them to coexist.

---

# Level 9 cards

## Lead to Slaughter

**File:** `gh-lead-to-slaughter.png`  
**Level:** 9  
**Initiative:** 40

### TOP

```text
ATTACK 4
RANGE 6
Force the target to move up to three hexes directly toward the closest hex adjacent to one of your allies.
```

`ENHANCEABLE: ATTACK, RANGE`

The forced movement uses the target of the attack and is not a Push or Pull ability.

### BOTTOM

```text
MOVE 3

Transfer all active DOOMS from their current target to one enemy within RANGE 4 of you.
```

`ENHANCEABLE: MOVE`

The Doom transfer is a separate ranged ability after the movement.

---

## Predator and Prey

**File:** `gh-predator-and-prey.png`  
**Level:** 9  
**Initiative:** 86

### TOP

```text
SUMMON Spitting Cobra
HP 5
MOVE 3
ATTACK 3
RANGE 3
POISON

XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SUMMON HP, SUMMON MOVE, SUMMON ATTACK`

### BOTTOM

```text
DOOM: Place your character token on any one enemy.

You and all allies add +X ATTACK to all your attacks targeting this enemy, where X is the difference between the range of the attack and the number of hexes to the enemy.

When this enemy dies or another DOOM is played, discard this card.
[PERSISTENT]
```

```text
PREDATOR_BONUS = max(0, ATTACK_RANGE - HEX_DISTANCE)
```

Example: a Range 5 attack against the Doomed enemy 2 hexes away gets `+3 ATTACK`. The effect never supplies a negative modifier.

---

# File checklist

```text
gh-ds-back.png
gh-a-moments-peace.png
gh-camouflage.png
gh-crashing-wave.png
gh-crippling-noose.png
gh-darkened-skies.png
gh-detonation.png
gh-expose.png
gh-felling-swoop.png
gh-feral-instincts.png
gh-flight-of-flame.png
gh-foot-snare.png
gh-fresh-kill.png
gh-frightening-curse.png
gh-impending-end.png
gh-inescapable-fate.png
gh-lead-to-slaughter.png
gh-multi-pronged-assault.png
gh-natures-hunger.png
gh-predator-and-prey.png
gh-press-the-attack.png
gh-race-to-the-grave.png
gh-rain-of-arrows.png
gh-relentless-offensive.png
gh-rising-momentum.png
gh-sap-life.png
gh-singular-focus.png
gh-solid-bow.png
gh-swift-trickery.png
gh-the-hunt-begins.png
gh-vital-charge.png
gh-wild-command.png
```

---

# Source cross-checks used

- Worldhaven image folder: `images/character-ability-cards/gloomhaven/ds/`
- Gloomhaven Card Browser metadata and filenames
- Gloomhaven Secretariat structured Doomstalker ability definitions and English card text
- Official Gloomhaven FAQ / Dized rulings for Dooms, Wild Command, Frightening Curse, Inescapable Fate, Crashing Wave, Rising Momentum, Predator and Prey, and Felling Swoop
- Full printed action wording checked against all 31 fronts in the three supplied card images
- Original metadata and enhancement annotations retained
