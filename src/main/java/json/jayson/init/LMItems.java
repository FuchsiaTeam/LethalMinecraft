package json.jayson.init;

import json.jayson.LM;
import json.jayson.LMUtil;
import json.jayson.common.item.FlashLightItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LMItems {


	//Tools
	
	//Tech
    public static final Item IRON_STOP_SIGN = registerItem("iron_stop_sign", new Item(new FabricItemSettings()));
    public static final Item DEFAULT_FLASHLIGHT = registerItem("lime_flashlight", new Item(new FabricItemSettings()));

    private static void addToLMTab(FabricItemGroupEntries entries) {
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, LMUtil.createLocation(name), item);
    }

    public static void register() {
        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(LMItems::addToLMTab);
    }
}
