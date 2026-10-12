package io.github.toflking.gloomhaven.scenarioscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rules.content.cards.CardCatalog;
import io.github.toflking.gloomhaven.rules.content.cards.DebugCards;
import io.github.toflking.gloomhaven.rules.engine.ScenarioEngine;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.scenarioscreen.debug.DebugScenarioFactory;
import io.github.toflking.gloomhaven.scenarioscreen.event.ScenarioEventHandler;
import io.github.toflking.gloomhaven.scenarioscreen.input.ScenarioInputController;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.ScenarioInteractionController;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.board.HexBoardRenderer;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.board.HexGridLayout;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.camera.OrbitCameraController;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.figure.FigureHealthBarRenderer;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.figure.FigureRenderer;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.figure.FigureSpriteCatalog;
import io.github.toflking.gloomhaven.scenarioscreen.ui.ScenarioHud;

public final class ScenarioScreen extends ScreenAdapter {
    private static final float MIN_WORLD_WIDTH = 16f;
    private static final float MIN_WORLD_HEIGHT = 9f;
    private static final float HEX_SIZE = 1f;

    private final PerspectiveCamera camera;
    private final OrbitCameraController cameraController;
    private final AssetManager assetManager;
    private final HexBoardRenderer boardRenderer;
    private final FigureRenderer figureRenderer;
    private final FigureHealthBarRenderer healthBarRenderer;
    private final Viewport viewport;

    private final ScenarioHud hud;
    private final ScenarioInputController inputController;
    private final InputMultiplexer inputMultiplexer;

    private final ScenarioState scenarioState;
    private final ScenarioEngine scenarioEngine;
    private final ScenarioInteractionController interactionController;

    public ScenarioScreen(AssetManager assetManager) {
        if (assetManager == null) {
            throw new IllegalArgumentException(
                "Asset manager cannot be null"
            );
        }

        this.assetManager = assetManager;
        scenarioState = DebugScenarioFactory.create();
        scenarioEngine = new ScenarioEngine(
            scenarioState,
            new CardCatalog(DebugCards.all())
        );
        interactionController =
            new ScenarioInteractionController(scenarioState);
        ScenarioEventHandler eventHandler =
            new ScenarioEventHandler(interactionController);

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

        HexGridLayout gridLayout = new HexGridLayout(HEX_SIZE);
        boardRenderer = new HexBoardRenderer(
            gridLayout,
            DebugScenarioFactory.BOARD_RADIUS
        );

        FigureSpriteCatalog spriteCatalog =
            FigureSpriteCatalog.createDefault();
        figureRenderer = new FigureRenderer(
            assetManager,
            spriteCatalog,
            gridLayout,
            camera
        );
        healthBarRenderer = new FigureHealthBarRenderer(
            gridLayout,
            spriteCatalog
        );

        Skin skin = loadHudSkin();
        hud = new ScenarioHud(
            skin,
            command -> eventHandler.handle(
                scenarioEngine.execute(command)
            ),
            interactionController::setSelectionOption
        );
        inputController = new ScenarioInputController(
            viewport,
            boardRenderer,
            cameraController,
            interactionController::handleBoardLeftClick,
            interactionController::handleBoardRightClick,
            interactionController::rotateAreaPlacement
        );
        inputMultiplexer = new InputMultiplexer(
            hud.stage(),
            inputController
        );
    }

    private Skin loadHudSkin() {
        if (!assetManager.contains(ScenarioHud.SKIN_ASSET)) {
            assetManager.load(ScenarioHud.SKIN_ASSET, Skin.class);
        }
        assetManager.finishLoadingAsset(ScenarioHud.SKIN_ASSET);
        return assetManager.get(ScenarioHud.SKIN_ASSET, Skin.class);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.08f, 0.08f, 0.1f, 1f, true);

        assetManager.update();
        inputController.update(delta);
        interactionController.handleBoardHover(
            inputController.hoveredHex()
        );

        viewport.apply();
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        boardRenderer.render(
            camera.combined,
            inputController.hoveredHex(),
            interactionController.selectedHex(),
            interactionController.movementPath(),
            interactionController.eligibleTargetHexes(),
            interactionController.selectedTargetHexes(),
            interactionController.eligibleAreaAnchors(),
            interactionController.targetableAreaHexes(),
            interactionController.untargetableAreaHexes(),
            interactionController.invalidAreaPreviewHexes()
        );
        figureRenderer.render(scenarioState, camera);
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        healthBarRenderer.render(
            scenarioState,
            camera,
            viewport,
            interactionController.interactionState()
        );

        hud.update(
            scenarioState,
            interactionController.interactionState(),
            scenarioEngine.canUndo()
        );
        hud.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
        hud.resize(width, height);
    }

    @Override
    public void dispose() {
        hud.dispose();
        healthBarRenderer.dispose();
        figureRenderer.dispose();
        boardRenderer.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void hide() {
        inputController.reset();
        if (Gdx.input.getInputProcessor() == inputMultiplexer) {
            Gdx.input.setInputProcessor(null);
        }
    }
}
