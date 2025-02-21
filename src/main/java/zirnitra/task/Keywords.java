package zirnitra.task;

/**
 * Enum representing different keywords for Zirnitra.
 */
public enum Keywords {
    /**
     * Keyword for exiting Duke.
     */
    BYE,

    /**
     * Keyword for listing all tasks.
     */
    LIST,

    /**
     * Keyword for marking a task as done.
     */
    MARK,

    /**
     * Keyword for unmarking a completed task.
     */
    UNMARK,

    /**
     * Keyword for adding a new Todo task.
     */
    TODO,

    /**
     * Keyword for adding a new Deadline task.
     */
    DEADLINE,

    /**
     * Keyword for adding a new Event task.
     */
    EVENT,

    /**
     * Keyword for deleting a task.
     */
    DELETE,
  
    /**
     * Keyword for finding a task.
     */
    FIND,

    /**
     * Represents an unknown keyword.
     */
    UNKNOWN;

    /**
     * Converts a string into the corresponding Keywords enum value.
     *
     * @param kw The input string keyword.
     * @return The corresponding Keywords enum value.
     */
    public static Keywords fromString(String kw) {
        try {
            return Keywords.valueOf(kw.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN; // Return UNKNOWN for unknown keywords
        }
    }
}
