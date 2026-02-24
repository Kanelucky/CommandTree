package org.example.exampleplugin.commands;

import cn.nukkit.command.Command;

import cn.nukkit.command.CommandSender;

import org.kanelucky.core.argument.StringArgumentType;
import org.kanelucky.core.command.tree.CommandResult;
import org.kanelucky.core.command.tree.CommandRouteTree;
import org.kanelucky.core.command.tree.SenderType;
import org.kanelucky.core.command.tree.node.CommandRouteNode;

public class ExampleCommand extends Command {

    private final CommandRouteTree tree;

    public ExampleCommand() {

        super("example", "Example Command", "/example");
        this.setAliases(new String[]{"ec"});
        this.tree = buildTree();

    }
    private CommandRouteTree buildTree() {

        CommandRouteTree tree = new CommandRouteTree("example");

        tree.getRoot()
                .permission("example.run", "LOL")
                .senderType(SenderType.PLAYER)
                .exec(ctx ->{
                    ctx.getSender().asPlayer().sendMessage("CommandTree is working!");
                    return CommandResult.success();
                })
                // /example test
                .then(CommandRouteNode.literal("test")
                        .senderType(SenderType.PLAYER)
                        .exec(ctx -> {
                            ctx.getSender().sendMessage("HAHAH");
                            return CommandResult.success();
                        }))
                // /example say <text>
                .then(CommandRouteNode.literal("say")
                        .permission("example.say", "LOL")
                        .then(CommandRouteNode.argument("text", StringArgumentType.string())
                                .exec(ctx -> {
                                    String text = ctx.getArg("text");
                                    ctx.getSender().sendMessage(text);
                                    return CommandResult.success();
                                })));
        return tree;
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        CommandResult result = tree.dispatch(sender, args);
        return result == CommandResult.success();
    }
}
