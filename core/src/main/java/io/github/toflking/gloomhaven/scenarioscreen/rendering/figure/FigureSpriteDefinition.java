package io.github.toflking.gloomhaven.scenarioscreen.rendering.figure;

public record FigureSpriteDefinition(
    String assetPath,
    float worldHeight,
    float groundOffset
) {
    public FigureSpriteDefinition {
        if (assetPath == null || assetPath.isBlank()) {
            throw new IllegalArgumentException(
                "Sprite asset path cannot be blank"
            );
        }
        if (!Float.isFinite(worldHeight) || worldHeight <= 0f) {
            throw new IllegalArgumentException(
                "Sprite height must be greater than zero"
            );
        }
        if (!Float.isFinite(groundOffset)) {
            throw new IllegalArgumentException(
                "Sprite ground offset must be finite"
            );
        }
    }
}
