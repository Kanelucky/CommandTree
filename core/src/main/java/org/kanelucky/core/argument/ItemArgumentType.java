package org.kanelucky.core.argument;

import cn.nukkit.item.Item;
import cn.nukkit.registry.Registries;
import cn.nukkit.utils.TextFormat;

import org.kanelucky.core.command.tree.CommandContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kanelucky
 */
public class ItemArgumentType implements ArgumentType<Item> {

    @Override
    public Item parse(CommandContext context, String input) {
        Item item = Registries.ITEM.get(input);
        if (item == null) {
            throw new IllegalArgumentException(TextFormat.RED + "Unknown item: " + input);
        }
        return item.clone();
    }

    public static ItemArgumentType item() {
        return new ItemArgumentType();
    }
}
