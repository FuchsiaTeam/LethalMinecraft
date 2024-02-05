package json.jayson.common.objects.event.listener.client;

import json.jayson.client.LMClient;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.network.LMNetwork;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class ClientEndTickListener {

    public static int pickupCharge = 0;
    public static int maxPickupCharge = 13;
    public static int currentMaxPickupCharge = maxPickupCharge;
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(LMClient.pickUpScrapKeyBind.isPressed()) {
                ++pickupCharge;
                HitResult hit = client.crosshairTarget;
                if (hit.getType() == HitResult.Type.ENTITY) {
                    EntityHitResult entityHit = (EntityHitResult) hit;
                    Entity entity = entityHit.getEntity();
                    int adjustedMax = maxPickupCharge;
                    if (entity instanceof ScrapLootEntity scrapLootEntity) {
                        if (scrapLootEntity.hasItem()) {
                            adjustedMax = scrapLootEntity.getGrabTime();
                            currentMaxPickupCharge = adjustedMax;
                        }

                        if(pickupCharge >= adjustedMax) {
                            LMNetwork.Client.sendPickUpScrapPacket(scrapLootEntity.getUuid());
                            pickupCharge = 0;
                        }
                    }
                }
            } else {
                pickupCharge = 0;
            }
        });
    }

}
