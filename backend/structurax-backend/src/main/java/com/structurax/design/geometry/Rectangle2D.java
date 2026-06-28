package com.structurax.design.geometry;

public class Rectangle2D {

    private double x;
    private double y;

    private double width;
    private double height;

    public Rectangle2D() {
    }

    public Rectangle2D(double x,
                       double y,
                       double width,
                       double height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getLeft() {
        return x;
    }

    public double getRight() {
        return x + width;
    }

    public double getTop() {
        return y;
    }

    public double getBottom() {
        return y + height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean contains(Point2D point) {

        return point.getX() >= getLeft()
                && point.getX() <= getRight()
                && point.getY() >= getTop()
                && point.getY() <= getBottom();
    }

    public boolean intersects(Rectangle2D other) {

        return this.getLeft() < other.getRight()
                && this.getRight() > other.getLeft()
                && this.getTop() < other.getBottom()
                && this.getBottom() > other.getTop();
    }

    public double area() {
        return width * height;
    }

    @Override
    public String toString() {

        return "[x="
                + x
                + ",y="
                + y
                + ",w="
                + width
                + ",h="
                + height
                + "]";
    }

}