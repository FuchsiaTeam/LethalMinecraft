package json.jayson.client.render.item;

import json.jayson.client.model.geo.ShotgunModel;
import json.jayson.common.objects.item.tools.ranged.shotgun.ShotgunItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShotgunRenderer extends GeoItemRenderer<ShotgunItem> {

    public ShotgunRenderer() {
        super(new ShotgunModel());
    }

}
