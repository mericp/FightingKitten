package Behaviors.Base.SuperClasses;

import Behaviors.Base.Interfaces.IAutomaton;
import Objects.Base.AbstractMob;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public abstract class Automaton extends AbstractMob implements IAutomaton {
    protected SteeringBehavior<Vector2> steeringBehavior;
    protected final SteeringAcceleration<Vector2> steeringOutput;

    public Automaton(Vector2 position, boolean pursuable, float maxLinearSpeed, float maxLinearAcceleration)
    {
        super(position, pursuable, maxLinearSpeed, maxLinearAcceleration);
        steeringOutput = new SteeringAcceleration<>(new Vector2());
    }

    @Override public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) { this.steeringBehavior = steeringBehavior; }
    @Override public SteeringBehavior<Vector2> getSteeringBehavior() { return steeringBehavior; }
    @Override public void calculateSteering(float delta)
    {
        steeringBehavior.calculateSteering(steeringOutput);
        applySteering(steeringOutput, delta);
        pursuable.updateSmellTrail(delta);
    }

    protected void applySteering(SteeringAcceleration<Vector2> steering, float delta)
    {
        changePosition(calculateNewPosition(delta));
        setVelocity(steering, delta);
        setOrientation(delta);
    }

    private Vector2 calculateNewPosition(float delta)
    {
        return new Vector2(position.x + getLinearVelocity().x * delta, position.y + getLinearVelocity().y * delta);
    }

    private void setVelocity(SteeringAcceleration<Vector2> steering, float delta)
    {
        if (steering.isZero())
        {
            motion.getVelocity().get().setZero();
        }

        getLinearVelocity().mulAdd(steering.linear, delta).limit(getMaxLinearSpeed());
    }

    private void setOrientation(float delta)
    {
        float newOrientation;

        if ( getLinearVelocity().isZero(0.1f))
        {
            newOrientation = getOrientation();
        }
        else
        {
            newOrientation = vectorToAngle(getLinearVelocity());
        }

        if (newOrientation != getOrientation())
        {
            motion.getVelocity().setAngular((newOrientation - getOrientation()) * delta);
            orientation.set(newOrientation);
        }
    }
}

