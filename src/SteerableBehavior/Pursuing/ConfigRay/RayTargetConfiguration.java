package SteerableBehavior.Pursuing.ConfigRay;

import SteerableBehavior.Interfaces.ISteerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;
import java.util.Arrays;
import java.util.Iterator;

public class RayTargetConfiguration implements IRayTargetConfiguration{
    protected ISteerable owner;
    protected Ray<Vector2>[] rays;

    public RayTargetConfiguration(ISteerable owner)
    {
        this.owner = owner;
    }

    public void setRays(int numRays)
    {
        rays = new Ray[numRays];

        for (int i = 0; i < numRays; i++)
        {
            rays[i] = new Ray(owner.newVector(), owner.newVector());
        }
    }

    public Iterator<Ray<Vector2>> getRays()
    {
        return Arrays.asList(rays).iterator();
    }

    @Override
    public Iterator<Ray<Vector2>> updateTarget(float x, float y) {
        return null;
    }
}