package json.jayson.common.init;

import com.mojang.serialization.Codec;

import json.jayson.util.LMIdentifier;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class LMDataAttachments {

    public static final AttachmentType<Integer> STAMINA = AttachmentRegistry.<Integer>builder()
            .persistent(Codec.INT)
            .copyOnDeath()
            .initializer(() -> 100)
            .buildAndRegister(LMIdentifier.data("stamina"));

    public static final AttachmentType<Float> WEIGHT = AttachmentRegistry.<Float>builder()
            .persistent(Codec.FLOAT)
            .initializer(() -> 0.0f)
            .buildAndRegister(LMIdentifier.data("weight"));

}
