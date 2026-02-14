<!-- PROJECT LOGO -->
<div align="center">
  <a href="https://github.com/Kanelucky/CommandTreeAP">
    <img src="tree.png" alt="Logo" width="200" height="200">
  </a>

  <h1>CommandTree</h1>

  <p>A modern command tree API for PowerNukkitX</p>
</div>

> [!IMPORTANT]
> 
> This project is in 0.x stage and is under active development. Breaking changes may occur at any time.


# What is CommandTree?

- CommandTree is a lightweight library that provides a modern command tree API for PowerNukkitX (Minecraft Bedrock server software).

# How to use
```java
public class ExampleCommand extends Command {

    private final CommandRouteTree tree;

    public ExampleCommand() {

        super("example", "Example Command", "/example");
        this.setPermission("example.run");
        this.setAliases(new String[]{"ec"});
        this.tree = buildTree();

    }
    private CommandRouteTree buildTree() {
        CommandRouteTree tree = new CommandRouteTree("example");
        tree.getRoot()
                .senderType(SenderType.ANY)
                .exec((sender, args) -> {
                    sender.sendMessage("This is root");
                    return CommandResult.SUCCESS;
                })
                .then(new CommandRouteNode("subcommand")
                        .permission("example.subcommand.run")
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

```
