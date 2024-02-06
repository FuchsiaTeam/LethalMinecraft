package json.jayson.client.overlay;

import json.jayson.common.init.LMDataAttachments;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.common.objects.item.IAmScrapLoot;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Random;

public class DefaultOverhaul implements HudRenderCallback {

    private final Identifier PLAYER_TEX = LMUtil.LMIdentifier.overlay("game_hud/player.png");
    private final Identifier STAMINA_BAR_TEX = LMUtil.LMIdentifier.overlay("game_hud/stamina_bar.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int x = (client.getWindow().getScaledWidth() / 2);
            int y = client.getWindow().getScaledHeight() / 2;
            /* DROP TEXT */
            drawDropText(drawContext, x, y, client);
            /* HP; WEIGHT */
            drawHealthPlayer(drawContext, x, y, client);
            /* Now comes the stamina, I suck at this type of stuff so dont wonder lmao ~Jayson */
            drawStaminaBar(drawContext,x,y, client);


            /* LOOT */
            if(ClientEndTickListener.scannedLootValue != 0) {
                drawContext.drawText(client.textRenderer, String.valueOf(ClientEndTickListener.scannedLootValue), x , y, 0xFFFFFFFF, true);
            }
        }
    }


    public void drawDropText(DrawContext drawContext, int x, int y, MinecraftClient client) {
        if(client.player.getMainHandStack().getItem() instanceof IAmScrapLoot) {
            String dropText = "Drop " + client.player.getMainHandStack().getName().getString() + "  :  [" + client.options.dropKey.getBoundKeyLocalizedText().getString() + "]";
            int dropTextLen = MinecraftClient.getInstance().textRenderer.getWidth(dropText);
            drawContext.drawText(client.textRenderer, dropText, x * 2 - 12 - dropTextLen, y - 100, 0xFFFFFFFF, true);
        }
    }

    public void  drawHealthPlayer(DrawContext drawContext, int x, int y, MinecraftClient client) {
        drawContext.drawTexture(PLAYER_TEX, x - 190, y - 100, 0, 0, 0, 32, 32, 32,32);
        int healthPerc = (int)(client.player.getHealth() / client.player.getMaxHealth() * 100);
        String health = String.valueOf(healthPerc);
        int healthColor = 0xFF297D23;
        if(healthPerc == 100) {
            drawContext.drawText(client.textRenderer, health.substring(1), x - 195, y - 78, healthColor, true);
            drawContext.drawText(client.textRenderer, "1", x - 203, y - 78, healthColor, true);
        } else {
            drawContext.drawText(client.textRenderer, health, x - 210, y - 87, healthColor, true);
        }
    }


    public void drawStaminaBar(DrawContext drawContext, int x, int y, MinecraftClient client) {
        /* THIS IS VERY HACKY AND NEEDS TOTALLY A REWRITE! */
        int staminaTemp = client.player.getAttachedOrElse(LMDataAttachments.STAMINA, 100);
        int staminaPerc = (int)(staminaTemp / 100.0 * 100.0f);
        int staminaU = 0;
        boolean staminaUpper = true;

        if(99>= staminaPerc) {
            staminaU = 115 - staminaPerc;
        }

        if(67>= staminaPerc) {
            staminaU = 25;
            staminaUpper = false;
        }
            /*staminaTemp = 127;
            int stamina = (int)(staminaTemp / 100.0 * 39.0f);
            int staminaU = 59 - stamina;*/
        drawContext.drawTexture(STAMINA_BAR_TEX, x - 212, y - 110 + staminaU, 0, staminaU, staminaUpper ? 64 : (int)(staminaTemp / 100.0 * 64.0f), 64, 64,128);
        drawContext.drawTexture(STAMINA_BAR_TEX, x - 212, y - 110, 0, 0, (int)(staminaTemp / 100.0 * 32.0f), 64, 64,128);
        drawContext.drawText(client.textRenderer, "0  lb", x - 160, y - 60, 0xFFA3691D, true);
    }

}
