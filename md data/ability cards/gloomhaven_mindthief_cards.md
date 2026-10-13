# Gloomhaven – Mindthief (MT) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/mt/`  
**Files covered:** 30 PNG files = 29 ability-card fronts + 1 card back.  
**Source folder:** https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/mt

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, duration, loss, summon statistics, and enhancement marks use the notation below. Explanatory notes outside the blocks are not printed card text.

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
- `SELF` means the acting Mindthief.
- `ADJACENT` means a distance of exactly 1 hex.
- `TARGET n` means the attack may affect up to `n` different valid targets.
- Every individual attack against one target is resolved separately, including its own attack-modifier draw.

## Common keywords

```text
ATTACK n
MOVE n
HEAL n [SELF | RANGE n]
SHIELD n [SELF]
RETALIATE n [SELF]
LOOT n
PUSH n
JUMP
POISON
WOUND
MUDDLE
IMMOBILIZE
DISARM
STUN
STRENGTHEN
INVISIBLE
CURSE
EXECUTE
SUMMON
```

Printed “kill” effects are written out in full. They are not attacks and do not draw attack modifiers.

## Elements

```text
INFUSE ICE
INFUSE DARK

CONSUME ICE  => effect
CONSUME DARK => effect
```

A consume line is conditional: the listed effect is obtained only when that element is consumed.

If two consume lines are printed independently, either one, both, or neither may be used, provided the corresponding elements are available.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains in the active area after the turn.
- `[LOSS]` — the card is moved to the lost pile after that action is used.
- `[PERSISTENT, LOSS]` — placed in the active area and remains there until removed; it is then moved to the lost pile.

## Augments

An `AUGMENT` is a persistent ability placed in SELF's active area.

```text
AUGMENT [PERSISTENT]

While active:
- listed effect on every melee attack performed by SELF

THEN:
- the one-time ATTACK printed below the Augment
```

Rules preserved by this transcription:

- The newly played Augment becomes active before the following printed attack, so that attack benefits from it.
- The Augment ability may be skipped while still performing the later attack.
- Normally, when another Augment is played, the previously active Augment is discarded.
- Augments affect SELF's melee attacks, not attacks made by SELF's summons.
- `Mass Hysteria` changes the normal one-Augment limit to two.

## Forced and granted actions

```text
FORCE <figure> TO PERFORM <action>
GRANT <ally> <action>
```

- SELF controls a forced action unless the card says otherwise.
- Forcing a figure to perform an action does not normally replace that figure's own turn.
- A monster forced to attack uses the monster attack-modifier deck.
- A granted attack performed by an ally uses that ally's attack-modifier deck.
- `Hostile Takeover` and `Domination` affect the selected enemy's actual turn for the round; they do not create an additional immediate turn.
- An enemy that has already acted this round is not a valid effective choice for those two turn-control actions.

## Summons

Summon statistics are written as:

```text
SUMMON <name>
- HP n
- MOVE n
- ATTACK n
- RANGE — | n
- additional attack effects
```

A summon action is `[PERSISTENT, LOSS]`. The summon remains on the map while alive; when the active card leaves the active area, it goes to the lost pile.

## Negative-condition formulas

```text
NEGATIVE_CONDITIONS(target)
    = number of negative conditions already present on target
      immediately before attack damage is resolved
```

For `Submissive Affliction` and `Perverse Edge`:

- Conditions applied by the current attack are not counted.
- `CURSE` is not counted because it is placed in an attack-modifier deck rather than on that specific target.

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: first ATTACK, second ATTACK
```

---

# Card back

## `gh-mt-back.png`

Mindthief / Brain ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Empathetic Assault

**File:** `gh-empathetic-assault.png`  
**Level:** 1  
**Initiative:** 11

### TOP

```text
ATTACK 4
RANGE 5
DISARM
INFUSE ICE
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 2

