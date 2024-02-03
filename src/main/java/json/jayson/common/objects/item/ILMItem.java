package json.jayson.common.objects.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface ILMItem {


    /* boolean to continue event */
    default boolean onItemDrop(PlayerEntity player, ItemEntity item) { return true; }

}
