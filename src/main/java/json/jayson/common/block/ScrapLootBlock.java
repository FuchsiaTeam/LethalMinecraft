package json.jayson.common.block;

import json.jayson.common.blockentity.ScrapLootBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;


/* We are using ScrapLootEntity instead */
@Deprecated
public class ScrapLootBlock extends Block implements BlockEntityProvider {
    public ScrapLootBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ScrapLootBlockEntity(pos, state);
    }
}
