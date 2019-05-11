package observer;

import gameobject.Ball;
import gameobject.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}