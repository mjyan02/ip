package zirnitra.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import zirnitra.Storage;
import zirnitra.TaskList;
import zirnitra.ui.Ui;
import zirnitra.exceptions.ZirnitraException;

import java.nio.file.Paths;
import java.util.ArrayList;

public class TodoTest {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    public void run() {
        ui = new Ui();
        storage = new Storage(Paths.get(System.getProperty("user.dir"), "TaskTest").toString());
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testValidTodoCreation() throws ZirnitraException {
        Todo todo = new Todo("Read book");
        taskList.addTask(todo, ui, storage);
        assertEquals("Here are the tasks in your list:\n" +
                "1. [T][ ] Read book", taskList.listTasks(ui));
    }

    @Test
    public void testMissingTodoDescription() {
        assertThrows(ZirnitraException.class, () -> taskList.addTask(new Todo(""), ui, storage));
    }

    @Test
    public void testInvalidTodoInput() {
        assertThrows(ZirnitraException.class, () -> taskList.addTask(new Todo("123456"), ui, storage));
    }
}