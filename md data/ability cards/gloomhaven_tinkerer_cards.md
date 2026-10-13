# Gloomhaven – Tinkerer (TI) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/ti/`  
**Source folder:** <https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/ti>  
**Files covered:** 32 PNG files = 31 ability-card fronts + 1 card back.

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, summon statistics, elements, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block, including printed grammatical errors. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n` or “n experience” within sentences. Recovery, refresh, trap, kill, and other special effects use the full printed wording. `AOE pattern below` refers to the separate preserved area diagram.

## Elements

```text
INFUSE FIRE
INFUSE EARTH
INFUSE DARK
CONSUME ICE => effect
```

A consume line is conditional: the listed effect is obtained only when the named element is consumed.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `[UNRECOVERABLE]` — once lost, the card cannot be recovered, regardless of how it became lost.
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
ENHANCEABLE: HEAL (2 marks), MOVE
ENHANCEABLE: summon HP, summon MOVE
ENHANCEABLE: one AOE hex
```

For a condition listed as enhanceable, the printed dot may be used to replace or add an eligible condition according to the enhancement rules.

## Summons, traps, items, and card recovery

Summon panels retain their printed name and statistics. `HEALTH` spells out the hit-point icon, and `—` preserves a printed dash. Traps and card/item recovery effects retain their full printed instructions.

## Hex directions and AOE diagrams

Relative directions around the acting figure:

```text
          6   1
       5   S   2
          4   3
```

- `S` = acting figure / origin hex
- `T` = targeted hex
- `.` = unaffected neighboring hex

A diagram may be rotated around `S`; its relative shape remains unchanged.

Compact ranged three-hex cluster used by several cards:

```text
          T
       T     T
```

The three `T` hexes form one connected triangular cluster. The pattern may be rotated.

---

# Card back

## `gh-ti-back.png`

Tinkerer / Gear ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Energizing Tonic

**File:** `gh-energizing-tonic.png`  
**Level:** 1  
**Initiative:** 16

### TOP

```text
HEAL 5
RANGE 2
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
MOVE 6
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE`

---

## Enhancement Field

**File:** `gh-enhancement-field.png`  
**Level:** 1  
**Initiative:** 61

### TOP

```text
ATTACK 3
RANGE 3
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
You and all adjacent allies add
+1 ATTACK to all your attacks this round.
[ROUND]
```

The ally portion is an adjacency aura: an ally gains or loses the modifier as they move into or out of adjacency.

---

## Flamethrower

**File:** `gh-flamethrower.png`  
**Level:** 1  
**Initiative:** 47

### TOP

```text
ATTACK 3
WOUND
AOE pattern below
XP +2
INFUSE FIRE
[LOSS]
```

Exact relative AOE:

```text
TINKERER: [0, 0]
TARGETS: [[1, -1], [1, 0], [2, -1]]
```



The attack covers the three target hexes in the printed pattern. The entire pattern may be rotated.

`ENHANCEABLE: ATTACK, two AOE-hex slots ([2, -2], [2, 0])`

### BOTTOM

```text
SHIELD 1
Affect self and all adjacent allies
[ROUND]
```

`ENHANCEABLE: SHIELD`

The ally portion is an adjacency aura: an ally gains or loses the shield as they move into or out of adjacency.

---

## Harmless Contraption

**File:** `gh-harmless-contraption.png`  
**Level:** 1  
**Initiative:** 74

### TOP

```text
Summon Decoy
HEALTH 6
MOVE 2
ATTACK —
RANGE —
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: summon HP, summon MOVE`

The Decoy has no attack value.

### BOTTOM

```text
HEAL 2
RANGE 3
```

`ENHANCEABLE: HEAL`

---

## Hook Gun

**File:** `gh-hook-gun.png`  
**Level:** 1  
**Initiative:** 72

### TOP

```text
ATTACK 2
RANGE 3
PULL 2
```

`ENHANCEABLE: RANGE, PULL`

### BOTTOM

```text
LOOT 2
XP +1
[LOSS]
```

---

## Ink Bomb

**File:** `gh-ink-bomb.png`  
**Level:** 1  
**Initiative:** 74

### TOP

```text
ATTACK 4
RANGE 3
AOE pattern below
Gain 1 experience for each
enemy targeted.
INFUSE DARK
[LOSS]
```

TARGETS: [[0, 0], [1, -1], [1, 0]]

