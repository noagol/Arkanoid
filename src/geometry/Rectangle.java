package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to describe the rectangle.
 * the rectangle is build by upper left point,width and height.
 */
public class Rectangle implements Drawable {
    // Members of the rectangle.
    private Point upperLeft;
    private double width;
    private double height;
    private List<Line> listRectangle;


    /**
     * Enum to describe the boundaries.
     */
    public enum boundary {
        /**
         * Top boundary.
         */
        TOP, /**
         * Bottom boundary.
         */
        BOTTOM, /**
         * Left boundary.
         */
        LEFT, /**
         * Right boundary.
         */
        RIGHT
    }

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft - the point of the start of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line - a line to check intersection
     * @return a list of intersection points.
     */
    public java.util.List intersectionPoints(Line line) {
        // Create a new list of points.
        ArrayList<Point> intersection = new ArrayList<>();
        // Create a list of the lines of the rectangle.
        listRectangle();
        // Goes all the lines create the rectangle.
        for (int i = 0; i < this.listRectangle.size(); i++) {
            /* Check if one of the lines of the rectangle
            intersection with another line.
            */
            if (line.isIntersecting(listRectangle.get(i))) {
                // Add the intersection point to the list.
                intersection.add(listRectangle.get(i).intersectionWith(line));
            }
        }
        // Return yhe list of the intersection.
        return (intersection);
    }

    /**
     * Create a new list of the lines of the rectangle.
     */
    public void listRectangle() {
        // Create a new list
        List<Line> line = new ArrayList<>();
        // Create a new point of the upper right of the rectangle.
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        // Create a new point of the lower left of the rectangle.
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        // Create a new point of the lower right of the rectangle.
        Point lowerRight = new Point(lowerLeft.getX() + width, lowerLeft.getY());
        // Add the line of the top of the rectangle.
        line.add(new Line(upperLeft, upperRight));
        // Add the line of the bottom of the rectangle.
        line.add(new Line(lowerLeft, lowerRight));
        // Add the line of the left side of the rectangle.
        line.add(new Line(upperLeft, lowerLeft));
        // Add the line of the right side of the rectangle.
        line.add(new Line(upperRight, lowerRight));
        this.listRectangle = line;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets list rectangle.
     *
     * @return the list of the lines of the rectangle.
     */
    public List<Line> getListRectangle() {
        return this.listRectangle;
    }

    /**
     * Check where on the rectangle a collision occur
     * and return the enum - top, bottom, left, right.
     *
     * @param collison - a collision point to check.
     * @return the place on the rectangle where a collision occur.
     */
    public boundary collisionFrame(Point collison) {
        // Check if the collision point is on the top line.
        if (this.getListRectangle().get(0).pointExists(collison)) {
            return boundary.TOP;
            // Check if the collision point is on the bottom line.
        } else if (this.getListRectangle().get(1).pointExists(collison)) {
            return boundary.BOTTOM;
            // Check if the collision point is on the left line.
        } else if (this.getListRectangle().get(2).pointExists(collison)) {
            return boundary.LEFT;
            // Check if the collision point is on the right line.
        } else if (this.getListRectangle().get(3).pointExists(collison)) {
            return boundary.RIGHT;
        } else {
            throw new RuntimeException("error");
        }
    }

    /**
     * Check if a point exists inside the rectangle.
     *
     * @param point - a point to check inside the rectangle.
     * @return - true if the point inside the rectangle else return false.
     */
    public boolean pointInsideRectangle(Point point) {
        // Check if a point exists inside the rectangle.
        return ((upperLeft.getX() <= point.getX() && point.getX() <= upperLeft.getX() + width)
                && (upperLeft.getY() <= point.getY() && point.getY() <= upperLeft.getY() + height));
    }

    /**
     * Draw fill, fill the object that draws.
     *
     * @param surface the surface
     * @param color   the color
     */
    @Override
    public void drawFill(DrawSurface surface, Color color) {
        surface.setColor(color);
        // Draw the block.
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY()
                , (int) getWidth(), (int) getHeight());
    }

    /**
     * Draw frame, draw the frame of the object.
     *
     * @param surface the surface
     * @param color   the color
     */
    @Override
    public void drawFrame(DrawSurface surface, Color color) {
        surface.setColor(color);
        // Draw the block borders.
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY()
                , (int) getWidth(), (int) getHeight());
    }

    /**
     * Sets width.
     *
     * @param newWidth the new width
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * Sets height.
     *
     * @param newHeigth the new heigth
     */
    public void setHeight(int newHeigth) {
        this.height = newHeigth;
    }

}