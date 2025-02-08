package duke.ui;

import java.util.Scanner;

/**
 * Handles all interactions with the user, including reading inputs and displaying messages or responses.
 */
public class Ui {
    /**
     * Scanner object to read user input.
     */
    protected final Scanner sc;

    /**
     * Constructs an Ui object for handling user interactions.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when Duke starts.
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke :D\n" + logo + "\nI can create todos, deadlines or events for you!");
        System.out.println("Use my keywords to mark, list, delete and find your tasks!");
        System.out.println("\nWhat can I do for you?");
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}