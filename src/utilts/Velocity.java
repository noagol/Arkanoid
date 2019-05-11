package utilts;

import geometry.Point;

/**
 * utilts.Velocity specifies the change in position
 * on the `x` and the `y` axes.
 */
public class Velocity {
    // Members of the class.
    private double dx;
    private double dy;

    /**
     * A constructor that uses the dx and dy given to be
     * saved as the utilts.Velocity.
     *
     * @param dx - saved as the utilts.Velocity's dx.
     * @param dy - saved as the utilts.Velocity's dy.
     */
    public Velocity(double dx, double dy) {
        //save the dx and dy.
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Uses the angle and speed to create the velocity
     * using Geometric functions to find dx and dy.
     * Create a new utilts.Velocity object and return it.
     *
     * @param angle - the direction of the velocity.
     * @param speed - the size of the speed.
     * @return the new velocity of the ball.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Calculate the dx.
        double dx = Math.round((Math.cos(Math.toRadians(angle) - Math.PI / 2) * speed));
        // Calculate the dy.
        double dy = Math.round((Math.sin(Math.toRadians(angle) - Math.PI / 2) * speed));
        // Return the new velocity.
        return new Velocity(dx, dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p  - a point to change.
     * @param dt the dt
     * @return the new point after changing thr position.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + (this.dx * dt), p.getY() + (this.dy * dt));
    }


    /**
     * Calculate the velocity by x ,y.
     *
     * @return the new velocity.
     */
    public double getSpeed() {
        // Calculate the pow of x.
        double powX = Math.pow(this.dx, 2);
        // Calculate the pow of y.
        double powY = Math.pow(this.dy, 2);
        // Calculate the new speed.
        double speed = Math.sqrt(powX + powY);
        // Return the new speed.
        return speed;
    }
}