package json.jayson.init;

import json.jayson.LMUtil;
import json.jayson.common.blockentity.ScrapLootBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMBlockEntities {

    public static final BlockEntityType<ScrapLootBlockEntity> SCRAP_LOOT = Registry.register(Registries.BLOCK_ENTITY_TYPE, LMUtil.createLocation("scrap_loot"),
            FabricBlockEntityTypeBuilder.create(ScrapLootBlockEntity::new, LMBlocks.SCRAP_LOOT).build());

    public static void register() {
    }

}
