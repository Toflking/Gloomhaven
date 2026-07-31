package io.github.toflking.gloomhaven;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import io.github.toflking.gloomhaven.scenarioscreen.ScenarioScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public final class GloomhavenGame extends Game {
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        setScreen(new ScenarioScreen(assetManager));
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        if (assetManager != null) {
            assetManager.dispose();
        }
    }
}
