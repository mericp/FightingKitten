package Behaviors.Pursuing.Rays;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.ai.utils.Ray;

public class RayStar implements RayConfiguration<Vector2>
{
    private final Steerable<Vector2> owner;
    private final Ray<Vector2>[] star;

    public RayStar(Steerable owner)
    {
        this.owner = owner;
        star = new Ray[6];

        setRays();
}

    private void setRays()
    {
        star[0] = new Ray(new Vector2(), new Vector2());

        for (int i = 1; i < star.length; i++)
        {
            this.star[i] = new Ray(star[0].start, new Vector2());
        }
    }

    @Override public Ray<Vector2>[] updateRays()
    {
        owner.angleToVector(Vector(), VelocityAngle() - Angle());

        Origin();
        North();
        Northeast();
        Southeast();
        South();
        Southwest();
        Northwest();

        return star;
    }

    private Vector2 Vector()
    {
        Vector2 vector = new Vector2();

        vector.set(owner.getLinearVelocity()).nor();

        return vector;
    }

    private float VelocityAngle()
    {
        return owner.vectorToAngle(owner.getLinearVelocity());
    }
    private float Angle()
    {
        return 60 * MathUtils.degreesToRadians;
    }
    private float Length()
    {
        return owner.getBoundingRadius() + owner.getBoundingRadius();
    }

    private void Origin() { star[0].start.set(owner.getPosition()); }
    private void North()
    {
        setRay(0, Length());
    }
    private void Northeast()
    {
        setRay(1, Length());
    }
    private void Southeast()
    {
        setRay(2, -Length());
    }
    private void South()
    {
        setRay(3, -Length());
    }
    private void Southwest()
    {
        setRay(4, -Length());
    }
    private void Northwest()
    {
        setRay(5, Length());
    }

    private void setRay(int index, float length)
    {
        star[index].end.set(Vector()).scl(length).add(owner.getPosition());
    }
}