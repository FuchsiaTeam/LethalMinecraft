package json.jayson.client.render.entity;

import json.jayson.client.model.CoilHeadModel;
import json.jayson.common.entity.coil_head.CoilHeadEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
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
