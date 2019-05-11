package gameobject;

import biuoop.DrawSurface;
import game.Constants;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import utilts.CollisionInfo;
import utilts.Velocity;

/**
 * A class to describe a ball.
 */
public class Ball implements Sprite {
   // Members of the class
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity ballVelocity;
    private GameEnvironment game;


    /**
     * A constructor, gets the details of the ball.
     *
     * @param center - a center of a new ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     * @param game   - a new game.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.radius = r;
        this.color = color;
        //Initializing the speed.
        this.ballVelocity = new Velocity(0, 0);
        this.game = game;
    }

    /**
     * A constructor, gets the details of the ball.
     *
     * @param x     - - x coordinate of the center of the ball.
     * @param y     - y coordinate of the center of the ball.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        //Create the point of the center.
        Point newPoint = new Point(x, y);
        this.center = newPoint;
        //Initializing the speed.
        this.ballVelocity = new Velocity(0, 0);
    }

    /**
     * Gets x.
     *
     * @return the x coordinate of the center.
     */
    public int getX() {
        return ((int) this.center.getX());
    }

    /**
     * Gets y.
     *
     * @return the y coordinate of the center.
     */
    public int getY() {
        return ((int) this.center.getY());
    }

    /**
     * Gets size.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return (this.radius);
    }

    /**
     * Gets color.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return (this.color);
    }

    /**
     * Draw the ball on surface.
     *
     * @param surface - Surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        // Set the color of the ball.
        surface.setColor(this.getColor());
        // Draw the ball.
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Move the ball.
     *
     * @param dt the dt
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * Add the ball to the game.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.getBallCounter().increase(1);
    }

    /**
     * Set the velocity of the ball.
     *
     * @param v - the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.ballVelocity = v;
    }

    /**
     * Set the velocity of the ball by their change of the dx and dy.
     *
     * @param dx - a parameter of speed of x.
     * @param dy - a parameter of speed of x.
     */
    public void setVelocity(double dx, double dy) {
        // Create the velocity of the ball.
        Velocity velocity = new Velocity(dx, dy);
        // set the velocity.
        this.ballVelocity = velocity;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return (this.ballVelocity);
    }


    /**
     * Moving the ball to the next position.
     * Checks if there is a collission of the ball with one of the collision objects.
     * If so the velocity is changed according to the object being hit
     * Then the ball is moved to the next position.
     *
     * @param dt the dt
     */
    public void moveOneStep(double dt) {
        // Get the current velocity of the ball.
        Velocity currentVelocity = this.getVelocity();
        // A line to describe the move of the ball.
        Line trajectory = new Line(this.center, this.ballVelocity.applyToPoint(this.center, dt));
        // Check if there is a collision with one od the objects.
        CollisionInfo collision = this.game.getClosestCollision(trajectory);
        // If there is a collision.
        if (collision != null) {
            // Change the velocity.
            currentVelocity = collision.collisionObject().
                    hit(this, collision.collisionPoint(), currentVelocity);
            // Save the new center after the collision.
            Point newCenter = newPointAfterCollision(collision);
            // Check if the next center is'nt inside object.
            if (!game.pointInsideCollidable(newCenter)) {
                // Set the new center.
                this.center = newCenter;
            }
            // If there is'nt a collision.
        } else {
            // Set the new center.
            this.center = this.ballVelocity.applyToPoint(this.center, dt);
        }
        // Set the new velocity.
        this.setVelocity(currentVelocity);
    }

    /**
     * Find the new center point after a collision.
     *
     * @param collision - objects to collision with
     * @return the new center of the ball.
     */
    public Point newPointAfterCollision(CollisionInfo collision) {
        //Check where the collision occur.
        Rectangle.boundary boundary = collision.collisionObject().getCollisionRectangle()
                .collisionFrame(collision.collisionPoint());
        // If the collision is on the top.
        if (boundary == Rectangle.boundary.TOP) {
            // Change the new center and return it.
            return new Point(collision.collisionPoint().getX(),
                    collision.collisionPoint().getY() - Constants.RADIUS);
            // If the collision is on the bottom.
        } else if (boundary == Rectangle.boundary.BOTTOM) {
            // Change the new center and return it.
            return new Point(collision.collisionPoint().getX(),
                    collision.collisionPoint().getY() + Constants.RADIUS);
            // If the collision is on the left side.
        } else if (boundary == Rectangle.boundary.LEFT) {
            // Change the new center and return it.
            return new Point(collision.collisionPoint().getX() - Constants.RADIUS,
                    collision.collisionPoint().getY());
            // If the collision is on the right side.
        } else if (boundary == Rectangle.boundary.RIGHT) {
            // Change the new center and return it.
            return new Point(collision.collisionPoint().getX() + Constants.RADIUS,
                    collision.collisionPoint().getY());
        } else {
            throw new RuntimeException("error");
        }
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }

}