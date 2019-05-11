package animation;

import biuoop.DrawSurface;
import gameobject.Background;
import utilts.HighScoresTable;
import utilts.ScoreInfo;

import java.awt.Color;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    // Member of the class.
    private HighScoresTable highScoresTable;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the high scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
    }

    /**
     * Do one frame in the animation.
     *
     * @param d  the surface
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // Create a new background
        Background background = new Background(Color.decode("#E3F2FD"));
        background.drawOn(d);
        d.setColor(Color.decode("#64B5F6"));
        // Draw the title - High Scores
        d.drawText(33, 40, "High Scores", 40);
        d.setColor(Color.decode("#1976D2"));
        d.drawText(30, 40, "High Scores", 40);
        d.setColor(Color.decode("#0D47A1"));
        // Draw the table of : name and score
        d.drawText(d.getWidth() / 2 - 200, 120, "Name", 30);
        d.drawText(d.getWidth() / 2, 120, "Score", 30);
        d.setColor(Color.decode("#9FA8DA"));
        d.fillRectangle(d.getWidth() / 2 - 200 + 3, 142, 280, 10);
        d.setColor(Color.decode("#5C6BC0"));
        d.fillRectangle(d.getWidth() / 2 - 200, 140, 280, 10);
        int i = 1;
        d.setColor(Color.decode("#7986CB"));
        // Draw all the people and their scores
        for (ScoreInfo score : this.highScoresTable.getHighScores()) {
            d.drawText(d.getWidth() / 2 - 200, 160 + i * 30,
                    score.getName(), 25);
            d.drawText(d.getWidth() / 2, 160 + i * 30, String.valueOf(score.getScore()), 25);
            i++;
        }
    }

    /**
     * Check if the animation should stop.
     *
     * @return true if the animation need to stop, else return false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * Stop the animation.
     */
    @Override
    public void stop() {
    }
}
