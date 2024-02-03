package json.jayson.common.objects.event.listener;

import json.jayson.client.hud.PickupScrapOverlay;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.common.objects.event.custom.ClientPlayerMovementCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

public class ClientPlayerMovementListener {

    public static void register() {
        ClientPlayerMovementCallback.EVENT.register((player -> {
            MinecraftClient client = MinecraftClient.getInstance();
            HitResult hit = client.crosshairTarget;
            if (hit.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) hit;
                Entity entity = entityHit.getEntity();
                if(entity instanceof ScrapLootEntity scrapLootEntity) {
                    if(scrapLootEntity.hasItem()) {
                        PickupScrapOverlay.SHOW = true;
                        PickupScrapOverlay.SCRAP = Text.translatable(scrapLootEntity.getItem().getTranslationKey()).getString();
                    }
                } else {
                    PickupScrapOverlay.SHOW = false;
                }
            } else {
                PickupScrapOverlay.SHOW = false;
            }
        }));
    }

}
