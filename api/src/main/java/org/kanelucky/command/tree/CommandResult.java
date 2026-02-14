package org.kanelucky.command.tree;

import cn.nukkit.utils.TextFormat;

/**
 * @author Kanelucky
 */
public class CommandResult {

    private final boolean success;
    private final String message;
    private static final String DEFAULT_FAIL_MESSAGE = TextFormat.RED + "Command failed.";

    private CommandResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static CommandResult success() {
        return new CommandResult(true, null);
    }

    public static CommandResult fail() {
        return new CommandResult(false, DEFAULT_FAIL_MESSAGE);
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
