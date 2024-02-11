package json.jayson.common.objects.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBlockHoldUse {

    int getBlockUseTime();
    void onBlockUse(World world, BlockPos pos);
    default void onBlockUseClient() {}
}
