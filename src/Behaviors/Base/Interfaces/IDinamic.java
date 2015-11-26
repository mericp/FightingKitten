package Behaviors.Base.Interfaces;

import Behaviors.Base.SuperClasses.Acceleration;
import Behaviors.Base.SuperClasses.Velocity;

public interface IDinamic {
    Velocity getVelocity();
    Acceleration getAcceleration();
}
