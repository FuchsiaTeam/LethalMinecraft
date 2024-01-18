package json.jayson;

import json.jayson.client.model.ItemModelRegistry;
import json.jayson.client.render.blockentity.ScrapLootBlockEntityRenderer;
import json.jayson.client.render.entity.CoilHeadRenderer;
import json.jayson.init.LMBlockEntities;
import json.jayson.init.LMEntities;
import json.jayson.init.LMItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class LMClient implements ClientModInitializer {

    public static ItemModelRegistry ITEM_MODELS = new ItemModelRegistry();

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(LMBlockEntities.SCRAP_LOOT, ScrapLootBlockEntityRenderer::new);

        EntityRendererRegistry.register(LMEntities.COIL_HEAD, CoilHeadRenderer::new);

        ITEM_MODELS.addModel(LMItems.GOLDEN_STOP_SIGN);
    }
}