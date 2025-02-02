package Duke;

import Duke.Task.Keywords;

public class Parser {
    public Parser() {
    }

    public static Keywords parseKeyword(String input) {
        return Keywords.fromString(input.split(" ")[0]);
    }
}