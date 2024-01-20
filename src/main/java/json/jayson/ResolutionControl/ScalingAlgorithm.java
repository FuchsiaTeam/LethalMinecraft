package json.jayson.ResolutionControl;

import net.minecraft.text.Text;
import org.lwjgl.opengl.GL11;

public enum ScalingAlgorithm {
    NEAREST(GL11.GL_NEAREST, GL11.GL_NEAREST_MIPMAP_NEAREST),
    LINEAR(GL11.GL_LINEAR, GL11.GL_LINEAR_MIPMAP_NEAREST);

    private final int id;
    private final int idMipped;

    ScalingAlgorithm(int id, int idMipped) {
        this.id = id;
        this.idMipped = idMipped;
    }

    public int getId(boolean mipped) {
        return mipped ? idMipped : id;
    }
}