package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame in the animation.
     *
     * @param d  the surface
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop, else return false.
     */
    boolean shouldStop();


    /**
     * Stop the animation.
     */
    void stop();
}
