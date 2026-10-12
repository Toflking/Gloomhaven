package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.ability.attack.targeting.DuplicatePolicy;
import io.github.toflking.gloomhaven.rules.ability.attack.targeting.TargetSelectionMode;
import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.HealPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TargetInteractionPreviewTest {
    private static final FigureId ACTOR = new FigureId("actor");
    private static final FigureId TARGET = new FigureId("target");

    @Test
    void attackPreviewRequiresSelectionOrHover() {
        AttackPreview preview = new AttackPreview(
            3,
            0,
            0,
            0,
            0,
            false,
            3
        );
        AttackTargetInteraction interaction =
            new AttackTargetInteraction(
                ACTOR,
                selection(),
                Map.of(TARGET, preview)
            );

        assertNull(interaction.previewFor(TARGET));

        interaction.hoverTarget(TARGET);
        assertEquals(preview, interaction.previewFor(TARGET));

        interaction.hoverTarget(null);
        assertNull(interaction.previewFor(TARGET));

        interaction.getSelectedTargets().add(TARGET);
        assertEquals(preview, interaction.previewFor(TARGET));
    }

    @Test
    void healPreviewRequiresSelectionOrHover() {
        HealPreview preview = new HealPreview(3, false, 3);
        HealTargetInteraction interaction = new HealTargetInteraction(
            ACTOR,
            selection(),
            Map.of(TARGET, preview)
        );

        assertNull(interaction.previewFor(TARGET));

        interaction.hoverTarget(TARGET);
        assertEquals(preview, interaction.previewFor(TARGET));

        interaction.hoverTarget(null);
        assertNull(interaction.previewFor(TARGET));

        interaction.getSelectedTargets().add(TARGET);
        assertEquals(preview, interaction.previewFor(TARGET));
    }

    private static FigureTargetSelection.FigureTargets selection() {
        return new FigureTargetSelection.FigureTargets(
            Set.of(TARGET),
            0,
            1,
            DuplicatePolicy.DISTINCT_TARGETS,
            TargetSelectionMode.MANUAL
        );
    }
}
