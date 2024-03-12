package json.jayson.client.model.geo;

import json.jayson.common.objects.entity.coil_head.CoilHeadEntity;
import json.jayson.util.LMIdentifier;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CoilHeadModel extends GeoModel<CoilHeadEntity> {

    public static final Identifier TEXTURE = LMIdentifier.texture("entity/coil_head/coil_head.png");

    @Override
    public Identifier getModelResource(CoilHeadEntity animatable) {
        return LMIdentifier.geo("coil_head.geo.json");
    }

    @Override
    public Identifier getTextureResource(CoilHeadEntity animatable) {
        return TEXTURE;
    }

    @Override
    public Identifier getAnimationResource(CoilHeadEntity animatable) {
        return LMIdentifier.animation("coil_head.animation.json");
    }
}
