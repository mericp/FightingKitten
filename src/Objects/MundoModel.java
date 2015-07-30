package Objects;

import DB.NotificationsDictionary;
import DTOs.MobDto;
import Entities.WaypointListener;
import Models.AbstractModel;
import Models.IMobModel;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;

public class MundoModel extends AbstractModel
{
    private final World mundo;
    private final List<IMobModel> mobModelArray = new ArrayList<>();

    public MundoModel()
    {
        mundo = new World(new Vector2(0, 0), false);
        mundo.setContactListener(new WaypointListener());
    }

    public void addMob(MobDto mob)
    {
        mobModelArray.add(mob.model);
        notifyUpdate(NotificationsDictionary.MOB_ADDED, mob);
    }

    public World getMundo()
    {
        return mundo;
    }

    public void saveLastPosition()
    {
        for(IMobModel mobModel : mobModelArray)
        {
            mobModel.getDynamicBody().saveLastPosition();
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