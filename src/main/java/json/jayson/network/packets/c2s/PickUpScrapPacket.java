package json.jayson.network.packets.c2s;


import java.util.UUID;

import json.jayson.common.init.LMDataAttachments;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.common.objects.item.IAmScrapLoot;
import json.jayson.network.LMNetwork;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;

public class PickUpScrapPacket {


    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld world = player.getServerWorld();
        UUID uuid = buf.readUuid();
        if(uuid != null) {
            Entity entity = world.getEntity(uuid);
            if(entity instanceof ScrapLootEntity scrapLootEntity) {
                IAmScrapLoot scrapLoot = (IAmScrapLoot) scrapLootEntity.getItem().getItem();
                float weight = scrapLootEntity.getWeight(scrapLootEntity.getItem().getNbt());
                float currentWeight = player.getAttachedOrElse(LMDataAttachments.WEIGHT, 0.0f);
                if(player.getInventory().insertStack(scrapLootEntity.getItem())) {
                    float newWeight = currentWeight + weight;
                    player.setAttached(LMDataAttachments.WEIGHT, newWeight);
                    world.playSound(player, player.getBlockPos(), scrapLoot.getPickUpSound(), SoundCategory.PLAYERS, 1,1);
                    scrapLootEntity.remove(Entity.RemovalReason.DISCARDED);
                    LMNetwork.Server.sendWeightUpdate(player, newWeight);
                }
            }
        }
    }

}
