package json.jayson.client.overlay;

import json.jayson.common.init.LMDataAttachments;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.common.objects.item.IAmScrapLoot;
import json.jayson.util.LMIdentifier;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DefaultOverhaul implements HudRenderCallback {

    private final Identifier PLAYER_TEX = LMIdentifier.overlay("game_hud/player.png");
    private final Identifier STAMINA_BAR_TEX = LMIdentifier.overlay("game_hud/stamina_bar.png");
    private final Identifier SCAN_BACKGROUND_TEX = LMIdentifier.overlay("game_hud/full_scan.png");
    private final Identifier TIME_BORDER_TEX = LMIdentifier.overlay("game_hud/time_border.png");

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
            drawScan(drawContext, x, y, client);

            /* TIME */
            drawTime(drawContext, x, y, client);
        }
    }

    public void drawTime(DrawContext drawContext, int x, int y, MinecraftClient client) {
        drawContext.drawTexture(TIME_BORDER_TEX, x - 32, 12, 0, 0, 64, 32,64, 32);
    }

    public void drawScan(DrawContext drawContext, int x, int y, MinecraftClient client) {
        if(ClientEndTickListener.scannedLootValue != 0) {
            drawContext.drawText(client.textRenderer, String.valueOf(ClientEndTickListener.scannedLootValue), x * 2 - 50 - (client.textRenderer.getWidth(String.valueOf(ClientEndTickListener.scannedLootValue))) , y + 30, 0xFF5CFF87, true);
            drawContext.drawText(client.textRenderer, Text.translatable("overlay.lm.total"), x * 2 - 155 + client.textRenderer.getWidth(Text.translatable("overlay.lm.total")) , y + 27, 0xFF5CFF87, true);
            drawContext.drawTexture(SCAN_BACKGROUND_TEX, x * 2 - 130, y + 25, (int) (64 * 2.5f), (int) (11 * 1.75f), 0, 0, 64, 11,64, 11);
        }
    }

    public void drawDropText(DrawContext drawContext, int x, int y, MinecraftClient client) {
        if(client.player.getMainHandStack().getItem() instanceof IAmScrapLoot) {
            //String dropText = "Drop " + client.player.getMainHandStack().getName().getString() + "  :  [" + client.options.dropKey.getBoundKeyLocalizedText().getString() + "]";
            String dropText = Text.translatable("overlay.lm.drop_text").getString().replaceAll("@s", client.player.getMainHandStack().getName().getString()).replaceAll("@k", client.options.dropKey.getBoundKeyLocalizedText().getString());
            int dropTextLen = MinecraftClient.getInstance().textRenderer.getWidth(dropText);
            drawContext.drawText(client.textRenderer, dropText, x * 2 - 12 - dropTextLen, y - 100, 0xFFFFFFFF, true);
        }
    }

    public void  drawHealthPlayer(DrawContext drawContext, int x, int y, MinecraftClient client) {
        drawContext.drawTexture(PLAYER_TEX, 45, y - 100, 0, 0, 0, 32, 32, 32,32);
        int healthPerc = (int)(client.player.getHealth() / client.player.getMaxHealth() * 100);
        String health = String.valueOf(healthPerc);
        int healthColor = 0xFF297D23;
        if(healthPerc == 100) {
            drawContext.drawText(client.textRenderer, health.substring(1), 40, y - 78, healthColor, true);
            drawContext.drawText(client.textRenderer, "1", 33, y - 78, healthColor, true);
        } else {
            drawContext.drawText(client.textRenderer, health, 65, y - 87, healthColor, true);
        }
    }


    public void drawStaminaBar(DrawContext drawContext, int x, int y, MinecraftClient client) {
        /* THIS IS VERY HACKY AND NEEDS TOTALLY A REWRITE! */
        int staminaTemp = client.player.getAttachedOrElse(LMDataAttachments.STAMINA, 100);

        int staminaPerc = (int)(staminaTemp / 100.0 * 100.0f);
        int staminaU = 0;
        boolean staminaUpper = true;

        if(99>= staminaPerc) {
            staminaU = 100 - staminaPerc;
        }

        if(57 >= staminaPerc) {
            staminaU = 0;
            staminaUpper = false;
        }
            /*staminaTemp = 127;
            int stamina = (int)(staminaTemp / 100.0 * 39.0f);
            int staminaU = 59 - stamina;*/
        drawContext.drawTexture(STAMINA_BAR_TEX, 23, y - 102 + staminaU, 0, staminaU, staminaUpper ? 64 : (int)(staminaTemp / 100.0 * 64.0f) + 4, 64, 64,128);
        drawContext.drawTexture(STAMINA_BAR_TEX, 23, y - 102 + 5, 0, 5, (int)(staminaTemp / 100.0 * 32.0f), 64, 64,128);
        drawContext.drawText(client.textRenderer, "0  lb", 80, y - 60, 0xFFA3691D, true);
    }

}
