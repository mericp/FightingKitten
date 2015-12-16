package Objects.Base;

import Behaviors.Base.SuperClasses.SteerableAgent;
import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMob extends SteerableAgent
{
    public AbstractMob(Vector2 position, boolean pursuable, float maxLinearSpeed, float maxLinearAcceleration) {
        super(position, pursuable, maxLinearSpeed, maxLinearAcceleration);
    }
}
