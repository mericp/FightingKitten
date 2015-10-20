package Objects.Kitten.MVC;

import DB.MySettings;
import DB.NotificationsDictionary;
import Objects.Base.BaseMob.AbstractMob;
import PhysicalObjects.PhysicalObjectsFactory;
import PhysicalObjects.StaticObject;
import SteerableBehavior.Interfaces.ICollisionable;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class KittenModel extends AbstractMob implements ICollisionable
{
    private StaticObject wayPoint;

    public KittenModel(World w)
    {
        super(w, MySettings.TILE_WIDTH, MySettings.TILE_HEIGHT);
        setWayPoint();
    }

    private void setWayPoint()
    {
        wayPoint = (StaticObject)PhysicalObjectsFactory.create(StaticObject.class, world, 1, 1);
        wayPoint.getBody().setUserData(this);
    }

    public StaticObject getWayPoint()
    {
        return this.wayPoint;
    }

    @Override
    public Rectangle get() {
        return null;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public void setHeight(int height) {

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
        super.setPosition(x, y);
        wayPoint.setPosition(x, y);
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
