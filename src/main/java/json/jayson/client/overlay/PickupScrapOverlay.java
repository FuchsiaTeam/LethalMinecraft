package json.jayson.client.overlay;

import json.jayson.client.LMClient;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.util.LMRenderUtil;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.util.Identifier;

public class PickupScrapOverlay implements HudRenderCallback {

    public static boolean SHOW = false;
    public static String SCRAP = "Scrap";

    private final Identifier OVERLAY_TEX = LMUtil.LMIdentifier.overlay("pickup_overlay.png");


    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            if (SHOW) {
                String text = "Grab: " + SCRAP + " [" + LMClient.pickUpScrapKeyBind.getBoundKeyLocalizedText().getString() + "]";
                int x = (client.getWindow().getScaledWidth() / 2) - (client.textRenderer.getWidth(text) / 2);
                int y = client.getWindow().getScaledHeight() / 2 + 10;
                drawContext.drawText(client.textRenderer, text, x, y, 0xFFFFFFFF, true);

                if(ClientEndTickListener.pickupCharge > 0) {
                    //LMRenderUtil.setRenderTexture(OVERLAY_TEX);
                    //drawContext.drawTexture(OVERLAY_TEX, 5, 5, 0, 0,128, 16);
                    //drawContext.drawTexture(OVERLAY_TEX, x, y + 15, 32, 0,128, 2);
                    drawContext.drawTexture(OVERLAY_TEX, x ,y + 15,0, 0,  (int)((float)ClientEndTickListener.pickupCharge / (float)ClientEndTickListener.currentMaxPickupCharge * client.textRenderer.getWidth(text)), 8);
                    drawContext.drawText(client.textRenderer, (int)((float)ClientEndTickListener.pickupCharge / (float)ClientEndTickListener.currentMaxPickupCharge * 100.0f) + "%", x, y + 16, 0xFFFFFFFF, true);
                }
            }
        }
    }
}
