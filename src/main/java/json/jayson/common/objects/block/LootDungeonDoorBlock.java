package json.jayson.common.objects.block;

import json.jayson.client.LMClient;
import json.jayson.client.overlay.PickupScrapOverlay;
import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LootDungeonDoorBlock extends DoorBlock implements IBlockOverlay, IBlockHoldUse {

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

        if(ClientEndTickListener.blockUseCharge > 0) {
            drawContext.drawTexture(PickupScrapOverlay.OVERLAY_TEX, x - (MinecraftClient.getInstance().textRenderer.getWidth(text) / 2) ,y + 25,0, 0,  (int)((float)ClientEndTickListener.blockUseCharge / (float)ClientEndTickListener.maxBlockUseCharge * MinecraftClient.getInstance().textRenderer.getWidth(text)), 8);
            drawContext.drawText(MinecraftClient.getInstance().textRenderer, (int)((float)ClientEndTickListener.blockUseCharge / (float)ClientEndTickListener.maxBlockUseCharge * 100.0f) + "%", x, y + 26, 0xFFFFFFFF, true);
        }

    }

    @Override
    public int getBlockUseTime() {
        switch (doorType) {
            case ENTRANCE -> {
                return 25;
            }

            case EXIT ->  {
                return 45;
            }
        }
        return 16;
    }

    @Override
    public void onBlockUse(World world, BlockPos pos) {
        switch (doorType) {
            case ENTRANCE -> {
            }

            case EXIT ->  {
            }

            case DEFAULT -> {
                setOpen(null, world, world.getBlockState(pos), pos, !isOpen(world.getBlockState(pos)));
            }
        }
    }

    public static enum DoorType {
        DEFAULT,
        ENTRANCE,
        EXIT
    }
}
