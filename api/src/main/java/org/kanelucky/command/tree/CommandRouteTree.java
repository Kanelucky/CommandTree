package org.kanelucky.command.tree;

import cn.nukkit.command.CommandSender;

import org.kanelucky.command.tree.node.CommandRouteNode;

public class CommandRouteTree {

    private final CommandRouteNode root;

    public CommandRouteTree(String rootName) {
        this.root = new CommandRouteNode(rootName);
    }

    public CommandRouteNode getRoot() {
        return root;
    }

    public CommandResult dispatch(CommandSender sender, String[] args) {
        CommandRouteNode current = root;
        int consumed = 0;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            CommandRouteNode next = null;
            for (CommandRouteNode child : current.getChildren()) {
                if (child.getName().equalsIgnoreCase(arg)) {
                    next = child;
                    break;
                }
            }

            if (next == null) {
                break;
            }

            current = next;
            consumed++;
        }

        if (current.isLeaf()) {

            if (!current.canExecute(sender)) {
                sender.sendMessage("§cYou cannot use this command.");
                return CommandResult.ERROR;
            }

            String[] remainingArgs =
                    java.util.Arrays.copyOfRange(args, consumed, args.length);

            return current.execute(sender, remainingArgs);
        }

        sender.sendMessage("§cWrong command syntax");
        return CommandResult.INVALID_SYNTAX;
    }
}

