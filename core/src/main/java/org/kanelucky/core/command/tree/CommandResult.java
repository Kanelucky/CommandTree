package org.kanelucky.core.command.tree;

import org.kanelucky.core.command.CommandMessages;

/**
 * @author Kanelucky
 */
public class CommandResult {

    private final boolean success;
    private final String message;

    private CommandResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static CommandResult success() {
        return new CommandResult(true, null);
    }

    public static CommandResult fail() {
        CommandMessages messages = new CommandMessages();
        return new CommandResult(false, messages.defaultFail());
    }

    public static CommandResult fail(String message) {
        return new CommandResult(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}