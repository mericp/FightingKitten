package Objects.Catalog.World.MVC;

import DB.StringRes.NotificationsDictionary;
import Objects.Base.MobDTO;
import Objects.Base.AbstractMob;
import Behaviors.Base.SuperClasses.Automaton;
import Behaviors.Base.Interfaces.IAutomaton;
import Behaviors.Base.SuperClasses.Observable;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

public class WorldModel extends Observable
{
    private final List<AbstractMob> mobModelArray = new ArrayList<>();
    private final World world;

    public WorldModel()
    {
        world = new World(new Vector2(0, 0), false);
    }

    public void addMob(MobDTO mob)
    {
        mobModelArray.add(mob.model);
        notifyUpdate(NotificationsDictionary.MOB_ADDED, mob);
    }

    public World getWorld()
    {
        return world;
    }

    public void updateAI(float delta)
    {
        for(AbstractMob mobModel : mobModelArray)
        {
            if(mobModel instanceof IAutomaton)
            {
                IAutomaton a = (Automaton)mobModel;

                if (a.getSteeringBehavior() != null)
                {
                    a.calculateSteering(delta);
                }
            }
        }
    }
}