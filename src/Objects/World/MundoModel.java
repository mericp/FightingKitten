package Objects.World;

import DB.NotificationsDictionary;
import Listeners.WaypointListener;
import Objects.Base.BaseModel.AbstractModel;
import Objects.Base.BaseModel.IMobModel;
import Objects.Base.MobDTO;
import SteerableBehavior.AI.Automaton;
import SteerableBehavior.AI.IAutomaton;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

public class MundoModel extends AbstractModel
{
    private final List<IMobModel> mobModelArray = new ArrayList<>();

    public MundoModel()
    {
        super(new World(new Vector2(0, 0), false));
        world.setContactListener(new WaypointListener());
    }

    public void addMob(MobDTO mob)
    {
        mobModelArray.add(mob.model);
        notifyUpdate(NotificationsDictionary.MOB_ADDED, mob);
    }

    public World getMundo()
    {
        return world;
    }

    public void saveLastPosition()
    {
        for(IMobModel mobModel : mobModelArray)
        {
            mobModel.getDynamicBody().saveLastPosition();
        }
    }

    public void updateAI(float delta)
    {
        for(IMobModel mobModel : mobModelArray)
        {
            if(mobModel instanceof Automaton)
            {
                Automaton a = (Automaton)mobModel;
                if (a.getSteeringBehavior() != null) { a.calculateSteering(delta); }
            }
        }
    }

    public void interpolatePositions(float alpha)
    {
        for(IMobModel mobModel : mobModelArray)
        {
            mobModel.interpolatePositions(alpha);
        }
    }
}