HEAL 2
SELF
```

`ENHANCEABLE: MOVE, HEAL`

---

## Fearsome Blade

**File:** `gh-fearsome-blade.png`  
**Level:** 1  
**Initiative:** 27

### TOP

```text
ATTACK 2
PUSH 3
XP +1
```

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
MOVE 4

ATTACK 2
XP +2
[LOSS]
```

`ENHANCEABLE: MOVE, ATTACK`

The move and attack are separate abilities performed in that order.

---

## Feedback Loop

**File:** `gh-feedback-loop.png`  
**Level:** 1  
**Initiative:** 79

### TOP

```text
AUGMENT
On your melee attacks:
Gain SHIELD 1 [ROUND]

ATTACK 1
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

Each separate melee attack grants a separate `SHIELD 1` ability for the remainder of the round. These Shield values stack.

### BOTTOM

```text
MOVE 4
JUMP

If you end the movement in the same hex you started in, perform
MUDDLE
Target all enemies moved through
```

`ENHANCEABLE: MOVE`

The Muddle application is a separate non-attack ability after the movement.

---

## Gnawing Horde

**File:** `gh-gnawing-horde.png`  
**Level:** 1  
**Initiative:** 82

### TOP

```text
SUMMON Rat Swarm
HP 6
MOVE 1
ATTACK 2
RANGE —
POISON

XP +2
[PERSISTENT, LOSS]
```

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Into the Night

**File:** `gh-into-the-night.png`  
**Level:** 1  
**Initiative:** 14

### TOP

```text
LOOT 1
INFUSE DARK
```

### BOTTOM

```text
INVISIBLE
SELF
```

---

## Parasitic Influence

**File:** `gh-parasitic-influence.png`  
**Level:** 1  
**Initiative:** 71

### TOP

```text
AUGMENT
On your melee attacks:
HEAL 2
SELF

ATTACK 1
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
Force one enemy within RANGE 4 to perform
MOVE 1
with you controlling the action.
```

---

## Perverse Edge

**File:** `gh-perverse-edge.png`  
**Level:** 1  
**Initiative:** 08

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience for each negative condition on the target.
[LOSS]
```

`ENHANCEABLE: ATTACK`

The attack's printed base value is `3`. For every qualifying condition already on the target, add `+2 ATTACK` and gain `1 XP`.

### BOTTOM

```text
ATTACK 1
RANGE 2
STUN
INFUSE ICE
XP +1
```

`ENHANCEABLE: ATTACK, RANGE`

---

## Scurry

**File:** `gh-scurry.png`  
**Level:** 1  
**Initiative:** 20

### TOP

```text
MOVE 3

ATTACK 1
```

`ENHANCEABLE: MOVE, ATTACK`

The movement and attack are separate abilities in that order.

### BOTTOM

```text
LOOT 2
XP +1
[LOSS]
```

---

## Submissive Affliction

**File:** `gh-submissive-affliction.png`  
**Level:** 1  
**Initiative:** 48

### TOP

```text
ATTACK 2
Add +1 ATTACK for each negative condition on the target.
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
Force one enemy within RANGE 5 to perform
ATTACK 2
RANGE +0
targeting another enemy with you controlling the action.
```

`RANGE +0` means the forced enemy uses its own base Range without modification. Any attack effects printed on that monster's stat card also apply, and the monster uses the monster attack-modifier deck.

This is not a targeted ability performed by SELF; the selected enemy is the acting attacker.

---

## The Mind's Weakness

**File:** `gh-the-minds-weakness.png`  
**Level:** 1  
**Initiative:** 75

### TOP

```text
AUGMENT
On your melee attacks:
Add +2 ATTACK

ATTACK 1
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

The supplied card image prints “On your melee attacks”; that wording is retained.

### BOTTOM

```text
ATTACK 1
WOUND
```

---

# Level X cards

## Frigid Apparition

**File:** `gh-frigid-apparition.png`  
**Level:** X  
**Initiative:** 29

### TOP

```text
ATTACK 3
CONSUME ICE: STUN, 1 experience
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4

STUN
Target one adjacent enemy

