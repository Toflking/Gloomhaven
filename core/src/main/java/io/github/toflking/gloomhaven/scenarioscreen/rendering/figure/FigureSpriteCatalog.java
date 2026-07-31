package io.github.toflking.gloomhaven.scenarioscreen.rendering.figure;

import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureType;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class FigureSpriteCatalog {
    private final Map<FigureType, FigureSpriteDefinition> definitions;
    private final Set<String> assetPaths;

    public FigureSpriteCatalog(
        Map<FigureType, FigureSpriteDefinition> definitions
    ) {
        if (definitions == null) {
            throw new IllegalArgumentException(
                "Sprite definitions cannot be null"
            );
        }

        EnumMap<FigureType, FigureSpriteDefinition> copy =
            new EnumMap<>(FigureType.class);
        copy.putAll(definitions);

        for (FigureType type : FigureType.values()) {
            if (!copy.containsKey(type)) {
                throw new IllegalArgumentException(
                    "Missing sprite definition for " + type
                );
            }
        }

        this.definitions = Map.copyOf(copy);

        Set<String> paths = new HashSet<>();
        for (FigureSpriteDefinition definition : copy.values()) {
            paths.add(definition.assetPath());
        }
        assetPaths = Set.copyOf(paths);
    }

    public static FigureSpriteCatalog createDefault() {
        return new FigureSpriteCatalog(Map.of(
            FigureType.CHARACTER,
            new FigureSpriteDefinition(
                "figures/character.png",
                2.3f,
                0.04f
            ),
            FigureType.MONSTER,
            new FigureSpriteDefinition(
                "figures/monster.png",
                2.5f,
                0.04f
            )
        ));
    }

    public FigureSpriteDefinition definitionFor(Figure figure) {
        return definitions.get(figure.type());
    }

    public Set<String> assetPaths() {
        return assetPaths;
    }
}
