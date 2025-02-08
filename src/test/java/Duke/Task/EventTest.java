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

public class EventTest {
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
    public void testValidEventCreation() throws DukeException {
        Event event = new Event("Project meeting", "2024-02-20", "2024-02-21");
        taskList.addTask(event, ui, storage);
        assertEquals("Here are the tasks in your list:\n" +
                "1. [E][ ] Project meeting (from: Feb 20 2024 to: Feb 21 2024)", taskList.listTasks(ui));
    }

    @Test
    public void testMissingEventDescription() {
        assertThrows(DukeException.class, () -> taskList.addTask(new Event("", "2024-02-20", "2024-02-21"), ui, storage));
    }

    @Test
    public void testInvalidEventDate() {
        assertThrows(DukeException.class, () -> taskList.addTask(new Event("Project meeting", "2024-02-30", "2024-02-31"), ui, storage));
    }
}