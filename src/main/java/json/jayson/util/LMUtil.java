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

    public static Identifier createFabricLocation(String name) {
        return new Identifier("fabric", name);
    }

    public static Identifier createMinecraftLocation(String name) {
        return new Identifier(name);
    }


    public static class LMIdentifier {
        public static Identifier texture(String name) {
            return new Identifier(LM.ID, "textures/" + name);
        }

        public static Identifier network(String name) {
            return new Identifier(LM.ID, "network/" + name);
        }

        /* Includes textures/ path */
        public static Identifier itemTextureFull(String name) {
            return new Identifier(LM.ID, "textures/item/" + name);
        }

        public static Identifier itemTexture(String name) {
            return new Identifier(LM.ID, "item/" + name);
        }

        public static Identifier model(String name) {
            return new Identifier(LM.ID, "models/" + name);
        }

        public static Identifier model3DItem(String name) {
            return new Identifier(LM.ID, "models/item/3d/" + name);
        }

        public static Identifier blockstate(String name) {
            return new Identifier(LM.ID, "blockstates/" + name);
        }

        public static Identifier animation(String name) {
            return new Identifier(LM.ID, "animations/" + name);
        }

        public static Identifier geo(String name) {
            return new Identifier(LM.ID, "geo/" + name);
        }

        public static Identifier sound(String name) {
            return new Identifier(LM.ID, "sounds/" + name);
        }
    }

}
