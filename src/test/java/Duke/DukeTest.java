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
    public void run() throws DukeException {
        ui = new Ui();
        storage = new Storage("C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/test.txt");
        taskList = new TaskList(new ArrayList<>());

        taskList.addTask(new Todo("read book"), ui, storage);
        taskList.addTask(new Deadline("return book", "2024-12-06"), ui, storage);
        taskList.addTask(new Event("project meeting", "2024-02-20", "2024-02-21"), ui, storage);
    }

    @Test
    public void testAddTask() {
        assertEquals(3, taskList.getSize());
        assertEquals("""
                Here are the tasks in your list:
                1. [T][ ] read book
                2. [D][ ] return book (by: Dec 06 2024)
                3. [E][ ] project meeting (from: Feb 20 2024 to: Feb 21 2024)""", taskList.listTasks(ui));
    }

    @Test
    public void testMarkTaskAsDone() throws DukeException {
        taskList.modifyTask(0, true, ui, storage);
        taskList.modifyTask(2, true, ui, storage);

        assertEquals("""
                Here are the tasks in your list:
                1. [T][X] read book
                2. [D][ ] return book (by: Dec 06 2024)
                3. [E][X] project meeting (from: Feb 20 2024 to: Feb 21 2024)""", taskList.listTasks(ui));
    }

    @Test
    public void testInvalidTaskNumber() {
        Exception exception = assertThrows(DukeException.class, () -> {
            taskList.modifyTask(5, true, ui, storage);
        });

        assertEquals("Task does not exist! Please enter a number between 1 and 3.", exception.getMessage());
    }

    @Test
    public void testDeleteTask() throws DukeException {
        taskList.deleteTask(0, ui, storage);

        assertEquals(2, taskList.getSize());
        assertEquals("""
                Here are the tasks in your list:
                1. [D][ ] return book (by: Dec 06 2024)
                2. [E][ ] project meeting (from: Feb 20 2024 to: Feb 21 2024)""", taskList.listTasks(ui));
    }

    @Test
    public void testFindExistingTask() throws DukeException {
        ArrayList<Task> matchedTasks = taskList.findTasks("book", ui);

        assertEquals(2, matchedTasks.size());
        assertEquals("[T][ ] read book", matchedTasks.get(0).toString());
    }

    @Test
    public void testFindNonExistentTask() throws DukeException {
        ArrayList<Task> matchedTasks = taskList.findTasks("homework", ui);

        assertEquals(0, matchedTasks.size());
    }
}
