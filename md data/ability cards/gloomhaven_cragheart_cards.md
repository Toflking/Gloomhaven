# Gloomhaven – Cragheart (CH) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/ch/`  
**Files covered:** 31 PNG files = 30 ability-card fronts + 1 card back.

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
- An ability may be skipped unless another listed component explicitly depends on it being performed.

## Common keywords

```text
ATTACK n
MOVE n
HEAL n [SELF | RANGE n]
SHIELD n [SELF]
RETALIATE n [SELF | RANGE n]
LOOT n
PUSH n
IMMOBILIZE
MUDDLE
DISARM
INVISIBLE
JUMP
EXECUTE
```

Printed direct-damage sentences are written out in full. These are not attacks and do not draw attack modifiers.

## Terrain operations

Terrain instructions are written out in full on every card.

- A `single-hex obstacle` occupies exactly one hex.
- Destroying a multi-hex obstacle destroys the complete obstacle unless the card says otherwise.
- Direct damage caused by terrain effects is not an attack.
- Obstacle placement must still obey the normal scenario-accessibility rules.

## Elements

```text
INFUSE EARTH
CONSUME EARTH => effect

INFUSE AIR
CONSUME AIR => effect
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

Conditions and definitions of X are written out on every card. Experience icons inside sentences are written as “n experience”; standalone experience icons use `XP +n`.

## Hex directions and AOE diagrams

Relative directions around the acting figure:

```text
          6   1
        5   S   2
          4   3
```

- `S` = acting figure / origin hex
- `T` = attacked hex
- `.` = unaffected hex
- A diagram may be rotated around `S`; its relative shape remains unchanged.
- For ranged AOE attacks without `S` in the diagram, the complete group of `T` hexes may be placed and rotated subject to the normal range and line-of-sight rules.

---

# Card back

## `gh-ch-back.png`

Cragheart ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Opposing Strike

**File:** `gh-opposing-strike.png`  
**Level:** 1  
**Initiative:** 46

### TOP

```text
ATTACK 3
AOE pattern below
XP +1
```

Exact relative AOE:

```text
CRAGHEART: [0, 0]
TARGETS: [[1, -1], [-1, 1]]
```

Equivalent direction set:

```text
targets = {1, 4}
```

The two attacked hexes are opposite one another. The pattern may be rotated.

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
On the next six melee attacks targeting you, gain RETALIATE 2.

TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second, fourth, and sixth triggered attacks each award 1 XP.

---

## Crushing Grasp

**File:** `gh-crushing-grasp.png`  
**Level:** 1  
**Initiative:** 35

### TOP

```text
ATTACK 3
IMMOBILIZE
INFUSE EARTH
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
LOOT 1
```

---

## Avalanche

**File:** `gh-avalanche.png`  
**Level:** 1  
**Initiative:** 75

### TOP

```text
ATTACK 4
AOE pattern below
CONSUME EARTH: +1 ATTACK, 1 experience

XP +1
[LOSS]
```

Exact relative AOE:

```text
CRAGHEART: [0, 0]
TARGETS: [[1, -1], [1, 0]]
```

Equivalent direction set:

```text
targets = {1, 2}
```

The action attacks two consecutive adjacent hexes. The pattern may be rotated.

`ENHANCEABLE: ATTACK, one AOE hex ([0, -1])`

### BOTTOM

```text
Create two single-hex obstacles in empty hexes adjacent to you.
INFUSE EARTH
```

The two obstacles do not need to be adjacent to each other.

---

## Rumbling Advance

**File:** `gh-rumbling-advance.png`  
**Level:** 1  
**Initiative:** 29

### TOP

```text
HEAL 4
RANGE 2
INFUSE EARTH
```

`ENHANCEABLE: HEAL, RANGE`

### BOTTOM

```text
MOVE 2

