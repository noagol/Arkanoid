package geometry;

/**
 * A geometry.Point class.
 */
public class Point {
    // Two coordinate of the point.
    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x - the x coordinate
     * @param y - the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - a point to measure the distance to
     * @return return the distance of this point to the other point
     */
    public double distance(Point other) {
        // The distance between to points.
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        // Return the distance between the two points.
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other - a point to check
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double dx = other.getX();
        double dy = other.getY();
        // Return if the points equals.
        return ((this.x == dx) && (this.y == dy));
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
}