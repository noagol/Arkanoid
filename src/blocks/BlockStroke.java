package blocks;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Block stroke.
 */
public class BlockStroke implements DrawBlock {
    private Color color;

    /**
     * Instantiates a new Block stroke.
     *
     * @param newColor the new color
     */
    public BlockStroke(Color newColor) {
        this.color = newColor;
    }
    /**
     * Draw the stroke of the block to the screen.
     *
     * @param d         a surface to draw on.
     * @param rectangle the block to draw.
     */
    @Override
    public void drawOn(DrawSurface d, Rectangle rectangle) {
        rectangle.drawFrame(d, this.color);
    }
}
