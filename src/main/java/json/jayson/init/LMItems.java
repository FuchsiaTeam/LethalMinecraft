package json.jayson.init;

import json.jayson.LMUtil;
import json.jayson.common.item.DefaultScrapItem;
import json.jayson.common.item.FlashLightItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMItems {


	//Tools
    public static final Item IRON_STOP_SIGN = registerItem("iron_stop_sign", new DefaultScrapItem(new FabricItemSettings(), 5, 50, 2.0f));
    public static final Item GOLDEN_YIELD_SIGN = registerItem("golden_yield_sign", new DefaultScrapItem(new FabricItemSettings(), 5, 50, 3.0f));
	
	//Tech
    public static final Item DEFAULT_FLASHLIGHT = registerItem("lime_flashlight", new FlashLightItem(new FabricItemSettings()));

    //Scrap
    public static final Item AXOLOTL_PLUSHIE = registerItem("axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item WHITE_AXOLOTL_PLUSHIE = registerItem("white_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item BLUE_AXOLOTL_PLUSHIE = registerItem("blue_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item YELLOW_AXOLOTL_PLUSHIE = registerItem("yellow_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item BROWN_AXOLOTL_PLUSHIE = registerItem("brown_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));

    private static void addToLMTab(FabricItemGroupEntries entries) {
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, LMUtil.createLocation(name), item);
    }

    public static void register() {
        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(LMItems::addToLMTab);
    }
}
