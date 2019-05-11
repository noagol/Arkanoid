package gameobject;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * A sprite class.
 * Sprites can be drawn on the screen, and can be
 * notified that time has passed
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d - a surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    void timePassed(double dt);

    /**
     * Add an object to the game.
     *
     * @param g - the game.
     */
    void addToGame(GameLevel g);
}