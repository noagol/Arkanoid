package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * The type Animation runner.
 * The AnimationRunner takes an
 * Animation object and runs it.
 */
public class AnimationRunner {
    //Members of the class.
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     * Instantiates a new Animation runner.
     *
     * @param framesPerSecond the frames per second
     * @param gui             the gui
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
        this.gui = gui;
    }

    /**
     * Run the animation.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, 1.0 / this.framesPerSecond);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }
}
