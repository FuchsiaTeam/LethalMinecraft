package json.jayson.common.objects.item;

import json.jayson.common.IScrapValue;
import json.jayson.common.IWeight;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public interface IAmScrapLoot extends ILMItem, IScrapValue, IWeight {

    SoundEvent getPickUpSound();

}
