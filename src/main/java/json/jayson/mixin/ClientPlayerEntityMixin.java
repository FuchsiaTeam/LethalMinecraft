package json.jayson.mixin;

import json.jayson.client.hud.PickupScrapOverlay;
import json.jayson.common.entity.ScrapLootEntity;
import json.jayson.event.custom.ClientPlayerMovementCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    @Inject(at = @At("TAIL"), method = "tickMovement")
    private void tickMovement(CallbackInfo callbackInfo) {
        ClientPlayerMovementCallback.EVENT.invoker().tickMovement(((ClientPlayerEntity) (Object) this));
    }

}
