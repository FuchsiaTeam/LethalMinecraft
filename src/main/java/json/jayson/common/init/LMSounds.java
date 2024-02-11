package json.jayson.common.init;

import json.jayson.util.LMUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class LMSounds {
	
	public static final SoundEvent SMACK = registerSoundEvent("item_shovel_hit");
	public static final SoundEvent SCAN = registerSoundEvent("scan");
	public static final SoundEvent PICKUP_METAL = registerSoundEvent("pickup_metal");
	public static final SoundEvent PICKUP_PLASTIC = registerSoundEvent("pickup_plastic");

	public static final SoundEvent DOOR_OPEN_0 = registerSoundEvent("door_open1");
	public static final SoundEvent DOOR_OPEN_1 = registerSoundEvent("door_open2");
	public static final SoundEvent DOOR_CLOSE_0 = registerSoundEvent("door_close1");
	public static final SoundEvent DOOR_CLOSE_1 = registerSoundEvent("door_close2");


	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = LMUtil.createLocation(name);
		return Registry.register(Registries.SOUND_EVENT,id,SoundEvent.of(id));
	}
	
	public static void register() {}

}
