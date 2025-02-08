package duke.task;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a description and a due date.
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in yyyy-MM-dd format.
     * @throws DukeException If the date format is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please use yyyy-MM-dd!");
        }
    }

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in yyyy-MM-dd format.
     * @param isDone Specifies if task is marked
     * @throws DukeException If the date format is invalid.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);

        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please use yyyy-MM-dd!");
        }
    }

    /**
     * Converts the task into a formatted string representation suitable for file storage on hard disk.
     *
     * @return A string representation of the task for file storage.
     */
    @Override
    public String toFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}