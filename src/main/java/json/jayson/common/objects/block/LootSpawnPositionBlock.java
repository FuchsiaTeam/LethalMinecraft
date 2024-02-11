package json.jayson.common.objects.block;

import json.jayson.common.LMData;
import json.jayson.common.init.LMEntities;
import json.jayson.common.init.LMItems;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.util.LMNBT;
import json.jayson.util.LMUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class LootSpawnPositionBlock extends Block {
    public LootSpawnPositionBlock(Settings settings) {
        super(settings.noCollision());
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if(!world.isClient() && LMData.GENERATING_LOOT_DUNGEON) {
            System.out.println("ADDED");
        }

        if(!world.isClient) {
            Random random = new Random();
            System.out.println("YEET");
            if(random.nextInt(2) == 0) {
                ScrapLootEntity scrapLootEntity = new ScrapLootEntity(LMEntities.SCRAP_LOOT, world);
                scrapLootEntity.setPosition(new Vec3d(pos.getX() + LMUtil.RANDOM.nextFloat(), pos.getY(), pos.getZ() + LMUtil.RANDOM.nextFloat()));
                scrapLootEntity.setItem(LMItems.AXOLOTL_PLUSHIE.getDefaultStack());
                scrapLootEntity.setYaw(LMUtil.RANDOM.nextFloat() * 360);
                world.spawnEntity(scrapLootEntity);
            }
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
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
