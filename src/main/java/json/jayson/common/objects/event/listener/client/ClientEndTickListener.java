package json.jayson.common.objects.event.listener.client;

import json.jayson.client.LMClient;
import json.jayson.common.init.LMSounds;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.network.LMNetwork;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ClientEndTickListener {

    public static int pickupCharge = 0;
    public static int maxPickupCharge = 13;
    public static int currentMaxPickupCharge = maxPickupCharge;
    public static boolean canScan = true;
    public static int scannedLootValue = 0;
    public static int scanTick = 0;
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

            if(client.mouse.wasRightButtonClicked() && canScan) {
                canScan = false;
                for (ScrapLootEntity scrapLootEntity : client.player.getWorld().getEntitiesByClass(ScrapLootEntity.class, Box.of(client.player.getBlockPos().toCenterPos(), 15, 4, 15), entity -> entity.hasItem())) {
                    scrapLootEntity.renderText = true;
                    scannedLootValue += scrapLootEntity.getScrapValue();
                    client.player.playSound(LMSounds.SCAN, SoundCategory.MASTER, 15, 1);
                }
                //LMNetwork.Client.requestScan(client.player.getBlockPos());
            }

            if(!canScan) {
                ++scanTick;

                if(scanTick >= 50) {
                    scanTick = 0;
                    scannedLootValue = 0;
                    canScan = true;
                }
            }
        });
    }

}
