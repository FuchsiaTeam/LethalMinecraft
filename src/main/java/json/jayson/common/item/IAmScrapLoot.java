package json.jayson.common.item;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface IAmScrapLoot {


    default void onItemDrop(PlayerEntity player, ItemEntity item) {}
}
