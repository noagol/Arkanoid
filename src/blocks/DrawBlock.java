package blocks;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * The interface Draw block.
 */
public interface DrawBlock {

    /**
     * Draw the blocks to the screen.
     *
     * @param d         a surface to draw on.
     * @param rectangle the block to draw.
     */
     void drawOn(DrawSurface d, Rectangle rectangle);
}
