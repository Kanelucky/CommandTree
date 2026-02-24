package org.kanelucky.core.command.tree.node;

import cn.nukkit.command.data.CommandEnum;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.tree.ParamList;
import cn.nukkit.command.tree.node.IParamNode;

import org.kanelucky.core.argument.ArgumentType;
import org.kanelucky.core.command.tree.CommandContext;

/**
 * @author Kanelucky
 */
public class ArgumentNode<T> implements IParamNode<T> {

    private final String name;
    private final ArgumentType<T> argumentType;
    private ParamList paramList;
    private T result;
    private boolean hasResult = false;
    private boolean optional = false;

    public ArgumentNode(String name, ArgumentType<T> argumentType) {
        this.name = name;
        this.argumentType = argumentType;
    }

    @Override
    public void fill(String arg) {
        CommandContext context = new CommandContext(paramList.getParamTree().getSender());
        try {
            this.result = argumentType.parse(context, arg);
            this.hasResult = true;
        } catch (IllegalArgumentException e) {
            this.error(e.getMessage());
        }
    }

    @Override
    public <E> E get() {
        return (E) result;
    }

    @Override
    public void reset() {
        this.result = null;
        this.hasResult = false;
    }

    @Override
    public boolean hasResult() {
        return hasResult;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    @Override
    public ParamList getParamList() {
        return paramList;
    }

    @Override
    public IParamNode<T> init(ParamList parent, String name, boolean optional,
                              CommandParamType type, CommandEnum enumData, String postFix) {
        this.paramList = parent;
        this.optional = optional;
        return this;
    }
}