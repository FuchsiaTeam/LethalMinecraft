package json.jayson.common.init;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import json.jayson.common.objects.codec.PlayerWeightCodec;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Supplier;

public class LMCodecs {

    public static final Supplier<Codec<PlayerWeightCodec>> PLAYER_WEIGHT = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> inst.group(
                    Codecs.POSITIVE_INT.optionalFieldOf("current", 0).forGetter(m -> m.current))
            .and(Codecs.POSITIVE_INT.optionalFieldOf("max", 1).forGetter(m -> m.max))
            .apply(inst, PlayerWeightCodec::new)));

}
