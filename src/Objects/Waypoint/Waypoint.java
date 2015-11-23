package Objects.Waypoint;

import SteerableBehavior.Base.SteerableAgent;
import com.badlogic.gdx.math.Vector2;

public class Waypoint extends SteerableAgent {
    public Waypoint(Vector2 position)
    {
        setPosition(position);
    }
}
