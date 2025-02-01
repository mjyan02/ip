public enum Keywords {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    UNKNOWN;

    public static Keywords fromString(String kw) {
        try {
            return Keywords.valueOf(kw.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN; // Return UNKNOWN for incorrect keywords
        }
    }
}
