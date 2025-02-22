package zirnitra.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import zirnitra.main.Storage;
import zirnitra.main.TaskList;
import zirnitra.ui.Ui;
import zirnitra.exceptions.ZirnitraException;

import java.nio.file.Paths;
import java.util.ArrayList;

public class DeadlineTest {
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
    public void testValidDeadlineCreation() throws ZirnitraException {
        Deadline deadline = new Deadline("Submit report", "2024-12-10");
        taskList.addTask(deadline, ui, storage);
        assertEquals("Here are the tasks in your list:\n" +
                "1. [D][ ] Submit report (by: Dec 10 2024)", taskList.listTasks(ui));
    }

    @Test
    public void testMissingDeadlineDescription() {
        assertThrows(ZirnitraException.class, () -> taskList.addTask(new Deadline("", "2024-12-10"), ui, storage));
    }

    @Test
    public void testInvalidDeadlineDate() {
        assertThrows(ZirnitraException.class, () -> taskList.addTask(new Deadline("Submit report", "2024-13-45"), ui, storage));
    }
}