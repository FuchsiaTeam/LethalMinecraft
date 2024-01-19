package json.jayson.common.item;

import net.minecraft.block.Blocks;
import net.minecraft.block.LightBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FlashLightItem extends Item {
    public FlashLightItem(Settings settings) {
        super(settings);
    }

    public static ArrayList<BlockPos> LIGHTS = new ArrayList<>();

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(selected) {
            Vec3d rotVec = entity.getRotationVector();
            for (int i = 2; i < 10; i = i + 2) {
                Vec3d lightPos3d = entity.getPos().add(0, 1, 0).add(rotVec.multiply(i));
                BlockPos lightPos = new BlockPos((int) lightPos3d.x, (int) lightPos3d.y, (int) lightPos3d.z);
                if (world.getBlockState(lightPos).getBlock().equals(Blocks.AIR)) {
                    world.setBlockState(lightPos, Blocks.LIGHT.getDefaultState().with(LightBlock.LEVEL_15, 7), 0);
                    LIGHTS.add(lightPos);
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
