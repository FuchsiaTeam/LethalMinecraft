package json.jayson.client.render.geo;

import json.jayson.client.model.geo.ShotgunModel;
import json.jayson.common.item.ShotgunItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShotgunRenderer extends GeoItemRenderer<ShotgunItem> {

    public ShotgunRenderer() {
        super(new ShotgunModel());
    }

}
