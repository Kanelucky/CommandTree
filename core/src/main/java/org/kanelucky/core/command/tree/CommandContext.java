package org.kanelucky.core.command.tree;

import cn.nukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kanelucky
 */
public class CommandContext {

    private final CommandSender sender;
    private final Map<String, Object> arguments = new HashMap<>();

    public CommandContext(CommandSender sender) {
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }

    public void putArg(String name, Object value) {
        arguments.put(name, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getArg(String name) {
        return (T) arguments.get(name);
    }

    public boolean hasArg(String name) {
        return arguments.containsKey(name);
    }

    public CommandResult success() {
        return CommandResult.success();
    }

    public CommandResult fail() {
        return CommandResult.fail();
    }

    public CommandResult fail(String message) {
        return CommandResult.fail(message);
    }
}


