package json.jayson.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class PickupScrapOverlay implements HudRenderCallback {

    public static boolean SHOW = false;
    public static String SCRAP = "Scrap";

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if(SHOW) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                String text = "Grab: " + SCRAP + " [F]";
                int x = (client.getWindow().getScaledWidth() / 2) - (client.textRenderer.getWidth(text) / 2);
                int y = client.getWindow().getScaledHeight() / 2 + 10;
                drawContext.drawText(client.textRenderer, text, x, y, 0xFFFFFFFF, true);
            }
        }
    }
}
