package Duke.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Duke.Ui.Ui;
import Duke.*;
import Duke.Exceptions.DukeException;
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
        assertEquals("[T][ ] Read book", taskList.getTask(0).toString());
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