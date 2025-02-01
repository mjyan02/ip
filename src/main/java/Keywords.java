public enum Keywords {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE;

    public static Keywords fromString(String commandStr) {
        return Keywords.valueOf(commandStr.toUpperCase());
    }
}
