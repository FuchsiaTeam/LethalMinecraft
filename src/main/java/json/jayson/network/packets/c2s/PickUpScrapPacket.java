package json.jayson.network.packets.c2s;


import json.jayson.common.objects.entity.ScrapLootEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.UUID;

public class PickUpScrapPacket {


    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld world = player.getServerWorld();
        UUID uuid = buf.readUuid();
        if(uuid != null) {
            Entity entity = world.getEntity(uuid);
            if(entity instanceof ScrapLootEntity scrapLootEntity) {
                if(player.getInventory().insertStack(scrapLootEntity.getItem())) {
                    scrapLootEntity.remove(Entity.RemovalReason.DISCARDED);
                }
            }
        }
    }

}
