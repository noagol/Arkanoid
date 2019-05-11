package animation;

/**
 * The interface Menu.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {
    /**
     * Add selection.
     *
     * @param key       the key to press
     * @param message   the message to print
     * @param returnVal the return val
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return the current status
     */
    T getStatus();
}