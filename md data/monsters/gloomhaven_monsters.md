# Gloomhaven — Monster

32 Monster-Typen aus dem korrigierten Bildsatz. Je Typ: Level 0–7 mit Normal und Elite sowie acht vollständige Ability-Karten.

## Leseschlüssel

Die englischen Kartentexte sind ausgeschrieben; Symbole werden als volle Schlüsselwörter dargestellt. `ATTACK`, `MOVE`, `RANGE`, `HEAL`, `TARGET` usw. stehen jeweils für das entsprechende gedruckte Symbol. `INFUSE FIRE/ICE/AIR/EARTH/LIGHT/DARK` bedeutet Element erzeugen; `CONSUME …:` kennzeichnet den vom Elementverbrauch abhängigen Effekt. Nachfolgende eingerückte Zeilen gehören zu diesem Effekt. `ANY ELEMENT` ist das beliebige Element-Symbol.

`—` bedeutet: Auf der Stat-Karte steht an dieser Stelle kein Wert. Bei RANGE bedeutet dies grundsätzlich Nahkampf, sofern die aktuelle Ability-Karte keine Reichweite vorgibt. Werte wie `ATTACK +1` und `MOVE -1` verändern den jeweiligen Grundwert. Ein fester Wert wie `RANGE 3` wird als solcher übernommen. Die Zeilenreihenfolge folgt der Karte. Doppelte Karten bleiben einzeln aufgeführt.

**Mischsymbol (SHUFFLE):** Bei „Ja“ wird am Ende der Runde der Ablagestapel dieses Ability-Decks zurück in das verbleibende Deck gemischt. Das Symbol ist für jede einzelne Karte separat erfasst. Es ist kein Loss-Symbol.

**Hex-Koordinaten:** axiales `(q, r)`-System mit spitzen Hexes; `q` steigt nach rechts, `r` nach rechts unten. Nachbarn von `(0, 0)`: `(1, 0)`, `(0, 1)`, `(-1, 1)`, `(-1, 0)`, `(0, -1)`, `(1, -1)`. Graue Hexes kennzeichnen die Monsterposition, rote Hexes mögliche Ziele. Bei Mustern ohne graues Hex ist `(0, 0)` nur ein roter Muster-Anker. Die Muster sind für diese Koordinatenschreibweise ausgerichtet; die Zielanordnung bleibt erhalten. Diese q/r-Übertragung ist zur anschließenden visuellen Kontrolle vorgesehen.

## Inhaltsverzeichnis

