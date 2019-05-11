
import game.GameFlow;
import io.FailedToParse;
import java.io.File;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The Main game.
     *
     * @param args the input arguments
     *             level sets
     */
    public static void main(String[] args) {
        // Run all the levels.
        if (args.length == 0) {
            // Create a new game.
            GameFlow gameFlow = new GameFlow();
            try {
                gameFlow.startTheGame("level_sets.txt");
            } catch (FailedToParse e) {
                System.out.println(e.toString());
                System.exit(1);
            }
        } else if (args.length == 1) {
            if (new File(args[0]).exists()) {
                // Create a new game.
                GameFlow gameFlow = new GameFlow();
                try {
                    gameFlow.startTheGame(args[0]);
                } catch (FailedToParse e) {
                    System.out.println(e.toString());
                    System.exit(1);
                }
            } else {
                throw new IllegalArgumentException("File was not found: " + args[0]);
            }
        }
    }
}
