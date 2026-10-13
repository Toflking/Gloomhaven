# Project design goals

The engine is guided by two non-negotiable product goals:

1. Undo is a core feature. Every relevant gameplay state, including future
   randomness and deck state, must be restorable without replaying a round.
2. The digital implementation should reproduce the physical board game rules
   one-to-one. Difficult cards and scenario-specific rules must remain
   representable instead of being silently simplified or omitted.

These goals imply that game truth belongs in the rules/scenario state, content
must not be coupled to its original character owner, and exceptional card or
scenario behavior needs explicit extension points. Unsupported behavior should
fail visibly until it is implemented correctly.

