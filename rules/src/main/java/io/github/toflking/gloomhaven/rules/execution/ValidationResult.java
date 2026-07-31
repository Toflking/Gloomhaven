package io.github.toflking.gloomhaven.rules.execution;

public record ValidationResult(boolean isValid, String rejectionReason) {
    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(String reason) {
        return new ValidationResult(false, reason);
    }
}
