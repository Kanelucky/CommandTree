package org.kanelucky.argument;

import org.kanelucky.command.tree.CommandContext;

/**
 * @author Kanelucky
 */
public interface ArgumentType<T> {

    T parse(CommandContext context, String input) throws IllegalArgumentException;

}
