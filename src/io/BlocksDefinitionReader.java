package io;

import blocks.BasicBlockCreator;
import blocks.BlockCreator;
import blocks.BlocksFactory;


import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {
    /**
     * Read from file blocks from symbols factory.
     *
     * @param fileName the file name
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory readFromFile(String fileName) {
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
     * From reader blocks from symbols factory.
     * Method that will get a file name and returns
     * a list of LevelInformation objects
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = new BlocksFromSymbolsFactory();
        BlocksFactory blocksFactory = new BlocksFactory();
        List<SplitRow> defult = new ArrayList<>();
        BufferedReader buff;
        SplitRow row = null;
        SplitRow symbol;
        BlockCreator basicBlockCreator;
        String line;
        String[] splittedLine;
        try {
            buff = new BufferedReader(reader);
            line = buff.readLine();
            while (line != null) {
                // spit line by spaces
                splittedLine = line.split(" ");
                // skip "#" and empty lines
                while (splittedLine[0].equals("") || splittedLine[0].startsWith("#")) {
                    line = buff.readLine();
                    if (line != null) {
                        splittedLine = line.split(" ");
                    }
                }
                // if the line start with default
                if (splittedLine[0].equals("default")) {
                    for (int i = 1; i < splittedLine.length; i++) {
                        // add default information
                        defult.add(new SplitRow(splittedLine[i]));
                    }
                    // if the line start with bdef symbol
                } else if (splittedLine[0].equals("bdef") && splittedLine[1].startsWith("symbol")) {
                    basicBlockCreator = new BasicBlockCreator();
                    for (int i = 2; i < splittedLine.length; i++) {
                        row = new SplitRow(splittedLine[i]);
                        // create block
                        basicBlockCreator = blocksFactory.addDecoration(basicBlockCreator, row);
                    }
                    for (int i = 0; i < defult.size(); i++) {
                        basicBlockCreator = blocksFactory.addDecoration(basicBlockCreator, defult.get(i));
                    }
                    symbol = new SplitRow(splittedLine[1]);
                    blocksFromSymbolsFactory.addBlockCreator(symbol.getVal(), basicBlockCreator);
                    // if the line start with sdef symbol
                } else if (splittedLine[0].equals("sdef") && splittedLine[1].startsWith("symbol")) {
                    symbol = new SplitRow(splittedLine[1]);
                    row = new SplitRow(splittedLine[2]);
                    // set spaces
                    blocksFromSymbolsFactory.addSpacer(symbol.getVal(),
                            Integer.parseInt(row.getVal()));
                } else {
                    System.out.println("loading file problem");
                }
                line = buff.readLine();
            }
        } catch (IOException e) {
            System.out.println("loading file problem");
            return null;
        }
        return blocksFromSymbolsFactory;
    }
}
