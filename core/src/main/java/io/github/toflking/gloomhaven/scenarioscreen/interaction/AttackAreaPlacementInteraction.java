package io.github.toflking.gloomhaven.scenarioscreen.interaction;

import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.execution.attack.AreaPlacementControl;
import io.github.toflking.gloomhaven.rules.execution.attack.FigureTargetSelection;
import io.github.toflking.gloomhaven.rules.execution.attack.ResolvedAreaPlacement;
import io.github.toflking.gloomhaven.rules.execution.attack.preview.AttackPreview;
import io.github.toflking.gloomhaven.rules.figure.FigureId;

import java.util.*;

public final class AttackAreaPlacementInteraction
    implements ScenarioInteractionState {

    private final FigureId attackerId;
    private final List<ResolvedAreaPlacement> placements;
    private final AreaPlacementControl control;
    private final List<Orientation> orientations;
    private final Map<FigureId, AttackPreview> previews;

    private HexPosition hoveredAnchor;
    private HexPosition lockedAnchor;
    private ResolvedAreaPlacement currentPlacement;
    private Orientation preferredOrientation;

    public AttackAreaPlacementInteraction(
        FigureId attackerId,
        FigureTargetSelection.AreaPlacement selection,
        Map<FigureId, AttackPreview> previews
    ) {
        this.attackerId = Objects.requireNonNull(attackerId, "actorId");
        this.placements = List.copyOf(selection.placements());
        this.control = Objects.requireNonNull(selection.control(), "control");
        orientations = this.placements.stream()
            .map(placement -> Orientation.from(placement.option()))
            .distinct()
            .toList();
        this.previews = Map.copyOf(previews);
        preferredOrientation = orientations.isEmpty()
            ? new Orientation(0, false)
            : orientations.getFirst();

        if (control == AreaPlacementControl.LOCKED_TO_ORIGIN
            && !this.placements.isEmpty()) {
            setCurrentPlacement(this.placements.getFirst());
            lockedAnchor = currentPlacement.option().anchor();
        }
    }

    public void previewAt(HexPosition anchor) {
        if (control == AreaPlacementControl.LOCKED_TO_ORIGIN
            || lockedAnchor != null) {
            return;
        }

        hoveredAnchor = anchor;
        currentPlacement = selectPlacement(anchor).orElse(null);
    }

    public void placeAt(HexPosition anchor) {
        if (control == AreaPlacementControl.LOCKED_TO_ORIGIN) {
            return;
        }

        ResolvedAreaPlacement placement = selectPlacement(anchor)
            .orElse(null);
        if (placement == null || !placement.eligible()) {
            return;
        }

        hoveredAnchor = anchor;
        lockedAnchor = anchor;
        setCurrentPlacement(placement);
    }

    public void unlockAt(HexPosition anchor) {
        if (control == AreaPlacementControl.LOCKED_TO_ORIGIN) {
            return;
        }

        lockedAnchor = null;
        hoveredAnchor = anchor;
        currentPlacement = selectPlacement(anchor).orElse(null);
    }

    public void rotate() {
        List<ResolvedAreaPlacement> atAnchor = placementsAt(
            activeAnchor()
        );

        if (!atAnchor.isEmpty()) {
            int currentIndex = atAnchor.indexOf(currentPlacement);
            int nextIndex = currentIndex < 0
                ? 0
                : (currentIndex + 1) % atAnchor.size();
            setCurrentPlacement(atAnchor.get(nextIndex));
            return;
        }

        int orientationIndex = orientations.indexOf(preferredOrientation);
        if (!orientations.isEmpty()) {
            preferredOrientation = orientations.get(
                (orientationIndex + 1) % orientations.size()
            );
        }
    }

    public void restorePlacement(
        FigureTargetSelection.AreaPlacementOption option
    ) {
        placements.stream()
            .filter(placement -> placement.option().equals(option))
            .findFirst()
            .ifPresent(placement -> {
                setCurrentPlacement(placement);
                lockedAnchor = placement.option().anchor();
                hoveredAnchor = lockedAnchor;
            });
    }

    public boolean canConfirm() {
        return currentPlacement != null
            && currentPlacement.eligible()
            && (control == AreaPlacementControl.LOCKED_TO_ORIGIN
            || lockedAnchor != null);
    }

    public boolean isPointerLocked() {
        return control == AreaPlacementControl.POINTER_CONTROLLED
            && lockedAnchor != null;
    }

    public FigureId attackerId() {
        return attackerId;
    }

    public AreaPlacementControl control() {
        return control;
    }

    public ResolvedAreaPlacement currentPlacement() {
        return currentPlacement;
    }

    public List<ResolvedAreaPlacement> placements() {
        return placements;
    }

    public AttackPreview previewFor(
        FigureId figureId,
        HexPosition figurePosition
    ) {
        if (currentPlacement == null
            || !currentPlacement.eligible()
            || !currentPlacement.targetableHexes().contains(figurePosition)) {
            return null;
        }

        return previews.get(figureId);
    }

    public Set<HexPosition> eligibleAnchors() {
        Set<HexPosition> result = new LinkedHashSet<>();
        placements.stream()
            .filter(ResolvedAreaPlacement::eligible)
            .forEach(placement ->
            result.add(placement.option().anchor())
        );
        return Set.copyOf(result);
    }

    public Set<HexPosition> targetableHexes() {
        return currentPlacement == null || !currentPlacement.eligible()
            ? Set.of()
            : currentPlacement.targetableHexes();
    }

    public Set<HexPosition> untargetableHexes() {
        if (currentPlacement == null || !currentPlacement.eligible()) {
            return Set.of();
        }

        Set<HexPosition> result = new LinkedHashSet<>(
            currentPlacement.affectedHexes()
        );
        result.removeAll(currentPlacement.targetableHexes());
        return Set.copyOf(result);
    }

    public Set<HexPosition> invalidPreviewHexes() {
        return currentPlacement == null || currentPlacement.eligible()
            ? Set.of()
            : currentPlacement.affectedHexes();
    }

    public int rotation() {
        return preferredOrientation.rotation();
    }

    public boolean mirrored() {
        return preferredOrientation.reflected();
    }

    private Optional<ResolvedAreaPlacement> selectPlacement(
        HexPosition anchor
    ) {
        if (anchor == null) {
            return Optional.empty();
        }

        List<ResolvedAreaPlacement> atAnchor = placementsAt(anchor);
        if (atAnchor.isEmpty()) {
            return Optional.empty();
        }

        return atAnchor.stream()
            .filter(placement -> preferredOrientation.equals(
                Orientation.from(placement.option())
            ))
            .findFirst()
            .or(() -> Optional.of(atAnchor.getFirst()));
    }

    private List<ResolvedAreaPlacement> placementsAt(
        HexPosition anchor
    ) {
        if (anchor == null) {
            return List.of();
        }

        return placements.stream()
            .filter(placement ->
                placement.option().anchor().equals(anchor)
            )
            .toList();
    }

    private HexPosition activeAnchor() {
        if (lockedAnchor != null) {
            return lockedAnchor;
        }
        return hoveredAnchor;
    }

    private void setCurrentPlacement(
        ResolvedAreaPlacement placement
    ) {
        currentPlacement = placement;
        preferredOrientation = Orientation.from(placement.option());
    }

    private record Orientation(int rotation, boolean reflected) {
        private static Orientation from(
            FigureTargetSelection.AreaPlacementOption option
        ) {
            return new Orientation(
                option.clockwiseRotations(),
                option.reflected()
            );
        }
    }
}
