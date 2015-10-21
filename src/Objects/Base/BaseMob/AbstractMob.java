package Objects.Base.BaseMob;

import DB.StringRes.NotificationsDictionary;
import SteerableBehavior.Base.Steerable;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMob extends Steerable
{
    public AbstractMob() {
        super();
    }

    public void setPosition(float x, float y) {
        super.setPosition(new Vector2(x, y));
        notifyUpdate(NotificationsDictionary.POSITION_SET, new Vector2(x, y));
    }
}
