package io.github.toflking.gloomhaven.rules.board.pathfinding;

import io.github.toflking.gloomhaven.rules.ability.movement.MovementType;
import io.github.toflking.gloomhaven.rules.board.HexBoard;
import io.github.toflking.gloomhaven.rules.board.HexPosition;
import io.github.toflking.gloomhaven.rules.board.HexTile;
import io.github.toflking.gloomhaven.rules.figure.Figure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public final class HexPathfinder {
    private HexPathfinder() {
    }

    public static List<HexPosition> shortestPath(
        HexBoard board,
        HexPosition start,
        HexPosition destination,
        int amount,
        MovementType type,
        Figure movingFigure,
        Map<HexPosition, Figure> figuresByPosition
    ) {
        Objects.requireNonNull(board, "Board cannot be null");
        Objects.requireNonNull(start, "Start cannot be null");
        Objects.requireNonNull(destination, "Destination cannot be null");
        Objects.requireNonNull(type, "Movement type cannot be null");
        Objects.requireNonNull(movingFigure, "Moving figure cannot be null");
        Objects.requireNonNull(
            figuresByPosition,
            "Figures by position cannot be null"
        );

        if (amount < 0) {
            throw new IllegalArgumentException(
                "Movement amount cannot be negative"
            );
        }

        if (!board.contains(start) || !board.contains(destination)) {
            return List.of();
        }

        PriorityQueue<SearchState> openHexes =
            new PriorityQueue<>((first, second) -> {
                int dangerComparison = Integer.compare(
                    first.dangerousHexes(),
                    second.dangerousHexes()
                );

                if (dangerComparison != 0) {
                    return dangerComparison;
                }

                int trapComparison = Integer.compare(
                    first.traps(),
                    second.traps()
                );

                if (trapComparison != 0) {
                    return trapComparison;
                }

                return Integer.compare(
                    first.movementSpent(),
                    second.movementSpent()
                );
            });
        Set<SearchState> discoveredStates = new HashSet<>();
        Map<SearchState, SearchState> previousStates = new HashMap<>();

        SearchState startState = new SearchState(start, 0, 0, 0);
        openHexes.add(startState);
        discoveredStates.add(startState);

        while (!openHexes.isEmpty()) {
            SearchState current = openHexes.remove();

            if (current.position().equals(destination)) {
                return reconstructPath(previousStates, current);
            }

            for (HexPosition neighbor : current.position().neighbors()) {
                if (!board.contains(neighbor)) {
                    continue;
                }

                if (board.hasWallBetween(current.position(), neighbor)) {
                    continue;
                }

                HexTile tile = board.tileAt(neighbor);
                Figure occupant = figuresByPosition.get(neighbor);
                boolean canEnter = neighbor.equals(destination)
                    ? MovementRules.canEndAt(
                        tile,
                        type,
                        movingFigure,
                        occupant
                    )
                    : MovementRules.canTraverse(
                        tile,
                        type,
                        movingFigure,
                        occupant
                    );

                if (!canEnter) {
                    continue;
                }

                int movementSpent = current.movementSpent()
                    + MovementRules.movementCost(tile, type);

                if (movementSpent > amount) {
                    continue;
                }

                int dangerousHexes = current.dangerousHexes()
                    + MovementRules.dangerousTerrainCost(tile, type);
                int traps = current.traps()
                    + MovementRules.trapCost(tile, type);
                SearchState next = new SearchState(
                    neighbor,
                    movementSpent,
                    dangerousHexes,
                    traps
                );

                if (discoveredStates.add(next)) {
                    previousStates.put(next, current);
                    openHexes.add(next);
                }
            }
        }

        return List.of();
    }

    private static List<HexPosition> reconstructPath(
        Map<SearchState, SearchState> previousStates,
        SearchState destination
    ) {
        List<HexPosition> path = new ArrayList<>();
        SearchState current = destination;

        while (current != null) {
            path.add(current.position());
            current = previousStates.get(current);
        }

        Collections.reverse(path);
        return List.copyOf(path);
    }

    private record SearchState(
        HexPosition position,
        int movementSpent,
        int dangerousHexes,
        int traps
    ) {
    }
}
