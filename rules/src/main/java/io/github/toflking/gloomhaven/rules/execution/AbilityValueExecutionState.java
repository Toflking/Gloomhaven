package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.ability.value.AbilityValue;
import io.github.toflking.gloomhaven.rules.ability.value.EvaluationMoment;

import java.util.Objects;

public final class AbilityValueExecutionState {
    private final AbilityValue value;
    private final Integer valueAtAbilityStart;
    private Integer valueAfterTargetSelection;

    public AbilityValueExecutionState(
        AbilityValue value,
        AbilityExpressionContext abilityStartContext
    ) {
        this.value = Objects.requireNonNull(value, "value");
        Objects.requireNonNull(
            abilityStartContext,
            "abilityStartContext"
        );
        this.valueAtAbilityStart =
            value.evaluationMoment() == EvaluationMoment.ABILITY_START
                ? value.resolve(abilityStartContext)
                : null;
    }

    private AbilityValueExecutionState(
        AbilityValue value,
        Integer valueAtAbilityStart,
        Integer valueAfterTargetSelection
    ) {
        this.value = Objects.requireNonNull(value, "value");
        this.valueAtAbilityStart = valueAtAbilityStart;
        this.valueAfterTargetSelection = valueAfterTargetSelection;
        validateCachedValues();
    }

    public int preview(AbilityExpressionContext context) {
        Objects.requireNonNull(context, "context");
        return switch (value.evaluationMoment()) {
            case ABILITY_START -> requireAbilityStartValue();
            case AFTER_TARGET_SELECTION ->
                valueAfterTargetSelection != null
                    ? valueAfterTargetSelection
                    : value.resolve(context);
            case PER_ATTACK, PER_TARGET -> value.resolve(context);
        };
    }

    public void resolveAfterTargetSelection(
        AbilityExpressionContext context
    ) {
        Objects.requireNonNull(context, "context");
        if (value.evaluationMoment()
            != EvaluationMoment.AFTER_TARGET_SELECTION) {
            return;
        }
        if (valueAfterTargetSelection != null) {
            throw new IllegalStateException(
                "Ability value was already resolved after target selection"
            );
        }
        valueAfterTargetSelection = value.resolve(context);
    }

    public int currentValue(
        AbilityExpressionContext context,
        EvaluationMoment perApplicationMoment
    ) {
        Objects.requireNonNull(context, "context");
        if (perApplicationMoment != EvaluationMoment.PER_ATTACK
            && perApplicationMoment != EvaluationMoment.PER_TARGET) {
            throw new IllegalArgumentException(
                "Per-application moment must be PER_ATTACK or PER_TARGET"
            );
        }

        return switch (value.evaluationMoment()) {
            case ABILITY_START -> requireAbilityStartValue();
            case AFTER_TARGET_SELECTION -> requireAfterTargetSelectionValue();
            case PER_ATTACK, PER_TARGET -> {
                if (value.evaluationMoment() != perApplicationMoment) {
                    throw new IllegalStateException(
                        "Ability value uses "
                            + value.evaluationMoment()
                            + " but this execution requires "
                            + perApplicationMoment
                    );
                }
                yield value.resolve(context);
            }
        };
    }

    public Snapshot snapshot() {
        return new Snapshot(
            value,
            valueAtAbilityStart,
            valueAfterTargetSelection
        );
    }

    public static AbilityValueExecutionState restore(Snapshot snapshot) {
        Objects.requireNonNull(snapshot, "snapshot");
        return new AbilityValueExecutionState(
            snapshot.value(),
            snapshot.valueAtAbilityStart(),
            snapshot.valueAfterTargetSelection()
        );
    }

    private int requireAbilityStartValue() {
        if (valueAtAbilityStart == null) {
            throw new IllegalStateException(
                "Ability-start value has not been resolved"
            );
        }
        return valueAtAbilityStart;
    }

    private int requireAfterTargetSelectionValue() {
        if (valueAfterTargetSelection == null) {
            throw new IllegalStateException(
                "Post-selection value has not been resolved"
            );
        }
        return valueAfterTargetSelection;
    }

    private void validateCachedValues() {
        if (value.evaluationMoment() == EvaluationMoment.ABILITY_START
            && valueAtAbilityStart == null) {
            throw new IllegalArgumentException(
                "Ability-start value is missing"
            );
        }
        if (value.evaluationMoment() != EvaluationMoment.ABILITY_START
            && valueAtAbilityStart != null) {
            throw new IllegalArgumentException(
                "Only ability-start values may have a start cache"
            );
        }
        if (value.evaluationMoment()
                != EvaluationMoment.AFTER_TARGET_SELECTION
            && valueAfterTargetSelection != null) {
            throw new IllegalArgumentException(
                "Only post-selection values may have a selection cache"
            );
        }
    }

    public record Snapshot(
        AbilityValue value,
        Integer valueAtAbilityStart,
        Integer valueAfterTargetSelection
    ) {
        public Snapshot {
            Objects.requireNonNull(value, "value");
        }
    }
}
