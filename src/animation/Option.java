package animation;

/**
 * The type Option.
 *
 * @param <T> the type parameter
 */
public class Option<T> {
    // Members of the class.
    private String optionKey;
    private T optionValue;
    private String text;

    /**
     * Instantiates a new Option.
     *
     * @param key the key
     * @param s   the s
     * @param val the val
     */
    public Option(String key, String s, T val) {
        this.optionKey = key;
        this.optionValue = val;
        this.text = s;
    }

    /**
     * Gets value of the option.
     *
     * @return the value
     */
    public T getValue() {
        return this.optionValue;
    }


    /**
     * Gets key of the option.
     *
     * @return the key
     */
    public String getKey() {
        return this.optionKey;
    }

    /**
     * Gets message of the option.
     *
     * @return the message
     */
    public String getMessage() {
        return this.text;
    }
}
