package io.github.toflking.gloomhaven.rules.execution;

import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.List;

public record ValidationResult(boolean isValid, String rejectionReason, Integer movementSpent, List<FigureId> targets) {
    public static ValidationResult valid() {
        return new ValidationResult(true, null, null, null);
    }

    public static ValidationResult valid(int movementSpent) {
        return new ValidationResult(true, null, movementSpent, null);
    }

    public static ValidationResult valid(List<FigureId> targets) {
        return new ValidationResult(true, null, null, targets);
    }

    public static ValidationResult invalid(String reason) {
        return new ValidationResult(false, reason, null, null);
    }
}
