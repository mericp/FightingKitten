package Behaviors.Catalog.Avoid.Behavior;

import Behaviors.Catalog.Avoid.Rays.RayStar;
import Behaviors.Catalog.Pursue.CollisionDetector.CollisionDetector;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.math.Vector2;

public class AvoidWall extends SteeringBehavior<Vector2>{
    private final RayStar radar;
    private final RaycastCollisionDetector<Vector2> wallCollisionDetector;

    public AvoidWall(Steerable<Vector2> owner)
    {
        super(owner);
        radar = new RayStar(owner);
        wallCollisionDetector = new CollisionDetector();
    }

    @Override
    protected SteeringAcceleration<Vector2> calculateRealSteering(SteeringAcceleration<Vector2> steeringAcceleration) {
        radar.updateRays();

        Vector2 direction = new Vector2();

        float minDistance = 0;
        int counter = 0;

        for(Ray ray : radar.getRays())
        {
            if(collides(ray))
            {
                direction.add((Vector2)ray.end);

                float distance = ((Vector2) ray.end).dst((Vector2)ray.start);

                if(minDistance == 0 || minDistance > distance)
                {
                    minDistance = distance;
                }

                counter++;
            }
        }

        direction.nor();

        if(counter > 0)
        {
            int minDistColision = 4;

            float maxAcceleration = (minDistColision * minDistColision) * owner.getMaxLinearAcceleration();
            minDistance = minDistance - owner.getBoundingRadius();
            float magnitude = maxAcceleration / (float)Math.pow(minDistance, 2);

            owner.getLinearVelocity().add(direction.scl(magnitude));
            steeringAcceleration.angular = 0;
        }

        return null;
    }

    private boolean collides(Ray ray)
    {
        return (wallCollisionDetector.collides(ray));
    }
}
