package json.jayson.init;

import json.jayson.common.item.ShotgunItem;
import json.jayson.util.LMUtil;
import json.jayson.common.item.DefaultScrapItem;
import json.jayson.common.item.FlashLightItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMItems {


	//Tools
    public static final Item IRON_STOP_SIGN = registerItem("iron_stop_sign", new DefaultScrapItem(new FabricItemSettings(), 5, 50, 2.0f));
    public static final Item GOLDEN_YIELD_SIGN = registerItem("golden_yield_sign", new DefaultScrapItem(new FabricItemSettings(), 5, 50, 3.0f));
	
	//Tech
    public static final Item DEFAULT_FLASHLIGHT = registerItem("lime_flashlight", new FlashLightItem(new FabricItemSettings()));

    public static final Item OAK_SHOTGUN = registerItem("oak_shotgun", new ShotgunItem(new FabricItemSettings()));


    //Scrap
    public static final Item AXOLOTL_PLUSHIE = registerItem("axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item WHITE_AXOLOTL_PLUSHIE = registerItem("white_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item BLUE_AXOLOTL_PLUSHIE = registerItem("blue_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item YELLOW_AXOLOTL_PLUSHIE = registerItem("yellow_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item BROWN_AXOLOTL_PLUSHIE = registerItem("brown_axolotl_plushie", new DefaultScrapItem(new FabricItemSettings()));

    public static final Item AIR_HORN = registerItem("air_horn", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item LOUD_HORN = registerItem("loud_horn", new DefaultScrapItem(new FabricItemSettings()));

    public static final Item PLASTIC_COD = registerItem("plastic_cod", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item PLASTIC_SALMON = registerItem("plastic_salmon", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item PLASTIC_TROPICAL = registerItem("plastic_tropical", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item PLASTIC_PUFFER_FISH = registerItem("plastic_puffer_fish", new DefaultScrapItem(new FabricItemSettings()));


    public static final Item COCA_COLA = registerItem("coca_cola", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item COCA_COLA_CAN = registerItem("coca_cola_can", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item PEPSI = registerItem("pepsi", new DefaultScrapItem(new FabricItemSettings()));
    public static final Item PEPSI_CAN = registerItem("pepsi_can", new DefaultScrapItem(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, LMUtil.createLocation(name), item);
    }

    public static void register() {}
}
