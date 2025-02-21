package zirnitra.exceptions;

/**
 * Class for throwing exceptions specific to the Duke.
 */
public class ZirnitraException extends Exception {
    /**
     * Throws a new DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public ZirnitraException(String message) {
        super(message);
    }
}