XP +1
[LOSS]
```

`ENHANCEABLE: MOVE`

The Stun is a separate non-attack ability after the movement.

---

## Possession

**File:** `gh-possession.png`  
**Level:** X  
**Initiative:** 51

### TOP

```text
One adjacent ally may perform
ATTACK 6

XP +2
[LOSS]
```

### BOTTOM

```text
One ally within RANGE 3 may perform
MOVE 4
```

---

## Withering Claw

**File:** `gh-withering-claw.png`  
**Level:** X  
**Initiative:** 77

### TOP

```text
AUGMENT
On your melee attacks:
Add MUDDLE and POISON

ATTACK 1
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
IMMOBILIZE
Target one adjacent enemy

MOVE 3
```

`ENHANCEABLE: MOVE`

The Immobilize is a separate non-attack ability performed before the movement.

---

# Level 2 cards

## Hostile Takeover

**File:** `gh-hostile-takeover.png`  
**Level:** 2  
**Initiative:** 09

### TOP

```text
ATTACK 2
RANGE 4
IMMOBILIZE
INFUSE ICE
XP +1
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
Force one normal or elite enemy within RANGE 3 to perform its turn this round as if its allies were enemies and its enemies were allies.

To signify this, place one of your character tokens on this enemy for the round.

XP +2
[ROUND, LOSS]
```

The action has no effect on an enemy that has already acted this round. It modifies the selected enemy's normal turn; it does not grant an additional immediate turn.

---

## Wretched Creature

**File:** `gh-wretched-creature.png`  
**Level:** 2  
**Initiative:** 84

### TOP

```text
SUMMON Monstrous Rat
HP 4
MOVE 3
ATTACK 3
RANGE —

XP +2
[PERSISTENT, LOSS]
```

### BOTTOM

```text
MOVE 3

CONSUME DARK:
CURSE
Target one adjacent enemy
```

`ENHANCEABLE: MOVE`

The Curse is a separate non-attack ability after the movement.

---

# Level 3 cards

## Brain Leech

**File:** `gh-brain-leech.png`  
**Level:** 3  
**Initiative:** 16

### TOP

```text
ATTACK 4
RANGE 4

HEAL 4
SELF

INFUSE DARK
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE, HEAL`

### BOTTOM

```text
ATTACK 1

STRENGTHEN
SELF
```

`ENHANCEABLE: ATTACK`

The Strengthen is a separate ability after the attack.

---

## Silent Scream

**File:** `gh-silent-scream.png`  
**Level:** 3  
**Initiative:** 73

### TOP

```text
AUGMENT
On your melee attacks:
HEAL 2
RANGE 2

ATTACK 2
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: HEAL, RANGE, ATTACK`

The triggered heal may target any valid figure within Range 2.

### BOTTOM

```text
MOVE 3

PUSH 2
Target all adjacent enemies
CONSUME ICE: +1 PUSH, 1 experience
```

`ENHANCEABLE: MOVE, PUSH`

The Push is a separate non-attack ability after the movement. If Ice is consumed, every affected target is pushed with value `3`.

---

# Level 4 cards

## Cranium Overload

**File:** `gh-cranium-overload.png`  
**Level:** 4  
**Initiative:** 05

### TOP

```text
Kill one normal enemy within RANGE 4.

ATTACK 2
Target all enemies adjacent to the killed target
Gain 1 experience for each enemy targeted with this attack.

[LOSS]
```

`ENHANCEABLE: ATTACK`

The initial executed enemy grants no XP. Each subsequent attack is a ranged attack originating from SELF, so normal line-of-sight requirements apply to every target.

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Pilfer

**File:** `gh-pilfer.png`  
**Level:** 4  
**Initiative:** 68

### TOP

```text
LOOT 1
Gain one additional money token from the supply for each adjacent enemy.
```

The action cannot be performed solely to gain the extra supplied tokens; at least one token on the map must actually be collected by `LOOT 1`.

### BOTTOM

```text
ATTACK 2

MOVE 3

