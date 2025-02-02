package Duke;

import Duke.Task.*;
import Duke.Exceptions.DukeException;
import Duke.Ui.Ui;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTasks());
    }

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
                        tasks.addTask(new Todo(todoArgs[1]), ui, storage);
                        break;

                    case DEADLINE:
                        String[] deadlineArgs = fullKeyword.split(" /by ", 2);

                        if (deadlineArgs.length < 2) {
                            throw new DukeException("Format: deadline <description> /by yyyy-MM-dd");
                        }

                        tasks.addTask(new Deadline(deadlineArgs[0], deadlineArgs[1]), ui, storage);
                        break;

                    case EVENT:
                        String[] eventArgs = fullKeyword.split(" /from ", 2);

                        if (eventArgs.length < 2 || !eventArgs[1].contains(" /to ")) {
                            throw new DukeException("Format: event <description> /from yyyy-MM-dd /to yyyy-MM-dd");
                        }

                        String[] eventTimes = eventArgs[1].split(" /to ", 2);
                        tasks.addTask(new Event(eventArgs[0], eventTimes[0], eventTimes[1]), ui, storage);
                        break;

                    case DELETE:
                        String[] deleteArgs = fullKeyword.split(" ", 2);
                        tasks.deleteTask(Integer.parseInt(deleteArgs[1]) - 1, ui, storage);
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

    public static void main(String[] args) {
        new Duke("C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/duke.txt").run();
    }
}