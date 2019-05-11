package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    // Members of the class.
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isPressed;
    private boolean shouldStop;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isPressed = true;
        this.shouldStop = false;
    }

    /**
     * Do one frame in the animation.
     *
     * @param d  the surface
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
        if ((this.keyboardSensor.isPressed(this.key)) && (!this.isPressed)) {
            this.shouldStop = true;
            this.animation.stop();
        } else if (!this.keyboardSensor.isPressed(this.key)) {
            this.isPressed = false;
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop, else return false.
     */
    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }

    /**
     * Stop the animation.
     */
    @Override
    public void stop() {
        this.shouldStop = true;
    }
}
