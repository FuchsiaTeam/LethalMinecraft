package json.jayson.common.item;


/* STILL WORKS, BUT YOU WILL HAVE TO LOAD THE MODEL YOURSELF */
@Deprecated
public interface IMultiModelItem {

   default String getModel() {
       return "";
   }

   default String getModelVariant() {
       return "inventory";
   }

}
