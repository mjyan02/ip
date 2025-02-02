package Duke;

import Duke.Exceptions.DukeException;
import Duke.Task.Deadline;
import Duke.Ui.Ui;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Method to add new tasks
    public void addTask(Task task, Ui ui, Storage storage) throws DukeException {
        if (task != null) {
            String description = task.getDescription().trim();

            if (description.isEmpty()) {
                throw new DukeException("Please include a description!");
            }

            if (description.matches("\\d+")) {
                throw new DukeException("Description cannot be only numbers!");
            }
        }

        tasks.add(task);
        ui.displayMessage("Got it. I've added this task:\n  " + task);
        ui.displayMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks);
    }

    // Method to list tasks
    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.displayMessage("There are no tasks in your list.");
        } else {
            ui.displayMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.displayMessage((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Method to mark or unmark tasks
    public void modifyTask(Integer tN, boolean isMarked, Ui ui, Storage storage) throws DukeException {
        int taskNumber = validateArguments(tN);
        Task task = tasks.get(taskNumber);

        if (isMarked) {
            task.markAsDone();
            ui.displayMessage("Nice! I've marked this task as done:\n  " + task);
        } else {
            task.markAsNotDone();
            ui.displayMessage("OK, I've marked this task as not done yet:\n  " + task);
        }
        storage.saveTasks(tasks);
    }

    // Method for deleting tasks
    public void deleteTask(Integer tN, Ui ui, Storage storage) throws DukeException {
        int taskNumber = validateArguments(tN);
        Task removedTask = tasks.remove(taskNumber);
        ui.displayMessage("Noted. I've removed this task:\n  " + removedTask);
        ui.displayMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.saveTasks(tasks);
    }

    // Method to check for correct arguments
    private int validateArguments(Integer tN) throws DukeException {
        int taskNumber = tN;

        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new DukeException("Task does not exist! Please enter a number between 1 and " + tasks.size() + ".");
        }

        return taskNumber;
    }

    // For testing purposes
    public int getSize() {
        return tasks.size();
    }

    // For testing purposes
    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number! Please enter a valid number.");
        }

        return tasks.get(index);
    }
}