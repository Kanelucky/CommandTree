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

```
