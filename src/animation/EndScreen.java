package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import utilts.Counter;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    //Members of thr class.
    private Counter score;
    private Counter live;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private boolean stop = false;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param live  the live
     * @param gui   the gui
     */
    public EndScreen(Counter score, Counter live, GUI gui) {
        this.score = score;
        this.live = live;
        this.keyboardSensor = gui.getKeyboardSensor();
        this.gui = gui;
    }

    /**
     * Do one frame in the animation.
     * If the game end - print message.
     *
     * @param d the surface.
     * @param dt the dt.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // Set the color.
        d.setColor(Color.decode("#F8BBD0"));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.decode("#FF4081"));
        // Check if the player have life
        if (live.getValue() == 0) {
            d.drawText(10, d.getHeight() / 2
                    , "Game Over. Your score is " + score.getValue(), 40);
            // If the player have life and the game over.
        } else {
            d.drawText(10, d.getHeight() / 2
                    , "You Win. Your score is " + score.getValue(), 40);
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop,
     * else return false.
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * Stop the animation.
     */
    @Override
    public void stop() {
        this.stop = true;
    }

}
