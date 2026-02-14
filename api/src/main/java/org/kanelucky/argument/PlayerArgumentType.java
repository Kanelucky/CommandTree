package org.kanelucky.argument;

import cn.nukkit.Player;

import org.kanelucky.command.tree.CommandContext;

/**
 * @author Kanelucky
 */
public class PlayerArgumentType implements ArgumentType<Player> {

    @Override
    public Player parse(CommandContext context, String input) {

        Player player = context.getSender()
                .getServer()
                .getPlayerExact(input);

        if (player == null) {
            throw new IllegalArgumentException("Player not found: " + input);
        }

        return player;
    }

    public static PlayerArgumentType player() {
        return new PlayerArgumentType();
    }
}

