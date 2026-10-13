# Gloomhaven – Scoundrel (SC) Ability Cards

**Source folder:** [Worldhaven – `images/character-ability-cards/gloomhaven/sc/`](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sc)  
**Scope:** `images/character-ability-cards/gloomhaven/sc/`  
**Files covered:** 29 PNG files = 28 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, tracks, duration, loss, and enhancement marks use the notation below. Explanatory notes outside the blocks are not printed card text.

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
- “An ally of SELF” never includes SELF.
- “An ally of the target” means another figure allied to that target.

## Common keywords

```text
ATTACK n
MOVE n
HEAL n [SELF | RANGE n]
LOOT n
PULL n
RETALIATE n [SELF]
POISON
WOUND
IMMOBILIZE
STUN
DISARM
INVISIBLE
ADVANTAGE
```

- `DISARM` is the negative condition.
- Printed trap-disarming instructions remove the trap without applying its effects to SELF.
- Printed “kill” effects are not attacks.

## Elements

```text
INFUSE DARK
INFUSE FIRE
CONSUME DARK => effect
```

A consume line is conditional: the listed effect is obtained only when `DARK` is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[ROUND, LOSS]` — a loss action whose ongoing effect lasts for the current round.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+n]` inside a track means that use also awards `n` experience.

Examples:

```text
TRACK: [ ] [XP+1] [ ] [XP+1] -> end
TRACK: [XP+2] -> end
```

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

The named entry identifies the exact printed line containing the mark.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: INVISIBLE
```

## Full wording

Targeting conditions are written out on every card. Being adjacent to one of your allies and being adjacent to none of the target’s allies are independent requirements. Experience icons inside sentences are written as “n experience”; standalone experience icons use `XP +n`.

---

# Card back

## `gh-sc-back.png`

Scoundrel ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Backstab

**File:** `gh-backstab.png`  
**Level:** 1  
**Initiative:** 06

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience when the target is adjacent to any of your allies.
Add +2 ATTACK and gain 1 experience when the target is adjacent to none of its allies.
[LOSS]
```

The two conditional modifiers are independent and may both apply to the same attack.

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 6
```

---

## Flanking Strike

**File:** `gh-flanking-strike.png`  
**Level:** 1  
**Initiative:** 04

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience when the target is adjacent to any of your allies.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Quick Hands

**File:** `gh-quick-hands.png`  
**Level:** 1  
**Initiative:** 64

### TOP

```text
MOVE 2

ATTACK 2
```

`ENHANCEABLE: MOVE, ATTACK`

The movement and attack are separate abilities in the listed order.

### BOTTOM

```text
LOOT 1
```

---

## Single Out

**File:** `gh-single-out.png`  
**Level:** 1  
**Initiative:** 86

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience when the target is adjacent to any of your allies.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
On your next four attacks targeting enemies adjacent to none of their allies, add +2 ATTACK.

TRACK: [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying attacks consume uses.  
The second and fourth qualifying attacks each award 1 XP.

---

## Smoke Bomb

**File:** `gh-smoke-bomb.png`  
**Level:** 1  
**Initiative:** 12

### TOP

```text
INVISIBLE
SELF
INFUSE DARK

On your next attack while INVISIBLE, double the value of the attack.

TRACK: [XP+2] -> end
[PERSISTENT, LOSS]
```

The delayed effect remains active until a qualifying attack is performed.  
The qualifying attack consumes the single use and awards 2 XP.

`ENHANCEABLE: INVISIBLE`

### BOTTOM

```text
PULL 2
RANGE 3
```

`ENHANCEABLE: PULL, RANGE`

---

## Special Mixture

**File:** `gh-special-mixture.png`  
**Level:** 1  
**Initiative:** 33

### TOP

```text
HEAL 3
SELF
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
MOVE 3

POISON
Target one adjacent enemy
```

`ENHANCEABLE: MOVE`

The poison application is a separate non-attack ability.

---

## Thief's Knack

**File:** `gh-thiefs-knack.png`  
**Level:** 1  
**Initiative:** 23

### TOP

```text
Disarm one adjacent trap.
XP +2
```

### BOTTOM

```text
ATTACK 3
XP +1
```

---

## Throwing Knives

**File:** `gh-throwing-knives.png`  
**Level:** 1  
**Initiative:** 10

### TOP

```text
ATTACK 2
RANGE 3
TARGET 2
XP +1
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
LOOT 2
[LOSS]
```

---

## Venom Shiv

**File:** `gh-venom-shiv.png`  
**Level:** 1  
**Initiative:** 60

### TOP

```text
ATTACK 3
POISON
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 5
```

---

# Level X cards

## Sinister Opportunity

**File:** `gh-sinister-opportunity.png`  
**Level:** X  
**Initiative:** 93

### TOP

```text
ATTACK 3
Add +1 ATTACK for each of your allies adjacent to the target.
CONSUME DARK: Add +2 ATTACK instead, 1 experience
```

`ENHANCEABLE: ATTACK`

Consuming `DARK` replaces the entire ally-count modifier; it does not add another `+2` on top of it.

### BOTTOM

```text
MOVE 3

