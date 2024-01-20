package json.jayson.client;

import json.jayson.ResolutionControl.ResolutionHandler;
import json.jayson.client.hud.PickupScrapOverlay;
import json.jayson.client.model.ItemModelRegistry;
import json.jayson.client.render.blockentity.ScrapLootBlockEntityRenderer;
import json.jayson.client.render.entity.CoilHeadRenderer;
import json.jayson.client.render.entity.ScrapLootRenderer;
import json.jayson.init.LMBlockEntities;
import json.jayson.init.LMBlocks;
import json.jayson.init.LMEntities;
import json.jayson.init.LMItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

@Environment(EnvType.CLIENT)
public class LMClient implements ClientModInitializer {

    public static ItemModelRegistry ITEM_MODELS = new ItemModelRegistry();
    public static ResolutionHandler RESOLUTION_HANDLER = new ResolutionHandler();

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(LMBlockEntities.SCRAP_LOOT, ScrapLootBlockEntityRenderer::new);

        EntityRendererRegistry.register(LMEntities.COIL_HEAD, CoilHeadRenderer::new);
        EntityRendererRegistry.register(LMEntities.SCRAP_LOOT, ScrapLootRenderer::new);
        registerModels();
        setBlockRenderMaps();

        /* EVENTS */
        HudRenderCallback.EVENT.register(new PickupScrapOverlay());
    }

    public void setBlockRenderMaps() {
        BlockRenderLayerMap.INSTANCE.putBlock(LMBlocks.STEEL_GRATE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(LMBlocks.REINFORCED_GLASS, RenderLayer.getCutout());
    }

    public void registerModels() {
        ITEM_MODELS.addModel(LMItems.GOLDEN_YIELD_SIGN);
        ITEM_MODELS.addModel(LMItems.AXOLOTL_PLUSHIE, "3d/axolotl/pink");
        ITEM_MODELS.addModel(LMItems.WHITE_AXOLOTL_PLUSHIE, "3d/axolotl/white");
        ITEM_MODELS.addModel(LMItems.YELLOW_AXOLOTL_PLUSHIE, "3d/axolotl/yellow");
        ITEM_MODELS.addModel(LMItems.BROWN_AXOLOTL_PLUSHIE, "3d/axolotl/brown");
        ITEM_MODELS.addModel(LMItems.BLUE_AXOLOTL_PLUSHIE, "3d/axolotl/blue");
        ITEM_MODELS.addModel(LMItems.DEFAULT_FLASHLIGHT, "3d/flashlight/default/lime");
    }
}