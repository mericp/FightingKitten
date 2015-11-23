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
        setRays(1);
    }

    public Iterator<Ray<Vector2>> updateTarget (float x, float y)
    {
        rays[0].start.set(owner.getPosition());
        rays[0].end.set(x, y);

        return getRays();
    }

}