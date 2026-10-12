package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.attack.AreaPlacementControl;
import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.ResolvedAreaPlacement;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AttackAreaPlacementInteractionTest {
    private static final HexPosition FIRST_ANCHOR =
        new HexPosition(0, 0);
    private static final HexPosition SECOND_ANCHOR =
        new HexPosition(1, 0);

    @Test
    void pointerPlacementFollowsLocksMovesAndUnlocks() {
        AttackAreaPlacementInteraction interaction = pointerInteraction();

        interaction.previewAt(FIRST_ANCHOR);
        interaction.rotate();
        ResolvedAreaPlacement preview = interaction.currentPlacement();
        assertEquals(1, preview.option().clockwiseRotations());
        assertFalse(interaction.canConfirm());

        interaction.placeAt(FIRST_ANCHOR);
        assertEquals(preview, interaction.currentPlacement());
        assertTrue(interaction.canConfirm());

        interaction.previewAt(SECOND_ANCHOR);
        assertEquals(FIRST_ANCHOR,
            interaction.currentPlacement().option().anchor());

        interaction.placeAt(SECOND_ANCHOR);
        assertEquals(SECOND_ANCHOR,
            interaction.currentPlacement().option().anchor());
        assertEquals(1,
            interaction.currentPlacement().option().clockwiseRotations());

        interaction.unlockAt(SECOND_ANCHOR);
        assertFalse(interaction.canConfirm());
        interaction.previewAt(FIRST_ANCHOR);
        assertEquals(FIRST_ANCHOR,
            interaction.currentPlacement().option().anchor());
        assertEquals(1,
            interaction.currentPlacement().option().clockwiseRotations());
    }

    @Test
    void lockedOriginStartsPlacedAndStillRotates() {
        AttackAreaPlacementInteraction interaction = new AttackAreaPlacementInteraction(
            new FigureId("attacker"),
            selection(
                placementsFor(FIRST_ANCHOR),
                AreaPlacementControl.LOCKED_TO_ORIGIN
            ),
            Map.of()
        );

        assertTrue(interaction.canConfirm());
        assertEquals(0, interaction.rotation());

        interaction.rotate();
        interaction.unlockAt(SECOND_ANCHOR);

        assertEquals(1, interaction.rotation());
        assertEquals(FIRST_ANCHOR,
            interaction.currentPlacement().option().anchor());
        assertTrue(interaction.canConfirm());
    }

    @Test
    void exposesTargetableAndUntargetablePreviewHexes() {
        HexPosition targetable = new HexPosition(1, 0);
        HexPosition untargetable = new HexPosition(0, 1);
        ResolvedAreaPlacement placement = new ResolvedAreaPlacement(
            new FigureTargetSelection.AreaPlacementOption(
                FIRST_ANCHOR,
                0,
                false
            ),
            Set.of(targetable, untargetable),
            Set.of(targetable)
        );
        AttackAreaPlacementInteraction interaction = new AttackAreaPlacementInteraction(
            new FigureId("attacker"),
            selection(
                List.of(placement),
                AreaPlacementControl.POINTER_CONTROLLED
            ),
            Map.of()
        );

        interaction.previewAt(FIRST_ANCHOR);

        assertEquals(Set.of(targetable), interaction.targetableHexes());
        assertEquals(Set.of(untargetable), interaction.untargetableHexes());
    }

    @Test
    void previewsButDoesNotLockAnIneligiblePlacement() {
        HexPosition affected = new HexPosition(1, 0);
        ResolvedAreaPlacement placement = new ResolvedAreaPlacement(
            new FigureTargetSelection.AreaPlacementOption(
                FIRST_ANCHOR,
                0,
                false
            ),
            Set.of(affected),
            Set.of(affected),
            false
        );
        AttackAreaPlacementInteraction interaction = new AttackAreaPlacementInteraction(
            new FigureId("attacker"),
            selection(
                List.of(placement),
                AreaPlacementControl.POINTER_CONTROLLED
            ),
            Map.of()
        );

        interaction.previewAt(FIRST_ANCHOR);
        interaction.placeAt(FIRST_ANCHOR);

        assertEquals(placement, interaction.currentPlacement());
        assertEquals(Set.of(affected), interaction.invalidPreviewHexes());
        assertTrue(interaction.eligibleAnchors().isEmpty());
        assertFalse(interaction.canConfirm());
    }

    @Test
    void exposesPreviewOnlyForCurrentEligibleArea() {
        FigureId targetId = new FigureId("target");
        HexPosition targetPosition = new HexPosition(1, 0);
        AttackPreview preview = new AttackPreview(
            3,
            0,
            0,
            0,
            0,
            false,
            3
        );
        ResolvedAreaPlacement placement = new ResolvedAreaPlacement(
            new FigureTargetSelection.AreaPlacementOption(
                FIRST_ANCHOR,
                0,
                false
            ),
            Set.of(FIRST_ANCHOR, targetPosition),
            Set.of(targetPosition),
            true
        );
        AttackAreaPlacementInteraction interaction =
            new AttackAreaPlacementInteraction(
                new FigureId("attacker"),
                selection(
                    List.of(placement),
                    AreaPlacementControl.POINTER_CONTROLLED
                ),
                Map.of(targetId, preview)
            );

        assertNull(interaction.previewFor(targetId, targetPosition));

        interaction.previewAt(FIRST_ANCHOR);

        assertEquals(
            preview,
            interaction.previewFor(targetId, targetPosition)
        );
        assertNull(interaction.previewFor(targetId, FIRST_ANCHOR));
    }

    private static AttackAreaPlacementInteraction pointerInteraction() {
        return new AttackAreaPlacementInteraction(
            new FigureId("attacker"),
            selection(
                List.of(
                    placementsFor(FIRST_ANCHOR).get(0),
                    placementsFor(FIRST_ANCHOR).get(1),
                    placementsFor(SECOND_ANCHOR).get(0),
                    placementsFor(SECOND_ANCHOR).get(1)
                ),
                AreaPlacementControl.POINTER_CONTROLLED
            ),
            Map.of()
        );
    }

    private static FigureTargetSelection.AreaPlacement selection(
        List<ResolvedAreaPlacement> placements,
        AreaPlacementControl control
    ) {
        return new FigureTargetSelection.AreaPlacement(
            placements,
            control
        );
    }

    private static List<ResolvedAreaPlacement> placementsFor(
        HexPosition anchor
    ) {
        return List.of(
            placement(anchor, 0),
            placement(anchor, 1)
        );
    }

    private static ResolvedAreaPlacement placement(
        HexPosition anchor,
        int rotation
    ) {
        return new ResolvedAreaPlacement(
            new FigureTargetSelection.AreaPlacementOption(
                anchor,
                rotation,
                false
            ),
            Set.of(anchor)
        );
    }
}
