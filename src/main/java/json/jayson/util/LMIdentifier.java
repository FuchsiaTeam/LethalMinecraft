package json.jayson.util;

import json.jayson.LM;
import net.minecraft.util.Identifier;

public class LMIdentifier {
    public static Identifier texture(String name) {
        return new Identifier(LM.ID, "textures/" + name);
    }

    public static Identifier data(String name) {
        return new Identifier(LM.ID, "data/" + name);
    }


    public static Identifier overlay(String name) {
        return new Identifier(LM.ID, "textures/overlay/" + name);
    }

    public static Identifier entityTexture(String name) {
        return new Identifier(LM.ID, "textures/entity/" + name);
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