import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

                default:
                    addTask(tasks, input, taskCount++);
            }
        }
    }

    // Method for listing tasks
    private static void listTasks(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    // Method for marking or unmarking tasks
    private static void modifyTask(Task[] tasks, String[] inputArr, boolean isMarked, int taskCount) {
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
                System.out.println("Invalid task number!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input format! Please specify a valid task number.");
        }
    }

    // Method for adding new tasks
    private static void addTask(Task[] tasks, String input, int taskCount) {
        tasks[taskCount] = new Task(input);
        System.out.println("added: " + input);
    }
}