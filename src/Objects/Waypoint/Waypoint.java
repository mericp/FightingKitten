package Objects.Waypoint;

import SteerableBehavior.Base.Steerable;
import com.badlogic.gdx.math.Vector2;

public class Waypoint extends Steerable{
    public Waypoint(){
        super();
    }

    public Waypoint(Vector2 position)
    {
        setPosition(position);
    }
}
