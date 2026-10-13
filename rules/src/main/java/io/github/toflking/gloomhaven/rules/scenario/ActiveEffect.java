package io.github.toflking.gloomhaven.rules.scenario;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.FigurePredicate;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.LineOfSightPolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetRegion;
import io.github.toflking.gloomhaven.rules.card.AbilitySource;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.execution.ExecutionMemory;

import java.util.Objects;

public record ActiveEffect(
    ActiveEffectId id,
    EffectSource source,
    EffectDuration duration,
    Effect effect
) {
    public ActiveEffect {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(duration, "duration");
        Objects.requireNonNull(effect, "effect");
    }

    public record ActiveEffectId(String value) {

        public ActiveEffectId {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException(
                    "Active effect ID cannot be blank"
                );
            }
        }
    }

    public record EffectSource(
        FigureId ownerId,
        FigureId actorId,
        FigureId controllerId,
        AbilitySource abilitySource,
        int abilityIndex,
        ExecutionMemory.Snapshot memory
    ) {
        public EffectSource {
            Objects.requireNonNull(ownerId, "ownerId");
            Objects.requireNonNull(actorId, "actorId");
            Objects.requireNonNull(controllerId, "controllerId");
            Objects.requireNonNull(abilitySource, "abilitySource");
            Objects.requireNonNull(memory, "memory");
            if (abilityIndex < 0) {
                throw new IllegalArgumentException(
                    "Ability index cannot be negative"
                );
            }
        }

        public EffectSource(
            FigureId actorId,
            AbilitySource abilitySource,
            int abilityIndex
        ) {
            this(
                actorId,
                actorId,
                actorId,
                abilitySource,
                abilityIndex,
                new ExecutionMemory().snapshot()
            );
        }
    }

    public sealed interface EffectDuration {
        record EndOfRound(int round) implements EffectDuration {
            public EndOfRound {
                if (round < 1) {
                    throw new IllegalArgumentException(
                        "Expiration round must be positive"
                    );
                }
            }
        }

        record WhileCardActive(FigureId ownerId, AbilitySource source)
            implements EffectDuration {

            public WhileCardActive {
                Objects.requireNonNull(ownerId, "ownerId");
                Objects.requireNonNull(source, "source");
            }
        }

        record Permanent() implements EffectDuration {
        }
    }

    sealed public interface Effect {
        record Shield(int amount, EffectScope scope) implements Effect {
            public Shield {
                requirePositive(amount, "Shield");
                Objects.requireNonNull(scope, "scope");
            }
        }

        record Retaliate(
            int amount,
            int range,
            LineOfSightPolicy lineOfSight,
            EffectScope scope
        ) implements Effect {
            public Retaliate {
                requirePositive(amount, "Retaliate");
                requirePositive(range, "Retaliate range");
                Objects.requireNonNull(lineOfSight, "lineOfSight");
                Objects.requireNonNull(scope, "scope");
            }

            public Retaliate(
                int amount,
                int range,
                EffectScope scope
            ) {
                this(
                    amount,
                    range,
                    LineOfSightPolicy.REQUIRED,
                    scope
                );
            }
        }
    }

    public record EffectScope(
        TargetRegion region,
        FigurePredicate figures,
        LineOfSightPolicy lineOfSight
    ) {
        public EffectScope {
            Objects.requireNonNull(region, "region");
            Objects.requireNonNull(figures, "figures");
            Objects.requireNonNull(lineOfSight, "lineOfSight");
        }
    }

    private static void requirePositive(int value, String label) {
        if (value <= 0) {
            throw new IllegalArgumentException(
                label + " must be positive"
            );
        }
    }
}
