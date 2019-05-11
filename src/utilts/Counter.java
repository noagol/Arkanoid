package utilts;

/**
 * The type Counter.
 */
public class Counter {
    // Member of the class.
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the number to add.
     */
    public void increase(int number) {
        count = count + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number decrease
     */
    public void decrease(int number) {
        count = count - number;
    }

    /**
     * Gets current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }
}