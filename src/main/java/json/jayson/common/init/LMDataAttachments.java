package json.jayson.common.init;

import com.mojang.serialization.Codec;
import json.jayson.LM;
import json.jayson.util.LMIdentifier;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.util.Identifier;

public class LMDataAttachments {

    public static final AttachmentType<Integer> STAMINA = AttachmentRegistry.<Integer>builder()
            .persistent(Codec.INT)
            .copyOnDeath()
            .initializer(() -> 100)
            .buildAndRegister(LMIdentifier.data("stamina"));

    public static final AttachmentType<Integer> WEIGHT = AttachmentRegistry.<Integer>builder()
            .persistent(Codec.INT)
            .copyOnDeath()
            .initializer(() -> 0)
            .buildAndRegister(LMIdentifier.data("weight"));

}
