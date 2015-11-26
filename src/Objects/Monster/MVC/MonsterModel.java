package Objects.Monster.MVC;

import Behaviors.Base.SuperClasses.Automaton;
import Behaviors.Base.SuperClasses.SteerableAgent;
import Behaviors.BehaviorsFactory;
import Behaviors.Pursuing.Behavior.Target;
import Behaviors.Pursuing.ConfigRay.RayTargetSingle;
import com.badlogic.gdx.math.Vector2;

public class MonsterModel extends Automaton {
    private Target target;

    public MonsterModel(Vector2 position)
    {
        super(position, true, 50, 100);
        this.target = new Target(this, new RayTargetSingle(this));
    }

    @Override public void calculateSteering(float delta)
    {
        steeringBehavior.calculateSteering(steeringOutput);
        applySteering(steeringOutput, delta);
        pursuable.updateSmellTrail(delta);
    }

    public void setTarget(SteerableAgent target)
    {
        this.target = new Target(target, new RayTargetSingle(this));
        setSteeringBehavior(BehaviorsFactory.COLLECTION.pursue(this, this.target));
    }

    public Target getTarget()
    {
        return target;
    }
}
