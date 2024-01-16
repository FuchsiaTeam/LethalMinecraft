package json.jayson.init;

import json.jayson.LMUtil;
import json.jayson.common.block.ScrapLootBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMBlocks {

    public static final ScrapLootBlock SCRAP_LOOT = Registry.register(Registries.BLOCK, LMUtil.createLocation("scrap_loot"), new ScrapLootBlock(FabricBlockSettings.create()));


    public static void register() {}

}
