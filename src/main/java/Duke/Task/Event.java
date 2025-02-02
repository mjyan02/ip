package Duke.Task;

import Duke.Exceptions.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, start date and end date.
 */
public class Event extends Task {
    /**
     * The start date of the event.
     */
    protected LocalDate from;

    /**
     * The end date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs a new Event task with the given description, start date and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event in yyyy-MM-dd format.
     * @param to The end date of the event in yyyy-MM-dd format.
     * @throws DukeException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please use yyyy-MM-dd!");
        }
    }

    /**
     * Constructs a new Event task with the given description, start date and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event in yyyy-MM-dd format.
     * @param to The end date of the event in yyyy-MM-dd format.
     * @param isDone Specifies if task is marked
     * @throws DukeException If the date format is invalid.
     */
    public Event(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);

        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
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
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}