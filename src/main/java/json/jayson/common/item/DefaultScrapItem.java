package json.jayson.common.item;

import json.jayson.common.IScrapValue;
import net.minecraft.item.Item;

public class DefaultScrapItem extends Item implements IScrapValue, IAmScrapLoot {
    int min = 5, max = 100;
    public DefaultScrapItem(Settings settings, int min, int max) {
        super(settings);
        this.min = min;
        this.max = max;
    }
}
