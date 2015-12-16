package Behaviors.Base.SuperClasses;

import com.badlogic.gdx.math.Vector2;

public class Velocity
{
    private final Vector2 vector;
    private float max;
    private float angular;
    private float maxAngular;

    public Velocity()
    {
        vector = new Vector2();
        max = 50f;
        maxAngular = 5f;
    }

    public Vector2 get() {
        return vector;
    }

    public float getMax()
    {
        return  max;
    }

    public float getAngular() {
        return angular;
    }

    public float getMaxAngular() {
        return maxAngular;
    }

    public void setMax(float maxVelocity) {
        this.max = maxVelocity;
    }

    public void setAngular(float angularVelocity) {
        this.angular = angularVelocity;
    }

    public void setMaxAngular(float maxAngularVelocity) {
        this.maxAngular = maxAngularVelocity;
    }
}
