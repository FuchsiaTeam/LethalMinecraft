package json.jayson.common.objects.event.custom;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.network.ClientPlayerEntity;

public interface ClientPlayerMovementCallback {

    Event<ClientPlayerMovementCallback> EVENT = EventFactory.createArrayBacked(ClientPlayerMovementCallback.class,
            (listeners) -> (player) -> {
                for (ClientPlayerMovementCallback listener : listeners) {
                    listener.tickMovement(player);
                }
            });

    void tickMovement(ClientPlayerEntity player);

}
