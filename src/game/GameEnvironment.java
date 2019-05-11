package game;

import gameobject.Collidable;
import gameobject.Paddle;
import geometry.Line;
import geometry.Point;
import utilts.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to describe game environment.
 * The class will be a collection of many objects a gameobject.Ball
 * can collide with.
 * The ball will know the game environment,
 * and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    private Paddle paddle;

    /**
     * A constructor, create an empty list of collidable objects.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Add a new collidable to the environment.
     *
     * @param c - a new collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable from the environment.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * An object moves from the start of the given line to
     * the it's end
     * If the object will not collide with any of the collidables
     * in this collection, returns null. Else, returns the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the object move from the start of the line
     *                   to the end of the line.
     * @return information about the closest collision that is going to occur,
     * if the object will not collide with any of the collidables, returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Double distance = null;
        Point closestIntersection = null;
        Point intersectionPoint = null;
        Collidable collision = null;
        List<Collidable> c = new ArrayList<>(collidables);
        for (Collidable collidable : c) {
            intersectionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (intersectionPoint != null) {
                if ((distance == null) || (distance > trajectory.start().distance(intersectionPoint))) {
                    distance = trajectory.start().distance(intersectionPoint);
                    closestIntersection = intersectionPoint;
                    collision = collidable;
                }
            }
        }
        if (closestIntersection == null) {
            return null;
        }
        return new CollisionInfo(closestIntersection, collision);
    }

    /**
     * Check if a point exists inside a collidable.
     *
     * @param point - A point to in the rectangle.
     * @return - true if the point is inside the collidable else return false.
     */
    public boolean pointInsideCollidable(Point point) {
        // Goes all the collidables
        for (Collidable collidable : collidables) {
            // Check if the point is inside the collidable.
            if (collidable.getCollisionRectangle().pointInsideRectangle(point)) {
                //If so return true.
                return true;
            }
        }
        // If not return false.
        return false;
    }

    /**
     * Sets paddle.
     *
     * @param p the paddle
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    }

    /**
     * Gets paddle.
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
}