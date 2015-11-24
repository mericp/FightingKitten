package SteerableBehavior.Pursuing.Behavior;

import SteerableBehavior.Base.SmellTrail;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;
import java.util.Iterator;

public class Pursue extends SteeringBehavior<Vector2>
{
    protected Target target;
    protected RaycastCollisionDetector<Vector2> wallCollisionDetector;

    public Pursue(Steerable<Vector2> owner,
                  Target target,
                  RaycastCollisionDetector<Vector2> wallCollisionDetector)
    {
        super(owner);

        this.target = target;
        this.wallCollisionDetector = wallCollisionDetector;
    }

    @Override protected SteeringAcceleration<Vector2> calculateRealSteering (SteeringAcceleration<Vector2> accelerationVector)
    {
        SteeringAcceleration<Vector2> realSteering;

        if(CanMoveStraightForward() || CanMoveToLastSmellTrail())
        {
            realSteering = accelerateTowardsTheTarget(accelerationVector);
        }
        else
        {
            realSteering = accelerationVector.setZero();
        }

        return realSteering;
    }

    private boolean CanMoveStraightForward()
    {
        return !collides(target.getCoords().x, target.getCoords().y);
    }

    private boolean CanMoveToLastSmellTrail()
    {
        Iterator<SmellTrail> iterator = target.getSteerableAgent().pursuable.getSmellTrails().iterator();

        while (iterator.hasNext())
        {
            SmellTrail smellTrail = iterator.next();

            if (!collides(smellTrail.center.x, smellTrail.center.y))
            {
                target.setCoords(smellTrail.center);
                return true;
            }
        }

        return false;
    }

    private SteeringAcceleration<Vector2> accelerateTowardsTheTarget (SteeringAcceleration<Vector2> accelerationVector)
    {
        // Try to match the position of the character with the position of the target by calculating
        // the direction to the target and by moving toward it as fast as possible.
        accelerationVector.linear.set(target.getCoords()).sub(owner.getPosition()).nor().scl(linearAcceleration());
        accelerationVector.angular = 0;

        return accelerationVector;
    }

    private float linearAcceleration()
    {
        return getActualLimiter().getMaxLinearAcceleration();
    }

    private boolean collides(float x, float y)
    {
        Iterator<Ray<Vector2>> ray = target.getRayConfig().updateTarget(x, y);

        while (ray.hasNext())
        {
            if (wallCollisionDetector.collides(ray.next())) return true;
        }

        return false;
    }
}
