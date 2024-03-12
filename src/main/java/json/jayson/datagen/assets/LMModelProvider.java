package json.jayson.datagen.assets;

import json.jayson.client.model.LMItemModelHandler;
import json.jayson.util.LMIdentifier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.item.Item;

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
