package json.jayson;

import net.minecraft.util.Identifier;

public class LMUtil {

    public static Identifier createLocation(String name) {
        return new Identifier(LM.ID, name);
    }

}
