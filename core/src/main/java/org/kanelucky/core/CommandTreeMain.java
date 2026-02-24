package org.kanelucky.core;

import cn.nukkit.plugin.PluginBase;

import cn.nukkit.utils.TextFormat;
import org.kanelucky.core.command.tree.CommandTree;

/**
 * @author Kanelucky
 */
public class CommandTreeMain extends PluginBase {

    private CommandTree commandTree;

    @Override
    public void onEnable() {
        this.getLogger().info(
                TextFormat.GREEN + "CommandTree-API " +
                        TextFormat.BLUE + "(https://github.com/Kanelucky/CommandTree) " +
                        TextFormat.GREEN + "enabled successfully!");
    }
}
