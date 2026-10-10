package io.github.toflking.gloomhaven.scenarioscreen.rendering.figure;

import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.identities.CharacterIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.FigureIdentity;
import io.github.toflking.gloomhaven.rules.figure.identities.MonsterIdentity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class FigureSpriteCatalog {
    private static final Set<Class<? extends FigureIdentity>>
        SUPPORTED_IDENTITIES = Set.of(
            CharacterIdentity.class,
            MonsterIdentity.class
        );

    private final Map<Class<? extends FigureIdentity>, FigureSpriteDefinition>
        definitions;
    private final Set<String> assetPaths;

    public FigureSpriteCatalog(
        Map<Class<? extends FigureIdentity>, FigureSpriteDefinition>
            definitions
    ) {
        if (definitions == null) {
            throw new IllegalArgumentException(
                "Sprite definitions cannot be null"
            );
        }

        Map<Class<? extends FigureIdentity>, FigureSpriteDefinition> copy =
            new HashMap<>(definitions);

        for (Class<? extends FigureIdentity> identityType
            : SUPPORTED_IDENTITIES) {
            if (!copy.containsKey(identityType)) {
                throw new IllegalArgumentException(
                    "Missing sprite definition for "
                        + identityType.getSimpleName()
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
            CharacterIdentity.class,
            new FigureSpriteDefinition(
                "figures/character.png",
                2.3f,
                0.04f
            ),
            MonsterIdentity.class,
            new FigureSpriteDefinition(
                "figures/monster.png",
                2.5f,
                0.04f
            )
        ));
    }

    public FigureSpriteDefinition definitionFor(Figure figure) {
        FigureSpriteDefinition definition = definitions.get(
            figure.type().getClass()
        );
        if (definition == null) {
            throw new IllegalArgumentException(
                "No sprite definition for figure identity "
                    + figure.type().getClass().getSimpleName()
            );
        }
        return definition;
    }

    public Set<String> assetPaths() {
        return assetPaths;
    }
}