A separate attack is resolved against each targeted enemy. XP is based on enemies targeted, not enemies damaged.

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Net Shooter

**File:** `gh-net-shooter.png`  
**Level:** 1  
**Initiative:** 19

### TOP

```text
ATTACK 3
RANGE 3
IMMOBILIZE
AOE pattern below
Gain 1 experience for each
enemy targeted.
[LOSS]
```

TARGETS: [[0, 0], [1, -1], [1, 0]]


A separate attack is resolved against each targeted enemy. XP is based on enemies targeted, not enemies damaged.

`ENHANCEABLE: one AOE-hex slot ([0, -1])`

### BOTTOM

```text
IMMOBILIZE
Target one adjacent enemy

MOVE 2
```

`ENHANCEABLE: IMMOBILIZE, MOVE`

The condition application is not an attack.

---

## Proximity Mine

**File:** `gh-proximity-mine.png`  
**Level:** 1  
**Initiative:** 62

### TOP

```text
Create one 6 damage trap
in an adjacent empty hex.

Gain 2 experience when the trap is sprung
by an enemy.
[LOSS]
```

The XP is awarded only when the created trap is sprung by an enemy.

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Reinvigorating Elixir

**File:** `gh-reinvigorating-elixir.png`  
**Level:** 1  
**Initiative:** 37

### TOP

```text
HEAL 3
RANGE 3
```

`ENHANCEABLE: HEAL, RANGE`

### BOTTOM

```text
One adjacent ally may Recover
all their discarded cards.
XP +2
[LOSS]
```

The targeted ally returns every card currently in their discard pile to their hand.

---

## Restorative Mist

**File:** `gh-restorative-mist.png`  
**Level:** 1  
**Initiative:** 17

### TOP

```text
HEAL 3
RANGE 3
```

`ENHANCEABLE: HEAL, RANGE`

### BOTTOM

```text
MOVE 2

HEAL 1
Affect all adjacent allies
```

`ENHANCEABLE: MOVE`

The heal is a separate ability after the movement and does not affect SELF.

---

## Stun Shot

**File:** `gh-stun-shot.png`  
**Level:** 1  
**Initiative:** 20

### TOP

```text
ATTACK 1
RANGE 3
STUN
```

`ENHANCEABLE: ATTACK, RANGE`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Toxic Bolt

**File:** `gh-toxic-bolt.png`  
**Level:** 1  
**Initiative:** 18

### TOP

```text
ATTACK 2
RANGE 3
POISON
INFUSE EARTH
```

`ENHANCEABLE: RANGE`

### BOTTOM

```text
ATTACK 5
RANGE 2
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK, RANGE`

---

# Level X cards

## Potent Potables

**File:** `gh-potent-potables.png`  
**Level:** X  
**Initiative:** 46

### TOP

```text
On your next four Heal actions, add +2 HEAL.
TRACK: [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second and fourth affected heal actions each award 1 XP. A heal action with multiple targets receives the modifier as one heal action.

### BOTTOM

```text
MOVE 3
```

`ENHANCEABLE: MOVE`

---

## Reviving Shock

**File:** `gh-reviving-shock.png`  
**Level:** X  
**Initiative:** 34

### TOP

```text
ATTACK 2
RANGE 3
TARGET 2
```

`ENHANCEABLE: ATTACK, TARGET`

### BOTTOM

```text
HEAL 5
RANGE 2
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

---

## Volatile Concoction

**File:** `gh-volatile-concoction.png`  
**Level:** X  
**Initiative:** 76

### TOP

```text
Create one 2 damage POISON
trap in an adjacent empty hex.
```

### BOTTOM

```text
One ally within RANGE 2 may
Recover one of their discarded card.
CONSUME ICE: Up to two discarded
cards instead
```

`ENHANCEABLE: RANGE`

The element effect increases the total recovery limit to two cards; it does not create a second independent target.

---

# Level 2 cards

## Disorienting Flash

**File:** `gh-disorienting-flash.png`  
**Level:** 2  
**Initiative:** 73

### TOP

```text
STUN
RANGE 3
TARGET 2
XP +2
[LOSS]
```

`ENHANCEABLE: RANGE, TARGET`

This is a non-attack ability that may affect up to two different valid targets.

### BOTTOM

```text
MUDDLE
Target all adjacent enemies

MOVE 2
```

`ENHANCEABLE: MOVE`

The condition application is not an attack.

---

## Stamina Booster

