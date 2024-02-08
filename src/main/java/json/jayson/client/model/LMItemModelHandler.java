package json.jayson.client.model;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class LMItemModelHandler {

    private static ArrayList<Data> MODELS = new ArrayList<>();

    /*
    * threeD = the 3D path to the model json
    * gui = the texture path to be shown in GUI
    * */
    public static void add(Item item, String threeD, String gui) {
        Data data = new Data();
        data.gui = gui;
        data.threeD = threeD;
        data.item = item;
        MODELS.add(data);
    }

    public static void add(Item item, String gui) {
        Data data = new Data();
        data.gui = gui;
        data.threeD = "";
        data.item = item;
        MODELS.add(data);
    }


    public static ArrayList<Data> getModels() {
        return MODELS;
    }

    /*
    * USED FOR EXTERNAL ADDONS TRYING TO USE THIS SYSTEM
    * IT DOESNT MAKE A GENERATOR ENTRY
    * */
    public static void addExternal(Item item, String threeD) {
        Data data = new Data();
        data.gui = "";
        data.threeD = threeD;
        data.item = item;
        MODELS.add(data);
    }

    public static class Data {
        public Item item;
        public String threeD;
        public String gui;
    }

}
