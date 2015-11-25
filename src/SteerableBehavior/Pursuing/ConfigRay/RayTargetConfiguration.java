package SteerableBehavior.Pursuing.ConfigRay;

import SteerableBehavior.Interfaces.ISteerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;

public class RayTargetConfiguration implements IRayTargetConfiguration{
    protected ISteerable owner;
    protected Ray ray;

    public RayTargetConfiguration(ISteerable owner)
    {
        this.owner = owner;
        ray = new Ray(owner.newVector(), owner.newVector());
    }

    public Ray<Vector2> getRay()
    {
        return ray;
    }

    @Override
    public Ray<Vector2> updateTarget(float x, float y) {
        return null;
    }
}