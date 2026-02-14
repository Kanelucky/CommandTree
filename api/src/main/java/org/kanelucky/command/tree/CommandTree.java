package org.kanelucky.command.tree;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.tree.node.CommandNode;

import java.util.List;

/**
 * @author Kanelucky
 */
public interface CommandTree {

    CommandResult parse(CommandSender sender, String[] args);

    CommandNode getRoot();

    List<CommandNode> getLeaves();

    interface Factory {
        CommandTree create(Command command);
    }
}