Force one adjacent enemy to perform
MOVE 1
with you controlling the action, and ending in a hex adjacent to you.
```

The forced movement is a separate non-attack ability.

---

## Swift Bow

**File:** `gh-swift-bow.png`  
**Level:** X  
**Initiative:** 36

### TOP

```text
ATTACK 3
RANGE 4
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 2
Loot every hex you enter with this action.
```

`ENHANCEABLE: MOVE`

The starting hex is not entered by the movement and is therefore not included automatically.

---

## Trickster's Reversal

**File:** `gh-tricksters-reversal.png`  
**Level:** X  
**Initiative:** 09

### TOP

```text
ATTACK 2
Add +X ATTACK where X is double the SHIELD value of the target.

XP +2
[LOSS]
```

The target's Shield is still applied normally when the attack deals damage.

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
The next time you suffer damage this round, suffer no damage instead.
[ROUND]
```

---

# Level 2 cards

## Flintlock

**File:** `gh-flintlock.png`  
**Level:** 2  
**Initiative:** 90

### TOP

```text
LOOT 1
```

### BOTTOM

```text
ATTACK 5
RANGE 4
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks), RANGE`

---

## Open Wound

**File:** `gh-open-wound.png`  
**Level:** 2  
**Initiative:** 11

### TOP

```text
ATTACK 4
Add WOUND and gain 1 experience when the target is adjacent to any of your allies.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

# Level 3 cards

## Duelist's Advance

**File:** `gh-duelists-advance.png`  
**Level:** 3  
**Initiative:** 16

### TOP

```text
MOVE 3

ATTACK 3
```

`ENHANCEABLE: MOVE, ATTACK`

The movement and attack are separate abilities in the listed order.

### BOTTOM

```text
Add +2 ATTACK to all your attacks this round targeting enemies adjacent to none of their allies.
[ROUND]
```

---

## Hidden Daggers

**File:** `gh-hidden-daggers.png`  
**Level:** 3  
**Initiative:** 85

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
INVISIBLE
SELF
```

`ENHANCEABLE: INVISIBLE`

---

# Level 4 cards

## Flurry of Blades

**File:** `gh-flurry-of-blades.png`  
**Level:** 4  
**Initiative:** 03

### TOP

```text
ATTACK 2
RANGE 3
TARGET 3
XP +1
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
MOVE 4

Gain ADVANTAGE on all your attacks this round.
[ROUND]
```

`ENHANCEABLE: MOVE`

---

## Gruesome Advantage

**File:** `gh-gruesome-advantage.png`  
**Level:** 4  
**Initiative:** 62

### TOP

```text
ATTACK 3
Add +3 ATTACK and gain 1 experience when the target is adjacent to any of your allies.
Add +3 ATTACK and gain 1 experience when the target is adjacent to none of its allies.
[LOSS]
```

The two conditional modifiers are independent and may both apply to the same attack.

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 7
```

---

# Level 5 cards

## Cull the Weak

**File:** `gh-cull-the-weak.png`  
**Level:** 5  
**Initiative:** 16

### TOP

```text
ATTACK 4
Add +2 ATTACK and gain 1 experience when the target is adjacent to any of your allies.
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
On your next six attacks targeting enemies with DISARM, IMMOBILIZE, or STUN, add +2 ATTACK.

TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying attacks consume uses.  
The second, fourth, and sixth qualifying attacks each award 1 XP.

---

## Visage of the Inevitable

**File:** `gh-visage-of-the-inevitable.png`  
**Level:** 5  
**Initiative:** 88

### TOP

```text
Kill one adjacent normal enemy that is adjacent to none of its allies and also adjacent to any of your allies.
XP +1
```

Elite and boss enemies are not valid targets.

### BOTTOM

```text
MOVE 2

POISON
Target all adjacent enemies
```

`ENHANCEABLE: MOVE`

The poison application is a separate non-attack ability.

---

# Level 6 cards

## Burning Oil

**File:** `gh-burning-oil.png`  
**Level:** 6  
**Initiative:** 95

### TOP

```text
ATTACK 4
RANGE 3
WOUND
INFUSE FIRE
XP +1
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
LOOT 2
XP +1
```

---

