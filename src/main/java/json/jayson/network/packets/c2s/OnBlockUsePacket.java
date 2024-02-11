package json.jayson.network.packets.c2s;

import json.jayson.common.objects.block.IBlockHoldUse;
import json.jayson.common.objects.block.IBlockOverlay;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.common.objects.item.IAmScrapLoot;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class OnBlockUsePacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld world = player.getServerWorld();
        BlockPos uuid = buf.readBlockPos();
        if(uuid != null) {
            Block block = player.getWorld().getBlockState(uuid).getBlock();
            if(block instanceof IBlockHoldUse blockOverlay) {
                blockOverlay.onBlockUse(player.getWorld(), uuid);
            }
        }
    }

}
