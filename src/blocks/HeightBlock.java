package blocks;

import gameobject.Block;

/**
 * The type Height block.
 */
public class HeightBlock extends AbstractBlockCreator {
    private int height;

    /**
     * Instantiates a new Height block.
     *
     * @param decorated the decorated
     * @param heightStr the height str
     */
    public HeightBlock(BlockCreator decorated, String heightStr) {
        super(decorated);
        this.height = Integer.parseInt(heightStr);
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
        Block b = super.create(x, y);
        // set the height
        b.setHeight(this.height);
        return b;
    }
}
