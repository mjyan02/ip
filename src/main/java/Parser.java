public class Parser {
    public static Keywords parseKeyword(String input) {
        return Keywords.fromString(input.split(" ")[0]);
    }
}