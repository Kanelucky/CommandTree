package org.example.exampleplugin.commands;

import cn.nukkit.command.Command;

import cn.nukkit.command.CommandSender;
import org.kanelucky.command.tree.CommandResult;
import org.kanelucky.command.tree.CommandRouteTree;
import org.kanelucky.command.tree.SenderType;
import org.kanelucky.command.tree.node.CommandRouteNode;

public class ExampleCommand extends Command {

    private final CommandRouteTree tree;

    public ExampleCommand() {

        super("examplecommand", "Example Command", "/examplecommand");
        this.setPermission("examplecommand.run");
        this.setAliases(new String[]{"ec"});
        this.tree = buildTree();

    }
    private CommandRouteTree buildTree() {
        CommandRouteTree tree = new CommandRouteTree("examplecommand");
        tree.getRoot()
                .senderType(SenderType.ANY)
                .exec((sender, args) -> {
                    sender.sendMessage("This is root");
                    return CommandResult.SUCCESS;
                })
                .then(new CommandRouteNode("subcommand")
                        .permission("examplecommand.subcommand.run")
                        .senderType(SenderType.PLAYER)
                        .exec((sender, args) -> {
                            sender.sendMessage("This is subcommand");
                            return CommandResult.SUCCESS;
                        })
                );

        return tree;

    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        CommandResult result = tree.dispatch(sender, args);
        return result == CommandResult.SUCCESS;
    }
}
