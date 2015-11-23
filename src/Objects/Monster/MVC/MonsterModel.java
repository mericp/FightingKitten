package Objects.Monster.MVC;

import Objects.Base.BaseMob.AbstractMob;
import SteerableBehavior.AI.Automaton;
import SteerableBehavior.Pursuing.Behavior.Pursue;
import SteerableBehavior.Pursuing.Behavior.Target;
import SteerableBehavior.Pursuing.CollisionDetector.RayWallDetector;
import SteerableBehavior.Pursuing.ConfigRay.RayTargetSingle;
import com.badlogic.gdx.math.Vector2;

public class MonsterModel extends Automaton {
    private Target target;

    public MonsterModel(Vector2 position)
    {
        super();

        setPosition(position);
        setMaxLinearSpeed(50);
        setMaxLinearAcceleration(100);
    }

    public void setTarget(AbstractMob target)
    {
        this.target = new Target(this, target.getPosition(), new RayTargetSingle(this));
        setBehavior();
    }

    private void setBehavior()
    {
        setSteeringBehavior(new Pursue(this, target, new RayWallDetector()));
    }
}
