package Duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Duke.Ui.Ui;
import Duke.Task.*;
import Duke.Exceptions.DukeException;

import java.util.ArrayList;

public class DukeTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void run() {
        ui = new Ui();
        storage = new Storage("C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/duke.txt");
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void testCreateDeadlineTask() throws DukeException {
        taskList.addTask(new Deadline("Submit report", "2024-12-10"), ui, storage);

        assertEquals(1, taskList.getSize());
        assertEquals("[D][ ] Submit report (by: Dec 10 2024)", taskList.getTask(0).toString());
    }

    @Test
    public void testCreateEventTask() throws DukeException {
        taskList.addTask(new Event("Project meeting", "2024-02-20", "2024-02-21"), ui, storage);

        assertEquals(1, taskList.getSize());
        assertEquals("[E][ ] Project meeting (from: Feb 20 2024 to: Feb 21 2024)", taskList.getTask(0).toString());
    }

    @Test
    public void testMarkTaskAsDone() throws DukeException {
        taskList.addTask(new Todo("Read book"), ui, storage);
        taskList.modifyTask(0, true, ui, storage);

        assertEquals("[T][X] Read book", taskList.getTask(0).toString());
    }

    @Test
    public void testInvalidTaskNumber() {
        Exception exception = assertThrows(DukeException.class, () -> {
            taskList.modifyTask(5, true, ui, storage);
        });

        assertEquals("Task does not exist! Please enter a number between 1 and 0.", exception.getMessage());
    }

    @Test
    public void testDeleteTask() throws DukeException {
        taskList.addTask(new Todo("Go jogging"), ui, storage);
        assertEquals(1, taskList.getSize());

        taskList.deleteTask(0, ui, storage);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testFindExistingTask() throws DukeException {
        taskList.addTask(new Todo("read book"), ui, storage);
        taskList.addTask(new Deadline("return book", "2024-06-06"), ui, storage);
        taskList.addTask(new Event("project meeting", "2024-02-20", "2024-02-21"), ui, storage);

        ArrayList<Task> matchedTasks = taskList.findTasks("book", ui); // This should match "read book" and "return book"

        assertEquals(2, matchedTasks.size());
        assertEquals("[T][ ] read book", matchedTasks.get(0).toString());
        assertEquals("[D][ ] return book (by: Jun 06 2024)", matchedTasks.get(1).toString());
    }

    @Test
    public void testFindNonExistentTask() throws DukeException {
        taskList.addTask(new Todo("read book"), ui, storage);
        taskList.addTask(new Deadline("return book", "2024-06-06"), ui, storage);

        ArrayList<Task> matchedTasks = taskList.findTasks("homework", ui);

        assertEquals(0, matchedTasks.size());
    }
}
