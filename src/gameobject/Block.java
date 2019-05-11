package gameobject;

import blocks.BlockFill;
import blocks.DrawBlock;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import utilts.Velocity;
import observer.HitListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class to describe a block.
 * A block is a rectangle with color and number of hits by the ball.
 */
public class Block implements Collidable, Sprite, HitNotifier, Cloneable {
    // Members of the class.
    private Rectangle rect;
    private java.awt.Color color;
    private int numberOfHits;
    private List<HitListener> hitListeners = new ArrayList<>();
    private Map<Integer, List<DrawBlock>> draw = new HashMap<>();


    /**
     * A constructor of the block.
     * get a new block sizes.
     * default number of hits - 1.
     *
     * @param upperLeft - the point of the left side of the block.
     * @param width     - the width of the block.
     * @param height    - the height of the block.
     */
    public Block(Point upperLeft, Double width, Double height) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.numberOfHits = 0;
        List<DrawBlock> drawer = new ArrayList<>();
        drawer.add(new BlockFill(Color.GRAY));
        draw.put(-1, drawer);
    }

    /**
     * A constructor of the block.
     * get a new block sizes and the number of the start hits.
     *
     * @param upperLeft    - the point of the left side of the block.
     * @param width        - the width of the block.
     * @param height       - the height of the block.
     * @param numberOfHits - the number of hits.
     */
    public Block(Point upperLeft, Double width, Double height, int numberOfHits) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.numberOfHits = numberOfHits;
        this.color = Color.RED;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param x the x
     * @param y the y
     */
    public Block(int x, int y) {
        this.rect = new Rectangle(new Point(x, y), 1.0, 1.0);
        this.numberOfHits = 0;
    }


    /**
     * @return - the "collision shape" of the object (rectangle).
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notify the object that we collided with it at collisionPoint
     * with a given velocity.
     * Return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * If it is hit from the sides, it changes its horizontal
     * direction, otherwise changes the the vertical direction.
     *
     * @param hitter          - hitter ball
     * @param collisionPoint  - the collision point between the
     *                        rectangle and the object.
     * @param currentVelocity - the object velocity.
     * @return the new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        List<Line> frame = this.rect.getListRectangle();
        numberOfHits--;
        this.notifyHit(hitter);
        // Check if the ball hit the upper or bottom of the block.
        if (frame.get(0).pointExists(collisionPoint) || frame.get(1).pointExists(collisionPoint)) {
            // Change horizontal direction.
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            // Check if the ball hit the left or right side of the block.
        } else if (frame.get(2).pointExists(collisionPoint) || frame.get(3).pointExists(collisionPoint)) {
            // Change vertical direction.
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            throw new RuntimeException("error");
        }
    }

    /**
     * Set the color of the block.
     *
     * @param newColor - a color to set.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * Draw a new block to the screen.
     * Draw the number of hits in the middle of the block.
     *
     * @param surface - a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        List<DrawBlock> drawBlocks;
        if (this.draw.containsKey(numberOfHits)) {
            drawBlocks = this.draw.get(numberOfHits);
        } else {
            drawBlocks = this.draw.get(-1);
        }
        if (drawBlocks != null) {
            for (DrawBlock block : drawBlocks) {
                block.drawOn(surface, rect);
            }
        }
    }

    /**
     * Currently we do nothing.
     *
     * @param dt - the dt.
     */
    public void timePassed(double dt) {
    }

    /**
     * Adding the block to the game.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Notify hit.
     *
     * @param hitter - hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - listener to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - listener to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return this.numberOfHits;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.rect.getWidth();
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.rect.setWidth(width);
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.rect.setHeight(height);
    }

    /**
     * Sets number of hits.
     *
     * @param newNumberOfHits the new number of hits
     */
    public void setNumberOfHits(int newNumberOfHits) {
        this.numberOfHits = newNumberOfHits;
    }

    /**
     * Add draw.
     *
     * @param drawBlock the draw block
     * @param hitPoint  the hit point
     */
    public void addDraw(DrawBlock drawBlock, int hitPoint) {
        if (draw.containsKey(hitPoint)) {
            draw.get(hitPoint).add(drawBlock);
        } else {
            List<DrawBlock> blocks = new ArrayList<>();
            blocks.add(drawBlock);
            draw.put(hitPoint, blocks);
        }
    }

    /**
     * @return a string representation
     * of the object block
     */
    public Block clone() {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}