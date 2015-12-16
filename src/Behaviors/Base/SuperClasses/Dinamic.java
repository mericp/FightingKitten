package Behaviors.Base.SuperClasses;

import Behaviors.Base.Interfaces.IDinamic;

public class Dinamic implements IDinamic{
    private final Velocity velocity;
    private final Acceleration acceleration;

    public Dinamic()
    {
        velocity = new Velocity();
        acceleration = new Acceleration();
    }

    public Velocity getVelocity()
    {
        return velocity;
    }

    public Acceleration getAcceleration()
    {
        return acceleration;
    }
}

