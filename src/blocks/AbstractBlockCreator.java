package blocks;

import gameobject.Block;

/**
 * The type Abstract block creator.
 */
public abstract class AbstractBlockCreator implements BlockCreator {
    private BlockCreator block;

    /**
     * Instantiates a new Abstract block creator.
     *
     * @param decorated the decorated
     */
    public AbstractBlockCreator(BlockCreator decorated) {
        this.block = decorated;
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
        return this.block.create(x, y);
    }
}
