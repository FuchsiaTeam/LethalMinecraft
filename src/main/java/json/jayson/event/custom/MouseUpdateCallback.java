package json.jayson.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface MouseUpdateCallback {

    Event<MouseUpdateCallback> EVENT = EventFactory.createArrayBacked(MouseUpdateCallback.class,
            (listeners) -> (double cursorDeltaX, double cursorDeltaY) -> {
                for (MouseUpdateCallback listener : listeners) {
                    listener.updateMouse(cursorDeltaX, cursorDeltaY);
                }
            });

    void updateMouse(double cursorDeltaX, double cursorDeltaY);

}
