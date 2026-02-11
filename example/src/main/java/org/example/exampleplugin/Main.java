package org.example.exampleplugin;

import cn.nukkit.plugin.PluginBase;

import org.example.exampleplugin.commands.ExampleCommand;

public class Main extends PluginBase {
    @Override
    public void onEnable() {
        //register the command
        this.getServer().getCommandMap().register("examplecommand", new ExampleCommand());
    }
}
