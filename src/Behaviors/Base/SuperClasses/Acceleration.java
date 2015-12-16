package Behaviors.Base.SuperClasses;

public class Acceleration
{
    private float maxAcceleration = 200f;
    private float maxAngularAcceleration = 5f;

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
