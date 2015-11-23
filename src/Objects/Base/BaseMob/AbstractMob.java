package Objects.Base.BaseMob;

import SteerableBehavior.Base.SteerableAgent;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMob extends SteerableAgent
{
    public AbstractMob() {
        super();
    }

    public void setPosition(float x, float y) {
        super.setPosition(new Vector2(x, y));
    }
}
