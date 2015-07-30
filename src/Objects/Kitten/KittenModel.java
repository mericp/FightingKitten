package Objects.Kitten;

import Objects.Base.BaseDto.PositionDTO;
import DB.MySettings;
import DB.NotificationsDictionary;
import Entities.ICollisionable;
import Objects.Base.BaseModel.AbstractModel;
import Objects.Base.BaseModel.IMobModel;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.PhysicalObjectsFactory;
import PhysicalObjects.StaticObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class KittenModel extends AbstractModel implements ICollisionable, IMobModel
{
    private final World mundo;
    private DynamicObject dynamicBody;
    private StaticObject wayPoint;

    public KittenModel(World mundo)
    {
        this.mundo = mundo;

        setDynamicBody();
        setWayPoint();
    }

    private void setDynamicBody()
    {
        dynamicBody = (DynamicObject)PhysicalObjectsFactory.create(DynamicObject.class, mundo, MySettings.KITTEN_HITBOX_WIDTH, MySettings.KITTEN_HITBOX_HEIGHT);
        dynamicBody.getBody().setUserData(this);
    }

    private void setWayPoint()
    {
        wayPoint = (StaticObject)PhysicalObjectsFactory.create(StaticObject.class, mundo, 1, 1);
        wayPoint.getBody().setUserData(this);
    }

    @Override
    public DynamicObject getDynamicBody()
    {
        return this.dynamicBody;
    }

    public StaticObject getWayPoint()
    {
        return this.wayPoint;
    }

    @Override
    public void onCollide()
    {
        this.dynamicBody.setLinearVelocity(0f);
        this.notifyUpdate(NotificationsDictionary.ANIMATION_CHANGED, null);
    }

    @Override
    public void setPosition(float x, float y)
    {
        dynamicBody.setPosition(x, y);
        wayPoint.setPosition(x, y);
        this.notifyUpdate(NotificationsDictionary.POSITION_SET, new PositionDTO(x, y));
    }

    @Override
    public void interpolatePositions(float alpha)
    {
        dynamicBody.interpolatePositions(alpha);
        this.notifyUpdate(NotificationsDictionary.POSITION_INTERPOLATED, null);
    }

    public void goToCoords(float x, float y)
    {
        dynamicBody.setDirectionVector(x, y); //Angle
        dynamicBody.setLinearVelocity(80f); //Velocity
    }

    public void dragged(Vector2 clickPosition)
    {
        getWayPoint().setPosition(clickPosition.x, clickPosition.y);
        goToCoords(getWayPoint().getBottomLeftCornerX(), getWayPoint().getBottomLeftCornerY());
    }
}
