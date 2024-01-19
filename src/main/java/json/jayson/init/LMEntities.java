package json.jayson.init;

import json.jayson.util.LMUtil;
import json.jayson.common.entity.ScrapLootEntity;
import json.jayson.common.entity.coil_head.CoilHeadEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class LMEntities {

    public static final EntityType<CoilHeadEntity> COIL_HEAD = registerEntity("coil_head", SpawnGroup.MONSTER, CoilHeadEntity::new, 1, 2);

    public static final EntityType<ScrapLootEntity> SCRAP_LOOT = registerEntity("scrap_loot", SpawnGroup.MISC, ScrapLootEntity::new, 1, 0.5f);

    public static <T extends Entity> EntityType<T> registerEntity(String name, SpawnGroup spawnGroup, EntityType.EntityFactory<T> entity,
                                                                  float width, float height) {
        return Registry.register(Registries.ENTITY_TYPE, LMUtil.createLocation(name), FabricEntityTypeBuilder.create(spawnGroup, entity).dimensions(EntityDimensions.changing(width, height)).build());
    }

}
