package observer;

import game.GameLevel;
import gameobject.Ball;
import gameobject.Block;
import utilts.Counter;

/**
 * The type blocks remover.
 * a BlockRemover is in charge of removing
 * blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    // Members of the class
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new blocks remover.
     *
     * @param game      the game
     * @param remaining the remaining
     */
    public BlockRemover(GameLevel game, Counter remaining) {
        this.gameLevel = game;
        this.remainingBlocks = remaining;
    }

    /**
     * blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel, and remove this listener from the block
     * that is being removed from the gameLevel.
     *
     * @param beingHit the block being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(gameLevel);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(1);
        }
    }
}