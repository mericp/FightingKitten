package SteerableBehavior.AI;

import SteerableBehavior.SteerableAgent;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

public abstract class Automaton extends SteerableAgent implements IAutomaton{
    protected SteeringBehavior<Vector2> steeringBehavior;
    protected SteeringAcceleration<Vector2> steeringOutput;

    protected boolean independentFacing = false;
    public float newOrientation;
    public float newX;
    public float newY;

    public Automaton() { steeringOutput = new SteeringAcceleration<>(new Vector2()); }

    @Override
    public abstract void onCollide();

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
        //Posicion:
        newX = position.getX() + getLinearVelocity().x * delta;
        newY = position.getY() + getLinearVelocity().y * delta;
        setPosition(newX, newY);

        //Velocidad:
        getLinearVelocity().mulAdd(steering.linear, delta).limit(getMaxLinearSpeed());

        //Orientacion:
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

