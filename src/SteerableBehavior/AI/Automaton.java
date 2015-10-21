package SteerableBehavior.AI;

import Objects.Base.BaseMob.AbstractMob;
import PhysicalObjects.DynamicObject;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public abstract class Automaton extends AbstractMob implements IAutomaton{
    protected SteeringBehavior<Vector2> steeringBehavior;
    protected SteeringAcceleration<Vector2> steeringOutput;

    protected boolean independentFacing = false;
    public float newOrientation;
    public float newX;
    public float newY;

    public Automaton(DynamicObject body)
    {
        super(body);
        steeringOutput = new SteeringAcceleration<>(new Vector2());
    }

    @Override public void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior) { this.steeringBehavior = steeringBehavior; }
    public SteeringBehavior<Vector2> getSteeringBehavior() { return steeringBehavior; }

    @Override public void setIndependentFacing (boolean b) { independentFacing = b; }

    public void calculateSteering(float delta)
    {
        steeringBehavior.calculateSteering(steeringOutput);
        applySteering(steeringOutput, delta);
    }

    private void applySteering(SteeringAcceleration<Vector2> steering, float delta)
    {
        //Position:
        newX = position.x + getLinearVelocity().x * delta;
        newY = position.y + getLinearVelocity().y * delta;
        setPosition(new Vector2(newX, newY));

        //Velocity:
        getLinearVelocity().mulAdd(steering.linear, delta).limit(getMaxLinearSpeed());

        //Orientation:
        if (independentFacing)
        {
            newOrientation = getOrientation() + getAngularVelocity() * delta;
            motion.getVelocity().setAngular(getAngularVelocity() + steering.angular * delta);
            orientation.set(newOrientation);
        }
        else
        {
            if ( getLinearVelocity().isZero(0.1f))
            {
                newOrientation = getOrientation();
            }
            else
            {
                newOrientation = vectorToAngle(getLinearVelocity());
            }

            if (newOrientation != getOrientation()) {
                motion.getVelocity().setAngular((newOrientation - getOrientation()) * delta);
                orientation.set(newOrientation);
            }
        }
    }
}

