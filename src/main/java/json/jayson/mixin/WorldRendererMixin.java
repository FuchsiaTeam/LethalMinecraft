package json.jayson.mixin;

import json.jayson.client.LMClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
CODE USED FROM:
https://github.com/UltimateBoomer/Resolution-Control/tree/1.20.2?tab=License-2-ov-file
IF YOU ARE THE AUTHOR(S) OF THIS MOD, PLEASE GIVE US A PM, WE WILL REMOVE IT THEN! :)
 */
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    public Framebuffer entityOutlinesFramebuffer;

    @Inject(at = @At("RETURN"), method = "loadEntityOutlinePostProcessor")
    private void onLoadEntityOutlineShader(CallbackInfo ci) {
        LMClient.RESOLUTION_HANDLER.resizeMinecraftFramebuffers();
    }

    @Inject(at = @At("RETURN"), method = "onResized")
    private void onOnResized(CallbackInfo ci) {
        if (entityOutlinesFramebuffer == null) return;
        LMClient.RESOLUTION_HANDLER.resizeMinecraftFramebuffers();
    }
}