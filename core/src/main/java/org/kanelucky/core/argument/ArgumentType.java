package org.kanelucky.core.argument;

import org.kanelucky.core.command.tree.CommandContext;

import java.util.Collections;
import java.util.List;

/**
 * @author Kanelucky
 */
public interface ArgumentType<T> {

    T parse(CommandContext context, String input) throws IllegalArgumentException;

}
