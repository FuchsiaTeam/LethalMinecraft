package json.jayson.common.item;

import json.jayson.LMNBT;
import json.jayson.common.IScrapValue;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;

public class DefaultScrapItem extends Item implements IScrapValue {
    int min = 5, max = 100;
    public DefaultScrapItem(Settings settings, int min, int max) {
        super(settings);
        this.min = min;
        this.max = max;
    }
}
