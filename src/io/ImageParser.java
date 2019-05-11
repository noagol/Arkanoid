package io;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * The type Image parser.
 */
public class ImageParser {
    /**
     * Gets image.
     *
     * @param s the s
     * @return the image
     */
    public Image getImage(String s) {
        // get the image
        String param = getString(s, "image(", ")");
        InputStream is = null;
        File file = new File(param);
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
            } else {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(param);
            }
            Image image = ImageIO.read(is);
            return image;
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Failed loading image: " + param, e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException("Failed loading image: " + param, e);
                }
            }
        }
    }

    /**
     * Gets substring.
     *
     * @param string the string
     * @param start  the start
     * @param end    the end
     * @return the string
     */
    private static String getString(String string, String start, String end) {
        return string.substring(start.length(), string.length() - end.length());
    }
}
