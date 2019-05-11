package blocks;


import gameobject.Block;

/**
 * The type Basic block creator.
 */
public class BasicBlockCreator implements BlockCreator {
    /**
     * Instantiates a new Basic block creator.
     */
    public BasicBlockCreator() {
    }

    /**
     * Create a block at the specified location.
     *
     * @param x the x
     * @param y the y
     * @return the block
     */
    @Override
    public Block create(int x, int y) {
        return new Block(x, y);
    }
}
