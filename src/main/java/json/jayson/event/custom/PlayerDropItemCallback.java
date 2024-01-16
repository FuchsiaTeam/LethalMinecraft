package json.jayson.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface PlayerDropItemCallback {

    /* RETURNING NULL WILL CANCEL THE EVENT */
    Event<PlayerDropItemCallback> EVENT = EventFactory.createArrayBacked(PlayerDropItemCallback.class,
            (listeners) -> (player, item) -> {
                for (PlayerDropItemCallback listener : listeners) {
                    ItemEntity result = listener.drop(player, item);
                    if(result != null) {
                        return result;
                    }
                }
                return item;
            });

    ItemEntity drop(PlayerEntity player, ItemEntity item);

}
