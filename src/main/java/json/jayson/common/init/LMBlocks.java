package json.jayson.common.init;

import json.jayson.util.LMUtil;
import json.jayson.common.objects.block.ScrapLootBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMBlocks {

    public static final ScrapLootBlock SCRAP_LOOT = Registry.register(Registries.BLOCK, LMUtil.createLocation("scrap_loot"), new ScrapLootBlock(FabricBlockSettings.create().nonOpaque()));

    /* BUILDING */
    public static final Block STEEL_BLOCK = registerBlock("steel_block", new Block(FabricBlockSettings.create()));
    public static final Block STEEL_GRATE = registerBlock("steel_grate", new Block(FabricBlockSettings.create().nonOpaque()));
    public static final Block STEEL_LIGHT_WALL = registerBlock("steel_light_wall", new Block(FabricBlockSettings.create().luminance(5)));
    public static final Block STEEL_PILLAR = registerBlock("steel_pillar", new Block(FabricBlockSettings.create()));
    public static final Block STEEL_PLATE = registerBlock("steel_plate", new Block(FabricBlockSettings.create()));
    public static final Block REINFORCED_GLASS = registerBlock("reinforced_glass", new Block(FabricBlockSettings.create().nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, LMUtil.createLocation(name), block);
        Registry.register(Registries.ITEM, LMUtil.createLocation(name), new BlockItem(registeredBlock, new FabricItemSettings()));
        return registeredBlock;
    }

    public static void register() {}

}