All adjacent allies and enemies suffer 1 damage.
INFUSE EARTH
```

The damage is resolved from SELF's final hex after the movement.

`ENHANCEABLE: MOVE`

---

## Massive Boulder

**File:** `gh-massive-boulder.png`  
**Level:** 1  
**Initiative:** 87

### TOP

```text
ATTACK 3
RANGE 3
All allies and enemies adjacent to the target suffer 1 damage.
INFUSE EARTH
```

The target itself is not affected by the additional adjacent-figure damage.

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Backup Ammunition

**File:** `gh-backup-ammunition.png`  
**Level:** 1  
**Initiative:** 77

### TOP

```text
On your next four ranged ATTACK actions, gain ADD TARGET.

TRACK: [XP+1] [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

A use is consumed whenever SELF performs a ranged attack action, even when no additional target is available. Each use awards 1 XP.

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Rock Tunnel

**File:** `gh-rock-tunnel.png`  
**Level:** 1  
**Initiative:** 41

### TOP

```text
Destroy one adjacent obstacle.
XP +1
```

### BOTTOM

```text
MOVE 5
JUMP

IMMOBILIZE
Target all enemies moved through

XP +1
[LOSS]
```

The immobilize is a separate non-attack ability after the movement.

`ENHANCEABLE: MOVE`

---

## Unstable Upheaval

**File:** `gh-unstable-upheaval.png`  
**Level:** 1  
**Initiative:** 13

### TOP

```text
ATTACK 3
Target all adjacent enemies
CONSUME EARTH: Target all enemies up to two hexes away instead, 1 experience

All adjacent allies suffer 2 damage.
XP +1
[LOSS]
```

The Earth-consume XP is additional to the action's printed 1 XP.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
All allies suffer 1 damage.

SHIELD 2
Affect all allies

XP +2
[ROUND, LOSS]
```

`Every ally` does not include SELF.

---

## Crater

**File:** `gh-crater.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
ATTACK 3
RANGE 3
CONSUME EARTH: PUSH 2, 1 experience
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
All adjacent allies and enemies suffer 1 damage.

MOVE 4
JUMP
XP +1

All adjacent allies and enemies suffer 1 damage.
CONSUME EARTH: 2 damage instead, 1 experience

[LOSS]
```

Consuming Earth modifies only the second damage instance, not both.

`ENHANCEABLE: MOVE`

---

## Dirt Tornado

**File:** `gh-dirt-tornado.png`  
**Level:** 1  
**Initiative:** 82

### TOP

```text
ATTACK 1
RANGE 2
AOE pattern below
CONSUME EARTH: +1 ATTACK, 1 experience

MUDDLE all allies and enemies in the targeted area.
```

Exact AOE:

```text
TARGETS: [[0, 0], [0, -1], [1, -1], [1, 0], [0, 1], [-1 ,1], [-1, 0]]
```

This is a seven-hex area consisting of one center hex and all six hexes adjacent to it.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Earthen Clod

**File:** `gh-earthen-clod.png`  
**Level:** 1  
**Initiative:** 38

### TOP

```text
ATTACK 2
RANGE 5
CONSUME EARTH: IMMOBILIZE, 1 experience
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 2
RANGE 3
```

`ENHANCEABLE: HEAL, RANGE`

---

# Level X cards

## Heaving Swing

**File:** `gh-heaving-swing.png`  
**Level:** X  
**Initiative:** 57

### TOP

```text
ATTACK 3
PUSH 1
You may push the target into hexes containing obstacles. In each case, destroy the obstacle, the target suffers 2 damage, and you gain 1 experience.
```

The special effect also works on a flying target. A multi-hex obstacle is destroyed completely when the target enters its first hex.

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
Add +1 ATTACK to all your ranged attacks this round.
[ROUND]
```

---

## Forceful Storm

**File:** `gh-forceful-storm.png`  
**Level:** X  
**Initiative:** 53

### TOP

```text
ATTACK 3
RANGE 3
AOE pattern below
DISARM
Gain 1 experience for each enemy targeted.
INFUSE AIR
[LOSS]
```

Exact AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

All three target hexes are mutually adjacent.

`ENHANCEABLE: ATTACK, one AOE hex ([0, -1])`

### BOTTOM

```text
Add +2 ATTACK to all your melee attacks this round.
[ROUND]
```

---

## Nature's Lift

**File:** `gh-natures-lift.png`  
**Level:** X  
**Initiative:** 64

### TOP

```text
HEAL 2
RANGE 3
TARGET 2
```

`ENHANCEABLE: HEAL, RANGE, TARGET`

### BOTTOM

```text
On your next six ranged ATTACK actions when AIR is Strong or Waning, CONSUME AIR to add +2 RANGE.

TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only a ranged attack action performed while Air is Strong or Waning can trigger and consume a use. The second, fourth, and sixth uses each award 1 XP.

---

# Level 2 cards

## Explosive Punch

**File:** `gh-explosive-punch.png`  
**Level:** 2  
**Initiative:** 28

### TOP

```text
Destroy one adjacent obstacle.

ATTACK 4
Target all enemies adjacent to the destroyed obstacle

INFUSE EARTH
XP +2
[LOSS]
```

The attacks are melee attacks. For a multi-hex obstacle, an enemy is a valid target when adjacent to any hex formerly occupied by that obstacle.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Sentient Growth

**File:** `gh-sentient-growth.png`  
**Level:** 2  
**Initiative:** 78

### TOP

```text
ATTACK 2
RANGE 3
AOE pattern below

HEAL 2
Affect all allies in the targeted area
```

Exact AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

The exact same three-hex area is used for both abilities. At least one enemy must be attacked in the area for the heal ability to be performed.

`ENHANCEABLE: ATTACK, HEAL, one AOE hex ([0, -1])`

### BOTTOM

```text
MOVE 2

HEAL 1
Affect all adjacent allies

ATTACK 1
Target all adjacent enemies
```

The heal and attack are separate abilities and may be skipped independently.

`ENHANCEABLE: HEAL, ATTACK`

---

# Level 3 cards

## Clear the Way

**File:** `gh-clear-the-way.png`  
**Level:** 3  
**Initiative:** 43

### TOP

```text
Move one adjacent single-hex obstacle to an empty hex within RANGE 4.

ATTACK 2
Target all enemies adjacent to the moved obstacle
XP +1
CONSUME EARTH: +1 ATTACK, 1 experience
```

The attacks are ranged attacks, measured from SELF to each enemy.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 5
JUMP

Destroy all obstacles and disarm all traps moved through.
XP +2
[LOSS]
```

`ENHANCEABLE: MOVE (2 marks)`

---

## Blunt Force

**File:** `gh-blunt-force.png`  
**Level:** 3  
**Initiative:** 21

### TOP

```text
ATTACK 4
CONSUME EARTH: +4 ATTACK, 2 experience

XP +1
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 2

RETALIATE 1
SELF

XP +1
[ROUND]
```

`ENHANCEABLE: MOVE, RETALIATE`

---

# Level 4 cards

## Rock Slide

**File:** `gh-rock-slide.png`  
**Level:** 4  
**Initiative:** 81

### TOP

```text
Create three single-hex obstacles in empty hexes within RANGE 4.

All allies and enemies adjacent to one or more created obstacles suffer 2 damage.
INFUSE EARTH
XP +1
```

Damage from multiple created obstacles does not stack; one figure can suffer at most 2 damage from this ability.

### BOTTOM

```text
MOVE 6
The movement must be in a straight line.
```

---

## Kinetic Assault

**File:** `gh-kinetic-assault.png`  
**Level:** 4  
**Initiative:** 19

### TOP

```text
MOVE 1

ATTACK 4
```

`ENHANCEABLE: MOVE, ATTACK`

### BOTTOM

```text
RETALIATE 3
RANGE 3

XP +2
[ROUND, LOSS]
```

`ENHANCEABLE: RETALIATE, RANGE`

---

# Level 5 cards

## Petrify

**File:** `gh-petrify.png`  
**Level:** 5  
**Initiative:** 47

### TOP

```text
Kill one normal enemy within RANGE 4.

Create one single-hex obstacle in the hex in which the enemy died.
INFUSE EARTH
XP +2
[LOSS]
```

Money tokens and treasure tiles in that hex remain on top of the created obstacle and can still be looted by effects that can legally reach them.

### BOTTOM

```text
MOVE 3

IMMOBILIZE
Target all adjacent enemies
```

`ENHANCEABLE: MOVE`

---

## Stone Pummel

**File:** `gh-stone-pummel.png`  
**Level:** 5  
**Initiative:** 32

### TOP

```text
ATTACK 3
AOE pattern below
MUDDLE
XP +1
```

Exact relative AOE:

```text
CRAGHEART: [0, 0]
TARGETS: [[1, -1], [1, 0], [-1, 1], [-1, 0]]
```

Equivalent direction set:

```text
targets = {1, 2, 4, 5}
```

The attack covers four adjacent hexes and excludes one opposite pair. The pattern may be rotated.

`ENHANCEABLE: ATTACK, two AOE hexes`

### BOTTOM

```text
On your next four melee attacks when possible, destroy an adjacent obstacle to add +3 ATTACK.

TRACK: [XP+1] [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

A use is consumed only when an adjacent destructible obstacle exists, even if SELF declines to destroy it and therefore receives no bonus. Each consumed use awards 1 XP.

---

# Level 6 cards

## Dig Pit

**File:** `gh-dig-pit.png`  
**Level:** 6  
**Initiative:** 78

### TOP

```text
Create one 2 damage STUN trap in an adjacent empty hex.

MUDDLE
Target all adjacent enemies
```

### BOTTOM

```text
MOVE 2

INVISIBLE
SELF
```

`ENHANCEABLE: MOVE`

---

## Cataclysm

**File:** `gh-cataclysm.png`  
**Level:** 6  
**Initiative:** 26

### TOP

```text
ATTACK 4
AOE pattern below
CONSUME EARTH: +2 ATTACK, 2 experience

IMMOBILIZE all allies and enemies in the targeted area.
[LOSS]
```

Exact relative AOE:

```text
CRAGHEART: [0, 0]
TARGETS: [[1, -1], [1, 0], [0, 1], [2, -1], [2, 0], [1, 1]]
```

The six target hexes form a forward-facing two-hex-deep area. The pattern may be rotated.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3
CONSUME EARTH: +3 MOVE
```

Consuming Earth produces `MOVE 6`.

`ENHANCEABLE: MOVE`

---

# Level 7 cards

## Meteor

**File:** `gh-meteor.png`  
**Level:** 7  
**Initiative:** 23

### TOP

```text
Create one three-hex triangular obstacle in empty hexes within RANGE 3.

ATTACK 4
IMMOBILIZE
Target all enemies adjacent to the obstacle

INFUSE EARTH
XP +2
[LOSS]
```

The attacks are ranged attacks. The obstacle occupies three mutually adjacent hexes.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
JUMP
```

`ENHANCEABLE: MOVE`

---

## Brutal Momentum

**File:** `gh-brutal-momentum.png`  
**Level:** 7  
**Initiative:** 52

### TOP

```text
ATTACK 3
PUSH 2
For each hex you cannot push the target because of a wall or obstacle, the target suffers 2 damage and you gain 1 experience.
```

At most two unperformed Push hexes can generate this effect.

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
Add +1 ATTACK to all your ranged attacks this round.
CONSUME EARTH: Add +2 ATTACK instead, 1 experience
[ROUND]
```

The chosen bonus applies to all of SELF's ranged attacks for the round.

---

# Level 8 cards

## Rocky End

**File:** `gh-rocky-end.png`  
**Level:** 8  
**Initiative:** 37

### TOP

```text
Destroy all obstacles within RANGE 2.
CONSUME EARTH: +1 RANGE, 1 experience

ATTACK X
where X is twice the number of hexes the destroyed obstacles occupied.
XP +2
[LOSS]
```

The attack is a separate melee attack after all qualifying obstacles are destroyed. Multi-hex obstacles contribute every hex they occupied to the count used for X.

### BOTTOM

```text
MOVE 6
The movement must end in a hex adjacent to an obstacle.
```

---

## Lumbering Bash

**File:** `gh-lumbering-bash.png`  
**Level:** 8  
**Initiative:** 85

### TOP

```text
MOVE 2

ATTACK 5
INFUSE EARTH
XP +1
```

`ENHANCEABLE: MOVE, ATTACK`

### BOTTOM

```text
At the start of your next five turns, perform a "HEAL 2, RANGE 2" action.

TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The first, third, and fifth triggered heals each award 1 XP.

---

# Level 9 cards

## Blind Destruction

**File:** `gh-blind-destruction.png`  
**Level:** 9  
**Initiative:** 74

### TOP

```text
ATTACK 4
RANGE 4
All allies and enemies adjacent to the target suffer damage equal to the damage the target suffered.
```

The target itself is not adjacent to itself and therefore does not suffer the additional damage. The copied value is the final damage suffered by the target, after Shield and other prevention effects.

`ENHANCEABLE: RANGE`

### BOTTOM

```text
MOVE 4

All allies and enemies adjacent to any hexes you enter during the movement suffer 2 damage.
INFUSE EARTH
```

A figure suffers this damage at most once, even if it is adjacent to multiple entered hexes. SELF's starting hex is not a hex entered during the movement.

`ENHANCEABLE: MOVE`

---

## Pulverize

**File:** `gh-pulverize.png`  
**Level:** 9  
**Initiative:** 31

### TOP

```text
ATTACK 3
PUSH 2
Target all adjacent enemies
You may push the targets into hexes containing obstacles. In each case, the obstacle is destroyed, the target suffers 2 damage, and you gain 1 experience.
[LOSS]
```

Each target and destroyed obstacle is resolved separately.

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
ATTACK 2
Target all adjacent enemies

MOVE 5
JUMP
XP +2

ATTACK 3
Target all adjacent enemies

[LOSS]
```

These are three separate abilities in the listed order; either attack may be skipped independently.

`ENHANCEABLE: first ATTACK, second ATTACK`

---

# File checklist

```text
gh-avalanche.png
gh-backup-ammunition.png
gh-blind-destruction.png
gh-blunt-force.png
gh-brutal-momentum.png
gh-cataclysm.png
gh-ch-back.png
gh-clear-the-way.png
gh-crater.png
gh-crushing-grasp.png
gh-dig-pit.png
gh-dirt-tornado.png
gh-earthen-clod.png
gh-explosive-punch.png
gh-forceful-storm.png
gh-heaving-swing.png
gh-kinetic-assault.png
gh-lumbering-bash.png
gh-massive-boulder.png
gh-meteor.png
gh-natures-lift.png
gh-opposing-strike.png
gh-petrify.png
gh-pulverize.png
gh-rock-slide.png
gh-rock-tunnel.png
gh-rocky-end.png
gh-rumbling-advance.png
gh-sentient-growth.png
gh-stone-pummel.png
gh-unstable-upheaval.png
```

---

# Source cross-checks used

- Worldhaven image folder: `images/character-ability-cards/gloomhaven/ch/`
- Gloomhaven Card Browser card metadata and filenames
- HavenPlanner Cragheart metadata and enhancement-slot data
- Gloomy Cragheart structured ability definitions
- Official Cragheart FAQ clarifications
- Full printed action wording checked against all 30 fronts in the three supplied card images
- Original enhancement annotations and AOE data retained