**File:** `gh-stamina-booster.png`  
**Level:** 2  
**Initiative:** 48

### TOP

```text
HEAL 4
RANGE 3
```

`ENHANCEABLE: HEAL`

### BOTTOM

```text
One ally within RANGE 3 may
Recover one of their lost card.
XP +2
[LOSS, UNRECOVERABLE]
```

The targeted ally returns one of their own lost cards to their hand.

---

# Level 3 cards

## Crank Bow

**File:** `gh-crank-bow.png`  
**Level:** 3  
**Initiative:** 66

### TOP

```text
ATTACK 6
RANGE 5
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Tinkerer's Tools

**File:** `gh-tinkerers-tools.png`  
**Level:** 3  
**Initiative:** 26

### TOP

```text
Disarm one adjacent trap.
XP +1
```

The selected trap is removed without being sprung.

### BOTTOM

```text
Create one STUN trap in
an adjacent empty hex.
```

The trap has no printed damage value; when sprung, it applies STUN.

---

# Level 4 cards

## Dangerous Contraption

**File:** `gh-dangerous-contraption.png`  
**Level:** 4  
**Initiative:** 84

### TOP

```text
Summon Battle Bot
HEALTH 6
MOVE 2
ATTACK 2
RANGE —
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: summon HP, summon MOVE, summon ATTACK`

The Battle Bot's attacks are melee.

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Micro Bots

**File:** `gh-micro-bots.png`  
**Level:** 4  
**Initiative:** 22

### TOP

```text
HEAL 6
RANGE 3
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
MOVE 3

LOOT 1
[LOSS]
```

`ENHANCEABLE: MOVE`

The movement and loot are separate abilities in the listed order.

---

# Level 5 cards

## Disintegration Beam

**File:** `gh-disintegration-beam.png`  
**Level:** 5  
**Initiative:** 36

### TOP

```text
Kill all enemies in the
targeted area whose
current hit point value is
equal to or less than 5.
Gain 1 experience for each
enemy killed.
AOE pattern below
[LOSS]
```

Exact relative AOE:

```text
TINKERER: [0, 0]
TARGETS: [[1, -1], [2, -2], [3, -3]]
```

The printed pattern has three target hexes in a straight hex-axis ray beginning adjacent to `S`. The original coordinates below contain only two target hexes; they are retained unchanged. The entire pattern may be rotated.

`ENHANCEABLE: one AOE-hex slot ([4, -4])`

This is not an attack and does not deal damage. Standard immunities and scenario-specific rules still apply.

### BOTTOM

```text
MOVE 2

