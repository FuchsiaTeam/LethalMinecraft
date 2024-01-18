package json.jayson.common.item;

import net.minecraft.util.Identifier;

public interface IMultiModelItem {

   default String getModel() {
       return "";
   }

   default String getModelVariant() {
       return "inventory";
   }

}
