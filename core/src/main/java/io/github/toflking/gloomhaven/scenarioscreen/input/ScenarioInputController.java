package io.github.toflking.gloomhaven.scenarioscreen.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.board.HexBoardRenderer;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.camera.OrbitCameraController;

import java.util.function.Consumer;

public final class ScenarioInputController extends InputAdapter {
    private final Viewport worldViewport;
    private final HexBoardRenderer boardRenderer;
    private final OrbitCameraController cameraController;
    private final Consumer<HexPosition> boardClickHandler;
    private final IntSet pressedKeys = new IntSet();

    private HexPosition hoveredHex;

    public ScenarioInputController(
        Viewport worldViewport,
        HexBoardRenderer boardRenderer,
        OrbitCameraController cameraController,
        Consumer<HexPosition> boardClickHandler
    ) {
        if (worldViewport == null
            || boardRenderer == null
            || cameraController == null
            || boardClickHandler == null) {

            throw new IllegalArgumentException(
                "Input controller dependencies cannot be null"
            );
        }

        this.worldViewport = worldViewport;
        this.boardRenderer = boardRenderer;
        this.cameraController = cameraController;
        this.boardClickHandler = boardClickHandler;
    }

    public void update(float delta) {
        cameraController.update(
            delta,
            axis(Input.Keys.S, Input.Keys.W),
            axis(Input.Keys.A, Input.Keys.D),
            axis(Input.Keys.E, Input.Keys.Q)
        );

        hoveredHex = pickHex(
            Gdx.input.getX(),
            Gdx.input.getY()
        );
    }

    public HexPosition hoveredHex() {
        return hoveredHex;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!isCameraKey(keycode)) {
            return false;
        }

        pressedKeys.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!isCameraKey(keycode)) {
            return false;
        }

        pressedKeys.remove(keycode);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        cameraController.zoom(amountY);
        return true;
    }

    @Override
    public boolean touchDown(
        int screenX,
        int screenY,
        int pointer,
        int button
    ) {
        if (button != Input.Buttons.LEFT) {
            return false;
        }

        HexPosition clickedHex = pickHex(screenX, screenY);
        if (clickedHex == null) {
            return false;
        }

        boardClickHandler.accept(clickedHex);
        return true;
    }

    public void reset() {
        pressedKeys.clear();
        hoveredHex = null;
    }

    private HexPosition pickHex(int screenX, int screenY) {
        return boardRenderer.pickHex(
            worldViewport,
            screenX,
            screenY
        );
    }

    private float axis(int negativeKey, int positiveKey) {
        float value = 0f;
        if (pressedKeys.contains(negativeKey)) {
            value -= 1f;
        }
        if (pressedKeys.contains(positiveKey)) {
            value += 1f;
        }
        return value;
    }

    private boolean isCameraKey(int keycode) {
        return keycode == Input.Keys.W
            || keycode == Input.Keys.A
            || keycode == Input.Keys.S
            || keycode == Input.Keys.D
            || keycode == Input.Keys.Q
            || keycode == Input.Keys.E;
    }
}
