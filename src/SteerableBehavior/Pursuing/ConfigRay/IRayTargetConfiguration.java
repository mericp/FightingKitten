package SteerableBehavior.Pursuing.ConfigRay;

import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;
import java.util.Iterator;

public interface IRayTargetConfiguration {
    Iterator<Ray<Vector2>> updateTarget (float x, float y);
}
