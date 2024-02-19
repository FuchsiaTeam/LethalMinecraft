package json.jayson.network.packets.s2c;

import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

@Deprecated
public class ScanLootPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        ClientEndTickListener.scannedLootValue = packetByteBuf.readInt();
    }
}
