# Gloomhaven – Soothsinger (SS) Ability Cards

**Scope:** [`images/character-ability-cards/gloomhaven/ss/`](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/ss)  
**Files covered:** 29 PNG files = 28 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

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
- An omitted `RANGE` on an attack means the attack is melee.
- `SELF` means the acting Soothsinger.
- `ALLY` does not include `SELF`.
- `ADJACENT` means a distance of exactly 1 hex.
- `TARGET n` means up to `n` different valid targets.

## Common keywords

```text
ATTACK n
ATTACK +n
MOVE n
MOVE n, JUMP
HEAL n [SELF | RANGE n]
SHIELD n
RETALIATE n [RANGE n]
LOOT n
PUSH n
BLESS
CURSE
DISARM
IMMOBILIZE
MUDDLE
STRENGTHEN
STUN
WOUND
ADVANTAGE
DISADVANTAGE
```

`ATTACK +0` preserves the card's printed modifier notation for an attack performed by a controlled enemy. It is not an `ATTACK 0`.

## Granted and controlled actions

```text
One ally may perform ATTACK n.
One ally may perform MOVE n.

FORCE one enemy:
- perform MOVE n
- SELF controls the action

FORCE one enemy:
- perform ATTACK +0
- target another enemy
- SELF controls the action
```

- A figure that “may perform” an action can decline it.
- When an enemy is forced to perform an action and the card says that `SELF` controls it, `SELF` chooses the movement or the valid target as applicable.
- A fixed forced attack such as `ATTACK 3` uses the printed fixed value.
- A forced `ATTACK +0` uses the acting enemy's normal attack value with a `+0` modifier.

## Songs

```text
[SONG, PERSISTENT]

While active:
- listed Song effect

At the beginning of each of SELF's turns:
- XP +1

When SELF plays another SONG:
- discard this card
```

- A Song remains in the active area and is **not** a loss unless a separate loss symbol is printed.
- `SELF` may voluntarily discard an active Song.
- Normally, playing another Song discards the currently active Song.
- Song effects that say “all allies” do not affect `SELF`.
- Song experience is gained only when the Song was already active at the beginning of `SELF`'s turn.
- `Melody and Harmony` changes the normal one-Song limit.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[SONG, PERSISTENT]` — remains active under the Song replacement rule.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[ROUND, LOSS]` — remains active for the round and is then lost.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [ ] [ ] [XP+1] [ ] [XP+1] -> end
```

## Dynamic auras

`AURA` means affected figures are evaluated continuously rather than only when the action is first played.

Example:

```text
AURA [ROUND]:
- Every ally currently ADJACENT to SELF has SHIELD 1.
```

An ally gains or loses that benefit as it enters or leaves the specified area.

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: MOVE, SHIELD
ENHANCEABLE: one AOE hex
```

When an action grants another figure an ability, an enhancement mark on that ability is still recorded as `ATTACK`, `MOVE`, and so on.

## AOE diagrams

- `T` = affected hex.
- A ranged pattern may be rotated and positioned according to the normal ranged-AOE rules.
- Every enemy in an affected hex is affected.
- For an AOE attack, resolve a separate attack against every affected enemy.

Three-hex triangular pattern:

```text
TRIANGLE_3

      T
    T   T
```

Seven-hex compact pattern:

```text
HEX_CLUSTER_7

      T   T
    T   T   T
      T   T
```

---

# Card back

## `gh-ss-back.png`

Soothsinger / Music Note ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Call to Action

**File:** `gh-call-to-action.png`  
**Level:** 1  
**Initiative:** 85

### TOP

```text
STRENGTHEN
Affect all allies
```


### BOTTOM

```text
One ally within RANGE 3 may perform
ATTACK 4
```

`ENHANCEABLE: ATTACK`

---

## Defensive Ditty

**File:** `gh-defensive-ditty.png`  
**Level:** 1  
**Initiative:** 09

### TOP

```text
SONG
SHIELD 1
Affect all allies

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

### BOTTOM

```text
The next time any ally within RANGE 2 suffers damage this round, they suffer no damage instead.

XP +1
[ROUND]
```

Only the first qualifying damage source is prevented.

---

## Marching Beat

**File:** `gh-marching-beat.png`  
**Level:** 1  
**Initiative:** 32

### TOP

```text
One ally within RANGE 3 may perform
MOVE 4
```

`ENHANCEABLE: MOVE`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Power Ballad

**File:** `gh-power-ballad.png`  
**Level:** 1  
**Initiative:** 19

### TOP

```text
SONG
All allies add +1 ATTACK to one of their attacks each turn.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

