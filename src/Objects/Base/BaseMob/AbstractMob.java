package Objects.Base.BaseMob;

import DB.StringRes.NotificationsDictionary;
import PhysicalObjects.DynamicObject;
import SteerableBehavior.Base.Steerable;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMob extends Steerable
{
    protected DynamicObject dynamicBody;

    public AbstractMob(DynamicObject dynamicBody) {
        super();
        setDynamicBody(dynamicBody);
    }

    protected void setDynamicBody(DynamicObject dynamicBody)
    {
        this.dynamicBody = dynamicBody;
        dynamicBody.getBody().setUserData(this);
    }

    public DynamicObject getDynamicBody() {
        return dynamicBody;
    }

    public void interpolatePositions(float alpha)
    {
        dynamicBody.interpolatePositions(alpha);
        notifyUpdate(NotificationsDictionary.POSITION_INTERPOLATED, null);
    }

    public void setPosition(float x, float y) {
        dynamicBody.setPosition(x, y);
        notifyUpdate(NotificationsDictionary.POSITION_SET, new Vector2(x, y));
    }
}
