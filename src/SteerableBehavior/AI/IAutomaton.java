package SteerableBehavior.AI;

import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public interface IAutomaton {
    void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior);
    void setIndependentFacing (boolean b);
}
