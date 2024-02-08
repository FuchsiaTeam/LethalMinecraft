package json.jayson.client;

import json.jayson.ResolutionControl.ResolutionHandler;
import json.jayson.client.overlay.BlockOverlay;
import json.jayson.client.overlay.DefaultOverhaul;
import json.jayson.client.overlay.PickupScrapOverlay;
import json.jayson.client.model.ItemModelRegistry;
import json.jayson.client.model.LMItemModelHandler;
import json.jayson.client.render.blockentity.ScrapLootBlockEntityRenderer;
import json.jayson.client.render.entity.CoilHeadRenderer;
import json.jayson.client.render.entity.ScrapLootRenderer;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.common.objects.event.listener.client.ClientPlayerMovementListener;
import json.jayson.common.init.LMBlockEntities;
import json.jayson.common.init.LMBlocks;
import json.jayson.common.init.LMEntities;
import json.jayson.network.LMNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class LMClient implements ClientModInitializer {

    public static ItemModelRegistry ITEM_MODELS = new ItemModelRegistry();
    public static ResolutionHandler RESOLUTION_HANDLER = new ResolutionHandler();

    public static KeyBinding pickUpScrapKeyBind;



    @Override
    public void onInitializeClient() {
        /* RENDER, MODELS */
        BlockEntityRendererFactories.register(LMBlockEntities.SCRAP_LOOT, ScrapLootBlockEntityRenderer::new);

        EntityRendererRegistry.register(LMEntities.COIL_HEAD, CoilHeadRenderer::new);
        EntityRendererRegistry.register(LMEntities.SCRAP_LOOT, ScrapLootRenderer::new);
        registerModels();
        setBlockRenderMaps();

        /* EVENTS */
        HudRenderCallback.EVENT.register(new PickupScrapOverlay());
        HudRenderCallback.EVENT.register(new DefaultOverhaul());
        HudRenderCallback.EVENT.register(new BlockOverlay());
        ClientPlayerMovementListener.register();
        ClientEndTickListener.register();


        /* KEY BINDS */
        pickUpScrapKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.lm.pick_up_scrap", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R,"keybind.category.lm"));

        /* NETWORK */
        LMNetwork.registerS2C();
    }

    public void setBlockRenderMaps() {
        BlockRenderLayerMap.INSTANCE.putBlock(LMBlocks.STEEL_GRATE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(LMBlocks.REINFORCED_GLASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(LMBlocks.LOOT_POSITION, RenderLayer.getCutout());
    }

    public void registerModels() {
        for (LMItemModelHandler.Data model : LMItemModelHandler.getModels()) {
            if(!model.threeD.isEmpty()) {
                ITEM_MODELS.addModel(model.item, model.threeD);
            }
        }


        /* MOVED TO LM#addItemModels */
        //ITEM_MODELS.addModel(LMItems.GOLDEN_YIELD_SIGN);
        //ITEM_MODELS.addModel(LMItems.AXOLOTL_PLUSHIE, "3d/axolotl/pink");
    }
}