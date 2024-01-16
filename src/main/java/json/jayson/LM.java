package json.jayson;

import json.jayson.ResolutionControl.ResolutionHandler;
import json.jayson.init.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LM implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("lm");
	public static final String ID = "lm";

	@Override
	public void onInitialize() {
		new ResolutionHandler();
		LMTabs.register();
		LMItems.register();
		LMSounds.registerSounds();
		LMBlocks.register();
		LMBlockEntities.register();

		UseItemCallback
	}
}