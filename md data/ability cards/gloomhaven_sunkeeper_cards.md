# Gloomhaven – Sunkeeper (SK) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/sk/`  
**Source folder:** `https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sk`  
**Files covered:** 31 PNG files = 30 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, elements, tracks, duration, loss, and enhancement marks use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Recovery and kill effects use the full printed wording.

## Elements

```text
INFUSE LIGHT
CONSUME LIGHT => effect
CONSUME DARK => effect
CONSUME ANY_ELEMENT => effect
```

A consume line is conditional: the listed effect is obtained only when the specified element is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is placed in the lost pile for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[ROUND, LOSS]` — active for the round, then moved to the lost pile.
- `[UNRECOVERABLE]` — once lost, the card cannot be recovered by an ability, regardless of how it became lost.
- `TRACK` — advance one use from left to right whenever its listed trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
```

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: ATTACK (2 marks), MOVE
ENHANCEABLE: SHIELD, STRENGTHEN
```

## Hex adjacency

Relative directions around the acting figure:

```text
          6   1
        5   S   2
          4   3
```

- `S` = acting figure / origin hex
- Every numbered position is adjacent to `S`.

---

# Card back

## `gh-sk-back.png`

Sunkeeper / Sun ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Brilliant Prayer

**File:** `gh-brilliant-prayer.png`  
**Level:** 1  
**Initiative:** 27

### TOP

```text
One adjacent ally may Recover
one of their lost cards.
XP +2
[LOSS, UNRECOVERABLE]
```

### BOTTOM

```text
HEAL 5
RANGE 3
INFUSE LIGHT
XP +2
[LOSS, UNRECOVERABLE]
```

`ENHANCEABLE: HEAL (2 marks)`

The unrecoverable symbol applies to this card regardless of which loss action was used or how the card entered the lost pile.

---

## Cautious Advance

**File:** `gh-cautious-advance.png`  
**Level:** 1  
**Initiative:** 23

### TOP

```text
SHIELD 1
Self
XP +1
[ROUND]
```

`ENHANCEABLE: SHIELD`

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Dazzling Charge

**File:** `gh-dazzling-charge.png`  
**Level:** 1  
**Initiative:** 57

### TOP

```text
HEAL 3
RANGE 3
INFUSE LIGHT
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
MOVE 5

STUN
Target one adjacent enemy

INFUSE LIGHT
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE, STUN`

The Stun is a separate non-attack ability performed after the movement.

---

## Defensive Stance

**File:** `gh-defensive-stance.png`  
**Level:** 1  
**Initiative:** 65

### TOP

```text
ATTACK 4
CONSUME LIGHT: +1 ATTACK, Advantage, XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
SHIELD 1
Self
Deduct -1 MOVE from all your moves.
XP +1
[PERSISTENT, LOSS]
```

`ENHANCEABLE: SHIELD`

The movement penalty applies to every Move ability performed by SELF while the card remains active.

---

## Empowering Command

**File:** `gh-empowering-command.png`  
**Level:** 1  
**Initiative:** 32

### TOP

```text
One adjacent ally may Recover
up to two of their discarded cards.
CONSUME LIGHT: Up to three discarded
cards instead, XP +1
```

### BOTTOM

```text
One adjacent ally may perform
ATTACK 4
```

`ENHANCEABLE: ATTACK`

---

## Hammer Blow

**File:** `gh-hammer-blow.png`  
**Level:** 1  
**Initiative:** 55

### TOP

```text
ATTACK 4
CONSUME LIGHT: +1 ATTACK, Advantage, XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Holy Strike

**File:** `gh-holy-strike.png`  
**Level:** 1  
**Initiative:** 85

### TOP

```text
ATTACK 2
STUN
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 5
RANGE 3
INFUSE LIGHT
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK`

---

## Lay on Hands

**File:** `gh-lay-on-hands.png`  
**Level:** 1  
**Initiative:** 90

### TOP

```text
Heal all damage on one adjacent ally.

Suffer 3 damage.

INFUSE LIGHT
XP +2
[LOSS]
```

### BOTTOM

```text
LOOT 1
```

---

## Protective Blessing

**File:** `gh-protective-blessing.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
BLESS and STRENGTHEN
Affect one ally within RANGE 3
```

### BOTTOM

```text
You and all adjacent allies suffer
no damage this round.
INFUSE LIGHT
XP +2
[ROUND, LOSS]
```

This prevents damage from every source, not merely attacks.

---

## Purifying Aura

**File:** `gh-purifying-aura.png`  
**Level:** 1  
**Initiative:** 21

### TOP

```text
On the next five melee attacks
targeting you, gain RETALIATE 2.
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The first, third, and fifth triggered attacks each award 1 XP.

### BOTTOM

```text
STRENGTHEN
Affect all adjacent allies
CONSUME LIGHT: Affect all allies within
RANGE 2 instead
```

`ENHANCEABLE: STRENGTHEN`

---

## Tactical Order

**File:** `gh-tactical-order.png`  
**Level:** 1  
**Initiative:** 29

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

# Level X cards

## Beacon of Light

**File:** `gh-beacon-of-light.png`  
**Level:** X  
**Initiative:** 36

### TOP

```text
HEAL 1
Affect self and all allies within RANGE 4
```

### BOTTOM

```text
At the end of your next five turns when
LIGHT is not Strong, generate LIGHT.
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The turn on which this action is activated is the first of the five turns. The first, third, and fifth uses each award 1 XP.

