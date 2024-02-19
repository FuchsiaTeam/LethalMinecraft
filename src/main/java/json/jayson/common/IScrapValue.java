package json.jayson.common;

import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.util.LMConfig;
import json.jayson.util.LMNBT;
import net.minecraft.nbt.NbtCompound;

public interface IScrapValue {

    default int getScrapValue() {
        return 0;
    }
    default int getScrapValue(NbtCompound nbt) {
        if(nbt == null || !nbt.contains(LMNBT.Int.SCRAP_VALUE)) return getScrapValue();
        return nbt.getInt(LMNBT.Int.SCRAP_VALUE);
    }

    default int getGrabTime() { return ClientEndTickListener.maxPickupCharge; }
}
