package json.jayson.datagen.data;

import json.jayson.common.init.LMBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class LMLootTableProvider extends FabricBlockLootTableProvider {
    public LMLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(LMBlocks.STEEL_BLOCK);
        addDrop(LMBlocks.REINFORCED_GLASS);
        addDrop(LMBlocks.STEEL_GRATE);
        addDrop(LMBlocks.STEEL_PILLAR);
        addDrop(LMBlocks.STEEL_LIGHT_WALL);
        addDrop(LMBlocks.STEEL_PLATE);
    }
}
