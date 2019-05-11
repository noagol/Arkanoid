package io;

import gameobject.Background;
import level.LevelInformation;
import level.LevelInformationFactory;
import utilts.Velocity;

import java.awt.Color;
import java.awt.Image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {

    /**
     * Read from file list.
     *
     * @param fileName the file name
     * @return the list
     */
    public static List<LevelInformation> readFromFile(String fileName) {
        Reader fileReader = null;
        File file = new File(fileName);
        try {
            if (file.exists()) {
                fileReader = new FileReader(file);
            } else {
                fileReader = new InputStreamReader(ClassLoader.
                        getSystemClassLoader().getResourceAsStream(fileName));
            }
            return fromReader(fileReader);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new FailedToParse("Unable to locate file at: " + fileName + ". " + e.toString());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    System.out.println("File not found" + fileName);
                }
            }
        }
    }

    /**
     * From reader list.
     * method that will get a file name and returns
     * a list of LevelInformation objects.
     *
     * @param reader the reader
     * @return the list
     */
    public static List<LevelInformation> fromReader(Reader reader) {
        ArrayList<LevelInformation> levels = new ArrayList<LevelInformation>();
        BufferedReader buff = null;
        SplitRow row = null;
        LevelInformationFactory level = null;
        try {
            buff = new BufferedReader(reader);
            String line = buff.readLine();
            // skip spaces and lines that start with #
            while (line.equals("") || line.startsWith("#")) {
                line = buff.readLine();
            }
            while (line != null) {
                while (line.equals("") || line.startsWith("#")) {
                    line = buff.readLine();
                }
                Map<String, Boolean> found = initHasBeenFoundMap();
                // if we red start level
                if (line.equals("START_LEVEL")) {
                    update(found, new SplitRow(line));
                    level = new LevelInformationFactory();
                    while (line != null) {
                        while (line.equals("") || line.startsWith("#")) {
                            line = buff.readLine();
                        }
                        line = buff.readLine();
                        // split the row
                        row = new SplitRow(line);
                        if (!row.getLine()) {
                            update(found, row);
                            setRow(level, row);
                            // if we red start blocks
                        } else if (line.equals("START_BLOCKS")) {
                            update(found, new SplitRow(line));
                            line = buff.readLine();
                            // continue until read end blocks
                            while (!line.equals("END_BLOCKS")) {
                                if (!line.equals("") || line.startsWith("#")) {
                                    level.addBlock(line);
                                }
                                line = buff.readLine();
                            }
                            update(found, new SplitRow(line));
                            // if we red end level
                        } else if (line.equals("END_LEVEL")) {
                            update(found, new SplitRow(line));
                            // goes all the map
                            for (Map.Entry<String, Boolean> entry : found.entrySet()) {
                                // check that every field is true
                                if (!entry.getValue()) {
                                    throw new FailedToParse("missing property: " + entry.getKey());
                                }
                            }
                            levels.add(level);
                            break;
                        } else {
                            throw new FailedToParse("not exist" + line);
                        }
                    }
                } else {
                    throw new FailedToParse("not exist" + line);
                }
                line = buff.readLine();
            }
        } catch (IOException e) {
            System.out.println("loading file problem");
        }
        // return the levels
        return levels;
    }

    /**
     * Sets row.
     * set all the filed of a level.
     *
     * @param level the level
     * @param row   the row
     */
    public static void setRow(LevelInformationFactory level, SplitRow row) {
        if ("level_name".equals(row.getName())) {
            level.setLevelName(row.getVal());
        } else if ("paddle_speed".equals(row.getName())) {
            level.setPaddleSpeed(casting(row.getVal()));
        } else if ("paddle_width".equals(row.getName())) {
            level.setPaddleWidth(casting(row.getVal()));
        } else if ("num_blocks".equals(row.getName())) {
            level.setNumberOfBlocksRemove(casting(row.getVal()));
        } else if ("ball_velocities".equals(row.getName())) {
            String[] velocity = row.getVal().split(" ");
            if (velocity.length == 0) {
                throw new FailedToParse("No ball velocities given: " + row.getVal());
            }
            for (String vel : velocity) {
                String[] speed = vel.split(",");
                if (speed.length != 2) {
                    throw new FailedToParse("Ball velocity in wrong format: " + row.getVal());
                }
                try {
                    level.addVelocity(Velocity.fromAngleAndSpeed(Double.parseDouble(speed[0])
                            , Double.parseDouble(speed[1])));
                } catch (NumberFormatException ex) {
                    throw new FailedToParse("Failed to parse number at: " + row.getVal());
                }
            }
        } else if ("background".equals(row.getName())) {
            background(level, row);
        } else if ("blocks_start_x".equals(row.getName())) {
            level.setBlockStartX(casting(row.getVal()));

        } else if ("blocks_start_y".equals(row.getName())) {
            level.setBlockStartY(casting(row.getVal()));
        } else if ("row_height".equals(row.getName())) {
            level.setRowHeight(casting(row.getVal()));
        } else if ("block_definitions".equals(row.getName())) {
            BlocksFromSymbolsFactory blocksFromSymbolsFactory =
                    BlocksDefinitionReader.readFromFile(row.getVal());
            level.setBlocksFromSymbolsFactory(blocksFromSymbolsFactory);
        } else {
            throw new FailedToParse("Unknown key encountered:");
        }
    }

    /**
     * Background.
     *
     * @param level the level
     * @param row   the row
     */
    public static void background(LevelInformationFactory level, SplitRow row) {
        // if the background is image
        if (row.getVal().startsWith("image(") && row.getVal().endsWith(")")) {
            ImageParser imageParser = new ImageParser();
            Image image = imageParser.getImage(row.getVal());
            level.setBackground(new Background(image));
            // if the background is color
        } else if (row.getVal().startsWith("color(")) {
            ColorsParser colorsParser = new ColorsParser();
            Color color = colorsParser.colorFromString(row.getVal());
            level.setBackground(new Background(color));
        } else {
            throw new FailedToParse("can't found" + row.getVal());
        }
    }

    /**
     * Init has been found map map.
     * check that all the fields exists.
     *
     * @return the map
     */
    private static Map<String, Boolean> initHasBeenFoundMap() {
        Map<String, Boolean> hasBeenFound = new HashMap<>();
        hasBeenFound.put("START_LEVEL", false);
        hasBeenFound.put("END_LEVEL", false);
        hasBeenFound.put("block_definitions", false);
        hasBeenFound.put("row_height", false);
        hasBeenFound.put("blocks_start_x", false);
        hasBeenFound.put("blocks_start_y", false);
        hasBeenFound.put("START_BLOCKS", false);
        hasBeenFound.put("END_BLOCKS", false);
        hasBeenFound.put("level_name", false);
        hasBeenFound.put("background", false);
        hasBeenFound.put("ball_velocities", false);
        hasBeenFound.put("paddle_speed", false);
        hasBeenFound.put("paddle_width", false);
        hasBeenFound.put("num_blocks", false);
        return hasBeenFound;
    }

    /**
     * Update the map according to
     * the values exist.
     *
     * @param map the map
     * @param row the row
     */
    private static void update(Map<String, Boolean> map, SplitRow row) {
        if (map.containsKey(row.getName()) && !map.get(row.getName())) {
            map.put(row.getName(), true);
        } else {
            throw new FailedToParse("failed to parser at property: " + row.toString());
        }
    }

    /**
     * Casting string to int.
     *
     * @param s the s
     * @return the int
     */
    private static int casting(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new FailedToParse("Failed to parse number at: " + s);
        }
    }
}