package io;

/**
 * The type Split row.
 */
public class SplitRow {
    // Members of the class
    private String name;
    private String val;
    private boolean line = false;

    /**
     * Instantiates a new Split row.
     *
     * @param s the s
     */
    public SplitRow(String s) {
        // split the string by ":"
        int split = s.indexOf(":");
        // if then 1 val
        if (split >= 0) {
            this.name = s.substring(0, split);
            this.val = s.substring(split + 1, s.length());
        } else {
            this.name = s;
            this.line = true;
        }
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets val.
     *
     * @return the val
     */
    public String getVal() {
        return this.val;
    }

    /**
     * Gets line - title line.
     *
     * @return the line
     */
    public boolean getLine() {
        return this.line;
    }

    /**
     * @return the string
     */
    @Override
    public String toString() {
        if (line) {
            return name;
        } else {
            return name + ":" + val;
        }
    }
}
