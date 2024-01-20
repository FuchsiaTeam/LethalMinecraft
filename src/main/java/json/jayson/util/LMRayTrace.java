package json.jayson.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
public class LMRayTrace {

    public static Entity getEntityInCrosshair(float partialTicks, double reachDistance) {
        MinecraftClient client = MinecraftClient.getInstance();
        HitResult hit = client.cameraEntity.raycast(reachDistance, partialTicks, false);
        if(hit != null) {
            if (hit.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) hit;
                return entityHit.getEntity();
            }
        }
        return null;
    }

}
