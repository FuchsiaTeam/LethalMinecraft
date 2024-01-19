package json.jayson.common.item;

import json.jayson.LMNBT;
import json.jayson.LMUtil;
import json.jayson.common.IScrapValue;
import json.jayson.common.IWeight;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DefaultScrapItem extends Item implements IAmScrapLoot {
    int min = 5, max = 100;
    float weight = 1.0f;
    public DefaultScrapItem(Settings settings, int min, int max, float weight) {
        super(settings);
        this.min = min;
        this.max = max;
        this.weight = weight;
    }

    public DefaultScrapItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(!stack.hasNbt()) {
            NbtCompound nbt = new NbtCompound();
            nbt.putInt(LMNBT.SCRAP_VALUE, LMUtil.RANDOM.nextInt(min, max));
            nbt.putFloat(LMNBT.WEIGHT, weight);
            stack.setNbt(nbt);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if(stack.hasNbt() && stack.getNbt().contains(LMNBT.SCRAP_VALUE)) {
            tooltip.add(Text.literal(String.valueOf(stack.getNbt().getInt(LMNBT.SCRAP_VALUE))));
        }
    }
}
