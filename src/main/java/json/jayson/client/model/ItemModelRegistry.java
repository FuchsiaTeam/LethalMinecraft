package json.jayson.client.model;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemModelRegistry {

    private Map<Item, ModelData> models = new HashMap<>();

    public void addModel(Item item) {
        models.put(item, new ModelData());
    }

    public ModelData getModel(Item item) {
        return models.get(item);
    }

    public boolean hasModel(Item item) {
        return models.containsKey(item);
    }

    public Map<Item, ModelData> getModels() {
        return models;
    }

    public static class ModelData {
        private String variant = "inventory";
        private String path = "";

        public ModelData(String variant, String path) {
            this.variant = variant;
            this.path = path;
        }

        public ModelData() {
            this.variant = variant;
            this.path = path;
        }

        public String getPath() {
            return path;
        }

        public String getVariant() {
            return variant;
        }
    }
}
