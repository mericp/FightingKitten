package SteerableBehavior.Pursuing.ConfigRay;

import SteerableBehavior.Interfaces.ISteerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;
import java.util.Iterator;

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