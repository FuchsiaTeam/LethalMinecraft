package json.jayson.client.model;

import json.jayson.LMUtil;
import json.jayson.common.entity.coil_head.CoilHeadEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CoilHeadModel extends GeoModel<CoilHeadEntity> {

    public static final Identifier TEXTURE = LMUtil.createLocation("textures/entity/coil_head/coil_head.png");

    @Override
    public Identifier getModelResource(CoilHeadEntity animatable) {
        return LMUtil.createLocation("geo/coil_head.geo.json");
    }

    @Override
    public Identifier getTextureResource(CoilHeadEntity animatable) {
        return TEXTURE;
    }

    @Override
    public Identifier getAnimationResource(CoilHeadEntity animatable) {
        return LMUtil.createLocation("animations/coil_head.animation.json");
    }
}
