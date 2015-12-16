package Objects.Catalog.Monster.MVC;

import Behaviors.Base.SuperClasses.Automaton;
import Behaviors.Base.SuperClasses.SteerableAgent;
import Behaviors.Catalog.BehaviorsFactory;
import Behaviors.Catalog.Pursue.Behavior.Target;
import Behaviors.Catalog.Pursue.Rays.RayTargetSingle;
import com.badlogic.gdx.ai.steer.behaviors.BlendedSteering;
import com.badlogic.gdx.math.Vector2;

public class MonsterModel extends Automaton {
    private Target target;
    private final BlendedSteering<Vector2> advancedSteering;

    public MonsterModel(Vector2 position)
    {
        super(position, true, 50, 100);
        target = new Target(this, new RayTargetSingle(this));
        advancedSteering = new BlendedSteering(this);
        setDefaultBehaviors();
    }

    @Override public void calculateSteering(float delta)
    {
        steeringBehavior.calculateSteering(steeringOutput);
        applySteering(steeringOutput, delta);

        pursuable.updateSmellTrail(delta);
    }

    private void setDefaultBehaviors()
    {
        addAvoidBehavior();
        setSteeringBehavior(advancedSteering);
    }

    public void changeTarget(SteerableAgent target)
    {
        this.target = new Target(target, new RayTargetSingle(this));
        addPursueBehavior();
    }

    private void addAvoidBehavior()
    {
        advancedSteering.add(BehaviorsFactory.COLLECTION.avoidWall(this),1f);
    }
    private void addPursueBehavior()
    {
        advancedSteering.add(BehaviorsFactory.COLLECTION.pursue(this, this.target),1f);
    }
}
