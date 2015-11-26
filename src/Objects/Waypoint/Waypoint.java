package Objects.Waypoint;

import Behaviors.Base.SuperClasses.SteerableAgent;
import com.badlogic.gdx.math.Vector2;

public class Waypoint extends SteerableAgent {
    public Waypoint(Vector2 position)
    {
        super(position, false, 0, 0);
    }

    public void dropAt(Vector2 position)
    {
        changePosition(position);
    }
}
