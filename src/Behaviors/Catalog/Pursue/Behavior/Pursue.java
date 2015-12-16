package Behaviors.Catalog.Pursue.Behavior;

import Behaviors.Base.SuperClasses.SmellTrail;
import Behaviors.Catalog.Pursue.CollisionDetector.RayWallDetector;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;
import java.util.Iterator;

public class Pursue extends SteeringBehavior<Vector2>
{
    private final Target target;
    private final RaycastCollisionDetector<Vector2> wallCollisionDetector;

    public Pursue(Steerable owner, Target target)
    {
        super(owner);

        this.target = target;
        wallCollisionDetector = new RayWallDetector();
    }

    @Override protected SteeringAcceleration<Vector2> calculateRealSteering (SteeringAcceleration<Vector2> accelerationVector)
    {
        SteeringAcceleration<Vector2> realSteering;

        if(CanMoveStraightForward())
        {
            realSteering = accelerateTowardsTheTarget(accelerationVector, target.get().getPosition());
        }
        else if (CanMoveToLastSmellTrail())
        {
            realSteering = accelerateTowardsTheTarget(accelerationVector, target.getAlternativeTargetCoords());
        }
        else
        {
            realSteering = accelerationVector.setZero();
        }

        return realSteering;
    }

    private boolean CanMoveStraightForward()
    {
        return !collides(target.get().getPosition().x, target.get().getPosition().y);
    }

    private boolean CanMoveToLastSmellTrail()
    {
        Iterator<SmellTrail> iterator = target.get().getPursuable().getSmellTrailsIterator();

        while (iterator.hasNext())
        {
            SmellTrail smellTrail = iterator.next();

            if (!collides(smellTrail.center.x, smellTrail.center.y))
            {
                target.setAlternativeTargetCoords(smellTrail.center);
                return true;
            }
        }

        return false;
    }

    private SteeringAcceleration<Vector2> accelerateTowardsTheTarget (SteeringAcceleration<Vector2> accelerationVector, Vector2 targetPosition)
    {
        accelerationVector.linear.set(targetPosition).sub(owner.getPosition()).nor().scl(linearAcceleration());
        accelerationVector.angular = 0;

        return accelerationVector;
    }

    private float linearAcceleration()
    {
        return getActualLimiter().getMaxLinearAcceleration();
    }

    private boolean collides(float x, float y)
    {
        Ray<Vector2> ray = target.getRayConfig().updateTarget(x, y);
        return (wallCollisionDetector.collides(ray));
    }
}
