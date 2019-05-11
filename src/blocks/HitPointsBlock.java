package blocks;

import gameobject.Block;

/**
 * The type Hit points block.
 */
public class HitPointsBlock extends AbstractBlockCreator {
    private int hitPoints;

    /**
     * Instantiates a new Hit points block.
     *
     * @param decorated   the decorated
     * @param hitPointVal the hit point val
     */
    public HitPointsBlock(BlockCreator decorated, String hitPointVal) {
        super(decorated);
        this.hitPoints = Integer.parseInt(hitPointVal);
    }

    /**
     * Create a block at the specified location.
     *
     * @param x the x
     * @param y the y
     * @return the block
     */
    @Override
    public Block create(int x, int y) {
        Block b = super.create(x, y);
        // set the hit points of the block
        b.setNumberOfHits(this.hitPoints);
        return b;
    }
}
