package json.jayson.network.packets.c2s;

import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.network.LMNetwork;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

public class RequestScanPacket {


    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        BlockPos pos = buf.readBlockPos();
        int value = 0;
        if(pos != null) {
            for (ScrapLootEntity scrapLootEntity : player.getWorld().getEntitiesByClass(ScrapLootEntity.class, Box.of(pos.toCenterPos(), 15, 2, 15), entity -> entity.hasItem())) {
                value += scrapLootEntity.getScrapValue();
            }

        }
        LMNetwork.Server.sendScanLootPacket(player, value);
    }
}
