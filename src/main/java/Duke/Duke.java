package duke;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Keywords;
import duke.task.Todo;
import duke.ui.Ui;

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
    public Duke(String filePath) throws DukeException {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTasks());
    }

    /**
     * Runs Duke and processes user inputs.
     *
     * @param input The user input command.
     */
    public void run(String input) {
        assert input != null : "User input should not be null";

        try {
            if (input.equalsIgnoreCase("help")) {
                ui.welcomeMessage();
                return;
            }

            Keywords keyword = Parser.parseKeyword(input);
            assert keyword != null : "Keyword cannot be null";

            switch (keyword) {
                case LIST:
                    tasks.listTasks(ui);
                    break;

                case MARK:
                    String[] markedArgs = input.split(" ", 2);
                    tasks.modifyTask(Integer.parseInt(markedArgs[1]) - 1, true, ui, storage);
                    break;

                case UNMARK:
                    String[] unmarkedArgs = input.split(" ", 2);
                    tasks.modifyTask(Integer.parseInt(unmarkedArgs[1]) - 1, false, ui, storage);
                    break;

                case TODO:
                    String todoDescription = Parser.extractDescription(input);
                    if (todoDescription.isEmpty()) {
                        throw new DukeException("Please include a valid task description!");
                    }
                    tasks.addTask(new Todo(todoDescription), ui, storage);
                    break;

                case DEADLINE:
                    String deadlineDescription = Parser.extractDescription(input);
                    String[] deadlineArgs = deadlineDescription.split(" /by ", 2);
                    if (deadlineArgs.length < 2) {
                        throw new DukeException("Please follow the input format: " +
                                "deadline <description> /by yyyy-MM-dd");
                    }
                    tasks.addTask(new Deadline(deadlineArgs[0], deadlineArgs[1]), ui, storage);
                    break;

                case EVENT:
                    String eventDescription = Parser.extractDescription(input);
                    String[] eventArgs = eventDescription.split(" /from ", 2);
                    if (eventArgs.length < 2 || !eventArgs[1].contains(" /to ")) {
                        throw new DukeException("Please follow the input format: " +
                                "event <description> /from yyyy-MM-dd /to yyyy-MM-dd");
                    }
                    String[] eventTimes = eventArgs[1].split(" /to ", 2);
                    tasks.addTask(new Event(eventArgs[0], eventTimes[0], eventTimes[1]), ui, storage);
                    break;

                case DELETE:
                    String[] deleteArgs = input.split(" ", 2);
                    tasks.deleteTask(Integer.parseInt(deleteArgs[1]) - 1, ui, storage);
                    break;

                case FIND:
                    String[] findArgs = input.split(" ", 2);
                    tasks.findTasks(findArgs.length > 1 ? findArgs[1] : "", ui);
                    break;

                case BYE:
                    ui.displayMessage("Bye! Hope you come back soon!!");
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't recognize that command :(");
            }
        } catch (NumberFormatException e) {
            ui.displayError("Please enter a number!");
        } catch (IndexOutOfBoundsException e) {
            ui.displayError("Please enter a valid task number!");
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
        }
    }
}