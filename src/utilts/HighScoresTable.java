package utilts;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    // members of the class.
    private int maxSize;
    private ArrayList<ScoreInfo> scores;

    /**
     * Instantiates a new High scores table.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size
     */

    public HighScoresTable(int size) {
        this.scores = new ArrayList<>(size);
        this.maxSize = size;
    }

    /**
     * Add a high-score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        // Check if the list is full.
        if (this.scores.size() == this.maxSize) {
            // Check if the new score is bigger.
            if (score.compareTo(this.scores.get(this.maxSize - 1)) < 0) {
                // Remove the old score.
                this.scores.remove(this.maxSize - 1);
                // Add the new score.
                this.scores.add(this.maxSize - 1, score);
            }
            // If there is place in the table
        } else {
            // Add the new score.
            this.scores.add(score);
        }
        // Sort the table.
        this.scores.sort(new ScoreInfoComparator());
    }


    /**
     * The table size.
     *
     * @return the int
     */
    public int size() {
        return this.maxSize;
    }

    /**
     * Gets high scores.
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return the high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }

    /**
     * Gets rank.
     * Return the rank of the current score
     *
     * @param score the score
     * @return the rank
     */
    public int getRank(int score) {
        // Goes all the table
        for (int i = 0; i < this.scores.size(); i++) {
            // Check if the new score is bigger
            if (this.scores.get(i).getScore() < score) {
                // Return the index to change
                return i;
            }
        }
        if (this.scores.size() < this.size()) {
            return this.scores.size();
        }
        // If there isn't place in the table return -1
        return -1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable highScoresTable = (HighScoresTable) input.readObject();
            this.scores = highScoresTable.scores;
            this.maxSize = highScoresTable.maxSize;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


    /**
     * Load from file high scores table.
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the filename
     * @return the high scores table
     */

    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable theTableOfHighScores = new HighScoresTable(5);
        try {
            theTableOfHighScores.load(filename);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        return theTableOfHighScores;
    }
}