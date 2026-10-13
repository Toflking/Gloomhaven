# Gloomhaven – Sawbones (SB) Ability Cards

**Scope:** `images/character-ability-cards/gloomhaven/sb/`  
**Files covered:** 32 PNG files = 29 normal ability-card fronts + 2 Medical Pack fronts + 1 card back.

**Worldhaven folder:** [github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sb](https://github.com/any2cards/worldhaven/tree/master/images/character-ability-cards/gloomhaven/sb)

The TOP and BOTTOM text blocks preserve the full printed English card wording. Icon keywords are written in capitals; experience, tracks, duration, loss, enhancement marks, and area patterns use the notation below. Explanatory notes outside the blocks are not printed card text.

---

# Notation / legend

## Full wording and icon keywords

The printed wording is retained in each action block. Icon keywords such as ATTACK, MOVE, HEAL, RANGE, and condition names are spelled out. Experience icons are represented by `XP +n`. Recovery, kill, condition removal, Medical Pack, and granted-card effects use the full printed wording. `AOE pattern below` refers to the separate preserved area diagram.

## Durations and loss

- `[ROUND]` — active until the end of the current round.
- `[PERSISTENT]` — remains active after the turn until its effect ends or its owner discards it.
- `[LOSS]` — the card is a loss card for that action.
- `[PERSISTENT, LOSS]` — placed in the active area and eventually moved to the lost pile.
- `TRACK` — consume one use from left to right whenever its trigger occurs.
- `[XP+1]` inside a track means that use also awards 1 experience.

Example:

```text
TRACK: [ ] [XP+1] [ ] [XP+1] -> end
```

## Granted active cards

Several Sawbones actions place their own card in an adjacent ally's active area.

```text
GRANT_TO_ADJACENT_ALLY
```

Unless a card explicitly says otherwise:

- The recipient is treated as owning that card while it is in their active area.
- `SELF` on the granted persistent effect therefore refers to the recipient.
- Duration, loss, recovery, and discard behavior are evaluated for the recipient as the temporary owner.
- At the end of the scenario, the granted card is returned to the Sawbones.

`Syringe` is the exception: it is placed in an ally's active area for the round, but the ally is **not** made the owner, and the card returns to the Sawbones' discard pile at the end of the round.

## Medical Pack supply cards

```text
GIVE_MEDICAL_PACK
GIVE_LARGE_MEDICAL_PACK
RETURN_TO_SAWBONES_SUPPLY
```

- A Medical Pack is added to the receiving ally's hand.
- It is not one of the Sawbones' normal hand cards.
- It cannot be used for a basic action.
- It cannot be lost to negate a source of damage.
- Whenever it is discarded for any reason, it returns to the Sawbones' supply instead of entering a discard or lost pile.

## Enhancement marks

`ENHANCEABLE:` records the printed enhancement dots. It does **not** mean the card is already enhanced.

Examples:

```text
ENHANCEABLE: ATTACK
ENHANCEABLE: HEAL (2 marks), MOVE
ENHANCEABLE: STUN
```

A condition named after `ENHANCEABLE:` means the printed dot is attached to that condition line.

## AOE diagrams

- `T` = targeted hex.
- A diagram may be rotated; its relative shape remains unchanged.
- The two Sawbones area abilities use the same three-hex triangular pattern.

```text
          T
       T     T
```

---

# Card back

## `gh-sb-back.png`

Sawbones / Saw ability-card back.  
No level, initiative, top action, or bottom action.

---

# Level 1 cards

## Battlefield Medicine

**File:** `gh-battlefield-medicine.png`  
**Level:** 1  
**Initiative:** 83

### TOP

```text
Give one adjacent ally one
“Large Medical Pack” ability card.
XP +2
[LOSS]
```

### BOTTOM

```text
You and all allies are immune to
all negative conditions this round.
[ROUND]
```

This prevents new negative conditions during the round; it does not remove conditions already present.

---

## Bloody Saw

**File:** `gh-bloody-saw.png`  
**Level:** 1  
**Initiative:** 25

### TOP

```text
ATTACK 3
WOUND
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
LOOT 1
```

---

## Booster Shot

**File:** `gh-booster-shot.png`  
**Level:** 1  
**Initiative:** 42

### TOP

```text
HEAL 4
RANGE 1
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
Place this card in one adjacent
ally’s active area and treat it
as if they own the card.
XP +2

SHIELD 1
Self
[PERSISTENT, LOSS]
```

The recipient is treated as owning the active card, so `SELF` means that ally.

---

## Curative Mixture

**File:** `gh-curative-mixture.png`  
**Level:** 1  
**Initiative:** 19

### TOP

```text
HEAL 3
RANGE 1
Remove all negative conditions
on the healed figure.
XP +1
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
MOVE 3

Remove all negative conditions
on one adjacent ally.
```

`ENHANCEABLE: MOVE`

The condition removal is a separate ability after the movement.

---

## First Aid

**File:** `gh-first-aid.png`  
**Level:** 1  
**Initiative:** 08

### TOP

```text
Give one adjacent ally one
“Medical Pack” ability card.
XP +1
```

### BOTTOM

```text
MOVE 4
```

`ENHANCEABLE: MOVE`

---

## Hand of the Surgeon

**File:** `gh-hand-of-the-surgeon.png`  
**Level:** 1  
**Initiative:** 62

### TOP

```text
ATTACK 4
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
All your Heal actions this round
affect you and all adjacent allies.
[ROUND]
```

Each Heal action uses its normal Heal value and applies to the entire listed set instead of only one figure.

---

## Hold Back the Pain

**File:** `gh-hold-back-the-pain.png`  
**Level:** 1  
**Initiative:** 57

### TOP

```text
HEAL 3
RANGE 1
XP +1
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
All your melee attacks this round
target all adjacent enemies.
[ROUND]
```

Each enemy is resolved as a separate attack within the same Attack action.

---

## Mobile Response

**File:** `gh-mobile-response.png`  
**Level:** 1  
**Initiative:** 12

### TOP

```text
At the start of each of your turns,
removed one negative condition
from any one of your allies.
XP +2
[PERSISTENT, LOSS]
```

“Ally” does not include `SELF`.

### BOTTOM

```text
MOVE 5

WOUND
Target all adjacent enemies
XP +1
[LOSS]
```

`ENHANCEABLE: MOVE`

The Wound application is a separate non-attack ability after the movement.

---

## Syringe

**File:** `gh-syringe.png`  
**Level:** 1  
**Initiative:** 15

### TOP

```text
Place this card in one adjacent ally’s
active area, then put it into your
discard pile at the end of the round.

SHIELD 2
Self
[ROUND]
```

`ENHANCEABLE: SHIELD`

Unlike the other granted active cards, the recipient is not treated as owning `Syringe`.

### BOTTOM

```text
ATTACK 1
POISON
STUN
```

`ENHANCEABLE: ATTACK`

---

## Triage

**File:** `gh-triage.png`  
**Level:** 1  
**Initiative:** 53

### TOP

```text
Once during each of your next four turns,
perform a “HEAL 2, RANGE 1” action.
TRACK: [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second and fourth uses each award 1 XP. A use is optional during its corresponding turn, but an unused turn is still passed when that turn ends.

### BOTTOM

```text
MOVE 5
```

`ENHANCEABLE: MOVE`

---

# Level X cards

## Prevention Is Key

**File:** `gh-prevention-is-key.png`  
**Level:** X  
**Initiative:** 13

### TOP

```text
DISARM
Target all adjacent enemies
```

`ENHANCEABLE: DISARM`

This is a non-attack ability.

### BOTTOM

```text
SHIELD 1
Affect self and all adjacent allies
XP +1
[ROUND]
```

`ENHANCEABLE: SHIELD`

This behaves as a round-long aura: an ally has the Shield only while that ally is adjacent to `SELF`.

---

## Teamwork

**File:** `gh-teamwork.png`  
**Level:** X  
**Initiative:** 89

### TOP

```text
You and all adjacent allies may Recover
up to a number of discarded cards equal to
the number of allies adjacent to you.
XP +2
[LOSS]
```

The recovery limit is calculated once from the number of allies adjacent to `SELF` when the ability is resolved.

### BOTTOM

```text
All adjacent allies may perform
a “MOVE 2” action.

MOVE 2
```

`ENHANCEABLE: SELF's MOVE`

All ally movements are separate granted Move abilities. The printed enhancement mark belongs only to the Sawbones' own `MOVE 2`.

---

## Vaccine

**File:** `gh-vaccine.png`  
**Level:** X  
**Initiative:** 44

### TOP

```text
ATTACK 1
RANGE 3
POISON
AOE pattern below
XP +1
```

Exact AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

`ENHANCEABLE: ATTACK`

A separate attack is resolved against each occupied target hex.

### BOTTOM

```text
Place this card in one adjacent
ally’s active area and treat it
as if they own the card.
XP +2

You are immune to
all negative conditions.
[PERSISTENT, LOSS]
```

The recipient is treated as owning the active card.

---

# Medical Pack supply cards

## Medical Pack

**File:** `gh-medical-pack.png`  
**Level:** M  
**Initiative:** 50

### TOP

```text
HEAL 4
Self

This card cannot be lost to avoid suffering
damage. When this card is discarded for any
reason, return it to the Sawbones’ supply.
```

### BOTTOM

```text
HEAL 4
Self

This card cannot be lost to avoid suffering
damage. When this card is discarded for any
reason, return it to the Sawbones’ supply.
```

This card cannot be used for a basic action and cannot be lost to negate a source of damage.

---

## Large Medical Pack

**File:** `gh-large-medical-pack.png`  
**Level:** M  
**Initiative:** 30

### TOP

```text
HEAL 8
Self

This card cannot be lost to avoid suffering
damage. When this card is discarded for any
reason, return it to the Sawbones’ supply.
```

### BOTTOM

```text
HEAL 8
Self

This card cannot be lost to avoid suffering
damage. When this card is discarded for any
reason, return it to the Sawbones’ supply.
```

This card cannot be used for a basic action and cannot be lost to negate a source of damage.

---

# Level 2 cards

## Hamstring

**File:** `gh-hamstring.png`  
**Level:** 2  
**Initiative:** 62

### TOP

```text
ATTACK 4
IMMOBILIZE
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
MOVE 6

IMMOBILIZE
Self
```

`ENHANCEABLE: MOVE`

The self-Immobilize is mandatory if this action is performed.

---

## Precaution

**File:** `gh-precaution.png`  
**Level:** 2  
**Initiative:** 09

### TOP

```text
SHIELD 1
Affect all allies
[ROUND]
```

`ENHANCEABLE: SHIELD`

This affects every ally, regardless of range, but does not include `SELF`.

### BOTTOM

```text
Give one adjacent ally one
“Medical Pack” ability card.
XP +1
```

---

# Level 3 cards

## Regenerative Tissue

**File:** `gh-regenerative-tissue.png`  
**Level:** 3  
**Initiative:** 46

### TOP

```text
HEAL 5
Self
XP +1
```

`ENHANCEABLE: HEAL (2 marks)`

### BOTTOM

```text
Place this card in one adjacent
ally’s active area and treat it
as if they own the card.
XP +2

At the start of each of
your turns, perform a
“HEAL 1, Self” action.
[PERSISTENT, LOSS]
```

This action is persistent and is a loss action. The recipient is treated as owning the active card.

---

## Vital Strike

**File:** `gh-vital-strike.png`  
**Level:** 3  
**Initiative:** 38

### TOP

```text
ATTACK 7
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

# Level 4 cards

## Blood Transfusion

**File:** `gh-blood-transfusion.png`  
**Level:** 4  
**Initiative:** 52

### TOP

```text
HEAL 7
Affect one adjacent ally

Suffer 2 damage.
XP +1
```

`ENHANCEABLE: HEAL`

The Sawbones suffers the damage after resolving the Heal.

### BOTTOM

```text
ATTACK 3

HEAL 2
Affect all adjacent allies
```

`ENHANCEABLE: ATTACK, HEAL`

The Attack and Heal are separate abilities and may be skipped independently.

---

## Do No Harm

**File:** `gh-do-no-harm.png`  
**Level:** 4  
**Initiative:** 76

### TOP

```text
Kill one adjacent normal or elite enemy.

DISARM and
IMMOBILIZE
Self
XP +2
[LOSS]
```

Bosses are not valid execution targets.

### BOTTOM

```text
MOVE 4

HEAL 3
RANGE 1
```

`ENHANCEABLE: HEAL (2 marks)`

The Heal is a separate ability after the movement.

---

# Level 5 cards

## Amputate

**File:** `gh-amputate.png`  
**Level:** 5  
**Initiative:** 86

### TOP

```text
ATTACK 4
WOUND
IMMOBILIZE
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
HEAL 7
RANGE 1
STUN
```

`ENHANCEABLE: HEAL (2 marks)`

The Stun is applied even though the target is an ally or `SELF`.

---

## Research the Cure

**File:** `gh-research-the-cure.png`  
**Level:** 5  
**Initiative:** 40

### TOP

```text
ATTACK 4

Remove all negative conditions
on all adjacent allies.
```

`ENHANCEABLE: ATTACK`

The condition removal is a separate ability and does not affect `SELF`.

### BOTTOM

```text
Place this card in one adjacent
ally’s active area and treat it
as if they own the card.
XP +2

SHIELD 1
Self

You are immune to all negative conditions.
[PERSISTENT, LOSS]
```

The recipient is treated as owning the active card.

---

# Level 6 cards

## Euthanize

**File:** `gh-euthanize.png`  
**Level:** 6  
**Initiative:** 27

### TOP

```text
Kill one adjacent normal enemy with at
least two negative condition tokens.
XP +1
```

Elite enemies and bosses are not valid targets.

### BOTTOM

```text
ATTACK 1
POISON
WOUND
STUN
```

`ENHANCEABLE: ATTACK`

---

## Prescription

**File:** `gh-prescription.png`  
**Level:** 6  
**Initiative:** 06

### TOP

```text
Give all adjacent allies one
“Medical Pack” ability card.
XP +1
```

Each eligible ally receives a separate Medical Pack from the available supply.

### BOTTOM

```text
Give all adjacent allies one
“Large Medical Pack” ability card.
XP +2
[LOSS]
```

Each eligible ally receives a separate Large Medical Pack from the available supply.

---

# Level 7 cards

## Master Physician

**File:** `gh-master-physician.png`  
**Level:** 7  
**Initiative:** 49

### TOP

```text
Before your next six Heal actions, remove all
negative conditions on the healed figure(s).
TRACK: [ ] [XP+1] [ ] [XP+1] [ ] [XP+1] -> end
[PERSISTENT, LOSS]
```

The second, fourth, and sixth Heal actions each award 1 XP. One use is consumed per Heal action, even if that action heals multiple figures.

### BOTTOM

```text
Add +4 HEAL to all your
Heal actions this round.
[ROUND]
```

The bonus applies separately to every Heal action performed during the round.

---

## Surgeon's Satchel

**File:** `gh-surgeons-satchel.png`  
**Level:** 7  
**Initiative:** 10

### TOP

```text
LOOT 1

HEAL 2
Affect all adjacent allies
```

`ENHANCEABLE: HEAL`

The Heal is a separate ability after the Loot.

### BOTTOM

```text
MOVE 4

Give one adjacent ally one
“Medical Pack” ability card.
XP +1
```

`ENHANCEABLE: MOVE`

---

# Level 8 cards

## Bedside Manner

**File:** `gh-bedside-manner.png`  
**Level:** 8  
**Initiative:** 05

### TOP

```text
HEAL 3
RANGE 1
Remove all negative conditions on the
healed figure and give them one
“Large Medical Pack” ability card.
XP +2
[LOSS]
```

`ENHANCEABLE: HEAL (2 marks)`

All listed effects belong to the same Heal ability and apply to its chosen target.

### BOTTOM

```text
MOVE 2

One adjacent ally may Recover
up to two of their discarded cards.
```

`ENHANCEABLE: MOVE`

---

## Gentleman's Anger

**File:** `gh-gentlemans-anger.png`  
**Level:** 8  
**Initiative:** 29

### TOP

```text
ATTACK 5
DISARM
XP +1
```

`ENHANCEABLE: ATTACK`

### BOTTOM

```text
ATTACK 3
RANGE 5
XP +1
```

`ENHANCEABLE: ATTACK`

---

# Level 9 cards

## Grisly Trauma

**File:** `gh-grisly-trauma.png`  
**Level:** 9  
**Initiative:** 81

### TOP

```text
Kill all normal or elite
enemies in the targeted
area suffering from 10
or more damage.
RANGE 3
AOE pattern below
XP +1
```

Exact AOE:

```text
TARGETS: [[0, 0], [1, -1], [1, 0]]
```

The requirement is checked separately for each enemy. Bosses are never valid targets. “Suffering from 10 or more damage” refers to damage currently marked on the enemy, not its remaining HP.

### BOTTOM

```text
MOVE 2

POISON, WOUND and
MUDDLE
Target all adjacent enemies
```

`ENHANCEABLE: MOVE`

The condition application is a separate non-attack ability after the movement.

---

## Prep for Surgery

**File:** `gh-prep-for-surgery.png`  
**Level:** 9  
**Initiative:** 22

### TOP

```text
All your ATTACK and MOVE abilities
may be performed as HEAL abilities
of an equal value plus one.
In such cases, ignore all conditions
applied by the ATTACK abilities.
XP +2
[PERSISTENT, LOSS]
```

The choice is made separately for each Attack or Move ability. Unless that ability states another range, the converted Heal has `RANGE 1` and may affect `SELF` or one adjacent ally. Other printed target and area qualifiers remain applicable where meaningful.

### BOTTOM

```text
MOVE 3

STUN
Target one adjacent enemy
```

`ENHANCEABLE: MOVE, STUN`

The Stun is a separate non-attack ability after the movement.

---

# File checklist

```text
gh-sb-back.png
gh-amputate.png
gh-battlefield-medicine.png
gh-bedside-manner.png
gh-blood-transfusion.png
gh-bloody-saw.png
gh-booster-shot.png
gh-curative-mixture.png
gh-do-no-harm.png
gh-euthanize.png
gh-first-aid.png
gh-gentlemans-anger.png
gh-grisly-trauma.png
gh-hamstring.png
gh-hand-of-the-surgeon.png
gh-hold-back-the-pain.png
gh-large-medical-pack.png
gh-master-physician.png
gh-medical-pack.png
gh-mobile-response.png
gh-precaution.png
gh-prep-for-surgery.png
gh-prescription.png
gh-prevention-is-key.png
gh-regenerative-tissue.png
gh-research-the-cure.png
gh-surgeons-satchel.png
gh-syringe.png
gh-teamwork.png
gh-triage.png
gh-vaccine.png
gh-vital-strike.png
```

---

# Sources used

- Visual comparison with the three supplied Sawbones card screenshots (014758, 014802, and 014805), covering all 29 normal ability-card fronts, both Medical Pack fronts, and the card back.
- Original document retained for file metadata, enhancement annotations, AOE coordinates, and explanatory notes outside the action blocks, except the corrected Regenerative Tissue loss note.
