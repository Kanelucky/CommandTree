package org.kanelucky.argument;

import org.kanelucky.command.tree.CommandContext;

/**
 * @author Kanelucky
 */
public class IntegerArgumentType implements ArgumentType<Integer> {

    private final int min;
    private final int max;

    public IntegerArgumentType(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer parse(CommandContext context, String input) {

        int value;

        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number: " + input);
        }

        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    "Number must be between " + min + " and " + max
            );
        }

        return value;
    }

    public static IntegerArgumentType integer() {
        return new IntegerArgumentType(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static IntegerArgumentType integer(int min, int max) {
        return new IntegerArgumentType(min, max);
    }
}


