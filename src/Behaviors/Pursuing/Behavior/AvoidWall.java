package Behaviors.Pursuing.Behavior;

import Behaviors.Pursuing.Rays.RayStar;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public class AvoidWall extends SteeringBehavior<Vector2>{
    private final RayStar lookAround;

    public AvoidWall(Steerable<Vector2> owner)
    {
        super(owner);
        lookAround = new RayStar(owner);
    }

    @Override
    protected SteeringAcceleration<Vector2> calculateRealSteering(SteeringAcceleration<Vector2> steeringAcceleration) {
        return null;
    }
}
