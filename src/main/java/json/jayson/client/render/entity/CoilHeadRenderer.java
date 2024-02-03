package json.jayson.client.render.entity;

import json.jayson.client.model.geo.CoilHeadModel;
import json.jayson.common.objects.entity.coil_head.CoilHeadEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CoilHeadRenderer extends GeoEntityRenderer<CoilHeadEntity> {
    public CoilHeadRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CoilHeadModel());
    }

    @Override
    public Identifier getTexture(CoilHeadEntity animatable) {
        return CoilHeadModel.TEXTURE;
    }



}
