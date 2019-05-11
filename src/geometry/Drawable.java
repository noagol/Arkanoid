package geometry;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The interface Drawable.
 */
public interface Drawable {
    /**
     * Draw fill, fill the object that draws.
     *
     * @param surface the surface
     * @param color   the color
     */
    void drawFill(DrawSurface surface, Color color);

    /**
     * Draw frame, draw the frame of the object.
     *
     * @param surface the surface
     * @param color   the color
     */
    void drawFrame(DrawSurface surface, Color color);
}
