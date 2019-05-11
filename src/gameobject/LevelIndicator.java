package gameobject;

import biuoop.DrawSurface;
import game.Constants;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type level indicator.
 */
public class LevelIndicator implements Sprite {
    //Members of the class.
    private String name;
    private Rectangle rec;

    /**
     * Instantiates a new level indicator.
     *
     * @param levelName the level name
     */
    public LevelIndicator(String levelName) {
        name = levelName;
        rec = new Rectangle(new Point(0, 0),
                (double) Constants.SCREEN_WIDTH, Constants.BLOCK_HIGTH);
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText((int) (rec.getUpperLeft().getX() + (rec.getWidth() * 0.8)),
                (int) (rec.getUpperLeft().getY() + (rec.getHeight() * 0.5) + 5),
                "level: " + name, 12);
    }

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt - the dt.
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
}
