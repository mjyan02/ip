import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String inputLowerCase = input.toLowerCase();

            if (inputLowerCase.equals("bye")) {
                System.out.println("Bye. Please come back soon!");
                break;
            } else {
                System.out.println(input);
            }
        }

        sc.close();
    }
}