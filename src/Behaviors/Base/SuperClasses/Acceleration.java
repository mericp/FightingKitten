package Behaviors.Base.SuperClasses;

public class Acceleration
{
    protected float maxAcceleration = 200f;
    protected float maxAngularAcceleration = 5f;

    public float getMax() {
        return maxAcceleration;
    }

    public float getMaxAngular() {
        return maxAngularAcceleration;
    }

    public void setMax(float maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public void setMaxAngular(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }
}
