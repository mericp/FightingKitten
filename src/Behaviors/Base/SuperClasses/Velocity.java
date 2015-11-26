package Behaviors.Base.SuperClasses;

import com.badlogic.gdx.math.Vector2;

public class Velocity
{
    protected Vector2 velocity = new Vector2();
    protected float maxVelocity = 50f;
    protected float modVelocity = 1.0f;
    protected float angularVelocity;
    protected float maxAngularVelocity = 5f;

    public Vector2 get() {
        return velocity;
    }

    public float getMax()
    {
        return  maxVelocity;
    }

    public float getModular() {
        return modVelocity;
    }

    public float getAngular() {
        return angularVelocity;
    }

    public float getMaxAngular() {
        return maxAngularVelocity;
    }

    public void setMax(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public void setModular(float modVelocity) {
        this.modVelocity = modVelocity;
    }

    public void setAngular(float angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public void setMaxAngular(float maxAngularVelocity) {
        this.maxAngularVelocity = maxAngularVelocity;
    }
}
