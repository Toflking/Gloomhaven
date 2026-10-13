# Gloomhaven – Nightshroud (NS) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/ns/`  
**Files covered:** 29 PNG files = 28 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Kill, conditional, and other special effects use the full printed wording. `AOE pattern below` refers to the separate preserved area diagram.

## Elements

```text
INFUSE DARK
CONSUME DARK => effect
CONSUME LIGHT => effect
CONSUME ANY => effect
CONSUME DARK + ANY_OTHER => effect
```

A consume line is conditional: the listed effect is obtained only when the required element or elements are consumed.

`ANY_OTHER` means one element other than `DARK`.

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
ENHANCEABLE: RANGE
```

## Invisibility conditions

A conditional bonus requiring invisibility is evaluated when the relevant attack or action is performed. Becoming invisible later in the same card half does not retroactively satisfy it.

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

## `gh-ns-back.png`

Nightshroud / Eclipse ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Black Knives

**File:** `gh-black-knives.png`  
**Level:** 1  
**Initiative:** 44

### TOP

```text
During your next four Attack actions while
INVISIBLE, perform an “ATTACK 2,
RANGE 3” as the final part of the action.
TRACK: [XP+1] [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying attack actions consume uses. The added ranged attack is part of the triggering attack action and does not trigger this effect again.

### BOTTOM

```text
ATTACK 2
MUDDLE
XP +1
```

---

## Cloak of Shade

**File:** `gh-cloak-of-shade.png`  
**Level:** 1  
**Initiative:** 13

### TOP

```text
ATTACK 1
INFUSE DARK
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 3
CONSUME DARK: INVISIBLE
Self
XP +1
```

---

## Dancing Shadows

**File:** `gh-dancing-shadows.png`  
**Level:** 1  
**Initiative:** 09

### TOP

```text
MOVE 3

ATTACK 1
```

`ENHANCEABLE: MOVE`

### BOTTOM

```text
All attacks targeting you
gain Disadvantage this round.
INFUSE DARK
[ROUND]
```

---

## Doomed Breeze

**File:** `gh-doomed-breeze.png`  
**Level:** 1  
**Initiative:** 15

### TOP

```text
ATTACK 1
RANGE 3
MUDDLE
INFUSE DARK
XP +1
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 3

CURSE
Target one adjacent enemy
```

The curse application is a separate non-attack ability.

---

## Empowering Void

**File:** `gh-empowering-void.png`  
**Level:** 1  
**Initiative:** 21

### TOP

```text
ATTACK 2
CONSUME DARK: Double the value of all your
MOVE abilities played during
the remainder of this turn, XP +1
```

### BOTTOM

```text
MOVE 2
CONSUME DARK: Double the value of all your
ATTACK abilities played during
the remainder of this turn, XP +1
```

The bottom movement occurs before the consume effect and is therefore not itself modified by that effect.

---

## Enervating Wound

**File:** `gh-enervating-wound.png`  
**Level:** 1  
**Initiative:** 77

### TOP

```text
ATTACK 4
WOUND

HEAL 4
Self
INFUSE DARK
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, HEAL`

### BOTTOM

```text
MOVE 4
```

---

## Silent Force

**File:** `gh-silent-force.png`  
**Level:** 1  
**Initiative:** 91

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience
if you are INVISIBLE.
```

### BOTTOM

```text
MOVE 6

LOOT 1
[LOSS]
```

`ENHANCEABLE: MOVE`

---

## Smoke Step

**File:** `gh-smoke-step.png`  
**Level:** 1  
**Initiative:** 28

### TOP

```text
MOVE 1

ATTACK 2
CONSUME DARK: +2 MOVE, +2 ATTACK, XP +1
```

The consume effect can make the action `MOVE 3`, followed by `ATTACK 4`.

### BOTTOM

```text
MOVE 1
INFUSE DARK
```

`ENHANCEABLE: MOVE (2 marks)`

---

## Spirit of the Night

**File:** `gh-spirit-of-the-night.png`  
**Level:** 1  
**Initiative:** 84

### TOP

```text
ATTACK 3
CONSUME DARK: Kill one normal
target instead, XP +1
```

Elite and boss targets cannot be executed by this effect.

### BOTTOM

```text
MOVE 6
INFUSE DARK
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE (2 marks)`

---

# Level X cards

## Concealed Dominance

**File:** `gh-concealed-dominance.png`  
**Level:** X  
**Initiative:** 35

### TOP

```text
ATTACK 4
CONSUME DARK: AOE pattern below, XP +2
[LOSS]
```

Exact relative AOE:

```text
NIGHTSHROUD: [0, 0]
TARGETS: [[1, -1], [1, 0], [0, 1], [2, -1], [2, 0], [1, 1]]
```

The six attacked hexes form a three-row cluster extending from `S`. The entire pattern may be rotated.

### BOTTOM

```text
MOVE 4
CONSUME DARK: Teleport to any unoccupied
hex instead.
[LOSS]
```

---

## Dark Cloud

**File:** `gh-dark-cloud.png`  
**Level:** X  
**Initiative:** 74

### TOP

```text
ATTACK 3
CURSE
```

### BOTTOM

```text
MUDDLE
Target all adjacent enemies
INFUSE DARK
```

The muddle application is not an attack.

---

## Wings of the Night

**File:** `gh-wings-of-the-night.png`  
**Level:** X  
**Initiative:** 24

### TOP

```text
ATTACK 1

INVISIBLE
Self
```

The invisibility is applied after the attack and therefore does not affect that attack.

### BOTTOM

```text
Before your next four Attack actions,
perform a “MOVE 2” action.
TRACK: [XP+1] [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

The added movement is performed before the triggering attack action.

---

# Level 2 cards

## Prepare for the Kill

**File:** `gh-prepare-for-the-kill.png`  
**Level:** 2  
**Initiative:** 07

### TOP

```text
ATTACK 2
INFUSE DARK
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 2
INFUSE DARK
```

`ENHANCEABLE: MOVE (2 marks)`

---

## Soulfire

**File:** `gh-soulfire.png`  
**Level:** 2  
**Initiative:** 85

### TOP

```text
ATTACK 1
RANGE 3
WOUND
Add STUN and gain 1 experience
if you are INVISIBLE.
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
ATTACK 6
CURSE
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

---

# Level 3 cards

## Armor of the Night

**File:** `gh-armor-of-the-night.png`  
**Level:** 3  
**Initiative:** 23

### TOP

```text
ATTACK 4
CONSUME DARK: HEAL 4
Self
XP +1
```

### BOTTOM

```text
SHIELD 1
Self
INFUSE DARK
[ROUND]
```

`ENHANCEABLE: SHIELD (2 marks)`

---

## Terror Blade

**File:** `gh-terror-blade.png`  
**Level:** 3  
**Initiative:** 17

### TOP

```text
ATTACK 3
PUSH 3
```

`ENHANCEABLE: PUSH`

### BOTTOM

```text
MOVE 4

All attacks targeting you
gain Disadvantage this round.
[ROUND]
```

---

# Level 4 cards

## Grim Sustenance

**File:** `gh-grim-sustenance.png`  
**Level:** 4  
**Initiative:** 29

### TOP

```text
ATTACK 2
Add +2 ATTACK and gain 1 experience
if you are INVISIBLE.

INVISIBLE
Self
```

The invisibility is applied after the attack. It does not activate the attack's own conditional bonus unless SELF was already invisible.

### BOTTOM

```text
LOOT 1
INFUSE DARK
```

---

## Nightfall

**File:** `gh-nightfall.png`  
**Level:** 4  
**Initiative:** 73

### TOP

```text
At the end of your next three turns
where DARK is not Strong, generate DARK.
TRACK: [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

A turn ending while `DARK` is already Strong does not satisfy the trigger and does not consume a use.

### BOTTOM

```text
MOVE 3

ATTACK 2
CONSUME LIGHT: +1 ATTACK, INFUSE DARK, XP +1
```

The consume effect can make the attack `ATTACK 3`.

---

# Level 5 cards

## Black Arrow

**File:** `gh-black-arrow.png`  
**Level:** 5  
**Initiative:** 11

### TOP

```text
ATTACK 4
RANGE 4
MUDDLE
CURSE
XP +1
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
INVISIBLE
Self
CONSUME DARK: ATTACK 3
RANGE 3
XP +1
```

The ranged attack is a separate ability performed after becoming invisible.

---

## Claws of the Night

**File:** `gh-claws-of-the-night.png`  
**Level:** 5  
**Initiative:** 26

### TOP

```text
ATTACK 2
POISON
AOE pattern below
CONSUME ANY: +1 ATTACK, INFUSE DARK, XP +1
```

Exact relative AOE:

```text
NIGHTSHROUD: [0, 0]
TARGETS: [[1, -1], [1, 0]]
```

The attack covers two adjacent target hexes that are also adjacent to each other. The pattern may be rotated.

`ENHANCEABLE: ATTACK (applies to both AOE targets)`

### BOTTOM

```text
During your next four Move actions while
INVISIBLE, perform an “ATTACK 2, Target all
adjacent enemies” as the final part of the action.
TRACK: [XP+1] [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying move actions consume uses. The added attacks are part of the triggering move action.

---

# Level 6 cards

## Swallowed by Fear

**File:** `gh-swallowed-by-fear.png`  
**Level:** 6  
**Initiative:** 90

### TOP

```text
ATTACK 2
CONSUME DARK + ANY_OTHER: Kill one normal or elite
target instead, XP +1
```

Boss targets cannot be executed by this effect.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 3

PUSH 2
Target all adjacent enemies
```

The push is a separate non-attack ability.

---

## Unseen Dread

**File:** `gh-unseen-dread.png`  
**Level:** 6  
**Initiative:** 05

### TOP

```text
ATTACK 3
INFUSE DARK
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 6
Jump
```

`ENHANCEABLE: MOVE`

---

# Level 7 cards

## Eyes of the Night

**File:** `gh-eyes-of-the-night.png`  
**Level:** 7  
**Initiative:** 35

### TOP

```text
Gain Advantage on all your attacks.
You can target INVISIBLE enemies.
XP +2
[PERSISTENT, LOSS]
```

### BOTTOM

```text
MOVE 5
CONSUME LIGHT: MUDDLE
Target all adjacent enemies
INFUSE DARK
XP +1
```

The muddle application is not an attack.

---

## Quiet Frenzy

**File:** `gh-quiet-frenzy.png`  
**Level:** 7  
**Initiative:** 19

### TOP

```text
ATTACK 3
Add +2 ATTACK and gain 1 experience
if you are INVISIBLE.
CONSUME DARK: +3 ATTACK, XP +1
```

The two modifiers are independent and may stack, producing `ATTACK 8` and awarding 2 XP.

### BOTTOM

```text
ATTACK 3

MOVE 3
```

These are two separate abilities in the printed order.

---

# Level 8 cards

## Gloom Darts

**File:** `gh-gloom-darts.png`  
**Level:** 8  
**Initiative:** 78

### TOP

```text
ATTACK 3
RANGE 4
POISON
CURSE
CONSUME DARK: TARGET 3, XP +1
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
MOVE 3
INFUSE DARK
```

`ENHANCEABLE: MOVE (2 marks)`

---

## Lurking Ruin

**File:** `gh-lurking-ruin.png`  
**Level:** 8  
**Initiative:** 18

### TOP

```text
ATTACK 5
Add POISON, MUDDLE, WOUND
and gain 1 experience if you are INVISIBLE.
```

### BOTTOM

```text
MOVE 3

INVISIBLE
Self
```

---

# Level 9 cards

## Angel of Death

**File:** `gh-angel-of-death.png`  
**Level:** 9  
**Initiative:** 38

### TOP

```text
ATTACK 4
Target all adjacent enemies
CONSUME DARK: Kill all normal targets instead, XP +2
XP +2
[LOSS]
```

Elite and boss targets still resolve the normal `ATTACK 4`.

Consuming `DARK` awards 2 conditional XP in addition to the action's unconditional 2 XP, for a total of 4 XP.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 5
Jump
CONSUME DARK: Kill one adjacent normal enemy, XP +1
```

`ENHANCEABLE: MOVE`

---

## Voice of the Night

**File:** `gh-voice-of-the-night.png`  
**Level:** 9  
**Initiative:** 06

### TOP

```text
On your next five attacks while INVISIBLE,
add STUN to the attack.
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Only qualifying individual attacks consume uses. In a multi-target attack action, each individual attack is evaluated separately.

### BOTTOM

```text
MOVE 2

ATTACK 2
CONSUME ANY: INVISIBLE
Self
INFUSE DARK
XP +1
```

The invisibility is applied after the attack and therefore does not affect that attack.

`ENHANCEABLE: MOVE, ATTACK`

---

# File checklist

```text
gh-ns-back.png
gh-angel-of-death.png
gh-armor-of-the-night.png
gh-black-arrow.png
gh-black-knives.png
gh-claws-of-the-night.png
gh-cloak-of-shade.png
gh-concealed-dominance.png
gh-dancing-shadows.png
gh-dark-cloud.png
gh-doomed-breeze.png
gh-empowering-void.png
gh-enervating-wound.png
gh-eyes-of-the-night.png
gh-gloom-darts.png
gh-grim-sustenance.png
gh-lurking-ruin.png
gh-nightfall.png
gh-prepare-for-the-kill.png
gh-quiet-frenzy.png
gh-silent-force.png
gh-smoke-step.png
gh-soulfire.png
gh-spirit-of-the-night.png
gh-swallowed-by-fear.png
gh-terror-blade.png
gh-unseen-dread.png
gh-voice-of-the-night.png
gh-wings-of-the-night.png
```

---

# Sources used

- Visual comparison with the three supplied Nightshroud card screenshots (015716, 015719, and 015722), covering all 28 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, and explanatory notes outside the action blocks.
