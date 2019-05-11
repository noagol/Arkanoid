package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * A class to describe a line.
 */
public class Line implements Drawable {
    // Members of the class.
    private Point start;
    private Point end;
    private Double slope;


    /**
     * A constructor, gets the details of the line.
     *
     * @param start - a start point of the line
     * @param end   - an end point of the line.
     */
    public Line(Point start, Point end) {

        this.start = start;
        this.end = end;
        this.slope = calculateSlope(start, end);
    }


    /**
     * A constructor, gets the details of the line.
     *
     * @param x1 - the x of the first point.
     * @param y1 - the y of the first point.
     * @param x2 - the x of the second point.
     * @param y2 - the y of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = calculateSlope(this.start, this.end);
    }

    /**
     * Calculate the slope of the line.
     *
     * @param start1 - the start point of the line.
     * @param end1   - the end point of the line.
     * @return - the slope of the line.
     */
    public Double calculateSlope(Point start1, Point end1) {
        // if the line vertical to axis x or that the line is a point.
        if ((start1.equals(end1)) || (start1.getX() == end1.getX())) {
            // there is no slope.
            return null;
        }
        // Calculate the slope and return it.
        return ((end.getY() - start.getY()) / (end.getX() - start.getX()));
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * Calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        // Calculate x middle.
        double x = (((this.start.getX()) + (this.end.getX())) / 2);
        // Calculate y middle.
        double y = (((this.start.getY()) + (this.end.getY())) / 2);
        // Create new point of the middle.
        Point middle = new Point(x, y);
        // Return the middle point.
        return middle;
    }

    /**
     * @return the start point.
     */
    public Point start() {
        return (this.start);
    }

    /**
     * @return the end point.
     */
    public Point end() {
        return (this.end);
    }

    /**
     * @return the slope of the line.
     */
    public double slope() {
        return (this.slope);
    }

    /**
     * Check if to vertical lines - one for axis a and one
     * for axis y have an intersection point.
     *
     * @param line1 - the first line to compare.
     * @param line2 - the second line to compare
     * @return - The point of the intersection or null if they not intersection.
     */
    public Point vertical(Line line1, Line line2) {
        // If the point of the intersection exits
        if ((line1.pointExists(new Point(line1.start.getX(), line2.start.getY())))
                && (line2.pointExists(new Point(line1.start.getX(), line2.start.getY())))) {
            //return the new point
            return (new Point(line1.start.getX(), line2.start.getY()));
        }
        //If the point doesn't exists return null.
        return null;
    }

    /**
     * Check if a point is exits on a line.
     *
     * @param point - a point to check on the line.
     * @return true if the point exists on the line
     * else return false.
     */
    public boolean pointExists(Point point) {
        return (((Math.round(this.start.getX()) <= Math.round(point.getX())
                && Math.round(point.getX()) <= Math.round(this.end.getX()))
                || Math.round(this.end.getX()) <= Math.round(point.getX())
                && Math.round(point.getX()) <= Math.round(this.start.getX())))
                && ((Math.round(this.start.getY()) <= Math.round(point.getY())
                && Math.round(point.getY()) <= Math.round(this.end.getY()))
                || (Math.round(this.end.getY()) <= Math.round(point.getY())
                && Math.round(point.getY()) <= Math.round(this.start.getY())));
    }

    /**
     * Check if the to points is the same points.
     *
     * @param start1 - the start point of the line.
     * @param end1   - the end point of the line.
     * @return - true if the line is a point else return false.
     */
    public boolean isApoint(Point start1, Point end1) {
        // If the coordinate equals
        if ((start1.getX() == end1.getX()) && (start1.getY() == end1.getY())) {
            // return true - the line is a point.
            return true;
        }
        // else return false.
        return false;
    }

