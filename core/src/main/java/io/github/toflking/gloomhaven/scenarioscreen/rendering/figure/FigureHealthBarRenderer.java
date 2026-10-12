package io.github.toflking.gloomhaven.scenarioscreen.rendering.figure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.AttackAreaPlacementInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.AttackTargetInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.HealAreaPlacementInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.HealTargetInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.ScenarioInteractionState;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.board.HexGridLayout;

public final class FigureHealthBarRenderer implements Disposable {
    private static final float BAR_WIDTH = 54f;
    private static final float BAR_HEIGHT = 7f;
    private static final float BORDER_SIZE = 2f;
    private static final float SCREEN_OFFSET_Y = 6f;
    private static final float WORLD_OFFSET_Y = 0.15f;
    private static final float HEALTH_TEXT_GAP = 7f;
    private static final float PREVIEW_TEXT_GAP = 5f;

    private static final Color BORDER_COLOR =
        new Color(0.05f, 0.05f, 0.05f, 0.9f);
    private static final Color MISSING_HEALTH_COLOR =
        new Color(0.3f, 0.06f, 0.06f, 0.9f);
    private static final Color HEALTH_COLOR =
        new Color(0.18f, 0.75f, 0.24f, 0.95f);
    private static final Color ATTACK_PREVIEW_COLOR =
        new Color(0.95f, 0.25f, 0.12f, 0.95f);
    private static final Color HEAL_PREVIEW_COLOR =
        new Color(0.25f, 0.9f, 0.55f, 0.95f);

    private final HexGridLayout gridLayout;
    private final FigureSpriteCatalog spriteCatalog;
    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final Matrix4 screenProjection = new Matrix4();
    private final Vector3 worldPosition = new Vector3();
    private final Vector3 screenPosition = new Vector3();
    private final Vector3 directionToFigure = new Vector3();

