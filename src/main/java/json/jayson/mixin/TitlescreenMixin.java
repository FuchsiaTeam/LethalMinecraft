package json.jayson.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitlescreenMixin {

    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo callbackInfo) {
       System.out.println("UwU");
    }

}
