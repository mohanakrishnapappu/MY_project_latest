package com.structurax.design.geometry;

public class GeometryUtils {

    private GeometryUtils() {
    }

    public static Rectangle2D createRectangle(
            double x,
            double y,
            double width,
            double height) {

        return new Rectangle2D(
                x,
                y,
                width,
                height
        );

    }

    public static boolean overlaps(
            Rectangle2D first,
            Rectangle2D second) {

        return first.intersects(second);

    }

    public static double distance(
            Point2D first,
            Point2D second) {

        return first.distance(second);

    }

    public static boolean insideRectangle(
            Rectangle2D rectangle,
            Point2D point) {

        return rectangle.contains(point);

    }

    public static double lineLength(
            LineSegment line) {

        return line.length();

    }

}