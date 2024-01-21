package json.jayson.util;

import json.jayson.LM;
import json.jayson.init.LMDimensions;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;

public class LMMoonUtil {

    public static boolean extractExperimentation(MinecraftServer server) {
        return LMUtil.extractMoon(FabricLoader.getInstance().getModContainer(LM.ID).get(), server, LMDimensions.MOON_EXPERIMENTATION_WORLD, "data/lm/moons/experimentation/region/");
    }

}