DISARM
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, DISARM`

The condition application is not an attack.

---

## Noxious Vials

**File:** `gh-noxious-vials.png`  
**Level:** 5  
**Initiative:** 75

### TOP

```text
One adjacent ally may Refresh
one of their consumed items.
```

The targeted ally refreshes one of their own consumed items.

### BOTTOM

```text
ATTACK 3
RANGE 3
POISON
AOE pattern below
Gain 1 experience for each
enemy targeted.
[LOSS]
```

TARGETS: [[0, 0], [1, -1], [1, 0]]

A separate attack is resolved against each targeted enemy. XP is based on enemies targeted, not enemies damaged.

`ENHANCEABLE: ATTACK, one AOE-hex slot ([0, -1])`

---

# Level 6 cards

## Auto Turret

**File:** `gh-auto-turret.png`  
**Level:** 6  
**Initiative:** 34

### TOP

```text
At the end of your next five turns, perform an
“ATTACK 2, RANGE 5” action.
TRACK: [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

Each trigger creates a separate attack action. The first, third, and fifth turns each award 1 XP. The track advances once at the end of each of those turns.

### BOTTOM

```text
HEAL 3
RANGE 4
```

`ENHANCEABLE: HEAL, RANGE`

---

## Gas Canister

**File:** `gh-gas-canister.png`  
**Level:** 6  
**Initiative:** 71

### TOP

```text
Create one 4 damage MUDDLE
trap in an adjacent empty hex.
```

### BOTTOM

```text
At the end of your next four turns,
one ally within RANGE 3 may
Recover one of their discarded cards.
TRACK: [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second and fourth turns each award 1 XP. The track advances at the end of each listed turn even if no card is recovered.

---

# Level 7 cards

## Curative Aerosol

**File:** `gh-curative-aerosol.png`  
**Level:** 7  
**Initiative:** 21

### TOP

```text
MOVE 2

HEAL 2
RANGE 1

MOVE 2

HEAL 2
RANGE 1
```

`ENHANCEABLE: first HEAL, second HEAL`

These are four separate abilities in the printed order. The two heals may have different targets.

### BOTTOM

```text
HEAL 4
RANGE 3
TARGET 2
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL, TARGET`

---

## Murderous Contraption

**File:** `gh-murderous-contraption.png`  
**Level:** 7  
**Initiative:** 94

### TOP

```text
Summon Kill Bot
HEALTH 6
MOVE 3
ATTACK 3
RANGE —
XP +2
[PERSISTENT, LOSS]
```

`ENHANCEABLE: summon HP, summon MOVE, summon ATTACK`

The Kill Bot's attacks are melee.

### BOTTOM

```text
ATTACK 4
RANGE 3
```

`ENHANCEABLE: ATTACK, RANGE`

---

# Level 8 cards

## Harsh Stimulants

**File:** `gh-harsh-stimulants.png`  
**Level:** 8  
**Initiative:** 27

### TOP

```text
ATTACK 9
Suffer 2 damage.
XP +2
[LOSS]
```

`ENHANCEABLE: ATTACK (2 marks)`

The self-damage is resolved separately and is not part of the attack.

### BOTTOM

```text
All adjacent allies add +2 ATTACK
to all their attacks this round.

All adjacent allies suffer 2 damage.
[ROUND]
```

The attack bonus is an adjacency aura for the round. The damage is applied once, immediately when the action is resolved. SELF is not included in either effect.

---

## Jet Propulsion

**File:** `gh-jet-propulsion.png`  
**Level:** 8  
**Initiative:** 38

### TOP

```text
All summoned allies within
RANGE 3 may perform

MOVE +2

with you controlling the actions.
```

The ability can affect allied summons regardless of which character summoned them.

### BOTTOM

```text
MOVE 8
Jump
XP +2
[LOSS]
```

`ENHANCEABLE: MOVE`

---

# Level 9 cards

## Chimeric Formula

**File:** `gh-chimeric-formula.png`  
**Level:** 9  
**Initiative:** 43

### TOP

```text
One adjacent ally may Recover up to
two of their lost cards, then lose two cards.
```

If the ally accepts the ability, they lose exactly two cards after recovering between zero and two cards.

### BOTTOM

```text
One ally within RANGE 3 may
Recover one lost card belonging
to an ally adjacent to them.
XP +2
[LOSS]
```

The recovered card is selected from ally B's lost pile and placed in ally A's hand. Ally A may use it as one of their own ability cards; a lost card that is explicitly unrecoverable still cannot be selected.

---

## Lethal Injection

**File:** `gh-lethal-injection.png`  
**Level:** 9  
**Initiative:** 85

### TOP

```text
Kill one adjacent normal or elite enemy.
XP +2
[LOSS]
```

Bosses and other enemies that are neither normal nor elite are not valid targets.

### BOTTOM

```text
MOVE 4

POISON
Target all adjacent enemies
```

`ENHANCEABLE: MOVE, POISON`

The poison application is not an attack.

---

# File checklist

```text
gh-ti-back.png
gh-auto-turret.png
gh-chimeric-formula.png
gh-crank-bow.png
gh-curative-aerosol.png
gh-dangerous-contraption.png
gh-disintegration-beam.png
gh-disorienting-flash.png
gh-energizing-tonic.png
gh-enhancement-field.png
gh-flamethrower.png
gh-gas-canister.png
gh-harmless-contraption.png
gh-harsh-stimulants.png
gh-hook-gun.png
gh-ink-bomb.png
gh-jet-propulsion.png
gh-lethal-injection.png
gh-micro-bots.png
gh-murderous-contraption.png
gh-net-shooter.png
gh-noxious-vials.png
gh-potent-potables.png
gh-proximity-mine.png
gh-reinvigorating-elixir.png
gh-restorative-mist.png
gh-reviving-shock.png
gh-stamina-booster.png
gh-stun-shot.png
gh-tinkerers-tools.png
gh-toxic-bolt.png
gh-volatile-concoction.png
```

---

# Sources used

- Visual comparison with the three supplied Tinkerer card screenshots (020205, 020209, and 020212), covering all 31 ability-card fronts and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, and explanatory notes outside the action blocks, with the inaccurate Flamethrower adjacency description corrected and the Disintegration Beam coordinate discrepancy identified.
