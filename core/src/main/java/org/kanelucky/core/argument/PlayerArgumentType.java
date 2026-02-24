package org.kanelucky.core.argument;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.TextFormat;

import org.kanelucky.core.command.tree.CommandContext;

import java.util.List;

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
            throw new IllegalArgumentException(TextFormat.RED + "Player not found: " + input);
        }

        return player;
    }

    public static PlayerArgumentType player() {
        return new PlayerArgumentType();
    }

}

