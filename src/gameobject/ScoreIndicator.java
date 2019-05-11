package gameobject;

import biuoop.DrawSurface;
import game.Constants;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import utilts.Counter;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    //Members of the class.
    private Counter scoreCounter;
    private Rectangle rec;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        scoreCounter = score;
        rec = new Rectangle(new Point(0, 0), (double) Constants.SCREEN_WIDTH, Constants.BLOCK_HIGTH);
    }

    /**
     * Draw the score indicator to the screen.
     *
     * @param d - a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        // Draw the block.
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY()
                , (int) rec.getWidth(), (int) rec.getHeight());
        d.setColor(Color.white);
        d.drawText((int) (rec.getUpperLeft().getX() + (rec.getWidth() / 2) - 2),
                (int) (rec.getUpperLeft().getY() + (rec.getHeight() / 2) + 5),
                "Score: " + Integer.toString(scoreCounter.getValue()), 12);
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