ATTACK 2
XP +2
[LOSS]
```

`ENHANCEABLE: first ATTACK, MOVE, second ATTACK`

These are three separate abilities in the printed order. Either attack may be skipped independently.

---

# Level 5 cards

## Frozen Mind

**File:** `gh-frozen-mind.png`  
**Level:** 5  
**Initiative:** 81

### TOP

```text
AUGMENT
On your melee attacks:
CONSUME ICE: STUN

ATTACK 2
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

The Ice consumption is optional for each qualifying melee attack. The attack printed on this action may use the newly activated Augment.

### BOTTOM

```text
Force one enemy within RANGE 5 to perform
MOVE 2
with you controlling the action.
```

`ENHANCEABLE: MOVE`

---

## Mass Hysteria

**File:** `gh-mass-hysteria.png`  
**Level:** 5  
**Initiative:** 12

### TOP

```text
ATTACK 1
RANGE 4
TARGET 4
MUDDLE
INFUSE ICE
XP +1
```

`ENHANCEABLE: TARGET`

### BOTTOM

```text
You may have two AUGMENTS active at once.

If a third AUGMENT is played, discard one of the others.
[PERSISTENT, LOSS]
```

`Mass Hysteria` is not itself an Augment and does not occupy either Augment slot.

---

# Level 6 cards

## Corrupting Embrace

**File:** `gh-corrupting-embrace.png`  
**Level:** 6  
**Initiative:** 39

### TOP

```text
ATTACK 1
POISON

ATTACK 1
MUDDLE
```

`ENHANCEABLE: first ATTACK, second ATTACK`

These are two separate melee attacks and may target the same or different enemies.

### BOTTOM

```text
MOVE 4
JUMP

POISON
Target all enemies moved through

INFUSE DARK
```

`ENHANCEABLE: MOVE`

The Poison application is a separate non-attack ability after the movement.

---

## Dark Frenzy

**File:** `gh-dark-frenzy.png`  
**Level:** 6  
**Initiative:** 10

### TOP

```text
ATTACK 2
CONSUME ICE: +2 ATTACK, 1 experience
CONSUME DARK: +2 ATTACK, 1 experience
```

`ENHANCEABLE: ATTACK`

The two consume effects are independent. Consuming both produces `ATTACK 6` and awards `2 XP`.

### BOTTOM

```text
MOVE 3

ATTACK 3
RANGE 3

INFUSE ICE
XP +1
```

`ENHANCEABLE: MOVE, ATTACK, RANGE`

The movement and attack are separate abilities in that order.

---

# Level 7 cards

## Psychic Projection

**File:** `gh-psychic-projection.png`  
**Level:** 7  
**Initiative:** 92

### TOP

```text
HEAL 5
SELF

SHIELD 1
SELF
[ROUND]

STUN
SELF
```

`ENHANCEABLE: HEAL, SHIELD`

The self-Stun is applied last. If this is SELF's first action of the turn, SELF cannot perform the other selected card's action afterward.

### BOTTOM

```text
All your ranged attacks gain the effects of your AUGMENTS this round.
[ROUND, LOSS]
```

This does not convert the ranged attacks into melee attacks; it only copies the active Augment effects onto them.

---

## Vicious Blood

**File:** `gh-vicious-blood.png`  
**Level:** 7  
**Initiative:** 83

### TOP

```text
AUGMENT
On your melee attacks:
Gain RETALIATE 2 [ROUND]

ATTACK 2
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

Each separate melee attack grants a separate `RETALIATE 2` ability for the remainder of the round, so repeated triggers stack.

### BOTTOM

```text
MOVE 2

ATTACK 2
```

`ENHANCEABLE: MOVE, ATTACK`

---

# Level 8 cards

## Domination

**File:** `gh-domination.png`  
**Level:** 8  
**Initiative:** 13

### TOP

```text
One adjacent ally may perform
ATTACK 4
This attack gains the effects of your AUGMENTS.

