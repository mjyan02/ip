package zirnitra.ui;

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
     * Displays the welcome message when Zirnitra starts.
     */
    public void welcomeMessage() {
        String logo = """
                             ______                         _  __           
                            /  _     (_) _ ___  _ ___   (_)    |__   __ __  __ _
                            \\ //  / |  |   '___|  '__   \\ |   |    __|  '___/  __`   |
                            /   // \\|  |   |     |   |   |   |   |   |__|   |   |   (__|  |
                          /______/__|__|     |__|   |__|__| \\___|__|    \\__, __|
                """;

        System.out.println("Greetings, I am Zirnitra, your personal assistant! 🐉\n" + logo);
        System.out.println("I can manage your tasks such as Todos, Deadlines, or Events.");
        System.out.println("Use my commands to mark, list, delete, and find your tasks.");
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