---

## Daybreak

**File:** `gh-daybreak.png`  
**Level:** X  
**Initiative:** 85

### TOP

```text
ATTACK 3
MUDDLE
CONSUME DARK: +1 ATTACK, INFUSE LIGHT, XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 2

BLESS
Affect one adjacent ally
```

`ENHANCEABLE: MOVE, BLESS`

---

## Glorious Bolt

**File:** `gh-glorious-bolt.png`  
**Level:** X  
**Initiative:** 39

### TOP

```text
HEAL 5
RANGE 3
CONSUME LIGHT: TARGET 2, XP +1
XP +1
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

Without consuming Light, the action awards 1 XP. With Light consumed, it heals up to two targets and awards 2 XP total.

### BOTTOM

```text
ATTACK 2
RANGE 3
MUDDLE
```

`ENHANCEABLE: ATTACK`

---

# Level 2 cards

## Practical Plans

**File:** `gh-practical-plans.png`  
**Level:** 2  
**Initiative:** 56

### TOP

```text
ATTACK 5
```

### BOTTOM

```text
MOVE 5
```

---

## Unwavering Mandate

**File:** `gh-unwavering-mandate.png`  
**Level:** 2  
**Initiative:** 72

### TOP

```text
One adjacent ally may immediately
perform one extra turn by playing
two cards from their hand.
XP +2
[LOSS]
```

The extra turn is resolved immediately before play returns to the normal initiative order.

### BOTTOM

```text
HEAL 3
RANGE 2
CONSUME LIGHT: TARGET 2, XP +1
```

`ENHANCEABLE: HEAL, RANGE`

---

# Level 3 cards

## Burning Flash

**File:** `gh-burning-flash.png`  
**Level:** 3  
**Initiative:** 51

### TOP

```text
ATTACK 4
Target all adjacent enemies
Gain 1 experience for each enemy targeted.
INFUSE LIGHT
[LOSS]
```

`ENHANCEABLE: ATTACK`

Experience is based on enemies targeted, not on enemies damaged or killed.

### BOTTOM

```text
HEAL 3
RANGE 3
CONSUME ANY_ELEMENT: +1 HEAL, INFUSE LIGHT
```

`ENHANCEABLE: HEAL, RANGE`

Exactly one available element is consumed for the conditional bonus.

---

## Mobilizing Axiom

**File:** `gh-mobilizing-axiom.png`  
**Level:** 3  
**Initiative:** 23

### TOP

```text
ATTACK 4
INFUSE LIGHT
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
All allies within RANGE 5 may perform
MOVE 4
XP +1
[LOSS]
```

Each affected ally independently chooses whether to perform the granted Move ability.

---

# Level 4 cards

## Engulfing Radiance

**File:** `gh-engulfing-radiance.png`  
**Level:** 4  
**Initiative:** 20

### TOP

```text
HEAL 3
Affect self and all allies
INFUSE LIGHT
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL`

The heal has no range restriction and affects every ally on the map for whom the ability is valid.

### BOTTOM

```text
All attacks targeting you or any
ally within RANGE 4 of you gain
Disadvantage this round.
INFUSE LIGHT
[ROUND]
```

The range condition is measured from SELF to the ally being targeted, not from the attacker.

---

## Righteous Strength

**File:** `gh-righteous-strength.png`  
**Level:** 4  
**Initiative:** 18

### TOP

```text
BLESS
Affect all adjacent allies
XP +1
```

`ENHANCEABLE: BLESS`

### BOTTOM

```text
MOVE 3

SHIELD 1
Self
[ROUND]
```

`ENHANCEABLE: MOVE, SHIELD`

---

# Level 5 cards

## Path of Glory

**File:** `gh-path-of-glory.png`  
**Level:** 5  
**Initiative:** 48

### TOP

```text
ATTACK 5
CONSUME LIGHT: +1 ATTACK, Advantage, XP +1
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 5

BLESS
Affect all allies moved through
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE`

An ally is affected when SELF enters and passes through that ally's occupied hex during this Move.

---

## Scales of Justice

**File:** `gh-scales-of-justice.png`  
**Level:** 5  
**Initiative:** 68

### TOP

```text
RETALIATE 2
Self
Deduct -1 ATTACK from all your attacks.
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: RETALIATE`

The penalty applies to every individual attack, including each attack in a multi-target or area attack action.

### BOTTOM

```text
MOVE 2
CONSUME LIGHT: +2 MOVE, XP +1

STUN
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, STUN`

The Stun is a separate non-attack ability after the movement and does not require Light to be consumed.

---

# Level 6 cards

## Illuminate the Target

**File:** `gh-illuminate-the-target.png`  
**Level:** 6  
**Initiative:** 91

### TOP

```text
ATTACK 5

