package io.github.toflking.gloomhaven.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rendering.HexGridLayout;
import io.github.toflking.gloomhaven.rules.hex.HexPosition;

public final class ScenarioScreen extends ScreenAdapter {
    private static final float MIN_WORLD_WIDTH = 16f;
    private static final float MIN_WORLD_HEIGHT = 9f;

    private static final float HEX_SIZE = 1f;
    private static final int BOARD_RADIUS = 2;

    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    private final Vector2 mouseWorldPosition = new Vector2();
    private final HexGridLayout hexGridLayout = new HexGridLayout(HEX_SIZE);
    private HexPosition hoveredHex;
    private HexPosition selectedHex;

    private final InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean scrolled(float amountX, float amountY) {
            camera.zoom = MathUtils.clamp(
                camera.zoom + amountY * 0.1f,
                0.2f,
                4f
            );

            return true;
        }
    };

    public ScenarioScreen() {
        camera = new OrthographicCamera();

        viewport = new ExtendViewport(
            MIN_WORLD_WIDTH,
            MIN_WORLD_HEIGHT,
            camera
        );

        shapeRenderer = new ShapeRenderer();

        camera.position.set(0f, 0f, 0f);
        camera.update();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.08f, 0.08f, 0.1f, 1f);

        float deltaTime = Gdx.graphics.getDeltaTime();
        updateCamera(deltaTime);

        updateHoveredHex();
        updateInput();

        viewport.apply();
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);


        drawBoard();
    }

    private void updateCamera(float deltaTime) {
        float movementSpeed = 7f * deltaTime;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.position.y += movementSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            camera.position.y -= movementSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= movementSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += movementSpeed;
        }
    }

    private void updateHoveredHex() {
        mouseWorldPosition.set(
            Gdx.input.getX(),
            Gdx.input.getY()
        );

        viewport.unproject(mouseWorldPosition);

        HexPosition candidate = hexGridLayout.worldToHex(mouseWorldPosition);

        hoveredHex = boardContains(candidate)
            ? candidate
            : null;
    }

    private void drawBoard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        forEachBoardHex((q, r) -> {
            HexPosition position = new HexPosition(q, r);

            float x = axialToWorldX(q);
            float y = axialToWorldY(q, r);

            if (position.equals(selectedHex)) {
                shapeRenderer.setColor(Color.GOLD);
            } else if (position.equals(hoveredHex)) {
                shapeRenderer.setColor(Color.LIGHT_GRAY);
            } else {
                shapeRenderer.setColor(
                    (q + r) % 2 == 0
                        ? Color.SLATE
                        : Color.GRAY
                );
            }

            drawFilledHex(x, y, HEX_SIZE);
        });

        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);

        forEachBoardHex((q, r) -> {
            float x = axialToWorldX(q);
            float y = axialToWorldY(q, r);

            drawHexOutline(x, y, HEX_SIZE);
        });

        shapeRenderer.end();
    }

    private void updateInput() {
        if (hoveredHex != null
            && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

            selectedHex = hoveredHex;
        }
    }

    private void drawFilledHex(float centerX, float centerY, float size) {
        for (int i = 0; i < 6; i++) {
            float angle1 = i * 60f;
            float angle2 = (i + 1) * 60f;

            float x1 = centerX + MathUtils.cosDeg(angle1) * size;
            float y1 = centerY + MathUtils.sinDeg(angle1) * size;

            float x2 = centerX + MathUtils.cosDeg(angle2) * size;
            float y2 = centerY + MathUtils.sinDeg(angle2) * size;

            shapeRenderer.triangle(
                centerX, centerY,
                x1, y1,
                x2, y2
            );
        }
    }

    private void drawHexOutline(float centerX, float centerY, float size) {
        for (int i = 0; i < 6; i++) {
            float angle1 = i * 60f;
            float angle2 = (i + 1) * 60f;

            float x1 = centerX + MathUtils.cosDeg(angle1) * size;
            float y1 = centerY + MathUtils.sinDeg(angle1) * size;

            float x2 = centerX + MathUtils.cosDeg(angle2) * size;
            float y2 = centerY + MathUtils.sinDeg(angle2) * size;

            shapeRenderer.line(x1, y1, x2, y2);
        }
    }

    private float axialToWorldX(int q) {
        return HEX_SIZE * 1.5f * q;
    }

    private float axialToWorldY(int q, int r) {
        return HEX_SIZE
            * (float) Math.sqrt(3)
            * (r + q / 2f);
    }

    private void forEachBoardHex(HexConsumer consumer) {
        for (int q = -BOARD_RADIUS; q <= BOARD_RADIUS; q++) {
            int minimumR = Math.max(-BOARD_RADIUS, -q - BOARD_RADIUS);
            int maximumR = Math.min(BOARD_RADIUS, -q + BOARD_RADIUS);

            for (int r = minimumR; r <= maximumR; r++) {
                consumer.accept(q, r);
            }
        }
    }

    private boolean boardContains(HexPosition position) {
        int q = position.q();
        int r = position.r();
        int s = -q - r;

        return Math.abs(q) <= BOARD_RADIUS
            && Math.abs(r) <= BOARD_RADIUS
            && Math.abs(s) <= BOARD_RADIUS;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
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

    @FunctionalInterface
    private interface HexConsumer {
        void accept(int q, int r);
    }
}
