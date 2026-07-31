package io.github.toflking.gloomhaven.scenarioscreen.rendering.figure;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.board.HexGridLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class FigureRenderer implements Disposable {
    private final AssetManager assets;
    private final FigureSpriteCatalog catalog;
    private final HexGridLayout gridLayout;
    private final CameraGroupStrategy groupStrategy;
    private final DecalBatch decalBatch;

    private final Map<FigureId, Decal> decals = new HashMap<>();
    private final Set<FigureId> visibleFigures = new HashSet<>();
    private final Vector3 worldPosition = new Vector3();
    private final Vector3 facingDirection = new Vector3();

    public FigureRenderer(
        AssetManager assets,
        FigureSpriteCatalog catalog,
        HexGridLayout gridLayout,
        Camera camera
    ) {
        if (assets == null
            || catalog == null
            || gridLayout == null
            || camera == null) {

            throw new IllegalArgumentException(
                "Figure renderer dependencies cannot be null"
            );
        }

        this.assets = assets;
        this.catalog = catalog;
        this.gridLayout = gridLayout;
        groupStrategy = new CameraGroupStrategy(camera);
        decalBatch = new DecalBatch(groupStrategy);

        queueSpriteAssets();
    }

    private void queueSpriteAssets() {
        TextureLoader.TextureParameter parameters =
            new TextureLoader.TextureParameter();
        parameters.genMipMaps = true;
        parameters.minFilter =
            Texture.TextureFilter.MipMapLinearLinear;
        parameters.magFilter = Texture.TextureFilter.Linear;

        for (String assetPath : catalog.assetPaths()) {
            if (!assets.contains(assetPath)) {
                assets.load(assetPath, Texture.class, parameters);
            }
        }
    }

    public boolean isReady() {
        for (String path : catalog.assetPaths()) {
            if (!assets.isLoaded(path, Texture.class)) {
                return false;
            }
        }
        return true;
    }

    public void render(
        ScenarioState scenario,
        Camera camera
    ) {
        if (!isReady()) {
            return;
        }

        visibleFigures.clear();

        for (Figure figure : scenario.figures()) {
            HexPosition hexPosition =
                scenario.positionOf(figure.id());
            FigureSpriteDefinition definition =
                catalog.definitionFor(figure);

            Decal decal = decals.computeIfAbsent(
                figure.id(),
                ignored -> createDecal(definition)
            );

            updateDecal(
                decal,
                definition,
                hexPosition,
                camera
            );
            visibleFigures.add(figure.id());
            decalBatch.add(decal);
        }

        decalBatch.flush();
        decals.keySet().retainAll(visibleFigures);
    }

    private Decal createDecal(
        FigureSpriteDefinition definition
    ) {
        Texture texture = assets.get(
            definition.assetPath(),
            Texture.class
        );
        float aspectRatio =
            (float) texture.getWidth() / texture.getHeight();

        return Decal.newDecal(
            definition.worldHeight() * aspectRatio,
            definition.worldHeight(),
            new TextureRegion(texture),
            true
        );
    }

    private void updateDecal(
        Decal decal,
        FigureSpriteDefinition definition,
        HexPosition hexPosition,
        Camera camera
    ) {
        gridLayout.hexToWorld(hexPosition, worldPosition);

        decal.setPosition(
            worldPosition.x,
            definition.groundOffset()
                + definition.worldHeight() * 0.5f,
            worldPosition.z
        );

        facingDirection
            .set(camera.position)
            .sub(worldPosition);
        facingDirection.y = 0f;

        if (facingDirection.isZero()) {
            facingDirection
                .set(camera.direction)
                .scl(-1f);
            facingDirection.y = 0f;
        }

        decal.setRotation(
            facingDirection.nor(),
            Vector3.Y
        );
    }

    @Override
    public void dispose() {
        decalBatch.dispose();
        groupStrategy.dispose();
    }
}
