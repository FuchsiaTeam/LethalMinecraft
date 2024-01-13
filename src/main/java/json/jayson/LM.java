package json.jayson;

import json.jayson.ResolutionControl.ResolutionHandler;
import json.jayson.init.LMItems;
import json.jayson.init.LMTabs;
import net.fabricmc.api.ModInitializer;

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
	}
}