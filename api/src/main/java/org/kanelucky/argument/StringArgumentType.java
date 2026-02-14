package org.kanelucky.argument;

import org.kanelucky.command.tree.CommandContext;

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
