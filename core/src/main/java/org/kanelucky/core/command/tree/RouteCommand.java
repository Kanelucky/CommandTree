package org.kanelucky.core.command.tree;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

/**
 * @author Kanelucky
 */
public abstract class RouteCommand extends Command {

    protected final CommandRouteTree tree;

    public RouteCommand(String name, CommandRouteTree tree) {
        super(name);
        this.tree = tree;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        CommandResult result = tree.dispatch(sender, args);
        return result.isSuccess();
    }
}