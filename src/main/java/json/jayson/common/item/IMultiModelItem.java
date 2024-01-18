package json.jayson.common.item;

public interface IMultiModelItem {

   default String getModel() {
       return "";
   }

   default String getModelVariant() {
       return "inventory";
   }

}
