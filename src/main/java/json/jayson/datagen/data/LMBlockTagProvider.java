package json.jayson.datagen.data;

import json.jayson.common.init.LMBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class LMBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public LMBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(LMBlocks.STEEL_BLOCK)
                .add(LMBlocks.REINFORCED_GLASS)
                .add(LMBlocks.STEEL_GRATE)
                .add(LMBlocks.STEEL_PILLAR)
                .add(LMBlocks.STEEL_LIGHT_WALL)
                .add(LMBlocks.STEEL_PLATE);
    }
}
