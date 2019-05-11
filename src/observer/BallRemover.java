package observer;

import game.GameLevel;
import gameobject.Ball;
import gameobject.Block;
import utilts.Counter;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    // Members of the class
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel      the game level
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Charge of removing balls, and updating
     * an availabe-balls counter.
     *
     * @param beingHit the block being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(gameLevel);
    }
}
