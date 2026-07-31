package io.github.toflking.gloomhaven.scenarioscreen.rendering.board;

import com.badlogic.gdx.math.Vector3;
import io.github.toflking.gloomhaven.rules.board.HexPosition;

public final class HexGridLayout {

    private static final float SQRT_3 = (float) Math.sqrt(3);

    private final float hexSize;

    public HexGridLayout(float hexSize) {
        if (hexSize <= 0f) {
            throw new IllegalArgumentException(
                "Hex size must be greater than zero"
            );
        }

        this.hexSize = hexSize;
    }

    public float hexSize() {
        return hexSize;
    }

    public Vector3 hexToWorld(
        HexPosition position,
        Vector3 result
    ) {
        float x =
            hexSize
                * 1.5f
                * position.q();

        float z =
            hexSize
                * SQRT_3
                * (position.r() + position.q() / 2f);

        return result.set(x, 0f, z);
    }

    public HexPosition worldToHex(float x, float z) {
        float fractionalQ =
            (2f / 3f * x)
                / hexSize;

        float fractionalR =
            (-1f / 3f * x
                + SQRT_3 / 3f * z)
                / hexSize;

        return roundAxial(fractionalQ, fractionalR);
    }

    private HexPosition roundAxial(float q, float r) {
        float x = q;
        float z = r;
        float y = -x - z;

        int roundedX = Math.round(x);
        int roundedY = Math.round(y);
        int roundedZ = Math.round(z);

        float xDifference = Math.abs(roundedX - x);
        float yDifference = Math.abs(roundedY - y);
        float zDifference = Math.abs(roundedZ - z);

        if (xDifference > yDifference
            && xDifference > zDifference) {

            roundedX = -roundedY - roundedZ;

        } else if (yDifference > zDifference) {

            roundedY = -roundedX - roundedZ;

        } else {

            roundedZ = -roundedX - roundedY;
        }

        return new HexPosition(roundedX, roundedZ);
    }
}
