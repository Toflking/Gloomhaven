package io.github.toflking.gloomhaven.rules.figure;

public class HealthState {
    final int maximumHealth;
    int currentHealth;

    public HealthState(int maximumHealth) {
        if (maximumHealth <= 0) {
            throw new IllegalArgumentException(
                "Maximum health cannot be negative or zero"
            );
        }

        this.maximumHealth = maximumHealth;
        this.currentHealth = maximumHealth;
    }

    public HealthState(int maximumHealth, int currentHealth) {
        if (maximumHealth <= 0) {
            throw new IllegalArgumentException(
                "Maximum health cannot be negative or zero"
            );
        }
        if (currentHealth < 0 || currentHealth > maximumHealth) {
            throw new IllegalArgumentException(
                "Health must be within the figure health range"
            );
        }

        this.maximumHealth = maximumHealth;
        this.currentHealth = currentHealth;

    }

    public int currentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        if (currentHealth < 0 || currentHealth > maximumHealth) {
            throw new IllegalArgumentException(
                "Health must be within the figure health range"
            );
        }
        this.currentHealth = currentHealth;
    }

    public int maximumHealth() {
        return maximumHealth;
    }
}
