package blocks;

import io.ColorsParser;
import io.ImageParser;
import io.SplitRow;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Blocks factory.
 */
public class BlocksFactory {

    /**
     * Add decoration block creator.
     *
     * @param creator the creator
     * @param row     the row
     * @return the block creator
     */
    public BlockCreator addDecoration(BlockCreator creator, SplitRow row) {
        // If we get the height of the block
        if ("height".equals(row.getName())) {
            return new HeightBlock(creator, row.getVal());
            // If we get the width of the block
        } else if ("width".equals(row.getName())) {
            return new WidthBlock(creator, row.getVal());
            // If we get the hit points of the block
        } else if ("hit_points".equals(row.getName())) {
            return new HitPointsBlock(creator, row.getVal());
            // If we get the the block fill or stroke
        } else if (row.getName().startsWith("fill") || row.getName().startsWith("stroke")) {
            int hitPointsValue = -1;
            // Check if we get fill
            boolean isFill = row.getName().startsWith("fill");
            int hitPoint = row.getName().indexOf("-");
            if (hitPoint != -1) {
                // save the num of the hit points
                hitPointsValue = Integer.parseInt(row.getName().substring(hitPoint + 1));
            }
            // If we get the the block image
            if (row.getVal().startsWith("image(")) {
                // load the image
                ImageParser imageParser = new ImageParser();
                Image image = imageParser.getImage(row.getVal());
                return new DrawingBlock(creator, hitPointsValue, image);
            } else if (row.getVal().startsWith("color(")) {
                ColorsParser colorsParser = new ColorsParser();
                Color color = colorsParser.colorFromString(row.getVal());
                return new DrawingBlock(creator, hitPointsValue, isFill, color);
            }
            // skip empty lines and spaces
        } else if (row.getName().equals("") || (row.getName().equals(" "))) {
            return creator;
        }
        // if we didn't have match - throw exception
        throw new RuntimeException("Unknown line " + row.toString());
    }
}