STRENGTHEN
Affect all adjacent allies
XP +1
```

`ENHANCEABLE: ATTACK, STRENGTHEN`

The Strengthen is a separate ability after the attack.

### BOTTOM

```text
One adjacent ally may perform
ATTACK 6
```

`ENHANCEABLE: ATTACK`

---

## Supportive Chant

**File:** `gh-supportive-chant.png`  
**Level:** 6  
**Initiative:** 11

### TOP

```text
All allies add +1 ATTACK to
all their attacks this round.
XP +1
[ROUND]
```

### BOTTOM

```text
All allies add +2 MOVE to
all their moves this round.
[ROUND]
```

Neither half affects SELF because the card explicitly says “all allies.”

---

# Level 7 cards

## Bright Aegis

**File:** `gh-bright-aegis.png`  
**Level:** 7  
**Initiative:** 14

### TOP

```text
SHIELD 2
Self

RETALIATE 1
Self

INFUSE LIGHT
[ROUND]
```

`ENHANCEABLE: SHIELD, RETALIATE`

### BOTTOM

```text
One adjacent ally may Recover
one of their lost cards.
CONSUME LIGHT: Up to two lost cards instead, XP +1
XP +2
[LOSS, UNRECOVERABLE]
```

Without consuming Light, the action awards 2 XP. With Light consumed, it recovers up to two cards and awards 3 XP total.

---

## Weapon of Purity

**File:** `gh-weapon-of-purity.png`  
**Level:** 7  
**Initiative:** 73

### TOP

```text
During any of your Attack actions,
you may CONSUME LIGHT to add +2 ATTACK,
gain Advantage and 1 experience.
[PERSISTENT, LOSS]
```

The option may be used repeatedly while the card remains active, but each use requires a separate Light consumption.

### BOTTOM

```text
MOVE 3

ATTACK 3
```

`ENHANCEABLE: MOVE, ATTACK`

The movement and attack are separate abilities in the listed order.

---

# Level 8 cards

## Cleansing Force

**File:** `gh-cleansing-force.png`  
**Level:** 8  
**Initiative:** 25

### TOP

```text
ATTACK 6
STUN
WOUND
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 4
INFUSE LIGHT
```

`ENHANCEABLE: MOVE`

---

## Inspiring Sanctity

**File:** `gh-inspiring-sanctity.png`  
**Level:** 8  
**Initiative:** 79

### TOP

```text
One adjacent ally may perform
ATTACK 8
CONSUME LIGHT: Kill one normal or elite target
of the attack instead, XP +1
XP +2
[LOSS]
```

If the granted action gains additional targets, only one eligible target is executed; any other attacks are resolved normally.

### BOTTOM

```text
All allies within RANGE 3 may perform
ATTACK 4
Gain 1 experience for each attack performed.
[LOSS]
```

Experience is awarded per attack actually performed, not merely per ally offered the ability.

---

# Level 9 cards

## Angelic Ascension

**File:** `gh-angelic-ascension.png`  
**Level:** 9  
**Initiative:** 87

### TOP

```text
On your next four Attack actions, add
+3 ATTACK, WOUND, and gain Advantage.
TRACK: [XP+1] [XP+1] [XP+1] [XP+1] -> end
[PERSISTENT, LOSS]
```

Each qualifying attack action consumes one use and awards 1 XP, regardless of how many individual attacks it contains.

### BOTTOM

```text
MOVE 6
Jump
```

`ENHANCEABLE: MOVE`

---

## Divine Intervention

**File:** `gh-divine-intervention.png`  
**Level:** 9  
**Initiative:** 09

### TOP

```text
When any ally suffers damage,
you may suffer that damage
reduced by 1 instead.
XP +2
[PERSISTENT, LOSS]
```

When the option is used, the ally does not suffer that damage; SELF becomes the figure suffering the reduced amount.

### BOTTOM

```text
SHIELD 1
Affect self and all allies
INFUSE LIGHT
[ROUND]
```

`ENHANCEABLE: SHIELD`

The Shield has no range restriction and applies separately to SELF and every ally.

---

# File checklist

```text
gh-sk-back.png
gh-angelic-ascension.png
gh-beacon-of-light.png
gh-bright-aegis.png
gh-brilliant-prayer.png
gh-burning-flash.png
gh-cautious-advance.png
gh-cleansing-force.png
gh-daybreak.png
gh-dazzling-charge.png
gh-defensive-stance.png
gh-divine-intervention.png
gh-empowering-command.png
gh-engulfing-radiance.png
gh-glorious-bolt.png
gh-hammer-blow.png
gh-holy-strike.png
gh-illuminate-the-target.png
gh-inspiring-sanctity.png
gh-lay-on-hands.png
gh-mobilizing-axiom.png
gh-path-of-glory.png
gh-practical-plans.png
gh-protective-blessing.png
gh-purifying-aura.png
gh-righteous-strength.png
gh-scales-of-justice.png
gh-supportive-chant.png
gh-tactical-order.png
gh-unwavering-mandate.png
gh-weapon-of-purity.png
```

---

# Sources used

- Visual comparison with the three supplied Sunkeeper card screenshots (014457, 014501, and 014504), covering all 30 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, and explanatory notes outside the action blocks.
