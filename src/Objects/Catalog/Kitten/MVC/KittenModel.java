package Objects.Catalog.Kitten.MVC;

import Behaviors.Base.SuperClasses.Automaton;
import Behaviors.Catalog.Arrive.Waypoint;
import Behaviors.Catalog.BehaviorsFactory;
import com.badlogic.gdx.math.Vector2;

public class KittenModel extends Automaton
{
    private final Waypoint wayPoint;

    public KittenModel(Vector2 position)
    {
        super(position, true, 50, 100);

        wayPoint = new Waypoint(position);

        setSteeringBehavior(BehaviorsFactory.COLLECTION.arrive(this, wayPoint, 0.1f, 5));
    }

    public void dragged(Vector2 clickPosition)
    {
        wayPoint.dropAt(clickPosition);
    }
}