XP +1
```

`ENHANCEABLE: ATTACK`

The granted `ATTACK 4` is considered melee even if the affected ally normally has a ranged attack trait. The ally performs the attack and uses its own attack-modifier deck.

### BOTTOM

```text
Force one normal or elite enemy within RANGE 5 to perform its turn this round as if its allies were enemies and its enemies were allies, with you controlling the actions.

To signify this, place one of your character tokens on this enemy for the round.

XP +2
[ROUND, LOSS]
```

The action has no effect on an enemy that has already acted this round. It modifies the selected enemy's normal turn; it does not grant an additional immediate turn.

---

## Shared Nightmare

**File:** `gh-shared-nightmare.png`  
**Level:** 8  
**Initiative:** 07

### TOP

```text
ATTACK 3
RANGE 4
TARGET 2
CONSUME DARK: POISON, CURSE

INFUSE ICE
XP +1
```

`ENHANCEABLE: ATTACK, RANGE, TARGET`

If Dark is consumed, both added conditions apply to every target of this attack ability.

### BOTTOM

```text
MOVE 5

CONSUME ICE:
SHIELD 1
SELF
XP +1
[ROUND]
```

`ENHANCEABLE: MOVE, SHIELD`

The Shield and XP are both conditional on consuming Ice.

---

# Level 9 cards

## Many as One

**File:** `gh-many-as-one.png`  
**Level:** 9  
**Initiative:** 91

### TOP

```text
SUMMON Rat King
HP 9
MOVE 2
ATTACK X
RANGE —
X is half the rat’s current hit point value (rounded up).

XP +2
[PERSISTENT, LOSS]
```

The Rat King's attack value is recalculated from its current HP whenever it attacks.

### BOTTOM

```text
All allies within RANGE 2 add +X ATTACK to all their attacks this round, where X is the number of your allies within RANGE 2 of you.

XP +2
[ROUND, LOSS]
```

SELF is not counted as its own ally. All qualifying allies receive the same value of `X`, and the bonus applies to all of their attacks for the round.

---

## Phantasmal Killer

**File:** `gh-phantasmal-killer.png`  
**Level:** 9  
**Initiative:** 67

### TOP

```text
AUGMENT
On your melee attacks:
CONSUME DARK: Kill one normal target instead

ATTACK 2
XP +1

When another AUGMENT is played, discard this card.
[PERSISTENT]
```

`ENHANCEABLE: ATTACK`

The Dark consumption replaces the qualifying attack against one normal target with an execute effect. The attack printed on this action may use the newly activated Augment.

### BOTTOM

```text
MOVE 2

LOOT 1

INVISIBLE
SELF

INFUSE DARK
```

`ENHANCEABLE: MOVE`

These are separate abilities performed in the printed order.

---

# File checklist

```text
gh-mt-back.png
gh-brain-leech.png
gh-corrupting-embrace.png
gh-cranium-overload.png
gh-dark-frenzy.png
gh-domination.png
gh-empathetic-assault.png
gh-fearsome-blade.png
gh-feedback-loop.png
gh-frigid-apparition.png
gh-frozen-mind.png
gh-gnawing-horde.png
gh-hostile-takeover.png
gh-into-the-night.png
gh-many-as-one.png
gh-mass-hysteria.png
gh-parasitic-influence.png
gh-perverse-edge.png
gh-phantasmal-killer.png
gh-pilfer.png
gh-possession.png
gh-psychic-projection.png
gh-scurry.png
gh-shared-nightmare.png
gh-silent-scream.png
gh-submissive-affliction.png
gh-the-minds-weakness.png
gh-vicious-blood.png
gh-withering-claw.png
gh-wretched-creature.png
```

---

# Source cross-checks used

- Worldhaven image folder: https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/mt
- Gloomhaven Card Browser Mindthief metadata and filenames: https://www.gloomhavencards.com/gh/characters/MT
- Dized Gloomhaven Mindthief FAQ and official card clarifications
- Full printed action wording checked against all 29 fronts in the three supplied card images
- Original metadata, enhancement annotations, and explanatory rules retained
