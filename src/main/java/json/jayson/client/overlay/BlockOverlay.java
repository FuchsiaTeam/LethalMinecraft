package json.jayson.client.overlay;

import json.jayson.common.objects.block.IBlockOverlay;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;

public class BlockOverlay implements HudRenderCallback {
    public static IBlockOverlay BLOCK;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if(BLOCK != null) {
            BLOCK.render(drawContext, tickDelta);
        }
    }
}
