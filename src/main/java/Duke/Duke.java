package Duke;

import Duke.Task.*;
import Duke.Exceptions.DukeException;
import Duke.Ui.Ui;

/**
 * This is the Duke application.
 * Duke handles user inputs, executes commands, and manages task storage.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Duke instance that initializes the UI, storage, and task list.
     *
     * @param filePath The file path where tasks are stored on the hard disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTasks());
    }

    /**
     * Runs Duke and processes user input.
     */
    public void run() {
        ui.welcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullKeyword = ui.readKeyword();
                ui.showLine();
                Keywords keyword = Parser.parseKeyword(fullKeyword);

                switch (keyword) {
                    case BYE:
                        ui.displayMessage("Bye! Hope you come back soon!!");
                        isExit = true;
                        break;

                    case LIST:
                        tasks.listTasks(ui);
                        break;

                    case MARK:

                    case UNMARK:
                        String[] markArgs = fullKeyword.split(" ", 2);
                        tasks.modifyTask(Integer.parseInt(markArgs[1]) - 1, keyword.equals(Keywords.MARK), ui, storage);
                        break;

                    case TODO:
                        String[] todoArgs = fullKeyword.split(" ", 2);
                        tasks.addTask(new Todo(todoArgs[1].trim()), ui, storage);
                        break;

                    case DEADLINE:
                        String deadlineDetails = fullKeyword.substring(fullKeyword.indexOf(" ") + 1);
                        String[] deadlineArgs = deadlineDetails.split(" /by ", 2);

                        if (deadlineArgs.length < 2) {
                            throw new DukeException("Format: deadline <description> /by yyyy-MM-dd");
                        }

                        tasks.addTask(new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim()), ui, storage);
                        break;

                    case EVENT:
                        String eventDetails = fullKeyword.substring(fullKeyword.indexOf(" ") + 1);
                        String[] eventArgs = eventDetails.split(" /from ", 2);

                        if (eventArgs.length < 2 || !eventArgs[1].contains(" /to ")) {
                            throw new DukeException("Format: event <description> /from yyyy-MM-dd /to yyyy-MM-dd");
                        }

                        String[] eventTimes = eventArgs[1].split(" /to ", 2);
                        tasks.addTask(new Event(eventArgs[0].trim(), eventTimes[0].trim(), eventTimes[1].trim()), ui, storage);
                        break;

                    case DELETE:
                        String[] deleteArgs = fullKeyword.split(" ", 2);
                        tasks.deleteTask(Integer.parseInt(deleteArgs[1]) - 1, ui, storage);
                        break;

                    case FIND:
                        String[] findArgs = fullKeyword.split(" ", 2);
                        tasks.findTasks(findArgs.length > 1 ? findArgs[1] : "", ui);
                        break;

                    case UNKNOWN:
                        throw new DukeException("I'm sorry, but I don't recognize that command :(");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.displayError("Please enter a valid task number!");
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method of chatbot application to run Duke.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/duke.txt").run();
    }
}