package json.jayson.init;

import json.jayson.LMUtil;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class LMDimensions {

    public static final RegistryKey<DimensionOptions> MOON_EXPERIMENTATION_OPTIONS = RegistryKey.of(RegistryKeys.DIMENSION, LMUtil.createLocation("experimentation"));
    public static final RegistryKey<World> MOON_EXPERIMENTATION_WORLD = RegistryKey.of(RegistryKeys.WORLD, LMUtil.createLocation("experimentation"));
    public static final RegistryKey<DimensionType> MOON_EXPERIMENTATION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, LMUtil.createLocation("experimentation_type"));

}
