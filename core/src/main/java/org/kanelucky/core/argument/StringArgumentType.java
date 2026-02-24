package org.kanelucky.core.argument;

import org.kanelucky.core.command.tree.CommandContext;

/**
 * @author Kanelucky
 */
public class StringArgumentType implements ArgumentType<String> {

    @Override
    public String parse(CommandContext context, String input) {
        return input;
    }

    public static StringArgumentType string() {
        return new StringArgumentType();
    }

}
