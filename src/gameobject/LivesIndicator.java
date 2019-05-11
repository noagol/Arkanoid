package gameobject;

import biuoop.DrawSurface;
import game.Constants;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import utilts.Counter;

import java.awt.Color;


/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    //Members of the class.
    private Counter liveCounter;
    private Rectangle rec;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param live the live
     */
    public LivesIndicator(Counter live) {
        liveCounter = live;
        rec = new Rectangle(new Point(0, 0),
                (double) Constants.SCREEN_WIDTH, Constants.BLOCK_HIGTH);
    }

    /**
     * Draw the Live indicator to the screen.
     *
     * @param d - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText((int) (rec.getUpperLeft().getX() + rec.getWidth() / 5),
                (int) (rec.getUpperLeft().getY() + (rec.getHeight() / 2) + 5),
                "Lives: " + Integer.toString(liveCounter.getValue()), 12);
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
