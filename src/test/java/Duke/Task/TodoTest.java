package duke.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TodoTest {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void run() {
        ui = new Ui();
        storage = new Storage("C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/duke.txt");
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testValidTodoCreation() throws DukeException {
        Todo todo = new Todo("Read book");
        taskList.addTask(todo, ui, storage);
        assertEquals("Here are the tasks in your list:\n" +
                "1. [T][ ] Read book", taskList.listTasks(ui));
    }

    @Test
    public void testMissingTodoDescription() {
        assertThrows(DukeException.class, () -> taskList.addTask(new Todo(""), ui, storage));
    }

    @Test
    public void testInvalidTodoInput() {
        assertThrows(DukeException.class, () -> taskList.addTask(new Todo("123456"), ui, storage));
    }
}