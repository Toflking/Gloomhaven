package io.github.toflking.gloomhaven.scenarioscreen.rendering.figure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.toflking.gloomhaven.rules.conditions.Conditions;
import io.github.toflking.gloomhaven.rules.conditions.Conditions.ConditionId;
import io.github.toflking.gloomhaven.rules.figure.Figure;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.DamagePreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.ConditionPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.EffectPreview;
import io.github.toflking.gloomhaven.rules.scenario.ScenarioState;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.AttackAreaPlacementInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.AttackTargetInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.HealAreaPlacementInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.HealTargetInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.DamageAreaPlacementInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.DamageTargetInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.ConditionAreaPlacementInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.ConditionTargetInteraction;
import io.github.toflking.gloomhaven.scenarioscreen.interaction.ScenarioInteractionState;
import io.github.toflking.gloomhaven.scenarioscreen.rendering.board.HexGridLayout;

import java.util.ArrayList;
import java.util.List;

public final class FigureHealthBarRenderer implements Disposable {
    private static final float BAR_WIDTH = 96f;
    private static final float BAR_HEIGHT = 11f;
    private static final float BORDER_SIZE = 3f;
    private static final float SCREEN_OFFSET_Y = 10f;
    private static final float WORLD_OFFSET_Y = 0.2f;
    private static final float HEALTH_TEXT_GAP = 10f;
    private static final float PREVIEW_TEXT_GAP = 8f;
    private static final float CONDITION_ICON_SIZE = 28f;
    private static final float CONDITION_ICON_GAP = 4f;
    private static final float CONDITION_ICON_TOP_GAP = 7f;
    private static final int CONDITION_ICONS_PER_ROW = 4;

    private static final List<ConditionIcon> CONDITION_ICONS = List.of(
        new ConditionIcon(
            Conditions.DISARM.id(),
            "conditions/icons/disarm.png"
        ),
        new ConditionIcon(
            Conditions.IMMOBILIZE.id(),
            "conditions/icons/immobilize.png"
        ),
        new ConditionIcon(
            Conditions.MUDDLE.id(),
            "conditions/icons/muddle.png"
        ),
        new ConditionIcon(
            Conditions.POISON.id(),
            "conditions/icons/poison.png"
        ),
        new ConditionIcon(
            Conditions.STRENGTHEN.id(),
            "conditions/icons/strengthen.png"
        ),
        new ConditionIcon(
            Conditions.STUN.id(),
            "conditions/icons/stun.png"
        ),
        new ConditionIcon(
            Conditions.INVISIBLE.id(),
            "conditions/icons/invisible.png"
        ),
        new ConditionIcon(
            Conditions.WOUND.id(),
            "conditions/icons/wound.png"
        )
    );

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
    private static final Color ADDED_CONDITION_TINT =
        new Color(0.65f, 1f, 0.78f, 1f);
    private static final Color REMOVED_CONDITION_TINT =
        new Color(1f, 0.48f, 0.48f, 1f);

    private final HexGridLayout gridLayout;
    private final FigureSpriteCatalog spriteCatalog;
    private final AssetManager assets;
    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private final GlyphLayout glyphLayout = new GlyphLayout();
    private final Matrix4 screenProjection = new Matrix4();
    private final Vector3 worldPosition = new Vector3();
    private final Vector3 screenPosition = new Vector3();
    private final Vector3 directionToFigure = new Vector3();
    private float previewPulseSeconds;

    public FigureHealthBarRenderer(
        AssetManager assets,
        HexGridLayout gridLayout,
        FigureSpriteCatalog spriteCatalog
    ) {
        if (assets == null
            || gridLayout == null
            || spriteCatalog == null) {
            throw new IllegalArgumentException(
                "Health bar renderer dependencies cannot be null"
            );
        }

        this.assets = assets;
        this.gridLayout = gridLayout;
        this.spriteCatalog = spriteCatalog;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.35f);
        queueConditionIcons();
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

