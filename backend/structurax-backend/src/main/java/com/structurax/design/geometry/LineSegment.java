package com.structurax.design.geometry;

public class LineSegment {

    private Point2D start;
    private Point2D end;

    public LineSegment() {
    }

    public LineSegment(Point2D start,
                       Point2D end) {

        this.start = start;
        this.end = end;
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    public double length() {

        return start.distance(end);

    }

    public boolean isHorizontal() {

        return start.getY() == end.getY();

    }

    public boolean isVertical() {

        return start.getX() == end.getX();

    }

    @Override
    public String toString() {

        return start + " -> " + end;

    }

}