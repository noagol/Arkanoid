package io;

import animation.AnimationRunner;
import animation.Menu;
import animation.MenuAnimation;
import animation.Task;
import biuoop.KeyboardSensor;
import game.GameFlow;
import gameobject.Background;
import level.LevelInformation;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.List;

/**
 * The type Level set reader.
 */
public class LevelSetReader {

    /**
     * Read from file menu.
     *
     * @param fileName        the file name
     * @param keyboardSensor  the keyboard sensor
     * @param background      the background
     * @param animationRunner the animation runner
     * @param gameFlow        the game flow
     * @return the menu
     */
    public static Menu<Task> readFromFile(String fileName,
                                          KeyboardSensor keyboardSensor,
                                          Background background,
                                          AnimationRunner animationRunner,
                                          GameFlow gameFlow) {
        Reader fileReader = null;
        File file = new File(fileName);
        try {
            if (file.exists()) {
                fileReader = new FileReader(file);
            } else {
                fileReader = new InputStreamReader(ClassLoader.
                        getSystemClassLoader().getResourceAsStream(fileName));
            }
            return fromReader(fileReader, keyboardSensor,
                    background, animationRunner, gameFlow);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new FailedToParse("Unable to locate file at: " + fileName + ". " + e.toString());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("file not found");
                }
            }
        }
    }

    /**
     * From reader menu.
     *
     * @param reader          the reader
     * @param keyboardSensor  the keyboard sensor
     * @param background      the background
     * @param animationRunner the animation runner
     * @param gameFlow        the game flow
     * @return the menu
     */
    public static Menu<Task> fromReader(java.io.Reader reader,
                                        KeyboardSensor keyboardSensor,
                                        Background background,
                                        AnimationRunner animationRunner,
                                        GameFlow gameFlow) {
        MenuAnimation<Task> subMenu = new MenuAnimation<>("Start Game",
                keyboardSensor, background,
                animationRunner);
        LineNumberReader lineNumberReader;
        String line;
        SplitRow row;
        String key = null;
        String val = null;
        try {
            lineNumberReader = new LineNumberReader(reader);
            line = lineNumberReader.readLine();
            while (line != null) {
                // skip empty lines
                while (line.equals("")) {
                    line = lineNumberReader.readLine();
                }
                row = new SplitRow(line);
                // if line odd-numbered we get level name
                if (!row.getLine() && lineNumberReader.getLineNumber() % 2 != 0) {
                    key = row.getName();
                    val = row.getVal();
                    line = lineNumberReader.readLine();
                    // if line in Even-numbered we get filename
                } else if (row.getLine() && lineNumberReader.getLineNumber() % 2 == 0) {
                    final String fileName = row.getName();
                    List<LevelInformation> level = LevelSpecificationReader.readFromFile(fileName);
                    // run the sub menu
                    subMenu.addSelection(key, val, new Task() {
                        @Override
                        public Void run() {
                            gameFlow.runLevels(level);
                            return null;
                        }
                    });
                    line = lineNumberReader.readLine();
                } else {
                    throw new FailedToParse("loading file problem");
                }

            }
        } catch (IOException e) {
            System.out.println("loading file problem");
            return null;
        }
        // return the sub menu
        return subMenu;
    }
}
