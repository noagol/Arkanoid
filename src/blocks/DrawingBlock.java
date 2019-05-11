package blocks;

import gameobject.Block;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Drawing block.
 */
public class DrawingBlock extends AbstractBlockCreator {
    // Members of the class
    private DrawBlock drawBlock;
    private int hitPoint;

    /**
     * Instantiates a new Drawing block.
     *
     * @param decorated the decorated
     * @param hitPoint  the hit point
     * @param fill      the fill
     * @param color     the color
     */
    public DrawingBlock(BlockCreator decorated, int hitPoint, boolean fill, Color color) {
        super(decorated);
        // If draw is fill
        if (fill) {
            // set block fill
            this.drawBlock = new BlockFill(color);
        } else {
            // set block stroke
            this.drawBlock = new BlockStroke(color);
        }
        this.hitPoint = hitPoint;
    }

    /**
     * Instantiates a new Drawing block.
     *
     * @param decorated the decorated
     * @param hitPoint  the hit point
     * @param image     the image
     */
    public DrawingBlock(BlockCreator decorated, int hitPoint, Image image) {
        super(decorated);
        //set image block
        this.drawBlock = new BlockImage(image);
        this.hitPoint = hitPoint;
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
        b.addDraw(this.drawBlock, this.hitPoint);
        return b;
    }
}
