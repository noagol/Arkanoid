package blocks;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Block fill.
 */
public class BlockFill implements DrawBlock {
    private Color color;

    /**
     * Instantiates a new Block fill.
     *
     * @param newColor the new color
     */
    public BlockFill(Color newColor) {
        this.color = newColor;
    }

    /**
     * Draw the fill block to the screen.
     *
     * @param d         a surface to draw on.
     * @param rectangle the block to draw.
     */
    @Override
    public void drawOn(DrawSurface d, Rectangle rectangle) {
        rectangle.drawFill(d, this.color);
    }
}
