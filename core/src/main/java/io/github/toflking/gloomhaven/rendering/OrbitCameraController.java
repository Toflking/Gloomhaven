package io.github.toflking.gloomhaven.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public final class OrbitCameraController {
    private static final float MOVE_SPEED = 6f;
    private static final float ROTATION_SPEED = 60f;
    private static final float MIN_DISTANCE = 4f;
    private static final float MAX_DISTANCE = 18f;
    private static final float LOOK_HEIGHT = 2.5f;

    private final PerspectiveCamera camera;
    private final Vector3 target = new Vector3();
    private final Vector3 offset = new Vector3();
    private final Vector3 forward = new Vector3();
    private final Vector3 right = new Vector3();
    private final Vector3 movement = new Vector3();

    public OrbitCameraController(PerspectiveCamera camera) {
        this.camera = camera;

        camera.position.set(8f, 10f, 8f);
        camera.near = 0.1f;
        camera.far = 100f;

        orientCamera();
        camera.update();
    }

    public void update(float delta) {
        updateMovement(delta);

        float rotation = ROTATION_SPEED * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            rotate(rotation);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            rotate(-rotation);
        }
    }

    public void zoom(float scrollAmount) {
        float distance = camera.position.dst(target);
        float newDistance = MathUtils.clamp(
            distance + scrollAmount,
            MIN_DISTANCE,
            MAX_DISTANCE
        );

        offset
            .set(camera.position)
            .sub(target)
            .nor()
            .scl(newDistance);
        camera.position
            .set(target)
            .add(offset);

        updateCamera();
    }

    private void updateMovement(float delta) {
        float forwardInput = axis(Input.Keys.S, Input.Keys.W);
        float rightInput = axis(Input.Keys.A, Input.Keys.D);

        if (forwardInput == 0f && rightInput == 0f) {
            return;
        }

        forward
            .set(camera.direction.x, 0f, camera.direction.z)
            .nor();
        right
            .set(forward)
            .crs(Vector3.Y)
            .nor();

        movement
            .setZero()
            .mulAdd(forward, forwardInput)
            .mulAdd(right, rightInput)
            .nor()
            .scl(MOVE_SPEED * delta);

        move(movement.x, movement.z);
    }

    private float axis(int negativeKey, int positiveKey) {
        float value = 0f;
        if (Gdx.input.isKeyPressed(negativeKey)) {
            value -= 1f;
        }
        if (Gdx.input.isKeyPressed(positiveKey)) {
            value += 1f;
        }
        return value;
    }

    private void move(float x, float z) {
        camera.position.add(x, 0f, z);
        target.add(x, 0f, z);
        updateCamera();
    }

    private void rotate(float degrees) {
        offset
            .set(camera.position)
            .sub(target)
            .rotate(Vector3.Y, degrees);
        camera.position
            .set(target)
            .add(offset);
        updateCamera();
    }

    private void updateCamera() {
        orientCamera();
        camera.update();
    }

    private void orientCamera() {
        camera.up.set(Vector3.Y);
        camera.lookAt(
            target.x,
            target.y + LOOK_HEIGHT,
            target.z
        );
    }
}
