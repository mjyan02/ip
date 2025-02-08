package duke.task;

import duke.exceptions.DukeException;

/**
 * Represents a generic task in Duke with a description and marked status.
 * This is an abstract class that serves as a base class for specific task types.
 */
public abstract class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task is completed.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     * @param isDone Specifies if task is marked.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if the task is completed.
     *
     * @return "X" if the task is done, whitespace otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Converts the task into a formatted string representation suitable for file storage on hard disk.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFile();

    /**
     * Creates a Task object from a formatted string read from the storage file on hard disk.
     *
     * @param line The line of text representing a task in the storage file on hard disk.
     * @return A Task object reconstructed from the file data, or null if the file is corrupted.
     * @throws DukeException If an error occurs while parsing the task data.
     */
    public static Task fromFile(String line) throws DukeException {
        String[] str = line.split(" \\| ");
        if (str.length < 3) {
            return null; // Corrupt file
        }

        String type = str[0];   // To identify the task type (T, D, E)
        boolean isDone = str[1].equals("1");
        String description = str[2];

        return switch (type) {
            case "T" -> new Todo(description, isDone);
            case "D" -> {
                if (str.length < 4) yield null;
                yield new Deadline(description, str[3], isDone);
            }
            case "E" -> {
                if (str.length < 5) yield null;
                yield new Event(description, str[3], str[4], isDone);
            }
            default -> null; // Invalid type
        };
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}