package org.kanelucky.core.command;

import cn.nukkit.utils.TextFormat;

/**
 * @author Kanelucky
 */
public class CommandMessages {

    private String noPermission = TextFormat.RED + "You do not have permission.";
    private String invalidSyntax = TextFormat.RED + "Invalid command syntax.";
    private String defaultFail = TextFormat.RED + "Command failed.";
    private String playerOnly = TextFormat.RED + "Only players can use this command.";
    private String consoleOnly = TextFormat.RED + "Only console can use this command.";

    public String noPermission() { return noPermission; }
    public String invalidSyntax() { return invalidSyntax; }
    public String defaultFail() { return defaultFail; }
    public String playerOnly() { return playerOnly; }
    public String consoleOnly() { return consoleOnly; }

    public void setNoPermission(String message) { this.noPermission = message; }
    public void setInvalidSyntax(String message) { this.invalidSyntax = message; }
}

