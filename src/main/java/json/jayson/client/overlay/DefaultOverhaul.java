package json.jayson.client.overlay;

import json.jayson.common.objects.item.IAmScrapLoot;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;

public class DefaultOverhaul implements HudRenderCallback {


    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            if(client.player.getMainHandStack().getItem() instanceof IAmScrapLoot) {
                int x = (client.getWindow().getScaledWidth() / 2);
                int y = client.getWindow().getScaledHeight() / 2;

                /* DROP TEXT */
                String dropText = "Drop " + client.player.getMainHandStack().getName().getString() + "  :  [" + client.options.dropKey.getBoundKeyLocalizedText().getString() + "]";
                int dropTextLen = MinecraftClient.getInstance().textRenderer.getWidth(dropText);
                drawContext.drawText(client.textRenderer, dropText, x + 225 - dropTextLen, y - 100, 0xFFFFFFFF, true);
                /*          */

                /* HP; WEIGHT */

            }
        }
    }

}
