package utilts;

import java.io.Serializable;
import java.util.Comparator;

/**
 * The type Score info.
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    // Members of the class.
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Return the name of the score.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * @param other - a score to compare
     * @return 1 if the other score is bigger
     * -1 if other is smaller
     * 0 if they equals
     */
    @Override
    public int compareTo(ScoreInfo other) {
        return Integer.compare(other.score, this.score);
    }

    /**
     * @return the string
     */
    @Override
    public String toString() {
        return String.format("%s : %d", getName(), getScore());
    }

}

/**
 * The type Score info comparator.
 */
class ScoreInfoComparator implements Comparator<ScoreInfo> {
    @Override
    public int compare(ScoreInfo first, ScoreInfo second) {
        return first.compareTo(second);
    }
}