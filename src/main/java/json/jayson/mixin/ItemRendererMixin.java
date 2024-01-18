package json.jayson.mixin;

import json.jayson.LMClient;
import json.jayson.client.model.ItemModelRegistry;
import json.jayson.common.item.IMultiModelItem;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel renderItem(BakedModel model, ItemStack itemStack, ModelTransformationMode mode) {
        if(itemStack.getItem() instanceof IMultiModelItem multiModelItem && mode != ModelTransformationMode.GUI) {
            Identifier itemId = Registries.ITEM.getId(itemStack.getItem());
            return ((ItemRendererAccessor) this).lm$getModels().getModelManager().getModel(new ModelIdentifier(itemId.getNamespace(), multiModelItem.getModel().isEmpty() ? "3d/" + itemId.getPath() : multiModelItem.getModel(), multiModelItem.getModelVariant()));
        }

        if(LMClient.ITEM_MODELS.hasModel(itemStack.getItem()) && mode != ModelTransformationMode.GUI) {
            Identifier itemId = Registries.ITEM.getId(itemStack.getItem());
            ItemModelRegistry.ModelData data = LMClient.ITEM_MODELS.getModel(itemStack.getItem());
            return ((ItemRendererAccessor) this).lm$getModels().getModelManager().getModel(new ModelIdentifier(itemId.getNamespace(), data.getPath().isEmpty() ? "3d/" + itemId.getPath() : data.getPath(), data.getVariant()));
        }
        return model;
    }

}
