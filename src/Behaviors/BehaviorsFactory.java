package Behaviors;

import Behaviors.Pursuing.Behavior.Pursue;
import Behaviors.Pursuing.Behavior.Target;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;

public enum BehaviorsFactory {
    COLLECTION()
    {
        @Override public SteeringBehavior pursue(Steerable owner, Target target)
        {
            return new Pursue(owner, target);
        }

        @Override public SteeringBehavior arrive(Steerable owner, Steerable target, float arrivalTolerance, float decelerationRadius)
        {
            Arrive behavior = new Arrive(owner, target);

            behavior.setArrivalTolerance(arrivalTolerance);
            behavior.setDecelerationRadius(decelerationRadius);

            return behavior;
        }
    };

    public abstract SteeringBehavior pursue(Steerable owner, Target target);
    public abstract SteeringBehavior arrive(Steerable owner, Steerable target, float arrivalTolerance, float decelerationRadius);

    BehaviorsFactory() {}
}
