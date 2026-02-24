package org.kanelucky.core.command.tree;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.tree.node.CommandNode;

import org.kanelucky.core.command.tree.node.CommandRouteNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kanelucky
 */
public class CommandTreeFactory implements CommandTree.Factory {

    @Override
    public CommandTree create(Command command) {

        CommandRouteTree routeTree = new CommandRouteTree(command.getName());

        return new CommandTree() {

            @Override
            public CommandResult parse(CommandSender sender, String[] args) {
                return routeTree.dispatch(sender, args);
            }

            @Override
            public CommandRouteNode getRoot() {
                return routeTree.getRoot();
            }

            @Override
            public List<CommandNode> getLeaves() {
                List<CommandNode> leaves = new ArrayList<>();
                collectLeaves(routeTree.getRoot(), leaves);
                return leaves;
            }

            private void collectLeaves(CommandRouteNode node, List<CommandNode> out) {
                if (node.isLeaf()) {
                    out.add(node);
                }
                for (CommandRouteNode child : node.getChildren()) {
                    collectLeaves(child, out);
                }
            }
        };
    }
}