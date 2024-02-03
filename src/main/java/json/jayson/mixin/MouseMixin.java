package json.jayson.mixin;


import json.jayson.common.objects.event.custom.MouseUpdateCallback;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow private double cursorDeltaX;

    @Shadow private double cursorDeltaY;

    @Inject(at = @At("TAIL"), method = "updateMouse")
    private void updateMouse(CallbackInfo callbackInfo) {
        MouseUpdateCallback.EVENT.invoker().updateMouse(this.cursorDeltaX, this.cursorDeltaY);
    }

}
