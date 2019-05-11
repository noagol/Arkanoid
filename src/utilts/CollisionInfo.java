package utilts;

import gameobject.Collidable;
import geometry.Point;

/**
 * A utilts.CollisionInfo class.
 * the utilts.CollisionInfo class holds information about the point
 * at which the collision occurs,
 * and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * A constructor of collision info with given collision point
     * and collidable object.
     *
     * @param collisionPoint  - the collision point.
     * @param collisionObject - the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}