The bonus is limited per ally and per turn. It applies to one individual attack, not to every attack in one attack action.

### BOTTOM

```text
MOVE 2

STRENGTHEN
Affect all adjacent allies

XP +1
```

`ENHANCEABLE: MOVE, STRENGTHEN`

---

## Singing Arrow

**File:** `gh-singing-arrow.png`  
**Level:** 1  
**Initiative:** 89

### TOP

```text
CURSE
RANGE 3
AOE TRIANGLE_3
```

TARGETS: [[0, 0], [1, -1], [1, 0]]

`ENHANCEABLE: one AOE hex ([0, -1])`

Every enemy in the three affected hexes is cursed.

### BOTTOM

```text
ATTACK 1
RANGE 3
WOUND
```

`ENHANCEABLE: ATTACK, RANGE`

---

## Song of Speed

**File:** `gh-song-of-speed.png`  
**Level:** 1  
**Initiative:** 05

### TOP

```text
SONG
All allies add +1 MOVE to all their MOVE actions, and +1 RANGE to all their ranged attacks.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

If one action contains multiple separate `MOVE` abilities, the `+1 MOVE` is applied to only one of those abilities.

### BOTTOM

```text
MUDDLE
Target all enemies within RANGE 4
XP +1
```

---

## Throw Voice

**File:** `gh-throw-voice.png`  
**Level:** 1  
**Initiative:** 44

### TOP

```text
DISARM
RANGE 3
AOE TRIANGLE_3
```
TARGETS: [[0, 0], [1, -1], [1, 0]]

`ENHANCEABLE: one AOE hex ([0, -1])`

Every enemy in the three affected hexes is disarmed.

### BOTTOM

```text
RANGE 3
AOE TRIANGLE_3
Force all enemies in the targeted area to perform a "MOVE 1" action with you controlling the actions.

XP +1
[LOSS]
```

Each affected enemy performs a separate controlled `MOVE 1` action.

TARGETS: [[0, 0], [1, -1], [1, 0]]

`ENHANCEABLE: one AOE hex ([0, -1])`

---

## Tuning the Outcome

**File:** `gh-tuning-the-outcome.png`  
**Level:** 1  
**Initiative:** 78

### TOP

```text
BLESS
Affect all adjacent allies
```

`ENHANCEABLE: BLESS`

### BOTTOM

```text
MOVE 3

CURSE
RANGE 3
```

`ENHANCEABLE: MOVE, CURSE`

The Curse is a separate ability performed after the movement.

---

## Warding Dagger

**File:** `gh-warding-dagger.png`  
**Level:** 1  
**Initiative:** 56

### TOP

```text
ATTACK 3
IMMOBILIZE
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4

SHIELD 2
Affect all adjacent allies

XP +1
[ROUND, LOSS]
```

`ENHANCEABLE: MOVE, SHIELD`

The Shield is evaluated continuously: an ally has it only while adjacent to `SELF`.

---

# Level X cards

## Nimble Knife

**File:** `gh-nimble-knife.png`  
**Level:** X  
**Initiative:** 27

### TOP

```text
ATTACK 2
CURSE

BLESS
Affect one adjacent ally
```

`ENHANCEABLE: ATTACK, BLESS`

The Bless is a separate ability after the attack.

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Unending Chant

**File:** `gh-unending-chant.png`  
**Level:** X  
**Initiative:** 51

### TOP

```text
The next five times you CURSE an enemy, CURSE them an additional time.

TRACK: [ ] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Each individual enemy cursed consumes one use.  
The additional Curse created by this effect does not trigger the effect again.

### BOTTOM

```text
MOVE 2

SHIELD 1
Affect all adjacent allies
[ROUND]
```

`ENHANCEABLE: SHIELD`

The Shield is evaluated continuously: an ally has it only while adjacent to `SELF`.

---

## Wistful Wounding

**File:** `gh-wistful-wounding.png`  
**Level:** X  
**Initiative:** 16

### TOP

```text
SONG
All allies add WOUND to all their attacks.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

### BOTTOM

```text
ATTACK 1
RANGE 4

LOOT 1
XP +1
```

`ENHANCEABLE: RANGE`

The Loot is a separate ability after the attack.

---

# Level 2 cards

## Change Tempo

**File:** `gh-change-tempo.png`  
**Level:** 2  
**Initiative:** 91

### TOP

```text
IMMOBILIZE
Target all enemies within RANGE 3
```

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

## Soothing Lullaby

**File:** `gh-soothing-lullaby.png`  
**Level:** 2  
**Initiative:** 11

### TOP

```text
SONG
All allies may perform a "HEAL 1, SELF" action at the beginning of each of their turns.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

