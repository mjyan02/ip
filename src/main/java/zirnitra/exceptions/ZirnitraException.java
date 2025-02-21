package zirnitra.exceptions;

/**
 * Class for throwing exceptions specific to Zirnitra.
 */
public class ZirnitraException extends Exception {
    /**
     * Throws a new ZirnitraException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public ZirnitraException(String message) {
        super(message);
    }
}