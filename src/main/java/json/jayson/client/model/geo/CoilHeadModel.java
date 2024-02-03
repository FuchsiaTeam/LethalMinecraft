package json.jayson.client.model.geo;

import json.jayson.util.LMUtil;
import json.jayson.common.objects.entity.coil_head.CoilHeadEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CoilHeadModel extends GeoModel<CoilHeadEntity> {

    public static final Identifier TEXTURE = LMUtil.LMIdentifier.texture("entity/coil_head/coil_head.png");

    @Override
    public Identifier getModelResource(CoilHeadEntity animatable) {
        return LMUtil.LMIdentifier.geo("coil_head.geo.json");
    }

    @Override
    public Identifier getTextureResource(CoilHeadEntity animatable) {
        return TEXTURE;
    }

    @Override
    public Identifier getAnimationResource(CoilHeadEntity animatable) {
        return LMUtil.LMIdentifier.animation("coil_head.animation.json");
    }
}
