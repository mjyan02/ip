public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public abstract String toFile();

    public static Task fromFile(String line) {
        String[] str = line.split(" \\| ");
        if (str.length < 3) {
            return null; // Corrupt file
        }

        String type = str[0];   // To identify the task type (T, D, E)
        boolean isDone = str[1].equals("1");
        String description = str[2];  // Task description

        return switch (type) {
            case "T" -> new Todo(description, isDone);
            case "D" -> {
                if (str.length < 4) yield null;
                yield new Deadline(description, str[3], isDone);
            }
            case "E" -> {
                if (str.length < 5) yield null;
                yield new Event(description, str[3], str[4], isDone);
            }
            default -> null; // Invalid type
        };
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}