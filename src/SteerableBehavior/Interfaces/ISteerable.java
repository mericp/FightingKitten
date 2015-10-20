package SteerableBehavior.Interfaces;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;

public interface ISteerable extends Steerable<Vector2>{
    boolean isTagged();
    void setTagged(boolean tagged);
}
