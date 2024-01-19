package json.jayson.common;

import json.jayson.util.LMNBT;
import net.minecraft.nbt.NbtCompound;

public interface IScrapValue {

    default int getScrapValue() {
        return 0;
    }
    default int getScrapValue(NbtCompound nbt) {
        if(nbt == null || !nbt.contains(LMNBT.SCRAP_VALUE)) return getScrapValue();
        return nbt.getInt(LMNBT.SCRAP_VALUE);
    }

}
