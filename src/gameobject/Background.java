package gameobject;

import biuoop.DrawSurface;
import game.Constants;
import game.GameLevel;
import geometry.Drawable;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Background.
 */
public class Background implements Sprite {
    // Members of the class.
    private Map<Drawable, Color> fillDrawable = new LinkedHashMap<>();
    private Map<Drawable, Color> drawDrawable = new LinkedHashMap<>();
    private Image image;

    /**
     * Instantiates a new Background.
     *
     * @param c the color
     */
    public Background(Color c) {
        addFill(new Rectangle(new Point(0, 0),
                Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), c);
    }

    /**
     * Instantiates a new Background.
     *
     * @param im the im
     */
    public Background(Image im) {
        this.image = im;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Draw all fill objects
        for (Drawable drawable : fillDrawable.keySet()) {
            drawable.drawFill(d, fillDrawable.get(drawable));
        }
        // Draw all frame objects
        for (Drawable drawable : drawDrawable.keySet()) {
            drawable.drawFrame(d, drawDrawable.get(drawable));
        }

        if (this.image != null) {
            d.drawImage(0, 0, image);
        }
    }

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * Add an object to the game.
     *
     * @param g - the game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Add fill draw.
     *
     * @param drawable the drawable
     * @param color    the color of the draw
     */
    public void addFill(Drawable drawable, Color color) {
        fillDrawable.put(drawable, color);
    }

    /**
     * Add draw frame.
     *
     * @param drawable the drawable
     * @param color    the color
     */
    public void addDraw(Drawable drawable, Color color) {
        drawDrawable.put(drawable, color);
    }

}
