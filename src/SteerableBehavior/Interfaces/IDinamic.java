package SteerableBehavior.Interfaces;

import SteerableBehavior.Base.Acceleration;
import SteerableBehavior.Base.Velocity;

public interface IDinamic {
    Velocity getVelocity();
    Acceleration getAcceleration();
}
