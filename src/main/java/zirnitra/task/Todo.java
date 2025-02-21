package zirnitra.task;

/**
 * Represents a Todo task that contains a task description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     * @param isDone Specifies if task is marked
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the task into a formatted string representation suitable for file storage on hard disk.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFile() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}