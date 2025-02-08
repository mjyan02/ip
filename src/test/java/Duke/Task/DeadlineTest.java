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

public class DeadlineTest {
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
    public void testValidDeadlineCreation() throws DukeException {
        Deadline deadline = new Deadline("Submit report", "2024-12-10");
        taskList.addTask(deadline, ui, storage);
        assertEquals("Here are the tasks in your list:\n" +
                "1. [D][ ] Submit report (by: Dec 10 2024)", taskList.listTasks(ui));
    }

    @Test
    public void testMissingDeadlineDescription() {
        assertThrows(DukeException.class, () -> taskList.addTask(new Deadline("", "2024-12-10"), ui, storage));
    }

    @Test
    public void testInvalidDeadlineDate() {
        assertThrows(DukeException.class, () -> taskList.addTask(new Deadline("Submit report", "2024-13-45"), ui, storage));
    }
}