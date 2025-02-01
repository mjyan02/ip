import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static final String PATH_URL = "C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/duke.txt";

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
        } catch (IOException e) {
            System.out.println("Error getting tasks!");
        }

        return tasks;
    }
}