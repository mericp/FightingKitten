package Behaviors.Catalog.Pursue.Rays;

import Behaviors.Base.Interfaces.ISteerable;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector2;

public class RayTargetConfiguration implements IRayTargetConfiguration{
    protected final ISteerable owner;
    protected final Ray ray;

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