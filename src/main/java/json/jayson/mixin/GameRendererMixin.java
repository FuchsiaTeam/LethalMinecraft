package json.jayson.mixin;

import json.jayson.client.LMClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
CODE USED FROM:
https://github.com/UltimateBoomer/Resolution-Control/tree/1.20.2?tab=License-2-ov-file
IF YOU ARE THE AUTHOR(S) OF THIS MOD, PLEASE GIVE US A PM, WE WILL REMOVE IT THEN! :)
 */
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(at = @At("HEAD"), method = "renderWorld")
    private void onRenderWorldBegin(CallbackInfo callbackInfo) {
        LMClient.RESOLUTION_HANDLER.setShouldScale(true);
    }

    @Inject(at = @At("RETURN"), method = "renderWorld")
    private void onRenderWorldEnd(CallbackInfo callbackInfo) {
        LMClient.RESOLUTION_HANDLER.setShouldScale(false);
    }

}
