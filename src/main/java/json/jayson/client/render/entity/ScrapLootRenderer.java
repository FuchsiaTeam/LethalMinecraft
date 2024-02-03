package json.jayson.client.render.entity;

import json.jayson.common.objects.entity.ScrapLootEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
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
        }
    }

    @Override
    public Identifier getTexture(ScrapLootEntity entity) {
        return null;
    }
}
