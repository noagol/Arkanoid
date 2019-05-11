package gameobject;

import geometry.Point;
import geometry.Rectangle;
import utilts.Velocity;

/**
 * A gameobject.Collidable interface.
 * The gameobject.Collidable interface will be used by
 * things that can be collided with.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint
     * with a given velocity, and change the velocity.
     *
     * @param hitter          - hitter ball
     * @param collisionPoint  - the collision point between the
     *                        rectangle and the object.
     * @param currentVelocity - the object velocity.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity);

}