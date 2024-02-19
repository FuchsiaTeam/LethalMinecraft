package json.jayson.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.util.LMIdentifier;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.client.particle.v1.ParticleRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.GlBlendState;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ExperienceOrbEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GLUtil;

import java.util.Random;

public class ScrapLootRenderer extends EntityRenderer<ScrapLootEntity> {
    public ScrapLootRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    private static final Identifier SCAN_CIRCLE_TEX = LMIdentifier.overlay("scan_circle.png");
    private static final RenderLayer LAYER;

    @Override
    public void render(ScrapLootEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if(entity.hasItem()) {
            matrices.push();
            matrices.translate(0, -0.4, 0);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
            MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(entity.getItem().getItem()), ModelTransformationMode.HEAD, light, 15, matrices, vertexConsumers, entity.getWorld(), 0);
            matrices.pop();
            renderName(entity, matrices, vertexConsumers, light, tickDelta);
            if(entity.renderText && 120 > entity.renderTextTime) {
                renderName(entity, matrices, vertexConsumers, light, tickDelta);
                entity.renderTextTime += tickDelta;
            }
            if(entity.renderTextTime >= 120 ) {
                entity.renderTextTime = 0;
                entity.renderText = false;
                scaleCircles = 0;
            }

        }
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    protected void renderName(ScrapLootEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float tickdelta) {
        double d = this.dispatcher.getSquaredDistanceToCamera(entity);
        if (!(d > 4096.0)) {
            matrices.push();
            matrices.translate(0.0F, 1.0f, 0.0F);
            matrices.multiply(this.dispatcher.getRotation());
            matrices.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            int j = 0xCC297D23;
            TextRenderer textRenderer = this.getTextRenderer();
            float h = 50 + (float)(-textRenderer.getWidth(entity.getItem().getName()) / 2);
            textRenderer.draw(entity.getItem().getName(), h, 0, 0xFF5CFF87, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.POLYGON_OFFSET, j, light);
            matrices.pop();


            matrices.push();
            matrices.translate(0.0F, 0.74f, 0.0F);
            matrices.multiply(this.dispatcher.getRotation());
            matrices.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f m4f = matrices.peek().getPositionMatrix();
            h = 35 + (float)(-textRenderer.getWidth("Value:  " + entity.getScrapValue()) / 2);
            //textRenderer.draw("Value:  " + entity.getScrapValue(), h, 0, 0xFF5CFF87, false, m4f, vertexConsumers, TextRenderer.TextLayerType.SEE_THROUGH, j, light);
            textRenderer.draw("Value:  " + entity.getScrapValue(), h, 0, 0xFF5CFF87, false, m4f, vertexConsumers, TextRenderer.TextLayerType.POLYGON_OFFSET, j, light);
            matrices.pop();
            renderScanCircles(matrices, entity, vertexConsumers, light, tickdelta);
        }
    }

    float scaleCircles = 0;
    protected void renderScanCircles(MatrixStack matrixStack, ScrapLootEntity scrapLootEntity, VertexConsumerProvider vertexConsumerProvider, int light, float tickDelta) {
        if(scrapLootEntity.maxCircleSize > scaleCircles) {
            scaleCircles += tickDelta * 0.15f;
        }
        matrixStack.push();
        //RenderSystem.disableDepthTest();
        matrixStack.translate(0F, 0.25F, 0f);
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
        matrixStack.scale(scaleCircles, scaleCircles, scaleCircles);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        vertex(vertexConsumer, matrix4f, matrix3f, -1F, -1F, 255, 255, 255, 0, 1, light);
        vertex(vertexConsumer, matrix4f, matrix3f, 1, -1F, 255, 255, 255, 1, 1, light);
        vertex(vertexConsumer, matrix4f, matrix3f, 1, 1F, 255, 255,255, 1, 0, light);
        vertex(vertexConsumer, matrix4f, matrix3f, -1F, 1F, 255, 255,255, 0, 0, light);
        matrixStack.pop();
    }

    @Override
    protected int getBlockLight(ScrapLootEntity entity, BlockPos pos) {
        if(entity.renderText) {
           // return 4;
        }
        return super.getBlockLight(entity, pos);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, int red, int green, int blue, float u, float v, int light) {
        vertexConsumer.vertex(positionMatrix, x, y, 0.0F).color(red, green, blue, 200).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(normalMatrix, 0.0F, 1.0F, 0.0F).next();
    }

    @Override
    public Identifier getTexture(ScrapLootEntity entity) {
        return SCAN_CIRCLE_TEX;
    }

    static {
        // LAYER = RenderLayer.getItemEntityTranslucentCull(SCAN_CIRCLE_TEX);
        LAYER = RenderLayer.getTextSeeThrough(SCAN_CIRCLE_TEX);
    }
}
