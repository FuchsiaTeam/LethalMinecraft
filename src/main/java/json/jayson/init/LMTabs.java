package json.jayson.init;

import json.jayson.LMUtil;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class LMTabs {

    public static final ItemGroup LM_GROUP = Registry.register(Registries.ITEM_GROUP, LMUtil.createLocation("lethalminecraft"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.lm")).icon(() -> new ItemStack(LMItems.IRON_STOP_SIGN)).entries((displayContext, entries) -> {
                entries.add(LMItems.IRON_STOP_SIGN);
                entries.add(LMItems.DEFAULT_FLASHLIGHT);
                entries.add(LMItems.GOLDEN_STOP_SIGN);
            }).build());

    public static void register() {

    }

}