- [Ancient Artillery](#ancient-artillery)
- [Bandit Archer](#bandit-archer)
- [Bandit Guard](#bandit-guard)
- [Black Imp](#black-imp)
- [Cave Bear](#cave-bear)
- [City Archer](#city-archer)
- [City Guard](#city-guard)
- [Cultist](#cultist)
- [Deep Terror](#deep-terror)
- [Earth Demon](#earth-demon)
- [Flame Demon](#flame-demon)
- [Forest Imp](#forest-imp)
- [Frost Demon](#frost-demon)
- [Giant Viper](#giant-viper)
- [Harrower Infester](#harrower-infester)
- [Hound](#hound)
- [Inox Archer](#inox-archer)
- [Inox Guard](#inox-guard)
- [Inox Shaman](#inox-shaman)
- [Living Bones](#living-bones)
- [Lurker](#lurker)
- [Night Demon](#night-demon)
- [Ooze](#ooze)
- [Rending Drake](#rending-drake)
- [Savvas Icestorm](#savvas-icestorm)
- [Savvas Lavaflow](#savvas-lavaflow)
- [Spitting Drake](#spitting-drake)
- [Stone Golem](#stone-golem)
- [Sun Demon](#sun-demon)
- [Vermling Scout](#vermling-scout)
- [Vermling Shaman](#vermling-shaman)
- [Wind Demon](#wind-demon)

## Ancient Artillery

**Ability-Deck:** Ancient Artillery · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | — | 2 | 4 | — | — |
| 0 | Elite | 7 | — | 3 | 5 | — | — |
| 1 | Normal | 6 | — | 2 | 4 | — | — |
| 1 | Elite | 9 | — | 3 | 5 | — | — |
| 2 | Normal | 7 | — | 2 | 5 | — | — |
| 2 | Elite | 11 | — | 3 | 6 | — | — |
| 3 | Normal | 8 | — | 3 | 5 | — | — |
| 3 | Elite | 13 | — | 4 | 6 | — | — |
| 4 | Normal | 9 | — | 4 | 5 | — | — |
| 4 | Elite | 13 | — | 4 | 6 | — | TARGET 2 |
| 5 | Normal | 11 | — | 4 | 6 | — | — |
| 5 | Elite | 15 | — | 4 | 7 | — | TARGET 2 |
| 6 | Normal | 14 | — | 4 | 6 | — | — |
| 6 | Elite | 16 | — | 5 | 7 | — | TARGET 2 |
| 7 | Normal | 16 | — | 4 | 7 | — | — |
| 7 | Elite | 20 | — | 5 | 7 | — | TARGET 2 |

### Ability-Karten

#### Karte 1 — Initiative 46

**Kartennummer:** 700 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
RANGE +2
```

#### Karte 2 — Initiative 71

**Kartennummer:** 701 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +0
All adjacent enemies suffer 2 damage
```

#### Karte 3 — Initiative 71

**Kartennummer:** 702 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +0
All adjacent enemies suffer 2 damage
```

#### Karte 4 — Initiative 37

**Kartennummer:** 703 · **Mischsymbol (SHUFFLE):** Nein

```text
PUSH 1
Target all adjacent enemies
ATTACK -1 [AOE 1]
RANGE -1
```

**AOE 1 — 3 rote Hexes:** Kein graues Monster-Hex; `(0, 0)` ist ein rotes Referenz-Hex. Rote Ziel-Hexes: `[(0, 0), (-1, 1), (0, 1)]`.

#### Karte 5 — Initiative 37

**Kartennummer:** 704 · **Mischsymbol (SHUFFLE):** Nein

```text
PUSH 1
Target all adjacent enemies
ATTACK -1 [AOE 1]
RANGE -1
```

**AOE 1 — 7 rote Hexes:** Kein graues Monster-Hex; `(0, 0)` ist ein rotes Referenz-Hex. Rote Ziel-Hexes: `[(0, 0), (1, 0), (0, 1), (-1, 1), (-1, 0), (0, -1), (1, -1)]`.

#### Karte 6 — Initiative 95

**Kartennummer:** 705 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +1
```

#### Karte 7 — Initiative 17

**Kartennummer:** 706 · **Mischsymbol (SHUFFLE):** Nein

```text
PUSH 2
Target all adjacent enemies
SHIELD 2
ATTACK -2
```

#### Karte 8 — Initiative 46

**Kartennummer:** 707 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1 [AOE 1]
IMMOBILIZE
```

**AOE 1 — 3 rote Hexes:** Kein graues Monster-Hex; `(0, 0)` ist ein rotes Referenz-Hex. Rote Ziel-Hexes: `[(0, 0), (-1, 1), (0, 1)]`.

## Bandit Archer

**Ability-Deck:** Archer · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | 2 | 2 | 3 | — | — |
| 0 | Elite | 6 | 2 | 3 | 3 | — | — |
| 1 | Normal | 5 | 2 | 2 | 4 | — | — |
| 1 | Elite | 7 | 2 | 3 | 5 | — | — |
| 2 | Normal | 6 | 3 | 2 | 4 | — | — |
| 2 | Elite | 9 | 3 | 3 | 5 | — | — |
| 3 | Normal | 6 | 3 | 3 | 4 | — | — |
| 3 | Elite | 10 | 3 | 4 | 5 | — | — |
| 4 | Normal | 8 | 3 | 3 | 4 | — | — |
| 4 | Elite | 10 | 3 | 4 | 6 | — | POISON |
| 5 | Normal | 10 | 3 | 3 | 5 | — | — |
| 5 | Elite | 12 | 4 | 4 | 6 | — | POISON |
| 6 | Normal | 10 | 3 | 4 | 5 | — | — |
| 6 | Elite | 13 | 4 | 5 | 6 | — | POISON |
| 7 | Normal | 13 | 3 | 4 | 5 | — | — |
| 7 | Elite | 17 | 4 | 5 | 6 | — | POISON |

### Ability-Karten

#### Karte 1 — Initiative 16

**Kartennummer:** 532 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 31

**Kartennummer:** 533 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 32

**Kartennummer:** 534 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +1
RANGE -1
```

#### Karte 4 — Initiative 44

**Kartennummer:** 535 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 5 — Initiative 56

**Kartennummer:** 536 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
TARGET 2
```

#### Karte 6 — Initiative 68

**Kartennummer:** 537 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +1
RANGE +1
```

#### Karte 7 — Initiative 14

**Kartennummer:** 538 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
Create a 3 damage trap in an adjacent empty hex closest to an enemy
```

#### Karte 8 — Initiative 29

**Kartennummer:** 539 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
RANGE +1
IMMOBILIZE
```

## Bandit Guard

**Ability-Deck:** Guard · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 2 | — | — | — |
| 0 | Elite | 9 | 2 | 3 | — | — | — |
| 1 | Normal | 6 | 3 | 2 | — | — | — |
| 1 | Elite | 9 | 2 | 3 | — | — | SHIELD 1 |
| 2 | Normal | 6 | 3 | 3 | — | — | — |
| 2 | Elite | 10 | 2 | 4 | — | — | SHIELD 1 |
| 3 | Normal | 9 | 3 | 3 | — | — | — |
| 3 | Elite | 10 | 3 | 4 | — | — | SHIELD 2 |
| 4 | Normal | 10 | 4 | 3 | — | — | — |
| 4 | Elite | 11 | 3 | 4 | — | — | MUDDLE; SHIELD 2 |
| 5 | Normal | 11 | 4 | 4 | — | — | — |
| 5 | Elite | 12 | 3 | 5 | — | — | MUDDLE; SHIELD 2 |
| 6 | Normal | 14 | 4 | 4 | — | — | — |
| 6 | Elite | 14 | 4 | 5 | — | — | MUDDLE; SHIELD 2 |
| 7 | Normal | 16 | 5 | 4 | — | — | — |
| 7 | Elite | 14 | 3 | 5 | — | — | MUDDLE; SHIELD 3 |

### Ability-Karten

#### Karte 1 — Initiative 15

**Kartennummer:** 524 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
RETALIATE 2
```

#### Karte 2 — Initiative 30

**Kartennummer:** 525 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 35

**Kartennummer:** 526 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
RANGE 2
```

#### Karte 4 — Initiative 50

**Kartennummer:** 527 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 50

**Kartennummer:** 528 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 70

**Kartennummer:** 529 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 55

**Kartennummer:** 530 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
STRENGTHEN
Self
```

#### Karte 8 — Initiative 15

**Kartennummer:** 531 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
ATTACK +0
POISON
```

## Black Imp

**Ability-Deck:** Imp · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 3 | 1 | 1 | 3 | — | — |
| 0 | Elite | 4 | 1 | 2 | 3 | — | POISON |
| 1 | Normal | 4 | 1 | 1 | 3 | — | POISON |
| 1 | Elite | 6 | 1 | 2 | 3 | — | POISON |
| 2 | Normal | 5 | 1 | 1 | 4 | — | POISON |
| 2 | Elite | 8 | 1 | 2 | 4 | — | POISON |
| 3 | Normal | 5 | 1 | 2 | 4 | — | POISON |
| 3 | Elite | 8 | 1 | 3 | 4 | — | Attackers gain DISADVANTAGE; POISON |
| 4 | Normal | 7 | 1 | 2 | 4 | — | POISON |
| 4 | Elite | 11 | 1 | 3 | 4 | — | Attackers gain DISADVANTAGE; POISON |
| 5 | Normal | 9 | 1 | 2 | 4 | — | POISON |
| 5 | Elite | 12 | 1 | 3 | 5 | — | Attackers gain DISADVANTAGE; POISON |
| 6 | Normal | 10 | 1 | 3 | 4 | — | POISON |
| 6 | Elite | 14 | 1 | 4 | 5 | — | Attackers gain DISADVANTAGE; POISON |
| 7 | Normal | 12 | 1 | 3 | 4 | — | POISON |
| 7 | Elite | 17 | 1 | 4 | 5 | — | Attackers gain DISADVANTAGE; POISON |

### Ability-Karten

#### Karte 1 — Initiative 05

**Kartennummer:** 676 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 5
HEAL 1
Self
```

#### Karte 2 — Initiative 37

**Kartennummer:** 677 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 37

**Kartennummer:** 678 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 42

**Kartennummer:** 679 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
HEAL 2
RANGE 3
```

#### Karte 5 — Initiative 43

**Kartennummer:** 680 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
TARGET 2
POISON
```

#### Karte 6 — Initiative 76

**Kartennummer:** 681 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 43

**Kartennummer:** 682 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
TARGET 2
CURSE
```

#### Karte 8 — Initiative 24

**Kartennummer:** 683 · **Mischsymbol (SHUFFLE):** Nein

```text
STRENGTHEN
Affect all allies within RANGE 2
MUDDLE
Target all enemies within RANGE 2
```

## Cave Bear

**Ability-Deck:** Cave Bear · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 7 | 3 | 3 | — | — | — |
| 0 | Elite | 11 | 3 | 4 | — | — | — |
| 1 | Normal | 9 | 3 | 3 | — | — | — |
| 1 | Elite | 14 | 3 | 4 | — | — | — |
| 2 | Normal | 11 | 4 | 3 | — | — | — |
| 2 | Elite | 17 | 4 | 4 | — | — | — |
| 3 | Normal | 13 | 4 | 4 | — | — | — |
| 3 | Elite | 20 | 4 | 5 | — | — | — |
| 4 | Normal | 16 | 4 | 4 | — | — | — |
| 4 | Elite | 21 | 5 | 5 | — | — | WOUND |
| 5 | Normal | 17 | 5 | 4 | — | — | WOUND |
| 5 | Elite | 24 | 5 | 6 | — | — | WOUND |
| 6 | Normal | 19 | 5 | 5 | — | — | WOUND |
| 6 | Elite | 28 | 5 | 7 | — | — | WOUND |
| 7 | Normal | 22 | 5 | 5 | — | — | WOUND |
| 7 | Elite | 33 | 5 | 7 | — | — | WOUND |

### Ability-Karten

#### Karte 1 — Initiative 13

**Kartennummer:** 596 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 14

**Kartennummer:** 597 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
IMMOBILIZE
```

#### Karte 3 — Initiative 34

**Kartennummer:** 598 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +1
WOUND
```

#### Karte 4 — Initiative 41

**Kartennummer:** 599 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 60

**Kartennummer:** 600 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 6 — Initiative 80

**Kartennummer:** 601 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK -1
MOVE -2
ATTACK -1
WOUND
```

#### Karte 7 — Initiative 61

**Kartennummer:** 602 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
TARGET 2
```

#### Karte 8 — Initiative 03

**Kartennummer:** 603 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 1
RETALIATE 2
HEAL 2
Self
```

## City Archer

**Ability-Deck:** Archer · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | 1 | 2 | 3 | — | — |
| 0 | Elite | 6 | 1 | 3 | 4 | — | — |
| 1 | Normal | 5 | 1 | 2 | 4 | — | — |
| 1 | Elite | 6 | 1 | 3 | 5 | — | PIERCE 1; SHIELD 1 |
| 2 | Normal | 6 | 1 | 3 | 4 | — | — |
| 2 | Elite | 7 | 1 | 4 | 5 | — | PIERCE 2; SHIELD 1 |
| 3 | Normal | 6 | 2 | 3 | 4 | — | SHIELD 1 |
| 3 | Elite | 8 | 2 | 4 | 5 | — | PIERCE 2; SHIELD 2 |
| 4 | Normal | 8 | 2 | 3 | 5 | — | SHIELD 1 |
| 4 | Elite | 10 | 2 | 4 | 6 | — | PIERCE 2; SHIELD 2 |
| 5 | Normal | 9 | 2 | 4 | 5 | — | SHIELD 1 |
| 5 | Elite | 11 | 2 | 5 | 6 | — | PIERCE 3; SHIELD 2 |
| 6 | Normal | 9 | 3 | 4 | 5 | — | SHIELD 2 |
| 6 | Elite | 12 | 3 | 6 | 6 | — | PIERCE 3; SHIELD 2 |
| 7 | Normal | 10 | 3 | 4 | 6 | — | SHIELD 2 |
| 7 | Elite | 13 | 3 | 6 | 7 | — | PIERCE 3; SHIELD 3 |

### Ability-Karten

#### Karte 1 — Initiative 16

**Kartennummer:** 532 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 31

**Kartennummer:** 533 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 32

**Kartennummer:** 534 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +1
RANGE -1
```

#### Karte 4 — Initiative 44

**Kartennummer:** 535 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 5 — Initiative 56

**Kartennummer:** 536 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
TARGET 2
```

#### Karte 6 — Initiative 68

**Kartennummer:** 537 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +1
RANGE +1
```

#### Karte 7 — Initiative 14

**Kartennummer:** 538 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
Create a 3 damage trap in an adjacent empty hex closest to an enemy
```

#### Karte 8 — Initiative 29

**Kartennummer:** 539 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
RANGE +1
IMMOBILIZE
```

## City Guard

**Ability-Deck:** Guard · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 2 | — | — | — |
| 0 | Elite | 6 | 2 | 3 | — | — | SHIELD 1 |
| 1 | Normal | 5 | 2 | 2 | — | — | SHIELD 1 |
| 1 | Elite | 6 | 2 | 3 | — | — | SHIELD 2 |
| 2 | Normal | 7 | 2 | 2 | — | — | SHIELD 1 |
| 2 | Elite | 9 | 2 | 3 | — | — | SHIELD 2 |
| 3 | Normal | 8 | 2 | 3 | — | — | SHIELD 1 |
| 3 | Elite | 9 | 2 | 4 | — | — | RETALIATE 1; SHIELD 2 |
| 4 | Normal | 9 | 3 | 3 | — | — | SHIELD 1 |
| 4 | Elite | 10 | 3 | 4 | — | — | RETALIATE 2; SHIELD 2 |
| 5 | Normal | 10 | 3 | 3 | — | — | SHIELD 2 |
| 5 | Elite | 12 | 3 | 4 | — | — | RETALIATE 2; SHIELD 3 |
| 6 | Normal | 11 | 3 | 4 | — | — | SHIELD 2 |
| 6 | Elite | 13 | 3 | 5 | — | — | RETALIATE 3; SHIELD 3 |
| 7 | Normal | 13 | 3 | 4 | — | — | SHIELD 2 |
| 7 | Elite | 14 | 3 | 6 | — | — | RETALIATE 3; SHIELD 3 |

### Ability-Karten

#### Karte 1 — Initiative 15

**Kartennummer:** 524 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
RETALIATE 2
```

#### Karte 2 — Initiative 30

**Kartennummer:** 525 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 35

**Kartennummer:** 526 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
RANGE 2
```

#### Karte 4 — Initiative 50

**Kartennummer:** 527 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 50

**Kartennummer:** 528 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 70

**Kartennummer:** 529 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 55

**Kartennummer:** 530 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
STRENGTHEN
Self
```

#### Karte 8 — Initiative 15

**Kartennummer:** 531 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
ATTACK +0
POISON
```

## Cultist

**Ability-Deck:** Cultist · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | 2 | 1 | — | — | — |
| 0 | Elite | 7 | 2 | 2 | — | — | — |
| 1 | Normal | 5 | 2 | 1 | — | — | — |
| 1 | Elite | 9 | 2 | 2 | — | — | — |
| 2 | Normal | 7 | 2 | 1 | — | — | — |
| 2 | Elite | 12 | 2 | 2 | — | — | — |
| 3 | Normal | 9 | 3 | 1 | — | — | — |
| 3 | Elite | 13 | 3 | 2 | — | — | CURSE |
| 4 | Normal | 10 | 3 | 2 | — | — | — |
| 4 | Elite | 15 | 3 | 3 | — | — | CURSE |
| 5 | Normal | 11 | 3 | 2 | — | — | CURSE |
| 5 | Elite | 18 | 3 | 3 | — | — | CURSE |
| 6 | Normal | 14 | 3 | 2 | — | — | CURSE |
| 6 | Elite | 22 | 3 | 3 | — | — | CURSE |
| 7 | Normal | 15 | 3 | 3 | — | — | CURSE |
| 7 | Elite | 25 | 3 | 4 | — | — | CURSE |

### Ability-Karten

#### Karte 1 — Initiative 10

**Kartennummer:** 604 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
On Death:
ATTACK +2 [AOE 1]
```

**AOE 1 — 6 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, 0), (0, 1), (-1, 1), (-1, 0), (0, -1), (1, -1)]`.

#### Karte 2 — Initiative 10

**Kartennummer:** 605 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
On Death:
ATTACK +2 [AOE 1]
```

**AOE 1 — 6 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, 0), (0, 1), (-1, 1), (-1, 0), (0, -1), (1, -1)]`.

#### Karte 3 — Initiative 27

**Kartennummer:** 606 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 27

**Kartennummer:** 607 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 39

**Kartennummer:** 608 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
HEAL 1
Self
```

#### Karte 6 — Initiative 63

**Kartennummer:** 609 · **Mischsymbol (SHUFFLE):** Ja

```text
Summon normal Living Bones
Cultist suffers 2 damage.
```

#### Karte 7 — Initiative 63

**Kartennummer:** 610 · **Mischsymbol (SHUFFLE):** Ja

```text
Summon normal Living Bones
Cultist suffers 2 damage.
```

#### Karte 8 — Initiative 31

**Kartennummer:** 611 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
HEAL 3
RANGE 3
```

## Deep Terror

**Ability-Deck:** Deep Terror · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 3 | — | 2 | — | — | — |
| 0 | Elite | 5 | — | 3 | — | — | — |
| 1 | Normal | 4 | — | 2 | — | — | RETALIATE 1 |
| 1 | Elite | 6 | — | 3 | — | — | RETALIATE 1 |
| 2 | Normal | 4 | — | 3 | — | — | RETALIATE 1 |
| 2 | Elite | 7 | — | 4 | — | — | RETALIATE 1 |
| 3 | Normal | 5 | — | 3 | — | — | RETALIATE 2 |
| 3 | Elite | 8 | — | 4 | — | — | RETALIATE 2 |
| 4 | Normal | 6 | — | 4 | — | — | RETALIATE 2 |
| 4 | Elite | 9 | — | 5 | — | — | RETALIATE 2 |
| 5 | Normal | 7 | — | 4 | — | — | RETALIATE 3 |
| 5 | Elite | 11 | — | 5 | — | — | RETALIATE 3 |
| 6 | Normal | 8 | — | 5 | — | — | RETALIATE 3 |
| 6 | Elite | 13 | — | 6 | — | — | RETALIATE 3 |
| 7 | Normal | 9 | — | 5 | — | — | RETALIATE 4 |
| 7 | Elite | 15 | — | 6 | — | — | RETALIATE 4 |

### Ability-Karten

#### Karte 1 — Initiative 65

**Kartennummer:** 732 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
RANGE 3
TARGET 3
CURSE
```

#### Karte 2 — Initiative 60

**Kartennummer:** 733 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +0 [AOE 1]
PIERCE 3
```

**AOE 1 — 5 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (2, -2), (3, -3), (4, -4), (5, -5)]`.

#### Karte 3 — Initiative 60

**Kartennummer:** 734 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +0 [AOE 1]
PIERCE 3
```

**AOE 1 — 5 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (2, -2), (3, -3), (4, -4), (5, -5)]`.

#### Karte 4 — Initiative 84

**Kartennummer:** 735 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
Target all adjacent enemies
ATTACK +0
RANGE 4
WOUND
```

#### Karte 5 — Initiative 75

**Kartennummer:** 736 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
POISON
ATTACK -1
RANGE 5
IMMOBILIZE
```

#### Karte 6 — Initiative 75

**Kartennummer:** 737 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -2
Target all adjacent enemies
DISARM
ATTACK +0
RANGE 3
TARGET 2
```

#### Karte 7 — Initiative 96

**Kartennummer:** 738 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -2
RANGE 6
Summon normal Deep Terror in a hex adjacent to the target
```

#### Karte 8 — Initiative 54

**Kartennummer:** 739 · **Mischsymbol (SHUFFLE):** Nein

```text
WOUND and POISON
Target all adjacent enemies
ATTACK +0
RANGE 4
```

## Earth Demon

**Ability-Deck:** Earth Demon · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 7 | 1 | 3 | — | — | — |
| 0 | Elite | 10 | 2 | 4 | — | — | — |
| 1 | Normal | 9 | 1 | 3 | — | — | — |
| 1 | Elite | 13 | 2 | 4 | — | — | — |
| 2 | Normal | 12 | 1 | 3 | — | — | — |
| 2 | Elite | 18 | 2 | 4 | — | — | — |
| 3 | Normal | 13 | 2 | 3 | — | — | — |
| 3 | Elite | 20 | 2 | 4 | — | — | IMMOBILIZE |
| 4 | Normal | 15 | 2 | 4 | — | — | — |
| 4 | Elite | 21 | 3 | 5 | — | — | IMMOBILIZE |
| 5 | Normal | 17 | 2 | 4 | — | — | IMMOBILIZE |
| 5 | Elite | 25 | 3 | 5 | — | — | IMMOBILIZE |
| 6 | Normal | 20 | 2 | 4 | — | — | IMMOBILIZE |
| 6 | Elite | 27 | 3 | 6 | — | — | IMMOBILIZE |
| 7 | Normal | 22 | 3 | 4 | — | — | IMMOBILIZE |
| 7 | Elite | 32 | 3 | 6 | — | — | IMMOBILIZE |

### Ability-Karten

#### Karte 1 — Initiative 40

**Kartennummer:** 636 · **Mischsymbol (SHUFFLE):** Ja

```text
HEAL 3
Self
CONSUME EARTH: IMMOBILIZE
  Target all enemies within RANGE 3
```

#### Karte 2 — Initiative 42

**Kartennummer:** 637 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 62

**Kartennummer:** 638 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
INFUSE EARTH
```

#### Karte 4 — Initiative 71

**Kartennummer:** 639 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
RANGE 4
CONSUME EARTH: TARGET 2
```

#### Karte 5 — Initiative 83

**Kartennummer:** 640 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
INFUSE EARTH
```

#### Karte 6 — Initiative 93

**Kartennummer:** 641 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
Target all adjacent enemies
CONSUME EARTH: PUSH 1
```

#### Karte 7 — Initiative 79

**Kartennummer:** 642 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK +0
CONSUME AIR: -2 ATTACK
```

#### Karte 8 — Initiative 87

**Kartennummer:** 643 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1 [AOE 1]
CONSUME ANY ELEMENT: INFUSE EARTH
```

**AOE 1 — 3 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (2, -1), (1, 0)]`.

## Flame Demon

**Ability-Deck:** Flame Demon · **Flying-Symbol neben dem Namen:** Ja

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 2 | 3 | 2 | 3 | — | FLYING; SHIELD 2 |
| 0 | Elite | 3 | 3 | 2 | 3 | — | FLYING; SHIELD 3 |
| 1 | Normal | 2 | 3 | 2 | 3 | — | FLYING; SHIELD 3 |
| 1 | Elite | 3 | 3 | 2 | 4 | — | FLYING; RETALIATE 2, RANGE 2; SHIELD 4 |
| 2 | Normal | 3 | 3 | 3 | 3 | — | FLYING; SHIELD 3 |
| 2 | Elite | 4 | 3 | 3 | 4 | — | FLYING; RETALIATE 3, RANGE 2; SHIELD 4 |
| 3 | Normal | 3 | 3 | 3 | 4 | — | FLYING; RETALIATE 2, RANGE 2; SHIELD 3 |
| 3 | Elite | 5 | 3 | 3 | 5 | — | FLYING; RETALIATE 3, RANGE 3; SHIELD 4 |
| 4 | Normal | 3 | 4 | 3 | 4 | — | FLYING; RETALIATE 3, RANGE 2; SHIELD 3 |
| 4 | Elite | 5 | 4 | 4 | 5 | — | FLYING; RETALIATE 4, RANGE 3; SHIELD 4 |
| 5 | Normal | 4 | 4 | 3 | 4 | — | FLYING; RETALIATE 3, RANGE 2; SHIELD 4 |
| 5 | Elite | 6 | 4 | 4 | 5 | — | FLYING; RETALIATE 4, RANGE 3; SHIELD 5 |
| 6 | Normal | 4 | 4 | 4 | 4 | — | FLYING; RETALIATE 4, RANGE 2; SHIELD 4 |
| 6 | Elite | 7 | 4 | 5 | 5 | — | FLYING; RETALIATE 5, RANGE 3; SHIELD 5 |
| 7 | Normal | 5 | 4 | 4 | 5 | — | FLYING; RETALIATE 4, RANGE 3; SHIELD 4 |
| 7 | Elite | 8 | 4 | 5 | 6 | — | FLYING; RETALIATE 5, RANGE 4; SHIELD 5 |

### Ability-Karten

#### Karte 1 — Initiative 03

**Kartennummer:** 628 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
INFUSE FIRE
```

#### Karte 2 — Initiative 24

**Kartennummer:** 629 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
INFUSE FIRE
```

#### Karte 3 — Initiative 46

**Kartennummer:** 630 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +0
CONSUME FIRE: [AOE 1]
```

**AOE 1 — 7 rote Hexes:** Kein graues Monster-Hex; `(0, 0)` ist ein rotes Referenz-Hex. Rote Ziel-Hexes: `[(0, 0), (1, 0), (0, 1), (-1, 1), (-1, 0), (0, -1), (1, -1)]`.

#### Karte 4 — Initiative 49

**Kartennummer:** 631 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0 [AOE 1]
CONSUME FIRE: +1 ATTACK, WOUND
```

**AOE 1 — 2 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (2, -2)]`.

#### Karte 5 — Initiative 67

**Kartennummer:** 632 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
RANGE -1
INFUSE FIRE
```

#### Karte 6 — Initiative 77

**Kartennummer:** 633 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
Target all adjacent enemies
CONSUME ICE: Flame Demon suffers 1 damage.
```

#### Karte 7 — Initiative 30

**Kartennummer:** 634 · **Mischsymbol (SHUFFLE):** Ja

```text
CONSUME FIRE: All adjacent enemies suffer 2 damage.
MOVE +0
ATTACK -2
WOUND
TARGET 2
```

#### Karte 8 — Initiative 08

**Kartennummer:** 635 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
Create a 4 damage trap in an adjacent empty hex closest to an enemy
CONSUME ANY ELEMENT: INFUSE FIRE
```

## Forest Imp

**Ability-Deck:** Imp · **Flying-Symbol neben dem Namen:** Ja

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 1 | 3 | 1 | 3 | — | FLYING; SHIELD 1 |
| 0 | Elite | 4 | 3 | 1 | 3 | — | FLYING; SHIELD 1 |
| 1 | Normal | 2 | 3 | 1 | 3 | — | FLYING; SHIELD 1 |
| 1 | Elite | 5 | 3 | 2 | 3 | — | FLYING; SHIELD 1 |
| 2 | Normal | 2 | 3 | 2 | 3 | — | FLYING; SHIELD 1 |
| 2 | Elite | 6 | 3 | 2 | 3 | — | FLYING; CURSE; SHIELD 1 |
| 3 | Normal | 3 | 4 | 2 | 4 | — | FLYING; SHIELD 1 |
| 3 | Elite | 7 | 4 | 2 | 4 | — | FLYING; CURSE; SHIELD 1 |
| 4 | Normal | 3 | 4 | 2 | 4 | — | FLYING; SHIELD 2 |
| 4 | Elite | 7 | 4 | 2 | 4 | — | FLYING; CURSE; SHIELD 2 |
| 5 | Normal | 4 | 4 | 2 | 4 | — | FLYING; CURSE; SHIELD 2 |
| 5 | Elite | 8 | 4 | 3 | 4 | — | FLYING; CURSE; SHIELD 2 |
| 6 | Normal | 4 | 4 | 3 | 4 | — | FLYING; CURSE; SHIELD 2 |
| 6 | Elite | 9 | 4 | 4 | 4 | — | FLYING; CURSE; SHIELD 2 |
| 7 | Normal | 6 | 4 | 3 | 4 | — | FLYING; CURSE; SHIELD 2 |
| 7 | Elite | 11 | 4 | 4 | 4 | — | FLYING; CURSE; SHIELD 2 |

### Ability-Karten

#### Karte 1 — Initiative 05

**Kartennummer:** 676 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 5
HEAL 1
Self
```

#### Karte 2 — Initiative 37

**Kartennummer:** 677 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 37

**Kartennummer:** 678 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 42

**Kartennummer:** 679 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
HEAL 2
RANGE 3
```

#### Karte 5 — Initiative 43

**Kartennummer:** 680 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
TARGET 2
POISON
```

#### Karte 6 — Initiative 76

**Kartennummer:** 681 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 43

**Kartennummer:** 682 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
TARGET 2
CURSE
```

#### Karte 8 — Initiative 24

**Kartennummer:** 683 · **Mischsymbol (SHUFFLE):** Nein

```text
STRENGTHEN
Affect all allies within RANGE 2
MUDDLE
Target all enemies within RANGE 2
```

## Frost Demon

**Ability-Deck:** Frost Demon · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 3 | — | — | — |
| 0 | Elite | 10 | 3 | 3 | — | — | — |
| 1 | Normal | 6 | 2 | 3 | — | — | RETALIATE 1 |
| 1 | Elite | 10 | 3 | 3 | — | — | RETALIATE 2 |
| 2 | Normal | 7 | 3 | 3 | — | — | RETALIATE 2 |
| 2 | Elite | 12 | 4 | 4 | — | — | RETALIATE 2 |
| 3 | Normal | 8 | 3 | 4 | — | — | RETALIATE 2 |
| 3 | Elite | 14 | 4 | 4 | — | — | RETALIATE 3 |
| 4 | Normal | 10 | 3 | 4 | — | — | RETALIATE 2 |
| 4 | Elite | 18 | 4 | 4 | — | — | RETALIATE 3 |
| 5 | Normal | 11 | 3 | 4 | — | — | RETALIATE 3 |
| 5 | Elite | 20 | 4 | 5 | — | — | RETALIATE 3 |
| 6 | Normal | 12 | 3 | 5 | — | — | RETALIATE 3 |
| 6 | Elite | 22 | 4 | 5 | — | — | RETALIATE 4 |
| 7 | Normal | 14 | 3 | 5 | — | — | RETALIATE 3 |
| 7 | Elite | 25 | 4 | 5 | — | — | RETALIATE 4 |

### Ability-Karten

#### Karte 1 — Initiative 18

**Kartennummer:** 620 · **Mischsymbol (SHUFFLE):** Nein

```text
IMMOBILIZE
Target all enemies within RANGE 2
CONSUME ICE: HEAL 3
  Self
```

#### Karte 2 — Initiative 38

**Kartennummer:** 621 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 58

**Kartennummer:** 622 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 58

**Kartennummer:** 623 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
RANGE 2
CONSUME ICE: +2 ATTACK
  +1 RANGE
```

#### Karte 5 — Initiative 78

**Kartennummer:** 624 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK +0 [AOE 1]
INFUSE ICE
```

**AOE 1 — 2 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (1, 0)]`.

#### Karte 6 — Initiative 78

**Kartennummer:** 625 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK +0 [AOE 1]
INFUSE ICE
```

**AOE 1 — 2 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (1, 0)]`.

#### Karte 7 — Initiative 58

**Kartennummer:** 626 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
PIERCE 3
CONSUME ANY ELEMENT: INFUSE ICE
```

#### Karte 8 — Initiative 18

**Kartennummer:** 627 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 2
MOVE +1
CONSUME FIRE: Frost Demon suffers 1 damage.
```

## Giant Viper

**Ability-Deck:** Giant Viper · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 2 | 2 | 1 | — | — | POISON |
| 0 | Elite | 3 | 2 | 2 | — | — | POISON |
| 1 | Normal | 3 | 2 | 1 | — | — | POISON |
| 1 | Elite | 5 | 2 | 2 | — | — | POISON |
| 2 | Normal | 4 | 3 | 1 | — | — | POISON |
| 2 | Elite | 7 | 3 | 2 | — | — | POISON |
| 3 | Normal | 4 | 3 | 2 | — | — | POISON |
| 3 | Elite | 8 | 3 | 3 | — | — | POISON |
| 4 | Normal | 6 | 3 | 2 | — | — | POISON |
| 4 | Elite | 11 | 3 | 3 | — | — | POISON |
| 5 | Normal | 7 | 3 | 3 | — | — | POISON |
| 5 | Elite | 13 | 4 | 3 | — | — | POISON |
| 6 | Normal | 8 | 4 | 3 | — | — | POISON |
| 6 | Elite | 14 | 4 | 4 | — | — | POISON |
| 7 | Normal | 10 | 4 | 3 | — | — | POISON |
| 7 | Elite | 17 | 4 | 4 | — | — | POISON |

### Ability-Karten

#### Karte 1 — Initiative 32

**Kartennummer:** 684 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +0
Add +2 Attack if the target is adjacent to any of the Giant Viper's allies.
```

#### Karte 2 — Initiative 32

**Kartennummer:** 685 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +0
Add +2 Attack if the target is adjacent to any of the Giant Viper's allies.
```

#### Karte 3 — Initiative 11

**Kartennummer:** 686 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 1
ATTACK -1
```

#### Karte 4 — Initiative 43

**Kartennummer:** 687 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
JUMP
ATTACK -1
Target all adjacent enemies.
```

#### Karte 5 — Initiative 58

**Kartennummer:** 688 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 6 — Initiative 58

**Kartennummer:** 689 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
JUMP
ATTACK -1
All attacks targeting Giant Viper this round gain Disadvantage.
```

#### Karte 7 — Initiative 43

**Kartennummer:** 690 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
JUMP
ATTACK +0
TARGET 2
```

#### Karte 8 — Initiative 23

**Kartennummer:** 691 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
IMMOBILIZE
ATTACK -1
```

## Harrower Infester

**Ability-Deck:** Harrower Infester · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 6 | 2 | 2 | — | — | — |
| 0 | Elite | 12 | 2 | 2 | — | — | — |
| 1 | Normal | 7 | 2 | 2 | — | — | RETALIATE 1 |
| 1 | Elite | 12 | 3 | 2 | — | — | RETALIATE 2 |
| 2 | Normal | 8 | 2 | 2 | — | — | RETALIATE 2 |
| 2 | Elite | 14 | 3 | 3 | — | — | RETALIATE 2 |
| 3 | Normal | 10 | 2 | 3 | — | — | RETALIATE 2 |
| 3 | Elite | 17 | 3 | 3 | — | — | RETALIATE 3 |
| 4 | Normal | 12 | 3 | 3 | — | — | RETALIATE 2 |
| 4 | Elite | 19 | 3 | 4 | — | — | RETALIATE 3 |
| 5 | Normal | 12 | 3 | 4 | — | — | RETALIATE 3 |
| 5 | Elite | 21 | 3 | 5 | — | — | RETALIATE 3 |
| 6 | Normal | 15 | 3 | 4 | — | — | RETALIATE 3 |
| 6 | Elite | 22 | 4 | 5 | — | — | RETALIATE 4 |
| 7 | Normal | 17 | 3 | 4 | — | — | RETALIATE 4 |
| 7 | Elite | 26 | 4 | 5 | — | — | RETALIATE 4 |

### Ability-Karten

#### Karte 1 — Initiative 38

**Kartennummer:** 724 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
TARGET 2
```

#### Karte 2 — Initiative 07

**Kartennummer:** 725 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
POISON
INFUSE DARK
```

#### Karte 3 — Initiative 16

**Kartennummer:** 726 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
HEAL 5
Self
```

#### Karte 4 — Initiative 16

**Kartennummer:** 727 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +2
IMMOBILIZE
RETALIATE 2
```

#### Karte 5 — Initiative 02

**Kartennummer:** 728 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 2
RETALIATE 2
RANGE 3
```

#### Karte 6 — Initiative 30

**Kartennummer:** 729 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0 [AOE 1]
CONSUME DARK: Perform "HEAL 2, Self" for each target damaged
```

**AOE 1 — 3 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(0, 1), (0, 2), (0, 3)]`.

#### Karte 7 — Initiative 38

**Kartennummer:** 730 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
TARGET 2
CONSUME DARK: +2 ATTACK, DISARM
```

#### Karte 8 — Initiative 07

**Kartennummer:** 731 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK -1
RANGE 3
MUDDLE
HEAL 4
Self
```

## Hound

**Ability-Deck:** Hound · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | 3 | 2 | — | — | — |
| 0 | Elite | 6 | 5 | 2 | — | — | — |
| 1 | Normal | 4 | 4 | 2 | — | — | RETALIATE 1 |
| 1 | Elite | 6 | 5 | 2 | — | — | RETALIATE 2 |
| 2 | Normal | 6 | 4 | 2 | — | — | RETALIATE 1 |
| 2 | Elite | 7 | 5 | 3 | — | — | RETALIATE 2 |
| 3 | Normal | 8 | 4 | 2 | — | — | RETALIATE 1 |
| 3 | Elite | 8 | 5 | 4 | — | — | RETALIATE 2 |
| 4 | Normal | 8 | 4 | 3 | — | — | RETALIATE 1 |
| 4 | Elite | 11 | 5 | 4 | — | — | RETALIATE 2 |
| 5 | Normal | 9 | 4 | 3 | — | — | RETALIATE 2 |
| 5 | Elite | 12 | 5 | 4 | — | — | RETALIATE 3 |
| 6 | Normal | 11 | 5 | 3 | — | — | RETALIATE 2 |
| 6 | Elite | 15 | 6 | 4 | — | — | RETALIATE 3 |
| 7 | Normal | 15 | 5 | 3 | — | — | RETALIATE 2 |
| 7 | Elite | 15 | 6 | 5 | — | — | RETALIATE 4 |

### Ability-Karten

#### Karte 1 — Initiative 06

**Kartennummer:** 540 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
IMMOBILIZE
```

#### Karte 2 — Initiative 07

**Kartennummer:** 541 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
MUDDLE
Target all adjacent enemies
```

#### Karte 3 — Initiative 19

**Kartennummer:** 542 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +0
Add +2 Attack if the target is adjacent to any of the Hound's allies
```

#### Karte 4 — Initiative 19

**Kartennummer:** 543 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +0
Add +2 Attack if the target is adjacent to any of the Hound's allies
```

#### Karte 5 — Initiative 26

**Kartennummer:** 544 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 26

**Kartennummer:** 545 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 7 — Initiative 83

**Kartennummer:** 546 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -2
ATTACK +1
```

#### Karte 8 — Initiative 72

**Kartennummer:** 547 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
PIERCE 2
MOVE -2
ATTACK -1
PIERCE 2
```

## Inox Archer

**Ability-Deck:** Archer · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 2 | 2 | — | — |
| 0 | Elite | 7 | 2 | 3 | 3 | — | — |
| 1 | Normal | 6 | 2 | 2 | 3 | — | — |
| 1 | Elite | 8 | 2 | 3 | 4 | — | — |
| 2 | Normal | 8 | 2 | 2 | 3 | — | — |
| 2 | Elite | 11 | 2 | 3 | 4 | — | — |
| 3 | Normal | 9 | 2 | 3 | 3 | — | — |
| 3 | Elite | 13 | 2 | 4 | 4 | — | — |
| 4 | Normal | 10 | 3 | 3 | 3 | — | — |
| 4 | Elite | 14 | 3 | 4 | 4 | — | WOUND |
| 5 | Normal | 12 | 3 | 3 | 4 | — | — |
| 5 | Elite | 17 | 3 | 4 | 5 | — | WOUND |
| 6 | Normal | 12 | 3 | 4 | 4 | — | WOUND |
| 6 | Elite | 19 | 3 | 5 | 5 | — | WOUND |
| 7 | Normal | 15 | 3 | 4 | 4 | — | WOUND |
| 7 | Elite | 23 | 3 | 5 | 5 | — | WOUND |

### Ability-Karten

#### Karte 1 — Initiative 16

**Kartennummer:** 532 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 31

**Kartennummer:** 533 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 32

**Kartennummer:** 534 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +1
RANGE -1
```

#### Karte 4 — Initiative 44

**Kartennummer:** 535 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 5 — Initiative 56

**Kartennummer:** 536 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
TARGET 2
```

#### Karte 6 — Initiative 68

**Kartennummer:** 537 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +1
RANGE +1
```

#### Karte 7 — Initiative 14

**Kartennummer:** 538 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
Create a 3 damage trap in an adjacent empty hex closest to an enemy
```

#### Karte 8 — Initiative 29

**Kartennummer:** 539 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1
RANGE +1
IMMOBILIZE
```

## Inox Guard

**Ability-Deck:** Guard · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 2 | — | — | — |
| 0 | Elite | 9 | 1 | 3 | — | — | RETALIATE 1 |
| 1 | Normal | 8 | 2 | 2 | — | — | — |
| 1 | Elite | 10 | 2 | 3 | — | — | RETALIATE 2 |
| 2 | Normal | 9 | 2 | 3 | — | — | — |
| 2 | Elite | 12 | 2 | 4 | — | — | RETALIATE 2 |
| 3 | Normal | 11 | 3 | 3 | — | — | — |
| 3 | Elite | 15 | 2 | 4 | — | — | RETALIATE 3 |
| 4 | Normal | 12 | 3 | 3 | — | — | RETALIATE 1 |
| 4 | Elite | 17 | 2 | 5 | — | — | RETALIATE 3 |
| 5 | Normal | 13 | 3 | 4 | — | — | RETALIATE 1 |
| 5 | Elite | 19 | 2 | 5 | — | — | RETALIATE 4 |
| 6 | Normal | 16 | 3 | 4 | — | — | RETALIATE 1 |
| 6 | Elite | 21 | 3 | 5 | — | — | RETALIATE 4 |
| 7 | Normal | 19 | 3 | 4 | — | — | RETALIATE 2 |
| 7 | Elite | 23 | 3 | 6 | — | — | RETALIATE 4 |

### Ability-Karten

#### Karte 1 — Initiative 15

**Kartennummer:** 524 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
RETALIATE 2
```

#### Karte 2 — Initiative 30

**Kartennummer:** 525 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 35

**Kartennummer:** 526 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
RANGE 2
```

#### Karte 4 — Initiative 50

**Kartennummer:** 527 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 50

**Kartennummer:** 528 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 70

**Kartennummer:** 529 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 55

**Kartennummer:** 530 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
STRENGTHEN
Self
```

#### Karte 8 — Initiative 15

**Kartennummer:** 531 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
ATTACK +0
POISON
```

## Inox Shaman

**Ability-Deck:** Shaman · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | 1 | 2 | 3 | — | — |
| 0 | Elite | 6 | 2 | 3 | 3 | — | — |
| 1 | Normal | 6 | 1 | 2 | 3 | — | — |
| 1 | Elite | 9 | 2 | 3 | 3 | — | — |
| 2 | Normal | 7 | 2 | 2 | 3 | — | — |
| 2 | Elite | 11 | 3 | 3 | 3 | — | — |
| 3 | Normal | 9 | 2 | 2 | 4 | — | — |
| 3 | Elite | 14 | 3 | 3 | 4 | — | — |
| 4 | Normal | 10 | 2 | 3 | 4 | — | — |
| 4 | Elite | 16 | 3 | 4 | 4 | — | — |
| 5 | Normal | 13 | 2 | 3 | 4 | — | — |
| 5 | Elite | 20 | 3 | 4 | 4 | — | — |
| 6 | Normal | 15 | 3 | 3 | 4 | — | — |
| 6 | Elite | 24 | 4 | 4 | 4 | — | — |
| 7 | Normal | 16 | 3 | 4 | 4 | — | — |
| 7 | Elite | 27 | 4 | 5 | 4 | — | — |

### Ability-Karten

#### Karte 1 — Initiative 08

**Kartennummer:** 588 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
DISARM
```

#### Karte 2 — Initiative 08

**Kartennummer:** 589 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
IMMOBILIZE
```

#### Karte 3 — Initiative 23

**Kartennummer:** 590 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
HEAL 3
RANGE 3
```

#### Karte 4 — Initiative 23

**Kartennummer:** 591 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
HEAL 3
RANGE 3
```

#### Karte 5 — Initiative 62

**Kartennummer:** 592 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 74

**Kartennummer:** 593 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 89

**Kartennummer:** 594 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
HEAL 1
Affect all adjacent allies
BLESS
Self
```

#### Karte 8 — Initiative 09

**Kartennummer:** 595 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
CURSE
TARGET 2
```

## Living Bones

**Ability-Deck:** Living Bones · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 1 | — | — | TARGET 2 |
| 0 | Elite | 6 | 4 | 2 | — | — | TARGET 2 |
| 1 | Normal | 5 | 3 | 1 | — | — | TARGET 2; SHIELD 1 |
| 1 | Elite | 6 | 4 | 2 | — | — | TARGET 3; SHIELD 1 |
| 2 | Normal | 5 | 3 | 2 | — | — | TARGET 2; SHIELD 1 |
| 2 | Elite | 7 | 4 | 3 | — | — | TARGET 3; SHIELD 1 |
| 3 | Normal | 7 | 3 | 2 | — | — | TARGET 2; SHIELD 1 |
| 3 | Elite | 10 | 4 | 3 | — | — | TARGET 3; SHIELD 1 |
| 4 | Normal | 7 | 3 | 3 | — | — | TARGET 2; SHIELD 1 |
| 4 | Elite | 11 | 4 | 4 | — | — | TARGET 3; SHIELD 1 |
| 5 | Normal | 9 | 3 | 3 | — | — | TARGET 2; SHIELD 1 |
| 5 | Elite | 11 | 4 | 4 | — | — | TARGET 3; SHIELD 2 |
| 6 | Normal | 10 | 4 | 3 | — | — | TARGET 2; SHIELD 1 |
| 6 | Elite | 11 | 6 | 4 | — | — | TARGET 3; SHIELD 2 |
| 7 | Normal | 13 | 4 | 3 | — | — | TARGET 2; SHIELD 1 |
| 7 | Elite | 14 | 6 | 4 | — | — | TARGET 3; SHIELD 2 |

### Ability-Karten

#### Karte 1 — Initiative 64

**Kartennummer:** 516 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 2 — Initiative 20

**Kartennummer:** 517 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -2
ATTACK +0
HEAL 2
Self
```

#### Karte 3 — Initiative 25

**Kartennummer:** 518 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 4 — Initiative 45

**Kartennummer:** 519 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 45

**Kartennummer:** 520 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 81

**Kartennummer:** 521 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +2
```

#### Karte 7 — Initiative 74

**Kartennummer:** 522 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
Target one enemy with all attacks
```

#### Karte 8 — Initiative 12

**Kartennummer:** 523 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
HEAL 2
Self
```

## Lurker

**Ability-Deck:** Lurker · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 2 | — | — | TARGET 2 |
| 0 | Elite | 7 | 2 | 3 | — | — | TARGET 2; SHIELD 1 |
| 1 | Normal | 7 | 2 | 2 | — | — | TARGET 2; PIERCE 1 |
| 1 | Elite | 9 | 2 | 3 | — | — | TARGET 2; PIERCE 1; SHIELD 1 |
| 2 | Normal | 9 | 3 | 2 | — | — | TARGET 2; PIERCE 1 |
| 2 | Elite | 12 | 3 | 3 | — | — | TARGET 2; PIERCE 2; SHIELD 1 |
| 3 | Normal | 10 | 3 | 3 | — | — | TARGET 2; PIERCE 2 |
| 3 | Elite | 14 | 3 | 4 | — | — | TARGET 2; PIERCE 2; SHIELD 1 |
| 4 | Normal | 10 | 3 | 3 | — | — | TARGET 2; PIERCE 2; SHIELD 1 |
| 4 | Elite | 14 | 3 | 4 | — | — | TARGET 2; PIERCE 3; SHIELD 2 |
| 5 | Normal | 11 | 3 | 4 | — | — | TARGET 2; PIERCE 2; SHIELD 1 |
| 5 | Elite | 15 | 3 | 5 | — | — | TARGET 2; PIERCE 3; SHIELD 2 |
| 6 | Normal | 12 | 4 | 4 | — | — | TARGET 2; PIERCE 3; SHIELD 1 |
| 6 | Elite | 16 | 4 | 5 | — | — | TARGET 2; PIERCE 4; SHIELD 2 |
| 7 | Normal | 14 | 4 | 4 | — | — | TARGET 2; PIERCE 3; SHIELD 1 |
| 7 | Elite | 18 | 4 | 5 | — | — | TARGET 2; PIERCE 4; SHIELD 2 |

### Ability-Karten

#### Karte 1 — Initiative 11

**Kartennummer:** 612 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
CONSUME ICE: SHIELD 2 instead
WOUND
Target all adjacent enemies
```

#### Karte 2 — Initiative 28

**Kartennummer:** 613 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 38

**Kartennummer:** 614 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 38

**Kartennummer:** 615 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
Target one enemy with all attacks
```

#### Karte 5 — Initiative 61

**Kartennummer:** 616 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 6 — Initiative 64

**Kartennummer:** 617 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +1
Target all adjacent enemies
```

#### Karte 7 — Initiative 41

**Kartennummer:** 618 · **Mischsymbol (SHUFFLE):** Nein

```text
CONSUME ICE: STRENGTHEN
  Self
MOVE +0
ATTACK -1
WOUND
```

#### Karte 8 — Initiative 23

**Kartennummer:** 619 · **Mischsymbol (SHUFFLE):** Ja

```text
SHIELD 1
MOVE +0
ATTACK -1
INFUSE ICE
```

## Night Demon

**Ability-Deck:** Night Demon · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 3 | 3 | 3 | — | — | Attackers gain DISADVANTAGE |
| 0 | Elite | 5 | 4 | 4 | — | — | Attackers gain DISADVANTAGE |
| 1 | Normal | 5 | 3 | 3 | — | — | Attackers gain DISADVANTAGE |
| 1 | Elite | 8 | 4 | 4 | — | — | Attackers gain DISADVANTAGE |
| 2 | Normal | 6 | 3 | 4 | — | — | Attackers gain DISADVANTAGE |
| 2 | Elite | 11 | 4 | 4 | — | — | Attackers gain DISADVANTAGE |
| 3 | Normal | 7 | 4 | 4 | — | — | Attackers gain DISADVANTAGE |
| 3 | Elite | 13 | 4 | 5 | — | — | Attackers gain DISADVANTAGE |
| 4 | Normal | 8 | 4 | 5 | — | — | Attackers gain DISADVANTAGE |
| 4 | Elite | 15 | 5 | 5 | — | — | Attackers gain DISADVANTAGE |
| 5 | Normal | 11 | 4 | 5 | — | — | Attackers gain DISADVANTAGE |
| 5 | Elite | 17 | 5 | 6 | — | — | Attackers gain DISADVANTAGE |
| 6 | Normal | 14 | 4 | 5 | — | — | Attackers have DISADVANTAGE |
| 6 | Elite | 21 | 5 | 6 | — | — | Attackers gain DISADVANTAGE |
| 7 | Normal | 15 | 4 | 6 | — | — | Attackers gain DISADVANTAGE |
| 7 | Elite | 21 | 5 | 8 | — | — | Attackers gain DISADVANTAGE |

### Ability-Karten

#### Karte 1 — Initiative 04

**Kartennummer:** 660 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
INFUSE DARK
```

#### Karte 2 — Initiative 07

**Kartennummer:** 661 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
CONSUME DARK: INVISIBLE
  Self
```

#### Karte 3 — Initiative 22

**Kartennummer:** 662 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
INFUSE DARK
```

#### Karte 4 — Initiative 26

**Kartennummer:** 663 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -2
RANGE 3
TARGET 3
CONSUME DARK: MUDDLE
```

#### Karte 5 — Initiative 46

**Kartennummer:** 664 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK +1
CONSUME DARK: +2 ATTACK
```

#### Karte 6 — Initiative 41

**Kartennummer:** 665 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK +1
INFUSE DARK
```

#### Karte 7 — Initiative 35

**Kartennummer:** 666 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
ATTACK -1
PIERCE 2
CONSUME LIGHT: CURSE
  Self
```

#### Karte 8 — Initiative 15

**Kartennummer:** 667 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
All adjacent enemies and allies suffer 1 damage.
CONSUME ANY ELEMENT: INFUSE DARK
```

## Ooze

**Ability-Deck:** Ooze · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 4 | 1 | 2 | 2 | — | — |
| 0 | Elite | 8 | 1 | 2 | 3 | — | — |
| 1 | Normal | 5 | 1 | 2 | 2 | — | SHIELD 1 |
| 1 | Elite | 9 | 1 | 2 | 3 | — | SHIELD 1 |
| 2 | Normal | 7 | 1 | 2 | 3 | — | SHIELD 1 |
| 2 | Elite | 11 | 1 | 3 | 3 | — | SHIELD 1 |
| 3 | Normal | 8 | 1 | 3 | 3 | — | SHIELD 1 |
| 3 | Elite | 11 | 2 | 3 | 4 | — | POISON; SHIELD 1 |
| 4 | Normal | 9 | 2 | 3 | 3 | — | SHIELD 1 |
| 4 | Elite | 13 | 2 | 4 | 4 | — | POISON; SHIELD 1 |
| 5 | Normal | 10 | 2 | 3 | 3 | — | POISON; SHIELD 1 |
| 5 | Elite | 15 | 3 | 4 | 4 | — | POISON; SHIELD 1 |
| 6 | Normal | 12 | 2 | 4 | 3 | — | POISON; SHIELD 1 |
| 6 | Elite | 16 | 3 | 4 | 4 | — | POISON; SHIELD 2 |
| 7 | Normal | 14 | 2 | 4 | 3 | — | POISON; SHIELD 1 |
| 7 | Elite | 18 | 3 | 5 | 4 | — | POISON; SHIELD 2 |

### Ability-Karten

#### Karte 1 — Initiative 36

**Kartennummer:** 692 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 57

**Kartennummer:** 693 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 59

**Kartennummer:** 694 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
TARGET 2
POISON
```

#### Karte 4 — Initiative 66

**Kartennummer:** 695 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
RANGE +1
```

#### Karte 5 — Initiative 94

**Kartennummer:** 696 · **Mischsymbol (SHUFFLE):** Ja

```text
Ooze suffers 2 damage
Summon normal Ooze with a hit point value equal to the summoning Ooze's current hit point value (limited by a normal Ooze's specified maximum hit point value)
```

#### Karte 6 — Initiative 94

**Kartennummer:** 697 · **Mischsymbol (SHUFFLE):** Ja

```text
Ooze suffers 2 damage
Summon normal Ooze with a hit point value equal to the summoning Ooze's current hit point value (limited by a normal Ooze's specified maximum hit point value)
```

#### Karte 7 — Initiative 66

**Kartennummer:** 698 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
LOOT 1
HEAL 2
Self
```

#### Karte 8 — Initiative 85

**Kartennummer:** 699 · **Mischsymbol (SHUFFLE):** Nein

```text
PUSH 1 and
POISON
Target all adjacent enemies
ATTACK +1
RANGE -1
```

## Rending Drake

**Ability-Deck:** Rending Drake · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 3 | 3 | — | — | — |
| 0 | Elite | 7 | 4 | 4 | — | — | — |
| 1 | Normal | 6 | 3 | 3 | — | — | WOUND |
| 1 | Elite | 7 | 4 | 5 | — | — | WOUND |
| 2 | Normal | 7 | 4 | 3 | — | — | WOUND |
| 2 | Elite | 9 | 5 | 5 | — | — | WOUND |
| 3 | Normal | 7 | 4 | 4 | — | — | WOUND |
| 3 | Elite | 10 | 5 | 6 | — | — | WOUND |
| 4 | Normal | 9 | 4 | 4 | — | — | WOUND |
| 4 | Elite | 11 | 6 | 6 | — | — | WOUND |
| 5 | Normal | 10 | 4 | 5 | — | — | WOUND |
| 5 | Elite | 14 | 6 | 6 | — | — | WOUND |
| 6 | Normal | 11 | 5 | 5 | — | — | WOUND |
| 6 | Elite | 15 | 6 | 7 | — | — | WOUND |
| 7 | Normal | 14 | 5 | 5 | — | — | WOUND |
| 7 | Elite | 18 | 6 | 7 | — | — | WOUND |

### Ability-Karten

#### Karte 1 — Initiative 12

**Kartennummer:** 548 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 13

**Kartennummer:** 549 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK -1
MOVE -1
ATTACK -1
```

#### Karte 3 — Initiative 25

**Kartennummer:** 550 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 39

**Kartennummer:** 551 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 5 — Initiative 54

**Kartennummer:** 552 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -2
ATTACK -1
RANGE 3
TARGET 2
POISON
```

#### Karte 6 — Initiative 59

**Kartennummer:** 553 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -2
ATTACK +1
TARGET 2
```

#### Karte 7 — Initiative 72

**Kartennummer:** 554 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK -1
ATTACK -1
ATTACK -2
```

#### Karte 8 — Initiative 06

**Kartennummer:** 555 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 2
HEAL 2
Self
STRENGTHEN
Self
```

## Savvas Icestorm

**Ability-Deck:** Savvas Icestorm · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 7 | 2 | 2 | 3 | — | PIERCE 3 |
| 0 | Elite | 12 | 2 | 3 | 4 | — | PIERCE 3 |
| 1 | Normal | 10 | 2 | 2 | 4 | — | PIERCE 3 |
| 1 | Elite | 12 | 2 | 3 | 5 | — | PIERCE 3; SHIELD 1 |
| 2 | Normal | 12 | 3 | 2 | 4 | — | PIERCE 3 |
| 2 | Elite | 15 | 3 | 3 | 5 | — | PIERCE 3; SHIELD 1 |
| 3 | Normal | 12 | 3 | 3 | 4 | — | PIERCE 3; SHIELD 1 |
| 3 | Elite | 18 | 3 | 4 | 6 | — | PIERCE 3; SHIELD 1 |
| 4 | Normal | 14 | 3 | 3 | 5 | — | PIERCE 3; SHIELD 1 |
| 4 | Elite | 19 | 4 | 4 | 6 | — | PIERCE 3; SHIELD 2 |
| 5 | Normal | 16 | 3 | 4 | 5 | — | PIERCE 3; SHIELD 1 |
| 5 | Elite | 21 | 4 | 5 | 6 | — | PIERCE 3; SHIELD 2 |
| 6 | Normal | 16 | 3 | 4 | 5 | — | PIERCE 3; SHIELD 2 |
| 6 | Elite | 23 | 4 | 6 | 6 | — | PIERCE 3; SHIELD 2 |
| 7 | Normal | 17 | 4 | 4 | 6 | — | PIERCE 3; SHIELD 2 |
| 7 | Elite | 24 | 4 | 6 | 6 | — | PIERCE 4; SHIELD 3 |

### Ability-Karten

#### Karte 1 — Initiative 70

**Kartennummer:** 708 · **Mischsymbol (SHUFFLE):** Nein

```text
PUSH 2
Target all adjacent enemies
CONSUME AIR: PUSH 4 instead
ATTACK +1
RANGE +1
```

#### Karte 2 — Initiative 98

**Kartennummer:** 709 · **Mischsymbol (SHUFFLE):** Nein

```text
Summon normal Wind Demon
INFUSE AIR
```

#### Karte 3 — Initiative 98

**Kartennummer:** 710 · **Mischsymbol (SHUFFLE):** Nein

```text
Summon normal Frost Demon
INFUSE ICE
```

#### Karte 4 — Initiative 19

**Kartennummer:** 711 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
RANGE -1
INFUSE ICE
SHIELD 1
Affect self and all allies within RANGE 2
```

#### Karte 5 — Initiative 14

**Kartennummer:** 712 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
CONSUME ICE: +2 ATTACK, IMMOBILIZE
RETALIATE 2
INFUSE AIR
```

#### Karte 6 — Initiative 14

**Kartennummer:** 713 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 4
HEAL 2
RANGE 3
CONSUME ICE: +3 HEAL
CONSUME AIR: ATTACK +0
```

#### Karte 7 — Initiative 47

**Kartennummer:** 714 · **Mischsymbol (SHUFFLE):** Ja

```text
DISARM
Target all adjacent enemies
MOVE +0
ATTACK -1
INFUSE AIR
```

#### Karte 8 — Initiative 35

**Kartennummer:** 715 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK -1 [AOE 1]
INFUSE ICE
INFUSE AIR
```

**AOE 1 — 5 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (2, -2), (2, -1), (1, 0), (2, 0)]`.

## Savvas Lavaflow

**Ability-Deck:** Savvas Lavaflow · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 8 | 3 | 2 | — | — | — |
| 0 | Elite | 13 | 3 | 3 | — | — | — |
| 1 | Normal | 9 | 3 | 2 | — | — | POISON |
| 1 | Elite | 15 | 3 | 3 | — | — | WOUND |
| 2 | Normal | 11 | 3 | 3 | — | — | POISON |
| 2 | Elite | 18 | 3 | 3 | — | — | POISON; WOUND |
| 3 | Normal | 14 | 3 | 3 | — | — | POISON |
| 3 | Elite | 21 | 3 | 4 | — | — | POISON; WOUND |
| 4 | Normal | 16 | 3 | 4 | — | — | POISON |
| 4 | Elite | 24 | 4 | 4 | — | — | POISON; WOUND |
| 5 | Normal | 18 | 3 | 4 | — | — | POISON; WOUND |
| 5 | Elite | 27 | 4 | 5 | — | — | POISON; WOUND |
| 6 | Normal | 20 | 4 | 4 | — | — | POISON; WOUND |
| 6 | Elite | 30 | 4 | 6 | — | — | POISON; WOUND |
| 7 | Normal | 24 | 4 | 4 | — | — | POISON; WOUND |
| 7 | Elite | 35 | 4 | 6 | — | — | POISON; WOUND |

### Ability-Karten

#### Karte 1 — Initiative 97

**Kartennummer:** 716 · **Mischsymbol (SHUFFLE):** Nein

```text
Summon normal Flame Demon
INFUSE FIRE
```

#### Karte 2 — Initiative 97

**Kartennummer:** 717 · **Mischsymbol (SHUFFLE):** Nein

```text
Summon normal Earth Demon
INFUSE EARTH
```

#### Karte 3 — Initiative 22

**Kartennummer:** 718 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
Target all adjacent enemies
CONSUME FIRE: RETALIATE 3
```

#### Karte 4 — Initiative 68

**Kartennummer:** 719 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK +1
RANGE 3
All allies and enemies adjacent to the target suffer 2 damage.
INFUSE EARTH
```

#### Karte 5 — Initiative 41

**Kartennummer:** 720 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1 [AOE 1]
CONSUME EARTH: +2 ATTACK, IMMOBILIZE
```

**AOE 1 — 3 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(0, 1), (0, 2), (0, 3)]`.

#### Karte 6 — Initiative 51

**Kartennummer:** 721 · **Mischsymbol (SHUFFLE):** Nein

```text
All enemies suffer 2 damage.
CONSUME FIRE: WOUND all enemies
CONSUME EARTH: DISARM all enemies
```

#### Karte 7 — Initiative 31

**Kartennummer:** 722 · **Mischsymbol (SHUFFLE):** Nein

```text
HEAL 4
RANGE 3
CONSUME EARTH: TARGET 3
```

#### Karte 8 — Initiative 68

**Kartennummer:** 723 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK -1
RANGE 3
TARGET 2
INFUSE FIRE
```

## Spitting Drake

**Ability-Deck:** Spitting Drake · **Flying-Symbol neben dem Namen:** Ja

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 3 | 3 | 3 | — | FLYING |
| 0 | Elite | 8 | 3 | 4 | 4 | — | FLYING |
| 1 | Normal | 6 | 3 | 3 | 3 | — | FLYING |
| 1 | Elite | 9 | 3 | 4 | 4 | — | FLYING; MUDDLE |
| 2 | Normal | 8 | 3 | 3 | 3 | — | FLYING; MUDDLE |
| 2 | Elite | 10 | 3 | 5 | 4 | — | FLYING; MUDDLE |
| 3 | Normal | 8 | 3 | 4 | 4 | — | FLYING; MUDDLE |
| 3 | Elite | 12 | 3 | 5 | 5 | — | FLYING; MUDDLE |
| 4 | Normal | 9 | 4 | 4 | 4 | — | FLYING; MUDDLE |
| 4 | Elite | 14 | 4 | 5 | 5 | — | FLYING; MUDDLE |
| 5 | Normal | 12 | 4 | 4 | 4 | — | FLYING; MUDDLE |
| 5 | Elite | 16 | 4 | 6 | 5 | — | FLYING; MUDDLE |
| 6 | Normal | 13 | 4 | 5 | 4 | — | FLYING; MUDDLE |
| 6 | Elite | 19 | 4 | 6 | 5 | — | FLYING; MUDDLE |
| 7 | Normal | 16 | 4 | 5 | 4 | — | FLYING; MUDDLE |
| 7 | Elite | 21 | 4 | 7 | 5 | — | FLYING; MUDDLE |

### Ability-Karten

#### Karte 1 — Initiative 32

**Kartennummer:** 556 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 2 — Initiative 52

**Kartennummer:** 557 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 3 — Initiative 57

**Kartennummer:** 558 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK -1 [AOE 1]
RANGE -1
```

**AOE 1 — 3 rote Hexes:** Kein graues Monster-Hex; `(0, 0)` ist ein rotes Referenz-Hex. Rote Ziel-Hexes: `[(0, 0), (-1, 1), (0, 1)]`.

#### Karte 4 — Initiative 27

**Kartennummer:** 559 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +0
TARGET 2
POISON
```

#### Karte 5 — Initiative 87

**Kartennummer:** 560 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 6 — Initiative 89

**Kartennummer:** 561 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -2
STUN
```

#### Karte 7 — Initiative 06

**Kartennummer:** 562 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 2
HEAL 2
Self
STRENGTHEN
Self
```

#### Karte 8 — Initiative 89

**Kartennummer:** 563 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK -2 [AOE 1]
RANGE -2
POISON
```

**AOE 1 — 7 rote Hexes:** Kein graues Monster-Hex; `(0, 0)` ist ein rotes Referenz-Hex. Rote Ziel-Hexes: `[(0, 0), (1, 0), (0, 1), (-1, 1), (-1, 0), (0, -1), (1, -1)]`.

## Stone Golem

**Ability-Deck:** Stone Golem · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 10 | 1 | 3 | — | — | — |
| 0 | Elite | 10 | 2 | 4 | — | — | SHIELD 1 |
| 1 | Normal | 10 | 1 | 3 | — | — | SHIELD 1 |
| 1 | Elite | 11 | 2 | 4 | — | — | SHIELD 2 |
| 2 | Normal | 11 | 1 | 4 | — | — | SHIELD 1 |
| 2 | Elite | 14 | 2 | 5 | — | — | SHIELD 2 |
| 3 | Normal | 11 | 1 | 4 | — | — | SHIELD 2 |
| 3 | Elite | 15 | 2 | 5 | — | — | SHIELD 3 |
| 4 | Normal | 12 | 2 | 4 | — | — | SHIELD 2 |
| 4 | Elite | 17 | 2 | 6 | — | — | SHIELD 3 |
| 5 | Normal | 13 | 2 | 5 | — | — | SHIELD 2 |
| 5 | Elite | 19 | 3 | 6 | — | — | SHIELD 3 |
| 6 | Normal | 16 | 2 | 5 | — | — | SHIELD 2 |
| 6 | Elite | 20 | 3 | 7 | — | — | SHIELD 3 |
| 7 | Normal | 16 | 2 | 5 | — | — | SHIELD 3 |
| 7 | Elite | 21 | 3 | 7 | — | — | SHIELD 4 |

### Ability-Karten

#### Karte 1 — Initiative 11

**Kartennummer:** 740 · **Mischsymbol (SHUFFLE):** Nein

```text
RETALIATE 3
RANGE 3
```

#### Karte 2 — Initiative 28

**Kartennummer:** 741 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK +0
Stone Golem suffers 1 damage.
```

#### Karte 3 — Initiative 51

**Kartennummer:** 742 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +1
ATTACK -1
```

#### Karte 4 — Initiative 65

**Kartennummer:** 743 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 5 — Initiative 72

**Kartennummer:** 744 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK +1
RANGE 3
Stone Golem suffers 2 damage
```

#### Karte 6 — Initiative 90

**Kartennummer:** 745 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 83

**Kartennummer:** 746 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
Target all adjacent enemies
```

#### Karte 8 — Initiative 28

**Kartennummer:** 747 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -2
RANGE 3
PULL 2
IMMOBILIZE
```

## Sun Demon

**Ability-Deck:** Sun Demon · **Flying-Symbol neben dem Namen:** Ja

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 5 | 2 | 2 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 0 | Elite | 9 | 2 | 3 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 1 | Normal | 7 | 2 | 2 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 1 | Elite | 12 | 2 | 3 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 2 | Normal | 9 | 2 | 2 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 2 | Elite | 13 | 2 | 4 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 3 | Normal | 10 | 2 | 3 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 3 | Elite | 15 | 3 | 4 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 4 | Normal | 11 | 3 | 3 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 4 | Elite | 16 | 3 | 5 | — | — | FLYING; ADVANTAGE; SHIELD 1 |
| 5 | Normal | 11 | 3 | 3 | — | — | FLYING; ADVANTAGE; SHIELD 2 |
| 5 | Elite | 16 | 3 | 5 | — | — | FLYING; ADVANTAGE; SHIELD 2 |
| 6 | Normal | 12 | 3 | 4 | — | — | FLYING; ADVANTAGE; SHIELD 2 |
| 6 | Elite | 18 | 4 | 5 | — | — | FLYING; ADVANTAGE; SHIELD 2 |
| 7 | Normal | 15 | 3 | 4 | — | — | FLYING; ADVANTAGE; SHIELD 2 |
| 7 | Elite | 22 | 4 | 5 | — | — | FLYING; ADVANTAGE; SHIELD 2 |

### Ability-Karten

#### Karte 1 — Initiative 17

**Kartennummer:** 652 · **Mischsymbol (SHUFFLE):** Ja

```text
HEAL 3
RANGE 3
CONSUME LIGHT: Target all allies within range
```

#### Karte 2 — Initiative 36

**Kartennummer:** 653 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
Target all adjacent enemies
INFUSE LIGHT
```

#### Karte 3 — Initiative 36

**Kartennummer:** 654 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
Target all adjacent enemies
INFUSE LIGHT
```

#### Karte 4 — Initiative 68

**Kartennummer:** 655 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +1
INFUSE LIGHT
```

#### Karte 5 — Initiative 73

**Kartennummer:** 656 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +1
CONSUME LIGHT: HEAL 3
  Self
```

#### Karte 6 — Initiative 95

**Kartennummer:** 657 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
RANGE 4
CONSUME LIGHT: Target all enemies within range
```

#### Karte 7 — Initiative 88

**Kartennummer:** 658 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
Target all adjacent enemies
CONSUME DARK: MUDDLE
  Self
```

#### Karte 8 — Initiative 50

**Kartennummer:** 659 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
RANGE 3
CONSUME ANY ELEMENT: INFUSE LIGHT
```

## Vermling Scout

**Ability-Deck:** Scout · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 2 | 3 | 1 | — | — | — |
| 0 | Elite | 4 | 3 | 2 | — | — | — |
| 1 | Normal | 3 | 3 | 1 | — | — | — |
| 1 | Elite | 5 | 3 | 2 | — | — | — |
| 2 | Normal | 3 | 3 | 2 | — | — | — |
| 2 | Elite | 5 | 4 | 3 | — | — | — |
| 3 | Normal | 5 | 3 | 2 | — | — | — |
| 3 | Elite | 7 | 4 | 3 | — | — | — |
| 4 | Normal | 6 | 3 | 3 | — | — | — |
| 4 | Elite | 8 | 4 | 4 | — | — | — |
| 5 | Normal | 8 | 3 | 3 | — | — | — |
| 5 | Elite | 11 | 4 | 4 | — | — | — |
| 6 | Normal | 9 | 4 | 3 | — | — | — |
| 6 | Elite | 12 | 5 | 4 | — | — | — |
| 7 | Normal | 11 | 4 | 3 | — | — | — |
| 7 | Elite | 15 | 5 | 4 | — | — | — |

### Ability-Karten

#### Karte 1 — Initiative 29

**Kartennummer:** 668 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK -1
RANGE 3
```

#### Karte 2 — Initiative 40

**Kartennummer:** 669 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
```

#### Karte 3 — Initiative 53

**Kartennummer:** 670 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 4 — Initiative 54

**Kartennummer:** 671 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -2
ATTACK +0
RANGE 3
POISON
```

#### Karte 5 — Initiative 69

**Kartennummer:** 672 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 6 — Initiative 92

**Kartennummer:** 673 · **Mischsymbol (SHUFFLE):** Ja

```text
ATTACK +2
POISON
```

#### Karte 7 — Initiative 79

**Kartennummer:** 674 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
RANGE 4
TARGET 2
```

#### Karte 8 — Initiative 35

**Kartennummer:** 675 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +1
JUMP
LOOT 1
```

## Vermling Shaman

**Ability-Deck:** Shaman · **Flying-Symbol neben dem Namen:** Nein

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 2 | 2 | 1 | 3 | — | SHIELD 2 |
| 0 | Elite | 3 | 3 | 2 | 3 | — | SHIELD 2 |
| 1 | Normal | 2 | 2 | 1 | 3 | — | SHIELD 3 |
| 1 | Elite | 3 | 3 | 2 | 3 | — | SHIELD 3 |
| 2 | Normal | 3 | 2 | 1 | 4 | — | SHIELD 3 |
| 2 | Elite | 4 | 3 | 2 | 4 | — | SHIELD 3 |
| 3 | Normal | 3 | 2 | 2 | 4 | — | SHIELD 3 |
| 3 | Elite | 5 | 3 | 3 | 4 | — | SHIELD 3 |
| 4 | Normal | 3 | 3 | 2 | 4 | — | MUDDLE; SHIELD 3 |
| 4 | Elite | 5 | 3 | 3 | 4 | — | MUDDLE; SHIELD 4 |
| 5 | Normal | 4 | 3 | 3 | 4 | — | MUDDLE; SHIELD 3 |
| 5 | Elite | 6 | 3 | 4 | 4 | — | MUDDLE; SHIELD 4 |
| 6 | Normal | 5 | 3 | 4 | 4 | — | MUDDLE; SHIELD 3 |
| 6 | Elite | 6 | 3 | 4 | 4 | — | MUDDLE; SHIELD 5 |
| 7 | Normal | 7 | 3 | 4 | 4 | — | MUDDLE; SHIELD 3 |
| 7 | Elite | 8 | 3 | 4 | 4 | — | MUDDLE; SHIELD 5 |

### Ability-Karten

#### Karte 1 — Initiative 08

**Kartennummer:** 588 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
DISARM
```

#### Karte 2 — Initiative 08

**Kartennummer:** 589 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +0
IMMOBILIZE
```

#### Karte 3 — Initiative 23

**Kartennummer:** 590 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
HEAL 3
RANGE 3
```

#### Karte 4 — Initiative 23

**Kartennummer:** 591 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
HEAL 3
RANGE 3
```

#### Karte 5 — Initiative 62

**Kartennummer:** 592 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0
```

#### Karte 6 — Initiative 74

**Kartennummer:** 593 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
```

#### Karte 7 — Initiative 89

**Kartennummer:** 594 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
HEAL 1
Affect all adjacent allies
BLESS
Self
```

#### Karte 8 — Initiative 09

**Kartennummer:** 595 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +1
ATTACK -1
CURSE
TARGET 2
```

## Wind Demon

**Ability-Deck:** Wind Demon · **Flying-Symbol neben dem Namen:** Ja

### Stats — Level 0–7

| Level | Typ | HEALTH | MOVE | ATTACK | RANGE | Immunitäten | Dauerhafte Eigenschaften |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | Normal | 3 | 3 | 2 | 3 | — | FLYING; SHIELD 1 |
| 0 | Elite | 5 | 4 | 3 | 4 | — | FLYING; SHIELD 1 |
| 1 | Normal | 3 | 3 | 2 | 3 | — | FLYING; SHIELD 2 |
| 1 | Elite | 5 | 4 | 3 | 4 | — | FLYING; SHIELD 2 |
| 2 | Normal | 4 | 4 | 2 | 3 | — | FLYING; SHIELD 2 |
| 2 | Elite | 7 | 5 | 3 | 4 | — | FLYING; SHIELD 2 |
| 3 | Normal | 5 | 4 | 3 | 3 | — | FLYING; SHIELD 2 |
| 3 | Elite | 8 | 5 | 4 | 4 | — | FLYING; SHIELD 2 |
| 4 | Normal | 7 | 4 | 3 | 3 | — | FLYING; SHIELD 2 |
| 4 | Elite | 8 | 5 | 4 | 4 | — | FLYING; DISARM; SHIELD 2 |
| 5 | Normal | 9 | 4 | 3 | 4 | — | FLYING; SHIELD 2 |
| 5 | Elite | 11 | 5 | 4 | 4 | — | FLYING; DISARM; SHIELD 2 |
| 6 | Normal | 10 | 4 | 3 | 4 | — | FLYING; SHIELD 3 |
| 6 | Elite | 12 | 5 | 4 | 4 | — | FLYING; DISARM; SHIELD 3 |
| 7 | Normal | 11 | 4 | 4 | 4 | — | FLYING; SHIELD 3 |
| 7 | Elite | 13 | 5 | 5 | 4 | — | FLYING; DISARM; SHIELD 3 |

### Ability-Karten

#### Karte 1 — Initiative 09

**Kartennummer:** 644 · **Mischsymbol (SHUFFLE):** Nein

```text
ATTACK -1
HEAL 1
Self
CONSUME AIR: INVISIBLE
  Self
```

#### Karte 2 — Initiative 21

**Kartennummer:** 645 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +0
PULL 1
INFUSE AIR
```

#### Karte 3 — Initiative 21

**Kartennummer:** 646 · **Mischsymbol (SHUFFLE):** Ja

```text
MOVE +0
ATTACK +0
PULL 1
INFUSE AIR
```

#### Karte 4 — Initiative 29

**Kartennummer:** 647 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK -1
TARGET 2
CONSUME AIR: PUSH 2
```

#### Karte 5 — Initiative 37

**Kartennummer:** 648 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE +0
ATTACK +0 [AOE 1]
CONSUME AIR: +1 ATTACK [AOE 2]
```

**AOE 1 — 3 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, -1), (2, -1), (1, 0)]`.

**AOE 2 — 6 rote Hexes:** Monster-Hexes: `[(0, 0)]`. Rote Ziel-Hexes: `[(1, 0), (2, 0), (1, 1), (0, 1), (1, -1), (2, -1)]`.

Beim Verbrauch von AIR ersetzt AOE 2 das ursprüngliche AOE 1 für diesen Angriff; es entsteht kein zusätzlicher Angriff.

#### Karte 6 — Initiative 43

**Kartennummer:** 649 · **Mischsymbol (SHUFFLE):** Nein

```text
MOVE -1
ATTACK +1
CONSUME AIR: TARGET 2
```

#### Karte 7 — Initiative 43

**Kartennummer:** 650 · **Mischsymbol (SHUFFLE):** Nein

```text
PUSH 1
Target all adjacent enemies
ATTACK +0
CONSUME EARTH: -2 RANGE
```

#### Karte 8 — Initiative 02

**Kartennummer:** 651 · **Mischsymbol (SHUFFLE):** Nein

```text
SHIELD 1
MOVE -1
ATTACK -1
CONSUME ANY ELEMENT: INFUSE AIR
```

## Quellen und Umfang

Primärquelle für Stat-Werte, Kartentexte, Flying-Symbole und Mischsymbole: die 88 Screenshots aus dem korrigierten `pictures(1).zip` sowie die zwei nachgereichten Frost-Demon-Screenshots `Screenshot 2026-10-10 091531.png` und `Screenshot 2026-10-10 091537.png`. Die englischen Texte bleiben in Englisch. Ergänzende technische Gegenprüfung: [Monster-Stat-Daten](https://github.com/mwinckler/GloomhavenMonsterStats) und [Gloomy Companion](https://github.com/johreh/gloomycompanion). Abweichungen solcher Hilfsdaten wurden anhand der Screenshots aufgelöst.

Regelabgleich: [Gloomhaven Rule Book](https://online.flippingbook.com/view/642368833) und [Aufbau der Monster-Ability-Karten](https://rules.dized.com/game/I7lEsCGOS2-zgol-ZRNf3g/rZP3Ty3sSVOpccipl4Hwnw/monster-ability-card-breakdown).

Living Corpse und Living Spirit sind im gelieferten Bildsatz nicht enthalten und deshalb nicht als eigene Monsterabschnitte aufgenommen. Ihre Namen können trotzdem in Summon-Effekten vorkommen.
