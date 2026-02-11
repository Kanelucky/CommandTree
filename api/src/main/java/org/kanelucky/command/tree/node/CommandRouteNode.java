package org.kanelucky.command.tree.node;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.tree.node.CommandNode;

import org.kanelucky.command.tree.CommandResult;
import org.kanelucky.command.tree.SenderType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class CommandRouteNode extends CommandNode {

    private final String name;
    private final List<CommandRouteNode> children = new ArrayList<>();
    private BiFunction<CommandSender, String[], CommandResult> executor;
    private SenderType senderType = SenderType.ANY;
    private String permission;
    private CommandRouteNode parent;

    public CommandRouteNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<CommandRouteNode> getChildren() {
        return children;
    }

    public CommandRouteNode then(CommandRouteNode child) {
        child.parent = this;
        children.add(child);
        return this;
    }

    public CommandRouteNode exec(
            BiFunction<CommandSender, String[], CommandResult> executor
    ) {
        this.executor = executor;
        return this;
    }

    public boolean isLeaf() {
        return executor != null;
    }

    public CommandResult execute(CommandSender sender, String[] args) {
        if (executor == null) {
            return CommandResult.INVALID_SYNTAX;
        }
        return executor.apply(sender, args);
    }

    public CommandRouteNode permission(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandRouteNode senderType(SenderType type) {
        this.senderType = type;
        return this;
    }

    public boolean canExecute(CommandSender sender) {

        CommandRouteNode current = this;

        while (current != null) {

            if (!current.checkSenderType(sender)) {
                return false;
            }

            if (current.permission != null && !sender.hasPermission(current.permission)) {
                return false;
            }

            current = current.parent;
        }

        return true;
    }

    private boolean checkSenderType(CommandSender sender) {
        return switch (senderType) {
            case ANY -> true;
            case PLAYER -> sender instanceof Player;
            case CONSOLE -> !(sender instanceof Player);
        };
    }
}
