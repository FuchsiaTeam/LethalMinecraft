package json.jayson.mixin;

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

    @ModifyVariable(method = "renderItem*", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel getIronStopSignModel(BakedModel model, ItemStack itemStack, ModelTransformationMode mode) {
        if(itemStack.getItem() instanceof IMultiModelItem multiModelItem && mode != ModelTransformationMode.GUI) {
            Identifier itemId = Registries.ITEM.getId(itemStack.getItem());
            return ((ItemRendererAccessor) this).lm$getModels().getModelManager().getModel(new ModelIdentifier(itemId.getNamespace(), multiModelItem.getModel().isEmpty() ? itemId.getPath() + "_model" : multiModelItem.getModel(), multiModelItem.getModelVariant()));
        }
        return model;
    }

}
