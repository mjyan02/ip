import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(); // Array to store tasks

        while (true) {
            try {
                String input = sc.nextLine();
                String[] inputArr = input.split(" ", 2); // Split arguments for marking and unmarking
                String inputLowerCase = inputArr[0].toLowerCase();

                switch (inputLowerCase) {
                    case "bye":
                        System.out.println("Bye! Hope you come back soon!!");
                        sc.close();
                        return;

                    case "list":
                        listTasks(tasks);
                        break;

                    case "mark":

                    case "unmark":
                        modifyTask(tasks, inputArr, inputLowerCase.equals("mark"));
                        break;

                    case "todo":
                        if (inputArr.length < 2) {
                            throw new DukeException("Please include a description of the task in the format: todo <description>!");
                        } else {
                            tasks.add(new Todo(inputArr[1]));
                            addTask(tasks.get(tasks.size() - 1), tasks);
                        }

                        break;

                    case "deadline": {
                        String[] details = inputArr[1].split(" /by ", 2); // Split arguments to get details

                        if (details.length < 2) {
                            throw new DukeException("Please include a description and due date for the task in the format: deadline <description> /by <due date>!");
                        } else {
                            tasks.add(new Deadline(details[0], details[1]));
                            addTask(tasks.get(tasks.size() - 1), tasks);
                        }

                        break;
                    }

                    case "event": {
                        String[] details = inputArr[1].split(" /from ", 2); // Split arguments to get details

                        if (details.length < 2 || !details[1].contains(" /to ")) {
                            throw new DukeException("Please include a description and duration for the task in the format: event <description> /from <start> /to <end>!");
                        } else {
                            String[] duration = details[1].split(" /to ", 2);
                            tasks.add(new Event(details[0], duration[0], duration[1]));
                            addTask(tasks.get(tasks.size() - 1), tasks);
                        }

                        break;
                    }

                    case "delete":
                        deleteTask(tasks, inputArr);
                        break;

                    default:
                        throw new DukeException("I'm sorry, I don't know what that means :(");
                }
            } catch (Exception e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    // Method for adding new tasks
    private static void addTask(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    // Method for listing tasks
    private static void listTasks(ArrayList<Task> tasks) {
        System.out.println(tasks.isEmpty() ? "There are no tasks in your list." : "Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    // Method for marking or unmarking tasks
    private static void modifyTask(ArrayList<Task> tasks, String[] inputArr, boolean isMarked) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please specify a valid task number!");
        }

        try {
            int taskNumber = Integer.parseInt(inputArr[1]) - 1;
            Task task = tasks.get(taskNumber);

            if (isMarked) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    // Method for deleting tasks
    private static void deleteTask(ArrayList<Task> tasks, String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("You must specify a task number to delete.");
        }

        try {
            int taskNumber = Integer.parseInt(inputArr[1]) - 1;
            Task removedTask = tasks.remove(taskNumber);

            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}