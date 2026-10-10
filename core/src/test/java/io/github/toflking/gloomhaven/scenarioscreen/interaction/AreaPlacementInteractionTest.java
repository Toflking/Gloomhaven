package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.attack.AreaPlacementControl;
import io.github.toflking.gloomhaven.rules.execution.attack.AttackChoice;
import io.github.toflking.gloomhaven.rules.execution.attack.ResolvedAreaPlacement;
import io.github.toflking.gloomhaven.rules.figure.FigureId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AreaPlacementInteractionTest {
    private static final HexPosition FIRST_ANCHOR =
        new HexPosition(0, 0);
    private static final HexPosition SECOND_ANCHOR =
        new HexPosition(1, 0);

    @Test
    void pointerPlacementFollowsLocksMovesAndUnlocks() {
        AreaPlacementInteraction interaction = pointerInteraction();

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
        AreaPlacementInteraction interaction = new AreaPlacementInteraction(
            new FigureId("attacker"),
            placementsFor(FIRST_ANCHOR),
            AreaPlacementControl.LOCKED_TO_ORIGIN
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
            new AttackChoice.AreaPlacementOption(
                FIRST_ANCHOR,
                0,
                false
            ),
            Set.of(targetable, untargetable),
            Set.of(targetable)
        );
        AreaPlacementInteraction interaction = new AreaPlacementInteraction(
            new FigureId("attacker"),
            List.of(placement),
            AreaPlacementControl.POINTER_CONTROLLED
        );

        interaction.previewAt(FIRST_ANCHOR);

        assertEquals(Set.of(targetable), interaction.targetableHexes());
        assertEquals(Set.of(untargetable), interaction.untargetableHexes());
    }

    @Test
    void previewsButDoesNotLockAnIneligiblePlacement() {
        HexPosition affected = new HexPosition(1, 0);
        ResolvedAreaPlacement placement = new ResolvedAreaPlacement(
            new AttackChoice.AreaPlacementOption(
                FIRST_ANCHOR,
                0,
                false
            ),
            Set.of(affected),
            Set.of(affected),
            false
        );
        AreaPlacementInteraction interaction = new AreaPlacementInteraction(
            new FigureId("attacker"),
            List.of(placement),
            AreaPlacementControl.POINTER_CONTROLLED
        );

        interaction.previewAt(FIRST_ANCHOR);
        interaction.placeAt(FIRST_ANCHOR);

        assertEquals(placement, interaction.currentPlacement());
        assertEquals(Set.of(affected), interaction.invalidPreviewHexes());
        assertTrue(interaction.eligibleAnchors().isEmpty());
        assertFalse(interaction.canConfirm());
    }

    private static AreaPlacementInteraction pointerInteraction() {
        return new AreaPlacementInteraction(
            new FigureId("attacker"),
            List.of(
                placementsFor(FIRST_ANCHOR).get(0),
                placementsFor(FIRST_ANCHOR).get(1),
                placementsFor(SECOND_ANCHOR).get(0),
                placementsFor(SECOND_ANCHOR).get(1)
            ),
            AreaPlacementControl.POINTER_CONTROLLED
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
            new AttackChoice.AreaPlacementOption(
                anchor,
                rotation,
                false
            ),
            Set.of(anchor)
        );
    }
}
