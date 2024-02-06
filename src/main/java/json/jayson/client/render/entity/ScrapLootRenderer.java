package json.jayson.client.render.entity;

import json.jayson.common.objects.entity.ScrapLootEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class ScrapLootRenderer extends EntityRenderer<ScrapLootEntity> {
    public ScrapLootRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(ScrapLootEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        if(entity.hasItem()) {
            matrices.push();
            matrices.translate(0, -0.4, 0);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yaw));
            MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(entity.getItem().getItem()), ModelTransformationMode.HEAD, light, 15, matrices, vertexConsumers, entity.getWorld(), 0);
            matrices.pop();
            if(entity.renderText && 1.1 * tickDelta > entity.renderTextTime) {
                renderName(entity, matrices, vertexConsumers, light);
                entity.renderTextTime += tickDelta + 0.05f;
            }
            if(entity.renderTextTime >= 1 * tickDelta) {
                entity.renderTextTime = 0;
                entity.renderText = false;
            }

        }
    }

    protected void renderName(ScrapLootEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        double d = this.dispatcher.getSquaredDistanceToCamera(entity);
        if (!(d > 4096.0)) {
            matrices.push();
            matrices.translate(0.0F, 1.0f, 0.0F);
            matrices.multiply(this.dispatcher.getRotation());
            matrices.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25F);
            int j = (int)(g * 255.0F) << 24;
            TextRenderer textRenderer = this.getTextRenderer();
            float h = (float)(-textRenderer.getWidth(entity.getItem().getName()) / 2);
            textRenderer.draw(entity.getItem().getName(), h, 0, 0xFFFFFFFF, false, matrix4f, vertexConsumers, TextRenderer.TextLayerType.NORMAL, j, light);
            matrices.pop();
        }
    }


    @Override
    public Identifier getTexture(ScrapLootEntity entity) {
        return null;
    }
}
