package json.jayson.common.objects.item;

import json.jayson.common.IScrapValue;
import json.jayson.common.IWeight;
import net.minecraft.sound.SoundEvent;

public interface IAmScrapLoot extends ILMItem, IScrapValue, IWeight {

    SoundEvent getPickUpSound();

}
