package json.jayson.common.objects.item.scrap;

import json.jayson.common.init.LMSounds;
import json.jayson.common.objects.item.IAmScrapLoot;
import json.jayson.network.LMNetwork;
import json.jayson.util.LMNBT;
import json.jayson.util.LMUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DefaultScrapItem extends Item implements IAmScrapLoot {
    int min = 5, max = 100;
    float weight = 1.0f;
    SoundEvent pickUpSound = LMSounds.PICKUP_PLASTIC;
    public DefaultScrapItem(Settings settings, int min, int max, float weight, SoundEvent pickUpSound) {
        super(settings.maxCount(1));
        this.min = min;
        this.max = max;
        this.weight = weight;
        this.pickUpSound = pickUpSound;
    }

    public DefaultScrapItem(Settings settings, int min, int max, float weight) {
        super(settings.maxCount(1));
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
            nbt.putInt(LMNBT.Int.SCRAP_VALUE, LMUtil.RANDOM.nextInt(min, max));
            nbt.putFloat(LMNBT.Flt.WEIGHT, weight);
            stack.setNbt(nbt);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if(stack.hasNbt() && stack.getNbt().contains(LMNBT.Int.SCRAP_VALUE)) {
            tooltip.add(Text.literal(String.valueOf(stack.getNbt().getInt(LMNBT.Int.SCRAP_VALUE))));
        }
    }

    @Override
    public SoundEvent getPickUpSound() {
        return pickUpSound;
    }
}
