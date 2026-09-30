/** Echo-only tutorial backend with command categories for styling. */
public class Duke {
    private String commandType = "";

    /** Generates an echo response and records the input category for the GUI. */
    public String getResponse(String input) {
        String command = input.strip().split("\\s+", 2)[0].toLowerCase(java.util.Locale.ROOT);
        commandType = switch (command) {
        case "todo", "deadline", "event" -> "AddCommand";
        case "mark", "unmark" -> "ChangeMarkCommand";
        case "delete" -> "DeleteCommand";
        default -> "";
        };
        return "Duke heard: " + input;
    }

    public String getCommandType() {
        return commandType;
    }
}
