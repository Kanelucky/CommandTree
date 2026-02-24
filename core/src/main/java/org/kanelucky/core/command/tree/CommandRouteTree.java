package org.kanelucky.core.command.tree;

import cn.nukkit.command.CommandSender;

import org.kanelucky.core.check.ExecutionCheck;
import org.kanelucky.core.command.CommandMessages;
import org.kanelucky.core.command.tree.node.CommandRouteNode;
import org.kanelucky.core.command.tree.node.NodeType;

/**
 * @author Kanelucky
 */
public class CommandRouteTree {

    private final CommandRouteNode root;
    private final CommandMessages messages = new CommandMessages();

    private String defaultPermissionMessage = messages.noPermission();
    private String invalidSyntaxMessage = messages.invalidSyntax();

    public CommandRouteTree(String rootName) {
        this.root = CommandRouteNode.literal(rootName);
    }

    public void setDefaultPermissionMessage(String message) {
        this.defaultPermissionMessage = message;
    }

    public String getDefaultPermissionMessage() {
        return defaultPermissionMessage;
    }

    public CommandRouteNode getRoot() {
        return root;
    }

    public CommandResult dispatch(CommandSender sender, String[] args) {

        CommandContext context = new CommandContext(sender);
        CommandRouteNode current = root;

        for (String arg : args) {

            CommandRouteNode next = findNextNode(current, arg, context);

            if (next == null) {
                return CommandResult.fail(invalidSyntaxMessage);
            }

            current = next;
        }

        if (!current.isLeaf()) {
            return CommandResult.fail(invalidSyntaxMessage);
        }

        ExecutionCheck check = current.check(sender, defaultPermissionMessage);

        if (!check.isAllowed()) {
            String message = check.getMessage();
            if (message != null) {
                sender.sendMessage(message);
            }
            return CommandResult.fail(message);
        }

        CommandResult result = current.execute(context);

        if (!result.isSuccess() && result.getMessage() != null) {
            sender.sendMessage(result.getMessage());
        }

        return result;
    }

    private CommandRouteNode findNextNode(
            CommandRouteNode current,
            String arg,
            CommandContext context
    ) {

        for (CommandRouteNode child : current.getChildren()) {
            if (child.getType() == NodeType.LITERAL &&
                    child.getName().equalsIgnoreCase(arg)) {
                return child;
            }
        }

        for (CommandRouteNode child : current.getChildren()) {
            if (child.getType() == NodeType.ARGUMENT) {
                try {
                    Object parsed = child.getArgumentType().parse(context, arg);
                    context.putArg(child.getName(), parsed);
                    return child;
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
        }

        return null;
    }
}