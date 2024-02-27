package json.jayson.common.init;

import json.jayson.util.LMIdentifier;
import json.jayson.util.LMUtil;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class LMDimensions {

    /* MOONS */
    public static final RegistryKey<DimensionOptions> MOON_EXPERIMENTATION_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION, LMIdentifier.lm("experimentation"));
    public static final RegistryKey<World> MOON_EXPERIMENTATION_WORLD = RegistryKey.of(RegistryKeys.WORLD, LMIdentifier.lm("experimentation"));
    public static final RegistryKey<DimensionType> MOON_EXPERIMENTATION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, LMIdentifier.lm("experimentation_type"));



    /* HERE SPAWNS THE STRUCTURES */
    public static final RegistryKey<DimensionOptions> LOOT_DUNGEON_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION, LMIdentifier.lm("loot_dungeon"));
    public static final RegistryKey<World> LOOT_DUNGEON = RegistryKey.of(RegistryKeys.WORLD, LMIdentifier.lm("loot_dungeon"));
    public static final RegistryKey<DimensionType> LOOT_DUNGEON_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, LMIdentifier.lm("loot_dungeon"));

}
