package level;

import gameobject.Block;
import gameobject.Sprite;
import utilts.Velocity;

import java.util.List;

/**
 * The interface level information.
 */
public interface LevelInformation {
    /**
     * Number of balls.
     *
     * @return the num of the balls
     */
    int numberOfBalls();

    /**
     * Initial ball velocities and
     * add them to list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed .
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * blocks list.
     * The blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove.
     *
     * @return the num of blocks to remove.
     */
    int numberOfBlocksToRemove();

    /**
     * @return a string representation
     * of the object.
     */
    LevelInformation clone();
}