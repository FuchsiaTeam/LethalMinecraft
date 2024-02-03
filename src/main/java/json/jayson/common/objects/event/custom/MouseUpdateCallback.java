package json.jayson.common.objects.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface MouseUpdateCallback {

    Event<MouseUpdateCallback> EVENT = EventFactory.createArrayBacked(MouseUpdateCallback.class,
            (listeners) -> (double cursorDeltaX, double cursorDeltaY) -> {
                for (MouseUpdateCallback listener : listeners) {
                    listener.updateMouse(cursorDeltaX, cursorDeltaY);
                }
            });

    void updateMouse(double cursorDeltaX, double cursorDeltaY);

}
