package Objects.Dragon.MVC;

import Objects.Base.BaseMob.AbstractMob;
import PhysicalObjects.StaticObject;
import com.badlogic.gdx.physics.box2d.World;

public class DragonModel extends AbstractMob{
    private StaticObject kittenradar;

    public DragonModel(World w)
    {
        super(w, 260, 200);
        setRadar();
    }

    private void setRadar()
    {
        //kittenradar = (StaticObject)PhysicalObjectsFactory.create(StaticObject.class, mundo, (int)dynamicBody.getWidth() + 10, (int)dynamicBody.getHeight() + 10);
        //kittenradar.getBody().setUserData(this);

    }
}
