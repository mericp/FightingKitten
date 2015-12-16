package Behaviors.Pursuing.Rays;

import Behaviors.Base.Interfaces.ISteerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;

public class RayTargetSingle extends RayTargetConfiguration
{
    public RayTargetSingle(ISteerable owner)
    {
        super(owner);
    }

    public Ray<Vector2> updateTarget (float x, float y)
    {
        ray.start.set(owner.getPosition());
        ray.end.set(new Vector2(x, y));

        return getRay();
    }
}