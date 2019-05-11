package level;

import gameobject.Background;
import gameobject.Block;
import gameobject.Sprite;
import io.BlocksFromSymbolsFactory;
import io.FailedToParse;
import utilts.Velocity;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Level information factory.
 */
public class LevelInformationFactory implements LevelInformation, Cloneable {
    // Members of the class
    private String levelName;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Velocity> ballVelocities = new LinkedList<Velocity>();
    private List<Block> block = new LinkedList<Block>();
    private int numBlockRemove;
    private Background background;
    private int rowHeight;
    private int blockStartX;
    private int blockStartY;
    private BlocksFromSymbolsFactory blocksFromSymbolsFactory;

    /**
     * Instantiates a new Level information factory.
     */
    public LevelInformationFactory() {
    }

    /**
     * Number of balls.
     *
     * @return the num of the balls
     */
    @Override
    public int numberOfBalls() {
        return this.ballVelocities.size();
    }

    /**
     * Initial ball velocities and
     * add them to list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * Paddle speed .
     *
     * @return the paddle speed
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Paddle width.
     *
     * @return the paddle width
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * blocks list.
     * The blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        return this.block;
    }

    /**
     * Number of blocks to remove.
     *
     * @return the num of blocks to remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlockRemove;
    }

    /**
     * Sets background.
     *
     * @param backgroundSprite the background sprite
     */
    public void setBackground(Background backgroundSprite) {
        this.background = backgroundSprite;
    }

    /**
     * Sets level name.
     *
     * @param newLevelName the new level name
     */
    public void setLevelName(String newLevelName) {
        this.levelName = newLevelName;
    }

    /**
     * Sets number of blocks remove.
     *
     * @param numberOfBlocksToClear the number of blocks to clear
     */
    public void setNumberOfBlocksRemove(int numberOfBlocksToClear) {
        this.numBlockRemove = numberOfBlocksToClear;
    }

    /**
     * Sets block.
     *
     * @param newBlock the new block
     */
    public void setBlock(Block newBlock) {
        this.block.add(newBlock);
    }

    /**
     * Sets blocks.
     *
     * @param newBlock the new block
     */
    public void setBlocks(List<Block> newBlock) {
        this.block = newBlock;
    }

    /**
     * Sets ball velocities.
     *
     * @param velocity the velocity
     */
    public void setBallVelocities(Velocity velocity) {
        this.ballVelocities.add(velocity);
    }

    /**
     * Sets paddle speed.
     *
     * @param newPaddleSpeed the new paddle speed
     */
    public void setPaddleSpeed(int newPaddleSpeed) {
        this.paddleSpeed = newPaddleSpeed;
    }

    /**
     * Sets paddle width.
     *
     * @param newPaddleWidth the new paddle width
     */
    public void setPaddleWidth(int newPaddleWidth) {
        this.paddleWidth = newPaddleWidth;
    }

    /**
     * Add velocity.
     *
     * @param velocity the velocity
     */
    public void addVelocity(Velocity velocity) {
        this.ballVelocities.add(velocity);
    }

    /**
     * Sets row height.
     *
     * @param newRowHeight the new row height
     */
    public void setRowHeight(int newRowHeight) {
        this.rowHeight = newRowHeight;
    }

    /**
     * Sets block start x.
     *
     * @param startX the start x
     */
    public void setBlockStartX(int startX) {
        this.blockStartX = startX;
    }

    /**
     * Sets block start y.
     *
     * @param startY the start y
     */
    public void setBlockStartY(int startY) {
        this.blockStartY = startY;
    }

    /**
     * Sets blocks from symbols factory.
     *
     * @param blockFromSymbolsFactory the block from symbols factory
     */
    public void setBlocksFromSymbolsFactory(BlocksFromSymbolsFactory blockFromSymbolsFactory) {
        this.blocksFromSymbolsFactory = blockFromSymbolsFactory;
    }

    /**
     * Add block.
     *
     * @param line the line
     */
    public void addBlock(String line) {
        String[] split = line.split("");
        int startX = this.blockStartX;
        // Set the blocks
        for (int i = 0; i < split.length; i++) {
            if (blocksFromSymbolsFactory.isBlockSymbol(split[i])) {
                Block newBlock = blocksFromSymbolsFactory.getBlock(split[i], startX, this.blockStartY);
                startX = startX + (int) newBlock.getWidth();
                this.block.add(newBlock);
            } else if (blocksFromSymbolsFactory.isSpaceSymbol(split[i])) {
                int space = blocksFromSymbolsFactory.getSpaceWidth(split[i]);
                startX = startX + space;
            } else {
                throw new FailedToParse("Unknown symbol given: " + split[i]);
            }
        }
        this.blockStartY = this.blockStartY + this.rowHeight;
    }

    /**
     * @return a string representation
     * of the object LevelInformation.
     */
    public LevelInformation clone() {
        try {
            LevelInformationFactory cloned = (LevelInformationFactory) super.clone();
            List<Block> newList = new LinkedList<>();
            for (Block b : cloned.block) {
                newList.add(b.clone());
            }
            cloned.setBlocks(newList);
            return cloned;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
