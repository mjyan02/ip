package zirnitra.main;

import zirnitra.task.Keywords;

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

    /**
     * Extracts a task description from the user's input.
     *
     * @param input User input.
     * @return The task description only.
     */
    public static String extractDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        if (firstSpaceIndex == -1) {
            return "";
        }
        return input.substring(firstSpaceIndex + 1).trim();
    }
}