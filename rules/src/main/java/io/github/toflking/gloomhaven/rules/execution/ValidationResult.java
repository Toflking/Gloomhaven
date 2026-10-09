package io.github.toflking.gloomhaven.rules.execution;

public record ValidationResult(boolean isValid, String rejectionReason, Integer movementSpent) {
    public static ValidationResult valid() {
        return new ValidationResult(true, null, null);
    }

    public static ValidationResult valid(int movementSpent) {
        return new ValidationResult(true, null, movementSpent);
    }

    public static ValidationResult invalid(String reason) {
        return new ValidationResult(false, reason, null);
    }
}
