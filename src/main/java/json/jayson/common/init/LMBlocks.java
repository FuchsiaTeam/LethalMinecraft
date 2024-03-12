package json.jayson.common.init;

import json.jayson.common.objects.block.LootDungeonDoorBlock;
import json.jayson.common.objects.block.LootSpawnPositionBlock;
import json.jayson.util.LMIdentifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMBlocks {

    //public static final ScrapLootBlock SCRAP_LOOT = Registry.register(Registries.BLOCK, LMIdentifier.lm("scrap_loot"), new ScrapLootBlock(FabricBlockSettings.create().nonOpaque()));

    /* BUILDING */
    public static final Block STEEL_BLOCK = registerBlock("steel_block", new Block(FabricBlockSettings.create()));
    public static final Block STEEL_GRATE = registerBlock("steel_grate", new Block(FabricBlockSettings.create().nonOpaque()));
    public static final Block STEEL_LIGHT_WALL = registerBlock("steel_light_wall", new Block(FabricBlockSettings.create().luminance(5)));
    public static final Block STEEL_PILLAR = registerBlock("steel_pillar", new Block(FabricBlockSettings.create()));
    public static final Block STEEL_PLATE = registerBlock("steel_plate", new Block(FabricBlockSettings.create()));
    public static final Block REINFORCED_GLASS = registerBlock("reinforced_glass", new Block(FabricBlockSettings.create().nonOpaque()));

    public static final Block ENTRANCE_DOOR = registerBlock("entrance_door", new LootDungeonDoorBlock(FabricBlockSettings.create().nonOpaque(), LootDungeonDoorBlock.DoorType.ENTRANCE));
    public static final Block EXIT_DOOR = registerBlock("exit_door", new LootDungeonDoorBlock(FabricBlockSettings.create().nonOpaque(), LootDungeonDoorBlock.DoorType.EXIT));
    public static final Block DUNGEON_DOOR = registerBlock("dungeon_door", new LootDungeonDoorBlock(FabricBlockSettings.create().nonOpaque(), LootDungeonDoorBlock.DoorType.DEFAULT));
    public static final Block LOOT_POSITION = registerBlock("loot_position", new LootSpawnPositionBlock(FabricBlockSettings.create().nonOpaque()));



    private static Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, LMIdentifier.lm(name), block);
        Registry.register(Registries.ITEM, LMIdentifier.lm(name), new BlockItem(registeredBlock, new FabricItemSettings()));
        return registeredBlock;
    }

    public static void register() {}

}
