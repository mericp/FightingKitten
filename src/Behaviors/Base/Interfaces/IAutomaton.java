package Behaviors.Base.Interfaces;

import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public interface IAutomaton {
    void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior);
    SteeringBehavior<Vector2> getSteeringBehavior();
    void setIndependentFacing (boolean b);
    void calculateSteering(float delta);
}
