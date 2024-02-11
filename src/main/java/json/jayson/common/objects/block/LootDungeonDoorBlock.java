package json.jayson.common.objects.block;

import json.jayson.client.LMClient;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class LootDungeonDoorBlock extends DoorBlock implements IBlockOverlay {

    DoorType doorType;
    public LootDungeonDoorBlock(Settings settings, DoorType doorType) {
        super(BlockSetType.IRON, settings);
        this.doorType = doorType;
    }


    @Override
    public void render(DrawContext drawContext, float tickDelta) {
        int x = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
        int y = MinecraftClient.getInstance().getWindow().getScaledHeight() / 2;
        String text = "";
        switch (doorType) {
            case ENTRANCE: {
                text = Text.translatable("overlay.lm.enter_door").getString().replaceAll("@k", LMClient.pickUpScrapKeyBind.getBoundKeyLocalizedText().getString());
                break;
            }

            case EXIT: {
                text = Text.translatable("overlay.lm.exit_door").getString().replaceAll("@k", LMClient.pickUpScrapKeyBind.getBoundKeyLocalizedText().getString());
                break;
            }

            case DEFAULT: {
                text = Text.translatable("overlay.lm.use_door").getString().replaceAll("@k", LMClient.pickUpScrapKeyBind.getBoundKeyLocalizedText().getString());
                break;
            }
        }
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, text, x + 48 - MinecraftClient.getInstance().textRenderer.getWidth(text), y + 15, 0xFFFFFFFF, true);
    }

    public static enum DoorType {
        DEFAULT,
        ENTRANCE,
        EXIT
    }
}
