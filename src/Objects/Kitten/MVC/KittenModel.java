package Objects.Kitten.MVC;

import SteerableBehavior.AI.Automaton;
import SteerableBehavior.AI.Waypoint;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;

public class KittenModel extends Automaton
{
    private Waypoint wayPoint;

    public KittenModel(Waypoint wayPoint)
    {
        super();
        this.wayPoint = wayPoint;

        configMotion();

        setSteeringBehavior(configArrive());
    }

    private void configMotion()
    {
        motion.getVelocity().setMax(50);
        motion.getAcceleration().setMax(100);
    }

    private Arrive configArrive()
    {
        Arrive behavior = new Arrive(this, wayPoint);

        behavior.setArrivalTolerance(1);
        behavior.setDecelerationRadius(50);

        return behavior;
    }

    public void dragged(Vector2 clickPosition)
    {
        wayPoint.setPosition(clickPosition);
    }
}
