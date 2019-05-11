package animation;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {
    /**
     * run a task.
     *
     * @return a value of the <T>
     */
    T run();
}