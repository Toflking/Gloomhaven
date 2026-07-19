package io.github.toflking.gloomhaven.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rules.hex.HexPosition;

public final class HexBoardRenderer implements Disposable {
    private final float hexSize;
    private final int boardRadius;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final HexGridLayout gridLayout;

    private final Matrix4 boardTransform =
        new Matrix4().setToRotation(Vector3.X, 90f);
    private final Plane boardPlane = new Plane(Vector3.Y, 0f);
    private final Vector3 boardPosition = new Vector3();

    public HexBoardRenderer(float hexSize, int boardRadius) {
        if (boardRadius < 0) {
            throw new IllegalArgumentException(
                "Board radius must not be negative"
            );
        }

        this.hexSize = hexSize;
        this.boardRadius = boardRadius;
        gridLayout = new HexGridLayout(hexSize);
    }

    public HexPosition pickHex(
        Viewport viewport,
        float screenX,
        float screenY
    ) {
        Ray pickRay = viewport.getPickRay(screenX, screenY);
        if (!Intersector.intersectRayPlane(
            pickRay,
            boardPlane,
            boardPosition
        )) {
            return null;
        }

        HexPosition candidate = gridLayout.worldToHex(
            boardPosition.x,
            boardPosition.z
        );
        return contains(candidate) ? candidate : null;
    }

    public void render(
        Matrix4 projection,
        HexPosition hoveredHex,
        HexPosition selectedHex
    ) {
        shapeRenderer.setProjectionMatrix(projection);
        shapeRenderer.setTransformMatrix(boardTransform);

        drawFilledHexes(hoveredHex, selectedHex);
        drawOutlines();
    }

    private void drawFilledHexes(
        HexPosition hoveredHex,
        HexPosition selectedHex
    ) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        forEachHex((q, r) -> {
            HexPosition position = new HexPosition(q, r);

            if (position.equals(selectedHex)) {
                shapeRenderer.setColor(Color.GOLD);
            } else if (position.equals(hoveredHex)) {
                shapeRenderer.setColor(Color.LIGHT_GRAY);
            } else {
                shapeRenderer.setColor(
                    (q + r) % 2 == 0 ? Color.SLATE : Color.GRAY
                );
            }

            drawFilledHex(
                axialToWorldX(q),
                axialToWorldY(q, r)
            );
        });

        shapeRenderer.end();
    }

    private void drawOutlines() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);

        forEachHex((q, r) -> drawHexOutline(
            axialToWorldX(q),
            axialToWorldY(q, r)
        ));

        shapeRenderer.end();
    }

    private void drawFilledHex(float centerX, float centerY) {
        for (int i = 0; i < 6; i++) {
            float angle1 = i * 60f;
            float angle2 = (i + 1) * 60f;

            float x1 = centerX + MathUtils.cosDeg(angle1) * hexSize;
            float y1 = centerY + MathUtils.sinDeg(angle1) * hexSize;
            float x2 = centerX + MathUtils.cosDeg(angle2) * hexSize;
            float y2 = centerY + MathUtils.sinDeg(angle2) * hexSize;

            shapeRenderer.triangle(
                centerX, centerY,
                x1, y1,
                x2, y2
            );
        }
    }

    private void drawHexOutline(float centerX, float centerY) {
        for (int i = 0; i < 6; i++) {
            float angle1 = i * 60f;
            float angle2 = (i + 1) * 60f;

            float x1 = centerX + MathUtils.cosDeg(angle1) * hexSize;
            float y1 = centerY + MathUtils.sinDeg(angle1) * hexSize;
            float x2 = centerX + MathUtils.cosDeg(angle2) * hexSize;
            float y2 = centerY + MathUtils.sinDeg(angle2) * hexSize;

            shapeRenderer.line(x1, y1, x2, y2);
        }
    }

    private float axialToWorldX(int q) {
        return hexSize * 1.5f * q;
    }

    private float axialToWorldY(int q, int r) {
        return hexSize
            * (float) Math.sqrt(3)
            * (r + q / 2f);
    }

    private void forEachHex(HexConsumer consumer) {
        for (int q = -boardRadius; q <= boardRadius; q++) {
            int minimumR = Math.max(-boardRadius, -q - boardRadius);
            int maximumR = Math.min(boardRadius, -q + boardRadius);

            for (int r = minimumR; r <= maximumR; r++) {
                consumer.accept(q, r);
            }
        }
    }

    private boolean contains(HexPosition position) {
        int q = position.q();
        int r = position.r();
        int s = -q - r;

        return Math.abs(q) <= boardRadius
            && Math.abs(r) <= boardRadius
            && Math.abs(s) <= boardRadius;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @FunctionalInterface
    private interface HexConsumer {
        void accept(int q, int r);
    }
}
