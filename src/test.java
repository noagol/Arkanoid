import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import game.Constants;
import utilts.HighScoresTable;
import utilts.ScoreInfo;

import java.io.File;

public class test {
    public static void main(String[] args) {
        HighScoresTable highScoresTable = new HighScoresTable(4);
        highScoresTable.add(new ScoreInfo("elad", 300));
        highScoresTable.add(new ScoreInfo("elad", 400));
        highScoresTable.add(new ScoreInfo("elad", 350));
        highScoresTable.add(new ScoreInfo("d", 200));
        System.out.println(highScoresTable.getHighScores().toString());
        System.out.println(highScoresTable.getRank(310));
        try {
            highScoresTable.save(new File("easy.ser"));
            HighScoresTable h = HighScoresTable.loadFromFile(new File("easy.ser"));
            System.out.println("h is: " + h.getHighScores().toString());
            GUI gui = new GUI("Arkanoid", Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            AnimationRunner runner = new AnimationRunner(60, gui);
            Animation a1 = new HighScoresAnimation(highScoresTable);
            Animation a2 = new HighScoresAnimation(highScoresTable);
            Animation a1k = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "m", a1);
            Animation a2k = new KeyPressStoppableAnimation(gui.getKeyboardSensor(), "m", a2);
            runner.run(a1k);
            runner.run(a2k);
            gui.close();
        } catch (Exception ex) {

        }
    }
}
