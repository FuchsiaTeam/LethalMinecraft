package json.jayson.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import json.jayson.LMClient;
import json.jayson.ResolutionControl.ResolutionHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL45;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.IntBuffer;


@Mixin(Framebuffer.class)
public abstract class FramebufferMixin {
    @Unique
    private boolean isMipmapped;
    @Unique private float scaleMultiplier;

    @Shadow
    public abstract int getColorAttachment();

    @Inject(method = "initFbo", at = @At("HEAD"))
    private void onInitFbo(int width, int height, boolean getError, CallbackInfo ci) {
        scaleMultiplier = (float) width / MinecraftClient.getInstance().getWindow().getWidth();
        isMipmapped = false;
    }


    @Redirect(method = "*", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/platform/GlStateManager;_texParameter(III)V"))
    private void onSetTexFilter(int target, int pname, int param) {
        if (pname == GL11.GL_TEXTURE_MIN_FILTER) {
            GlStateManager._texParameter(target, pname,
                    LMClient.RESOLUTION_HANDLER.getUpscaleAlgorithm().getId(isMipmapped));
        } else if (pname == GL11.GL_TEXTURE_MAG_FILTER) {
            GlStateManager._texParameter(target, pname,
                    LMClient.RESOLUTION_HANDLER.getDownscaleAlgorithm().getId(false));
        } else if (pname == GL11.GL_TEXTURE_WRAP_S || pname == GL11.GL_TEXTURE_WRAP_T) {
            GlStateManager._texParameter(target, pname, GL12.GL_CLAMP_TO_EDGE);
        } else {
            GlStateManager._texParameter(target, pname, param);
        }
    }

    @Redirect(method = "initFbo", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/platform/GlStateManager;_texImage2D(IIIIIIIILjava/nio/IntBuffer;)V"))
    private void onTexImage(int target, int level, int internalFormat, int width, int height, int border, int format,
                            int type, IntBuffer pixels) {
        if (isMipmapped) {
            int mipmapLevel = MathHelper.ceil(Math.log(scaleMultiplier) / Math.log(2));
            for (int i = 0; i < mipmapLevel; i++) {
                GlStateManager._texImage2D(target, i, internalFormat,
                        width << i, height << i,
                        border, format, type, pixels);
            }
        } else {
            GlStateManager._texImage2D(target, 0, internalFormat, width, height, border, format, type, pixels);
        }

    }

    @Inject(method = "drawInternal", at = @At("HEAD"))
    private void onDraw(int width, int height, boolean bl, CallbackInfo ci) {
        if (isMipmapped) {
            GlStateManager._bindTexture(this.getColorAttachment());
            GL45.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        }
    }
}