Each ally's heal targets that ally, not the Soothsinger.

### BOTTOM

```text
HEAL 3
RANGE 4
XP +1
```

`ENHANCEABLE: HEAL`

---

# Level 3 cards

## Crippling Chorus

**File:** `gh-crippling-chorus.png`  
**Level:** 3  
**Initiative:** 62

### TOP

```text
STUN
Target all enemies within RANGE 3

XP +2
[LOSS]
```

`ENHANCEABLE: STUN`

### BOTTOM

```text
MOVE 3

DISARM
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, DISARM`

The Disarm is a separate ability after the movement.

---

## Echoing Aria

**File:** `gh-echoing-aria.png`  
**Level:** 3  
**Initiative:** 08

### TOP

```text
SONG
All allies gain RETALIATE 2, RANGE 2.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

### BOTTOM

```text
SHIELD 2
Affect all allies within RANGE 3

XP +1
[ROUND]
```

The Shield is evaluated continuously: an ally gains or loses it as it enters or leaves `RANGE 3`.

---

# Level 4 cards

## Disorienting Dirge

**File:** `gh-disorienting-dirge.png`  
**Level:** 4  
**Initiative:** 14

### TOP

```text
SONG
All enemies gain DISADVANTAGE on all their attacks.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

### BOTTOM

```text
CURSE
Target all enemies within RANGE 3

BLESS
Affect all allies within RANGE 3

XP +1
```

`ENHANCEABLE: CURSE, BLESS`

Curse and Bless are two separate abilities.

---

## Inspiring Anthem

**File:** `gh-inspiring-anthem.png`  
**Level:** 4  
**Initiative:** 50

### TOP

```text
Any one ally may perform
MOVE 5
```

`ENHANCEABLE: MOVE`

There is no printed range restriction.

### BOTTOM

```text
Any one ally may perform
ATTACK 5
```

`ENHANCEABLE: ATTACK`

There is no printed range restriction.

---

# Level 5 cards

## Melody and Harmony

**File:** `gh-melody-and-harmony.png`  
**Level:** 5  
**Initiative:** 78

### TOP

```text
You may have two SONGS active at once, but you only gain experience from one.

If a third SONG is played, discard one of the others.
[PERSISTENT, LOSS]
```

This changes the active-Song limit but does not itself count as a Song.

### BOTTOM

```text
MOVE 3

ATTACK 3
RANGE 3
```

`ENHANCEABLE: MOVE, ATTACK`

The attack is a separate ability after the movement.

---

## Mobilizing Measure

**File:** `gh-mobilizing-measure.png`  
**Level:** 5  
**Initiative:** 06

### TOP

```text
SONG
All allies may perform a "MOVE 1" action before any of their ATTACK actions.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

The granted `MOVE 1` is a separate action. It is available only when the ally can provably perform at least one attack after that movement as part of the corresponding Attack action.

### BOTTOM

```text
MOVE 6
XP +1
```

`ENHANCEABLE: MOVE`

---

# Level 6 cards

## Provoke Terror

**File:** `gh-provoke-terror.png`  
**Level:** 6  
**Initiative:** 60

### TOP

```text
STUN
RANGE 4
AOE TRIANGLE_3
```
TARGETS: [[0, 0], [1, -1], [1, 0]]

`ENHANCEABLE: one AOE hex ([0, -1])`

Every enemy in the three affected hexes is stunned.

### BOTTOM

```text
ATTACK 4
RANGE 4
PUSH 3
```

`ENHANCEABLE: ATTACK, PUSH`

---

## Pull the Strings

**File:** `gh-pull-the-strings.png`  
**Level:** 6  
**Initiative:** 20

### TOP

```text
SONG
At the beginning of each of your turns, you may force one enemy within RANGE 5 to perform a "MOVE 2" action with you controlling the action.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

### BOTTOM

```text
Force one enemy within RANGE 5 to perform
ATTACK +0
targeting another enemy with you controlling the action.

XP +1
```

The forced enemy uses its own normal attack value with a `+0` modifier.

---

# Level 7 cards

## Booming Proclamation

**File:** `gh-booming-proclamation.png`  
**Level:** 7  
**Initiative:** 65

### TOP

```text
ATTACK 1
Target all enemies within RANGE 2
PUSH 2
```

A separate attack is resolved against every enemy within range.

`ENHANCEABLE: ATTACK, PUSH`

### BOTTOM

```text
All allies within RANGE 3 may perform
ATTACK 3

XP +1
[LOSS]
```

