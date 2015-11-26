package Behaviors.Base.SuperClasses;

import Behaviors.Base.Interfaces.ISteerable;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SteerableAgent implements ISteerable {
    protected Vector2 position;
    protected Orientable orientation;
    protected Dinamic motion;
    protected Collisionable hitbox;
    protected Pursuable pursuable;

    public SteerableAgent(Vector2 position, boolean pursuable, float maxLinearSpeed, float maxLinearAcceleration)
    {
        super ();
        hitbox = new Collisionable();
        orientation = new Orientable();
        motion = new Dinamic();
        this.pursuable = new Pursuable(pursuable);

        changePosition(position);
        setMotion(maxLinearSpeed, maxLinearAcceleration);
    }

    private void setMotion(float maxLinearSpeed, float maxLinearAcceleration)
    {
        setMaxLinearSpeed(maxLinearSpeed);
        setMaxLinearAcceleration(maxLinearAcceleration);
    }

    @Override public Vector2 newVector() { return new Vector2(); }

    @Override
    public float vectorToAngle(Vector2 vector) {
        float angulo =(float)Math.atan2(vector.y, vector.x);
        return (angulo + MathUtils.PI2) % MathUtils.PI2;
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = (float)Math.cos(angle);
        outVector.y = (float)Math.sin(angle);

        return outVector;
    }

    @Override public void setMaxLinearSpeed(float maxLinearSpeed) { motion.getVelocity().setMax(maxLinearSpeed); }
    @Override public void setMaxLinearAcceleration(float maxAcelLineal) { motion.getAcceleration().setMax(maxAcelLineal); }
    @Override public void setMaxAngularSpeed(float maxAngularSpeed) { motion.getVelocity().setMaxAngular(maxAngularSpeed); }
    @Override public void setMaxAngularAcceleration(float maxAcelAngular) { motion.getAcceleration().setMaxAngular(maxAcelAngular); }
    protected void changePosition(Vector2 newPosition)
    {
        if(position == null)
        {
            position = new Vector2();
        }

        position.set(newPosition);
        hitbox.get().setCenter(newPosition);

        if (pursuable.is())
        {
            pursuable.addSmellTrailAt(newPosition);
        }
    }

    @Override public Vector2 getPosition() { return position; }
    @Override public float getOrientation() { return orientation.get(); }
    @Override public Vector2 getLinearVelocity() { return motion.getVelocity().get(); }
    @Override public float getAngularVelocity() { return motion.getVelocity().getAngular(); }
    @Override public float getBoundingRadius() { return (hitbox.getWidth() + hitbox.getHeight()) / 4; }

    @Override public boolean isTagged() { return false; }
    @Override public void setTagged(boolean b) {}

    @Override public float getMaxLinearSpeed() { return motion.getVelocity().getMax(); }
    @Override public float getMaxLinearAcceleration() { return motion.getAcceleration().getMax(); }
    @Override public float getMaxAngularSpeed() { return motion.getVelocity().getMaxAngular(); }
    @Override public float getMaxAngularAcceleration() { return motion.getAcceleration().getMaxAngular(); }
    public Pursuable getPursuable(){ return pursuable; }
}
