package json.jayson.common.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IAmScrapLoot {


    /* boolean to continue event */
    default boolean onItemDrop(PlayerEntity player, ItemEntity item) { return true; }
}
