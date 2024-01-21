package json.jayson.util;

import json.jayson.LM;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.World;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

public class LMUtil {

    public static Random RANDOM = new Random();

    public static Identifier createLocation(String name) {
        return new Identifier(LM.ID, name);
    }

    public static String getCurrentSaveName() {
       return getCurrentSaveName(MinecraftClient.getInstance().getServer());
    }

    public static String getCurrentSaveFull() {
        return getCurrentSaveFull(MinecraftClient.getInstance().getServer());
    }

    public static String getCurrentSaveName(IntegratedServer server) {
        return server.getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
    }

    public static String getCurrentSaveFull(IntegratedServer server) {
        return MinecraftClient.getInstance().runDirectory + "/saves/" + getCurrentSaveName(server) + "/";
    }

    public static String getCurrentSaveName(MinecraftServer server) {
        return server.getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString();
    }

    public static String getCurrentSaveFull(MinecraftServer server) {
        return MinecraftClient.getInstance().runDirectory + "/saves/" + getCurrentSaveName(server) + "/";
    }

    public static boolean extractMoon(ModContainer modContainer, MinecraftServer server, RegistryKey<World> dim, String dataPath) {
        Path moon = modContainer.findPath(dataPath).get();
        try (Stream<Path> moonPath = Files.list(moon)) {
            //server.getWorld(dim).close();
            FileUtils.deleteDirectory(new File(getCurrentSaveFull(server) + "dimensions/"+ dim.getValue().getNamespace() + "/" + dim.getValue().getPath() + "/"));
            FileUtils.forceMkdir(new File(getCurrentSaveFull(server) + "dimensions/"+ dim.getValue().getNamespace() + "/" + dim.getValue().getPath() + "/region/"));
            for (Path path : moonPath.toList()) {
                FileUtils.copyFile(path.toFile(), new File(getCurrentSaveFull(server) + "dimensions/"+ dim.getValue().getNamespace() + "/" + dim.getValue().getPath() + path.toString().substring(path.toString().indexOf("\\region\\"))));
            }
        } catch (IOException e) {
            LM.LOGGER.warn("Could not extract moon", e);
        }
        return true;
    }

}
