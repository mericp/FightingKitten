package Objects.Kitten.MVC;

import SteerableBehavior.AI.Automaton;
import Objects.Waypoint.Waypoint;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;

public class KittenModel extends Automaton
{
    private Waypoint wayPoint;

    public KittenModel(Vector2 position)
    {
        super();

        wayPoint = new Waypoint(position);

        setPosition(position);
        setMotion();
        setBehavior();
    }

    private void setMotion()
    {
        setMaxLinearSpeed(50);
        setMaxLinearAcceleration(100);

        setTagged(true);
    }

    private void setBehavior()
    {
        Arrive behavior = new Arrive(this, wayPoint);

        behavior.setArrivalTolerance(0.1f);
        behavior.setDecelerationRadius(5);

        setSteeringBehavior(behavior);
    }

    public void dragged(Vector2 clickPosition)
    {
        wayPoint.setPosition(clickPosition);
    }
}
