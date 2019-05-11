package blocks;

import gameobject.Block;

/**
 * The interface Block creator.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos the x
     * @param ypos the y
     * @return the block
     */
    Block create(int xpos, int ypos);
}