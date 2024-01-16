package json.jayson.event.listener;

import json.jayson.common.item.IAmScrapLoot;
import json.jayson.event.custom.PlayerDropItemCallback;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PlayerDropItemEventListener {

    public static void register() {
        PlayerDropItemCallback.EVENT.register((player, item) -> {
            if(item.getStack().getItem() instanceof IAmScrapLoot scrapLoot) {
                ItemStack stack = item.getStack();
                scrapLoot.onItemDrop(player, item);
                return new ItemEntity(player.getWorld(), player.getPos().x, player.getPos().y, player.getPos().z, new ItemStack(Items.DIAMOND));
            }
            return item;
        });
    }

}
