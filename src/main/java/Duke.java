import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100]; // Array to store tasks
        int taskCount = 0;

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
                        listTasks(tasks, taskCount);
                        break;

                    case "mark":

                    case "unmark":
                        modifyTask(tasks, inputArr, inputLowerCase.equals("mark"), taskCount);
                        break;

                    case "todo":
                        if (inputArr.length < 2) {
                            throw new DukeException("Please include a description of the task in the format: todo <description>!");
                        } else {
                            tasks[taskCount] = new Todo(inputArr[1]);
                            addTask(tasks[taskCount], ++taskCount);
                        }

                        break;

                    case "deadline": {
                        String[] details = inputArr[1].split(" /by ", 2); // Split arguments to get details

                        if (details.length < 2) {
                            throw new DukeException("Please include a description and due date for the task in the format: deadline <description> /by <due date>!");
                        } else {
                            tasks[taskCount] = new Deadline(details[0], details[1]);
                            addTask(tasks[taskCount], ++taskCount);
                        }
                        break;
                    }

                    case "event": {
                        String[] details = inputArr[1].split(" /from ", 2); // Split arguments to get details

                        if (details.length < 2 || !details[1].contains(" /to ")) {
                            throw new DukeException("Please include a description and duration for the task in the format: event <description> /from <start> /to <end>!");
                        } else {
                            String[] timeDetails = details[1].split(" /to ", 2);
                            tasks[taskCount] = new Event(details[0], timeDetails[0], timeDetails[1]);
                            addTask(tasks[taskCount], ++taskCount);
                        }
                        break;
                    }

                    default:
                        throw new DukeException("I'm sorry, I don't know what that means :(");
                }
            } catch (Exception e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    // Method for listing tasks
    private static void listTasks(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");

            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    // Method for marking or unmarking tasks
    private static void modifyTask(Task[] tasks, String[] inputArr, boolean isMarked, int taskCount) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Please specify a valid task number!");
        }

        try {
            int taskNumber = Integer.parseInt(inputArr[1]) - 1;

            if (taskNumber >= 0 && taskNumber < taskCount) {
                if (isMarked) {
                    tasks[taskNumber].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    tasks[taskNumber].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println("  " + tasks[taskNumber]);
            } else {
                throw new DukeException("Invalid task number!");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    // Method for adding new tasks
    private static void addTask(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}