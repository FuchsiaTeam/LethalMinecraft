package json.jayson.network;

import json.jayson.network.packets.c2s.PickUpScrapPacket;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class LMNetwork {

    public static final Identifier PICKUP_SCRAP_ID = LMUtil.LMIdentifier.network("pickup_scrap");


    public static void registerS2C() {

    }

    public static void registerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(PICKUP_SCRAP_ID, PickUpScrapPacket::receive);
    }

    public static class Client {

        public static void sendPickUpScrapPacket(UUID scrapEntity) {
            PacketByteBuf byteBufs = PacketByteBufs.create();
            byteBufs.writeUuid(scrapEntity);
            ClientPlayNetworking.send(LMNetwork.PICKUP_SCRAP_ID, byteBufs);
        }

    }

    public static class Server {

    }

}
