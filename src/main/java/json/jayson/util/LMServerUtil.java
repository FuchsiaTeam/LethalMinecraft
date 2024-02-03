package json.jayson.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.WorldSavePath;

public class LMServerUtil {

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

}
