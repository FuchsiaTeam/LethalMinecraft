package json.jayson.mixin;

import json.jayson.ResolutionControl.ResolutionHandler;
import net.minecraft.client.util.Window;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
CODE USED FROM:
https://github.com/UltimateBoomer/Resolution-Control/tree/1.20.2?tab=License-2-ov-file
IF YOU ARE THE AUTHOR(S) OF THIS MOD, PLEASE GIVE US A PM, WE WILL REMOVE IT THEN! :)
 */
@Mixin(Window.class)
public abstract class WindowMixin {

	@Inject(at = @At("RETURN"), method = "getFramebufferWidth", cancellable = true)
	private void getFramebufferWidth(CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(scale(ci.getReturnValueI()));
	}

	@Inject(at = @At("RETURN"), method = "getFramebufferHeight", cancellable = true)
	private void getFramebufferHeight(CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(scale(ci.getReturnValueI()));
	}

	private int scale(int value) {
		double scaleFactor = ResolutionHandler.getInstance().getCurrentScaleFactor();
		return Math.max(MathHelper.ceil((double) value * scaleFactor), 1);
	}

	@Inject(at = @At("RETURN"), method = "getScaleFactor", cancellable = true)
	private void getScaleFactor(CallbackInfoReturnable<Double> ci) {
		ci.setReturnValue(ci.getReturnValueD() * ResolutionHandler.getInstance().getCurrentScaleFactor());
	}

	@Inject(at = @At("RETURN"), method = "onFramebufferSizeChanged")
	private void onFramebufferSizeChanged(CallbackInfo ci) {
		ResolutionHandler.getInstance().onResolutionChanged();
	}

	@Inject(at = @At("RETURN"), method = "updateFramebufferSize")
	private void onUpdateFramebufferSize(CallbackInfo ci) {
		ResolutionHandler.getInstance().onResolutionChanged();
	}

}
