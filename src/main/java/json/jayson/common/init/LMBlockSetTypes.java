package json.jayson.common.init;

import net.minecraft.block.BlockSetType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class LMBlockSetTypes {

    public static final BlockSetType DUNGEON_DOOR = new BlockSetType("dungeon", false, false, false, BlockSetType.ActivationRule.EVERYTHING, BlockSoundGroup.METAL,  LMSounds.DOOR_CLOSE_0,  LMSounds.DOOR_OPEN_0, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON);
    public static final BlockSetType DUNGEON_DOOR_ALT = new BlockSetType("dungeon_alt", false, false, false, BlockSetType.ActivationRule.EVERYTHING, BlockSoundGroup.METAL,  LMSounds.DOOR_CLOSE_1,  LMSounds.DOOR_OPEN_1, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON);



}
