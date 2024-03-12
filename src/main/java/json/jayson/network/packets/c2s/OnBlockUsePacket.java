package json.jayson.network.packets.c2s;

import json.jayson.common.objects.block.IBlockHoldUse;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class OnBlockUsePacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld world = player.getServerWorld();
        BlockPos uuid = buf.readBlockPos();
        if(uuid != null) {
            Block block = world.getBlockState(uuid).getBlock();
            if(block instanceof IBlockHoldUse blockOverlay) {
                blockOverlay.onBlockUse(world, uuid);
            }
        }
    }

}
