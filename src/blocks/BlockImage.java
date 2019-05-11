package blocks;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Image;


/**
 * The type Block image.
 */
public class BlockImage implements DrawBlock {
    private Image image;

    /**
     * Instantiates a new Block image.
     *
     * @param newImage the new image
     */
    public BlockImage(Image newImage) {
        this.image = newImage;
    }

    /**
     * Draw the block with the image
     * to the screen.
     *
     * @param d         a surface to draw on.
     * @param rectangle the block to draw.
     */
    @Override
    public void drawOn(DrawSurface d, Rectangle rectangle) {
        d.drawImage((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(), this.image);
    }
}
