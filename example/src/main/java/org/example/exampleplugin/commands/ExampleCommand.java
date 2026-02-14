package org.example.exampleplugin.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.simple.Arguments;
import com.sun.jdi.connect.Connector;
import org.kanelucky.argument.ArgumentType;
import org.kanelucky.argument.IntegerArgumentType;
import org.kanelucky.argument.PlayerArgumentType;
import org.kanelucky.argument.StringArgumentType;
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
                .senderType(SenderType.PLAYER)
                .exec(ctx ->{
                    ctx.getSender().asPlayer().sendMessage("HAHAHHAHA");
                    return CommandResult.success();
                })
                .then(CommandRouteNode.literal("haha")
                        .senderType(SenderType.PLAYER)
                        .exec(ctx -> {
                            ctx.getSender().sendMessage("HAHAH");
                            return CommandResult.success();
                        }));
        return tree;
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        CommandResult result = tree.dispatch(sender, args);
        return result == CommandResult.success();
    }
}
