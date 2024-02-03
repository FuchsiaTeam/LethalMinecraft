package json.jayson.client.model.geo;

import json.jayson.common.item.tools.ranged.shotgun.ShotgunItem;
import json.jayson.util.LMUtil;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ShotgunModel extends GeoModel<ShotgunItem> {
    @Override
    public Identifier getModelResource(ShotgunItem animatable) {
        return LMUtil.createLocation("geo/shotgun.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShotgunItem animatable) {
        return LMUtil.createLocation("textures/item/3d/shotgun/oak/oak_shotgun.png");
    }

    @Override
    public Identifier getAnimationResource(ShotgunItem animatable) {
        return LMUtil.createLocation("animations/shotgun.animation.json");
    }
}
