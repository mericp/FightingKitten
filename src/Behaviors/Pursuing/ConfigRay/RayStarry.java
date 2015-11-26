package Behaviors.Pursuing.ConfigRay;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.ai.utils.Ray;

public class RayStarry implements RayConfiguration<Vector2>
{
    protected Steerable<Vector2> owner;
    protected Ray<Vector2>[] rays;

    private float length;
    private final float angle = 60 * MathUtils.degreesToRadians;
    private Vector2 vector;

    public RayStarry(Steerable owner)
    {
        this.owner = owner;
        setRays();
        setLength(owner);
        vector = new Vector2();
    }

    private void setRays()
    {
        rays = new Ray[6];
        rays[0] = new Ray(new Vector2(), new Vector2());

        for (int i = 1; i < rays.length; i++)
        {
            this.rays[i] = new Ray(rays[0].start, new Vector2());
        }
    }

    private void setLength(Steerable owner)
    {
        length = owner.getBoundingRadius() + owner.getBoundingRadius();
    }

    @Override public Ray<Vector2>[] updateRays()
    {
        float velocityAngle = owner.vectorToAngle(owner.getLinearVelocity());

        //Front ray:
        rays[0].start.set(owner.getPosition());
        vector.set(owner.getLinearVelocity()).nor();
        setRayAt(0, 3);

        //Front-left ray:
        owner.angleToVector(vector, velocityAngle - angle);
        setRayAt(1, 4);

        //Front-right ray:
        owner.angleToVector(vector, velocityAngle + angle);
        setRayAt(2, 5);

        return rays;
    }

    private void setRayAt(int indexo, int indexe)
    {
        rays[indexo].end.set(vector).scl(length).add(owner.getPosition());
        rays[indexe].end.set(vector).scl(-length).add(owner.getPosition());
    }

    public Steerable<Vector2> getOwner ()
    {   return owner; }

    public void setOwner (Steerable<Vector2> owner)
    {   this.owner = owner; }

    public Ray<Vector2>[] getRays ()
    {   return rays; }
}