    /**
     * Check if a vertical line intersection with another line.
     *
     * @param line1 - a vertical line.
     * @param line2 - a line to check.
     * @return the point of the intersection if exists,
     * else return null.
     */
    public Point verticalIntersection(Line line1, Line line2) {
        // The slope of the line.
        double a = line2.slope();
        // b from y=ax+b.
        double b = -a * line2.start.getX() + line2.start.getY();
        // y from y=ax+b.
        double y = line1.start.getX() * a + b;
        // Check if the point exists on the line1.
        if ((((line2.start.getX() <= line1.start.getX()) && (line1.start.getX() <= line2.end.getX()))
                || ((line2.end.getX() <= line1.start.getX()) && (line1.start.getX() <= line2.start.getX())))
                && (((line2.start.getY() <= y) && (y <= line2.end.getY()))
                || ((line2.end.getY() <= y) && (y <= line2.start.getY())))
                && (((line1.start.getY() <= y) && (y <= line1.end.getY()))
                || ((line1.end.getY() <= y) && (y <= line1.start.getY())))) {
            return (new Point(line1.start.getX(), y));
        }
        // If the point does'nt exists return null.
        return null;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other - a line to check intersection.
     * @return - the point of the intersection if exits,
     * else return null.
     */
    public Point intersectionWith(Line other) {
        // Check if the two lines are points.
        if ((isApoint(this.start, this.end)) && isApoint(other.start, other.end)) {
            // If the points equal
            if (this.equals(other)) {
                // return the point.
                return other.start;
            }
            // If it isn't the same point return null.
            return null;
            // If the first line is a point.
        } else if (isApoint(this.start, this.end)) {
            // Check if the point exits on the other line.
            if (other.pointExists(this.start)) {
                // Return the point.
                return this.start;
            }
            // Else the point is not on the line.
            return null;
            // If the other line is a point.
        } else if (isApoint(other.start, other.end)) {
            // Check if the point exits on the first line.
            if (this.pointExists(other.start)) {
                // Return the point.
                return other.start;
            }
            // Else the point is not on the line.
            return null;
            // if the lines are parallel
        } else if (this.slope == other.slope) {
            return (null);
            // if one is parallel to axis x and one to axis y
        } else if ((this.slope == null) && (other.slope != 0)) {
            return (verticalIntersection(new Line(this.start, this.end), new Line(other.start, other.end)));
        } else if ((other.slope == null) && (this.slope != 0)) {
            return (verticalIntersection(new Line(other.start, other.end), new Line(this.start, this.end)));

        } else if ((this.slope == null) && (other.slope == 0)) {
            /* Return the point of intersection or
             null if it isn't exits.*/
            return (vertical(new Line(this.start, this.end), new Line(other.start, other.end)));
            // if one is parallel to axis x and one to axis y
        } else if ((this.slope == 0) && (other.slope == null)) {
            /* Return the point of intersection or
             null if it isn't exits.*/
            return (vertical(new Line(other.start, other.end), new Line(this.start, this.end)));
        }
        // The slope of the first line.
        double a = this.slope;
        // Calculate b from y=ax+b.
        double b = this.start.getY() - a * this.start.getX();
        // The slope of the other line.
        double c = other.slope();
        // Calculate b from y=cx+d.
        double d = other.start.getY() - c * other.start.getX();
        // Find the coordinate of the intersection.
        double x = ((d - b) / (a - c));
        double y = (a * x) + b;
        // Create the new point.
        Point newPoint = new Point(x, y);
        // Check if the point is om both sections.
        if ((this.pointExists(newPoint))
                && (other.pointExists(newPoint))) {
            // If so return the point.
            return newPoint;
        }
        // There is no intersection.
        return null;
    }

    /**
     * Checks if two lines intersect, uses the function "intersectionWith".
     * If the function "intersectionWith" returns a geometry.Point this
     * function return true, else if the function
     * "intersectionWith" return null this function return false.
     *
     * @param other - a line to check intersection
     * @return - true if the lines intersection, otherwise return false.
     */
    public boolean isIntersecting(Line other) {
        /*return true if the lines intersection,
        otherwise return false.*/
        return (this.intersectionWith(other) != null);
    }

    /**
     * Check if two lines are equal.
     *
     * @param other - a line to compare.
     * @return - true if the lines equal else return false.
     */
    public boolean equals(Line other) {
        // Check if the start points equals.
        boolean startEqual = this.start.equals(other.start);
        // Check if the end points equals.
        boolean endEqual = this.start.equals(other.end);
        // Return true if both of them equals, else return false.
        return ((startEqual) && (endEqual));
    }

    /**
     * Find the closest intersection point to the start of the line.
     * If this line does not intersect with the rectangle, return null.
     *
     * @param rect - a rectangle.
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line intersectionLine = new Line(this.start, this.end);
        // List of the lines of the rectangle.
        List intersections = rect.intersectionPoints(intersectionLine);
        // If there is no intersection.
        if (intersections.size() == 0) {
            return null;
            // If there is intersection.
        } else {
            /*Save the first distance of the first
            intersection from the line.*/
            double distance = this.start.distance((Point) intersections.get(0));
            // Save the first intersection point.
            Point closestPoint = (Point) intersections.get(0);
            // Goes all the intersection.
            for (int i = 1; i < intersections.size(); i++) {
                // Check which line have the closest intersection
                if (this.start.distance((Point) intersections.get(i)) < distance) {
                    distance = this.start.distance((Point) intersections.get(i));
                    closestPoint = (Point) intersections.get(i);
                }
            }
            // Return the closest intersection point.
            return (closestPoint);
        }
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
        surface.drawLine((int) start.getX(),
                (int) start.getY(),
                (int) end.getX(),
                (int) end.getY());
    }
    /**
     * Draw frame, draw the frame of the object,
     * uses daw fill function.
     *
     * @param surface the surface
     * @param color   the color
     */
    @Override
    public void drawFrame(DrawSurface surface, Color color) {
        drawFill(surface, color);
    }
}