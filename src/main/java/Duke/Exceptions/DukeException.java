package duke.exceptions;

/**
 * Class for throwing exceptions specific to the Duke chatbot application.
 */
public class DukeException extends Exception {
    /**
     * Throws a new DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}