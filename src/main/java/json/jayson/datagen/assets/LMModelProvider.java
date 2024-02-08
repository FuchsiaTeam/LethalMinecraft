package json.jayson.datagen.assets;

import com.google.gson.JsonElement;
import json.jayson.client.model.LMItemModelHandler;
import json.jayson.common.init.LMItems;
import json.jayson.util.LMIdentifier;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LMModelProvider extends FabricModelProvider {
    public LMModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }


    protected ItemModelGenerator modelGenerator;
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        modelGenerator = itemModelGenerator;
        //addTexturedItem(LMItems.AXOLOTL_PLUSHIE, "axolotl/pink_plushie");

        for (LMItemModelHandler.Data model : LMItemModelHandler.getModels()) {
            addTexturedItem(model.item, model.gui);
        }

    }


    public final void addTexturedItem(Item item, String texture) {
        Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(LMIdentifier.itemTexture(texture)), modelGenerator.writer);
    }
}
