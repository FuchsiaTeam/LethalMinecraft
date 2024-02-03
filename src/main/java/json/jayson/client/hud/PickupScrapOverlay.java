package json.jayson.client.hud;

import json.jayson.client.LMClient;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class PickupScrapOverlay implements HudRenderCallback {

    public static boolean SHOW = false;
    public static String SCRAP = "Scrap";

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
                    drawContext.drawText(client.textRenderer, ClientEndTickListener.pickupCharge + "/" + ClientEndTickListener.maxPickupCharge, x, y + 16, 0xFFFFFFFF, true);
                }
            }
        }
    }
}
