package json.jayson.util;

import json.jayson.LM;
import json.jayson.common.init.LMDimensions;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LMMoonUtil {

    public static boolean extractExperimentation(MinecraftServer server) {
        return extractMoon(FabricLoader.getInstance().getModContainer(LM.ID).get(), server, LMDimensions.MOON_EXPERIMENTATION_WORLD, "data/lm/moons/experimentation/region/");
    }

    public static boolean extractMoon(ModContainer modContainer, MinecraftServer server, RegistryKey<World> dim, String dataPath) {
        Path moon = modContainer.findPath(dataPath).get();
        try (Stream<Path> moonPath = Files.list(moon)) {
            //server.getWorld(dim).close();
            String dimPath = LMServerUtil.getCurrentSaveFull(server) + "dimensions/"+ dim.getValue().getNamespace() + "/" + dim.getValue().getPath();
            FileUtils.deleteDirectory(new File(dimPath + "/"));
            FileUtils.forceMkdir(new File(dimPath + "/region/"));
            for (Path path : moonPath.toList()) {
                FileUtils.copyFile(path.toFile(), new File(dimPath + path.toString().substring(path.toString().indexOf("\\region\\"))));
            }
        } catch (IOException e) {
            LM.LOGGER.warn("Could not extract moon", e);
        }
        return true;
    }

}
