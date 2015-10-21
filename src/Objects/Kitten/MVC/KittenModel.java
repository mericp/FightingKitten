package Objects.Kitten.MVC;

import DB.StringRes.NotificationsDictionary;
import Objects.Base.BaseMob.AbstractMob;
import PhysicalObjects.DynamicObject;
import PhysicalObjects.StaticObject;
import com.badlogic.gdx.math.Vector2;

public class KittenModel extends AbstractMob
{
    private StaticObject wayPoint;

    public KittenModel(DynamicObject body, StaticObject wayPoint)
    {
        super(body);
        setWayPoint(wayPoint);
    }

    private void setWayPoint(StaticObject wayPoint)
    {
        this.wayPoint = wayPoint;
        wayPoint.getBody().setUserData(this);
    }

    public StaticObject getWayPoint()
    {
        return this.wayPoint;
    }

    public void onCollide() {
        dynamicBody.setLinearVelocity(0f);
        notifyUpdate(NotificationsDictionary.ANIMATION_CHANGED, null);
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(x, y);
        wayPoint.setPosition(x, y);
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