    public FigureHealthBarRenderer(
        HexGridLayout gridLayout,
        FigureSpriteCatalog spriteCatalog
    ) {
        if (gridLayout == null || spriteCatalog == null) {
            throw new IllegalArgumentException(
                "Health bar renderer dependencies cannot be null"
            );
        }

        this.gridLayout = gridLayout;
        this.spriteCatalog = spriteCatalog;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    public void render(
        ScenarioState scenario,
        Camera camera,
        Viewport viewport,
        ScenarioInteractionState interactionState
    ) {
        if (scenario == null
            || camera == null
            || viewport == null
            || interactionState == null) {
            throw new IllegalArgumentException(
                "Health bar render arguments cannot be null"
            );
        }

        screenProjection.setToOrtho2D(
            0f,
            0f,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        shapeRenderer.setProjectionMatrix(screenProjection);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Figure figure : scenario.figures()) {
            HexPosition figurePosition = scenario.positionOf(figure.id());
            drawHealthBar(
                figure,
                figurePosition,
                camera,
                viewport,
                previewFor(
                    interactionState,
                    figure,
                    figurePosition
                )
            );
        }

        shapeRenderer.end();

        spriteBatch.setProjectionMatrix(screenProjection);
        spriteBatch.begin();

        for (Figure figure : scenario.figures()) {
            HexPosition figurePosition = scenario.positionOf(figure.id());
            drawHealthText(
                figure,
                figurePosition,
                camera,
                viewport,
                previewFor(
                    interactionState,
                    figure,
                    figurePosition
                )
            );
        }

        spriteBatch.end();
    }

    private void drawHealthBar(
        Figure figure,
        HexPosition hexPosition,
        Camera camera,
        Viewport viewport,
        ActionPreview preview
    ) {
        if (!projectBarPosition(
            figure,
            hexPosition,
            camera,
            viewport
        )) {
            return;
        }

        float healthRatio = MathUtils.clamp(
            (float) figure.health().currentHealth() / figure.health().maximumHealth(),
            0f,
            1f
        );
        float left = screenPosition.x - BAR_WIDTH * 0.5f;
        float bottom = screenPosition.y + SCREEN_OFFSET_Y;

        shapeRenderer.setColor(BORDER_COLOR);
        shapeRenderer.rect(
            left - BORDER_SIZE,
            bottom - BORDER_SIZE,
            BAR_WIDTH + BORDER_SIZE * 2f,
            BAR_HEIGHT + BORDER_SIZE * 2f
        );

        shapeRenderer.setColor(MISSING_HEALTH_COLOR);
        shapeRenderer.rect(
            left,
            bottom,
            BAR_WIDTH,
            BAR_HEIGHT
        );

        shapeRenderer.setColor(HEALTH_COLOR);
        shapeRenderer.rect(
            left,
            bottom,
            BAR_WIDTH * healthRatio,
            BAR_HEIGHT
        );

        if (preview == null || preview.amount() <= 0) {
            return;
        }

        int currentHealth = figure.health().currentHealth();
        int maximumHealth = figure.health().maximumHealth();
        float previewStartRatio;
        float previewEndRatio;

        if (preview.type() == PreviewType.ATTACK) {
            previewStartRatio = MathUtils.clamp(
                (float) Math.max(0, currentHealth - preview.amount())
                    / maximumHealth,
                0f,
                healthRatio
            );
            previewEndRatio = healthRatio;
            shapeRenderer.setColor(ATTACK_PREVIEW_COLOR);
        } else {
            previewStartRatio = healthRatio;
            previewEndRatio = MathUtils.clamp(
                (float) Math.min(
                    maximumHealth,
                    currentHealth + preview.amount()
                ) / maximumHealth,
                healthRatio,
                1f
            );
            shapeRenderer.setColor(HEAL_PREVIEW_COLOR);
        }

        shapeRenderer.rect(
            left + BAR_WIDTH * previewStartRatio,
            bottom,
            BAR_WIDTH * (previewEndRatio - previewStartRatio),
            BAR_HEIGHT
        );
    }

    private void drawHealthText(
        Figure figure,
        HexPosition hexPosition,
        Camera camera,
        Viewport viewport,
        ActionPreview preview
    ) {
        if (!projectBarPosition(
            figure,
            hexPosition,
            camera,
            viewport
        )) {
            return;
        }

        String healthText = Integer.toString(figure.health().currentHealth());
        glyphLayout.setText(font, healthText);

        float barLeft = screenPosition.x - BAR_WIDTH * 0.5f;
        float barBottom = screenPosition.y + SCREEN_OFFSET_Y;
        float textX =
            barLeft - HEALTH_TEXT_GAP - glyphLayout.width;
        float textY =
            barBottom
                + BAR_HEIGHT * 0.5f
                + glyphLayout.height * 0.5f;

        font.draw(
            spriteBatch,
            glyphLayout,
            textX,
            textY
        );

        if (preview == null) {
            return;
        }

        String previewText = preview.label() + " " + preview.amount();
        glyphLayout.setText(font, previewText);
        float previewTextX =
            barLeft + (BAR_WIDTH - glyphLayout.width) * 0.5f;
        float previewTextY =
            barBottom
                + BAR_HEIGHT
                + PREVIEW_TEXT_GAP
                + glyphLayout.height;

        font.draw(
            spriteBatch,
            glyphLayout,
            previewTextX,
            previewTextY
        );
    }

    private static ActionPreview previewFor(
        ScenarioInteractionState interactionState,
        Figure figure,
        HexPosition figurePosition
    ) {
        if (interactionState instanceof AttackTargetInteraction attack) {
            return attackPreview(attack.previewFor(figure.id()));
        }
        if (interactionState instanceof HealTargetInteraction heal) {
            return healPreview(heal.previewFor(figure.id()));
        }
        if (interactionState instanceof AttackAreaPlacementInteraction attack) {
            return attackPreview(attack.previewFor(
                figure.id(),
                figurePosition
            ));
        }
        if (interactionState instanceof HealAreaPlacementInteraction heal) {
            return healPreview(heal.previewFor(
                figure.id(),
                figurePosition
            ));
        }
        return null;
    }

    private static ActionPreview attackPreview(AttackPreview preview) {
        return preview == null
            ? null
            : new ActionPreview(
                "Attack",
                Math.max(0, preview.projectedDamage()),
                PreviewType.ATTACK
            );
    }

    private static ActionPreview healPreview(HealPreview preview) {
        return preview == null
            ? null
            : new ActionPreview(
                "Heal",
                Math.max(0, preview.projectedHealing()),
                PreviewType.HEAL
            );
    }

    private boolean projectBarPosition(
        Figure figure,
        HexPosition hexPosition,
        Camera camera,
        Viewport viewport
    ) {
        FigureSpriteDefinition spriteDefinition =
            spriteCatalog.definitionFor(figure);

        gridLayout.hexToWorld(hexPosition, worldPosition);
        worldPosition.y =
            spriteDefinition.groundOffset()
                + spriteDefinition.worldHeight()
                + WORLD_OFFSET_Y;

        directionToFigure
            .set(worldPosition)
            .sub(camera.position);

        if (directionToFigure.dot(camera.direction) <= 0f) {
            return false;
        }

        screenPosition.set(worldPosition);
        camera.project(
            screenPosition,
            viewport.getScreenX(),
            viewport.getScreenY(),
            viewport.getScreenWidth(),
            viewport.getScreenHeight()
        );

        return screenPosition.z >= 0f && screenPosition.z <= 1f;
    }

    @Override
    public void dispose() {
        font.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

    private enum PreviewType {
        ATTACK,
        HEAL
    }

    private record ActionPreview(
        String label,
        int amount,
        PreviewType type
    ) {}
}