        previewPulseSeconds += Gdx.graphics.getDeltaTime();

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
            drawConditionIcons(
                figure,
                figurePosition,
                camera,
                viewport,
                effectPreviewFor(
                    interactionState,
                    figure,
                    figurePosition
                )
            );
        }

        spriteBatch.end();
    }

    private void queueConditionIcons() {
        TextureLoader.TextureParameter parameters =
            new TextureLoader.TextureParameter();
        parameters.minFilter = Texture.TextureFilter.Linear;
        parameters.magFilter = Texture.TextureFilter.Linear;

        for (ConditionIcon icon : CONDITION_ICONS) {
            if (!assets.contains(icon.assetPath())) {
                assets.load(
                    icon.assetPath(),
                    Texture.class,
                    parameters
                );
            }
        }
    }

    private void drawConditionIcons(
        Figure figure,
        HexPosition hexPosition,
        Camera camera,
        Viewport viewport,
        EffectPreview effectPreview
    ) {
        if (!projectBarPosition(
            figure,
            hexPosition,
            camera,
            viewport
        )) {
            return;
        }

        List<DisplayedConditionIcon> displayedIcons = new ArrayList<>();
        EffectPreview effects = effectPreview == null
            ? EffectPreview.empty()
            : effectPreview;
        for (ConditionIcon icon : CONDITION_ICONS) {
            if (!assets.isLoaded(icon.assetPath(), Texture.class)) {
                continue;
            }

            boolean active = figure.conditions().contains(icon.condition());
            boolean removed = effects.removedConditions()
                .contains(icon.condition());
            boolean added = effects.addedConditions()
                .contains(icon.condition());

            if (active) {
                displayedIcons.add(new DisplayedConditionIcon(
                    icon,
                    removed
                        ? ConditionIconState.PENDING_REMOVAL
                        : ConditionIconState.ACTIVE
                ));
            } else if (added) {
                displayedIcons.add(new DisplayedConditionIcon(
                    icon,
                    ConditionIconState.PENDING_ADDITION
                ));
            }
        }

        if (displayedIcons.isEmpty()) {
            return;
        }

        float barBottom = screenPosition.y + SCREEN_OFFSET_Y;
        float firstRowY = barBottom
            - BORDER_SIZE
            - CONDITION_ICON_TOP_GAP
            - CONDITION_ICON_SIZE;

        float pulseAlpha = 0.575f
            + 0.225f * MathUtils.sin(previewPulseSeconds * 3.2f);

        for (int index = 0; index < displayedIcons.size(); index++) {
            int row = index / CONDITION_ICONS_PER_ROW;
            int column = index % CONDITION_ICONS_PER_ROW;
            int rowStart = row * CONDITION_ICONS_PER_ROW;
            int iconsInRow = Math.min(
                CONDITION_ICONS_PER_ROW,
                displayedIcons.size() - rowStart
            );
            float rowWidth = iconsInRow * CONDITION_ICON_SIZE
                + (iconsInRow - 1) * CONDITION_ICON_GAP;
            float iconX = screenPosition.x
                - rowWidth * 0.5f
                + column * (CONDITION_ICON_SIZE + CONDITION_ICON_GAP);
            float iconY = firstRowY
                - row * (CONDITION_ICON_SIZE + CONDITION_ICON_GAP);
            DisplayedConditionIcon displayed = displayedIcons.get(index);
            ConditionIcon icon = displayed.icon();

            switch (displayed.state()) {
                case ACTIVE -> spriteBatch.setColor(Color.WHITE);
                case PENDING_ADDITION -> spriteBatch.setColor(
                    ADDED_CONDITION_TINT.r,
                    ADDED_CONDITION_TINT.g,
                    ADDED_CONDITION_TINT.b,
                    pulseAlpha
                );
                case PENDING_REMOVAL -> spriteBatch.setColor(
                    REMOVED_CONDITION_TINT.r,
                    REMOVED_CONDITION_TINT.g,
                    REMOVED_CONDITION_TINT.b,
                    pulseAlpha
                );
            }

            spriteBatch.draw(
                assets.get(icon.assetPath(), Texture.class),
                iconX,
                iconY,
                CONDITION_ICON_SIZE,
                CONDITION_ICON_SIZE
            );
        }
        spriteBatch.setColor(Color.WHITE);
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

        if (preview.type() != PreviewType.HEAL) {
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
        if (interactionState instanceof DamageTargetInteraction damage) {
            return damagePreview(damage.previewFor(figure.id()));
        }
        if (interactionState instanceof DamageAreaPlacementInteraction damage) {
            return damagePreview(damage.previewFor(
                figure.id(),
                figurePosition
            ));
        }
        return null;
    }

    private static EffectPreview effectPreviewFor(
        ScenarioInteractionState interactionState,
        Figure figure,
        HexPosition figurePosition
    ) {
        if (interactionState instanceof AttackTargetInteraction attack) {
            AttackPreview preview = attack.previewFor(figure.id());
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof HealTargetInteraction heal) {
            HealPreview preview = heal.previewFor(figure.id());
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof DamageTargetInteraction damage) {
            DamagePreview preview = damage.previewFor(figure.id());
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof ConditionTargetInteraction condition) {
            ConditionPreview preview = condition.previewFor(figure.id());
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof AttackAreaPlacementInteraction attack) {
            AttackPreview preview = attack.previewFor(
                figure.id(),
                figurePosition
            );
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof HealAreaPlacementInteraction heal) {
            HealPreview preview = heal.previewFor(
                figure.id(),
                figurePosition
            );
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof DamageAreaPlacementInteraction damage) {
            DamagePreview preview = damage.previewFor(
                figure.id(),
                figurePosition
            );
            return preview == null ? null : preview.effects();
        }
        if (interactionState instanceof ConditionAreaPlacementInteraction condition) {
            ConditionPreview preview = condition.previewFor(
                figure.id(),
                figurePosition
            );
            return preview == null ? null : preview.effects();
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

    private static ActionPreview damagePreview(DamagePreview preview) {
        return preview == null
            ? null
            : new ActionPreview(
                "Damage",
                Math.max(0, preview.projectedDamage()),
                PreviewType.DAMAGE
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
        DAMAGE,
        HEAL
    }

    private record ActionPreview(
        String label,
        int amount,
        PreviewType type
    ) {}

    private record ConditionIcon(
        ConditionId condition,
        String assetPath
    ) {}

    private enum ConditionIconState {
        ACTIVE,
        PENDING_ADDITION,
        PENDING_REMOVAL
    }

    private record DisplayedConditionIcon(
        ConditionIcon icon,
        ConditionIconState state
    ) {}
}
