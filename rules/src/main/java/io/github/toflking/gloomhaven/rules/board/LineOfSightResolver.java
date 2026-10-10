package io.github.toflking.gloomhaven.rules.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Resolves corner-to-corner line of sight on a flat-top axial hex grid. */
public final class LineOfSightResolver {
    private static final double SQRT_THREE = Math.sqrt(3.0);
    private static final double EPSILON = 1.0e-9;

    private LineOfSightResolver() {
    }

    public static boolean hasLineOfSight(
        HexBoard board,
        HexPosition source,
        HexPosition target
    ) {
        Objects.requireNonNull(board, "board");
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(target, "target");

        if (!board.contains(source) || !board.contains(target)) {
            throw new IllegalArgumentException(
                "Line-of-sight endpoints must be on the board"
            );
        }
        if (source.equals(target)) {
            return true;
        }

        List<Segment> walls = board.walls()
            .stream()
            .map(LineOfSightResolver::wallSegment)
            .toList();

        for (Point sourceCorner : corners(source)) {
            for (Point targetCorner : corners(target)) {
                Segment sightLine = new Segment(
                    sourceCorner,
                    targetCorner
                );
                if (walls.stream().noneMatch(wall ->
                    intersectsOrTouches(sightLine, wall)
                )) {
                    return true;
                }
            }
        }

        return false;
    }

    private static List<Point> corners(HexPosition position) {
        Point center = center(position);
        List<Point> result = new ArrayList<>(6);

        for (int corner = 0; corner < 6; corner++) {
            double angle = corner * Math.PI / 3.0;
            result.add(new Point(
                center.x() + Math.cos(angle),
                center.y() + Math.sin(angle)
            ));
        }

        return result;
    }

    private static Segment wallSegment(HexEdge wall) {
        Point firstCenter = center(wall.first());
        Point secondCenter = center(wall.second());
        double differenceX = secondCenter.x() - firstCenter.x();
        double differenceY = secondCenter.y() - firstCenter.y();
        double centerDistance = Math.hypot(differenceX, differenceY);
        Point midpoint = new Point(
            (firstCenter.x() + secondCenter.x()) / 2.0,
            (firstCenter.y() + secondCenter.y()) / 2.0
        );
        double perpendicularX = -differenceY / centerDistance;
        double perpendicularY = differenceX / centerDistance;

        return new Segment(
            new Point(
                midpoint.x() - perpendicularX / 2.0,
                midpoint.y() - perpendicularY / 2.0
            ),
            new Point(
                midpoint.x() + perpendicularX / 2.0,
                midpoint.y() + perpendicularY / 2.0
            )
        );
    }

    private static Point center(HexPosition position) {
        return new Point(
            1.5 * position.q(),
            SQRT_THREE * (position.r() + position.q() / 2.0)
        );
    }

    private static boolean intersectsOrTouches(
        Segment first,
        Segment second
    ) {
        double orientation1 = cross(
            first.start(),
            first.end(),
            second.start()
        );
        double orientation2 = cross(
            first.start(),
            first.end(),
            second.end()
        );
        double orientation3 = cross(
            second.start(),
            second.end(),
            first.start()
        );
        double orientation4 = cross(
            second.start(),
            second.end(),
            first.end()
        );

        if (oppositeSides(orientation1, orientation2)
            && oppositeSides(orientation3, orientation4)) {
            return true;
        }

        return (isZero(orientation1)
            && isOnSegment(first, second.start()))
            || (isZero(orientation2)
            && isOnSegment(first, second.end()))
            || (isZero(orientation3)
            && isOnSegment(second, first.start()))
            || (isZero(orientation4)
            && isOnSegment(second, first.end()));
    }

    private static boolean oppositeSides(double first, double second) {
        return (first > EPSILON && second < -EPSILON)
            || (first < -EPSILON && second > EPSILON);
    }

    private static boolean isOnSegment(Segment segment, Point point) {
        return point.x() >= Math.min(
            segment.start().x(),
            segment.end().x()
        ) - EPSILON
            && point.x() <= Math.max(
                segment.start().x(),
                segment.end().x()
            ) + EPSILON
            && point.y() >= Math.min(
                segment.start().y(),
                segment.end().y()
            ) - EPSILON
            && point.y() <= Math.max(
                segment.start().y(),
                segment.end().y()
            ) + EPSILON;
    }

    private static double cross(Point start, Point end, Point point) {
        return (end.x() - start.x()) * (point.y() - start.y())
            - (end.y() - start.y()) * (point.x() - start.x());
    }

    private static boolean isZero(double value) {
        return Math.abs(value) <= EPSILON;
    }

    private record Point(double x, double y) {
    }

    private record Segment(Point start, Point end) {
    }
}
