package Objects.Base.BaseMob;

import DB.NotificationsDictionary;
import Objects.Base.BaseDto.PositionDTO;
import Objects.Base.BaseModel.IMobModel;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import SteerableBehavior.SteerableAgent;
import com.badlogic.gdx.physics.box2d.World;

public class AbstractMob extends SteerableAgent implements IMobModel
{
    protected DynamicObject dynamicBody;

    public AbstractMob(World w, int dynamicBodyWidth, int dynamicBodyHeight) {
        super(w);
        setDynamicBody(dynamicBodyWidth, dynamicBodyHeight);
    }

    protected void setDynamicBody(int width, int height)
    {
        dynamicBody = (DynamicObject) PhysicalObjectsFactory.create(DynamicObject.class, world, width, height);
        dynamicBody.getBody().setUserData(this);
    }

    @Override
    public DynamicObject getDynamicBody() {
        return dynamicBody;
    }

    @Override
    public void interpolatePositions(float alpha) {

    }

    @Override
    public void setPosition(float x, float y) {
        dynamicBody.setPosition(x, y);
        this.notifyUpdate(NotificationsDictionary.POSITION_SET, new PositionDTO(x, y));
    }
}
