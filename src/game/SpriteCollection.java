package game;

import biuoop.DrawSurface;
import gameobject.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * A sprite collection class.
 * Holds a list of sprite objects.
 */
public class SpriteCollection {
    private List<Sprite> collection;

    /**
     * A constructor, create an empty list of sprites.
     */
    public SpriteCollection() {
        collection = new ArrayList<>();
    }

    /**
     * Add a new sprite to the list.
     *
     * @param s - a new sprite to add.
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s a sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.collection.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copiedSprites = new ArrayList<>(collection);
        for (Sprite sprite : copiedSprites) {
            sprite.timePassed(1.0 / Constants.SECOND);
        }
    }

    /**
     * Call DrawBlock(d) on all sprites.
     *
     * @param d - a surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copiedSprites = new ArrayList<>(collection);
        for (Sprite sprite : copiedSprites) {
            sprite.drawOn(d);
        }
    }
}