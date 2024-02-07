package json.jayson.common.objects.block;

import json.jayson.common.LMData;
import json.jayson.util.LMNBT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LootSpawnPositionBlock extends Block {
    public LootSpawnPositionBlock(Settings settings) {
        super(settings.noCollision());
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if(!world.isClient() && LMData.GENERATING_LOOT_DUNGEON) {
            System.out.println("ADDED");
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(itemStack.getOrCreateNbt().contains(LMNBT.LOOTMARKER_ID)) {
            System.out.println(itemStack.getOrCreateNbt().getString(LMNBT.LOOTMARKER_ID));
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Marks the Spot where loot can spawn"));
        if(stack.getOrCreateNbt().contains(LMNBT.LOOTMARKER_ID)) {
            tooltip.add(Text.literal("Loot Id: " + stack.getOrCreateNbt().getString(LMNBT.LOOTMARKER_ID)));
        }
    }
}
