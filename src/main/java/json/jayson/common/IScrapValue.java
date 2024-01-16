package json.jayson.common;

import json.jayson.LMNBT;
import net.minecraft.nbt.NbtCompound;

public interface IScrapValue {

    default int getScrapValue() {
        return 0;
    }
    default int getScrapValue(NbtCompound nbt) {
        return nbt.getInt(LMNBT.SCRAP_VALUE);
    }

}
