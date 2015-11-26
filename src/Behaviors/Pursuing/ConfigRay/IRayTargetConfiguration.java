package Behaviors.Pursuing.ConfigRay;

import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;

public interface IRayTargetConfiguration {
    Ray<Vector2> updateTarget (float x, float y);
}
