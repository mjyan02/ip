package Duke;

import Duke.Exceptions.DukeException;
import Duke.Task.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    protected static String PATH_URL;

    public Storage(String filePath) {
        PATH_URL = filePath;
    }

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
        } catch (IOException | DukeException e) {
            System.out.println("Error getting tasks!");
        }

        return tasks;
    }
}