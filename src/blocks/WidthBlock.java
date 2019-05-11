package blocks;

import gameobject.Block;

/**
 * The type Width block.
 */
public class WidthBlock extends AbstractBlockCreator {
    private int width;

    /**
     * Instantiates a new Width block.
     *
     * @param decorated the decorated
     * @param widthStr  the width str
     */
    public WidthBlock(BlockCreator decorated, String widthStr) {
        super(decorated);
        this.width = Integer.parseInt(widthStr);
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
        // set the width of the block
        b.setWidth(this.width);
        return b;
    }
}
