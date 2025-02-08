package duke;

import duke.task.Keywords;

/**
 * Parses user input commands and returns the corresponding keyword.
 */
public class Parser {
    /**
     * Default constructor for a Parser instance.
     */
    public Parser() {
    }

    /**
     * Parses the user's input command and returns the corresponding keyword.
     *
     * @param input The user's raw input command.
     * @return The corresponding keyword as an Enum.
     */
    public static Keywords parseKeyword(String input) {
        return Keywords.fromString(input.split(" ")[0]);
    }
}