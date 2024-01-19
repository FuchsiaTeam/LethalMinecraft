package json.jayson.util;

import json.jayson.LM;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.World;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Random;

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

    public static void extractMoon(MinecraftServer server, RegistryKey<World> dim, String dataPath) throws URISyntaxException, IOException {
        URI resource = LMUtil.class.getClass().getResource("").toURI();
        FileSystem fileSystem = FileSystems.newFileSystem(
                resource,
                Collections.<String, String>emptyMap()
        );


        final Path jarPath = fileSystem.getPath(dataPath);
        final Path target = new File(getCurrentSaveFull(server) + "dimensions/"+ dim.getValue().getNamespace() + "/" + dim.getValue().getPath()).toPath();
        Path parentDir = target.getParent();
        if (!Files.exists(parentDir)) Files.createDirectories(parentDir);
        Files.walkFileTree(jarPath, new SimpleFileVisitor<>() {

            private Path currentTarget;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                currentTarget = target.resolve(jarPath.relativize(dir).toString());
                Files.createDirectories(currentTarget);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(jarPath.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }

        });
    }

}
