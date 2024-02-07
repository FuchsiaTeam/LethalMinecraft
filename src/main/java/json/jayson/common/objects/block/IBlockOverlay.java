package json.jayson.common.objects.block;

import net.minecraft.client.gui.DrawContext;

public interface IBlockOverlay {

    void render(DrawContext drawContext, float tickDelta);

}
