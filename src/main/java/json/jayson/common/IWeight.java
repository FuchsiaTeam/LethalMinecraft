package json.jayson.common;

import json.jayson.util.LMNBT;
import net.minecraft.nbt.NbtCompound;

public interface IWeight {

    float getWeight();
    default float getWeight(NbtCompound nbt) {
        if(nbt == null || !nbt.contains(LMNBT.WEIGHT)) return getWeight();
        return nbt.getFloat(LMNBT.WEIGHT);
    }

}
