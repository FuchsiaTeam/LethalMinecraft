package json.jayson.mixin;

import json.jayson.common.objects.event.custom.ClientPlayerMovementCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {

    @Inject(at = @At("TAIL"), method = "tickMovement")
    private void tickMovement(CallbackInfo callbackInfo) {
        ClientPlayerMovementCallback.EVENT.invoker().tickMovement(((ClientPlayerEntity) (Object) this));
    }

}
