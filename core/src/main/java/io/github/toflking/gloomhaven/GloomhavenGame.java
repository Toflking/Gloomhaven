package io.github.toflking.gloomhaven;

import com.badlogic.gdx.Game;
import io.github.toflking.gloomhaven.screen.ScenarioScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public final class GloomhavenGame extends Game {

    @Override
    public void create() {
        setScreen(new ScenarioScreen());
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().dispose();
        }
    }
}
