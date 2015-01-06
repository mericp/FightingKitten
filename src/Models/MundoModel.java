package Models;

import DB.NotificationsDictionary;
import DTOs.MundoDTOs;
import Entities.WaypointListener;
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

    public void addMobModel(IMobModel mobModel)
    {
        mobModelArray.add(mobModel);
        this.notifyUpdate(NotificationsDictionary.KITTEN_ADDED, new MundoDTOs.AddKittenDTO(kittenModel));
    }

    public World getMundo()
    {
        return mundo;
    }

    public void saveLastPosition()
    {
        for(KittenModel kittenModel : mobModelArray)
        {
            kittenModel.getDynamicBody().saveLastPosition();
        }
    }

    public void interpolatePositions(float alpha)
    {
        for(KittenModel kittenModel : mobModelArray)
        {
            kittenModel.interpolatePositions(alpha);
        }
    }
}