package json.jayson.common.init;

import json.jayson.util.LMNBT;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LMTabs {

    public static final ItemGroup LM_GROUP = Registry.register(Registries.ITEM_GROUP, LMUtil.createLocation("lethalminecraft"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lm")).icon(() -> new ItemStack(LMItems.IRON_STOP_SIGN)).entries((displayContext, entries) -> {
                entries.add(LMItems.DEFAULT_FLASHLIGHT);
                entries.add(LMItems.OAK_SHOTGUN);
                entries.add(LMItems.IRON_STOP_SIGN);
                entries.add(LMItems.GOLDEN_YIELD_SIGN);
                entries.add(LMItems.AXOLOTL_PLUSHIE);
                entries.add(LMItems.WHITE_AXOLOTL_PLUSHIE);
                entries.add(LMItems.BLUE_AXOLOTL_PLUSHIE);
                entries.add(LMItems.BROWN_AXOLOTL_PLUSHIE);
                entries.add(LMItems.YELLOW_AXOLOTL_PLUSHIE);
                entries.add(LMItems.AIR_HORN);
                entries.add(LMItems.LOUD_HORN);
                entries.add(LMItems.PLASTIC_COD);
                entries.add(LMItems.PLASTIC_SALMON);
                entries.add(LMItems.PLASTIC_TROPICAL);
                entries.add(LMItems.PLASTIC_PUFFER_FISH);
                entries.add(LMItems.COCA_COLA);
                entries.add(LMItems.COCA_COLA_CAN);
                entries.add(LMItems.PEPSI);
                entries.add(LMItems.PEPSI_CAN);
            }).build());

    public static final ItemGroup LM_BUILDING_GROUP = Registry.register(Registries.ITEM_GROUP, LMUtil.createLocation("lethalminecraftbuilding"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lm.building")).icon(() -> new ItemStack(LMBlocks.STEEL_PLATE.asItem())).entries((displayContext, entries) -> {
                entries.add(LMBlocks.STEEL_BLOCK);
                entries.add(LMBlocks.STEEL_GRATE);
                entries.add(LMBlocks.STEEL_PLATE);
                entries.add(LMBlocks.STEEL_PILLAR);
                entries.add(LMBlocks.STEEL_LIGHT_WALL);
                entries.add(LMBlocks.REINFORCED_GLASS);
            }).build());

    public static final ItemGroup LM_DEV = Registry.register(Registries.ITEM_GROUP, LMUtil.createLocation("lethalminecraftdev"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lm.dev")).icon(() -> new ItemStack(LMBlocks.LOOT_POSITION.asItem())).entries((displayContext, entries) -> {
                entries.add(LMBlocks.LOOT_POSITION);
                entries.add(addLootMarker(LMMarkers.EXPERIMENTATION));
                entries.add(Blocks.STRUCTURE_BLOCK);
                entries.add(Blocks.STRUCTURE_VOID);
                entries.add(Blocks.COMMAND_BLOCK);
            }).build());


    private static ItemStack addLootMarker(Identifier lootId) {
        ItemStack itemStack = LMBlocks.LOOT_POSITION.asItem().getDefaultStack();
        itemStack.getOrCreateNbt().putString(LMNBT.LOOTMARKER_ID, lootId.toString());
        return itemStack;
    }

    public static void register() {

    }

}