`ENHANCEABLE: ATTACK`

Every qualifying ally may perform one separate attack.

---

## Nightmare Serenade

**File:** `gh-nightmare-serenade.png`  
**Level:** 7  
**Initiative:** 13

### TOP

```text
SONG
At the beginning of your turns, CURSE one enemy within RANGE 5.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

### BOTTOM

```text
ATTACK 1
RANGE 3
AOE TRIANGLE_3
CURSE
XP +1
```
TARGETS: [[0, 0], [1, -1], [1, 0]]

`ENHANCEABLE: ATTACK, one AOE hex ([0, -1])`

Resolve a separate attack against every enemy in the affected area.

---

# Level 8 cards

## Commanding Presence

**File:** `gh-commanding-presence.png`  
**Level:** 8  
**Initiative:** 59

### TOP

```text
One ally within RANGE 3 may perform
ATTACK 2
Target all adjacent enemies
```

`ENHANCEABLE: ATTACK`

The chosen ally resolves a separate attack against every enemy adjacent to that ally.

### BOTTOM

```text
RANGE 2
AOE HEX_CLUSTER_7
Force all enemies in the targeted area to perform a "MOVE 2" action with you controlling the actions.

XP +1
[LOSS]
```
TARGETS: [[0, 0], [1, -1], [1, 0], [0, 1], [0, -1], [-1, 0], [-1, 1]]

Each affected enemy performs a separate controlled `MOVE 2` action.

---

## Tranquil Trill

**File:** `gh-tranquil-trill.png`  
**Level:** 8  
**Initiative:** 10

### TOP

```text
SONG
All allies may perform a "HEAL 2, SELF" action at the beginning of each of their turns.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

Each ally's heal targets that ally, not the Soothsinger.

### BOTTOM

```text
DISARM
Target all adjacent enemies
XP +1
```

`ENHANCEABLE: DISARM`

---

# Level 9 cards

## Captivating Performance

**File:** `gh-captivating-performance.png`  
**Level:** 9  
**Initiative:** 25

### TOP

```text
SONG
All allies add +1 ATTACK to all their attacks.

Gain 1 experience at the beginning of each of your turns.
When another SONG is played, discard this card.
[SONG, PERSISTENT]
```

The bonus applies to every individual attack, including every attack of a multi-target or AOE attack action.

### BOTTOM

```text
STUN
RANGE 3
TARGET 3
XP +1
```

`ENHANCEABLE: STUN, TARGET`

Up to three different enemies may be stunned.

---

## Shadow Puppets

**File:** `gh-shadow-puppets.png`  
**Level:** 9  
**Initiative:** 52

### TOP

```text
MOVE 4
JUMP

Force all enemies moved through to perform an "ATTACK 3" action targeting another enemy with you controlling the actions.
```

`ENHANCEABLE: MOVE`

Each affected enemy performs one separate melee `ATTACK 3` action after the movement.

### BOTTOM

```text
Two allies within RANGE 3 may perform
ATTACK 4
```

---

# File checklist

```text
gh-ss-back.png
gh-booming-proclamation.png
gh-call-to-action.png
gh-captivating-performance.png
gh-change-tempo.png
gh-commanding-presence.png
gh-crippling-chorus.png
gh-defensive-ditty.png
gh-disorienting-dirge.png
gh-echoing-aria.png
gh-inspiring-anthem.png
gh-marching-beat.png
gh-melody-and-harmony.png
gh-mobilizing-measure.png
gh-nightmare-serenade.png
gh-nimble-knife.png
gh-power-ballad.png
gh-provoke-terror.png
gh-pull-the-strings.png
gh-shadow-puppets.png
gh-singing-arrow.png
gh-song-of-speed.png
gh-soothing-lullaby.png
gh-throw-voice.png
gh-tranquil-trill.png
gh-tuning-the-outcome.png
gh-unending-chant.png
gh-warding-dagger.png
gh-wistful-wounding.png
```

---

# Source cross-checks used

- Worldhaven image folder: `images/character-ability-cards/gloomhaven/ss/`
- Gloomhaven Card Browser card metadata and filenames
- HavenPlanner Soothsinger enhancement-slot metadata
- Gloomy Soothsinger structured ability definitions
- Full printed action wording checked against all 28 fronts in the three supplied card images
- Gloomhaven “Music Note” FAQ cross-checks for Songs, one-use prevention, aura timing, `Song of Speed`, `Unending Chant`, and `Mobilizing Measure`
- Original metadata, enhancement annotations, AOE data, tracks, and explanatory rules retained
