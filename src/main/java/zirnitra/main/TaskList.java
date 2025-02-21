package zirnitra.main;

import zirnitra.exceptions.ZirnitraException;
import zirnitra.ui.Ui;
import zirnitra.task.Task;

import java.util.ArrayList;

/**
 * Manages all the different types of tasks.
 * Provides methods to add, delete, modify, and list tasks.
 * Provides methods used for validating arguments and for testing purposes.
 */
public class TaskList {
    /**
     * ArrayList to store an existing list of tasks.
     */
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList instance with an existing list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     * @param ui The UI instance to display messages.
     * @param storage The storage instance to save the task.
     * @throws ZirnitraException If any errors occur while adding the task.
     */
    public void addTask(Task task, Ui ui, Storage storage) throws ZirnitraException {
        String description = task.getDescription().trim();

        if (description.isEmpty()) {
            throw new ZirnitraException("Please include a description!");
        }

        if (description.matches("\\d+")) {
            throw new ZirnitraException("Description cannot be only numbers!");
        }

        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                throw new ZirnitraException("Duplicate task detected! This task already exists!");
            }
        }

        tasks.add(task);
        ui.displayMessage("Got it. I've added this task:\n" + task);
        ui.displayMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks);
    }

    /**
     * Displays all tasks in the task list
     *
     * @param ui The UI instance to display messages.
     * @return A string representation of all tasks in the task list.
     */
    public String listTasks(Ui ui) {
        StringBuilder result = new StringBuilder();

        if (tasks.isEmpty()) {
            result.append("There are no tasks in your list.");
        } else {
            result.append("Here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }

        String outString = result.toString().trim();
        ui.displayMessage(outString);
        return outString;
    }

    /**
     * Marks or unmarks a task as done.
     *
     * @param tN The task number to modify.
     * @param isMarked True if marked as done, false if unmarked.
     * @param ui The UI instance to display messages.
     * @param storage The storage instance to save changes.
     * @throws ZirnitraException If the task number provided is invalid.
     */
    public void modifyTask(Integer tN, boolean isMarked, Ui ui, Storage storage) throws ZirnitraException {
        int taskNumber = validateArguments(tN);
        Task task = tasks.get(taskNumber);

        if (isMarked) {
            task.markAsDone();
            ui.displayMessage("Nice! I've marked this task as done:\n" + task);
        } else {
            task.markAsNotDone();
            ui.displayMessage("OK, I've marked this task as not done yet:\n " + task);
        }
        storage.saveTasks(tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param tN The task number to delete.
     * @param ui The UI instance to display messages.
     * @param storage The storage instance to save changes.
     * @throws ZirnitraException If the task number provided is invalid.
     */
    public void deleteTask(Integer tN, Ui ui, Storage storage) throws ZirnitraException {
        int taskNumber = validateArguments(tN);
        Task removedTask = tasks.remove(taskNumber);
        ui.displayMessage("Noted. I've removed this task:\n" + removedTask);
        ui.displayMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks);
    }

    /**
     * Finds and displays tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @param ui The UI instance to display results.
     * @return A list of matching tasks.
     * @throws ZirnitraException If no keyword is provided.
     */
    public ArrayList<Task> findTasks(String keyword, Ui ui) throws ZirnitraException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new ZirnitraException("Please enter a keyword to search for!");
        }

        ArrayList<Task> matchedTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toUpperCase().contains(keyword.toUpperCase())) {
                matchedTasks.add(task);
            }
        }

        if (matchedTasks.isEmpty()) {
            ui.displayMessage("I'm sorry, no tasks named '" + keyword + "' were found :(");
        } else {
            ui.displayMessage("Here are the matching tasks in your list:");

            for (int i = 0; i < matchedTasks.size(); i++) {
                ui.displayMessage((i + 1) + ". " + matchedTasks.get(i));
            }
        }

        return matchedTasks;
    }

    /**
     * Validates the task number provided by the user.
     *
     * @param tN The task number to validate.
     * @return The validated task index.
     * @throws ZirnitraException If the task number provided is invalid or out of bounds.
     */
    private int validateArguments(Integer tN) throws ZirnitraException {
        int taskNumber = tN;

        if (tasks.isEmpty()) {
            throw new ZirnitraException("You have no tasks!");
        }

        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new ZirnitraException("Task does not exist! " +
                    "Please enter a number between 1 and " + tasks.size() + ".");
        }

        return taskNumber;
    }

    /**
     * Returns the number of tasks in the task list for testing purposes.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}