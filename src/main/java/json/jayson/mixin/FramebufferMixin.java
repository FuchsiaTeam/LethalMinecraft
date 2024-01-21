package json.jayson.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import json.jayson.client.LMClient;
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
    @Unique private float scaleMultiplier;

    @Shadow
    public abstract int getColorAttachment();

    @Inject(method = "initFbo", at = @At("HEAD"))
    private void onInitFbo(int width, int height, boolean getError, CallbackInfo ci) {
        scaleMultiplier = (float) width / MinecraftClient.getInstance().getWindow().getWidth();
    }


    @Redirect(method = "*", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/platform/GlStateManager;_texParameter(III)V"))
    private void onSetTexFilter(int target, int pname, int param) {
        if (pname == GL11.GL_TEXTURE_MIN_FILTER) {
            GlStateManager._texParameter(target, pname,
                    GL11.GL_NEAREST);
        } else if (pname == GL11.GL_TEXTURE_MAG_FILTER) {
            GlStateManager._texParameter(target, pname,
                    GL11.GL_NEAREST_MIPMAP_NEAREST);
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
            GlStateManager._texImage2D(target, 0, internalFormat, width, height, border, format, type, pixels);
    }

}
