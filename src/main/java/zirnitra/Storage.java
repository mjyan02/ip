package zirnitra;

import zirnitra.exceptions.ZirnitraException;
import zirnitra.task.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles getting and saving tasks to a local file on the hard disk.
 */
public class Storage {
    /**
     * The file path where tasks are stored on the hard disk.
     */
    protected static String PATH_URL;

    /**
     * Initializes the Storage with the specified file path.
     *
     * @param filePath The file path where tasks will be stored locally.
     */
    public Storage(String filePath) {
        PATH_URL = filePath;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(PATH_URL);
            file.getParentFile().mkdirs(); // Check if directory exists, if not make new dir
            FileWriter w = new FileWriter(file);

            for (Task task : tasks) {
                w.write(task.toFile() + "\n");
            }

            w.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks!");
        }
    }

    /**
     * Gets tasks from the storage file.
     *
     * @return An ArrayList containing the tasks from the storage file.
     */
    public static ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(PATH_URL);

        if (!file.exists()) {
            return tasks; // Return empty list
        }

        try {
            FileReader reader = new FileReader(file);
            int ch;
            StringBuilder line = new StringBuilder();

            while ((ch = reader.read()) != -1) {
                if (ch == '\n') {
                    tasks.add(Task.fromFile(line.toString()));
                    line.setLength(0);
                } else {
                    line.append((char) ch);
                }
            }

            // Add last line if no newline at end
            if (!line.isEmpty()) {
                tasks.add(Task.fromFile(line.toString()));
            }

            reader.close();
        } catch (IOException | ZirnitraException e) {
            System.out.println("Error getting tasks!");
        }

        return tasks;
    }
}