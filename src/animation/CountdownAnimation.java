package animation;

import biuoop.DrawSurface;

import game.Constants;
import game.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    //Members of the class.
    private boolean live;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection spriteCollection;
    private int framePassed = 0;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.live = false;
        this.spriteCollection = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
    }

    /**
     * Do one frame in the animation.
     *
     * @param d  the surface.
     * @param dt the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // count the num of frames.
        framePassed++;
        this.spriteCollection.drawAllOn(d);
        double numOfFrames = Constants.SECOND / (countFrom / numOfSeconds);
        int count = countFrom - ((int) (framePassed / numOfFrames));
        // check if we go all the frames
        if (framePassed >= countFrom * numOfFrames) {
            this.live = true;
            return;
        }
        d.setColor(Color.BLACK);
        d.drawText(Constants.SCREEN_WIDTH / 2 - 30 + 1, Constants.SCREEN_HEIGHT / 2 + 1,
                Integer.toString(count), 100);
        // Set the color and draw the text.
        d.setColor(Color.WHITE);
        d.drawText(Constants.SCREEN_WIDTH / 2 - 30, Constants.SCREEN_HEIGHT / 2,
                Integer.toString(count), 100);

    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop,
     * else return false.
     */
    public boolean shouldStop() {
        return (this.live);
    }

    /**
     * Stop the animation.
     */
    @Override
    public void stop() {
    }
}