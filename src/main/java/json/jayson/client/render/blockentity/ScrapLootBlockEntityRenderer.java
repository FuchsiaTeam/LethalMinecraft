package json.jayson.client.render.blockentity;

import json.jayson.common.blockentity.ScrapLootBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Deprecated
public class ScrapLootBlockEntityRenderer implements BlockEntityRenderer<ScrapLootBlockEntity> {

    public ScrapLootBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(ScrapLootBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if(entity.hasItem()) {
            matrices.push();
            MinecraftClient.getInstance().getItemRenderer().renderItem(new ItemStack(entity.getItem()), ModelTransformationMode.HEAD, light, overlay, matrices, vertexConsumers, entity.getWorld(), 0);
            matrices.pop();
        }
    }
}
