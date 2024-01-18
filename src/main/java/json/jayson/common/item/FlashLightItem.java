package json.jayson.common.item;

import net.minecraft.block.Blocks;
import net.minecraft.block.LightBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FlashLightItem extends Item implements IMultiModelItem {
    public FlashLightItem(Settings settings) {
        super(settings);
    }

    public static ArrayList<BlockPos> LIGHTS = new ArrayList<>();

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(selected) {
            for (BlockPos light : LIGHTS) {
                if(world.getBlockState(light).getBlock().equals(Blocks.LIGHT)) {
                    world.setBlockState(light, Blocks.AIR.getDefaultState(), 0);
                }
            }
            Direction playerDir = entity.getMovementDirection();
            BlockPos playerPos = entity.getBlockPos().up();
            for (int i = 2; i < 10; i = i + 2) {
                if (world.getBlockState(playerPos.add(playerDir.getVector().multiply(i))).getBlock().equals(Blocks.AIR)) {
                    world.setBlockState(playerPos.add(playerDir.getVector().multiply(i)), Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, 7), 0);
                    LIGHTS.add(playerPos.add(playerDir.getVector().multiply(i)));
                    System.out.println(i);
                }
            }
        } else {
            for (BlockPos light : LIGHTS) {
                if(world.getBlockState(light).getBlock().equals(Blocks.LIGHT)) {
                    world.setBlockState(light, Blocks.AIR.getDefaultState(), 0);
                }
            }
            LIGHTS.clear();
        }
    }
}
