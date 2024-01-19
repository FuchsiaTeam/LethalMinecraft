package json.jayson.init;

import json.jayson.LM;
import json.jayson.LMUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class LMSounds {
	
	 public static final SoundEvent SMACK = registerSoundEvent("item_shovel_hit");
	
	private static SoundEvent registerSoundEvent(String name)
	{
		Identifier id = LMUtil.createLocation(name);
		return Registry.register(Registries.SOUND_EVENT,id,SoundEvent.of(id));
		
	}
	
	public static void register() {}

}
