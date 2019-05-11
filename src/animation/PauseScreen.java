package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    //Members of thr class.
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the keyboard
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Do one frame in the animation.
     *
     * @param d  the surface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        //Check if the space is pressed.
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop,
     * else return false.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Stop the animation.
     */
    @Override
    public void stop() {
        this.stop = true;
    }
}
