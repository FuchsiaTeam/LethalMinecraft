package json.jayson.datagen;

import json.jayson.datagen.assets.LMModelProvider;
import json.jayson.datagen.data.LMBlockTagProvider;
import json.jayson.datagen.data.LMEntityTagProvider;
import json.jayson.datagen.data.LMItemTagProvider;
import json.jayson.datagen.data.LMLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class LMDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
			FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
			pack.addProvider(LMModelProvider::new);
			pack.addProvider(LMBlockTagProvider::new);
			pack.addProvider(LMEntityTagProvider::new);
			pack.addProvider(LMItemTagProvider::new);
			pack.addProvider(LMLootTableProvider::new);
	}

}
