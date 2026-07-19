package io.github.toflking.gloomhaven.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rendering.HexBoardRenderer;
import io.github.toflking.gloomhaven.rendering.OrbitCameraController;
import io.github.toflking.gloomhaven.rules.hex.HexPosition;

public final class ScenarioScreen extends ScreenAdapter {
    private static final float MIN_WORLD_WIDTH = 16f;
    private static final float MIN_WORLD_HEIGHT = 9f;
    private static final float HEX_SIZE = 1f;
    private static final int BOARD_RADIUS = 2;

    private final PerspectiveCamera camera;
    private final OrbitCameraController cameraController;
    private final HexBoardRenderer boardRenderer;
    private final Viewport viewport;

    private HexPosition hoveredHex;
    private HexPosition selectedHex;

    private final InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean scrolled(float amountX, float amountY) {
            cameraController.zoom(amountY);
            return true;
        }
    };

    public ScenarioScreen() {
        camera = new PerspectiveCamera(
            55f,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        cameraController = new OrbitCameraController(camera);

        viewport = new ExtendViewport(
            MIN_WORLD_WIDTH,
            MIN_WORLD_HEIGHT,
            camera
        );
        boardRenderer = new HexBoardRenderer(HEX_SIZE, BOARD_RADIUS);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.08f, 0.08f, 0.1f, 1f);

        cameraController.update(delta);
        hoveredHex = boardRenderer.pickHex(
            viewport,
            Gdx.input.getX(),
            Gdx.input.getY()
        );
        updateSelection();

        viewport.apply();
        boardRenderer.render(
            camera.combined,
            hoveredHex,
            selectedHex
        );
    }

    private void updateSelection() {
        if (hoveredHex != null
            && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

            selectedHex = hoveredHex;
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void dispose() {
        boardRenderer.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void hide() {
        if (Gdx.input.getInputProcessor() == inputAdapter) {
            Gdx.input.setInputProcessor(null);
        }
    }
}
