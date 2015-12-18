package Behaviors.Catalog.Avoid.Rays;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.utils.RayConfiguration;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.ai.utils.Ray;

public class RayStar implements RayConfiguration<Vector2>
{
    private final Steerable<Vector2> owner;
    private final Ray[] star;

    private enum rays
    {
        COLLECTION()
        {
            @Override public int NORTH(){return 0;}
            @Override public int NORTHEAST(){return 1;}
            @Override public int SOUTHEAST(){return 2;}
            @Override public int SOUTH(){return 3;}
            @Override public int SOUTHWEST(){return 4;}
            @Override public int NORTHWEST(){return 5;}
        };

        public abstract int NORTH();
        public abstract int NORTHEAST();
        public abstract int SOUTHEAST();
        public abstract int SOUTH();
        public abstract int SOUTHWEST();
        public abstract int NORTHWEST();

        rays(){}
    }

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

        updateRays();
    }

    public Ray[] getRays()
    {
        return star;
    }

    @Override public Ray[] updateRays()
    {
        final Vector2 vector = Vector();

        SetOrigin();

        North(vector);
        South(vector);

        owner.angleToVector(vector, VelocityAngle() - Angle());

        Northeast(vector);
        Southwest(vector);

        owner.angleToVector(vector, VelocityAngle() + Angle());

        Southeast(vector);
        Northwest(vector);

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

    private void SetOrigin() { star[0].start.set(owner.getPosition()); }
    private void North(Vector2 vector){ setRay(rays.COLLECTION.NORTH(), vector, Length()); }
    private void Northeast(Vector2 vector)
    {
        setRay(rays.COLLECTION.NORTHEAST(), vector, Length());
    }
    private void Southeast(Vector2 vector)
    {
        setRay(rays.COLLECTION.SOUTHEAST(), vector, -Length());
    }
    private void South(Vector2 vector)
    {
        setRay(rays.COLLECTION.SOUTH(), vector, -Length());
    }
    private void Southwest(Vector2 vector)
    {
        setRay(rays.COLLECTION.SOUTHWEST(), vector, -Length());
    }
    private void Northwest(Vector2 vector)
    {
        setRay(rays.COLLECTION.NORTHWEST(), vector, Length());
    }

    private void setRay(int index, Vector2 vector, float length)
    {
        star[index].end.set(vector).scl(length).add(owner.getPosition());
    }
}