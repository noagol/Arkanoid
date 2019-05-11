package io;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Get the color from the string.
     *
     * @param s the string of the color
     * @return the color
     */
    public Color colorFromString(String s) {
        // if the color is rgb
        if (s.startsWith("color(RGB(") && s.endsWith("))")) {
            String param = getString(s, "color(RGB(", "))");
            // split the line
            String[] c = param.split(",");
            // create the rgb color
            Color color = new Color(Integer.parseInt(c[0].trim()),
                    Integer.parseInt(c[1].trim()),
                    Integer.parseInt(c[2].trim()));
            return color;
            // if we get the name of the color
        } else if (s.startsWith("color(") && s.endsWith(")")) {
            String colorName = getString(s, "color(", ")");
            Color color = getColor(colorName);
            return color;
            // if we get unknown color
        } else {
            throw new FailedToParse("unknown color format: " + s);
        }
    }


    /**
     * Gets color from string.
     *
     * @param name the name
     * @return the color
     */
    private static Color getColor(String name) {
        try {
            Field field = Class.forName("java.awt.Color").getField(name);
            return (Color) field.get(null);
        } catch (Exception e) {
            throw new FailedToParse("unknown color: " + name);
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
