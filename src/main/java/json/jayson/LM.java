package json.jayson;

import json.jayson.common.entity.coil_head.CoilHeadEntity;
import json.jayson.event.listener.PlayerDropItemEventListener;
import json.jayson.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LM implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("lm");
	public static final String ID = "lm";


	@Override
	public void onInitialize() {
		/* INITS FOR STATICS */
		LMTabs.register();
		LMItems.register();
		LMSounds.register();
		LMBlocks.register();
		LMBlockEntities.register();


		/* EVENTS */
		PlayerDropItemEventListener.register();

		/* ENTITY ATTRIBUTES */
		FabricDefaultAttributeRegistry.register(LMEntities.COIL_HEAD, CoilHeadEntity.attributes());
	}
}