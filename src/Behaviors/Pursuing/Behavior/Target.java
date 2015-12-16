package Behaviors.Pursuing.Behavior;

import Behaviors.Base.SuperClasses.SteerableAgent;
import Behaviors.Pursuing.Rays.IRayTargetConfiguration;
import com.badlogic.gdx.math.Vector2;

public class Target {
    private Vector2 alternativeTarget;
    private final IRayTargetConfiguration rayTargetConfig;
    private final SteerableAgent target;

    public Target(SteerableAgent target, IRayTargetConfiguration rayTargetConfig)
    {
        this.target = target;
        this.alternativeTarget = target.getPosition();
        this.rayTargetConfig = rayTargetConfig;
    }

    public SteerableAgent get()
    {
        return target;
    }

    public Vector2 getAlternativeTargetCoords() {
        return alternativeTarget;
    }

    public void setAlternativeTargetCoords(Vector2 alternativeTarget) {
        this.alternativeTarget = alternativeTarget;
    }

    public IRayTargetConfiguration getRayConfig() {
        return rayTargetConfig;
    }
}
