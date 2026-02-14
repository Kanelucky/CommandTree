package org.kanelucky;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;

import org.kanelucky.command.tree.CommandTree;
import org.kanelucky.command.tree.CommandTreeFactory;

/**
 * @author Kanelucky
 */
public class CommandTreeMain extends PluginBase {

    private CommandTree commandTree;

    @Override
    public void onEnable() {

        Command rootCommand = new Command("example") {
            @Override
            public boolean execute(
                    CommandSender sender,
                    String label,
                    String[] args
            ) {
                commandTree.parse(sender, args);
                return true;
            }
        };

        CommandTree.Factory factory = new CommandTreeFactory();
        this.commandTree = factory.create(rootCommand);

        getServer().getCommandMap().register(
                "example",
                rootCommand
        );
    }
}
