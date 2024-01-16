package json.jayson;

import net.minecraft.util.Identifier;

import java.util.Random;

public class LMUtil {

    public static Random RANDOM = new Random();

    public static Identifier createLocation(String name) {
        return new Identifier(LM.ID, name);
    }

}
