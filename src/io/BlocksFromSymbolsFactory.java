package io;

import blocks.BlockCreator;
import gameobject.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Blocks from symbols factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new HashMap<>();
    private Map<String, BlockCreator> blockCreators = new HashMap<>();

    /**
     * Is space symbol boolean.
     * returns true if 's' is a valid space symbol.
     *
     * @param s the string
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     * returns true if 's' is a valid block symbol.
     *
     * @param s the string
     * @return the boolean
     */
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    /**
     * Gets space width.
     * Returns the width in pixels associated
     * with the given spacer-symbol.
     *
     * @param s the string
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * Gets block.
     * Return a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s the string
     * @param x the x position
     * @param y the y position
     * @return the block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blockCreators.get(s).create(x, y);
    }

    /**
     * Add block creator.
     *
     * @param symbol       the symbol
     * @param blockCreator the block creator
     */
    public void addBlockCreator(String symbol, BlockCreator blockCreator) {
        blockCreators.put(symbol, blockCreator);
    }

    /**
     * Add spacer.
     *
     * @param symbol the symbol
     * @param width  the width
     */
    public void addSpacer(String symbol, Integer width) {
        spacerWidths.put(symbol, width);
    }
}