## Crippling Poison

**File:** `gh-crippling-poison.png`  
**Level:** 6  
**Initiative:** 30

### TOP

```text
Add +2 ATTACK to all your attacks targeting enemies with POISON.
[PERSISTENT, LOSS]
```

### BOTTOM

```text
ATTACK 2
POISON
IMMOBILIZE
XP +1
```

`ENHANCEABLE: ATTACK`

---

# Level 7 cards

## Spring the Trap

**File:** `gh-spring-the-trap.png`  
**Level:** 7  
**Initiative:** 13

### TOP

```text
Disarm one adjacent trap.

Target one enemy within RANGE 3 to suffer the effects of the disarmed trap.
XP +2
```

The trap is removed and SELF does not suffer its effects.

### BOTTOM

```text
On your next attack targeting an enemy adjacent to none of its allies and also adjacent to any of your allies, double the value of the attack.

TRACK: [XP+2] -> end
[PERSISTENT, LOSS]
```

Only a qualifying attack consumes the single use and awards 2 XP.

---

## Stick to the Shadows

**File:** `gh-stick-to-the-shadows.png`  
**Level:** 7  
**Initiative:** 26

### TOP

```text
ATTACK 4
CONSUME DARK: Add +4 ATTACK and gain 2 experience when the target is adjacent to any of your allies.
```

Both consuming `DARK` and satisfying `FLANKED(target)` are required for the modifier.

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 2

INVISIBLE
SELF
```

`ENHANCEABLE: INVISIBLE`

The invisibility is a separate ability performed after the movement.

---

# Level 8 cards

## Pain's End

**File:** `gh-pains-end.png`  
**Level:** 8  
**Initiative:** 38

### TOP

```text
HEAL 8
SELF
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
MOVE 3

Kill one adjacent enemy whose current hit point value is equal to or less than 3.
```

The execute is a separate ability after the movement.

---

## Stiletto Storm

**File:** `gh-stiletto-storm.png`  
**Level:** 8  
**Initiative:** 80

### TOP

```text
ATTACK 2
RANGE 3
TARGET 4
XP +1
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
MOVE 4

RETALIATE 1
SELF
[ROUND]
```

`ENHANCEABLE: MOVE, RETALIATE`

---

# Level 9 cards

## Long Con

**File:** `gh-long-con.png`  
**Level:** 9  
**Initiative:** 02

### TOP

```text
ATTACK 4
Target all adjacent enemies
DISARM
```

`ENHANCEABLE: ATTACK`

A separate attack is resolved against every adjacent enemy.

### BOTTOM

```text
Force one adjacent normal or elite enemy to perform its turn this round as if its allies were enemies and its enemies were allies, with you controlling the actions.

To signify this, place one of your character tokens on this enemy for the round.

XP +2
[ROUND, LOSS]
```

Boss enemies are not valid targets.  
The chosen enemy does not act immediately merely because this ability is performed; the effect controls its turn during the round.

---

## Watch It Burn

**File:** `gh-watch-it-burn.png`  
**Level:** 9  
**Initiative:** 98

### TOP

```text
Add POISON and WOUND to all your attacks.

XP +2
[PERSISTENT, LOSS]
```

### BOTTOM

```text
MOVE 5

WOUND
Target all adjacent enemies
```

`ENHANCEABLE: MOVE, WOUND`

The wound application is a separate non-attack ability.

---

# File checklist

```text
gh-sc-back.png
gh-backstab.png
gh-burning-oil.png
gh-crippling-poison.png
gh-cull-the-weak.png
gh-duelists-advance.png
gh-flanking-strike.png
gh-flintlock.png
gh-flurry-of-blades.png
gh-gruesome-advantage.png
gh-hidden-daggers.png
gh-long-con.png
gh-open-wound.png
gh-pains-end.png
gh-quick-hands.png
gh-single-out.png
gh-sinister-opportunity.png
gh-smoke-bomb.png
gh-special-mixture.png
gh-spring-the-trap.png
gh-stick-to-the-shadows.png
gh-stiletto-storm.png
gh-swift-bow.png
gh-thiefs-knack.png
gh-throwing-knives.png
gh-tricksters-reversal.png
gh-venom-shiv.png
gh-visage-of-the-inevitable.png
gh-watch-it-burn.png
```

---

# Source cross-checks used

- [Worldhaven Scoundrel image folder](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sc)
- Gloomhaven Card Browser card names, levels, initiatives, and filenames
- Gloomhaven Wiki Scoundrel card metadata
- Full printed action wording checked against all 28 fronts in the three supplied card images
- Stick to the Shadows conditional experience corrected from 1 to 2
- Original enhancement annotations and other metadata retained
