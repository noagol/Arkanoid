package gameobject;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Constants;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import utilts.Velocity;

import java.awt.Color;

/**
 * A class to describe a paddle.
 * The gameobject.Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    // Members of the paddle.
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int width;
    private int speed;

    /**
     * A constructor, creates a new paddle as rectangle
     * controlled by the given keyboard sensor.
     *
     * @param keyboardSensor the keyboard sensor
     * @param width          the width
     * @param speed          the speed
     */
    public Paddle(KeyboardSensor keyboardSensor, int width, int speed) {
        // Create a keyboard sensor for the current GUI.
        keyboard = keyboardSensor;
        // Create a rectangle - paddle.
        paddle = new Rectangle(new Point((Constants.SCREEN_WIDTH - width) / 2,
                Constants.SCREEN_HEIGHT - Constants.BLOCK_HIGTH),
                width, Constants.PADDLE_HIGTH);
        this.width = width;
        this.speed = speed;
    }

    /**
     * Move the paddle one step to the left (10 in each move),
     * the paddle will stop at the border.
     *
     * @param dt - the dt.
     */

    public void moveLeft(double dt) {
        // The new x after the paddle move left.
        double newX = Math.max(paddle.getUpperLeft().getX() - (speed * dt),
                Constants.BLOCK_HIGTH);
        // Move the paddle left.
        paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()),
                paddle.getWidth(), paddle.getHeight());
    }

    /**
     * Move the paddle one step to the right (10 in each move),
     * the paddle will stop at the border.
     *
     * @param dt - the dt.
     */
    public void moveRight(double dt) {
        // The new x after the paddle move right.
        double newX = Math.min(paddle.getUpperLeft().getX() + (speed * dt),
                (Constants.SCREEN_WIDTH - this.paddle.getWidth() - Constants.BLOCK_HIGTH));
        // Move the paddle right.
        paddle = new Rectangle(new Point(newX, paddle.getUpperLeft().getY()),
                paddle.getWidth(), paddle.getHeight());
    }

    /**
     * Check if the "left" or "right" keys are pressed,
     * and if so move it accordingly.
     *
     * @param dt - the dt.
     */
    public void timePassed(double dt) {
        // If the left key is pressed.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            // Move the paddle left.
            moveLeft(dt);
        }
        // If the right key is pressed.
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            // Move the paddle right.
            moveRight(dt);
        }
    }

    /**
     * Draw the paddle (rectangle).
     *
     * @param d - a surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        // Set the color of the paddle to orange.
        d.setColor(Color.decode("#F8BBD0"));
        // Draw the rectangle - paddle.
        d.fillRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY()
                , (int) paddle.getWidth(), (int) paddle.getHeight());
        // Set the color of the frame of the paddle to black.
        d.setColor(Color.black);
        // Draw the frame of the rectangle.
        d.drawRectangle((int) paddle.getUpperLeft().getX(), (int) paddle.getUpperLeft().getY()
                , (int) paddle.getWidth(), (int) paddle.getHeight());
    }

    /**
     * @return the collidable object - paddle.
     */
    public Rectangle getCollisionRectangle() {
        return paddle;
    }

    /**
     * Return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * The paddle having 5 equally-spaced regions.
     * The behavior of the ball's bounce depends on where it hits the paddle.
     *
     * @param hitter          - hitter ball
     * @param collisionPoint  - the collision point between the
     *                        rectangle and the object.
     * @param currentVelocity - the object velocity.
     * @return the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Get x of the collision point.
        double collisionX = collisionPoint.getX();
        // Calculate where on the paddle the hit occur.
        double len = (collisionX - paddle.getUpperLeft().getX()) / 100;
        // Get the new velocity od the ball.
        double newSpeed = currentVelocity.getSpeed();
        // Check if the collision is in regions 1.
        if (0 <= len && len <= 0.2) {
            // Return the new velocity.
            return Velocity.fromAngleAndSpeed(300, newSpeed);
            // Check if the collision is in regions 2.
        } else if (0.2 < len && len <= 0.4) {
            // Return the new velocity.
            return Velocity.fromAngleAndSpeed(330, newSpeed);
            // Check if the collision is in regions 3.
        } else if (0.4 < len && len <= 0.6) {
            // Return the new velocity.
            return new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            // Check if the collision is in regions 4.
        } else if (0.6 < len && len <= 0.8) {
            // Return the new velocity.
            return Velocity.fromAngleAndSpeed(30, newSpeed);
            // The collision is in regions 5.
        } else {
            // Return the new velocity.
            return Velocity.fromAngleAndSpeed(60, newSpeed);
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g - a game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}