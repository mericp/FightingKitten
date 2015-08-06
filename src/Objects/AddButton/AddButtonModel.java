package Objects.AddButton;

import DB.MySettings;
import DB.NotificationsDictionary;
import Objects.Base.BaseDto.PositionDTO;
import Objects.Base.BaseModel.AbstractModel;
import Objects.Base.BaseModel.IMobModel;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import com.badlogic.gdx.physics.box2d.World;

public class AddButtonModel extends AbstractModel implements IMobModel
{
    private final World mundo;
    private DynamicObject dynamicBody;

    public AddButtonModel(World mundo)
    {
        this.mundo = mundo;
        setDynamicBody();
    }

    private void setDynamicBody()
    {
        dynamicBody = (DynamicObject) PhysicalObjectsFactory.create(DynamicObject.class, mundo, MySettings.KITTEN_HITBOX_WIDTH, MySettings.KITTEN_HITBOX_HEIGHT);
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
