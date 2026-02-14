package org.kanelucky.command.tree.node;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.tree.node.CommandNode;

import cn.nukkit.utils.TextFormat;
import org.kanelucky.argument.ArgumentType;
import org.kanelucky.check.ExecutionCheck;
import org.kanelucky.command.CommandMessages;
import org.kanelucky.command.tree.CommandContext;
import org.kanelucky.command.tree.CommandResult;
import org.kanelucky.command.tree.SenderType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Kanelucky
 */
public class CommandRouteNode extends CommandNode {

    private final String name;
    private final List<CommandRouteNode> children = new ArrayList<>();
    private Function<CommandContext, CommandResult> executor;
    private SenderType senderType = SenderType.ANY;
    private String permission;
    private CommandRouteNode parent;
    private final NodeType type;
    private ArgumentType<?> argumentType;
    private String permissionMessage;
    private final CommandMessages messages = new CommandMessages();

    private CommandRouteNode(String name, NodeType type) {
        this.name = name;
        this.type = type;
    }

    public CommandMessages messages() {
        return messages;
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

    public static CommandRouteNode literal(String name) {
        return new CommandRouteNode(name, NodeType.LITERAL);
    }

    public static <T> CommandRouteNode argument(String name, ArgumentType<T> type) {
        CommandRouteNode node = new CommandRouteNode(name, NodeType.ARGUMENT);
        node.argumentType = type;
        return node;
    }

    public NodeType getType() {
        return type;
    }

    public CommandRouteNode exec(Function<CommandContext, CommandResult> executor) {
        this.executor = executor;
        return this;
    }

    public boolean isLeaf() {
        return executor != null;
    }

    public CommandResult execute(CommandContext context) {
        if (executor == null) {
            return CommandResult.fail();
        }
        return executor.apply(context);
    }

    public ArgumentType<?> getArgumentType() {
        return argumentType;
    }

    public CommandRouteNode permission(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandRouteNode permission(String permission, String message) {
        this.permission = permission;
        this.permissionMessage = message;
        return this;
    }

    public CommandRouteNode senderType(SenderType senderType) {
        this.senderType = senderType;
        return this;
    }

    public ExecutionCheck check(CommandSender sender, String defaultPermissionMessage) {

        CommandRouteNode current = this;

        while (current != null) {

            if (!current.checkSenderType(sender)) {
                return ExecutionCheck.fail(TextFormat.RED + "This command can only be executed by a player.");
            }

            if (current.permission != null && !sender.hasPermission(current.permission)) {

                String message = current.permissionMessage != null
                        ? current.permissionMessage
                        : defaultPermissionMessage;

                return ExecutionCheck.fail(message);
            }

            current = current.parent;
        }

        return ExecutionCheck.success();
    }

    private boolean checkSenderType(CommandSender sender) {
        return switch (senderType) {
            case ANY -> true;
            case PLAYER -> sender instanceof Player;
            case CONSOLE -> !(sender instanceof Player);
        };
    }
}
